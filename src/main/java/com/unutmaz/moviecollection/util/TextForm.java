package com.unutmaz.moviecollection.util;

import java.util.ArrayList;
import java.util.List;

public class TextForm {
	private String text;

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public List<Long> StrToIds(){
		List<Long> ids = new ArrayList<>();
		for(String elem : this.text.split(",")) {
			ids.add(Long.parseLong(elem));
		}
		
		return ids;
	}
	
}
