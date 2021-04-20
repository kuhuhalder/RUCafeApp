package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

/**
 * This method is for the Ordering Coffee Activity screen and takes care of the user actions on that screen.
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    /**
     * Spinner to choose the coffee size
     */
    private Spinner size;
    /**
     * The checkboxes for the coffee add-ins
     */
    private CheckBox milk, caramel, syrup, whippedcream, cream;
    /**
     * The Text View field for the subtotal of the coffee
     */
    private TextInputEditText subtotal;
    /**
     * The quantity of the coffee that is currently being ordered
     */
    private static final int QUANTITY = 1;
    /**
     * The Order object that holds the current menu items in the order
     */
    private Order order = MainActivity.order;
    /**
     * Price for a plain short black coffee
     */
    private static final String SHORT_BLACK = "1.99";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);
        this.setTitle(R.string.order_coffee_activity);
        size = findViewById(R.id.sizeSpinner);
        size.setOnItemSelectedListener(this);
        milk = findViewById(R.id.milkCheckbox);
        caramel = findViewById(R.id.caramelCheckbox);
        syrup = findViewById(R.id.syrupCheckbox);
        whippedcream = findViewById(R.id.whippedCreamCheckbox);
        cream = findViewById(R.id.creamCheckbox);
        subtotal = findViewById(R.id.subtotalCoffee);
        subtotal.setEnabled(false);
        subtotal.setText(SHORT_BLACK);
    }


    /**
     * This method adds a Coffee to the current Order
     * @param view The current view
     */
    public void addToOrder(View view) {
        String selectedSize = size.getSelectedItem().toString();
        MenuItem coffee = new Coffee(selectedSize, QUANTITY);
        if (milk.isChecked())
            ((Coffee) coffee).add(R.string.milk);
        if (caramel.isChecked())
            ((Coffee) coffee).add(R.string.caramel);
        if (syrup.isChecked())
            ((Coffee) coffee).add(R.string.syrup);
        if (whippedcream.isChecked())
            ((Coffee) coffee).add(R.string.whipped_cream);
        if (cream.isChecked())
            ((Coffee) coffee).add(R.string.cream);
        coffee.itemPrice();
        subtotal.setText(String.format("%,.2f", coffee.getPrice()));
        order.add(coffee);

        subtotal.setText(SHORT_BLACK);
        size.setSelection(0);
        if (milk.isChecked())
            milk.setChecked(false);
        if (caramel.isChecked())
            caramel.setChecked(false);
        if (syrup.isChecked())
            syrup.setChecked(false);
        if (whippedcream.isChecked())
            whippedcream.setChecked(false);
        if (cream.isChecked())
            cream.setChecked(false);

        Context context = getApplicationContext();
        CharSequence text = getString(R.string.coffee_added_to_order);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


    /**
     * This gets the size of the Coffee you want to order and sets it to the coffee object
     * @param parent the entire view
     * @param view the list view
     * @param position of selected object in the view list
     * @param id of the list
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        setSubtotal(view);
    }

    /**
    * Method for if nothing is selected in the list view
     * @param parent the entire view
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * sets the Subtotal text view box to the price of the coffee
     * @param view the current view
     */
    public void setSubtotal(View view) {
        String selectedSize = size.getSelectedItem().toString();
        MenuItem coffee = new Coffee(selectedSize, QUANTITY);
        if (milk.isChecked())
            ((Coffee) coffee).add(R.string.milk);
        if (caramel.isChecked())
            ((Coffee) coffee).add(R.string.caramel);
        if (syrup.isChecked())
            ((Coffee) coffee).add(R.string.syrup);
        if (whippedcream.isChecked())
            ((Coffee) coffee).add(R.string.whipped_cream);
        if (cream.isChecked())
            ((Coffee) coffee).add(R.string.cream);
        coffee.itemPrice();
        subtotal.setText(String.format("%,.2f", coffee.getPrice()));

    }
}