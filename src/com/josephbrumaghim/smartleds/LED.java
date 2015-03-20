package com.josephbrumaghim.smartleds;

import java.awt.Color;
import java.util.UUID;

public class LED {
	
	public Color col;
	private int index;
	private final String id = UUID.randomUUID().toString();
	
	public LED(int i) {
		col = new Color(250,50,50);
		this.index = i;
	}
	
	
	public int getIndex() {
		return index;
	}


	public String getId() {
		return id;
	}

}
