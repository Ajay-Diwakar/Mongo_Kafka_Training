package com.virtusa.collections.interfaces;

import java.util.ArrayList;
import java.util.List;

public class ListDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<String> list = new ArrayList<>();
		
		list.add("ajay");
		list.add("aishu");
		list.add("ajay");
		list.add("ajay");
		
		System.out.println(list.get(1));
		

	}

}
