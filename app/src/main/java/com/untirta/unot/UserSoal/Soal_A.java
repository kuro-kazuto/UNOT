package com.untirta.unot.UserSoal;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.untirta.unot.UserSoal.Adapter.SoalAAdapter;
import com.untirta.unot.UserSoal.Model.ModelNilai;
import com.untirta.unot.UserSoal.Model.SoalAModel;
import com.untirta.unot.R;
import com.untirta.unot.UnderConstruction;
import com.untirta.unot.UserSide.MainActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class Soal_A extends AppCompatActivity {



//==================================================================================================
//============ INI ADALAH BAGIAN PROGRAM, JANGAN DIUBAH KECUALI OLEH DEVELOPER =====================
//================== DIBUAT OLEH : GALIH AJI PAMBUDI & DIMAS EMERALDO A ============================
//================ PROJECT MATA KULIAH APLIKASI BERGERAK JTE UNTIRTA 2021 ==========================
//==================================================================================================

    private RecyclerView mRecyclerView;
    private SoalAAdapter mAdapter;

    private ConstraintLayout mParentLayout;
    private TextView mScoreTextViewL,mScoreTextViewS, tvTimer;
    private TextView mRemaningQuestionsTextView;
    private TextView tvNIM;
    private int mTotalQuestions;
    private int mScore;
    private SoalAModel currentQuestion;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Soal");
    DatabaseReference nilaiDB = FirebaseDatabase.getInstance().getReference("Nilai");


    Button btnNext;
    CountDownTimer countDownTimer;

    //private ArrayList<Soal> questionsList;
    List<SoalAModel> fetchdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soal_a);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        btnNext = findViewById(R.id.btnNext);
        tvTimer = findViewById(R.id.Timer);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = nilaiDB.push().getKey();
                String NIM = tvNIM.getText().toString();


                String total = mScoreTextViewS.getText().toString().trim();
                int jumlahSoal = fetchdata.size();
                Double konversiTotal = Double.parseDouble(total);
                Double T = (konversiTotal*100)/jumlahSoal;
                DecimalFormat df = new DecimalFormat("#");
                String hasil = df.format(T);
                String nilai = hasil;

                ModelNilai userNilai = new ModelNilai(id, NIM, nilai);

                nilaiDB.child(userNilai.getIdentitas()).setValue(userNilai);

                Intent intent = new Intent(Soal_A.this, FinalScore.class);
                intent.putExtra("nilai", nilai);
                intent.putExtra("Uname", NIM);
                startActivity(intent);
                countDownTimer.cancel();
            }
        });

        //addQuestion();

        mRecyclerView = findViewById(R.id.recyclerView);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Soal_A.this);
        mRecyclerView.setLayoutManager(layoutManager);
        fetchdata = new ArrayList<>();
        mAdapter = new SoalAAdapter(this, fetchdata);
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    SoalAModel data = dataSnapshot.getValue(SoalAModel.class);
                    fetchdata.add(data);
                }

                mRecyclerView.setAdapter(mAdapter);
                mTotalQuestions = fetchdata.size();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        tvNIM = findViewById(R.id.Nim);

        mScoreTextViewS = findViewById(R.id.scoreS);
        mParentLayout = findViewById(R.id.question_layout);
        mRemaningQuestionsTextView = findViewById(R.id.QuestionNumber);


        mScore = 0;
        displayScore();

        tvNIM.setText(getIntent().getStringExtra("Uname"));




        //mScoreTextViewL.setText(getIntent().getStringExtra("scoreListening"));

        timer();
    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Toast.makeText(Soal_A.this,"Anda tidak diperkenankan kembali sebelum soal diselesaikan !",Toast.LENGTH_SHORT).show();
        return;
    }

    public void displayScore() {
        String scoreString = String.valueOf(mScore);
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
                String id = nilaiDB.push().getKey();
                String NIM = tvNIM.getText().toString();
                String nilai = mScoreTextViewS.getText().toString().trim();

                ModelNilai userNilai = new ModelNilai(id, NIM, nilai);

                nilaiDB.child(userNilai.getIdentitas()).setValue(userNilai);
                Intent intent = new Intent(Soal_A.this, FinalScore.class);
                intent.putExtra("nilai", nilai);
                intent.putExtra("Uname", NIM);
                startActivity(intent);
            }
        }.start();
    }

    public void updateScore() {
        mScore++;
    }

    //--------------------------BANK SOAL LISTENING SECTION (ISI SESUAI ARRAY)--------------------------


}
