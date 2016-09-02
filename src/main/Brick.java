package main;

/**
 * Created by Andreas Appelqvist on 2016-01-03.
 * Fix started: 2016-09-01
 *
 * One unit in the grid
 *
 */
public class Brick {

    private boolean forbidden = false;
    private boolean visited = false;

    /**
     * Set the brick to it's forbidden
     */
    public void setForbiddenField(){
        this.forbidden = true;
    }

    /**
     * Sees if the brick is forbidden
     * @return if the brick is forbidden
     */
    public boolean isForbidden(){
        return this.forbidden;
    }

    /**
     * Set that this brick is already visited
     */
    public void setVisited(){
        this.visited = true;
    }

    /**
     * Sees if the brick has been visited before
     */
    public boolean isVisited(){
        return this.visited;
    }

    /**
     * Sees if it's allowed to use this brick
     * @return
     */
    public boolean isAllowed(){
        if(forbidden || visited)
            return false;
        else
            return true;
    }

}
