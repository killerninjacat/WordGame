package com.example.wordgame;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity3 extends AppCompatActivity {
    Button button1;    Button button2;    Button button3;    Button button4;    Button button5;    Button button6;    Button button7;    Button button8;    Button button9;    Button button10;    Button button11;    Button button12;    Button button13;Button button14;Button button15;Button button16;
    TextView answer,actualtimer;TextView enterclue;
    Vibrator vib;
    Button clueinfo,check,life1,life2,life3,reset,ok,homebutton,playagain;
    String prmpt;
    String wd;String answ1="";
    int chk1=0,chk2=0,chk3=0,chk4=0,chk5=0,chk6=0,chk7=0,chk8=0,chk9=0,chk10=0,chk11=0,chk12=0,chk13=0,chk14=0,chk15=0,chk16=0;
    char ch[]=new char[16];static int tmp;
    int k=0,l=0,m=0,d=0,md,timesec,sz=0;
    int score=1500;int scr;
    public void Updatescore() {
        Log.d("insidemethod", "Value: " + Integer.toString(score));
        SharedPreferences settings = getApplicationContext().getSharedPreferences("com.example.wordgame", 0);
        int bestscore = settings.getInt("scoreget", 0);
        if (score > bestscore) {
            SharedPreferences.Editor editor = settings.edit();
            editor.putInt("scoreget", score);
            editor.commit();
        }
    }
    public void Timerset(int s,final TextView actualtime){

        new CountDownTimer(s* 1000+1000, 1000) {

            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                actualtime.setText("TIME : " + minutes
                        + ":" + seconds);
            }

            public void onFinish() {
                actualtime.setText("Time's Up!");
                score=0;
                sz=1;
                Toast.makeText(MainActivity3.this, "Time's Up!", Toast.LENGTH_SHORT).show();
                gameoverdialog();
            }
        }.start();
    }
    public void cluedialog(){
        final Dialog dialog = new Dialog(MainActivity3.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.cluepopup);
        ok=(Button)findViewById(R.id.ok);
        TextView txt = dialog.findViewById(R.id.enterclue);
        Button btn = dialog.findViewById(R.id.ok);
        txt.setText(prmpt);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void gameoverdialog(){
        final Dialog dialog = new Dialog(MainActivity3.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.getWindow().setLayout(500,500);
        dialog.setContentView(R.layout.gameoverpopup);
        homebutton=(Button)findViewById(R.id.homebutton);
        playagain=(Button)findViewById(R.id.playagain);
        TextView txt = dialog.findViewById(R.id.displayscore);
        final MediaPlayer plyagn=MediaPlayer.create(this, R.raw.playagain);
        final MediaPlayer hme=MediaPlayer.create(this, R.raw.home);
        Button homebutton=dialog.findViewById(R.id.homebutton);
        Button playagain=dialog.findViewById(R.id.playagain);
        txt.setText("Your Score: "+score);
        playagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                plyagn.start();
                button1.setBackgroundResource(R.drawable.unclicked);
                button2.setBackgroundResource(R.drawable.unclicked);
                button3.setBackgroundResource(R.drawable.unclicked);
                button4.setBackgroundResource(R.drawable.unclicked);
                button5.setBackgroundResource(R.drawable.unclicked);
                button6.setBackgroundResource(R.drawable.unclicked);
                button7.setBackgroundResource(R.drawable.unclicked);
                button8.setBackgroundResource(R.drawable.unclicked);
                button9.setBackgroundResource(R.drawable.unclicked);
                button10.setBackgroundResource(R.drawable.unclicked);
                button11.setBackgroundResource(R.drawable.unclicked);
                button12.setBackgroundResource(R.drawable.unclicked);
                button13.setBackgroundResource(R.drawable.unclicked);
                button14.setBackgroundResource(R.drawable.unclicked);
                button15.setBackgroundResource(R.drawable.unclicked);
                button16.setBackgroundResource(R.drawable.unclicked);
                l=0;m=1;
                score=1500;
                life1.setBackgroundResource(R.drawable.redheart);
                life2.setBackgroundResource(R.drawable.redheart);
                life3.setBackgroundResource(R.drawable.redheart);
                startActivity(getIntent());
                finish();
                overridePendingTransition(0, 0);
                k=0;
                chk1=0;chk2=0;chk3=0;chk4=0;chk5=0;chk6=0;chk6=0;chk7=0;chk8=0;chk9=0;chk10=0;chk11=0;chk12=0;chk13=0;chk14=0;chk15=0;chk16=0;
                dialog.dismiss();
            }
        });
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hme.start();
                Intent in=new Intent(MainActivity3.this,MainActivity.class);
                startActivity(in);
            }
        });
        dialog.show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main_1);
        this.getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        ch=getIntent().getCharArrayExtra("str1");
        wd=getIntent().getStringExtra("str2");
        prmpt=getIntent().getStringExtra("str3");
        md=getIntent().getIntExtra("int1",0);
        timesec=getIntent().getIntExtra("int2",0);
        actualtimer=(TextView) findViewById(R.id.actualtimer);
        if(md==1)
            Timerset(timesec,actualtimer);
        else
            actualtimer.setVisibility(View.INVISIBLE);
        vib=(Vibrator)getSystemService(VIBRATOR_SERVICE);
        final MediaPlayer ltrclk=MediaPlayer.create(this, R.raw.notif);
        final MediaPlayer rst=MediaPlayer.create(this, R.raw.resetsound);
        final MediaPlayer wrng=MediaPlayer.create(this, R.raw.wrongans);
        final MediaPlayer gover=MediaPlayer.create(this,R.raw.gameover);
        final MediaPlayer win=MediaPlayer.create(this,R.raw.success);
        String ans[]=new String[wd.length()];
        reset=(Button) findViewById(R.id.reset);
        life1=(Button) findViewById(R.id.life1);
        life2=(Button) findViewById(R.id.life2);
        life3=(Button) findViewById(R.id.life3);
        check=(Button) findViewById(R.id.check);
        button1 = (Button) findViewById(R.id.button1);
        button1.setText(ch[0]+" ");
        button2 = (Button) findViewById(R.id.button2);
        button2.setText(ch[1]+" ");
        button3 = (Button) findViewById(R.id.button3);
        button3.setText(ch[2]+" ");
        button4 = (Button) findViewById(R.id.button4);
        button4.setText(ch[3]+" ");
        button5 = (Button) findViewById(R.id.button5);
        button5.setText(ch[4]+" ");
        button6 = (Button) findViewById(R.id.button6);
        button6.setText(ch[5]+" ");
        button7 = (Button) findViewById(R.id.button7);
        button7.setText(ch[6]+" ");
        button8 = (Button) findViewById(R.id.button8);
        button8.setText(ch[7]+" ");
        button9 = (Button) findViewById(R.id.button9);
        button9.setText(ch[8]+" ");
        button10 = (Button) findViewById(R.id.button10);
        button10.setText(ch[9]+" ");
        button11 = (Button) findViewById(R.id.button11);
        button11.setText(ch[10]+" ");
        button12 = (Button) findViewById(R.id.button12);
        button12.setText(ch[11]+" ");
        button13 = (Button) findViewById(R.id.button13);
        button13.setText(ch[12]+" ");
        button14 = (Button) findViewById(R.id.button14);
        button14.setText(ch[13]+" ");
        button15 = (Button) findViewById(R.id.button15);
        button15.setText(ch[14]+" ");
        button16 = (Button) findViewById(R.id.button16);
        button16.setText(ch[15]+" ");
        clueinfo=(Button)findViewById(R.id.clueinfo);
        clueinfo.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            cluedialog();
                                        }
                                    }
        );
            for (int i = 0; i < wd.length(); i++)
                ans[i] = " _ ";
            String answ = TextUtils.join("", ans);
            answer = (TextView) findViewById(R.id.answer);
            answer.setText(answ);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk1==0)
                if(k<ans.length) {
                    button1.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[0] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                    Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk1=1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk2==0)
                if(k<ans.length) {
                    button2.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[1] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                    Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk2=1;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk3==0)
                if(k<ans.length) {
                    button3.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[2] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk3=1;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk4==0)
                if(k<ans.length) {
                    button4.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[3] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk4=1;
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk5==0)
                if(k<ans.length) {
                    button5.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[4] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk5=1;
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk6==0)
                if(k<ans.length) {
                    button6.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[5] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk6=1;
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk7==0)
                if(k<ans.length) {
                    button7.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[6] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk7=1;
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk8==0)
                if(k<ans.length) {
                    button8.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[7] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk8=1;
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk9==0)
                if(k<ans.length) {
                    button9.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[8] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk9=1;
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk10==0)
                if(k<ans.length) {
                    button10.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[9] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk10=1;
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk11==0)
                if(k<ans.length) {
                    button11.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[10] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk11=1;
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk12==0)
                if(k<ans.length) {
                    button12.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[11] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk12=1;
            }
        });
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk13==0)
                if(k<ans.length) {
                    button13.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[12] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk13=1;
            }
        });
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk14==0)
                if(k<ans.length) {
                    button14.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[13] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk14=1;
            }
        });
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk15==0)
                if(k<ans.length) {
                    button15.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[14] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk15=1;
            }
        });
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(chk16==0)
                if(k<ans.length) {
                    button16.setBackgroundResource(R.drawable.clicked);
                    ans[k++] = " " + ch[15] + " ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    ltrclk.start();
                    vib.vibrate(100);
                }
                else
                Toast.makeText(MainActivity3.this, "Letter limit reached!", Toast.LENGTH_SHORT).show();
                chk16=1;
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answ=TextUtils.join("",ans);
                for(int q=0;q<answ.length();q++)
                    if(Character.isAlphabetic(answ.charAt(q)))
                        answ1=answ1+answ.charAt(q);
                if(ans[wd.length()-1].compareTo(" _ ")!=0) {
                    if (answ1.equalsIgnoreCase(wd)&&l<3) {
                        win.start();
                        Log.d("ADebugTag", "Value: " + Integer.toString(score));
                        Updatescore();
                        Toast.makeText(MainActivity3.this, "Correct Answer!", Toast.LENGTH_SHORT).show();
                        gameoverdialog();
                    } else if (l == 0) {
                        score-=500;
                        scr=score;
                        Log.d("ADebugTag", "Value: " + Integer.toString(score));
                        wrng.start();
                        life1.setBackgroundResource(R.drawable.yellowheart);
                        Toast.makeText(MainActivity3.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                        l++;
                        button1.setBackgroundResource(R.drawable.unclicked);
                        button2.setBackgroundResource(R.drawable.unclicked);
                        button3.setBackgroundResource(R.drawable.unclicked);
                        button4.setBackgroundResource(R.drawable.unclicked);
                        button5.setBackgroundResource(R.drawable.unclicked);
                        button6.setBackgroundResource(R.drawable.unclicked);
                        button7.setBackgroundResource(R.drawable.unclicked);
                        button8.setBackgroundResource(R.drawable.unclicked);
                        button9.setBackgroundResource(R.drawable.unclicked);
                        button10.setBackgroundResource(R.drawable.unclicked);
                        button11.setBackgroundResource(R.drawable.unclicked);
                        button12.setBackgroundResource(R.drawable.unclicked);
                        button13.setBackgroundResource(R.drawable.unclicked);
                        button14.setBackgroundResource(R.drawable.unclicked);
                        button15.setBackgroundResource(R.drawable.unclicked);
                        button16.setBackgroundResource(R.drawable.unclicked);
                        int index;char temp;
                        chk1=0;chk2=0;chk3=0;chk4=0;chk5=0;chk6=0;chk6=0;chk7=0;chk8=0;chk9=0;chk10=0;chk11=0;chk12=0;chk13=0;chk14=0;chk15=0;chk16=0;
                        Random random = new Random();
                        for (int i = ch.length - 1; i > 0; i--)
                        {
                            index = random.nextInt(i + 1);
                            temp = ch[index];
                            ch[index] = ch[i];
                            ch[i] = temp;
                        }
                        button1.setText(ch[0]+" ");
                        button2.setText(ch[1]+" ");
                        button3.setText(ch[2]+" ");
                        button4.setText(ch[3]+" ");
                        button5.setText(ch[4]+" ");
                        button6.setText(ch[5]+" ");
                        button7.setText(ch[6]+" ");
                        button8.setText(ch[7]+" ");
                        button9.setText(ch[8]+" ");
                        button10.setText(ch[9]+" ");
                        button11.setText(ch[10]+" ");
                        button12.setText(ch[11]+" ");
                        button13.setText(ch[12]+" ");
                        button14.setText(ch[13]+" ");
                        button15.setText(ch[14]+" ");
                        button16.setText(ch[15]+" ");
                        for (int c = 0; c < wd.length(); c++)
                            ans[c] = " _ ";
                        answ = TextUtils.join("", ans);
                        answer.setText(answ);
                        k = 0;answ1="";
                    } else if (l == 1) {
                        score-=500;
                        scr=score;
                        Log.d("ADebugTag", "Value: " + Integer.toString(score));
                        wrng.start();
                        life2.setBackgroundResource(R.drawable.yellowheart);
                        Toast.makeText(MainActivity3.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                        l++;
                        button1.setBackgroundResource(R.drawable.unclicked);
                        button2.setBackgroundResource(R.drawable.unclicked);
                        button3.setBackgroundResource(R.drawable.unclicked);
                        button4.setBackgroundResource(R.drawable.unclicked);
                        button5.setBackgroundResource(R.drawable.unclicked);
                        button6.setBackgroundResource(R.drawable.unclicked);
                        button7.setBackgroundResource(R.drawable.unclicked);
                        button8.setBackgroundResource(R.drawable.unclicked);
                        button9.setBackgroundResource(R.drawable.unclicked);
                        button10.setBackgroundResource(R.drawable.unclicked);
                        button11.setBackgroundResource(R.drawable.unclicked);
                        button12.setBackgroundResource(R.drawable.unclicked);
                        button13.setBackgroundResource(R.drawable.unclicked);
                        button14.setBackgroundResource(R.drawable.unclicked);
                        button15.setBackgroundResource(R.drawable.unclicked);
                        button16.setBackgroundResource(R.drawable.unclicked);
                        int index;char temp;
                        chk1=0;chk2=0;chk3=0;chk4=0;chk5=0;chk6=0;chk6=0;chk7=0;chk8=0;chk9=0;chk10=0;chk11=0;chk12=0;chk13=0;chk14=0;chk15=0;chk16=0;
                        Random random = new Random();
                        for (int i = ch.length - 1; i > 0; i--)
                        {
                            index = random.nextInt(i + 1);
                            temp = ch[index];
                            ch[index] = ch[i];
                            ch[i] = temp;
                        }
                        button1.setText(ch[0]+" ");
                        button2.setText(ch[1]+" ");
                        button3.setText(ch[2]+" ");
                        button4.setText(ch[3]+" ");
                        button5.setText(ch[4]+" ");
                        button6.setText(ch[5]+" ");
                        button7.setText(ch[6]+" ");
                        button8.setText(ch[7]+" ");
                        button9.setText(ch[8]+" ");
                        button10.setText(ch[9]+" ");
                        button11.setText(ch[10]+" ");
                        button12.setText(ch[11]+" ");
                        button13.setText(ch[12]+" ");
                        button14.setText(ch[13]+" ");
                        button15.setText(ch[14]+" ");
                        button16.setText(ch[15]+" ");
                        for (int c = 0; c < wd.length(); c++)
                            ans[c] = " _ ";
                        answ = TextUtils.join("", ans);
                        answer.setText(answ);
                        k = 0;answ1="";
                    } else {
                        score-=500;
                        scr=score;
                        Log.d("ADebugTag", "Value: " + Integer.toString(score));
                        life3.setBackgroundResource(R.drawable.yellowheart);
                        Toast.makeText(MainActivity3.this, "Wrong Answer!", Toast.LENGTH_SHORT).show();
                        l++;
                        Updatescore();
                        gover.start();
                        gameoverdialog();
                    }
                }
                else
                    Toast.makeText(MainActivity3.this, "Enter all the letters before checking!", Toast.LENGTH_SHORT).show();
            }
        });
            reset.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rst.start();
                    button1.setBackgroundResource(R.drawable.unclicked);
                    button2.setBackgroundResource(R.drawable.unclicked);
                    button3.setBackgroundResource(R.drawable.unclicked);
                    button4.setBackgroundResource(R.drawable.unclicked);
                    button5.setBackgroundResource(R.drawable.unclicked);
                    button6.setBackgroundResource(R.drawable.unclicked);
                    button7.setBackgroundResource(R.drawable.unclicked);
                    button8.setBackgroundResource(R.drawable.unclicked);
                    button9.setBackgroundResource(R.drawable.unclicked);
                    button10.setBackgroundResource(R.drawable.unclicked);
                    button11.setBackgroundResource(R.drawable.unclicked);
                    button12.setBackgroundResource(R.drawable.unclicked);
                    button13.setBackgroundResource(R.drawable.unclicked);
                    button14.setBackgroundResource(R.drawable.unclicked);
                    button15.setBackgroundResource(R.drawable.unclicked);
                    button16.setBackgroundResource(R.drawable.unclicked);
                    int index;char temp;
                    chk1=0;chk2=0;chk3=0;chk4=0;chk5=0;chk6=0;chk6=0;chk7=0;chk8=0;chk9=0;chk10=0;chk11=0;chk12=0;chk13=0;chk14=0;chk15=0;chk16=0;
                    Random random = new Random();
                    for (int i = ch.length - 1; i > 0; i--)
                    {
                        index = random.nextInt(i + 1);
                        temp = ch[index];
                        ch[index] = ch[i];
                        ch[i] = temp;
                    }
                    button1.setText(ch[0]+" ");
                    button2.setText(ch[1]+" ");
                    button3.setText(ch[2]+" ");
                    button4.setText(ch[3]+" ");
                    button5.setText(ch[4]+" ");
                    button6.setText(ch[5]+" ");
                    button7.setText(ch[6]+" ");
                    button8.setText(ch[7]+" ");
                    button9.setText(ch[8]+" ");
                    button10.setText(ch[9]+" ");
                    button11.setText(ch[10]+" ");
                    button12.setText(ch[11]+" ");
                    button13.setText(ch[12]+" ");
                    button14.setText(ch[13]+" ");
                    button15.setText(ch[14]+" ");
                    button16.setText(ch[15]+" ");
                    Toast.makeText(MainActivity3.this, "Answer has been reset!", Toast.LENGTH_SHORT).show();
                    for (int c = 0; c < wd.length(); c++)
                        ans[c] = " _ ";
                    String answ = TextUtils.join("", ans);
                    answer.setText(answ);
                    k = 0;answ1="";
                }
            });
        Log.d("outerscoretag", "Value: " + Integer.toString(score));


        }
    }

