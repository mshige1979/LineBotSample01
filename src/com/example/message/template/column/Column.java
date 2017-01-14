package com.example.message.template.column;

import java.util.List;

import com.example.message.template.actions.Actions;

public class Column {
	public String thumbnailImageUrl;
	public String title;
	public String text;
	public List<Actions> actions;
	
	public Column(String text, List<Actions> actions) {
		this.text = text;
		this.actions = actions;
	}
}
