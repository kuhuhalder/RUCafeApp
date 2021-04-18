package com.example.rucafeapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    private ListView ordersDisplay;
    private TextInputEditText orderTotal;
    private StoreOrders store = MainActivity.store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        ordersDisplay = findViewById(R.id.storeOrdersListView);
        orderTotal = findViewById(R.id.orderTotal);
        ordersDisplay.setOnItemClickListener(this);
        orderTotal.setEnabled(false);

        ArrayList<Order> orders = store.getOrders();
        ArrayList <String> list = new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            list.add(orders.get(i).toString());
        }

        ArrayAdapter<String> ordersList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ordersDisplay.setAdapter(ordersList);
    }

    public void deleteStoreOrder(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<Order> orders = store.getOrders();
        ArrayList <String> list = new ArrayList<>();
        for(int i=0;i<orders.size();i++)
        {
            list.add(orders.get(i).toString());
        }
        ArrayAdapter<String> ordersList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ordersDisplay.setAdapter(ordersList);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Remove Item");
        alert.setMessage("Order to be removed?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                list.remove(position);
               store.remove(store.getOrders().get(position));
                ordersList.notifyDataSetChanged();

                Context context = getApplicationContext();
                CharSequence text = getString(R.string.delete_order);
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

            }
        }).setNegativeButton("No", null);
        AlertDialog dialog = alert.create();
        dialog.show();

    }
}