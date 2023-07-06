package com.samarth.brainteaser;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    Button help;
    int score =0;
    int noOfQuestions=0;
    TextView scoreTextView;
    TextView sumTextView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void chooseAnswer(View view){
        if(Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }
        else{
            resultTextView.setText("Wrong :(");
        }
        noOfQuestions++;
        scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
        newQuestion();
    }
    public void newQuestion(){

        Random rand = new Random();
        int a= rand.nextInt(21);
        int b=rand.nextInt(21);
        sumTextView.setText(Integer.toString(a)+" + "+Integer.toString(b));
        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();
        for (int i = 0; i < 4; i++) {
            if(i==locationOfCorrectAnswer)
                answers.add(a+b);
            else{
                int wrongAnswer = rand.nextInt(41);
                while(wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }
public void playAgain(View view){
score=0;
noOfQuestions=0;
timerTextView.setText("30s");
scoreTextView.setText(Integer.toString(score)+"/"+Integer.toString(noOfQuestions));
newQuestion();
playAgainButton.setVisibility(View.INVISIBLE);
resultTextView.setText("");

    new CountDownTimer(30100,1000){

        @Override
        public void onTick(long l) {
            timerTextView.setText(String.valueOf(l/1000)+"s");
        }

        @Override
        public void onFinish() {
            resultTextView.setText("Done!");
            playAgainButton.setVisibility(View.VISIBLE);
        }
    }.start();

}
    public void start(View view){
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        help = findViewById(R.id.Help);
        help.setOnClickListener(View ->{
            showAlertDialog("Give the correct answers until times runs out and maximize your score");
        });
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        sumTextView = findViewById(R.id.sumTextView);
        playAgainButton = findViewById(R.id.playAgainButton);
        goButton=findViewById(R.id.goButton);
        gameLayout=findViewById(R.id.gameLayout);

        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);

    }

    private void showAlertDialog(String s) {
        AlertDialog dialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("How to play")
                .setMessage(s)
                .setPositiveButton("Play", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();
    }
}