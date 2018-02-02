package searchImplementations;

import java.util.ArrayList;
import java.util.Comparator;

public class HeuristicAstar{
	
	private   ArrayList<TreeNode> open = new ArrayList<>();
	protected ArrayList<TreeNode> closed = new ArrayList<>();
	private TreeGenerator tree = new TreeGenerator();
	private String goalState;
	private TreeNode root;
	
	public HeuristicAstar(String startState, String goalState){
		root = new TreeNode(startState);
		root.depth = 0;
		this.goalState = goalState;
	}
	
	public void hSearch(){
		root.hValue = calcOutOfPlace(root.state) + 0;
		open.add(root);
		boolean foundState = false;
		closed.clear();
		while(!open.isEmpty() && foundState==false){
			TreeNode x = open.remove(0);
			if(x.state.equals(goalState)){
				foundState = true;
				closed.add(x);
			}
			else{
				tree.generateChildren(x);
				closed.add(x);
				
				while(!tree.children.isEmpty()){
					TreeNode temp = tree.children.pop();
					temp.hValue = calcOutOfPlace(temp.state) + temp.depth;
					boolean inOpen = false;
					boolean inClosed = false;
					
					/* Check if child is in open
					 * If child is on open and the path is shorter
					 * (Same state exists in open but this path is shorter)
					 */
					int index = 0;
					while(index<open.size() && inOpen==false){
						if((open.get(index).state).equals(temp.state)){
							inOpen=true;
							if(temp.depth < open.get(index).depth){
								open.set(index, temp);
							}
						}
						index++;
					}
					
					//Check if child is in closed
					index =0;
					while(index<closed.size() && inClosed==false){
						if((closed.get(index).state).equals(temp.state)){
							inClosed = true;
							if(temp.depth<closed.get(index).depth){
								closed.remove(index);
								index--;
								open.add(temp);
							}
						}
						index++;
					}
					/*
					 * Child not in open or closed
					 * Give it a heuristic value, add to open
					 */
					if(inOpen==false && inClosed==false){
						open.add(temp);
					}
				}
			}
			open.sort(new NodeComparator()); //Sort list by heuristic value(low cost to high cost)
		}
	}
	
	public int calcOutOfPlace(String s){
		int hValue = 0;
		// # of pieces out of place
		for(int i=0; i<goalState.length();i++)
			if(s.indexOf(i)!=goalState.indexOf(i))
				hValue++;
		return hValue;
	}

	public ArrayList<String> getSolutionPath(){
		ArrayList<String> path = new ArrayList<>();
		TreeNode currentNode = closed.get(closed.size()-1);
		path.add(currentNode.state);
		int i = 2;
		while(closed.size()-i>=1){
			
			if(closed.get(closed.size()-i).down.state.equals(currentNode.state)){
				currentNode = closed.get(closed.size()-i);
				path.add(currentNode.state); //parent of solution
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
	
	class NodeComparator implements Comparator<TreeNode>, java.io.Serializable{

		private static final long serialVersionUID = 3408238623005743778L;
		
		@Override
		public int compare(TreeNode x, TreeNode y) {
			int xValue = x.hValue + x.depth;
			int yValue = y.hValue + y.depth;
			
			if(xValue<yValue)
				return -1;
			else if(xValue==yValue)
				return 0;
			else
				return 1;
		}
	}
}
