package main;

/**
 * Created by Andreas Appelqvist on 2016-01-03.
 *
 * Är en enhet i rutnätet
 *
 */
public class Brick {

    private boolean forbidden = false;
    private boolean visited = false;

    public Brick(){ }

    public void setForbiddenField(){
        this.forbidden = true;
    }

    public boolean isForbidden(){
        return this.forbidden;
    }

    public void setVisited(){
        this.visited = true;
    }

    public boolean isVisited(){
        return this.visited;
    }

    public boolean isAllowed(){
        if(forbidden || visited)
            return false;
        else
            return true;
    }

}
