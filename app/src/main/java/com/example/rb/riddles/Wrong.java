package com.example.rb.riddles;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Wrong extends AppCompatActivity {
  Button c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong);
        c=findViewById(R.id.button1);
        getSupportActionBar().hide();
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Wrong.this,Levels.class);
                startActivity(intent);
            }
        });
    }
}
