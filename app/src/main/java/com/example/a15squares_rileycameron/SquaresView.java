package com.example.a15squares_rileycameron;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SquaresView {
    private Button[][] buttons;

    private Button reset;
    private SquaresModel model;

    public SquaresView(SquaresModel m, Button r)
    {
        model = m;
        buttons = new Button[4][4];
        reset = r;
    }

    public Button getReset() {
        return reset;
    }

    public void addButton(int row, int col, Button b)
    {
        buttons[row][col] = b;
    }

    public Button[][] getButtons() {
        return buttons;
    }

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


    public void enableButton(int row, int col)
    {
        buttons[row][col].setVisibility(View.VISIBLE);
        buttons[row][col].setClickable(true);
    }

    public void disableButton(int row, int col)
    {
        buttons[row][col].setVisibility(View.INVISIBLE);
        buttons[row][col].setClickable(false);
    }

    public void updateButtons(){
        int[][] g = model.getGridMap();

        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
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
