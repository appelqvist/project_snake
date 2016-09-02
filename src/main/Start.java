package main;

/**
 * Created by Andreas Appelqvist on 2016-01-02.
 */
public class Start {
    public static void main(String[] args) {


        int[][] array = new int[4][5];

        System.out.println(array[0].length);

        String map = "src/map.txt";
        new Project_Snake(map);

    }

}

