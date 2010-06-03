package org.ow2.aspirerfid.ide.bpwme.test;

import java.util.ArrayList;
import java.util.HashMap;

import org.ow2.aspirerfid.ide.bpwme.test.Node.NodeType;

public class LocationTree {

	private HashMap<String, HashMap<String, String>> companyMap;
	private HashMap<String, HashMap<String, String>> warehouseMap;
	private HashMap<String, HashMap<String, String>> readpointMap;
	private Node root;
	
	public void setCompanyMap(
			HashMap<String, HashMap<String, String>> companyMap) {
		this.companyMap = companyMap;
	}
	
	public void setReadpointMap(
			HashMap<String, HashMap<String, String>> readpointMap) {
		this.readpointMap = readpointMap;
	}
	
	public void setWarehouseMap(
			HashMap<String, HashMap<String, String>> warehouseMap) {
		this.warehouseMap = warehouseMap;
	}
	
	public LocationTree() {
		root = new Node("root", NodeType.Root);
	}
	
	public void buildTree() {
		if(companyMap.keySet().size() == 0) {
			System.out.println("No Company Map");
			return;
		}
		for(String key : companyMap.keySet()) {
			Node company = new Node(key, NodeType.Company);
			root.addChild(company);
			HashMap<String, String> wmap = companyMap.get(key);
			if(wmap.keySet().size() != 0) {
				String wchildren = wmap.get("WarehouseChildren");
				String[] achildren = wchildren.split(",");
				for(String wstring : achildren) {
					Node newNode = new Node(key + "," + wstring, NodeType.Warehouse);
					company.addChild(newNode);
					buildSubTree(newNode);
				}
			}
		}
	}
	
	public void buildSubTree(Node n) {
		HashMap<String, String> subMap = warehouseMap.get(n.getId());
		if(subMap == null) {
			return;
		}
		if(subMap.keySet().size() == 0) {
			return;
		}
		
		String wchildren = subMap.get("WarehouseChildren");
		if(wchildren != null) {
			String[] childrenList = wchildren.split(",");
			for(int i = 0; i < childrenList.length; i++) {
				Node newNode = new Node(n.getId() + "," + childrenList[i], NodeType.Warehouse);
				n.addChild(newNode);
				buildSubTree(newNode);	
			}	
		}
		
		String rchildren = subMap.get("ReadPoint");
		if(rchildren != null) {
			String[] childrenList = rchildren.split(",");
			for(int i = 0; i < childrenList.length; i++) {
				Node newNode = new Node(n.getId() + "," + childrenList[i], NodeType.ReadPoint);
				n.addChild(newNode);
			}
		}	
	}

	private void printNode(Node n) {
		
		for(int i = 0; i < n.getLevel(); i++) {
			System.out.print("-");
		}
		System.out.println(n);
		for(Node child : n.getChildren()) {
			printNode(child);
		}
	}
	
	public void printNode() {
		printNode(root);
	}
	
	public String[] split(String s) {
		return s.split(",");
	}
}


class Node{
	public enum NodeType{
		Root,
		Company,
		Warehouse,
		ReadPoint
	}
	
	private NodeType type;
	private String id;
	private ArrayList<Node> children;
	private int level;
	
	public Node() {
		children = new ArrayList<Node>();
		level = 0;
	}
	
	public Node(String id, NodeType type) {
		this.id = id;
		this.type = type;
		children = new ArrayList<Node>();
		level = 0;
	}
	
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public void addChild(Node node) {
		children.add(node);
		node.setLevel(level + 1);
	}
	
	public ArrayList<Node> getChildren() {
		return children;
	}
	
	public String toString() {
		return type + " Node:" + id;
	}
}