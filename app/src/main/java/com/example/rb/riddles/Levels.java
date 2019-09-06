package com.example.rb.riddles;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Levels extends AppCompatActivity {
    private level_adapter mAdapter;
    ArrayList<model> list;
    RecyclerView rv;
    ProgressDialog Dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        list=new ArrayList<model>();
        setContentView(R.layout.activity_levels);
        final String[] data={"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32", "33", "34", "35", "36", "37", "38", "39", "40", "41", "42", "43", "44", "45", "46", "47", "48","49",
                "50","51","52","53","54","55","56","57","58","59","60","61","62","63","64","65","66","67","68","69","70","71"};

        setContentView(R.layout.activity_levels);
 rv=findViewById(R.id.rv);

        rv.setLayoutManager(new GridLayoutManager(this, 5));
        mAdapter=new level_adapter(this,data,list);
        rv.setAdapter(mAdapter);
         Dialog = new ProgressDialog(Levels.this);
        Dialog.setMessage("Doing something...");
        Dialog.show();
        DatabaseReference databasequestion= FirebaseDatabase.getInstance().getReference("questions");
        databasequestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(mAdapter!=null){
                    mAdapter.newData(list);
                }else{
                    mAdapter=new level_adapter(Levels.this,data,list);
                    rv.setAdapter(mAdapter);
                }


                for(DataSnapshot artistsnapshot:dataSnapshot.getChildren())
                {
                   model m=artistsnapshot.getValue(model.class);
                   list.add(m);


                }
                Dialog.hide();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
