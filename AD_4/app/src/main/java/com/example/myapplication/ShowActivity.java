package com.example.myapplication;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class ShowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        TextView name=findViewById(R.id.textView1);
        TextView gend=findViewById(R.id.textView3);
        TextView sub=findViewById(R.id.textView2);
        TextView qualification=findViewById(R.id.textView4);
        Bundle extras=getIntent().getExtras();

        if(extras!=null){

            name.setText(extras.getString("name"));
            sub.setText(extras.getString("sub"));
            gend.setText(extras.getString("gend"));
            qualification.setText(extras.getString("qualification"));
        }
    }
}