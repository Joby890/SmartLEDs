package com.josephbrumaghim.smartleds.test;

import java.awt.Color;

import com.josephbrumaghim.smartleds.LED;
import com.josephbrumaghim.smartleds.LEDManager;
import com.josephbrumaghim.smartleds.LEDScript;

public class TestScript extends LEDScript {

	
	
	public TestScript(LEDManager man) {
		super(man);
		
		
		getManager().setUpdatePerSecond(30);
	}

	@Override
	public void update() {
		LED a = getManager().findLED(1);
		if(a.col.getRed() - 10 < 10) {
			a.col = new Color(255, a.col.getGreen(), a.col.getBlue());
		} else {
			a.col = new Color(a.col.getRed() - 10, a.col.getGreen(), a.col.getBlue());
		}
		//a.col = new Color(a.col.getRed() - 10, a.col.getGreen(), a.col.getBlue());
		for(LED l : getManager().getOrderLeds()) {
			if(l.getIndex() != 1) {
				LED before = getManager().findLED(l.getIndex() - 1);
				if(before.col.getRed() - 10 < 10) {
					l.col = new Color(255, l.col.getGreen(), l.col.getBlue());
				} else {
					l.col = new Color(before.col.getRed() - 10, l.col.getGreen(), l.col.getBlue());
				}
				
			}
		}
		
		getManager().sendUpdate(getManager().getLeds());
		
	}

}
