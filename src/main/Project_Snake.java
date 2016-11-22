package main;

import java.io.*;

/**
 * Created by Andreas Appelqvist on 2016-01-03.
 */
public class Project_Snake {
    private int[][] mgameboard;
    public Project_Snake(String map) {
        initMap(map);
        printMap();
        //Building a graph to find the optimal solution
        Node[] nodes = buildGraph();
        String[] result = (new LongestPath(nodes[0], nodes[1])).findLongestPath();
        System.out.println("Map Coverage: "+result[0]);
        System.out.println(result[1]);
    }

    /**
     * Initilize the map.
     * @param map
     */
    public void initMap(String map) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/map.txt"));
            int row, col;
            String[] line;

            String input = br.readLine();
            line = input.split(",");
            row = Integer.parseInt(line[0]);
            col = Integer.parseInt(line[1]);
            int nbrOfObst = Integer.parseInt(line[2]);
            mgameboard = new int[row][col];

            for (int i = 0; i < mgameboard.length; i++) {
                for (int j = 0; j < mgameboard[i].length; j++) {
                    mgameboard[i][j] = 0;
                }
            }

            for (int i = 0; i < nbrOfObst; i++) {
                input = br.readLine();
                line = input.split(",");
                mgameboard[Integer.parseInt(line[0])][Integer.parseInt(line[1])] = 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * building a graph of the current gameboard
     * @return a array of Nodes[2]: Node[0] = startNode, Node[1] = finishNode
     */
    private Node[] buildGraph() {
        Node[] startAndFinish = new Node[2];

        Node[][] nodes = new Node[mgameboard.length][mgameboard[0].length];
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                if (mgameboard[i][j] != 1) { //Don't need the obstacles
                    nodes[i][j] = new Node(j + i * mgameboard[i].length, ""+i+", "+j);
                    if (i == 0 && j == 0) { //finish node
                        startAndFinish[1] = nodes[i][j];
                    } else if (i == 1 && j == 0) { //starting node
                        startAndFinish[0] = nodes[i][j];
                    }
                }
            }
        }

        for (int i = 0; i < mgameboard.length; i++) {
            for (int j = 0; j < mgameboard[i].length; j++) {
                if (mgameboard[i][j] == 0) {
                    //Look up
                    if (i - 1 >= 0 && mgameboard[i - 1][j] == 0) {
                        nodes[i][j].addNeighbor(nodes[i - 1][j]);
                    }
                    //Look down
                    if (i + 1 < mgameboard.length && mgameboard[i + 1][j] == 0) {
                        nodes[i][j].addNeighbor(nodes[i + 1][j]);
                    }
                    //Look right
                    if (j - 1 >= 0 && mgameboard[i][j - 1] == 0) {
                        nodes[i][j].addNeighbor(nodes[i][j - 1]);
                    }
                    //Look left
                    if (j + 1 < mgameboard[i].length && mgameboard[i][j + 1] == 0) {
                        nodes[i][j].addNeighbor(nodes[i][j + 1]);
                    }
                }
            }
        }

        startAndFinish[0].removeNeighbor(startAndFinish[1]);
        startAndFinish[1].removeNeighbor(startAndFinish[0]);
        return startAndFinish;
    }

    /**
     * Print out visual were the obstacles is located on the map.
     */
    private void printMap() {
        for (int i = 0; i < mgameboard.length; i++) {
            for (int j = 0; j < mgameboard[i].length; j++) {
                System.out.print(mgameboard[i][j]);
            }
            System.out.println();
        }
        System.out.println("\n\n");
    }

}
