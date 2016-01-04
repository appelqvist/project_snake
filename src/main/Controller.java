package main;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Andreas Appelqvist on 2016-01-03.
 */

public class Controller {

    private Brick[][] brickArray;
    private int nbrRows;
    private int nbrColums;

    public Controller(String map){
        initMap(map);
        startSnake();
    }

    public void startSnake(){
        int currentRow = 0;
        int currentCol = 0;
        brickArray[currentRow][currentCol].setVisited();
        currentRow = 1;

        while(!(currentRow == 0 && currentCol == 0)){

            if((currentCol+1 <= nbrColums) && brickArray[currentRow][currentCol+1].isAllowed()){ //Om du får gå till höger

            }else if((currentRow+1 <= nbrRows) && brickArray[currentRow][currentCol].isAllowed()){ //Om du får gå neråt

            }else if((currentRow-1 >= 0) && brickArray[currentRow][currentCol].isAllowed()){ //Om du får gå uppåt

            }else if((currentCol-1 >= 0) && brickArray[currentRow][currentCol].isAllowed()){ //Om du får gå åt vänster

            }else{
                System.out.println("Finns ingenstans att gå");
                printMap();
            }

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

        nbrRows = Integer.parseInt(splitted.get(0));
        nbrColums = Integer.parseInt(splitted.get(1));

        brickArray = new Brick[nbrColums][nbrRows];

        //Bygger upp rutnätet
        for (int i = 0; i < brickArray.length; i++) {
            for (int j = 0; j < brickArray[i].length; j++) {
                brickArray[i][j] = new Brick();
            }
        }

        //Lägger till forbiddenfields
        for(int i = 3; i < splitted.size(); i = i+2){
            System.out.println("row: "+splitted.get(i)+", col: "+splitted.get(i+1));
            brickArray[Integer.parseInt(splitted.get(i))][Integer.parseInt(splitted.get(i+1))].setForbiddenField();
        }
        //printMap();
    }

    public void printMap(){
        System.out.println("\n*****************KARTA***************");
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
