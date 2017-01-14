package com.example.message.template;

import java.util.List;

import com.example.message.template.actions.Actions;

public class Buttons extends Template {
	public String type = "buttons";
	public String thumbnailImageUrl;
	public String title;
	public String text;
	public List<Actions> actions;
}
