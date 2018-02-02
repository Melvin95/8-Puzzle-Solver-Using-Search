package searchImplementations;

import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class BreadthFirst {
	
	private Deque<TreeNode> open = new LinkedList<>();
	protected ArrayList<TreeNode> closed = new ArrayList<>();
	private TreeGenerator tree = new TreeGenerator();
	private String goalState;
	private TreeNode root;
	
	public BreadthFirst(String startState,String goalState){
		root = new TreeNode(startState);
		this.goalState = goalState;
	}
	
	public void bfs(){
		open.addLast(root);
		boolean foundState = false;
		while(!open.isEmpty() && foundState==false){
			TreeNode x = open.removeFirst();
			
			if(x.state.equals(goalState)){
				foundState = true;
				closed.add(x);
			}
			else{
				tree.generateChildren(x);
				closed.add(x);
				//Remove any children that are in open or closed already else add to open
				//Stack<TreeNode> children = tree.children;
				while(!tree.children.isEmpty()){
					TreeNode temp = tree.children.pop();
					Iterator<TreeNode> iter = open.iterator();
					boolean b = false;
					//Check if state is in open
					while(iter.hasNext() && b==false){
						if(iter.next().state.equals(temp.state))
							b = true;
					}
					//Check if state is in closed
					iter = closed.iterator();
					while(iter.hasNext() && b==false){
						if(iter.next().state.equals(temp.state))
							b=true;
					}
					
					//Isn't in open or closed, so add to open
					if(b==false)
						open.addLast(temp);;
				}
			}
		}
	}
	
	public ArrayList<String> getSolutionPath(){
		ArrayList<String> path = new ArrayList<>();
		TreeNode currentNode = closed.get(closed.size()-1);
		path.add(currentNode.state);
		int i = 2;
		while(closed.size()-i>=1){
			
			if(closed.get(closed.size()-i).down.state.equals(currentNode.state)){
				currentNode = closed.get(closed.size()-i);
				path.add(currentNode.state); 
			}
			else if(closed.get(closed.size()-i).up.state.equals(currentNode.state)){
				currentNode = closed.get(closed.size()-i);
				path.add(currentNode.state);
			}
			else if(closed.get(closed.size()-i).left.state.equals(currentNode.state)){
				currentNode = closed.get(closed.size()-i);
				path.add(currentNode.state);
			}
			else if(closed.get(closed.size()-i).right.state.equals(currentNode.state)){
				currentNode = closed.get(closed.size()-i);
				path.add(currentNode.state);
			}
			else
				i++;
		}
		path.add(root.state);
		return path;
	}
}
