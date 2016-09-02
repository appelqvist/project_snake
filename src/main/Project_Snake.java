package main;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andreas Appelqvist on 2016-01-03.
 */

public class Project_Snake {

    private Brick[][] brickArray;
    private int nbrRows;
    private int nbrColums;
    private int currentRow = 0;
    private int currentCol = 0;

    public Project_Snake(String map){
        initMap(map);
        startSnake();
        printMap();
    }


    /**
     * Startar ormen så den får gå genom kartan
     */
    public void startSnake() {


        //Check if its Ok to start at row 1
        if (brickArray[1][0].isAllowed()) {
            currentCol = 0;
            currentRow = 1;
        }

        boolean running = true;
        while (running) {


        }
    }

    

    /**
     * Läser in från textfilen och skapar ett rutnät med hinder.
     * @param map path (String) til kartan
     */
    public void initMap(String map){
        ArrayList<String> splitted = new ArrayList<String>();

        try (BufferedReader br = new BufferedReader(new FileReader(map))){
            String line;
            String[] cutLine;
            while ((line = br.readLine()) != null)
            {
                cutLine = line.split(",");
                if(cutLine.length > 2){
                    for(int i = 0; i < 3; i++)
                        splitted.add(cutLine[i]);
                }
                else{
                    splitted.add(cutLine[0]);
                    splitted.add(cutLine[1]);
                }
            }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        nbrColums = Integer.parseInt(splitted.get(0));
        nbrRows = Integer.parseInt(splitted.get(1));

        brickArray = new Brick[nbrRows][nbrColums];

        //Bygger upp rutnätet
        for (int i = 0; i < brickArray.length; i++) {
            for (int j = 0; j < brickArray[i].length; j++) {
                brickArray[i][j] = new Brick();
            }
        }

        //Lägger till forbiddenfields
        for(int i = 3; i < splitted.size(); i = i+2){
            System.out.println("row: "+splitted.get(i+1)+", col: "+splitted.get(i));
            brickArray[Integer.parseInt(splitted.get(i+1))][Integer.parseInt(splitted.get(i))].setForbiddenField();
        }
        //printMap();
    }

    /**
     * Skriver ut hur kartan ser ut
     * [ ] = En obesökt brick
     * [X] = Forbiddenfield
     * [S] = Besökta bricks av ormen
     */
    public void printMap(){
        System.out.println("\n*****************KARTA***************\n");

        for (int i = 0; i < brickArray.length; i++) {
            for (int j = 0; j < brickArray[i].length; j++) {

                if(brickArray[i][j].isForbidden()){
                    System.out.print("[X]");
                }else if(brickArray[i][j].isVisited()){
                    System.out.print("[S]");
                }else{
                    System.out.print("[ ]");
                }
                if(j == brickArray[i].length-1)
                    System.out.println();
            }
        }
        System.out.println("\n*****************KARTA***************\n");
    }

}
