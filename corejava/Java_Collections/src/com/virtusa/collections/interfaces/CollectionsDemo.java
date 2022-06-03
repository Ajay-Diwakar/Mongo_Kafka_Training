package com.virtusa.collections.interfaces;

import java.util.ArrayList;
import java.util.Collection;

public class CollectionsDemo {
	public static void main(String[] args) {
		Collection<String> devNames = new ArrayList<>();
		devNames.add("AJAY");
		devNames.add("Lakshith");
		devNames.add("Aishu");
		
		System.out.println(devNames);
		
		//devNames.remove();
		
		System.out.println(devNames.contains("AJAY"));
	}

}
