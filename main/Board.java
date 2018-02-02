package main;

import java.awt.*;
import javax.swing.*;

public class Board extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 659139904644898483L;
	String[] state = new String[9];

	public Board(String s){
		for(int i=0;i<=8;i++)
			state[i] = s.substring(i, i+1);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		
		g.setFont(new Font("Arial",20,20));

		/*Draw grid*/
		g.drawRect(100, 100, 300, 300);
		/*Vertical Lines*/
		g.drawLine(200, 100, 200, 400);
		g.drawLine(300, 100, 300, 400);
		g.drawLine(400, 100, 400, 400);
		/*Horizontal Lines*/
		g.drawLine(100,200,400,200);
		g.drawLine(100,300,400,300);
		
		/*Draw numbers in*/
		for(int i=0;i<=8;i++){
			int xPos = 0;
			int yPos = 0;
			
			if(i%3==0)
				xPos=150;
			else if(i%3==1)
				xPos=250;
			else if(i%3==2)
				xPos=350;
			
			if(i>=0 && i<=2)
				yPos=150;
			else if(i>=3 && i<=5)
				yPos=250;
			else if(i>=6 && i<=8)
				yPos=350;
			
			g.drawString(state[i],xPos ,yPos);
		}
			
	}
}
