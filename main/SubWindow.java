package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

public class SubWindow extends JFrame {
	
	      /**
	 * 
	 */
	private static final long serialVersionUID = 8362089248406768397L;
		private static final String DEF_TITLE = "Text-Based Path Solution";
		  
	      public SubWindow(String text) {
	    	  
	    	  this(DEF_TITLE,text);
	      }
	    		           
		  	  
		  public SubWindow(String title,
				            String text) {
			  	setLayout(new BorderLayout(2,1));
			    JTextArea jtaText = new JTextArea(text);
			  
				jtaText.setPreferredSize(new Dimension(500,500));
				jtaText.setFont(new Font("sansSerif",Font.BOLD,15));
				jtaText.setForeground(Color.BLUE);
				jtaText.setBackground(new Color(224,224,224));
				jtaText.setEditable(false);
			    add(jtaText);
			    
			    setTitle(title);
		      	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
		      	setLocationRelativeTo(null);
		      	setSize(500,500);
		      	setVisible(true);
		      	pack();  
		  }
}