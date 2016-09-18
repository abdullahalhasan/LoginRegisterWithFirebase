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

public class SignUp extends AppCompatActivity {

    private EditText emailET,passwordET;

    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private String email,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailET = (EditText) findViewById(R.id.emailSignupET);
        passwordET = (EditText) findViewById(R.id.passwordSignupET);

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser()!=null) {
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
        }



    }

    public void register(View view) {
        email = emailET.getText().toString().trim();
        password = passwordET.getText().toString().trim();

        if (email.isEmpty()||password.isEmpty()) {
            Toast.makeText(SignUp.this, "Fill up the empty field!!", Toast.LENGTH_SHORT).show();
            return;
        } else {
            progressDialog.setMessage("Registering User ...");
            progressDialog.show();
            firebaseAuth.createUserWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(SignUp.this, "Congratulation! Registration Successgull!!", Toast.LENGTH_SHORT).show();
                                finish();
                                startActivity(new Intent(getApplicationContext(),SignIn.class));
                            } else {
                                Toast.makeText(SignUp.this, "Failed! Check Network Connecion!!", Toast.LENGTH_SHORT).show();
                            }
                            emailET.getText().clear();
                            passwordET.getText().clear();
                            progressDialog.dismiss();
                        }
                    });


        }
    }
}
