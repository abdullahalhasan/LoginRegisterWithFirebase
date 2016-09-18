package com.abdullah.firebasepractice;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignIn extends AppCompatActivity {

    private EditText emailSigninET, passwordSigninET;
    private String email,password;
    private TextView signinTV;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        emailSigninET = (EditText) findViewById(R.id.emailSigninET);
        passwordSigninET = (EditText) findViewById(R.id.passwordSigninET);
        signinTV = (TextView) findViewById(R.id.signupTV);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        if (firebaseAuth.getCurrentUser()!=null) {
            finish();
            startActivity(new Intent(SignIn.this,Home.class));
        }

        signinTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });
    }

    public void login(View view) {
        email = emailSigninET.getText().toString().trim();
        password = passwordSigninET.getText().toString().trim();

        if (email.isEmpty()||password.isEmpty()) {
            Toast.makeText(SignIn.this, "Fill up the empty field!!", Toast.LENGTH_SHORT).show();
            return;
        } else {

            progressDialog.setMessage("Verifying Email ...");
            progressDialog.show();

            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                finish();
                                startActivity(new Intent(SignIn.this,Home.class));
                                Toast.makeText(SignIn.this, "Congratulation! Successfully Signed In !!", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(SignIn.this, "Failed! Check Network Connecion!!", Toast.LENGTH_SHORT).show();
                            }

                            progressDialog.dismiss();
                        }
                    });

        }
    }
}
