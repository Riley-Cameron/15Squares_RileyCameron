/**
 * Riley Cameron
 * 2/27/2023
 *
 * SquaresController class - uses the model and view classes to process button clicks and run the game
 */
package com.example.a15squares_rileycameron;

import android.app.AlertDialog;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SquaresController implements View.OnClickListener{
    //view
    private SquaresView view;

    //model
    private SquaresModel model;

    //dialog pop-up for when the player wins
    private AlertDialog dialog;

    //constructor
    public SquaresController(SquaresModel m, SquaresView v, AlertDialog d)
    {
        model = m;
        view = v;
        dialog = d;
    }

    //swaps the clicked button with the empty tile
    public void swap(View b) {
        int row = view.getRow((Button) b);
        int col = view.getCol((Button) b);

        model.swapSquares(row, col);
        view.updateButtons();
    }

    //this method runs whenever a button is clicked
    public void onClick(View v)
    {
        //check if the button the got clicked was the reset
        if (v.equals(view.getReset())) {
            //if it is, reshuffle the grid and re-initialize the buttons
            model.randomizeList();
            view.initButtons();
            return;
        }

        //if it is a button in the grid, check if it is one that can be swapped
        Button[] available = model.getPossibleSwaps(view.getButtons());
        for (int i = 0; i < 4; i++) {
            //if allowed, call the swap method on the button
            if (v.equals(available[i])) {
                swap(v);

            }
        }

        //check if the game has been won and alert the player if so
        if (model.winCheck()) {
            dialog.show();
        }
    }
}
