package com.example.message.template.actions;

public class Actions {
	public String type = "message";
	public String label;
	public String text;
	
	public Actions(String label, String text) {
		this.label = label;
		this.text = text;
	}
}
