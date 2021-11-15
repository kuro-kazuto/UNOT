package com.untirta.unot;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.untirta.unot.Adapter.StructureAdapter;
//import com.labcnt.sttq.Direction.Reading_direction;
import com.untirta.unot.Model.StructureModel;
import com.untirta.unot.UserSide.MainActivity;

import java.util.ArrayList;


public class Structure extends AppCompatActivity {



//==================================================================================================
//============ INI ADALAH BAGIAN PROGRAM, JANGAN DIUBAH KECUALI OLEH DEVELOPER =====================
//================== DIBUAT OLEH : GALIH AJI PAMBUDI & DIMAS EMERALDO A ============================
//============= PROJECT KERJA PRAKTIK TAHUN 2021 JURUSAN TEKNIK ELEKTRO FT UNTIRTA =================
//==================================================================================================

    private RecyclerView mRecyclerView;
    private StructureAdapter mAdapter;

    private ConstraintLayout mParentLayout;
    private TextView mScoreTextViewL,mScoreTextViewS, tvTimer;
    private TextView mRemaningQuestionsTextView;
    private TextView tvNIM;
    private int mTotalQuestions;
    private int mScore;
    private StructureModel currentQuestion;


    Button btnNext;
    CountDownTimer countDownTimer;

    private ArrayList<StructureModel> questionsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_structure);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Structure");
        }



        btnNext = findViewById(R.id.btnNext);
        tvTimer = findViewById(R.id.Timer);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NIM = tvNIM.getText().toString();
                String scoreListening = mScoreTextViewL.getText().toString();
                String scoreStructure = mScoreTextViewS.getText().toString();
                Intent intent = new Intent(Structure.this, MainActivity.class);
                intent.putExtra("NIM", NIM);
                intent.putExtra("scoreListening", scoreListening);
                intent.putExtra("scoreStructure", scoreStructure);
                startActivity(intent);
                countDownTimer.cancel();
            }
        });

        addQuestion();


        tvNIM = findViewById(R.id.Nim);
        mScoreTextViewL = findViewById(R.id.scoreL);
        mScoreTextViewS = findViewById(R.id.scoreS);
        mParentLayout = findViewById(R.id.question_layout);
        mRemaningQuestionsTextView = findViewById(R.id.QuestionNumber);

        mRecyclerView = findViewById(R.id.recyclerView);
        mAdapter = new StructureAdapter(this,questionsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Structure.this);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mTotalQuestions = questionsList.size();
        mScore = 0;
        displayScore();

        tvNIM.setText(getIntent().getStringExtra("NIM"));
        mScoreTextViewL.setText(getIntent().getStringExtra("scoreListening"));

        timer();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        //Toast.makeText(Listening.this,"You Click Back Button !, Are You Want To Cheating ?",Toast.LENGTH_SHORT).show();
        return;
    }

    public void displayScore() {
        String scoreString = "Score Structure: " + mScore;
        mScoreTextViewS.setText(scoreString);
        mRemaningQuestionsTextView.setText("Remaining Questions: " + mTotalQuestions--);
    }

    private void timer() {
        int milidetik = 1000; //jangan diubah
        int detik = 60; //jangan diubah
        int menit = 25;
        countDownTimer = new CountDownTimer(menit * detik * milidetik, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int seconds = (int) (millisUntilFinished/1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tvTimer.setText(String.format("%02d", minutes) + ":" + String.format("%02d", seconds));
            }

            @Override
            public void onFinish() {
                String NIM = tvNIM.getText().toString();
                String scoreListening = mScoreTextViewL.getText().toString();
                String scoreStructure = mScoreTextViewS.getText().toString();
                Intent intent = new Intent(Structure.this, UnderConstruction.class);
                intent.putExtra("NIM", NIM);
                intent.putExtra("scoreListening", scoreListening);
                intent.putExtra("scoreStructure", scoreStructure);
                startActivity(intent);
            }
        }.start();
    }

    public void updateScore() {
        mScore++;
    }

    //--------------------------BANK SOAL LISTENING SECTION (ISI SESUAI ARRAY)--------------------------
    private void addQuestion(){
        String a1 = getString(R.string.SA1); String b1 = getString(R.string.SB1); String c1 = getString(R.string.SC1); String d1 = getString(R.string.SD1); String ans1 = getString(R.string.SAns1);
        String a2 = getString(R.string.SA2); String b2 = getString(R.string.SB2); String c2 = getString(R.string.SC2); String d2 = getString(R.string.SD2); String ans2 = getString(R.string.SAns2);
        String a3 = getString(R.string.SA3); String b3 = getString(R.string.SB3); String c3 = getString(R.string.SC3); String d3 = getString(R.string.SD3); String ans3 = getString(R.string.SAns3);
        String a4 = getString(R.string.SA4); String b4 = getString(R.string.SB4); String c4 = getString(R.string.SC4); String d4 = getString(R.string.SD4); String ans4 = getString(R.string.SAns4);
        String a5 = getString(R.string.SA5); String b5 = getString(R.string.SB5); String c5 = getString(R.string.SC5); String d5 = getString(R.string.SD5); String ans5 = getString(R.string.SAns5);
        String a6 = getString(R.string.SA6); String b6 = getString(R.string.SB6); String c6 = getString(R.string.SC6); String d6 = getString(R.string.SD6); String ans6 = getString(R.string.SAns6);
        String a7 = getString(R.string.SA7); String b7 = getString(R.string.SB7); String c7 = getString(R.string.SC7); String d7 = getString(R.string.SD7); String ans7 = getString(R.string.SAns7);
        String a8 = getString(R.string.SA8); String b8 = getString(R.string.SB8); String c8 = getString(R.string.SC8); String d8 = getString(R.string.SD8); String ans8 = getString(R.string.SAns8);
        String a9 = getString(R.string.SA9); String b9 = getString(R.string.SB9); String c9 = getString(R.string.SC9); String d9 = getString(R.string.SD9); String ans9 = getString(R.string.SAns9);
        String a10 = getString(R.string.SA10); String b10 = getString(R.string.SB10); String c10 = getString(R.string.SC10); String d10 = getString(R.string.SD10); String ans10 = getString(R.string.SAns10);
        String a11 = getString(R.string.SA11); String b11 = getString(R.string.SB11); String c11 = getString(R.string.SC11); String d11 = getString(R.string.SD11); String ans11 = getString(R.string.SAns11);
        String a12 = getString(R.string.SA12); String b12 = getString(R.string.SB12); String c12 = getString(R.string.SC12); String d12 = getString(R.string.SD12); String ans12 = getString(R.string.SAns12);
        String a13 = getString(R.string.SA13); String b13 = getString(R.string.SB13); String c13 = getString(R.string.SC13); String d13 = getString(R.string.SD13); String ans13 = getString(R.string.SAns13);
        String a14 = getString(R.string.SA14); String b14 = getString(R.string.SB14); String c14 = getString(R.string.SC14); String d14 = getString(R.string.SD14); String ans14 = getString(R.string.SAns14);
        String a15 = getString(R.string.SA15); String b15 = getString(R.string.SB15); String c15 = getString(R.string.SC15); String d15 = getString(R.string.SD15); String ans15 = getString(R.string.SAns15);
        String a16 = getString(R.string.SA16); String b16 = getString(R.string.SB16); String c16 = getString(R.string.SC16); String d16 = getString(R.string.SD16); String ans16 = getString(R.string.SAns16);
        String a17 = getString(R.string.SA17); String b17 = getString(R.string.SB17); String c17 = getString(R.string.SC17); String d17 = getString(R.string.SD17); String ans17 = getString(R.string.SAns17);
        String a18 = getString(R.string.SA18); String b18 = getString(R.string.SB18); String c18 = getString(R.string.SC18); String d18 = getString(R.string.SD18); String ans18 = getString(R.string.SAns18);
        String a19 = getString(R.string.SA19); String b19 = getString(R.string.SB19); String c19 = getString(R.string.SC19); String d19 = getString(R.string.SD19); String ans19 = getString(R.string.SAns19);
        String a20 = getString(R.string.SA20); String b20 = getString(R.string.SB20); String c20 = getString(R.string.SC20); String d20 = getString(R.string.SD20); String ans20 = getString(R.string.SAns20);
        String a21 = getString(R.string.SA21); String b21 = getString(R.string.SB21); String c21 = getString(R.string.SC21); String d21 = getString(R.string.SD21); String ans21 = getString(R.string.SAns21);
        String a22 = getString(R.string.SA22); String b22 = getString(R.string.SB22); String c22 = getString(R.string.SC22); String d22 = getString(R.string.SD22); String ans22 = getString(R.string.SAns22);
        String a23 = getString(R.string.SA23); String b23 = getString(R.string.SB23); String c23 = getString(R.string.SC23); String d23 = getString(R.string.SD23); String ans23 = getString(R.string.SAns23);
        String a24 = getString(R.string.SA24); String b24 = getString(R.string.SB24); String c24 = getString(R.string.SC24); String d24 = getString(R.string.SD24); String ans24 = getString(R.string.SAns24);
        String a25 = getString(R.string.SA25); String b25 = getString(R.string.SB25); String c25 = getString(R.string.SC25); String d25 = getString(R.string.SD25); String ans25 = getString(R.string.SAns25);
        String a26 = getString(R.string.SA26); String b26 = getString(R.string.SB26); String c26 = getString(R.string.SC26); String d26 = getString(R.string.SD26); String ans26 = getString(R.string.SAns26);
        String a27 = getString(R.string.SA27); String b27 = getString(R.string.SB27); String c27 = getString(R.string.SC27); String d27 = getString(R.string.SD27); String ans27 = getString(R.string.SAns27);
        String a28 = getString(R.string.SA28); String b28 = getString(R.string.SB28); String c28 = getString(R.string.SC28); String d28 = getString(R.string.SD28); String ans28 = getString(R.string.SAns28);
        String a29 = getString(R.string.SA29); String b29 = getString(R.string.SB29); String c29 = getString(R.string.SC29); String d29 = getString(R.string.SD29); String ans29 = getString(R.string.SAns29);
        String a30 = getString(R.string.SA30); String b30 = getString(R.string.SB30); String c30 = getString(R.string.SC30); String d30 = getString(R.string.SD30); String ans30 = getString(R.string.SAns30);
        String a31 = getString(R.string.SA31); String b31 = getString(R.string.SB31); String c31 = getString(R.string.SC31); String d31 = getString(R.string.SD31); String ans31 = getString(R.string.SAns31);
        String a32 = getString(R.string.SA32); String b32 = getString(R.string.SB32); String c32 = getString(R.string.SC32); String d32 = getString(R.string.SD32); String ans32 = getString(R.string.SAns32);
        String a33 = getString(R.string.SA33); String b33 = getString(R.string.SB33); String c33 = getString(R.string.SC33); String d33 = getString(R.string.SD33); String ans33 = getString(R.string.SAns33);
        String a34 = getString(R.string.SA34); String b34 = getString(R.string.SB34); String c34 = getString(R.string.SC34); String d34 = getString(R.string.SD34); String ans34 = getString(R.string.SAns34);
        String a35 = getString(R.string.SA35); String b35 = getString(R.string.SB35); String c35 = getString(R.string.SC35); String d35 = getString(R.string.SD35); String ans35 = getString(R.string.SAns35);
        String a36 = getString(R.string.SA36); String b36 = getString(R.string.SB36); String c36 = getString(R.string.SC36); String d36 = getString(R.string.SD36); String ans36 = getString(R.string.SAns36);
        String a37 = getString(R.string.SA37); String b37 = getString(R.string.SB37); String c37 = getString(R.string.SC37); String d37 = getString(R.string.SD37); String ans37 = getString(R.string.SAns37);
        String a38 = getString(R.string.SA38); String b38 = getString(R.string.SB38); String c38 = getString(R.string.SC38); String d38 = getString(R.string.SD38); String ans38 = getString(R.string.SAns38);
        String a39 = getString(R.string.SA39); String b39 = getString(R.string.SB39); String c39 = getString(R.string.SC39); String d39 = getString(R.string.SD39); String ans39 = getString(R.string.SAns39);
        String a40 = getString(R.string.SA40); String b40 = getString(R.string.SB40); String c40 = getString(R.string.SC40); String d40 = getString(R.string.SD40); String ans40 = getString(R.string.SAns40);


        questionsList = new ArrayList<>();
        questionsList.add(new StructureModel(R.string.SQuestion1, a1, b1, c1, d1, ans1)); //1
        questionsList.add(new StructureModel(R.string.SQuestion2, a2, b2, c2, d2, ans2)); //2
        questionsList.add(new StructureModel(R.string.SQuestion3, a3, b3, c3, d3, ans3)); //3
        questionsList.add(new StructureModel(R.string.SQuestion4, a4, b4, c4, d4, ans4)); //4
        questionsList.add(new StructureModel(R.string.SQuestion5, a5, b5, c5, d5, ans5)); //5
        questionsList.add(new StructureModel(R.string.SQuestion6, a6, b6, c6, d6, ans6)); //6
        questionsList.add(new StructureModel(R.string.SQuestion7, a7, b7, c7, d7, ans7)); //7
        questionsList.add(new StructureModel(R.string.SQuestion8, a8, b8, c8, d8, ans8)); //8
        questionsList.add(new StructureModel(R.string.SQuestion9, a9, b9, c9, d9, ans9)); //9
        questionsList.add(new StructureModel(R.string.SQuestion10, a10, b10, c10, d10, ans10)); //10
        questionsList.add(new StructureModel(R.string.SQuestion11, a11, b11, c11, d11, ans11)); //11
        questionsList.add(new StructureModel(R.string.SQuestion12, a12, b12, c12, d12, ans12)); //12
        questionsList.add(new StructureModel(R.string.SQuestion13, a13, b13, c13, d13, ans13)); //13
        questionsList.add(new StructureModel(R.string.SQuestion14, a14, b14, c14, d14, ans14)); //14
        questionsList.add(new StructureModel(R.string.SQuestion15, a15, b15, c15, d15, ans15)); //15
        questionsList.add(new StructureModel(R.string.SQuestion16, a16, b16, c16, d16, ans16)); //16
        questionsList.add(new StructureModel(R.string.SQuestion17, a17, b17, c17, d17, ans17)); //17
        questionsList.add(new StructureModel(R.string.SQuestion18, a18, b18, c18, d18, ans18)); //18
        questionsList.add(new StructureModel(R.string.SQuestion19, a19, b19, c19, d19, ans19)); //19
        questionsList.add(new StructureModel(R.string.SQuestion20, a20, b20, c20, d20, ans20)); //20
        questionsList.add(new StructureModel(R.string.SQuestion21, a21, b21, c21, d21, ans21)); //21
        questionsList.add(new StructureModel(R.string.SQuestion22, a22, b22, c22, d22, ans22)); //22
        questionsList.add(new StructureModel(R.string.SQuestion23, a23, b23, c23, d23, ans23)); //23
        questionsList.add(new StructureModel(R.string.SQuestion24, a24, b24, c24, d24, ans24)); //24
        questionsList.add(new StructureModel(R.string.SQuestion25, a25, b25, c25, d25, ans25)); //25
        questionsList.add(new StructureModel(R.string.SQuestion26, a26, b26, c26, d26, ans26)); //26
        questionsList.add(new StructureModel(R.string.SQuestion27, a27, b27, c27, d27, ans27)); //27
        questionsList.add(new StructureModel(R.string.SQuestion28, a28, b28, c28, d28, ans28)); //28
        questionsList.add(new StructureModel(R.string.SQuestion29, a29, b29, c29, d29, ans29)); //29
        questionsList.add(new StructureModel(R.string.SQuestion30, a30, b30, c30, d30, ans30)); //30
        questionsList.add(new StructureModel(R.string.SQuestion31, a31, b31, c31, d31, ans31)); //31
        questionsList.add(new StructureModel(R.string.SQuestion32, a32, b32, c32, d32, ans32)); //32
        questionsList.add(new StructureModel(R.string.SQuestion33, a33, b33, c33, d33, ans33)); //33
        questionsList.add(new StructureModel(R.string.SQuestion34, a34, b34, c34, d34, ans34)); //34
        questionsList.add(new StructureModel(R.string.SQuestion35, a35, b35, c35, d35, ans35)); //35
        questionsList.add(new StructureModel(R.string.SQuestion36, a36, b36, c36, d36, ans36)); //36
        questionsList.add(new StructureModel(R.string.SQuestion37, a37, b37, c37, d37, ans37)); //37
        questionsList.add(new StructureModel(R.string.SQuestion38, a38, b38, c38, d38, ans38)); //38
        questionsList.add(new StructureModel(R.string.SQuestion39, a39, b39, c39, d39, ans39)); //39
        questionsList.add(new StructureModel(R.string.SQuestion40, a40, b40, c40, d40, ans40)); //40






    }

}
