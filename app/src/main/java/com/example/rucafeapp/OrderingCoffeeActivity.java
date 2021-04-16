package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);
        size= findView

    }

    public void addToOrder(View view)
    {

    }
}