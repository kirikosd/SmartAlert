package com.kirikos.smartalert.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.kirikos.smartalert.R;
import com.kirikos.smartalert.employee.InspectCasesActivity;
import com.kirikos.smartalert.user.UserHomePageActivity;

public class SignInActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String email;
    private String password;
    private EditText emailEditText;
    private EditText passwordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

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
    public void signIn(View view) {
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();

        if (email.isEmpty()){
            Toast.makeText(getApplicationContext(), "Το email είναι κενό!", Toast.LENGTH_LONG).show();
            return;
        }
        if (password.isEmpty()){
            Toast.makeText(getApplicationContext(), "Το password είναι κενό!", Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Log.d("t", "LOGIN COMPLETED");
                            Toast.makeText(getApplicationContext(), "Επιτυχής είσοδος", Toast.LENGTH_LONG).show();
                            updateUI(user);
                        } else {
                            Log.d("t", "LOGIN NOT COMPLETED");
                            // If sign in fails, display a message to the user.
                            Toast.makeText(getApplicationContext(), "Η είσοδος απέτυχε", Toast.LENGTH_LONG).show();
//                            updateUI(null);
                        }
                    }
                });
    }
    public void updateUI(FirebaseUser user){
        if (user.getEmail().equals("admin@gmail.com")) {
            Intent intent = new Intent(this, InspectCasesActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, UserHomePageActivity.class);
            startActivity(intent);
        }
    }
    public void goToSignUp(View view) {
        finish();
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}