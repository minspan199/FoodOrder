package com.michael.pan.foodorder;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

	Button btnSignIn, btnSignUp;
	TextView txtSlogan;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnSignIn = findViewById(R.id.btnSignIn);
		btnSignUp = findViewById(R.id.btnSignUp);
		txtSlogan = findViewById(R.id.txtSlogan);
		Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Exo2-Regular.ttf");
		txtSlogan.setTypeface(typeface);
		btnSignIn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});
	}
}
