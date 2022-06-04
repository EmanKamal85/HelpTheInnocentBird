package com.example.helptheinnocentbird;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView volume, innocentBird, enemy1, enemy2, enemy3, coin;
    AppCompatButton buttonStart;
    Animation animation;
    MediaPlayer mediaPlayer;
    boolean volumeStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        volume = findViewById(R.id.volume);
        innocentBird = findViewById(R.id.innocent_bird);
        enemy1 = findViewById(R.id.enemy1);
        enemy2 = findViewById(R.id.enemy2);
        enemy3 = findViewById(R.id.enemy3);
        coin = findViewById(R.id.coin);
        buttonStart = findViewById(R.id.button_start);

        animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.scale_animation);
        innocentBird.setAnimation(animation);
        enemy1.setAnimation(animation);
        enemy2.setAnimation(animation);
        enemy3.setAnimation(animation);
        coin.setAnimation(animation);

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                buttonStart.startAnimation(animation);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.game_music);
        mediaPlayer.start();
        volume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!volumeStatus){
                    mediaPlayer.setVolume(0,0);
                    volume.setImageResource(R.drawable.ic_baseline_volume_off_24);
                    volumeStatus = true;
                }else {
                    mediaPlayer.setVolume(1,1);
                    volume.setImageResource(R.drawable.ic_baseline_volume_up_24);
                    volumeStatus = false;
                }
            }
        });

        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.reset();
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onPause() {
        mediaPlayer.reset();
        super.onPause();
    }
}