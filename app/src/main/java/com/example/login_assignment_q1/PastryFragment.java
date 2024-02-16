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

public class PastryFragment extends Fragment {

    //initializing all buttons and textview in snacks fragment
    Button chococakeAdd,chococakeSubract,vanillaAdd,vanillaSubract,blackforestAdd,blackforestSubract,pastrySave;
    TextView chococakeQuantity,chococakePrice,chococake,vanillaQuantity,vanillaPrice,vanilla,blackforestQuantity,blackforestPrice,blackforest;

    //creating an instance of quantityAddSud and orderDatabase
    quantityAddSub quantityAddSub = new quantityAddSub();

    int chococakeCount,vanillaCount,blackforestcount;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name= bundle.getString("name");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pastry, container, false);

        //mapping the variables with the respective IDs
        chococakeAdd= view.findViewById(R.id.Chococake_add);
        chococakeSubract = view.findViewById(R.id.Chococake_sub);
        chococake=view.findViewById(R.id.ChocolateCake);
        chococakePrice=view.findViewById(R.id.Chococake_price);
        chococakeQuantity=view.findViewById(R.id.Chococake_quantity);

        vanillaAdd = view.findViewById(R.id.vanilla_add);
        vanillaSubract = view.findViewById(R.id.vanilla_sub);
        vanilla = view.findViewById(R.id.VanillaCake);
        vanillaPrice = view.findViewById(R.id.vanilla_price);
        vanillaQuantity = view.findViewById(R.id.vanilla_quantity);

        blackforestAdd = view.findViewById(R.id.blackforest_add);
        blackforestSubract = view.findViewById(R.id.blackforest_sub);
        blackforest= view.findViewById(R.id.blackforest);
        blackforestPrice = view.findViewById(R.id.blackforest_price);
        blackforestQuantity = view.findViewById(R.id.blackforest_quantity);

        pastrySave = view.findViewById(R.id.pastrySave);

        //setting onClickListeners for each buttons to increase or decrease number of quantity
        chococakeAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = chococakeQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                chococakeCount = Integer.parseInt(quantity);
                chococakeQuantity.setText(quantity);
            }
        });

        chococakeSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = chococakeQuantity.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                chococakeCount = Integer.parseInt(quantity);
                chococakeQuantity.setText(quantity);
            }
        });

        vanillaAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q =vanillaQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                vanillaCount = Integer.parseInt(quantity);
                vanillaQuantity.setText(quantity);
            }
        });

        vanillaSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q =vanilla.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                vanillaCount = Integer.parseInt(quantity);
                vanilla.setText(quantity);
            }
        });

        blackforestAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = blackforestQuantity.getText().toString();
                String quantity = quantityAddSub.qAdd(q);
                blackforestcount = Integer.parseInt(quantity);
                blackforestQuantity.setText(quantity);
            }
        });

        blackforestSubract.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q = blackforestQuantity.getText().toString();
                String quantity = quantityAddSub.qSubract(q);
                blackforestcount = Integer.parseInt(quantity);
                blackforestQuantity.setText(quantity);
            }
        });

        //storing the orders in the database
        ContentValues orderValues = new ContentValues();
        pastrySave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chococakeCount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,chococake.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,chococakeQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price1 = Integer.parseInt(chococakeQuantity.getText().toString()) * Integer.parseInt(chococakePrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price1));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);

                } if (vanillaCount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,vanilla.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,vanillaQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price2 = Integer.parseInt(vanillaQuantity.getText().toString()) * Integer.parseInt(vanillaPrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price2));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);
                } if (blackforestcount>0) {
                    orderValues.put(OrderContentProvider.NAME,name);
                    orderValues.put(OrderContentProvider.FOODS,blackforest.getText().toString());
                    orderValues.put(OrderContentProvider.QUANTITY,blackforestQuantity.getText().toString());
                    //calculating the price of food with respective to quantity
                    int price3 = Integer.parseInt(blackforestQuantity.getText().toString()) * Integer.parseInt(blackforestPrice.getText().toString());
                    orderValues.put(OrderContentProvider.PRICE,Integer.toString(price3));
                    getContext().getContentResolver().insert(OrderContentProvider.CONTENT_URI, orderValues);

                }else if (chococakeCount==0 && vanillaCount==0 && blackforestcount==0){
                    //below message is toasted if the user does not select the foods
                    Toast.makeText(getContext(),"Please Select pastry",Toast.LENGTH_SHORT).show();
                }
                if(chococakeCount>0 || vanillaCount>0 || blackforestcount>0){
                    //below message is toasted if the user selects the foods
                    Toast.makeText(getContext(),"Pastries saved",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }
}