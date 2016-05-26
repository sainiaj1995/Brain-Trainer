package ajay.braintrainer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    Button GoButton;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView Sumtextview;
    TextView pointstextview;
    TextView Timertextview;
    TextView resulttextview;
    RelativeLayout gameRelativeLayout;
    Button playaginbutton;
    ArrayList<Integer> answers=new ArrayList<Integer>();
    int locationofcorrectAns;
    int score=0,numberofqsns=0;
    boolean isGameactive=false;
    Random rand=new Random();
    public void Start(View view){
        GoButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(RelativeLayout.VISIBLE);
        playAgain(view);

    }
    public void genrateQsn(){
        int a=rand.nextInt(21);
        int b=rand.nextInt(21);
        int wrongsum=0;

        Sumtextview.setText(a+" + "+b);
        locationofcorrectAns=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){
            if(i==locationofcorrectAns){
                answers.add(a+b);
            }else{
               wrongsum=rand.nextInt(41);
                while(wrongsum==a+b){
                    wrongsum=rand.nextInt(41);
                }
                answers.add(wrongsum=rand.nextInt(41));
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }
    public void playAgain(View view){
        isGameactive=true;
        score=0;
        numberofqsns=0;
        Timertextview.setText("30s");
        pointstextview.setText("0/0");
        resulttextview.setText("");
        playaginbutton.setVisibility(View.INVISIBLE);
        genrateQsn();
        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                Timertextview.setText(String.valueOf(millisUntilFinished/1000)+"s");
            }

            @Override
            public void onFinish() {
                isGameactive=false;
                playaginbutton.setVisibility(View.VISIBLE);
                Timertextview.setText("0s");
                resulttextview.setText("Your score "+Integer.toString(score)+"/"+Integer.toString(numberofqsns));
            }

            }.start();
        }
    public void chooseAnswer(View view){
        if(isGameactive) {
            if (view.getTag().toString().equals(Integer.toString(locationofcorrectAns))) {
                resulttextview.setText("Correct!");
                score++;
            } else {
                resulttextview.setText("Wrong!");
            }
            numberofqsns++;
            pointstextview.setText(score + "/" + numberofqsns);
            genrateQsn();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoButton=(Button)findViewById(R.id.GoButton);
        button0=(Button)findViewById(R.id.button0);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button3=(Button)findViewById(R.id.button3);
        Sumtextview=(TextView)findViewById(R.id.Sumtextview);
        pointstextview=(TextView)findViewById(R.id.pointstextView);
        Timertextview=(TextView)findViewById(R.id.TimertextView);
        resulttextview=(TextView)findViewById(R.id.resulttextView);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);
        playaginbutton=(Button)findViewById(R.id.playagainbutton);
    }
}
