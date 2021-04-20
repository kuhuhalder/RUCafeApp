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

/**
 * This class is an activity class to accompany the current order/ order details layout and handles the functionalities associated with it
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class OrderDetailsActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    /**
     * the list view that displays the menu items in the current order
     */
    private ListView orderList;
    /**
     * The Text View fields in the current order activity screen
     */
    private TextInputEditText subtotal, tax, total;
    /**
     * The Order object that holds the current menu items in the order
     */
    private Order order = MainActivity.order;
    /**
     * The StoreOrders object that holds all of the orders in for the RUCafe App
     */
    private StoreOrders store = MainActivity.store;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        this.setTitle(R.string.order_details_activity);
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
        for (int i = 0; i < menuItems.size(); i++) {
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

    /**
     * This method is to handle the place order functionalities of the your order screen
     *
     * @param view the current view of the screen - the button accompanying the place order
     */
    public void placeOrder(View view) {

        ArrayList<MenuItem> menuItems = order.getMenuItem();
        if (menuItems.size() == 0) {
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

    /**
     * This class is an on item click listener for the list view and deletes menu items from the current order if the user confirms
     *
     * @param parent   the entire view
     * @param view     the list view
     * @param position the position or index of the item selected in the list view
     * @param id       the id of the list view
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ArrayList<MenuItem> menuItems = order.getMenuItem();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < menuItems.size(); i++) {
            list.add(menuItems.get(i).toString());
        }
        ArrayAdapter<String> menuItemsList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        orderList.setAdapter(menuItemsList);
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle(R.string.alert_title_item);
        alert.setMessage(R.string.alert_message_item);
        alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            /**
             * @param dialog the dialog of the alert
             * @param which which button is selected
             */
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
        }).setNegativeButton(R.string.no, null);
        AlertDialog dialog = alert.create();
        dialog.show();

    }
}