package com.abdullah.firebasepractice;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Home extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private TextView homeTV;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        homeTV = (TextView) findViewById(R.id.homeTextView);
        user = firebaseAuth.getCurrentUser();
        homeTV.setText("Welcome "+user.getEmail());
    }

    public void signOut(View view) {
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(getApplicationContext(),SignIn.class));
    }

}
