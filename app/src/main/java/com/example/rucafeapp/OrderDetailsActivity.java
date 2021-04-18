package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

public class OrderDetailsActivity extends AppCompatActivity {

    ListView listofitems;
    TextView subtotal,tax, total;
    Order order=MainActivity.order; // this way or  from under placeOrder
    StoreOrders store=MainActivity.store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        listofitems= findViewById(R.id.yourOrdersListView);
        subtotal= findViewById(R.id.OrderSubtotal);
        tax= findViewById(R.id.OrderSalesTax);
        total = findViewById(R.id.OrderTotal);
        subtotal.setEnabled(false);
        tax.setEnabled(false);
        total.setEnabled(false);
        subtotal.setText("0.00");
        tax.setText("0.00");
        total.setText("0.00");
    }

    public void placeOrder(View view){
        Intent intent= new Intent(this, MainActivity.class);
        intent.putExtra("submittedOrder", (Parcelable) order);
        startActivity(intent);
//        store.add(order);
        listofitems.clearChoices();

        Context context = getApplicationContext();
        CharSequence text = getString(R.string.order_has_been_placed);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}