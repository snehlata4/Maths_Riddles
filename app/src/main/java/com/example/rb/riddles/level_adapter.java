package com.example.rb.riddles;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorSpace;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class level_adapter extends RecyclerView.Adapter<level_adapter.programmingviewholder> {
    private String[] data;
    DatabaseReference databasequestion;
    int lock=1;
     int x;
     String ana;
    int l=8;
   model m;
   String q1;
   private ArrayList<model> mModels;
    public level_adapter(Context context, String[] data,ArrayList<model> mModels)
    {
        this.data=data;
        this.mModels=mModels;


    }

    @Override
    public programmingviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflator=LayoutInflater.from(parent.getContext());
        View view=inflator.inflate(R.layout.item_level,parent,false);
        return new programmingviewholder(view);

    }

    @Override
    public void onBindViewHolder(final programmingviewholder holder, final int position) {
     final String val=data[position];


        holder.b.setText(val);

     holder.but.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
  int lock= mModels.get(position).locked;
             if(lock==1)
            {
                Toast.makeText(v.getContext(),"locked",Toast.LENGTH_SHORT).show();
            }
           else
            {

             Intent intent=new Intent(v.getContext(), question_page.class);
             intent.putExtra("positio", val);

                intent.putExtra("lock", x);
             v.getContext().startActivity(intent);
         }}
     });

    }

    @Override
    public int getItemCount() {
        return data.length;
    }

    public class programmingviewholder extends RecyclerView.ViewHolder {
    TextView b;
    RelativeLayout lev;
    Button but;

        public programmingviewholder(View itemView) {
            super(itemView);
        b=itemView.findViewById(R.id.b);

            but=itemView.findViewById(R.id.b);
        }


    }
    public void newData(ArrayList<model> models){
       mModels=models;
       notifyDataSetChanged();
    }





}
