package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 *
 */
public class MainActivity extends AppCompatActivity {
    private ImageButton launchDonuts, launchCoffeee;
    private Button currentOrderButton, storeOrdersButton;
    protected static Order order = new Order();
    protected static StoreOrders store = new StoreOrders();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        launchDonuts = findViewById(R.id.MainDonutsImage);
        launchCoffeee = findViewById(R.id.MainCoffeeImage);
        currentOrderButton = findViewById(R.id.CurrentOrderMainButton);
        storeOrdersButton = findViewById(R.id.StoreOrdersMainButton);
    }

    /**
     * @param view
     */
    public void launchOrderDonuts(View view)
    {
        Intent intent = new Intent(MainActivity.this, OrderingDonutsActivity.class);
        startActivity(intent);
    }

    /**
     * @param view
     */
    public void launchOrderCoffee(View view)
    {
        Intent intent = new Intent(MainActivity.this, OrderingCoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * @param view
     */
    public void launchYourOrder(View view)
    {
        Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
       startActivity(intent);

    }

    /**
     * @param view
     */
    public void launchStoreOrders(View view)
    {
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }

}