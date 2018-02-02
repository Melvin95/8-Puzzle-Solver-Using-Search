package main;

import searchImplementations.DepthFirst;
import searchImplementations.BreadthFirst;
import searchImplementations.HeuristicAstar;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class Test extends JPanel implements ActionListener {
	
	private static final long serialVersionUID = 1L;
	private String[] searchOptions = {"Depth First","Breadth First","A*"};
	private JTextField startState, goalState;
	private JLabel label1, label2;
	private JButton submit;
	private JRadioButton[] searchButton = new JRadioButton[3];
	private ButtonGroup bg = new ButtonGroup();
	private static JFrame frame = new JFrame();
	private JButton next = new JButton("NEXT");
	private JButton prev = new JButton("PREVIOUS");
	private JButton restart = new JButton("RESTART");
	private Board board;
	private TextArea textArea = new TextArea();
	
	private int index = 0;
	
	private BreadthFirst bfs;
	private DepthFirst dfs;
	private HeuristicAstar hSearch;
	
	private ArrayList<String> path;
	
	private long startTime;
	
	public JPanel topControls(){
		JPanel ControlPanel = new JPanel(new BorderLayout());
		
		JPanel p1 = new JPanel(new FlowLayout());
		JLabel help = new JLabel(" A space represents blank space on board. 1234 5678");
		help.setFont(new Font("Times New Roman",20,20));
		label1 = new JLabel("Start State: ");
		label2 = new JLabel("Goal State:  ");
		startState = new JTextField("",10);
		goalState = new JTextField("",10);
		p1.add(label1);
		p1.add(startState);
		p1.add(label2);
		p1.add(goalState);
		
		JPanel p2 = new JPanel(new GridLayout(1,1));
		for(int i=0; i<searchOptions.length;i++){
			searchButton[i] = new JRadioButton(searchOptions[i]);
			p2.add(searchButton[i]);
			bg.add(searchButton[i]);
		}
		p2.setBorder(new TitledBorder(new LineBorder(Color.BLACK),
				"Search Algorithm?",
				TitledBorder.CENTER,
				TitledBorder.CENTER));
		submit = new JButton("ENTER");
		
		ControlPanel.add(p1,BorderLayout.CENTER);
		ControlPanel.add(p2,BorderLayout.SOUTH);
		ControlPanel.add(submit,BorderLayout.EAST);
		ControlPanel.add(help,BorderLayout.NORTH);
		return ControlPanel;
	}
	
	public JPanel drawBoard(String s){
		board = new Board(s);
		return board;
	}
	
	public Test(String s){
		setLayout(new BorderLayout());
		add(topControls(),BorderLayout.NORTH);
		add(drawBoard(s),BorderLayout.CENTER);
		
		JPanel iterate = new JPanel(new FlowLayout());
		next.setEnabled(false);
		prev.setEnabled(false);
		restart.setEnabled(false);
		iterate.add(next);
		iterate.add(prev);
		iterate.add(restart);
		add(iterate,BorderLayout.EAST);

		submit.addActionListener(this);
		
		next.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				nextStep();
			}
		});
		
		prev.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				previousStep();
			}
		});
		
		restart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reset();
			}
		});
	}
	
	public static void main(String[] args) {
		frame.add(new Test("1234 5678"));
		frame.setSize(700,700);
		frame.setTitle("8 Puzzle");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
	public void reset(){
		frame.add(new Test("1234 5678"));
		frame.setSize(700,700);
		frame.setTitle("8 Puzzle");
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		submit.setEnabled(true);
		searchButton[0].setEnabled(true);
		searchButton[1].setEnabled(true);
		searchButton[2].setEnabled(true);
		path.clear();
		textArea.setText("");
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		startTime = System.currentTimeMillis();
		submit.setEnabled(false);
		frame.add(new Test(startState.getText()));
		JPanel control = new JPanel(new GridLayout(2,3));
		JLabel start = new JLabel("Start State: "+ startState.getText());
		JLabel goal = new JLabel("Goal State: "+goalState.getText());
		JLabel search = new JLabel("");
		for(int i=0; i<searchOptions.length;i++){
			if(searchButton[i].isSelected()){
				search = new JLabel("Search Chosen: "+searchOptions[i]);
				searchButton[i].setEnabled(false);
				
				if(searchOptions[i].equals("Depth First")){
					dfs = new DepthFirst(startState.getText(),goalState.getText());
					dfs.dfs();
					path = dfs.getSolutionPath();
				}
				else if(searchOptions[i].equals("Breadth First")){
					bfs = new BreadthFirst(startState.getText(),goalState.getText());
					bfs.bfs();
					path = bfs.getSolutionPath();
				}
				else if(searchOptions[i].equals("A*")){
					hSearch = new HeuristicAstar(startState.getText(),goalState.getText());
					hSearch.hSearch();
					path = hSearch.getSolutionPath();
				}
			}
			else
				searchButton[i].setEnabled(false);
		}
		
		index = path.size()-1; //Last element of path(thus the start state/root node)
		
		setTextArea();
		(new SubWindow(textArea.getText())).setVisible(true);
		
		control.add(search);
		control.add(start);
		control.add(goal);
		frame.add(control,BorderLayout.SOUTH);
		next.setEnabled(true);
		prev.setEnabled(true);
		restart.setEnabled(true);
		frame.setVisible(true);
	}
	
	public void setTextArea(){
		textArea.append("TOTAL MOVES: "+ (path.size()-1) +"\t\t Time Taken: "+((System.currentTimeMillis()-startTime)/1000)+" second(s) \n\n" );
		textArea.append("START STATE: "+path.get(path.size()-1).replace(' ', 'b')+"\n");
		int i = path.size()-2;
		while(i>0){
			textArea.append(path.get(i).replace(' ', 'b')+"\n");
			i--;
		}
		textArea.append("\n\n GOAL STATE: "+path.get(0).replace(' ', 'b'));
	}
	
	/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 * Displays step by step the solution by changing the board's
	 * state by moving the blank space when NEXT/PREVIOUS buttons pressed
	 *\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 */
	public void nextStep(){
		if(index==0){
			frame.add(new Test(path.get(index)));
		}
		else if(index>0){
			index--;
			frame.add(new Test(path.get(index)));
		}
		frame.setVisible(true);
	}
	
	public void previousStep(){
		if(index<path.size()-1){
			index++;
			frame.add(new Test(path.get(index)));
			frame.setVisible(true);
		}
	}
	/*\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	 \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\*/
}
