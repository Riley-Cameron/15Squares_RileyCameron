package com.example.a15squares_rileycameron;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SquaresController implements View.OnClickListener{
    private SquaresView view;
    private SquaresModel model;

    public SquaresController(SquaresModel m, SquaresView v)
    {
        model = m;
        view = v;
    }

    public void swap(View b) {
        int row = view.getRow((Button) b);
        int col = view.getCol((Button) b);

        model.swapSquares(row, col);
        view.updateButtons();
    }


    public void onClick(View v)
    {
        if (v.equals(view.getReset())) {
            model.randomizeList();
            view.initButtons();
            return;
        }

        Button[] available = model.getPossibleSwaps(view.getButtons());
        for (int i = 0; i < 4; i++) {
            if (v.equals(available[i])) {
                swap(v);

            }
        }

        model.winCheck();
    }
}
