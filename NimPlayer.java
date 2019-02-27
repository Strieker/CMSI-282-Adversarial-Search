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
       Map<GameTreeNode, Integer> graveyard = new HashMap<>();
       GameTreeNode root = new GameTreeNode(remaining, 0, true);
	   root.score = alphaBetaMinimax(root, Integer.MIN_VALUE, Integer.MAX_VALUE, true, graveyard);
       int highestScore = Integer.MIN_VALUE; 
       int bestAction = -1; 
       for (GameTreeNode child: root.children) {
    	   System.out.println(child.action);
    		if(child.score > highestScore && child.score <= MAX_REMOVAL) {
    			bestAction = child.action;
    			highestScore = child.score;
    		}
       }
       return bestAction;
    }
    

    private int alphaBetaMinimax (GameTreeNode node, int alpha, int beta, boolean isMax, Map<GameTreeNode, Integer> visited) { 
    	if (node.remaining == 0) {
    		if (isMax) {
    			node.score = 0;
    			return node.score;
    		} else {
    			node.score = 1;
    			return node.score; 
    		}
    	}
    	if (node.isMax) {
    		int v = Integer.MIN_VALUE;
        	for (int action = 1; action <= Math.min(MAX_REMOVAL, node.remaining); action++) {
        		GameTreeNode child = new GameTreeNode(node.remaining - action,action,false);
        		node.children.add(child);
        		if(visited.containsKey(child)) {
        			child.score = visited.get(child);
        		} else {
        			child.score = alphaBetaMinimax(child, alpha, beta, false, visited);
        		}
        		node.score = Math.max(node.score,child.score);
        		v = Math.max(v, child.score);
        		alpha = Math.max(alpha, v);
        		visited.put(child, child.score);
        		if (beta <= alpha) {
        			break;
        		}
        	}
    		return v; 
    	} else {
    		int v = Integer.MAX_VALUE;
        	for (int action = 1; action <= Math.min(MAX_REMOVAL, node.remaining); action++) {
        		
        		GameTreeNode child = new GameTreeNode(node.remaining - action,action,false);
        		node.children.add(child);
        		if(visited.containsKey(child)) {
        			child.score = visited.get(child);
        		} else {
        			child.score = alphaBetaMinimax(child, alpha, beta, false, visited);
        		}
        		node.score = Math.min(node.score,child.score);
        		v = Math.min(v, child.score);
        		beta = Math.min(beta, v);
        		visited.put(child, child.score);
        		if (beta <= alpha) {
        			break;
        		}
        	}
    		return v; 
    	}
    	}
    }
    
	
    
//    private int max(int n1, int n2){
//		return n1 > n2 ? n1 : n2;
//	};
//	
//	private int min(int n1, int n2){
//		return n1 > n2 ? n2 : n1;
//	};
//}

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
