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

    public Project_Snake(String map){
        initMap(map);
        startSnake();
        printMap();
    }


    /**
     * Startar ormen så den får gå genom kartan
     */
    public void startSnake(){
        int currentRow = 0;
        int currentCol = 0;
        brickArray[currentRow][currentCol].setVisited();

        System.out.println("Börjar på: 0,1 och ska till 0,0");
        currentRow = currentRow+1;

        boolean running = true;
        while(running){
            //Kolla om de går att gå vänster
            if((currentCol-1 >= 0) && brickArray[currentRow][currentCol-1].isAllowed()){
                brickArray[currentRow][currentCol].setVisited();
                currentCol = currentCol-1;
            }
            //Kollar om de går att gå uppåt
            else if((currentRow-1 >= 0) && brickArray[currentRow-1][currentCol].isAllowed()){
                brickArray[currentRow][currentCol].setVisited();
                currentRow = currentRow-1;
            }
            //Kollar om det går att gå höger
            else if((currentCol+1 <= nbrColums) && brickArray[currentRow][currentCol+1].isAllowed()){
                brickArray[currentRow][currentCol].setVisited();
                currentCol = currentCol+1;
            }
            //Kollar om det går att gå neråt
            else if((currentRow+1 <= nbrRows) && brickArray[currentRow+1][currentCol].isAllowed()){
                brickArray[currentRow][currentCol].setVisited();
                currentRow = currentRow+1;
            }

            //Stuck
            else{
                System.out.println("*****Stuck**********");
                running = false;
            }

            if(currentCol-1 == 0 && currentRow == 0){
                brickArray[currentRow][currentCol].setVisited();
                running = false;
            }
            System.out.println(currentCol+", "+currentRow);
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
     * O = En obesökt brick
     * X = Forbiddenfield
     * S = Besökta bricks av ormen
     */
    public void printMap(){
        System.out.println("\n*****************KARTA***************\n\n");

        for (int i = 0; i < brickArray.length; i++) {
            for (int j = 0; j < brickArray[i].length; j++) {

                if(brickArray[i][j].isForbidden()){
                    System.out.print("X   ");
                }else if(brickArray[i][j].isVisited()){
                    System.out.print("S   ");
                }else{
                    System.out.print("O   ");
                }
                if(j == brickArray[i].length-1)
                    System.out.println("\n\n");
            }
        }
        System.out.println("*****************KARTA***************\n");
    }

}
