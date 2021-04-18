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
 *
 * @author Dhvani Kakabalia
 * @author Kuhu Halder
 */
public class OrderingCoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner size;
    private CheckBox milk, caramel, syrup, whippedcream, cream;
    private TextInputEditText subtotal;
    private static final int QUANTITY = 1;
    private Order order = MainActivity.order;
    private static final String SHORT_BLACK = "1.99";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_coffee);
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
            ((Coffee) coffee).add("Milk");
        if (caramel.isChecked())
            ((Coffee) coffee).add("Caramel");
        if (syrup.isChecked())
            ((Coffee) coffee).add("Syrup");
        if (whippedcream.isChecked())
            ((Coffee) coffee).add("Whipped Cream");
        if (cream.isChecked())
            ((Coffee) coffee).add("Cream");
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
     * @param parent
     * @param view the list view
     * @param position of selected object in the view
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//        String selectedSize = size.getSelectedItem().toString();
//        MenuItem coffee = new Coffee(selectedSize, QUANTITY);
        setSubtotal(view);
    }

    /**
     * @param parent
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * @param view
     */
    public void setSubtotal(View view) {
        String selectedSize = size.getSelectedItem().toString();
        MenuItem coffee = new Coffee(selectedSize, QUANTITY);
        if (milk.isChecked())
            ((Coffee) coffee).add("Milk");
        if (caramel.isChecked())
            ((Coffee) coffee).add("Caramel");
        if (syrup.isChecked())
            ((Coffee) coffee).add("Syrup");
        if (whippedcream.isChecked())
            ((Coffee) coffee).add("Whipped Cream");
        if (cream.isChecked())
            ((Coffee) coffee).add("Cream");
        coffee.itemPrice();
        subtotal.setText(String.format("%,.2f", coffee.getPrice()));

    }
}