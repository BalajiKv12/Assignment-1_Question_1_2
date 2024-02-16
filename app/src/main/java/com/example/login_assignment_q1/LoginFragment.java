package com.example.login_assignment_q1;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginFragment extends Fragment {

    Button Login,Signup;
    EditText name, email, password;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        Login = view.findViewById(R.id.login);
        Signup = view.findViewById(R.id.Signup);
        name = view.findViewById(R.id.Name);
        email = view.findViewById(R.id.Email);
        password =view.findViewById(R.id.Password);

        Login.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("Range")
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty()) {
                    Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                } else {
                    String select = "name =? AND email=? AND password=?";
                    String args[] = {userName,userEmail,userPassword};
                    // creating a cursor object of the
                    // content URI
                    Cursor cursor = getContext().getContentResolver().query(MyContentProvider.CONTENT_URI,
                            null, select, args, null);

                    // iteration of the cursor
                    // to print whole table
                    if (cursor.moveToFirst()) {
                        String i, t, a;
                        while (!cursor.isAfterLast()) {
                            i = cursor.getString(cursor.getColumnIndex(MyContentProvider.name));
                            t = cursor.getString(cursor.getColumnIndex(MyContentProvider.email));
                            a = cursor.getString(cursor.getColumnIndex(MyContentProvider.password));
                            if(userName.equals(i) && userEmail.equals(t) && userPassword.equals(a))
                            {
                                Toast.makeText(getContext(),"Welcome Back "+userName,Toast.LENGTH_SHORT).show();
                                Log.e("inside if","check ok");
                                Fragment fragment = new MenuFragment();
                                Bundle bundle = new Bundle();
                                bundle.putString("name",i);
                                fragment.setArguments(bundle);
                                getActivity().getSupportFragmentManager().beginTransaction()
                                        .replace(R.id.fragementContainer,fragment).commit();
                                break;
                            }

                            cursor.moveToNext();
                        }
                    }else{
                        Toast.makeText(getContext(),"Entered details is wrong, Please Try Again "+userName,Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userEmail = email.getText().toString();
                String userPassword = password.getText().toString();
                if (userName.isEmpty() || userEmail.isEmpty() || userPassword.isEmpty())
                {
                    Toast.makeText(getContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
                }
                else{
                    // class to add values in the database
                    ContentValues values = new ContentValues();

                    // fetching text from user
                    values.put(MyContentProvider.name,userName);
                    values.put(MyContentProvider.email,userEmail);
                    values.put(MyContentProvider.password,userPassword);

                    // inserting into database through content URI
                    getContext().getContentResolver().insert(MyContentProvider.CONTENT_URI, values);
                    Toast.makeText(getContext(),"Welcome "+userName,Toast.LENGTH_SHORT).show();
                    Fragment fragment = new MenuFragment();
                    Bundle bundle = new Bundle();
                    bundle.putString("name",userName);
                    fragment.setArguments(bundle);
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragementContainer, fragment).commit();
                }
            }
        });
        return view;
    }

}