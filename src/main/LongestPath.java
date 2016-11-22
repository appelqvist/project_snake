package main;

import java.util.LinkedList;

/**
 * A Class that brute force the optimal solution for Longest Path.
 * Created by Andréas Appelqvist on 2016-11-22.
 */
public class LongestPath {

    private int mPath = 1; //you start with 1 at map coverage
    private int mBestPath = 0;
    private LinkedList<Node> mNodesInLongestPath;
    private String bestStrPath = "";
    private Node mStart, mFinish;

    public LongestPath(Node start, Node finish){
        mStart = start;
        mFinish = finish;
        mNodesInLongestPath = new LinkedList<>();
    }

    /**
     * Get map coverage and every node that the snake has visit
     * @return str[0] = coverage, str[1] = snake path(in coords)
     */
    public String[] findLongestPath(){
        path(mStart);
        String[] ret = new String[2];
        ret[0] = ""+mBestPath;
        ret[1] = bestStrPath+mFinish.mCoords;
        return ret;
    }

    /**
     * A recursive method that goes through every possible combination of nodes.
     * @param u
     */
    private void path(Node u){
        mNodesInLongestPath.add(u);
        for(Node v : u.getNeighborhood()){
            if(!mNodesInLongestPath.contains(v)){
                mPath++;
                if(mPath > mBestPath){
                    mBestPath = mPath;
                    bestStrPath = "";
                    for(Node n : mNodesInLongestPath)
                        bestStrPath += n.mCoords +"\n";
                }
                path(v);
                mPath--;
            }
        }
        mNodesInLongestPath.remove(u);
    }

}
