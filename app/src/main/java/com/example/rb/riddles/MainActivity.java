package com.example.rb.riddles;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
   // DatabaseHelper myDb;
    EditText editName,editSurname,editMarks ,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnDelete;
    DatabaseReference databasequestion;


    Button btnviewUpdate;
    Button play;
    Button levels;
    Button share;
    Button exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


  databasequestion= FirebaseDatabase.getInstance().getReference("questions");
        play=findViewById(R.id.play);
        levels=findViewById(R.id.levels);
        share=findViewById(R.id.share);
        exit=findViewById(R.id.exit);
        getSupportActionBar().hide();
        if(!isNetworkAvailable())
        {   play.setEnabled(false);
            levels.setEnabled(false);
            share.setEnabled(false);
            exit.setEnabled(false);
            Toast.makeText(this,"Please connect to internet to play this game",Toast.LENGTH_LONG).show();
        }
        else {
            play.setEnabled(true);
            levels.setEnabled(true);
            share.setEnabled(true);
            exit.setEnabled(true);

            AddData();
        }






    }


    public  void AddData() {


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Levels.class);
                startActivity(intent);

            }
        });

        levels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Levels.class);
                startActivity(intent);
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
exit.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which){
                    case DialogInterface.BUTTON_POSITIVE:
                        finish();
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //No button clicked

                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();


    }
});
      //  Toast.makeText(this,"artist added",Toast.LENGTH_LONG).show();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!isNetworkAvailable()) {
            play.setEnabled(false);
            levels.setEnabled(false);
            share.setEnabled(false);
            exit.setEnabled(false);
            Toast.makeText(this, "Please connect to internet to play this game", Toast.LENGTH_LONG).show();
        } else {
            play.setEnabled(true);
            levels.setEnabled(true);
            share.setEnabled(true);
            exit.setEnabled(true);

            AddData();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        if(!isNetworkAvailable())
        {   play.setEnabled(false);
            levels.setEnabled(false);
            share.setEnabled(false);
            exit.setEnabled(false);
            Toast.makeText(this,"Please connect to internet to play this game",Toast.LENGTH_LONG).show();
        }
        else {
            play.setEnabled(true);
            levels.setEnabled(true);
            share.setEnabled(true);
            exit.setEnabled(true);

            AddData();
        }
    }
}
