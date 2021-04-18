package com.example.rucafeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

/**
 * 
 */
public class OrderingDonutsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner donutFlavor, donutQuantity;
    private TextInputEditText subtotal;
    private Order order = MainActivity.order;
    private static final String DONUT_PRICE = "1.39";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ordering_donuts);
        donutFlavor = findViewById(R.id.donutFlavorSpinner);
        donutQuantity = findViewById(R.id.donutQuantitySpinner);
        subtotal = findViewById(R.id.subtotalDonut);
        subtotal.setEnabled(false);
        subtotal.setText(DONUT_PRICE);
        donutFlavor.setOnItemSelectedListener(this);
        donutQuantity.setOnItemSelectedListener(this);
    }

    /**
     * @param view 
     */
    public void addToOrder(View view)
    {
        String selectedFlavor = donutFlavor.getSelectedItem().toString();
        int selectedQuantity = Integer.parseInt(String.valueOf(donutQuantity.getSelectedItem()));
        MenuItem donut = new Donut(selectedFlavor, selectedQuantity);

        donut.itemPrice();
        subtotal.setText(String.format("%,.2f", donut.getPrice()));
        order.add(donut);

        Context context = getApplicationContext();
        CharSequence text = getString(R.string.donut_added_to_order);; // hardcoded text
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        donutFlavor.setSelection(0);
        donutQuantity.setSelection(0);
        subtotal.setText(DONUT_PRICE);
    }


    /**
     * @param parent 
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String selectedFlavor = donutFlavor.getSelectedItem().toString();
        int selectedQuantity = Integer.parseInt(String.valueOf(donutQuantity.getSelectedItem()));
        MenuItem donut = new Donut(selectedFlavor, selectedQuantity);
        donut.itemPrice();
        subtotal.setText(String.format("%,.2f", donut.getPrice()));
    }

    /**
     * @param parent 
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}