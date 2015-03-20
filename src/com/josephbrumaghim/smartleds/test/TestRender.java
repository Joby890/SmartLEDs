package com.josephbrumaghim.smartleds.test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import com.josephbrumaghim.smartleds.LED;

public class TestRender {
	
	private JFrame frame;
	private Dimension dim = new Dimension(500, 500);
	private List<LED> leds = new ArrayList<>();
	
	public TestRender() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(500, 500);
		frame.setPreferredSize(dim);
		frame.setMinimumSize(dim);
		frame.setMaximumSize(dim);
		frame.setVisible(true);
		frame.pack();
	}
	

	public void render(List<LED> uled) {
		for(LED l : uled) {
			boolean found = false;
			for(LED to : leds) {
				if(l.getId().equals(to.getId())) {
					found = true;
				}
			}
			if(!found) {
				leds.add(l);
			}
		}
		BufferStrategy bs = frame.getBufferStrategy();
		if (bs == null) {
			frame.createBufferStrategy(3);
			return;
		}

		Graphics2D g2 = (Graphics2D) bs.getDrawGraphics();
		g2.setColor(Color.WHITE);
		//g2.fillRect(0, 0, 650, 650);
		for(LED l : leds) {
			
			g2.setColor(l.col);
			g2.fillOval(l.getIndex() * 36 - 15, 250, 35, 35);
			
		}
		
		bs.show();
		
		
	}
	
	

}
