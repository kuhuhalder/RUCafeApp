package com.example.rucafeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderDetailsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView orderList;
    private TextInputEditText subtotal, tax, total;
    private Order order = MainActivity.order; // this way or  from under placeOrder
    private StoreOrders store = MainActivity.store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        orderList = findViewById(R.id.yourOrdersListView);
        orderList.setOnItemClickListener(this);
        subtotal = findViewById(R.id.subtotal);
        tax = findViewById(R.id.salestax);
        total = findViewById(R.id.total);
        subtotal.setEnabled(false);
        tax.setEnabled(false);
        total.setEnabled(false);
        ArrayList<MenuItem> menuItems = order.getMenuItem();

        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < menuItems.size(); i++)
        {
            list.add(menuItems.get(i).toString());
        }
        ArrayAdapter<String> menuItemsList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        orderList.setAdapter(menuItemsList);

        order.calculateSubtotal();
        subtotal.setText(String.format("%,.2f", order.getSubTotal()));
        order.calculateTotal();
        tax.setText(String.format("%,.2f", order.getSalestax()));
        total.setText(String.format("%,.2f", order.getTotal()));

    }

    public void placeOrder(View view) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.putExtra("submittedOrder", (Parcelable) order);
//        startActivity(intent);

        ArrayList<MenuItem> menuItems = order.getMenuItem();
        if(menuItems.size() == 0)
        {
            Context context = getApplicationContext();
            CharSequence text = getString(R.string.add_menuitems);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }
        store.add(order);
        order = new Order();
        MainActivity.order = order;

        orderList.clearChoices();


        ArrayList<String> list = new ArrayList<>();
        ArrayAdapter<String> menuItemsList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        orderList.setAdapter(menuItemsList);

        order.calculateSubtotal();
        subtotal.setText(String.format("%,.2f", order.getSubTotal()));
        order.calculateTotal();
        tax.setText(String.format("%,.2f", order.getSalestax()));
        total.setText(String.format("%,.2f", order.getTotal()));

        Context context = getApplicationContext();
        CharSequence text = getString(R.string.order_has_been_placed);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<MenuItem> menuItems = order.getMenuItem();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < menuItems.size(); i++)
        {
            list.add(menuItems.get(i).toString());
        }
        ArrayAdapter<String> menuItemsList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        orderList.setAdapter(menuItemsList);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove Item");
        alert.setMessage("Item to be removed?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
                order.remove(order.getMenuItem().get(position));
               menuItemsList.notifyDataSetChanged();

                order.calculateSubtotal();
                subtotal.setText(String.format("%,.2f", order.getSubTotal()));
                order.calculateTotal();
                tax.setText(String.format("%,.2f", order.getSalestax()));
                total.setText(String.format("%,.2f", order.getTotal()));

                Context context = getApplicationContext();
                CharSequence text = getString(R.string.item_removed);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }).setNegativeButton("No", null);
        AlertDialog dialog = alert.create();
        dialog.show();

    }
}