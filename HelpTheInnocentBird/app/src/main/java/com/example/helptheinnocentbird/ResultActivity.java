package com.example.helptheinnocentbird;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    TextView textViewResultInfo, textViewMyScore, textViewHighestScore;
    AppCompatButton buttonAgain;

    int score;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewResultInfo = findViewById(R.id.textview_result_info);
        textViewMyScore = findViewById(R.id.textview_my_score);
        textViewHighestScore = findViewById(R.id.textview_highest_score);

        buttonAgain = findViewById(R.id.button_again);

        score = getIntent().getIntExtra("score", 0);
        textViewMyScore.setText("Your Score is: " + score);

        sharedPreferences = this.getSharedPreferences("Score", Context.MODE_PRIVATE);
        int highestScore = sharedPreferences.getInt("highestScore", 0);

        if (score >= 200){
            textViewResultInfo.setText("Congratulations, You won the game!");
            textViewMyScore.setText("Your Score is: " + score);
            textViewHighestScore.setText("Highest Score: " + score);
            sharedPreferences.edit().putInt("highestScore", score).apply();
        }else if (score >= highestScore){
            textViewResultInfo.setText("Sorry, You lost the game");
            textViewMyScore.setText("Your Score is: " + score);
            textViewHighestScore.setText("Highest Score: " + score);
            sharedPreferences.edit().putInt("highestScore", score).apply();
        }else {
            textViewResultInfo.setText("Sorry, You lost the game");
            textViewMyScore.setText("Your Score is: " + score);
            textViewHighestScore.setText("Highest Score: " + highestScore);

        }

        buttonAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ResultActivity.this);
        builder.setTitle("Help The Innocent Bird");
        builder.setMessage("Are you sure you want to quit the game?");
        builder.setCancelable(false);
        builder.setNegativeButton("Quit Game", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(0);
            }
        });

        builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.create().show();
    }
}