package com.ptit.tuannc.game;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView txt_coin;
    private TextView txt_heart;
    private TextView txt_ketQua;
    private int coin = 0;
    private int heart = 5;
    private int i = 0;
    private int dem = 0;
    private LinearLayout layout_4;
    private LinearLayout layout_5;
    private LinearLayout layout_3;
    private LinearLayout layout_2;
    private LinearLayout layout_button;
    private Button btnChoose;
    private final String[] DAP_AN = {
            "HOIDONG",
            "AOMUA",
            "BAOCAO",
            "OTO",
            "DANONG",
            "XAKEP",
            "XAPHONG",
            "TOHOAI",
            "CANTHIEP",
            "CATTUONG",
            "DANHLUA",
            "TICHPHAN",
            "QUYHANG",
            "GIANGMAI",
            "GIANDIEP",
            "SONGSONG",
            "THOTHE",
            "THATTINH",
            "TRANHTHU",
            "TOTIEN",
            "MASAT",
            "HONGTAM"
    };

    public static final int[] QUESTIONS = {
            R.drawable.hoidong,
            R.drawable.aomua,
            R.drawable.baocao,
            R.drawable.oto,
            R.drawable.danong,
            R.drawable.xakep,
            R.drawable.xaphong,
            R.drawable.tohoai,
            R.drawable.canthiep,
            R.drawable.cattuong,
            R.drawable.danhlua,
            R.drawable.tichphan,
            R.drawable.quyhang,
            R.drawable.giangmai,
            R.drawable.giandiep,
            R.drawable.songsong,
            R.drawable.thothe,
            R.drawable.thattinh,
            R.drawable.tranhthu,
            R.drawable.totien,
            R.drawable.masat,
            R.drawable.hongtam,
    };
    private Random random = new Random();
    private String ketQua = "";
    private Button[] btnKq;
    private int rd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rd = random();
        initText();
        createButton();
        createImage();
        createButtonPick();
    }

    @SuppressLint("ResourceType")
    public void createButtonChoose() {
        btnChoose = new Button(this);
        layout_button = findViewById(R.id.layout_buton);
        btnChoose.setLayoutParams(new LinearLayout.LayoutParams(250, 100));
        btnChoose.setBackgroundResource(R.drawable.next);
        btnChoose.setOnClickListener(this);
        btnChoose.setId(100);
        layout_button.addView(btnChoose);
    }

    public int random() {
        ArrayList<Integer> arrRD = new ArrayList<>();
        int rdNumber = 0;
        while (check(arrRD, rdNumber)) {
            rdNumber = random.nextInt(QUESTIONS.length);
            arrRD.add(rdNumber);
        }
        return rdNumber;
    }

    public void initText() {
        txt_coin = findViewById(R.id.txt_coin);
        txt_heart = findViewById(R.id.txt_avatar);
        txt_ketQua = findViewById(R.id.txt_ketQua);
    }

    public void createImage() {
        layout_2 = findViewById(R.id.layout_2);
        ImageView[] iv = new ImageView[QUESTIONS.length];
        iv[rd] = new ImageView(this);
        iv[rd].setImageResource(QUESTIONS[rd]);
        layout_2.addView(iv[rd]);
    }

    public void createButton() {
        layout_3 = findViewById(R.id.layout_3);
        btnKq = new Button[DAP_AN[rd].length()];
        for (int i = 0; i < DAP_AN[rd].length(); i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(85, 85));
            btn.setId(i);
            btn.setBackgroundResource(R.drawable.button_xam);
            layout_3.addView(btn);
            btnKq[i] = findViewById(btn.getId());
        }
    }

    public ArrayList randomQuestions() {
        ArrayList<String> arrS = new ArrayList<>();
        int tm = random.nextInt(25) + 65;
        for (int i = 0; i < DAP_AN[rd].length(); i++) {
            arrS.add(DAP_AN[rd].charAt(i) + "");
        }
        for (int i = 0; i < 16 - DAP_AN[rd].length(); i++) {
            arrS.add((char) tm + "");
        }
        return arrS;
    }

    public boolean check(ArrayList<Integer> arrSo, int n) {
        for (int i = 0; i < arrSo.size(); i++) {
            if (n == arrSo.get(i)) {
                return false;
            }
        }
        return true;
    }

    public void createButtonPick() {
        layout_4 = findViewById(R.id.layout_4);
        layout_5 = findViewById(R.id.layout_5);
        ArrayList<Integer> arrSo = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(91, 110));
            btn.setBackgroundResource(R.drawable.tile_hover);
            btn.setOnClickListener(this);
            while (btn.getText() == "") {
                int tmp = random.nextInt(16);
                if (check(arrSo, tmp)) {
                    btn.setText((CharSequence) randomQuestions().get(tmp));
                    randomQuestions().remove(tmp);
                    arrSo.add(tmp);
                }
            }
            layout_4.addView(btn);
        }
        for (int i = 0; i < 8; i++) {
            Button btn = new Button(this);
            btn.setLayoutParams(new LinearLayout.LayoutParams(91, 110));
            btn.setBackgroundResource(R.drawable.tile_hover);
            btn.setOnClickListener(this);
            while (btn.getText() == "") {
                int tmp = random.nextInt(16);
                if (check(arrSo, tmp)) {
                    btn.setText((CharSequence) randomQuestions().get(tmp));
                    randomQuestions().remove(tmp);
                    arrSo.add(tmp);
                }
            }
            layout_5.addView(btn);
        }
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if (dem == DAP_AN[rd].length()) {
            if (ketQua.equals(DAP_AN[rd])) {
                for (int i = 0; i < DAP_AN[rd].length(); i++) {
                    btnKq[i].setBackgroundResource(R.drawable.tile_true);
                }
                txt_ketQua.setText("Bạn đã trả lời đúng !!!");
                createButtonChoose();
                btnChoose.setText("NEXT");
                layout_button.setVisibility(View.VISIBLE);
                switch (v.getId()) {
                    case 100:
                        coin += 100;
                        txt_coin.setText(coin + "");
                        layout_4.removeAllViews();
                        layout_5.removeAllViews();
                        layout_3.removeAllViews();
                        layout_2.removeAllViews();
                        layout_button.removeAllViews();
                        rd = random();
                        createButtonPick();
                        createImage();
                        createButton();
                        txt_ketQua.setText("");
                        ketQua = "";
                        dem = 0;
                        i = 0;
                        break;
                }
                return;
            } else {
                for (int i = 0; i < DAP_AN[rd].length(); i++) {
                    btnKq[i].setBackgroundResource(R.drawable.tile_false);
                }
                txt_ketQua.setText("Bạn đã trả lời sai !!!");
                createButtonChoose();
                btnChoose.setText("AGAIN");
                layout_button.setVisibility(View.VISIBLE);
                switch (v.getId()) {
                    case 100:
                        heart -= 1;
                        txt_heart.setText(heart + "");
                        layout_4.removeAllViews();
                        layout_5.removeAllViews();
                        layout_3.removeAllViews();
                        layout_2.removeAllViews();
                        layout_button.removeAllViews();
                        createButtonPick();
                        createImage();
                        createButton();
                        txt_ketQua.setText("");
                        ketQua = "";
                        dem = 0;
                        i = 0;
                        break;
                }
                return;
            }
        }
        if (heart == 0) {
            Toast.makeText(this, "Bạn đã thua", Toast.LENGTH_SHORT).show();
            return;
        }
        btnKq[i].setText(button.getText());
        ketQua += button.getText();
        i++;
        v.setEnabled(false);
        v.setBackgroundColor(601800);
        ((Button) v).setText("");
        dem++;
    }

}

