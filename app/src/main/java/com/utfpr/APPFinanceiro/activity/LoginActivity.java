package com.utfpr.APPFinanceiro.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.utfpr.APPFinanceiro.R;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    EditText emailId, password;
    Button btnSignIn;
    TextView tvSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnSignIn = findViewById(R.id.login);
        tvSignUp = findViewById(R.id.textView);

        mAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser = mFirebaseAuth.getCurrentUser();
                if( mFirebaseUser != null ){
                    Toast.makeText(com.utfpr.APPFinanceiro.activity.LoginActivity.this,"Você está conectado",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(com.utfpr.APPFinanceiro.activity.LoginActivity.this, HomeActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(com.utfpr.APPFinanceiro.activity.LoginActivity.this,"Tente novamente",Toast.LENGTH_SHORT).show();
                }
            }
        };


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if (email.isEmpty()){
                    emailId.setError("Por favor, forneça um e-mail");
                    emailId.requestFocus();
                } else if (pwd.isEmpty()) {
                    password.setError("Por favor, forneça uma senha");
                    password.requestFocus();
                } else if (email.isEmpty() && pwd.isEmpty()) {
                    Toast.makeText(com.utfpr.APPFinanceiro.activity.LoginActivity.this, "Existem campos não preenchidos!", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && pwd.isEmpty())) {
                    mFirebaseAuth.signInWithEmailAndPassword(email, pwd).addOnCompleteListener(com.utfpr.APPFinanceiro.activity.LoginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(com.utfpr.APPFinanceiro.activity.LoginActivity.this, "Um erro aconteceu, por favor tente novamente.", Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intToHome = new Intent(com.utfpr.APPFinanceiro.activity.LoginActivity.this, HomeActivity.class);
                                startActivity(intToHome);
                            }
                        }
                    });
                }
            }
        });

        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intSignUp = new Intent(com.utfpr.APPFinanceiro.activity.LoginActivity.this, com.utfpr.APPFinanceiro.activity.MainActivity.class);
                startActivity(intSignUp);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
    }
}