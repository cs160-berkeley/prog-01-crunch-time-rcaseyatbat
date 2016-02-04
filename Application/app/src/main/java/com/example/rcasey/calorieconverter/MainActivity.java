package com.example.rcasey.calorieconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.EditText;

import org.w3c.dom.Text;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity implements TextWatcher, AdapterView.OnItemSelectedListener {

    Spinner inputSpinner;
    Spinner outputSpinner;
    EditText rep_counter;
    TextView calories_text;
    TextView equivalent_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputSpinner = (Spinner) findViewById(R.id.input_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.exercise_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inputSpinner.setAdapter(adapter);
        inputSpinner.setOnItemSelectedListener(this);
        inputSpinner.setSelection(6);

        outputSpinner = (Spinner) findViewById(R.id.output_spinner);
        ArrayAdapter adapterOut = ArrayAdapter.createFromResource(this, R.array.exercise_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterOut.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        outputSpinner.setAdapter(adapterOut);
        outputSpinner.setOnItemSelectedListener(this);
        outputSpinner.setSelection(2);

        rep_counter = (EditText) findViewById(R.id.rep_count);
        rep_counter.addTextChangedListener(this);

        calories_text = (TextView) findViewById(R.id.calories_burned);
        equivalent_text = (TextView) findViewById(R.id.equivalent_text);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        Spinner spinner = (Spinner) parent;

        if(spinner.getId() == R.id.input_spinner)
        {
            TextView spinnerText = (TextView) view;

            // set input_type here:
            String type = spinnerText.getText().toString();
            TextView inputTextType = (TextView) findViewById(R.id.input_type);
            if (type.equals("Push-ups")) {
                inputTextType.setText("Reps");
            } else if (type.equals("Sit-ups")) {
                inputTextType.setText("Reps");
            } else if (type.equals("Squats")) {
                inputTextType.setText("Reps");
            } else if (type.equals("Pull-ups")) {
                inputTextType.setText("Reps");
            } else {
                inputTextType.setText(("Minutes"));
            }
        }

       calculateCalories();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        // Recalculate if the user changed the reps/minutes
        calculateCalories();
    }

    public void calculateCalories() {
        try {
            double reps = Double.parseDouble(rep_counter.getText().toString());
            // use exercise type here
            String type = inputSpinner.getSelectedItem().toString();
            double calories = 0.0;
            if (type.equals("Push-ups")) {
                calories = reps * 100.0 / 350.0;
            } else if (type.equals("Sit-ups")) {
                calories = reps * 100.0 / 200.0;
            } else if (type.equals("Jumping Jacks")) {
                calories = reps * 100.0 / 10.0;
            } else if (type.equals("Jogging")) {
                calories = reps * 100.0 / 12.0;
            } else if (type.equals("Squats")) {
                calories = reps * 100.0 / 225.0;
            } else if (type.equals("Leg-lifts")) {
                calories = reps * 100.0 / 25.0;
            } else if (type.equals("Planking")) {
                calories = reps * 100.0 / 25.0;
            } else if (type.equals("Pull-ups")) {
                calories = reps * 100.0 / 100.0;
            } else if (type.equals("Cycling")) {
                calories = reps * 100.0 / 12.0;
            } else if (type.equals("Walking")) {
                calories = reps * 100.0 / 20.0;
            } else if (type.equals("Swimming")) {
                calories = reps * 100.0 / 13.0;
            } else if (type.equals("Stair-climbing")) {
                calories = reps * 100.0 / 15.0;
            }
            String roundedCalories = String.format("%.2f", calories);
            calories_text.setText("You burned " + roundedCalories + " calories! ");

            // Now, do the conversion
            String output_type = outputSpinner.getSelectedItem().toString();
            double equivalentReps = 0.0;
            String ending = "";
            if (output_type.equals("Push-ups")) {
                equivalentReps = calories * 350.0/100.0;
                ending = "reps of Push-ups!";
            } else if (output_type.equals("Sit-ups")) {
                equivalentReps = calories * 200.0/100.0;
                ending = "reps of Sit-ups!";
            } else if (output_type.equals("Jumping Jacks")) {
                equivalentReps = calories * 10.0/100.0;
                ending = "minutes of Jumping Jacks!";
            } else if (output_type.equals("Jogging")) {
                equivalentReps = calories * 12.0/100.0;
                ending = "minutes of Jogging!";
            } else if (output_type.equals("Squats")) {
                equivalentReps = calories * 225.0/100.0;
                ending = "reps of Squats!";
            } else if (output_type.equals("Leg-lifts")) {
                equivalentReps = calories * 25.0/100.0;
                ending = "minutes of Leg-lifts!";
            } else if (output_type.equals("Planking")) {
                equivalentReps = calories * 25.0/100.0;
                ending = "minutes of Planking!";
            } else if (output_type.equals("Pull-ups")) {
                equivalentReps = calories * 100.0/100.0;
                ending = "reps of Pull-ups!";
            } else if (output_type.equals("Cycling")) {
                equivalentReps = calories * 12.0/100.0;
                ending = "minutes of Cycling!";
            } else if (output_type.equals("Walking")) {
                equivalentReps = calories * 20.0 / 100.0;
                ending = "minutes of Walking!";
            } else if (output_type.equals("Swimming")) {
                equivalentReps = calories * 13.0/100.0;
                ending = "minutes of Swimming!";
            } else if (output_type.equals("Stair-climbing")) {
                equivalentReps = calories * 15.0/100.0;
                ending = "minutes of Stair-climbing!";
            }
            String roundedReps = String.format("%.2f", equivalentReps);
            equivalent_text.setText("Equivalent to " + roundedReps + " " + ending);
        }
        catch (NumberFormatException e) {
            calories_text.setText("You burned " + 0 + " calories! ");

            // Now, set the equivalent to be 0
            String output_type = outputSpinner.getSelectedItem().toString();
            String ending = "";
            if (output_type.equals("Push-ups")) {
                ending = "reps of Push-ups!";
            } else if (output_type.equals("Sit-ups")) {
                ending = "reps of Sit-ups!";
            } else if (output_type.equals("Jumping Jacks")) {
                ending = "minutes of Jumping Jacks!";
            } else if (output_type.equals("Jogging")) {
                ending = "minutes of Jogging!";
            } else if (output_type.equals("Squats")) {
                ending = "reps of Squats!";
            } else if (output_type.equals("Leg-lifts")) {
                ending = "minutes of Leg-lifts!";
            } else if (output_type.equals("Planking")) {
                ending = "minutes of Planking!";
            } else if (output_type.equals("Pull-ups")) {
                ending = "reps of Pull-ups!";
            } else if (output_type.equals("Cycling")) {
                ending = "minutes of Cycling!";
            } else if (output_type.equals("Walking")) {
                ending = "minutes of Walking!";
            } else if (output_type.equals("Swimming")) {
                ending = "minutes of Swimming!";
            } else if (output_type.equals("Stair-climbing")) {
                ending = "minutes of Stair-climbing!";
            }
            equivalent_text.setText("Equivalent to " + 0 + " " + ending);
        }

        return;
    }

}
