package com.virtusa.kafka.broker.stream.commodity;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.KafkaStreamBrancher;
import org.springframework.kafka.support.serializer.JsonSerde;

import com.virtusa.kafka.broker.message.OrderMessage;
import com.virtusa.kafka.broker.message.OrderPatternMessage;
import com.virtusa.kafka.broker.message.OrderRewardMessage;
import com.virtusa.kafka.util.CommodityStreamUtil;

//@Configuration
public class CommodityFiveStream {

	private static final Logger LOG = LoggerFactory.getLogger(CommodityFiveStream.class);

	@Bean
	public KStream<String, OrderMessage> kstreamCommodityTrading(StreamsBuilder builder) {
		var stringSerde = Serdes.String();
		var orderSerde = new JsonSerde<>(OrderMessage.class);
		var orderPatternSerde = new JsonSerde<>(OrderPatternMessage.class);
		var orderRewardSerde = new JsonSerde<>(OrderRewardMessage.class);
		
		var maskedCreditCardStream = builder
										.stream("t-commodity-order", Consumed.with(stringSerde, orderSerde))
										.mapValues(CommodityStreamUtil::maskCreditCard);
		
//		var patternStreams = maskedCreditCardStream
//									.mapValues(CommodityStreamUtil::mapToOrderPattern)
//									.branch(CommodityStreamUtil.isPlastic(), (k, v) -> true);
		
		final var branchProducer = Produced.with(stringSerde, orderPatternSerde);
		
		new KafkaStreamBrancher<String, OrderPatternMessage>()
				.branch(
						CommodityStreamUtil.isPlastic(), 
						kstream -> kstream.to("t-commodity-pattern-five-plastic", branchProducer))
				.defaultBranch(kstream -> kstream.to("t-commodity-pattern-five-nonplastic", branchProducer))
				.onTopOf(maskedCreditCardStream.mapValues(CommodityStreamUtil::mapToOrderPattern));
		
//		var plasticIndex = 0;
//		var nonPlasticIndex = 1;
									
		//plastic
//		patternStreams[plasticIndex]
//				.to("t-commodity-pattern-two-plastic", Produced.with(stringSerde, orderPatternSerde));
//		//non plastic
//		patternStreams[nonPlasticIndex]
//				.to("t-commodity-pattern-two-nonplastic", Produced.with(stringSerde, orderPatternSerde));
		
		var rewardStream = maskedCreditCardStream
									.filter(CommodityStreamUtil.isLargeQuantity())
									.filter(CommodityStreamUtil.isCheap())
									.map(CommodityStreamUtil.mapToOrderRewardChangeKey());
		
		rewardStream.to("t-commodity-reward-five", Produced.with(stringSerde, orderRewardSerde));
		
		//maskedCreditCardStream.to("t-commodity-storage-two",  Produced.with(stringSerde, orderSerde));
		
		var storageStream = maskedCreditCardStream
				.selectKey(CommodityStreamUtil.generateStoragekey());
		
		storageStream.to("t-commodity-storage-five", Produced.with(stringSerde, orderSerde));
		
		//4th sink
		maskedCreditCardStream.filter(
				(k,v) -> v.getOrderLocation()
						.toUpperCase()
						.startsWith("C")
				).foreach(
						(k, v) -> this.reportFraud(v)
				);
		
		return maskedCreditCardStream;
	}
	
	private void reportFraud(OrderMessage v) {
		LOG.info("Reporting Fraud {}", v);
	}
}
