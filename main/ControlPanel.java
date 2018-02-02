package main;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel implements ActionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JTextField startState, goalState;
	JLabel label1, label2;
	JButton submit;
	JRadioButton dfs;
	JRadioButton bfs;
	JRadioButton aStar;
	
	ControlPanel(){
		
		setLayout(new BorderLayout());
		JPanel p1 = new JPanel(new FlowLayout());
		label1 = new JLabel("Start State: ");
		label2 = new JLabel("Goal State:  ");
		startState = new JTextField("",10);
		goalState = new JTextField("",10);
		p1.add(label1);
		p1.add(startState);
		p1.add(label2);
		p1.add(goalState);
		
		JPanel p2 = new JPanel(new GridLayout(1,1));
		ButtonGroup bg = new ButtonGroup();
		dfs = new JRadioButton("Depth First Search");
		bfs = new JRadioButton("Breadth First Search");
		aStar = new JRadioButton("A* algorithm");
		bg.add(dfs);
		bg.add(bfs);
		bg.add(aStar);
		p2.add(dfs);
		p2.add(bfs);
		p2.add(aStar);
		p2.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
				"Search Algorithm?",
				TitledBorder.CENTER,
				TitledBorder.CENTER));
		
		submit = new JButton("ENTER");
		add(p1,BorderLayout.NORTH);
		add(p2,BorderLayout.CENTER);
		add(submit,BorderLayout.EAST);
		
		submit.addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
	}

}
