package com.untirta.unot.UserSoal.Toggle;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RadioButton;
import android.widget.TableLayout;
import androidx.appcompat.app.AlertDialog;
import com.untirta.unot.R;
import com.untirta.unot.UserSoal.Soal_A;
import java.util.ArrayList;

public class SoalAToggleButton extends TableLayout {

    private RadioButton mActiveRadioButton;

    public SoalAToggleButton(Context context) {
        super(context);
    }

    public SoalAToggleButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ArrayList<RadioButton> getChildren() {
        ArrayList<RadioButton> radioButtons = new ArrayList<>();
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            TableLayout tableRow = (TableLayout) this.getChildAt(i);
            int rowChildCount = tableRow.getChildCount();
            for (int j = 0; j < rowChildCount; j++) {
                View v = tableRow.getChildAt(j);
                if (v instanceof RadioButton) {
                    radioButtons.add((RadioButton) v);
                }
            }
        }
        return radioButtons;
    }

    public void checkAnswer(final RadioButton rb, String answer, Context mContext) {
        if (mActiveRadioButton != null) {
            mActiveRadioButton.setChecked(false);
        }
        int id = -1;
        rb.setChecked(true);
        if (rb.isChecked()){
            //------------
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getContext(), R.style.AlertDialogCustom);

            // set title dialog
            alertDialogBuilder.setTitle("Are You Sure?");

            // set pesan dari dialog
            alertDialogBuilder
                    .setMessage("Your Answer Will be Locked and unable to change")
                    .setIcon(R.mipmap.ico_circle_mini)
                    .setCancelable(false)
                    .setPositiveButton("YES",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog,int id) {
                            // jika tombol diklik, soal langsung terupdate
                            setRadioButtonBackgroundColor(rb, R.color.transparent_grey);
                            if (rb.getText().equals(answer)) {
                                ((Soal_A) mContext).updateScore();
                                Animation startAnimation = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.fade_out);
                                rb.startAnimation(startAnimation);
                                if (startAnimation == startAnimation) {
                                    int waktu_loading = 1000;
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            rb.setVisibility(View.GONE);
                                        }
                                    }, waktu_loading);

                                }
                            }
                            else {
                                Animation startAnimation = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.fade_out);
                                rb.startAnimation(startAnimation);
                                if (startAnimation == startAnimation) {
                                    int waktu_loading = 1000;
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            rb.setVisibility(View.GONE);
                                        }
                                    }, waktu_loading);

                                }

                                for (RadioButton radioButton : getChildren()) {
                                    if (radioButton.getText().equals(answer)) {
                                        radioButton.startAnimation(startAnimation);
                                        if (startAnimation == startAnimation) {
                                            int waktu_loading = 1000;
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    radioButton.setVisibility(View.GONE);
                                                }
                                            }, waktu_loading);

                                        }

                                        id = radioButton.getId();
                                    }
                                }
                            }
                            ((Soal_A) mContext).displayScore();
                            mActiveRadioButton = rb;
                            for (RadioButton radioButton : getChildren()) {
                                radioButton.setClickable(false);
                                if (radioButton.getId() != rb.getId() && radioButton.getId() != id) {
                                    Animation startAnimation = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.fade_out);
                                    radioButton.startAnimation(startAnimation);
                                    if (startAnimation == startAnimation) {
                                        int waktu_loading = 1000;
                                        new Handler().postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                radioButton.setVisibility(View.GONE);
                                            }
                                        }, waktu_loading);

                                    }
                                }
                            }


                        }
                    })
                    .setNegativeButton("NO",new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            // jika tombol ini diklik, akan menutup dialog
                            // dan tidak terjadi apa2

                            if (rb != null) {
                                rb.setChecked(false);
                            }
                            dialog.cancel();

                        }
                    });

            // membuat alert dialog dari builder
            AlertDialog alertDialog = alertDialogBuilder.create();

            // menampilkan alert dialog
            alertDialog.show();
            //------------

        }

    }

    private void setRadioButtonBackgroundColor(RadioButton button, int colorId) {
        button.getBackground().setColorFilter(Color.parseColor(getContext().getString(colorId)), PorterDuff.Mode.MULTIPLY);
    }
}
