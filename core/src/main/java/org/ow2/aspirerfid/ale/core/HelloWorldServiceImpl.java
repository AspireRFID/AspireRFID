package org.ow2.aspirerfid.ale.core;

public class HelloWorldServiceImpl implements HelloWorldService{

	public String hello(String name) {
		String reply = "Hello " + name;
		return reply;
	}

}
