package org.ow2.aspirerfid.ale.core.helloworld;


public class SimpleServiceImpl implements Helloworld {

	public String sayHello(String msg) {
		return "Hello " + msg;
	}

	public String saluer(String name) {
		return "Bonjour " + name;
	}
}