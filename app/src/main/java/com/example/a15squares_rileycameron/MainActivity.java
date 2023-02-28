/**
 * Riley Cameron
 * 2/27/2023
 *
 * Main Activity class - onCreate method runs on startup
 *
 * sources:
 * https://chat.openai.com/chat - used for learning how to create an alert dialog
 */
package com.example.a15squares_rileycameron;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setup an alert dialog to be displayed when the player wins
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("You Win!!").setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        AlertDialog dialog = builder.create();

        //get the reset button
        Button reset = findViewById(R.id.reset);

        //initialize the model and view classes for the game
        SquaresModel model = new SquaresModel();
        SquaresView view = new SquaresView(model, reset);

        //get each button in the grid and add the to the view class (reused code from in class)
        view.addButton(0,0, findViewById(R.id.b_11));
        view.addButton(0,1, findViewById(R.id.b_12));
        view.addButton(0,2, findViewById(R.id.b_13));
        view.addButton(0,3, findViewById(R.id.b_14));
        view.addButton(1,0, findViewById(R.id.b_21));
        view.addButton(1,1, findViewById(R.id.b_22));
        view.addButton(1,2, findViewById(R.id.b_23));
        view.addButton(1,3, findViewById(R.id.b_24));
        view.addButton(2,0, findViewById(R.id.b_31));
        view.addButton(2,1, findViewById(R.id.b_32));
        view.addButton(2,2, findViewById(R.id.b_33));
        view.addButton(2,3, findViewById(R.id.b_34));
        view.addButton(3,0, findViewById(R.id.b_41));
        view.addButton(3,1, findViewById(R.id.b_42));
        view.addButton(3,2, findViewById(R.id.b_43));
        view.addButton(3,3, findViewById(R.id.b_44));

        //assign each button a number and display it
        view.initButtons();

        //initialize the controller class
        SquaresController controller = new SquaresController(model, view, dialog);

        //set the controller as the listener for all of the buttons
        view.setListener(controller);
    }

}