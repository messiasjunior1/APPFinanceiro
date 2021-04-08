package com.utfpr.APPFinanceiro.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.utfpr.APPFinanceiro.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mFirebaseAuth;
    EditText emailId, password;
    Button btnSignUp;
    TextView tvSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.username);
        password = findViewById(R.id.password);
        btnSignUp = findViewById(R.id.register);
        tvSignIn = findViewById(R.id.logintv);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                if(email.isEmpty()){
                    emailId.setError("Forneça um email");
                    emailId.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Forneça uma senha");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty()){
                    Toast.makeText(com.utfpr.APPFinanceiro.activity.MainActivity.this,"Por favor, verifique os campos!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(com.utfpr.APPFinanceiro.activity.MainActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(com.utfpr.APPFinanceiro.activity.MainActivity.this,"Por favor tente novamente",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(com.utfpr.APPFinanceiro.activity.MainActivity.this, HomeActivity.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(com.utfpr.APPFinanceiro.activity.MainActivity.this,"Tente novamente!",Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.utfpr.APPFinanceiro.activity.MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
    }
}