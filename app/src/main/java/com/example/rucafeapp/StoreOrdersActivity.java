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

import java.util.ArrayList;

/**
 * This method is for the Store Orders screen and takes care of the user actions on that screen.
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class StoreOrdersActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * the list view that displays all of the orders submitted to the store
     */
    private ListView ordersDisplay;
    /**
     * The StoreOrders object that holds all of the orders in for the RUCafe App
     */
    private StoreOrders store = MainActivity.store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);
        this.setTitle(R.string.store_orders_activity);
        ordersDisplay = findViewById(R.id.storeOrdersListView);
        ordersDisplay.setOnItemClickListener(this);

        ArrayList<Order> orders = store.getOrders();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            list.add(orders.get(i).toString());
        }

        ArrayAdapter<String> ordersList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ordersDisplay.setAdapter(ordersList);
    }


    /**
     * This gets the order that has been selected and deletes it if the user confirms
     *
     * @param parent   the entire view
     * @param view     the list view
     * @param position of selected object in the view list
     * @param id       of the list
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        ArrayList<Order> orders = store.getOrders();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < orders.size(); i++) {
            list.add(orders.get(i).toString());
        }
        ArrayAdapter<String> ordersList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        ordersDisplay.setAdapter(ordersList);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.alert_title_order);
        alert.setMessage(R.string.alert_message_order);
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            /**
             * @param dialog of the alert
             * @param which index of the list view item selected
             */
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
        }).setNegativeButton(R.string.no, null);
        AlertDialog dialog = alert.create();
        dialog.show();

    }
}