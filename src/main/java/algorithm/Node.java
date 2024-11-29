package com.algorithms;

import java.util.ArrayList;
import java.util.List;

public class Node {

	private String val;
	private List<Node> children;

	public Node(String val){
		this.val = val;
	}

	public void addChildren(Node node){
		if(children == null)
			children = new ArrayList<>();

		children.add(node);
	}

	public Node getChild(int index){
		if(children != null || children.size() > index)
			return children.get(index);

		return null;
	}

	public List<Node> getChildren(){
		return children;
	}
}
