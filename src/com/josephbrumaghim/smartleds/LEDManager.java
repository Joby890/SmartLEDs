package com.josephbrumaghim.smartleds;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.josephbrumaghim.smartleds.test.TestRender;
import com.josephbrumaghim.smartleds.test.TestScript;

public class LEDManager implements Runnable {
	
	private Thread ledManThread;
	private boolean running = false;
	private List<LED> leds = new ArrayList<>();
	private int updatePerSecond = 75;
	private long last = System.currentTimeMillis();
	private LEDScript script;
	private TestRender render;

	public void start() {
		running = true;
		render = new TestRender();
		System.out.println("Created Render");
		for(int i = 0; i < 12; i++) {
			leds.add(new LED(i + 1));
		}
		System.out.println("Created LEDs");
		ledManThread = new Thread(this);
		ledManThread.start();
		script = new TestScript(this);
	}
	
	public void stop() throws Exception {
		running = false;
		ledManThread.join();
		System.out.println("Stopped");
	}
	
	public void run() {
		System.out.println(running);
		while(running) {
			long current = System.currentTimeMillis();
			if(current - last >= (1000 / updatePerSecond)) {
				last = current;
				script.update();
			}
		}
		
		
	}
	
	public void sendUpdate(List<LED> leds) {
		render.render(leds);
	}
	
	
	
	
	public List<LED> getLeds() {
		return leds;
	}
	
	
	public List<LED> getOrderLeds() {
		int current = 1;
		List<LED> led = new ArrayList<>();
		while(led.size() != leds.size()) {
			for(LED l : leds) {
				if(l.getIndex() == current) {
					led.add(l);
					current++;
				}
			}
		}
		return led;
		
	}
	
	public LED findLED(int index) {
		for(LED l : leds) {
			if(l.getIndex() == index) {
				return l;
			}
		}
		return null;
	}

	public static void main(String[] args) throws Exception {
		Scanner s = new Scanner(System.in);
		LEDManager man = new LEDManager();
		man.start();
		while(true) {
			String line = s.nextLine();
			if(line.toLowerCase().equals("stop")) {
				man.stop();
				s.close();
				break;
			}
		}
		
		
	}

	public void setUpdatePerSecond(int i) {
		updatePerSecond = i;
		
	}
	
	
}
