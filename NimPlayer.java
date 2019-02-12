package nim;

import java.util.ArrayList;
import java.util.Map;

// NimPlayer.java, which is the workhorse of your assignment.
// In here, your only task is to implement the choose(state) method
// that returns the integer indicating the action the agent should take
// from the given state. I have generously also included the GameTreeNode
// class to aid in this pursuit, as well as a signature for the α-β pruning / minimax search component.

//
// [Repeated from Course Notes] Below, you'll find the algorithm for α-β Pruning.
// This pseudocode is 80% of what you need to implement for this assignment,
// the remaining 20% being how to construct and then use the Game Tree in pursuit
// of optimal decision-making in the Game of Nim, and how to add memoization to this stub.
//
//  function alphabeta(node, α, β, maximizingPlayer)
//    if node is a terminal node
//      return the utility score of node
//    if maximizingPlayer
//      v := -∞
//      for each child of node
//        v := max(v, alphabeta(child, α, β, FALSE))
//        α := max(α, v)
//        if β ≤ α
//          break;
//      return v
//    else
//      v := ∞
//      for each child of node
//        v := min(v, alphabeta(child, α, β, TRUE))
//        β := min(β, v)
//        if β ≤ α
//          break;
//      return v

// Additionally, since our agent is the maximizing player, we would start the ball rolling with the call:
//
//   alphabeta(root, -∞, ∞, TRUE)
// Note, although Java has no -∞ nor ∞, you can safely use Integer.MIN_VALUE and Integer.MAX_VALUE for this purpose, respectively.

// HINT
// You've graduated from CMSI 281! Feel free to use any data structures 
// from the Java Collections Framework in pursuit of your task.
// Consider the appropriate data structures from the JCF that can
// be used to accomplish Memoization of Game Tree subtrees [Hint:
// you'll probably want some way to relate GameTreeNodes to the minimax scores
// you found for them in the past!]. I've extended this hint in the skeleton.
// Function getting out-of-hand complicated? Remember to use ample helper methods!
//  Be particularly conscious of repeated code if you want full style-points.
// Additionally, here's a good order of tasks to tackle:
// Review your course notes and make sure you have a solid
// grasp on how Minimax Search is meant to operate, at least at a
// high-level. During this review, envision what fields and data
// structures may be relevant in your solution, paying special attention to what is recorded in each GameTreeNode.
// Your Classwork 2 will be a useful companion to help you understand and implement this assignment.
// Using the algorithm for α-β pruning given above, step through a small example by hand.
// Once you've done the above, you should be ready to bring your Nimesis to life!
// Start early and ask questions! I'm here to help!

/**
 * Artificial Intelligence responsible for playing the game of Nim!
 * Implements the alpha-beta-pruning mini-max search algorithm
 */
public class NimPlayer {

    private final int MAX_REMOVAL;

    NimPlayer (int MAX_REMOVAL) {
        this.MAX_REMOVAL = MAX_REMOVAL;
    }

    /**
     *
     * @param   remaining   Integer representing the amount of stones left in the pile
     * @return  An int action representing the number of stones to remove in the range
     *          of [1, MAX_REMOVAL]
     */
    public int choose (int remaining) {
        throw new UnsupportedOperationException();
    }

    /**
     * Constructs the minimax game tree by the tenets of alpha-beta pruning with
     * memoization for repeated states.
     * @param   node    The root of the current game sub-tree
     * @param   alpha   Smallest minimax score possible
     * @param   beta    Largest minimax score possible
     * @param   isMax   Boolean representing whether the given node is a max (true) or min (false) node
     * @param   visited Map of GameTreeNodes to their minimax scores to avoid repeating large subtrees
     * @return  Minimax score of the given node + [Side effect] constructs the game tree originating
     *          from the given node
     */
    private int alphaBetaMinimax (GameTreeNode node, int alpha, int beta, boolean isMax, Map<GameTreeNode, Integer> visited) {
        throw new UnsupportedOperationException();
    }

}

/**
 * GameTreeNode to manage the Nim game tree.
 */
class GameTreeNode {

    int remaining, action, score;
    boolean isMax;
    ArrayList<GameTreeNode> children;

    /**
     * Constructs a new GameTreeNode with the given number of stones
     * remaining in the pile, and the action that led to it. We also
     * initialize an empty ArrayList of children that can be added-to
     * during search, and a placeholder score of -1 to be updated during
     * search.
     *
     * @param   remaining   The Nim game state represented by this node: the #
     *          of stones remaining in the pile
     * @param   action  The action (# of stones removed) that led to this node
     * @param   isMax   Boolean as to whether or not this is a maxnode
     */
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
            : false;
    }

    @Override
    public int hashCode () {
        return remaining + ((isMax) ? 1 : 0);
    }

}
