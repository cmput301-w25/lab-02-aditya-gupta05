package com.example.listycity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    ListView cityList;
    ArrayAdapter<String> cityAdapter;
    ArrayList<String> dataList;
    Button confrim;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //imp*
        setContentView(R.layout.activity_main); //imp*


        EdgeToEdge.enable(this);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //findViewById()-> imp*
        cityList = findViewById(R.id.city_list);
        confrim = findViewById(R.id.Confrim);

        String[] cities={"Edmonton", "Paris", "London"};

        dataList = new ArrayList<>();
        dataList.addAll(Arrays.asList(cities));

        cityAdapter = new ArrayAdapter<>(this, R.layout.content, dataList);
        cityList.setAdapter(cityAdapter);

        setUpListViewListener();

        confrim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addItem(view);
            }
        });

    }
    private void setUpListViewListener(){
        cityList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Context context = getApplicationContext();
                Toast.makeText(context, "Item Removed", Toast.LENGTH_LONG).show();


                dataList.remove(position);
                cityAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
    private void addItem(View view){
        EditText input = findViewById(R.id.cityentry);
        String itemText = input.getText().toString();

        if (!(itemText.equals(""))){
            cityAdapter.add(itemText);
            input.setText("");
        } else{
            Toast.makeText(getApplicationContext(), "Please enter city name", Toast.LENGTH_LONG).show();
        }
    }
}