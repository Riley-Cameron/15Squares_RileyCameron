/**
 * Riley Cameron
 * 2/27/2023
 *
 * SquaresView class - contains all of the objects that are displayed on the screen (button grid & reset button)
 */
package com.example.a15squares_rileycameron;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SquaresView {

    //grid of buttons
    private Button[][] buttons;

    //reset button
    private Button reset;

    //model
    private SquaresModel model;

    //constructor: initializes the model, reset button, and button grid
    public SquaresView(SquaresModel m, Button r)
    {
        model = m;
        buttons = new Button[4][4];
        reset = r;
    }

    //returns the reset button object
    public Button getReset() {
        return reset;
    }

    //adds a button to the grid at the specified row and column
    public void addButton(int row, int col, Button b)
    {
        buttons[row][col] = b;
    }


    //returns the button grid
    public Button[][] getButtons() {
        return buttons;
    }

    //returns the row of a given button
    public int getRow(Button b) {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if (b.equals(buttons[i][j])) {
                    return i;
                }
            }
        }
        return -1;
    }

    //returns the column of a given button
    public int getCol(Button b) {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if (b.equals(buttons[i][j])) {
                    return j;
                }
            }
        }
        return -1;
    }

    //initializes the button grid by pairing each button with a number using the gridMap from the model
    public void initButtons() {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                int num = ((i*4) + j );
                model.addPair(num,i,j);
            }
        }
        updateButtons();
    }

    //enables the button at the coordinates (makes it visible and clickable)
    public void enableButton(int row, int col)
    {
        buttons[row][col].setVisibility(View.VISIBLE);
        buttons[row][col].setClickable(true);
    }

    //disables a button (makes it invisible and not clickable)
    public void disableButton(int row, int col)
    {
        buttons[row][col].setVisibility(View.INVISIBLE);
        buttons[row][col].setClickable(false);
    }

    //iterates through the button grid and enables/disables the correct buttons and sets the correct text on each
    public void updateButtons(){
        int[][] g = model.getGridMap();

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                if (g[i][j] == 0) {
                    disableButton(i,j);
                } else {
                    enableButton(i, j);
                    String numString = Integer.toString(g[i][j]);
                    buttons[i][j].setText(numString);
                }
            }
        }
    }

    //sets the on click listener for all the buttons
    public void setListener(View.OnClickListener ocl)
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                buttons[i][j].setOnClickListener(ocl);
            }
        }
        reset.setOnClickListener(ocl);
    }
}
