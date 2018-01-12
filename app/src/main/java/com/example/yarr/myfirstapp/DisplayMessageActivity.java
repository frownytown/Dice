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
            Dice die = new Dice();
            // get value from sides text box
            EditText editText = (EditText) findViewById(R.id.editText2);
            String message_initial = editText.getText().toString();
            if (Dice.tryParseInt(message_initial))
            {
                int numberEntered = Integer.parseInt(message_initial);
                if (numberEntered > 0)
                {
                    // try to get value from modifier box
                    EditText editText1 = (EditText) findViewById(R.id.editText3);
                    String modifier_initial = editText1.getText().toString();
                    if (Dice.tryParseInt(modifier_initial))
                    {
                        int modifierParsed = Integer.parseInt(modifier_initial);
                        if (modifierParsed > 0)
                        {
                            // roll die and add modifier
                            int dieValue = Dice.diceRoll(numberEntered);
                            int dieModified = Dice.addModify(dieValue, modifierParsed);
                            String output = Integer.toString(dieModified);
                            // out put rolled value in result box
                            displayRoll(die);
                        }
                    }
                    else
                    {
                        // roll as though there is no modifier
                        int roll = Dice.diceRoll(numberEntered);
                        // out put rolled value in result box
                        displayRoll(die);
                    }
                }
                else
                {
                    // some error message
                }
            }
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

    // function for displaying total to textView6
    // other functions create dice object and set total, this function send to screen
    private void displayRoll(Dice die)
    {
        // out put rolled value in result box
        String message = Integer.toString(die.getDiceRollValue());
        TextView textView = findViewById(R.id.textView6);
        textView.setText(message);
        System.out.println(message);
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
