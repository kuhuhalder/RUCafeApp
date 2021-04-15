package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

public class OrderingDonutsActivity extends AppCompatActivity {
    private Spinner donutFlavor, donutQuantity;
    private TextView subtotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        donutFlavor = findViewById(R.id.donutFlavorSpinner);
        donutQuantity = findViewById(R.id.donutQuantitySpinner);
        subtotal = findViewById(R.id.subtotalDonut);
    }

    public void addToOrder(View view)
    {

    }
    
    
}