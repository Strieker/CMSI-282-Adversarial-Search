package nim;

import java.util.*;

/**
 * Artificial Intelligence responsible for playing the game of Nim!
 * Implements the alpha-beta-pruning mini-max search algorithm
 */
public class NimPlayer {

    private final int MAX_REMOVAL;

    NimPlayer (int MAX_REMOVAL) {
        this.MAX_REMOVAL = MAX_REMOVAL;
    }

    public int choose (int remaining) {
       int pruningFactor = 0; 
       //HOW DO YOU USE THIS / WHAT ARE WE DOING WITH THE V
       //you are supposed to still do the key call 
       //refernce to the root at the end 
       // look at eac kid with the root node and choose the ref to root node look at the kids with the max score 
       Map<GameTreeNode, Integer> graveyard = new HashMap<>();
       GameTreeNode root = new GameTreeNode(remaining, 0, true);
       root.score = 1;
       int closestAction = 1;
       if (graveyard.isEmpty()) {
   			pruningFactor = alphaBetaMinimax(root, Integer.MIN_VALUE, Integer.MAX_VALUE, true, graveyard);
   			System.out.println(pruningFactor);
       }
       if(root.children.size() != 0) {
    		closestAction = root.children.get(0).action;
    		   //WAT TO DO IF GAME TREE NODE IS NULL 
       }
       for (GameTreeNode child: root.children) {
    	   System.out.println(child.action);
    		if(child.score == 1) {
    			closestAction = child.action;
    		}
       }
       
       return closestAction;
      // STILL DONT GET WHY CALL IT TWICE 
    }
    

    private int alphaBetaMinimax (GameTreeNode node, int alpha, int beta, boolean isMax, Map<GameTreeNode, Integer> visited) {
       GameTreeNode current = node;
       // current node's score pruning score so it's the current minimax score
       // if already in it then just attach the utility score from that visited 
    	int remaining = node.remaining;
    	int currentScore = current.score;
    	if (current.score == 0) {
    		return current.score;
    	}
    	if (current.isMax == isMax) {
    		current.score = Integer.MIN_VALUE;
    		// for each action to current 
        	for (int action = 1; action < remaining; action++) {
        		if(visited.containsKey(current)) {
        			break;
        		}
        		isMax = !isMax;
    			int remainingStonesAfterTakingAction = current.remaining - action;
    			if (remainingStonesAfterTakingAction == 0 && isMax) {
    				currentScore = 0;
    			}
        		GameTreeNode child = new GameTreeNode(remainingStonesAfterTakingAction,0,isMax);
        		child.score = currentScore;
        		current.score = max(current.score, alphaBetaMinimax(child, alpha, beta, false, visited));
        		alpha = max(alpha, current.score);
        		if (beta <= alpha) {
        			break;
        		}
        		current.children.add(child);
        	}
    		visited.put(current, current.score);
    	} else {
    		current.score = Integer.MAX_VALUE;
    		for (int action = 1; action < remaining; action++) {
    			if(visited.containsKey(current)) {
        			break;
        			// am i already figuring out the score? 
        		}
        		isMax = !isMax;
    			int remainingStonesAfterTakingAction = current.remaining - action;
    			if (remainingStonesAfterTakingAction == 0 && isMax) {
    				currentScore = 0;
    			}
        		GameTreeNode child = new GameTreeNode(remainingStonesAfterTakingAction,action,isMax);
        		child.score = currentScore;
        		current.score = min(current.score, alphaBetaMinimax(child, alpha, beta, false, visited));
        		beta = min(beta, current.score);
        		if (beta <= alpha) {
        			break;
        		}
        		current.children.add(child);
        	}
    		visited.put(current, current.score);
    	}
    	System.out.println("--------");
    	System.out.println(current.children.toString());
    	return current.score;
    	
    }
    
	
    
    private int max(int n1, int n2){
		return n1 > n2 ? n1 : n2;
	};
	
	private int min(int n1, int n2){
		return n1 > n2 ? n2 : n1;
	};
}

class GameTreeNode {

    int remaining, action, score;
    boolean isMax;
    ArrayList<GameTreeNode> children;

    GameTreeNode (int remaining, int action, boolean isMax) {
        this.remaining = remaining;
        this.action = action;
        this.isMax = isMax;
        children = new ArrayList<>();
        score = -1;
    }
    
    // WHAT DOES THIS DO / WHEN TO USE THIS PLEASE CAN YOU JUST USE CONTAINS 
    @Override
    public boolean equals (Object other) {
        return other instanceof GameTreeNode
            ? remaining == ((GameTreeNode) other).remaining &&
              isMax == ((GameTreeNode) other).isMax &&
              action == ((GameTreeNode) other).action
              // possibly kill action 
            : false;
    }

    @Override
    public int hashCode () {
        return remaining + ((isMax) ? 1 : 0);
    }

}
