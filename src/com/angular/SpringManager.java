package com.angular;

public class SpringManager implements ISpring {

	@Override
	public String get() {
		System.out.println("---I am springManager----");
		
		return "I am getMethod";
	}

}