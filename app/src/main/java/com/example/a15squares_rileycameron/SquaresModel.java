/**
 * Riley Cameron
 *
 * SquaresModel class
 *
 * sources:
 * https://www.digitalocean.com/community/tutorials/shuffle-array-java - For randomizing the grid
 */
package com.example.a15squares_rileycameron;

import android.util.Log;
import android.widget.Button;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class SquaresModel {

    private List<Integer> rng;

    private int[][] gridMap;

    private int[][] gridKey = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};


    public SquaresModel()
    {
        Integer[] temp = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,0};
        rng = Arrays.asList(temp);
        //randomizeList();
        gridMap = new int[4][4];
    }

    public void addPair(int x, int row, int col) {
        gridMap[row][col] = rng.get(x);
    }

    public void randomizeList() {
        Collections.shuffle(rng);
    }

    public int[][] getGridMap() {
        return gridMap;
    }

    public void swapSquares(int r, int c) {
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++) {
                if (gridMap[i][j] == 0) {
                    gridMap[i][j] = gridMap[r][c];
                    gridMap[r][c] = 0;
                }
            }
        }
    }

    public Button[] getPossibleSwaps(Button[][] buttons) {
        Button[] available = new Button[4];

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gridMap[i][j] == 0) {
                    if (i+1 < 4) {
                        available[0] = buttons[i+1][j];
                    }
                    if (j+1 < 4) {
                        available[1] = buttons[i][j+1];
                    }
                    if (i-1 >= 0) {
                        available[2] = buttons[i-1][j];
                    }
                    if (j-1 >= 0) {
                        available[3] = buttons[i][j-1];
                    }
                }
            }
        }

        return available;
    }

    public boolean winCheck() {
        for (int i = 0;i<4;i++) {
            for (int j = 0;j<4;j++) {
                if (gridMap[i][j] != gridKey[i][j]) {
                    Log.i("Win Check", "win condition not met");
                    return false;
                }
            }
        }
        Log.i("Win Check", "win condition met!");
        return true;
    }

}
