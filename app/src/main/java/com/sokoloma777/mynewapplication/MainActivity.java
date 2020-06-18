package com.sokoloma777.mynewapplication;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    MyRecyclerAdapter myRecyclerAdapter = null;
    ArrayList<ListItem> myArray = new ArrayList<>();
    private int category_index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rcView = findViewById(R.id.rcView);
        NavigationView navigationView = findViewById(R.id.navigationView);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        navigationView.setNavigationItemSelectedListener(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        rcView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rcView.setLayoutManager(layoutManager);

        myArray.addAll(fillArray(changeIntToArray(R.array.image_of_female_persons),
                changeIntToArray(R.array.female_persons_items)));
        category_index = 3;

        myRecyclerAdapter = new MyRecyclerAdapter(myArray, category_index,this);
        rcView.setAdapter(myRecyclerAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_settings, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.settings_button){
            Intent intent = new Intent(MainActivity.this, PreferenceActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public ArrayList<ListItem> fillArray(int[] imageArray, int[] textArray){
        ArrayList<ListItem> goalArray = new ArrayList<>();
        for(int i=0; i<imageArray.length; i++) {
            ListItem li = new ListItem(imageArray[i], textArray[i]);
            goalArray.add(li);
        }
        return goalArray;
    }

    public int[] changeIntToArray(int array){
        TypedArray tArray = getResources().obtainTypedArray(array);
        int count = tArray.length();
        int[] idsArray = new int[count];

        for (int i=0; i<idsArray.length; i++) {
            idsArray[i] = tArray.getResourceId(i, 0);
        }

        tArray.recycle();
        return idsArray;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        ArrayList<ListItem> newMyArray = new ArrayList<>();
        switch (menuItem.getItemId()) {
            case (R.id.main_persons_items):
                Toast.makeText(this, "Главные персонажи сериала", Toast.LENGTH_SHORT).show();
                category_index = 1;
                newMyArray.addAll(fillArray(changeIntToArray(R.array.image_of_main_persons),
                        changeIntToArray(R.array.main_persons_items)));
                myRecyclerAdapter.updateAdapter(newMyArray, category_index);
                break;

            case (R.id.secondary_persons_items):
                Toast.makeText(this, "Второстепенные персонажи", Toast.LENGTH_SHORT).show();
                category_index = 2;
                newMyArray.addAll(fillArray(changeIntToArray(R.array.image_of_secondary_persons),
                        changeIntToArray(R.array.secondary_persons_items)));
                myRecyclerAdapter.updateAdapter(newMyArray, category_index);
                break;

            case (R.id.male_persons_items):
                Toast.makeText(this, "Мужские персонажи", Toast.LENGTH_SHORT).show();
                category_index = 3;
                newMyArray.addAll(fillArray(changeIntToArray(R.array.image_of_male_persons),
                        changeIntToArray(R.array.male_persons_items)));
                myRecyclerAdapter.updateAdapter(newMyArray, category_index);
                break;

            case (R.id.female_persons_items):
                Toast.makeText(this, "Женские персонажи", Toast.LENGTH_SHORT).show();
                category_index = 4;
                newMyArray.addAll(fillArray(changeIntToArray(R.array.image_of_female_persons),
                        changeIntToArray(R.array.female_persons_items)));
                myRecyclerAdapter.updateAdapter(newMyArray, category_index);
                break;
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}