package com.example.wordgame;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    char ch[]=new char[16];
    String wd="";
    String prmpt="";
    EditText inputWord; TextView hiscore;
    EditText inputPrompt;
    Button startGame;
    Vibrator vibr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hiscore=(TextView) findViewById(R.id.hiscore);
        inputWord=(EditText) findViewById(R.id.inputWord);
        inputPrompt=(EditText) findViewById(R.id.inputPrompt);
        startGame=(Button) findViewById(R.id.startGame);
        final MediaPlayer strt=MediaPlayer.create(this, R.raw.start);
        vibr=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        SharedPreferences settings = getApplicationContext().getSharedPreferences("com.example.wordgame", 0);
        int bestscore = settings.getInt("scoreget", 0);
        hiscore.setText(" HIGHSCORE: "+bestscore+" ");
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prmpt=inputPrompt.getText().toString();
                wd=inputWord.getText().toString();
                strt.start();
                vibr.vibrate(200);
                int index;char temp;
                Random random = new Random();
                for(int i=0;i<wd.length();i++)
                    ch[i]=wd.charAt(i);
                for(int i=wd.length();i<16;i++)
                {
                    ch[i] = (char) ('A' + random.nextInt(26));
                }
                for (int i = ch.length - 1; i > 0; i--)
                {
                    index = random.nextInt(i + 1);
                    temp = ch[index];
                    ch[index] = ch[i];
                    ch[i] = temp;
                }
                Bundle bundle=new Bundle();
                bundle.putCharArray("str1",ch);
                bundle.putString("str2",wd);
                bundle.putString("str3",prmpt);
                Intent i=new Intent(MainActivity.this,MainActivity3.class);
                i.putExtra("str1",ch);
                i.putExtra("str2",wd);
                i.putExtra("str3",prmpt);
                startActivity(i);
            }
        });

    }
}