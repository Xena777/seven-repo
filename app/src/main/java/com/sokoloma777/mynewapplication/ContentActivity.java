package com.sokoloma777.mynewapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

public class ContentActivity extends AppCompatActivity {
    private TextView tvDiscription;
    private TextView tvHeroName;
    private int position;
    private String[] hero_full_names_array;
    private String[] hero_discription_array;
    private int category_index;
    private SharedPreferences save_pref;
    private String text;
    private String color;
    private ScrollView back_lin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        tvDiscription = findViewById(R.id.tvDiscription);
        tvHeroName = findViewById(R.id.tvHeroName);
        back_lin = findViewById(R.id.back_lin);

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

        save_pref = PreferenceManager.getDefaultSharedPreferences(this);
        text = save_pref.getString("text_size", "Средний");
        color = save_pref.getString("colors", "Фиолетовый");

        switch (text) {
            case "Очень крупный":
                tvDiscription.setTextSize(getResources().getDimension(R.dimen.extra_big_size_text));
                tvHeroName.setTextSize(getResources().getDimension(R.dimen.extra_extra_big_size_text));
                break;
            case "Крупный":
                tvDiscription.setTextSize(getResources().getDimension(R.dimen.big_size_text));
                tvHeroName.setTextSize(getResources().getDimension(R.dimen.extra_big_size_text));
                break;
            case "Средний":
                tvDiscription.setTextSize(getResources().getDimension(R.dimen.middle_size_text));
                tvHeroName.setTextSize(getResources().getDimension(R.dimen.big_size_text));
                break;
            case "Маленький":
                tvDiscription.setTextSize(getResources().getDimension(R.dimen.small_size_text));
                tvHeroName.setTextSize(getResources().getDimension(R.dimen.middle_size_text));
                break;
        }

        switch (color) {
            case "Красный":
                back_lin.setBackgroundColor(getResources().getColor(R.color.red));
                break;
            case "Зеленый":
                back_lin.setBackgroundColor(getResources().getColor(R.color.green));
                break;
            case "Синий":
                back_lin.setBackgroundColor(getResources().getColor(R.color.blue));
                break;
            case "Оранжевый":
                back_lin.setBackgroundColor(getResources().getColor(R.color.orange));
                break;
            case "Желтый":
                back_lin.setBackgroundColor(getResources().getColor(R.color.yellow));
                break;
            case "Розовый":
                back_lin.setBackgroundColor(getResources().getColor(R.color.pink));
                break;
            case "Фиолетовый":
                back_lin.setBackgroundColor(getResources().getColor(R.color.purple));
                break;
        }

        tvHeroName.setText(hero_full_names_array[position]);
        tvDiscription.setText(hero_discription_array[position]);

    }
}
