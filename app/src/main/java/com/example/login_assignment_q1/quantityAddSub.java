package com.example.login_assignment_q1;

public class quantityAddSub {

    public String qAdd(String q){
        int quantity = Integer.parseInt(q);
        quantity++;
        q  = Integer.toString(quantity);
        return q;
    }

    public String qSubract(String q){
        int quantity = Integer.parseInt(q);
        if(quantity>0) {
            quantity--;
        }
        q =Integer.toString(quantity);
        return q;
    }
}
