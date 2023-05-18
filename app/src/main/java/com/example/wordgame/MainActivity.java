package com.example.wordgame;

import androidx.appcompat.app.AppCompatActivity;
import java.util.*;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    char ch[]=new char[16];
    int md=0;long timesec;
    String wd="";
    String prmpt="";
    EditText inputWord; TextView hiscore;
    EditText gettimebox;
    EditText inputPrompt;
    Button startGame,normalbtn,timedbtn,start1;
    Vibrator vibr;
    public void showtimepopup(){
        final Dialog dialog1 = new Dialog(MainActivity.this);
        dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog1.setCancelable(true);
        dialog1.setContentView(R.layout.gettime);
        start1=(Button)findViewById(R.id.start1);
        EditText gettimebox = dialog1.findViewById(R.id.gettimebox);
        Button start1 = dialog1.findViewById(R.id.start1);
        final MediaPlayer ltsgo=MediaPlayer.create(this, R.raw.letsgo);
        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ltsgo.start();
                timesec=Long.parseLong(gettimebox.getText().toString());
                Bundle bundle=new Bundle();
                bundle.putCharArray("str1",ch);
                bundle.putString("str2",wd);
                bundle.putString("str3",prmpt);
                bundle.putInt("int1",md);
                bundle.putLong("int2",timesec);
                Intent i=new Intent(MainActivity.this,MainActivity3.class);
                i.putExtra("str1",ch);
                i.putExtra("str2",wd);
                i.putExtra("str3",prmpt);
                i.putExtra("int1",md);
                i.putExtra("int2",timesec);
                startActivity(i);
                dialog1.dismiss();
            }
        });
        dialog1.show();
    }
    public void Modeselector(){
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.askmodepopup);
        normalbtn=(Button)findViewById(R.id.normalbtn);
        timedbtn=(Button)findViewById(R.id.timedbtn);
        Button nbtn = dialog.findViewById(R.id.normalbtn);
        Button tbtn = dialog.findViewById(R.id.timedbtn);
        final MediaPlayer strt=MediaPlayer.create(this, R.raw.start);
        nbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strt.start();
                md=0;
                dialog.dismiss();
                Bundle bundle=new Bundle();
                bundle.putCharArray("str1",ch);
                bundle.putString("str2",wd);
                bundle.putString("str3",prmpt);
                bundle.putInt("int1",md);
                Intent i=new Intent(MainActivity.this,MainActivity3.class);
                i.putExtra("str1",ch);
                i.putExtra("str2",wd);
                i.putExtra("str3",prmpt);
                i.putExtra("int1",md);
                startActivity(i);
            }
        });
        tbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                md=1;
                dialog.dismiss();
                showtimepopup();

            }
        });
        dialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        hiscore=(TextView) findViewById(R.id.hiscore);
        inputWord=(EditText) findViewById(R.id.inputWord);
        inputPrompt=(EditText) findViewById(R.id.inputPrompt);
        startGame=(Button) findViewById(R.id.startGame);
        vibr=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        SharedPreferences settings = getApplicationContext().getSharedPreferences("com.example.wordgame", 0);
        int bestscore = settings.getInt("scoreget", 0);
        hiscore.setText(" HIGHSCORE: "+bestscore+" ");
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prmpt = inputPrompt.getText().toString();
                wd = inputWord.getText().toString();
                if (wd.length() != 0 && wd.length() <= 16) {
                    Modeselector();
                    vibr.vibrate(200);
                    int index;
                    char temp;
                    Random random = new Random();
                    for (int i = 0; i < wd.length(); i++)
                        ch[i] = wd.charAt(i);
                    for (int i = wd.length(); i < 16; i++) {
                        ch[i] = (char) ('A' + random.nextInt(26));
                    }
                    for (int i = ch.length - 1; i > 0; i--) {
                        index = random.nextInt(i + 1);
                        temp = ch[index];
                        ch[index] = ch[i];
                        ch[i] = temp;
                    }
                }
                else if(wd.length()==0)
                    Toast.makeText(MainActivity.this, "Enter a Word", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Enter a maximum of 16 letters", Toast.LENGTH_SHORT).show();
            }
        });

    }
}