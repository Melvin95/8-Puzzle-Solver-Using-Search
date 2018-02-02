# 8 Puzzle Solver Using Search

A program that simulates the solving of the 8 puzzle game using different search algorithms.  Breadth First Search, Depth First Search and a Heuristic Based Search(A* algorithm)

Heuristic Evaluation Function:

  A heuristic function is one where problem-specific knowledge is added to increase efficiency of search algorithms. They calculate a       value(cost) of moving from one state to another, the idea is to search through the least costly states first.
  The heuristic function used for the A* algorithm for the 8-puzzle game simply counted the number of tiles out of place in each state       compared to the goal state. The algorithm avoids expanding paths that are expensive (a lot of tiles out place) but expands the paths       with the lowest cost.
  The heuristic used (number of tiles out of place) is bounded above by the actual number of moves required to get to the goal state, h(n)   <= h*(n), so this makes the algorithm admissible as it will always find the shortest path (least number of moves) to goal.

  Two factors determine the cost of moving between states:
    -Number of tiles out of place in a particular state compared to the goal state.
    -Depth of a state (shortest distance between state and root of tree)

The Breadth First Search and the A* algorithm were practically identical in performance (a few milliseconds difference, which is negligible) even though the BFS is an uninformed search and the A* is an informed one. This might be because a state representation of the 8 puzzle has a small branching factor (at most 4 when the blank space is in the centre).

The DFS performed poorly because it expands to the leftmost leaf first of the tree before expanding the neighbours. It eventually finds the goal state but the solution path isn’t optimal and the execution time is lengthy.
