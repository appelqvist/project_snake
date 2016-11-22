package main;
import java.util.LinkedList;

/**
 * Representing a node in the graph
 * Created by Andréas Appelqvist on 2016-11-11.
 */
public class Node {
    public int mId;
    public String mCoords;
    private LinkedList<Node> mNeighbor;

    /**
     * Construct.
     * @param id
     * @param coordinate
     */
    public Node(int id, String coordinate) {
        mId = id;
        mNeighbor = new LinkedList<>();
        mCoords = coordinate;
    }

    /**
     * Add a neighbor to this node
     * @param n
     */
    public void addNeighbor(Node n){
        mNeighbor.add(n);
    }

    /**
     * Remove a neighbor to this node
     * @param n
     */
    public void removeNeighbor(Node n){
        mNeighbor.remove(n);
    }

    /**
     * Get all neighbors of this node
     * @return
     */
    public LinkedList<Node> getNeighborhood(){
        return mNeighbor;
    }
}