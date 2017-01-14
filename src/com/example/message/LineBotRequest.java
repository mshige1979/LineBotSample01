package com.example.message;

import java.util.ArrayList;
import java.util.List;


public class LineBotRequest {
	public String to;
	public List<SendMessage> messages;
	
	public LineBotRequest(){
		this.messages = new ArrayList<SendMessage>();
	}
}
