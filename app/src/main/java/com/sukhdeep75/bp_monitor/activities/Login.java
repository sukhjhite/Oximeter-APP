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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sukhdeep75.bp_monitor.R;

public class Login extends AppCompatActivity {
    Button login,signup;
    EditText username,password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.login_btn);
        Button signup = findViewById(R.id.create_acc);
        EditText username = findViewById(R.id.username);
        EditText password = findViewById(R.id.password);

        auth = FirebaseAuth.getInstance();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            String uname = username.getText().toString();
            String pass = password.getText().toString();

            if(uname.isEmpty() || pass.isEmpty()){
                Toast.makeText(Login.this, "Username and password fields are empty", Toast.LENGTH_SHORT).show();
            }
            else{
            auth.signInWithEmailAndPassword(uname,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                  if(task.isSuccessful()){
                      Toast.makeText(Login.this, "Logged in successfully!", Toast.LENGTH_LONG).show();
                      {
                          startActivity(new Intent(Login.this, Home.class));
                      }

                  }
                  else{
                      Toast.makeText(Login.this,task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                  }
                }
            });

            }}
        });




    }
}