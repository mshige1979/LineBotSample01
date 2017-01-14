package com.example.message.template;

import java.util.List;

import com.example.message.template.actions.Actions;

public class Confirm extends Template {
	public String type = "confirm";
	public String text;
	public List<Actions> actions;
}
