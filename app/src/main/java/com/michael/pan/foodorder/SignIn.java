package com.michael.pan.foodorder;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.michael.pan.foodorder.Common.Common;
import com.michael.pan.foodorder.Model.User;

public class SignIn extends AppCompatActivity {

	EditText edtPhone, edtPassword;
	Button btnSignIn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_in);
		Toolbar toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		edtPassword = findViewById(R.id.edtPassword);
		edtPhone = findViewById(R.id.edtPhone);
		btnSignIn = findViewById(R.id.btnSignIn2);
		final ProgressBar progressBar = findViewById(R.id.progressBar);

		//Init Firebase
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		final DatabaseReference table_user = database.getReference("User");

		btnSignIn.setOnClickListener(v -> {
			progressBar.setVisibility(View.VISIBLE);
			table_user.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
					//Get user info
					progressBar.setVisibility(View.GONE);
					//Check if user exists in the database
					if (dataSnapshot.child(edtPhone.getText().toString()).exists()){
						// Get Post object and use the values to update the UI
						User user = dataSnapshot.child(edtPhone.getText().toString()).getValue(User.class);
						if (user.getPassword().equals(edtPassword.getText().toString())){
							Toast.makeText(SignIn.this, "Sign in sucessfully", Toast.LENGTH_SHORT).show();
							Intent homeIntent = new Intent(SignIn.this, Home.class);
							Common.currentUser = user;
							startActivity(homeIntent);
							finish();
						} else {
							Toast.makeText(SignIn.this, "Incorrect password!", Toast.LENGTH_SHORT).show();
						}
					} else {
						Toast.makeText(SignIn.this, "User does not exist!", Toast.LENGTH_SHORT).show();
					}

				}

				@Override
				public void onCancelled(@NonNull DatabaseError databaseError) {
// Getting Post failed, log a message
					Log.w(SignIn.class.getSimpleName(), "loadPost:onCancelled", databaseError.toException());
					// ...
				}
			});
		});
	}

}
