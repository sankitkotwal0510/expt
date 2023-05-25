package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.content.Intent;
import android.widget.EditText;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name=findViewById(R.id.editTextTextPersonName);
        RadioGroup gender=findViewById(R.id.radioGroup);
        Spinner subject=findViewById(R.id.spinner);
        Button reg=findViewById(R.id.button);
        CheckBox ch1=findViewById(R.id.checkBox);
        CheckBox ch2=findViewById(R.id.checkBox2);
        Intent intent= new Intent(MainActivity.this,ShowActivity.class);

        ArrayList<String> sub=new ArrayList<>();
        sub.add("Android Development");
        sub.add("Fiber Optic Communication");

        ArrayAdapter<String> subada=new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,sub);

        subject.setAdapter(subada);

        subject.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String msg = "Subject : " + sub.get(i);
                intent.putExtra("sub",msg);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int selid=radioGroup.getCheckedRadioButtonId();
                RadioButton radioButton=findViewById(selid);
                String msg = "Gender : " + radioButton.getText().toString();
                intent.putExtra("gend",msg);
            }
        });



        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = "Qualification : ";
                if(ch1.isChecked())
                    msg = msg + " B.E. ";
                if(ch2.isChecked())
                    msg = msg + " M.E. ";
                String msg2 = "Name : " + name.getText().toString();
                intent.putExtra("name",msg2);
                intent.putExtra("qualification", msg);
                startActivity(intent);
            }
        });


    }
}
