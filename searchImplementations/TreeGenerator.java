package searchImplementations;

import java.util.ArrayList;
import java.util.Stack;

/* Represents the state of the game in node/tree form
 * Generates all the possibilities of a single move of the blank space
 * given the position of the blank space of a specific state
 * Basically generates the children for a specific node
 * generateChldren(X) generates children of state X where X is a TreeNode object
 */
public class TreeGenerator {

	Stack<TreeNode> children = new Stack<>(); //Stores the children of a specific node
	
	public void generateChildren(TreeNode node){
		children.clear();	//Empties stack
		String s = node.state;
		int blankSpacePosition = s.indexOf(' ');	//Blank space position determines possibility of moves
		
		/*
		 * Check if UP move is possible
		 * A blank space in the 1st row cannot move up, indices in 1st row are {0,1,2}
		 * Every other row, blank space can move up {3,4,5,6,7,8} - 3 >=0
		 */
		if(blankSpacePosition-3>=0){
			//Now swap number and blank space
			node.up = new TreeNode(swap(s,blankSpacePosition,blankSpacePosition-3));
			node.up.depth = node.depth +1;	//Track depth of node(length from root to node) for A* algorithm
			children.push(node.up);
		}
		else //Not possible to move up, set UP node to empty string(null messes with search algorithm)
			node.up = new TreeNode("");
		
		/*
		 * Check if DOWN move is possible
		 * Similar to evaluating whether an upward move is possible
		 */
		if(blankSpacePosition+3<=8){
			node.down = new TreeNode(swap(s,blankSpacePosition,blankSpacePosition+3));
			node.down.depth = node.depth+1;
			children.push(node.down);
		}
		else
			node.down = new TreeNode("");;
		
		/*
		 * Check if RIGHT move is possible
		 * Won't be possible for indices {2,5,8}
		 * {2,5,8}%3==2 means no right move
		 */
		if(blankSpacePosition%3==2){
			//Can't move right.
			node.right = new TreeNode("");
		}
		else{
			node.right = new TreeNode(swap(s,blankSpacePosition,blankSpacePosition+1));
			node.right.depth = node.depth+1;
			children.push(node.right);
		}
		
		/*
		 * Check if LEFT move is possible
		 * Similar to evaluating RIGHT move
		 * Indices {0,3,6}%3==0 no left move
		 */
		if(blankSpacePosition%3==0){
			node.left = new TreeNode("");
		}
		else{
			node.left = new TreeNode(swap(s,blankSpacePosition,blankSpacePosition-1));
			node.left.depth = node.depth+1;
			children.push(node.left);
		}
	}
	
	/* Moving pieces, swaps blank space with character */
	public String swap(String s, int indexB,int indexN){
		ArrayList<Character> c = new ArrayList<>();
		for(int i=0; i<s.length();i++)
			c.add(s.charAt(i));
		c.set(indexB, c.get(indexN));
		c.set(indexN, ' ');
		String d ="";
		for(int j=0;j<c.size();j++)
			d += c.get(j);
		return d;
	}
}

/*
 * Each TreeNode represents a state of the 8 puzzle game
 */
class TreeNode{
	
	protected String state; //Actual states(1234 5678)
	
	/*Represents children(operators) of this ncde*/
	protected TreeNode up ;
	protected TreeNode down;
	protected TreeNode left;
	protected TreeNode right;
	
	/*Values for A* algorithm*/
	protected int hValue = 0; //Heuristic: # of pieces out of place
	protected int depth = 0; //Length from root to this node
	
	public TreeNode(String s){
		state = s;
	}	
}
