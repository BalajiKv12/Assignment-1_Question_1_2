package com.example.login_assignment_q1;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;



import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;


public class MenuFragment extends Fragment {

    //creating variables for cardView and button
    CardView beverages,snacks,pastry;
    Button Checkout;
    @SuppressLint("MissingInflatedId")
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        String name = bundle.getString("name");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_menu, container, false);
        //mapping the ID's with variables
        beverages = view.findViewById(R.id.BeverageCard);
        snacks = view.findViewById(R.id.SnacksCard);
        pastry = view.findViewById(R.id.PastryCard);

        Checkout = view.findViewById(R.id.Checkout);

        //setting on clickListener for all cardViews
        beverages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating an instance of BeverageFragment
                Fragment fragment = new BeverageFragment();
                fragment.setArguments(bundle);
                //adding fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragementContainer_itemView,fragment).addToBackStack(null).commit();
            }
        });

        snacks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating an instance of SnacksFragment
                Fragment fragment = new SnacksFragment();
                fragment.setArguments(bundle);
                //adding fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragementContainer_itemView,fragment).addToBackStack(null).commit();
            }
        });

        pastry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //creating an instance of PastryFragment
                Fragment fragment = new PastryFragment();
                fragment.setArguments(bundle);
                //adding fragment
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragementContainer_itemView,fragment).addToBackStack(null).commit();
            }
        });

        Checkout.setOnClickListener(new View.OnClickListener() {
            @SuppressLint({"Range", "SetTextI18n"})
            @Override
            public void onClick(View view) {
                //initializing cursor to get order details from database
                Cursor cursor = getContext().getContentResolver().query(OrderContentProvider.CONTENT_URI,
                        null,null,null, null);

                String name = "";
                String headString = "NAME----------QUANTITY----------PRICE";
                int totalPrice =0;
                // iteration of the cursor
                // to print whole table
                if(cursor.moveToFirst()) {
                    StringBuilder strBuild=new StringBuilder();
                    
                    while (!cursor.isAfterLast()) {
                        strBuild.append("\n").
                                append(cursor.getString(cursor.getColumnIndex(OrderContentProvider.FOODS))).
                                append("----------").append(cursor.getString(cursor.getColumnIndex(OrderContentProvider.QUANTITY))).
                                append("----------").append(cursor.getString(cursor.getColumnIndex(OrderContentProvider.PRICE)));
                        name = cursor.getString(cursor.getColumnIndex(OrderContentProvider.NAME));
                        totalPrice = totalPrice + Integer.parseInt(cursor.getString(cursor.getColumnIndex(OrderContentProvider.PRICE)));
                        cursor.moveToNext();
                    }
                    //creating a AlertDialog box to print the order details
                    AlertDialog.Builder infoDialog = new AlertDialog.Builder(getContext());
                    infoDialog.setTitle("OrderInfo");
                    //creating a textview in the dialog box
                    final TextView info = new TextView(getContext());
                    infoDialog.setView(info);
                    //setting text size of textview
                    info.setTextSize(18f);
                    //setting the order details to the textView
                    info.setText(headString+"\n"+strBuild+"\n\n Total price ---- "+totalPrice +"\n\nThank You "+name);

                    //creating a Positive response button to close the dialog box
                    infoDialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //deleting order details from database after user conforming the order
                            getContext().getContentResolver().delete(OrderContentProvider.CONTENT_URI,null,null);
                            infoDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                                @Override
                                public void onCancel(DialogInterface dialog) {
                                    dialog.cancel();
                                }
                            });
                        }
                    });
                    //creating and displaying the Alert dialog box
                    AlertDialog infoDialog_info = infoDialog.create();
                    infoDialog_info.show();
                }
                else {
                    //if database is empty beloe message is toasted
                    Toast.makeText(getContext(),"No Orders Found",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

}