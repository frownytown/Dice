package com.example.yarr.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 Created by Roger McClain
 */

public class MainActivity extends AppCompatActivity {
    public final String ERROR_STRING = "Enter a valid number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // create ArrayList to store rolls
    ArrayList<Integer> rolls = new ArrayList<Integer>();

    // Called when the user taps the Roll button
    public void sendMessage(View view)
    {
        // get tag to decide what to roll
        String button_tag = view.getTag().toString();
        if (button_tag.equals("1"))
        {
            EditText editText = (EditText) findViewById(R.id.editText);
            String message_initial = editText.getText().toString();
            verifyFunc(message_initial);
        }
        else
        {
            verifyFunc(button_tag);
        }
        historyWindow(rolls);
        keyboardHide();

    }

    // called when the user taps the Advanced button
    public void advancedTab(View view)
    {
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        try
        {
            intent.putIntegerArrayListExtra("History", rolls);
            startActivityForResult(intent, 1);
        }
        catch (RuntimeException e)
        {
            System.err.println("RuntimeException: " + e.getMessage());
        }
    }
    // receive data from other activity
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1)
        {
            if (resultCode == RESULT_OK)
            {
                ArrayList<Integer> history_list = getIntent().getIntegerArrayListExtra("data");
                historyWindow(history_list);
            }
        }
    }



    // validation and some rolling logic
    public void verifyFunc(String message_initial)
    {
        if (!message_initial.isEmpty())
        {
            if (Dice.tryParseInt(message_initial))
            {
                int numberEntered = Integer.parseInt(message_initial);
                if (numberEntered > 0)
                {
                    Integer roll = Dice.diceRoll(numberEntered);
                    String message = Integer.toString(roll);
                    if (rolls.size() > 10)
                    {
                        rolls.remove(0);
                        System.out.println("Index 0 removed");
                    }
                    if (rolls.add(roll))
                    {
                        System.err.println("Value Added" + roll);
                        for (int x : rolls)
                        {
                            System.out.println(x);
                        }
                        System.out.println(rolls.size());
                    }
                    TextView textView = findViewById(R.id.textView3);
                    textView.setText(message);
                }
            }
            else
            {
                TextView textView = findViewById(R.id.textView3);
                textView.setText(ERROR_STRING);
            }
        }
        else if (message_initial.isEmpty())
        {
            TextView textView = findViewById(R.id.textView3);
            textView.setText(ERROR_STRING);
        }
    }

    public void historyWindow(ArrayList<Integer> historyList)
    {
        ArrayList<Integer> wholeHistory = new ArrayList<Integer>();
        wholeHistory = historyList;
        try
        {
            if (wholeHistory.size() > 0)
            {
                try
                {
                    StringBuilder sb = new StringBuilder();
                    for (Integer s : wholeHistory)
                    {

                        String history_string = Integer.toString(s);
                        sb.append(history_string);
                        sb.append("  ");
                    }
                    String history = sb.toString();
                    TextView textView = findViewById(R.id.textView4);
                    System.out.println(history);
                    textView.setText(history);
                }
                catch (IndexOutOfBoundsException e)
                {
                    System.err.println("IndexOutOfBoundsException: " + e.getMessage());
                }
            }
        }
        catch (NullPointerException e)
        {
            System.err.println("NullPointerException: " + e.getMessage());
        }

    }

    // function for hiding keyboard after "roll" pressed
    public void keyboardHide()
    {
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        catch (NullPointerException e){
            System.err.println("NullPointerException" + e.getMessage());
        }

    }

}

