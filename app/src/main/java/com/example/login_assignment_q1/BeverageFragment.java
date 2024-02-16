package com.example.login_assignment_q1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class BeverageFragment extends Fragment {

    //initializing all buttons and textview in beverage fragment
    Button cocacolaAdd,cococolaSubract,milkshakeAdd,milkshakeSubract,juiceAdd,juiceSubract,beverageSave;
    TextView cococolaQuantity,cococolaPrice,cococola,milkshakeQuantity,milkshakePrice,milkshake,juiceQuantity,juicePrice,juice;

    //creating an instance of quantityAddSud and orderDatabase
    quantityAddSub quantityAddSub = new quantityAddSub();

    int cocoCount,milkshakeCount,juicecount;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name= bundle.getString("name");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beverage, container, false);

        //mapping the variables with the respective IDs
        cocacolaAdd= view.findViewById(R.id.CocoCola_add);
        cococolaSubract = view.findViewById(R.id.CocoCola_sub);
        cococola=view.findViewById(R.id.CocoCola);
        cococolaPrice=view.findViewById(R.id.CocoCola_price);
        cococolaQuantity=view.findViewById(R.id.CocoCola_quantity);

        milkshakeAdd = view.findViewById(R.id.Milkshake_add);
        milkshakeSubract = view.findViewById(R.id.Milkshake_sub);
        milkshake = view.findViewById(R.id.ChocolateMilkshake);
        milkshakePrice = view.findViewById(R.id.ChocolateMilkshake_price);
        milkshakeQuantity = view.findViewById(R.id.Milkshake_quantity);

        juiceAdd = view.findViewById(R.id.Juice_add);
        juiceSubract = view.findViewById(R.id.Juice_sub);
        juice = view.findViewById(R.id.AppleJuice);
        juicePrice = view.findViewById(R.id.AppleJuice_price);
        juiceQuantity = view.findViewById(R.id.Juice_quantity);

        beverageSave = view.findViewById(R.id.beverageSave);

        //setting onClickListeners for each buttons to increase or decrease number of quantity
        cocacolaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = cococolaQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                cocoCount = Integer.parseInt(quantity);
                cococolaQuantity.setText(quantity);
            }
        });

        cococolaSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = cococolaQuantity.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                cocoCount = Integer.parseInt(quantity);
                cococolaQuantity.setText(quantity);
            }
        });

        milkshakeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = milkshakeQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                milkshakeCount = Integer.parseInt(quantity);
                milkshakeQuantity.setText(quantity);
            }
        });

        milkshakeSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = milkshakeQuantity.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                milkshakeCount = Integer.parseInt(quantity);
                milkshakeQuantity.setText(quantity);
            }
        });

        juiceAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = juiceQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                juicecount = Integer.parseInt(quantity);
                juiceQuantity.setText(quantity);
            }
        });

        juiceSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = juiceQuantity.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                juicecount = Integer.parseInt(quantity);
                juiceQuantity.setText(quantity);
            }
        });

        //storing the orders in the database
        ContentValues orderValues = new ContentValues();
        beverageSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cocoCount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,cococola.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,cococolaQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price1 = Integer.parseInt(cococolaQuantity.getText().toString()) * Integer.parseInt(cococolaPrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price1));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);

                } if (milkshakeCount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,milkshake.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,milkshakeQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price2 = Integer.parseInt(milkshakeQuantity.getText().toString()) * Integer.parseInt(milkshakePrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price2));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);
                } if (juicecount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,juice.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,juiceQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price3 = Integer.parseInt(juiceQuantity.getText().toString()) * Integer.parseInt(juicePrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price3));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);

                }else if (cocoCount==0 && milkshakeCount==0 && juicecount==0){
                    //below message is toasted if the user does not select the foods
                    Toast.makeText(getContext(),"Please Select beverages",Toast.LENGTH_SHORT).show();
                }

                if (cocoCount>0 || milkshakeCount>0 || juicecount>0){
                    //below message is toasted if the user selects the foods
                    Toast.makeText(getContext(),"Beverages saved",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}