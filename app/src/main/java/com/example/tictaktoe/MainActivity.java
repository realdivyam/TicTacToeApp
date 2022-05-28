package com.example.tictaktoe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button xbtn0, xbtn1, xbtn2, xbtn3, xbtn4, xbtn5, xbtn6,xbtn7, xbtn8;
    TextView headerText;
    int PLAYER_O = 0;
    int PLAYER_X = 1;
    int activePlayer = PLAYER_O;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        headerText = findViewById(R.id.header_text);
        headerText.setText(R.string.OTurn);

        xbtn0 = findViewById(R.id.btn0);
        xbtn1 = findViewById(R.id.btn1);
        xbtn2 = findViewById(R.id.btn2);
        xbtn3 = findViewById(R.id.btn3);
        xbtn4 = findViewById(R.id.btn4);
        xbtn5 = findViewById(R.id.btn5);
        xbtn6 = findViewById(R.id.btn6);
        xbtn7 = findViewById(R.id.btn7);
        xbtn8 = findViewById(R.id.btn8);

        xbtn0.setOnClickListener(this);
        xbtn1.setOnClickListener(this);
        xbtn2.setOnClickListener(this);
        xbtn3.setOnClickListener(this);
        xbtn4.setOnClickListener(this);
        xbtn5.setOnClickListener(this);
        xbtn6.setOnClickListener(this);
        xbtn7.setOnClickListener(this);
        xbtn8.setOnClickListener(this);
    }
    int[] filledPos = {-1,-1,-1,-1,-1,-1,-1,-1,-1};

    boolean isGameActive = false;

    @Override
    public void onClick(View view) {

        if (isGameActive){
            return;
        }

        Button clickedBtn = findViewById(view.getId());
        int clickedTag = Integer.parseInt(view.getTag().toString());

        if (filledPos[clickedTag]!=-1){
            return;
        }
        filledPos[clickedTag] =activePlayer;

        if (activePlayer== PLAYER_O) {
            clickedBtn.setText("O");
            activePlayer = PLAYER_X;
            headerText.setText(R.string.XTurn);
        }
        else {
            clickedBtn.setText("X");
            activePlayer = PLAYER_O;
            headerText.setText(R.string.OTurn);
        }

        CheckForWin();

    }

    private void CheckForWin() {
        int[][] winningPos = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,7},{2,4,6}};

        for(int i =0;i<8;i++) {
            int val0 = winningPos[i][0];
            int val1 = winningPos[i][1];
            int val2 = winningPos[i][2];

            if(filledPos[val0] == filledPos[val1] && filledPos[val1] == filledPos[val2]){
                if(filledPos[val0]!=-1){

                    isGameActive=true;

                    if(filledPos[val0]== PLAYER_O){
                        showDialog("O is winner");
                    }
                    else {
                        showDialog("X is winner");
                    }
                }
            }
        }
    }

    private void showDialog(String winnerText) {
        new AlertDialog.Builder(this)
                .setTitle(winnerText)
                .setPositiveButton("Restart game", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        restartGame();
                    }
                })
                .show();
    }

    private void restartGame(){
        activePlayer  = PLAYER_O;
        headerText.setText(R.string.OTurn);
        filledPos = new int[]{-1,-1,-1,-1,-1,-1,-1,-1,-1};
        xbtn0.setText("");
        xbtn1.setText("");
        xbtn2.setText("");
        xbtn3.setText("");
        xbtn4.setText("");
        xbtn5.setText("");
        xbtn6.setText("");
        xbtn7.setText("");
        xbtn8.setText("");
        isGameActive = false;
    }
}