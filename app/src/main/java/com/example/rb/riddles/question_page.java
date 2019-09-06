package com.example.rb.riddles;

import android.app.ActionBar;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.content.DialogInterface.*;

public class question_page extends AppCompatActivity implements RewardedVideoAdListener {
    private EditText ans;
    DatabaseReference databasequestion;
    DatabaseReference databasequestion2;
    int n;
    String qu="Dummy text";
    AlertDialog  alertDialog;
    String hi;
    model m;
  String answerr;
    String an;
    private boolean answered;
TextView question;
   int le;
   String position;
    ProgressDialog Dialog;
Button submit;
private RewardedVideoAd mad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_page);
        getSupportActionBar().hide();
        //add
        mad= MobileAds.getRewardedVideoAdInstance(this);
        mad.setRewardedVideoAdListener(this);
        loadRewardedVideoAd();




        TextView level = findViewById(R.id.level);
         question = findViewById(R.id.question);
        ans = findViewById(R.id.answer);
        Button hint = findViewById(R.id.hint);
         submit = findViewById(R.id.submit);

        position = getIntent().getStringExtra("positio");
        //int lock= getIntent().getIntExtra("lock",1);
        hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mad.isLoaded())
                {
                    Dialog = new ProgressDialog(question_page.this);
                    Dialog.setMessage("Loading Add...");
                    Dialog.setCanceledOnTouchOutside(false);
                    Dialog.show();

                    final Handler handler  = new Handler();
                    final Runnable runnable = new Runnable() {
                        @Override
                        public void run() {
                            if (Dialog.isShowing()) {
                                Dialog.dismiss();

                                if(mad.isLoaded())
                                {
                                    mad.show();
                                }
                            }
                        }
                    };
                    handler.postDelayed(runnable, 2500);
                }
                else
                {
                    if(mad.isLoaded())
                    {
                        mad.show();
                    }
                }

            }
        });

       //Toast.makeText(this,lock,Toast.LENGTH_LONG).show();
         le=Integer.parseInt(position);
         int re=le+1;
        level.setText("LEVEL " + position);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkanswer();

            }
        });
        databasequestion2= FirebaseDatabase.getInstance().getReference("questions").child(String.valueOf(re)).child("locked");

    }
    @Override
    protected void onStart() {
        super.onStart();
        databasequestion= FirebaseDatabase.getInstance().getReference("questions");
        databasequestion.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              for(DataSnapshot artistsnapshot:dataSnapshot.getChildren())
              {
                   m=artistsnapshot.getValue(model.class);
                  int l=m.getLevelno();

                  if(l==le)
                   {  qu=m.getQuestion();
                   hi=m.hint;
                   an=m.getAnswer().toString().trim();
                     question.setText(qu);

                  }
              }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
    public void checkanswer()
    {Toast.makeText(this,an, Toast.LENGTH_SHORT).show();
        answerr=ans.getText().toString();
        if(answerr.equals(""))
        {
            Toast.makeText(this,"Please enter answer",Toast.LENGTH_LONG).show();
        }
        else {
            if (an.equals(answerr)) {

                databasequestion2.setValue(0);

                Intent intent = new Intent(question_page.this, Right.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                ans.setText("");
            } else {

                Intent intent = new Intent(question_page.this, Wrong.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                ans.setText("");
            }
        }


    }
    private void loadRewardedVideoAd() {
        if(!mad.isLoaded()) {
            mad.loadAd("ca-app-pub-3940256099942544/5224354917",
                    new AdRequest.Builder().build());
        }
    }

    @Override
    public void onRewardedVideoAdLoaded() {

    }

    @Override
    public void onRewardedVideoAdOpened() {

    }

    @Override
    public void onRewardedVideoStarted() {

    }
    @Override
    public void onResume() {
        mad.resume(this);
        super.onResume();
    }

    @Override
    public void onPause() {
        mad.pause(this);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mad.destroy(this);
        super.onDestroy();
    }

    @Override
    public void onRewardedVideoAdClosed() {
        loadRewardedVideoAd();
       alertDialog = new AlertDialog.Builder(
                question_page.this).create();
        alertDialog.setTitle("Hint");
        alertDialog.setMessage(hi);



        // Showing Alert Message
        alertDialog.show();








        //Intent intent=new Intent(question_page.this,Hint.class);
        //startActivity(intent);
    }

    @Override
    public void onRewarded(RewardItem rewardItem) {

    }

    @Override
    public void onRewardedVideoAdLeftApplication() {

    }

    @Override
    public void onRewardedVideoAdFailedToLoad(int i) {

    }
}
