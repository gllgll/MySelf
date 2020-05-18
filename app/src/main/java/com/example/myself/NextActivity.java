package com.example.myself;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NextActivity extends AppCompatActivity {
    RatingBar ratingBar;
    TextView display;
    Button buttonBaidu, buttonCall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        display = findViewById(R.id.textView8);
        ratingBar = findViewById(R.id.ratingBar);
        buttonBaidu = findViewById(R.id.button3);
        buttonCall = findViewById(R.id.button4);

        buttonBaidu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.jju.edu.cn/"));
                startActivity(intent);
            }
        });

        buttonCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:10086"));
                startActivity(intent);
            }
            });

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                    int core = (int)(v * 20);
                    String core_str = String.valueOf(core);
                    if (core < 60){
                        display.setText("你也配给我"+core_str+"分？");
                        display.setTextColor(Color.BLACK);
                    }else if(core >= 60 && core< 90){
                        display.setText("谢谢您的"+core_str+"分鼓励");
                        display.setTextColor(Color.GREEN);
                    }else {
                        display.setText("感谢您"+core_str+"的评分！您眼光真好！");
                        display.setTextColor(Color.RED);
                    }
            }
        });
    }
}
