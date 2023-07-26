package com.example.calculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;


public class MainActivity extends AppCompatActivity
{
    Button button_1,button_2,button_3,button_4,button_5,button_6,button_7,button_8,button_9,button_0;
    Button button_c,button_ac,button_left_brac,button_right_brac,button_multiply,button_dot,button_division,button_plus,button_minus,button_equal;

    TextView textViewInput,textViewAnswer;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInput = findViewById(R.id.textViewInput);
        textViewAnswer = findViewById(R.id.textViewAnswer);


        assignID(button_1, R.id.Button_1);
        assignID(button_2, R.id.Button_2);
        assignID(button_3, R.id.Button_3);
        assignID(button_4, R.id.Button_4);
        assignID(button_5, R.id.Button_5);
        assignID(button_6, R.id.Button_6);
        assignID(button_7, R.id.Button_7);
        assignID(button_8, R.id.Button_8);
        assignID(button_9, R.id.Button_9);
        assignID(button_0, R.id.Button_0);
        assignID(button_ac,R.id.Button_AC);
        assignID(button_c,R.id.Button_C);
        assignID(button_dot,R.id.Button_dot);
        assignID(button_plus,R.id.Button_plus);
        assignID(button_equal,R.id.Button_equal);
        assignID(button_division,R.id.Button_division);
        assignID(button_minus,R.id.Button_minus);
        assignID(button_multiply,R.id.Button_multiply);
        assignID(button_left_brac,R.id.Button_left_brac);
        assignID(button_right_brac,R.id.Button_right_brac);

    }

    void assignID(Button button, int id) {
        button = (Button) findViewById(id);
    }

    public void onClick(View v) {
        Button button = (Button) v;
        String buttonText = button.getText().toString();
        String calutatingText = textViewInput.getText().toString();

        if (buttonText.equals("AC")){
            textViewAnswer.setText("0");
            textViewInput.setText("");
            return;
        }

        else  if (buttonText.equals("=")){
                textViewInput.setText(textViewAnswer.getText());
                return;
        }
        else if (buttonText.equals("C")) {
            calutatingText = calutatingText.substring(0,calutatingText.length()-1);

        }
        else {
            calutatingText = calutatingText + buttonText;
        }
        textViewInput.setText(calutatingText);
        String finalResult = getResult(calutatingText);

        if (!finalResult.equals("Err")){

                textViewAnswer.setText(finalResult);
        }




    }
    String getResult(String data){
        try {
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if (finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Err";
        }

    }
}