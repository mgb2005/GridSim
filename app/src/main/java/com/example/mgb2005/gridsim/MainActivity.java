package com.example.mgb2005.gridsim;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final Toast t1 = Toast.makeText(getApplicationContext(), "Congradulations, You Pressed Button 1!", Toast.LENGTH_SHORT);
        final Toast t2 = Toast.makeText(getApplicationContext(), "Congradulations, You Pressed Button 2!", Toast.LENGTH_SHORT);

        final Button b1 = (Button) findViewById(R.id.button);
        b1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t1.show();
            }
        });

        final Button b2 = (Button) findViewById(R.id.button2);
        b2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                t2.show();
            }
        });
    }
}
