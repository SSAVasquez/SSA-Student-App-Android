package com.fuady.ssa_app_redo1;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by fuady on 11/20/2017.
 */

public class signInActivity extends AppCompatActivity implements View.OnClickListener {

private Button Login;
private EditText Email;
private EditText Password;

private ProgressDialog progressDialog;

private FirebaseAuth firebaseAuth;


@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);
       // System.out.println("opening sign in");
       // Toast.makeText(signInActivity.this, "opening sign in",Toast.LENGTH_SHORT).show();
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        progressDialog = new ProgressDialog(this);

        Login = (Button) findViewById(R.id.Login);
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);

        Login.setOnClickListener(this);

        }

private void loginUser(){
        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
        //email is empty
        Toast.makeText(this, "Please enter your email", Toast.LENGTH_SHORT).show();
        return;
        }

        if(TextUtils.isEmpty(password)){
        //password is empty
        Toast.makeText(this, "Please enter your password", Toast.LENGTH_SHORT).show();
        return;
        }
        progressDialog.setMessage("Loging you in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
@Override
public void onComplete(@NonNull Task<AuthResult> task) {
        if(task.isSuccessful()){
        Toast.makeText(signInActivity.this, "Logged in Successfully",Toast.LENGTH_SHORT).show();
        finish();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));

        }else{
        Toast.makeText(signInActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
        }
        }
        });

        }

@Override
public void onClick (View view) {
        if((view) == Login){
        loginUser();
        }
        }

        }
