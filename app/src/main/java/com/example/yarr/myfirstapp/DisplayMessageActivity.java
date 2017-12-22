package com.example.yarr.myfirstapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        try
        {
            historyDisplayFunc();
        }
        catch (RuntimeException e)
        {
            System.err.println("RuntimeException: " + e.getMessage());
        }
    }

    public ArrayList<Integer> stringToArray(String stringToConv)
    {
        ArrayList<Integer> arrList = new ArrayList<Integer>();
        String[] array = stringToConv.split(" ");
        for (String s : array)
        {
            arrList.add(Integer.parseInt(s));
        }
        return arrList;
    }

    // code for the reroll button
    public void rerollButton(View view)
    {
        try
        {
            EditText editText = (EditText) findViewById(R.id.editText2);
            String message_initial = editText.getText().toString();
            int numberEntered = Integer.parseInt(message_initial);
            int roll = Dice.diceRoll(numberEntered);
            String message = Integer.toString(roll);
            TextView textView = findViewById(R.id.textView6);
            textView.setText(message);
            System.out.println(message);
        }
        catch (RuntimeException e)
        {
            System.err.println("RuntimeException" + e.getMessage());
        }
    }

    // function used to display the history text box
    // also gets the ArrayList that contains the data via the intent from the main activity
    public void historyDisplayFunc()
    {
        // get the Intent that started this activity
        try
        {
            ArrayList<Integer> history_list = getIntent().getIntegerArrayListExtra("History");
            try
            {
                StringBuilder his = new StringBuilder();
                for (Integer x : history_list)
                {
                    String history_list_string = Integer.toString(x);
                    his.append(history_list_string);
                    his.append(" ");
                }
                String history = his.toString();

                // capture the layout's TextView and set the string as it's text
                TextView textView = findViewById(R.id.textView);
                textView.setText(history);
            }
            catch (IndexOutOfBoundsException e)
            {
                System.err.println("IndexOutOfBoundsException: " + e.getMessage());
            }
        }
        catch (RuntimeException e)
        {
            System.err.println("RuntimeException: " + e.getMessage());
        }
    }

    @Override
    public void onBackPressed()
    {
        TextView history_window = findViewById(R.id.textView);
        String history = history_window.getText().toString();
        if (history.equals("") || history.equals(" "))
        {
            finish();
        }
        else
        {
            ArrayList<Integer> data = stringToArray(history);
            Intent i = new Intent();
            i.putIntegerArrayListExtra("Whole History", data);
            setResult(RESULT_OK, i);
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }

        return(super.onOptionsItemSelected(item));
    }

}
