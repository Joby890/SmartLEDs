package com.josephbrumaghim.smartleds;

public abstract class LEDScript {

	private LEDManager man;

	public LEDScript(LEDManager man) {
		this.man = man;
	}
	
	public abstract void update();
	
	
	
	
	
	
	public LEDManager getManager() {
		return man;
	}
	
}
