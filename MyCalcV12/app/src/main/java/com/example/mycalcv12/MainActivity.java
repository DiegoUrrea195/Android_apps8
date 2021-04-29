package com.example.mycalcv12;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private String operation = "Suma";
    private EditText label1;
    private EditText label2;
    private TextView response_label;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1 = findViewById(R.id.input_one);
        label2 = findViewById(R.id.input_two);
        response_label = findViewById(R.id.lb_response);

        Spinner spinner = (Spinner) findViewById(R.id.options);
        spinner.setOnItemSelectedListener(this);

        List<String> operations = new ArrayList<String>();
        operations.add("Suma");
        operations.add("Resta");
        operations.add("Multiplicación");
        operations.add("Division");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, operations);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

    }

    public void start(View view) {

        if(!inputsAreValid(this.label1, this.label2)) {
            Toast.makeText(getApplicationContext(),"Entradas no validas", Toast.LENGTH_LONG).show();
            return;
        }

        int n1 = Integer.parseInt(this.label1.getText().toString());
        int n2 = Integer.parseInt(this.label2.getText().toString());

        int result = 0;



        switch(this.operation) {
            case "Suma":
                result = sum(n1, n2);
                break;
            case "Resta":
                result = res(n1, n2);
                break;
            case "Multiplicación":
                result = mul(n1, n2);
                break;
            case "Division":
                try {
                    result =  div(n1, n2);
                }catch (Exception e) {
                    response_label.setText("Error");
                    Toast.makeText(getApplicationContext(),"Error "+ e.getMessage(), Toast.LENGTH_LONG).show();
                    return;
                }
        }

        response_label.setText(String.valueOf(result));


    }

    private int sum(int x, int y) {
        return  x + y;
    }

    private int res(int x, int y) {
        return  x - y;
    }

    private int div(int x, int y) {
        return  x / y;
    }

    private int mul(int x, int y) {
        return  x * y;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        this.operation = parent.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private boolean inputsAreValid(EditText lb1, EditText lb2) {

        boolean flag = true;

        String label1 = lb1.getText().toString();
        String label2 = lb2.getText().toString();

        if("".equals(label1) || "".equals(label2)) {
            flag = false;
        }

        return  flag;
    }
}