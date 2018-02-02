package searchImplementations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;

public class DepthFirst {
	
	Stack<TreeNode> open = new Stack<>();
	ArrayList<TreeNode> closed = new ArrayList<>();
	TreeGenerator tree = new TreeGenerator();
	String goalState;
	TreeNode root;
	
	public DepthFirst(String startState,String goalState){
		root = new TreeNode(startState);
		this.goalState = goalState;
	}
	
	public void dfs(){
		open.push(root);
		boolean foundState = false;
		while(!open.empty() && foundState!=true){
			TreeNode x = open.pop();
			if(x.state.equals(goalState)){
				foundState=true;
				closed.add(x);
			}
			else{
				tree.generateChildren(x);
				closed.add(x);
				/*Don't add the children that are already in open or closed into open
				 * i.e those children that have been visited or are already in line to be visited
				 */
				while(!tree.children.isEmpty()){
					TreeNode temp = tree.children.pop();
					Iterator<TreeNode> iter = open.iterator();
					boolean b = false;
					
					while(iter.hasNext() && b==false){
						if(iter.next().state.equals(temp.state))
							b = true;
					}
					
					iter = closed.iterator();
					while(iter.hasNext() && b==false){
						if(iter.next().state.equals(temp.state))
							b=true;
					}
					
					if(b==false)
						open.push(temp);
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
