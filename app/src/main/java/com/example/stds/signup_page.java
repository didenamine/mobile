package com.example.stds;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class signup_page extends AppCompatActivity {
    int erros = 0 ;
    DBhelper DB = new DBhelper(this) ;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_page);
        // Initialize Firebase Authentication
        FirebaseApp.initializeApp(this);
        mAuth = FirebaseAuth.getInstance();
        EditText  sign_username = findViewById(R.id.signup_page_username);
        EditText sign_email = findViewById(R.id.signup_page_email);
        EditText sign_password = findViewById(R.id.signup_page_password);
        EditText sign_password2 = findViewById(R.id.signup_page_password2);
        Button sign_button = findViewById(R.id.signup_page_sign);

        ///edit text constraints

        sign_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (sign_username.getText().toString().equals("")){
                    sign_username.setError("Empty name");
                }
            }
        });


        sign_email.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT) {
                    sign_password.requestFocus(); // move focus to next EditText
                    return true;
                }
                return false;
            }
        });


        sign_password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (sign_email.getText().toString().equals("")|sign_email.getText().toString().indexOf("@")==-1){
                    sign_email.setError("Wrong email");
                }
            }
        });
        sign_password2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (sign_password.getText().toString().length()<4)
                {
                    sign_password.setError("Weak password");
                }
            }
        });



        sign_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = sign_username.getText().toString();
                String email = sign_email.getText().toString();
                String password = sign_password.getText().toString();
                String password2 = sign_password2.getText().toString();

                // Validate input
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(email) ||
                        TextUtils.isEmpty(password) || TextUtils.isEmpty(password2)) {
                    Toast.makeText(signup_page.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(password2)) {
                    Toast.makeText(signup_page.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create Firebase user
                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signup_page.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Firebase user creation successful
                                    FirebaseUser user = mAuth.getCurrentUser();

                                    // Insert user data into local SQLite database
                                    DB.insertData(username, email, password);

                                    // Insert user data into Firebase Realtime Database
                                    FirebaseDatabase.getInstance().getReference().child("users").child(user.getUid())
                                            .setValue(new User(username, email))
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        // Navigation to login page activity
                                                        Intent intent = new Intent(getApplicationContext(), login_page.class);
                                                        Toast.makeText(signup_page.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                                        startActivity(intent);
                                                    } else {
                                                        Toast.makeText(signup_page.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                } else {
                                    // Firebase user creation failed
                                    Toast.makeText(signup_page.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });





    }
}