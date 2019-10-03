package com.example.littleminesweeper;

import com.example.littleminesweeper.View.mineSweeperView;
import com.google.android.material.snackbar.Snackbar;


import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ToggleButton;


public class MainActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private Button startButton;
    private Button placeFlagButton;
    private Button newFieldButton;
    private ImageButton reStartButton;

//    Bitmap bmp = ((BitmapDrawable)getResources().getDrawable(R.drawable.bg)).getBitmap();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final mineSweeperView gameView = findViewById(R.id.s_gameView);
//        Button startButton = findViewById(R.id.s_startButton);
        ImageButton reStartButton = findViewById(R.id.image_reStartButton);
        ToggleButton newField_tb = findViewById(R.id.s_toggleButton);

        newField_tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked==true){
                    gameView.drawFlag(100);
                }
                else{
                    gameView.drawFlag(555);
                }

            }
        });
//        startButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                gameView.resetGame();
//            }
//        });


            reStartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameView.resetGame();
                }
            });



    }



    public void setMessage(){
        Snackbar.make(findViewById(R.id.s_gameView),"GAME OVER!" , Snackbar.LENGTH_INDEFINITE)
        //Snackbar.make(Linearlayout,"GAME OVER!" , Snackbar.LENGTH_LONG) This one does not working

                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }

}




