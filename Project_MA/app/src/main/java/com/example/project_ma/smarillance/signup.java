package com.example.project_ma.smarillance;

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

public class signup extends AppCompatActivity {

    private Button signupbtn;
    private EditText newuser,newpass;
    private TextView loginpage;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_signup);

        int btn_signup = 0;
        signupbtn=(Button)findViewById(btn_signup);
        newuser=(EditText) findViewById(R.id.input_newemail);
        newpass=(EditText)findViewById(R.id.input_newpassword);
        loginpage=(TextView)findViewById(R.id.link_login);
        //firebase code
        firebaseAuth= FirebaseAuth.getInstance();//firebase

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //upload data to firebase database
                //validation
                if(newuser.getText().toString().matches("")||newpass.getText().toString().matches(""))
                {
                    Toast.makeText(getApplicationContext(),"Fill Required Fields.", Toast.LENGTH_LONG).show();
                }
                else{
                    String user_id=newuser.getText().toString().trim();
                    String user_pass=newpass.getText().toString().trim();
                    //firebase
                    firebaseAuth.createUserWithEmailAndPassword(user_id,user_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(), "Registration Successful.", Toast.LENGTH_LONG).show();
                                firebaseAuth.signOut();
                                finish();
                                startActivity(new Intent(signup.this,MainActivity.class));


                            }
                            else{
                                Toast.makeText(getApplicationContext(), "Internet Connectivity Failed.", Toast.LENGTH_SHORT).show();
                            }



                        }
                    });//firebase


                }}
        });


}}
