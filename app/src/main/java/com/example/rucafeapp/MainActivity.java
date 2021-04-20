package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

/**
 * This class is an activity class to accompany the main activity layout and handles the functionalities associated with it
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class MainActivity extends AppCompatActivity {

    /**
     * The ImageButtons that lead to the order item menu for that specific item type
     */
    private ImageButton launchDonuts, launchCoffeee;
    /**
     * The Buttons that lead to the the current order or store order pages
     */
    private Button currentOrderButton, storeOrdersButton;
    /**
     * The Order object that holds the current menu items in the order
     */
    protected static Order order = new Order();
    /**
     * The StoreOrders object that holds all of the orders in for the RUCafe App
     */
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
     * This method is to launch the order donuts screen
     *
     * @param view the current view of the image button
     */
    public void launchOrderDonuts(View view) {
        Intent intent = new Intent(MainActivity.this, OrderingDonutsActivity.class);
        startActivity(intent);
    }

    /**
     * This method is to launch the order coffee screen
     *
     * @param view the current view of the image button
     */
    public void launchOrderCoffee(View view) {
        Intent intent = new Intent(MainActivity.this, OrderingCoffeeActivity.class);
        startActivity(intent);
    }

    /**
     * This method is to launch the current/ your order screen
     *
     * @param view the current view of the image button
     */
    public void launchYourOrder(View view) {
        Intent intent = new Intent(MainActivity.this, OrderDetailsActivity.class);
        startActivity(intent);

    }

    /**
     * This method is to launch the store orders screen
     *
     * @param view the current view of the image button
     */
    public void launchStoreOrders(View view) {
        Intent intent = new Intent(MainActivity.this, StoreOrdersActivity.class);
        startActivity(intent);
    }

}