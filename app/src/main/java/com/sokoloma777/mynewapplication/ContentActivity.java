package com.sokoloma777.mynewapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ContentActivity extends AppCompatActivity {
    private TextView tvDiscription;
    private TextView tvHeroName;
    private int position;
    private String[] hero_full_names_array;
    private String[] hero_discription_array;
    private int category_index;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        tvDiscription = findViewById(R.id.tvDiscription);
        tvHeroName = findViewById(R.id.tvHeroName);

        catchIntent();
    }

    private void catchIntent() {
        Intent iVar = getIntent();
        if (iVar != null) {
            position = iVar.getIntExtra("position", 0);
            category_index = iVar.getIntExtra("category", 3);
        }

        switch (category_index){
            case (1):
                hero_full_names_array = getResources().getStringArray(R.array.full_names_of_main_persons);
                hero_discription_array = getResources().getStringArray(R.array.discriptions_of_main_persons);
                break;
            case (2):
                hero_full_names_array = getResources().getStringArray(R.array.full_names_of_secondary_persons);
                hero_discription_array = getResources().getStringArray(R.array.discriptions_of_secondary_persons);
                break;
            case (3):
                hero_full_names_array = getResources().getStringArray(R.array.full_names_of_male_persons);
                hero_discription_array = getResources().getStringArray(R.array.discriptions_of_male_persons);
                break;
            case (4):
                hero_full_names_array = getResources().getStringArray(R.array.full_names_of_female_persons);
                hero_discription_array = getResources().getStringArray(R.array.discriptions_of_female_persons);
                break;
        }

        tvHeroName.setText(hero_full_names_array[position]);
        tvDiscription.setText(hero_discription_array[position]);
    }
}
