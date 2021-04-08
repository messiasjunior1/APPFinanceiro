package com.utfpr.APPFinanceiro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.utfpr.APPFinanceiro.R;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.firebase.auth.FirebaseAuth;
import com.utfpr.APPFinanceiro.activity.MainActivity;

public class HomeActivity extends AppCompatActivity {
    Button btnLogout, btnSaldo;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnLogout = findViewById(R.id.logout);
        btnSaldo = findViewById(R.id.btnSaldo);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(com.utfpr.APPFinanceiro.activity.HomeActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });

        btnSaldo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intToSaldo = new Intent(com.utfpr.APPFinanceiro.activity.HomeActivity.this, BalanceActivity.class);
                startActivity(intToSaldo);
            }
        });
    }
}