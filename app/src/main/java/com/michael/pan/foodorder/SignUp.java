package com.michael.pan.foodorder;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.michael.pan.foodorder.Model.User;
import com.rengwuxian.materialedittext.MaterialEditText;

public class SignUp extends AppCompatActivity {

	MaterialEditText edtPhone2, edtPassword2, edtName2;
	Button btnSignUp2;
	ProgressBar progressBar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		edtName2 = findViewById(R.id.edtName2);
		edtPassword2 = findViewById(R.id.edtPassword2);
		edtPhone2 = findViewById(R.id.edtPhone2);
		btnSignUp2 = findViewById(R.id.btnSignUp2);
		progressBar = findViewById(R.id.progressBar2);
		//Init Firebase
		final FirebaseDatabase database = FirebaseDatabase.getInstance();
		final DatabaseReference table_user = database.getReference("User");
		btnSignUp2.setOnClickListener(v -> {
			progressBar.setVisibility(View.VISIBLE);
			table_user.addValueEventListener(new ValueEventListener() {
				@Override
				public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
					//Check if user phone already exist
					if (edtPhone2.getText() != null){
						if (dataSnapshot.child(edtPhone2.getText().toString()).exists()){
							progressBar.setVisibility(View.GONE);
							Toast.makeText(SignUp.this, "Phone registered in database.", Toast.LENGTH_SHORT).show();
						}else {
							User user = new User(edtName2.getText().toString(), edtPassword2.getText().toString());
							table_user.child(edtPhone2.getText().toString()).setValue(user);
							Toast.makeText(SignUp.this, "Phone registered.", Toast.LENGTH_SHORT).show();
							finish();
						}
					}
				}

				@Override
				public void onCancelled(@NonNull DatabaseError databaseError) {

				}
			});
		});
	}
}
