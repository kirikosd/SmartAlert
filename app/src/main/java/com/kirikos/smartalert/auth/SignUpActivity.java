package com.kirikos.smartalert.auth;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.user.UserHomePageActivity;

public class SignUpActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private EditText emailEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);

        mAuth = FirebaseAuth.getInstance();
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    public void signUp(View view){
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Συμπληρώστε όλα τα πεδία!", Toast.LENGTH_LONG).show();
        } else {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(getApplicationContext(), "Η εγγραφή ολοκληρώθηκε με επιτυχία", Toast.LENGTH_LONG).show();
                                updateUI();
                            } else {
                                task.getException();
                                // If sign in fails, display a message to the user.
                                Toast.makeText(getApplicationContext(), "Η εγγραφή απέτυχε", Toast.LENGTH_LONG).show();
//                            updateUI(null);
                            }
                        }
                    });
        }
    }
    public void updateUI(){
        Intent intent = new Intent(this, UserHomePageActivity.class);
        startActivity(intent);
    }
    public void goToSignIn(View view) {
        finish();
        Intent intent = new Intent(this, SignInActivity.class);
        startActivity(intent);
    }
}