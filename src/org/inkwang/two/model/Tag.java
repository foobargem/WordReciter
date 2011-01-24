package org.inkwang.two.model;

public class Tag {

	private String name;
	
	public Tag(String name) {
		setName(name);
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public String toString() {
		return name;
	}
	
}
