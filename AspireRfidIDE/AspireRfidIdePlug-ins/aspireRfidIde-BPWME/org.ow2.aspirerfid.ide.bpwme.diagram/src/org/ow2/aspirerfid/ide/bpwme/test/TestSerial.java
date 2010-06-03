package org.ow2.aspirerfid.ide.bpwme.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class TestSerial {

	public static void main(String args[]) throws Exception{
		FileOutputStream fout = new FileOutputStream("C:\\Documents and Settings\\Robert\\AspireRFID\\IDE\\APDLs\\test.xml");
		ObjectOutputStream oos = new ObjectOutputStream(fout);
		oos.writeObject(new Class1());
		oos.writeObject(new Class2());
		oos.close();
		FileInputStream fin = new FileInputStream("C:\\Documents and Settings\\Robert\\AspireRFID\\IDE\\APDLs\\test.xml");
		ObjectInputStream ois = new ObjectInputStream(fin);
		System.out.println(ois.readObject());
		System.out.println(ois.readObject());

	}
}

class Class1 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2318326868266379559L;
	int i;
	int j;
	public Class1() {
		i = 1;
		j = 1;
	}
	
	public String toString() {
		return "Class1";
	}
}

class Class2 implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2398385706340211171L;
	int i;
	int j;
	public Class2() {
		i = 2;
		j = 2;
	}
	
	public String toString() {
		return "Class2";
	}
}