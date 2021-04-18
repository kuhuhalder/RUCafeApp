package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class StoreOrdersActivity extends AppCompatActivity {

    ListView OrdersDisplay;
    TextView OrderTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        OrdersDisplay=findViewById(R.id.storeOrdersListView);
        OrderTotal=findViewById(R.id.StoreOrderTotalText);
    }

    public void deleteStoreOrder(View view){

    }
}