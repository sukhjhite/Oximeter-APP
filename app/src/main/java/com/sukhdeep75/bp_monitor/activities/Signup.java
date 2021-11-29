package com.sukhdeep75.bp_monitor.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.sukhdeep75.bp_monitor.R;
import com.sukhdeep75.bp_monitor.ui.main.Users;

public class Signup extends AppCompatActivity {

    Button create;
    EditText username_box,password_box,name_box;
    FirebaseAuth auth;
    FirebaseFirestore database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Button create=findViewById(R.id.create_btn);
        EditText username_box = findViewById(R.id.create_username);
        EditText name_box = findViewById(R.id.create_name);
        EditText password_box = findViewById(R.id.create_password);
        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password,name;
                email = username_box.getText().toString();
                password = password_box.getText().toString();
                name = name_box.getText().toString();

                Users users = new Users();
                users.setName(name);
                users.setUsername(email);
                users.setPass(password);
                Intent intent = new Intent(Signup.this,Home.class);
                intent.putExtra("name",users.getName());

                auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            database.collection("Users")
                                    .document(name).set(users).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(Signup.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
                                  startActivity(new Intent(Signup.this,Login.class));
                                }
                            });

                        }
                        else{
                            Toast.makeText(Signup.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}