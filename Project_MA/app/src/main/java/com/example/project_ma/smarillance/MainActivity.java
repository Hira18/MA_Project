package com.example.project_ma.smarillance;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project_ma.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private Button loginbtn;
    private TextView signup;
    private EditText email,pass;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
   // private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_main);
        loginbtn=(Button)findViewById(R.id.btn_login);
        signup=(TextView)findViewById(R.id.link_signup);
        email=(EditText)findViewById(R.id.input_email);
        pass=(EditText)findViewById(R.id.input_password);
       firebaseAuth = FirebaseAuth.getInstance();
        progressDialog= new ProgressDialog(this);
        FirebaseUser user= firebaseAuth.getCurrentUser();// firebase code to check, Is user signin or signout?.
        if (user!= null)
        {
            finish();//default method.
            Intent intent=new Intent(this,dashboard.class);
            startActivity(intent);
            //refer to new Activity
        }

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(email.getText().toString(), pass.getText().toString());

            }
        });







        /*login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),dashboard.class);
                startActivity(intent);


            }});*/

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent= new Intent(getApplicationContext(),signup.class);
                startActivity(intent);


            }});


    }
    public void login(String id, String pass) {
        if (id.equals("") && pass.equals("")) {
            Toast.makeText(getApplicationContext(), "Fill Required Fields", Toast.LENGTH_LONG).show();
        }

        progressDialog.setMessage("Validating Credentials...");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(id, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Login Successful.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), dashboard.class);
                    startActivity(intent);
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Invalid Credentials OR Check Internet", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }}
