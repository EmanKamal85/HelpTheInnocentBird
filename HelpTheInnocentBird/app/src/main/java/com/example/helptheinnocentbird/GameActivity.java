package com.example.helptheinnocentbird;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    ImageView bird, enemy1, enemy2, enemy3, coin1, coin2, right1, right2, right3;
    TextView textViewScore, startInfo;
    ConstraintLayout constraintLayout;

    boolean touchControl = false;
    boolean beginControl = false;

    int screenHeight;
    int screenWidth;

    int birdX, enemy1X, enemy2X, enemy3X, coin1X, coin2X;
    int birdY, enemy1Y, enemy2Y, enemy3Y, coin1Y, coin2Y;

    int rights =3;
    int score = 0;

    Handler handler, handler2;
    Runnable runnable, runnable2;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        bird = findViewById(R.id.bird_imageview);
        enemy1 = findViewById(R.id.enemy1_imageview);
        enemy2 = findViewById(R.id.enemy2_imageview);
        enemy3 = findViewById(R.id.enemy3_imageview);
        coin1 = findViewById(R.id.coin_imageview);
        coin2 = findViewById(R.id.coin_imageview_2);
        right1 = findViewById(R.id.right1);
        right2 = findViewById(R.id.right2);
        right3 = findViewById(R.id.right3);
        textViewScore = findViewById(R.id.score_textview);
        startInfo = findViewById(R.id.start_info_textview);
        constraintLayout = findViewById(R.id.constraint_layout);

        constraintLayout.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                startInfo.setVisibility(View.INVISIBLE);

                if (!beginControl){
                    beginControl = true;

                    screenWidth = (int)constraintLayout.getWidth();
                    screenHeight = (int)constraintLayout.getHeight();

                    birdX = (int) bird.getX();
                    birdY = (int) bird.getY();



                    handler = new Handler();
                    runnable = new Runnable() {
                        @Override
                        public void run() {
                            moveBird();
                            enemyControl();
                            collisionControl();

                        }

                    };
                    handler.post(runnable);
                }

                else {
                    if (event.getAction() == MotionEvent.ACTION_DOWN){
                        touchControl = true;
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP){
                        touchControl = false;
                    }
                }


                return true;
            }
        });


    }

    public void moveBird(){
        if (touchControl){
            birdY = birdY - (screenHeight/50);
        }else {
            birdY = birdY + (screenHeight/50);
        }

        if (birdY <0){
            birdY = 0;
        }

        if (birdY >= (screenHeight - bird.getHeight())){
            birdY = screenHeight - bird.getHeight();
        }



        bird.setY(birdY);

    }

    public void enemyControl(){

        enemy1.setVisibility(View.VISIBLE);
        enemy2.setVisibility(View.VISIBLE);
        enemy3.setVisibility(View.VISIBLE);
        coin1.setVisibility(View.VISIBLE);
        coin2.setVisibility(View.VISIBLE);

        //enemy1
        enemy1X = enemy1X - (screenWidth/150);

        if (score >= 50 && score < 100){
            enemy1X = enemy1X - (screenWidth/130);
        }

        if (score >= 100 && score < 150){
            enemy1X = enemy1X - (screenWidth/110);
        }

        if (score >= 150 && score < 200){
            enemy1X = enemy1X - (screenWidth/100);
        }

        if (enemy1X < 0){
            enemy1X = screenWidth + 200;
            enemy1Y = (int) Math.floor(Math.random() * screenHeight);

            if (enemy1Y < 0){
                enemy1Y = 0;
            }

            if (enemy1Y >= (screenHeight - enemy1.getHeight())){
                enemy1Y = screenHeight-enemy1.getHeight();
            }

        }

        enemy1.setX(enemy1X);
        enemy1.setY(enemy1Y);
        //enemy1

        //enemy2
        enemy2X = enemy2X - (screenWidth/140);

        if (score >= 50 && score < 100){
            enemy2X = enemy2X - (screenWidth/120);
        }

        if (score >= 100 && score < 150){
            enemy2X = enemy2X - (screenWidth/110);
        }

        if (score >= 150 && score < 200){
            enemy2X = enemy2X - (screenWidth/90);
        }

        if (enemy2X < 0){
            enemy2X = screenWidth + 200;
            enemy2Y = (int) Math.floor(Math.random() * screenHeight);

            if (enemy2Y < 0){
                enemy2Y = 0;
            }

            if (enemy2Y >= (screenHeight - enemy2.getHeight())){
                enemy2Y = screenHeight-enemy2.getHeight();
            }

        }

        enemy2.setX(enemy2X);
        enemy2.setY(enemy2Y);
        //enemy2

        //enemy3
        enemy3X = enemy3X - (screenWidth/130);

        if (score >= 50 && score < 100){
            enemy3X = enemy3X - (screenWidth/120);
        }

        if (score >= 100 && score < 150){
            enemy3X = enemy3X - (screenWidth/100);
        }

        if (score >= 150 && score < 200){
            enemy3X = enemy3X - (screenWidth/80);
        }

        if (enemy3X < 0){
            enemy3X = screenWidth + 200;
            enemy3Y = (int) Math.floor(Math.random() * screenHeight);

            if (enemy3Y < 0){
                enemy3Y = 0;
            }

            if (enemy3Y >= (screenHeight - enemy3.getHeight())){
                enemy3Y = screenHeight-enemy3.getHeight();
            }

        }

        enemy3.setX(enemy3X);
        enemy3.setY(enemy3Y);
        //enemy3

        //coin1
        coin1X = coin1X - (screenWidth/150);

        if (coin1X < 0){
            coin1X = screenWidth + 200;
            coin1Y = (int) Math.floor(Math.random() * screenHeight);

            if (coin1Y < 0){
                coin1Y = 0;
            }

            if (coin1Y >= (screenHeight - coin1.getHeight())){
                coin1Y = screenHeight-coin1.getHeight();
            }

        }

        coin1.setX(coin1X);
        coin1.setY(coin1Y);
        //coin1

        //coin2
        coin2X = coin2X - (screenWidth/150);

        if (coin2X < 0){
            coin2X = screenWidth + 200;
            coin2Y = (int) Math.floor(Math.random() * screenHeight);

            if (coin2Y < 0){
                coin2Y = 0;
            }

            if (coin2Y >= (screenHeight - coin2.getHeight())){
                coin2Y = screenHeight-coin2.getHeight();
            }

        }

        coin2.setX(coin2X);
        coin2.setY(coin2Y);
        //coin2
    }

    public void collisionControl(){

        //enemy1
        int enemy1CenterX = enemy1X + (enemy1.getWidth()/2);
        int enemy1CenterY = enemy1Y + (enemy1.getHeight()/2);

        if (enemy1CenterX >= birdX &&
            enemy1CenterX <= (birdX + bird.getWidth()) &&
            enemy1CenterY >= birdY &&
            enemy1CenterY < (birdY + bird.getHeight())){

            enemy1X = screenWidth + 200;
            rights--;
        }
        //enemy1


        //enemy2
        int enemy2CenterX = enemy2X + (enemy2.getWidth()/2);
        int enemy2CenterY = enemy2Y + (enemy2.getHeight()/2);

        if (enemy2CenterX >= birdX &&
                enemy2CenterX <= (birdX + bird.getWidth()) &&
                enemy2CenterY >= birdY &&
                enemy2CenterY < (birdY + bird.getHeight())){

            enemy2X = screenWidth + 200;
            rights--;
        }
        //enemy2

        //enemy3
        int enemy3CenterX = enemy3X + (enemy3.getWidth()/2);
        int enemy3CenterY = enemy3Y + (enemy3.getHeight()/2);

        if (enemy3CenterX >= birdX &&
                enemy3CenterX <= (birdX + bird.getWidth()) &&
                enemy3CenterY >= birdY &&
                enemy3CenterY < (birdY + bird.getHeight())){

            enemy3X = screenWidth + 200;
            rights--;
        }
        //enemy2

        //coin1
        int coin1CenterX = coin1X + (coin1.getWidth()/2);
        int coin1CenterY = coin1Y + (coin1.getHeight()/2);

        if (coin1CenterX >= birdX &&
                coin1CenterX <= (birdX + bird.getWidth()) &&
                coin1CenterY >= birdY &&
                coin1CenterY < (birdY + bird.getHeight())){

            coin1X = screenWidth + 200;
            score += 10;
            textViewScore.setText(" " + score);
        }
        //coin1

        //coin2
        int coin2CenterX = coin2X + (coin2.getWidth()/2);
        int coin2CenterY = coin2Y + (coin2.getHeight()/2);

        if (coin2CenterX >= birdX &&
                coin2CenterX <= (birdX + bird.getWidth()) &&
                coin2CenterY >= birdY &&
                coin2CenterY < (birdY + bird.getHeight())){

            coin2X = screenWidth + 200;
            score += 10;
            textViewScore.setText(" " + score);
        }
        //coin2

        if (rights >0 && score < 200){
            if (rights == 2){
                right1.setImageResource(R.drawable.grey_heart);
            }

            if (rights == 1){
                right2.setImageResource(R.drawable.grey_heart);
            }
            handler.postDelayed(runnable, 20);


        } else if (score >= 200){
            handler.removeCallbacks(runnable);
            startInfo.setVisibility(View.VISIBLE);
            startInfo.setText("Congratulations, You won the game");
            enemy1.setVisibility(View.INVISIBLE);
            enemy2.setVisibility(View.INVISIBLE);
            enemy3.setVisibility(View.INVISIBLE);
            coin1.setVisibility(View.INVISIBLE);
            coin2.setVisibility(View.INVISIBLE);
            constraintLayout.setEnabled(false);

            handler2 = new Handler();
            runnable2 = new Runnable() {
                @Override
                public void run() {
                    birdX = birdX + (screenWidth/300);
                    bird.setX(birdX);
                    bird.setY(screenHeight/2f);

                    if (birdX <= screenWidth){
                        handler2.postDelayed(runnable2, 20);
                    }else {
                        handler2.removeCallbacks(runnable2);
                        Intent intent = new Intent(GameActivity.this, ResultActivity.class);
                        intent.putExtra("score", score);
                        startActivity(intent);
                        finish();

                    }
                        }
            };
            handler2.post(runnable2);

        }else if (rights == 0){
            handler.removeCallbacks(runnable);
            right3.setImageResource(R.drawable.grey_heart);
            Intent intent = new Intent(GameActivity.this, ResultActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
            finish();

        }


    }
}