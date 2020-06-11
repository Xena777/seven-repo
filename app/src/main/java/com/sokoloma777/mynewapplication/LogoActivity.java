package com.sokoloma777.mynewapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class LogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        ImageView imageForLogo = findViewById(R.id.LogoImage);
        TextView textViewLogo = findViewById(R.id.textViewLogo);
        imageForLogo.setImageResource(R.drawable.naruto_1);

        Animation logo = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.logo_anim);

        imageForLogo.startAnimation(logo);
        textViewLogo.startAnimation(logo);

        start_activity();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    private void start_activity() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Intent intent = new Intent(LogoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        }).start();
    }
}
