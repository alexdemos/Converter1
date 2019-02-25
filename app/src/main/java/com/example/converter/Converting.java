package com.example.converter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Converting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converting);

        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner3);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this,
                R.array.length_array, android.R.layout.simple_spinner_item);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this,
                R.array.weight_array, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final ArrayAdapter<CharSequence> volumeAdapter = ArrayAdapter.createFromResource(this,
                R.array.volume_array, android.R.layout.simple_spinner_item);
        volumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        final Spinner bigSpinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> bigAdapter = ArrayAdapter.createFromResource(this,
                R.array.measure_array, android.R.layout.simple_spinner_item);
        bigAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bigSpinner.setAdapter(bigAdapter);

        bigSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    String measurement = bigSpinner.getSelectedItem().toString();
                    if (measurement.equals("length")) {
                        spinner.setAdapter(lengthAdapter);
                        spinner2.setAdapter(lengthAdapter);
                    } else if (measurement.equals("weight")) {
                        spinner.setAdapter(weightAdapter);
                        spinner2.setAdapter(weightAdapter);
                    } else if (measurement.equals("volume")) {
                        spinner.setAdapter(volumeAdapter);
                        spinner2.setAdapter(volumeAdapter);
                    }
                }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void main(View view){
        EditText editText = (EditText) findViewById(R.id.editText);
        String numStr = editText.getText().toString();

        if(numStr.equals("")){
            TextView textView = (TextView) findViewById(R.id.textView3);
            textView.setText("Enter a number");

        }
        else {
            double num = Double.parseDouble(numStr);


            //gets values from both spinners
            Spinner spinner = findViewById(R.id.spinner);
            Spinner spinner2 = findViewById(R.id.spinner3);
            Spinner bigSpinner = findViewById(R.id.spinner2);

            String unit = spinner.getSelectedItem().toString();
            String convertUnit = spinner2.getSelectedItem().toString();
            String measure = bigSpinner.getSelectedItem().toString();

            String finalValue;

            //outputs the final values
            finalValue = convert(unit, convertUnit, num);

            setContentView(R.layout.activity_converting);
            TextView textView3 = (TextView) findViewById(R.id.textView3);
            textView3.setText(finalValue);

            DecimalFormat df = new DecimalFormat("0.######");
            String formatNum = df.format(num);

            //prevents user input from disappearing
            EditText editText1 = (EditText) findViewById(R.id.editText);
            editText1.setText(formatNum);

            ArrayAdapter<CharSequence> adap3 = ArrayAdapter.createFromResource(this,
                    R.array.measure_array, android.R.layout.simple_spinner_item);
            Spinner spinnerFinal2 = findViewById(R.id.spinner2);
            int arrayPos3 = adap3.getPosition(measure);
            spinnerFinal2.setAdapter(adap3);
            spinnerFinal2.setSelection(arrayPos3);

            final ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this,
                    R.array.length_array, android.R.layout.simple_spinner_item);
            lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            final ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this,
                    R.array.weight_array, android.R.layout.simple_spinner_item);
            weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            final ArrayAdapter<CharSequence> volumeAdapter = ArrayAdapter.createFromResource(this,
                    R.array.volume_array, android.R.layout.simple_spinner_item);
            volumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            //keeps both spinners on the same adapter after button press and on same item
            Spinner spin = findViewById(R.id.spinner);
            Spinner spin2 = findViewById(R.id.spinner3);
            if (measure.equals("length")) {
                spin.setAdapter(lengthAdapter);
                spin2.setAdapter(lengthAdapter);
                int arrayPos = lengthAdapter.getPosition(unit);
                spin.setSelection(arrayPos);

                int arrayPos2 = lengthAdapter.getPosition(convertUnit);
                spin2.setSelection(arrayPos2);

            } else if (measure.equals("weight")) {
                spin.setAdapter(weightAdapter);
                spin2.setAdapter(weightAdapter);

                int arrayPos = weightAdapter.getPosition(unit);
                spin.setSelection(arrayPos);

                int arrayPos2 = weightAdapter.getPosition(convertUnit);
                spin2.setSelection(arrayPos2);

            } else if (measure.equals("volume")) {
                spin.setAdapter(volumeAdapter);
                spin2.setAdapter(volumeAdapter);

                int arrayPos = volumeAdapter.getPosition(unit);
                spin.setSelection(arrayPos);

                int arrayPos2 = volumeAdapter.getPosition(convertUnit);
                spin2.setSelection(arrayPos2);
            }
        }
    }


    /*
    takes two units and a value and returns a statement in the from x unit1s is y unit2s
     */
    public static String convert(String unit1, String unit2, double measure){
        String finalValue;
        double convertedNum = 0;

        if(unit1.equals(unit2)){
            convertedNum = measure;
        }

        else if(unit1.equals("meters")){
            if(unit2.equals("inches")){
                convertedNum = measure * 39.3701;
            }
            else if(unit2.equals("feet")){
                convertedNum = measure * 3.2808;
            }
            else if(unit2.equals("yards")){
                convertedNum = measure * 1.09361;
            }
        }

        else if(unit1.equals("inches")){
            if(unit2.equals("meters")){
                convertedNum = measure / 39.3701;
            }
            else if(unit2.equals("feet")){
                convertedNum = measure * 12;
            }
            else if(unit2.equals("yards")){
                convertedNum = measure * 36;
            }
        }

        else if(unit1.equals("feet")){
            if(unit2.equals("meters")){
                convertedNum = measure / 3.2808;
            }
            else if(unit2.equals("inches")){
                convertedNum = measure / 12;
            }
            else if(unit2.equals("yards")){
                convertedNum = measure * 3;
            }
        }

        else if(unit1.equals("yards")){
            if(unit2.equals("meters")){
                convertedNum = measure / 1.09361;
            }
            else if(unit2.equals("inches")){
                convertedNum = measure / 36;
            }
            else if(unit2.equals("feet")){
                convertedNum = measure / 3;
            }
        }

        else if(unit1.equals("grams")){
            if(unit2.equals("ounces")){
                convertedNum = measure *.035274;
            }
            else if(unit2.equals("pounds")){
                convertedNum = measure * .00220462;
            }
            else if(unit2.equals("tons")){
                convertedNum = measure * .0000011023;
            }
        }

        else if(unit1.equals("ounces")){
            if(unit2.equals("grams")){
                convertedNum = measure / .035274;
            }
            else if(unit2.equals("pounds")){
                convertedNum = measure * .0625;
            }
            else if(unit2.equals("tons")){
                convertedNum = measure * .00003125;
            }
        }

        else if(unit1.equals("pounds")){
            if(unit2.equals("grams")){
                convertedNum = measure * 453.592;
            }
            else if(unit2.equals("ounces")){
                convertedNum = measure / .0625;
            }
            else if(unit2.equals("tons")){
                convertedNum = measure / 2000;
            }
        }

        else if(unit1.equals("tons")){
            if(unit2.equals("grams")){
                convertedNum = measure * 907185;
            }
            else if(unit2.equals("ounces")){
                convertedNum = measure * 32000;
            }
            else if(unit2.equals("pounds")){
                convertedNum = measure * 2000;
            }
        }

        else if(unit1.equals("liters")){
            if(unit2.equals("pints")){
                convertedNum = measure * 2.11338;
            }
            else if(unit2.equals("quarts")){
                convertedNum = measure * 1.05669;
            }
            else if(unit2.equals("gallons")){
                convertedNum = measure * .264172;
            }
        }

        else if(unit1.equals("pints")){
            if(unit2.equals("liters")){
                convertedNum = measure / 2.11338;
            }
            else if(unit2.equals("quarts")){
                convertedNum = measure * .5;
            }
            else if(unit2.equals("gallons")){
                convertedNum = measure * .125;
            }
        }

        else if(unit1.equals("quarts")){
            if(unit2.equals("liters")){
                convertedNum = measure * .946353;
            }
            else if(unit2.equals("pints")){
                convertedNum = measure / .5;
            }
            else if(unit2.equals("gallons")){
                convertedNum = measure * .25;
            }
        }

        else if(unit1.equals("gallons")){
            if(unit2.equals("liters")){
                convertedNum = measure * 3.78541;
            }
            else if(unit2.equals("pints")){
                convertedNum = measure * 8;
            }
            else if(unit2.equals("quarts")){
                convertedNum = measure / .25;
            }
        }

        DecimalFormat df = new DecimalFormat("0.######");
        String formatNum= df.format(measure);
        String formatConvertedNum = df.format(convertedNum);

        //outputs the final values
        finalValue = formatNum + " " + unit1 + "\nis "+ "\n" + formatConvertedNum + " " + unit2;

        return finalValue;
    }

    //fixes problem where the unit spinners would not change according to the big spinner after a button press
    public void confirm(View view){
        Spinner bigSpinner = findViewById(R.id.spinner2);
        String measure = bigSpinner.getSelectedItem().toString();

        final ArrayAdapter<CharSequence> lengthAdapter = ArrayAdapter.createFromResource(this,
                R.array.length_array, android.R.layout.simple_spinner_item);
        lengthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> weightAdapter = ArrayAdapter.createFromResource(this,
                R.array.weight_array, android.R.layout.simple_spinner_item);
        weightAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        final ArrayAdapter<CharSequence> volumeAdapter = ArrayAdapter.createFromResource(this,
                R.array.volume_array, android.R.layout.simple_spinner_item);
        volumeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spin =  findViewById(R.id.spinner);
        Spinner spin2 = findViewById(R.id.spinner3);
        if (measure.equals("length")) {
            spin.setAdapter(lengthAdapter);
            spin2.setAdapter(lengthAdapter);
        } else if (measure.equals("weight")) {
            spin.setAdapter(weightAdapter);
            spin2.setAdapter(weightAdapter);
        } else if (measure.equals("volume")) {
            spin.setAdapter(volumeAdapter);
            spin2.setAdapter(volumeAdapter);
        }



    }

}

