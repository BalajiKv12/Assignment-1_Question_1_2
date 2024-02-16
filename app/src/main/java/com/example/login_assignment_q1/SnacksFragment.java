package com.example.login_assignment_q1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class SnacksFragment extends Fragment {

    //initializing all buttons and textview in snacks fragment
    Button puffsAdd,puffsSubract,chipsAdd,chipsSubract,sandwichAdd,sandwichSubract,snacksSave;
    TextView puffsQuantity,puffsPrice,puffs,chipsQuantity,chipsPrice,chips,sandwichQuantity,sandwichPrice,sandwich;

    //creating an instance of quantityAddSud and orderDatabase
    quantityAddSub quantityAddSub = new quantityAddSub();

    int puffsCount,chipsCount,sandwichcount;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name= bundle.getString("name");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_snacks, container, false);

        //mapping the variables with the respective IDs
        puffsAdd= view.findViewById(R.id.Puffs_add);
        puffsSubract = view.findViewById(R.id.puffs_sub);
        puffs=view.findViewById(R.id.eggPuffs);
        puffsPrice=view.findViewById(R.id.Puffs_price);
        puffsQuantity=view.findViewById(R.id.Puffs_quantity);

        chipsAdd = view.findViewById(R.id.Chips_add);
        chipsSubract = view.findViewById(R.id.Chips_sub);
        chips = view.findViewById(R.id.PotatoChips);
        chipsPrice = view.findViewById(R.id.Chips_price);
        chipsQuantity = view.findViewById(R.id.Chips_quantity);

        sandwichAdd = view.findViewById(R.id.Sandwich_add);
        sandwichSubract = view.findViewById(R.id.Sandwich_sub);
        sandwich = view.findViewById(R.id.Sandwich);
        sandwichPrice = view.findViewById(R.id.Sandwich_price);
        sandwichQuantity = view.findViewById(R.id.Sandwich_quantity);

        snacksSave = view.findViewById(R.id.snacksSave);

        //setting onClickListeners for each buttons to increase or decrease number of quantity
        puffsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = puffsQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                puffsCount = Integer.parseInt(quantity);
                puffsQuantity.setText(quantity);
            }
        });

        puffsSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = puffsQuantity.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                puffsCount = Integer.parseInt(quantity);
                puffsQuantity.setText(quantity);
            }
        });

        chipsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = chipsQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                chipsCount = Integer.parseInt(quantity);
                chipsQuantity.setText(quantity);
            }
        });

        chipsSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q =chips.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                chipsCount = Integer.parseInt(quantity);
                chips.setText(quantity);
            }
        });

        sandwichAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = sandwichQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                sandwichcount = Integer.parseInt(quantity);
                sandwichQuantity.setText(quantity);
            }
        });

        sandwichSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = sandwichQuantity.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                sandwichcount = Integer.parseInt(quantity);
                sandwichQuantity.setText(quantity);
            }
        });

        //storing the orders in the database
        ContentValues orderValues = new ContentValues();
        snacksSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(puffsCount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,puffs.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,puffsQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price1 = Integer.parseInt(puffsQuantity.getText().toString()) * Integer.parseInt(puffsPrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price1));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);

                } if (chipsCount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,chips.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,chipsQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price2 = Integer.parseInt(chipsQuantity.getText().toString()) * Integer.parseInt(chipsPrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price2));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);
                } if (sandwichcount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,sandwich.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,sandwichQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price3 = Integer.parseInt(sandwichQuantity.getText().toString()) * Integer.parseInt(sandwichPrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price3));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);

                }else if(puffsCount==0 && chipsCount==0 && sandwichcount==0){
                    //below message is toasted if the user does not select the foods
                    Toast.makeText(getContext(),"Please Select snacks",Toast.LENGTH_SHORT).show();
                }
                if(puffsCount>0 || chipsCount>0 || sandwichcount>0){
                    //below message is toasted if the user selects the foods
                    Toast.makeText(getContext(),"Snacks saved",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}