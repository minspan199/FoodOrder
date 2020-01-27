package com.michael.pan.foodorder;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.michael.pan.foodorder.Common.Common;
import com.michael.pan.foodorder.Interface.ItemClickListener;
import com.michael.pan.foodorder.Model.Category;
import com.michael.pan.foodorder.ViewHolder.MenuViewHolder;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

	private AppBarConfiguration mAppBarConfiguration;
	FirebaseDatabase database;
	DatabaseReference category;
	TextView txtFullName;
	RecyclerView recycler_menu;
	RecyclerView.LayoutManager layoutManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Toolbar toolbar = findViewById(R.id.toolbar);
		toolbar.setTitle("Menu");
		setSupportActionBar(toolbar);

		//Init Firebase
		database = FirebaseDatabase.getInstance();
		category = database.getReference("Category");
		FloatingActionButton fab = findViewById(R.id.fab);
		fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
			.setAction("Action", null).show());
		DrawerLayout drawer = findViewById(R.id.drawer_layout);
		NavigationView navigationView = findViewById(R.id.nav_view);
		navigationView.setNavigationItemSelectedListener(this);

		// Passing each menu ID as a set of Ids because each
		// menu should be considered as top level destinations.
//		mAppBarConfiguration = new AppBarConfiguration.Builder(
//			R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
//			R.id.nav_tools, R.id.nav_share, R.id.nav_send)
//			.setDrawerLayout(drawer)
//			.build();
//		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//		NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
//		NavigationUI.setupWithNavController(navigationView, navController);

		//Set name for user
		View headerView = navigationView.getHeaderView(0);
		txtFullName = headerView.findViewById(R.id.txtFullName);
		txtFullName.setText(Common.currentUser.getName());

		//Load Menu
		recycler_menu = findViewById(R.id.recycler_view);
		recycler_menu.setHasFixedSize(true);
		layoutManager = new LinearLayoutManager(this);
		recycler_menu.setLayoutManager(layoutManager);
		loadMenu();

	}

	private void loadMenu() {
		FirebaseRecyclerAdapter<Category, MenuViewHolder> adapter =
			new FirebaseRecyclerAdapter<Category, MenuViewHolder>(Category.class, R.layout.menu_item, MenuViewHolder.class, category) {
				@Override
				protected void populateViewHolder(MenuViewHolder menuViewHolder, Category category, int i) {
					menuViewHolder.txtMenuName.setText(category.getName());
					Picasso.get().load(category.getImage()).into(menuViewHolder.imageView);
					final Category clickItem = category;
					menuViewHolder.setClickListener((view, position, isLongClick) -> Toast.makeText(Home.this, ""+clickItem.getName(), Toast.LENGTH_SHORT).show());
				}
		};
		recycler_menu.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
		int id = menuItem.getItemId();
		if (id == R.id.nav_cart){

		} else if (id == R.id.nav_menu){

		} else if (id == R.id.nav_log_out){

		} else if (id == R.id.nav_orders){

		}
		return true;
	}

//	@Override
//	public boolean onSupportNavigateUp() {
//		NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
//		return NavigationUI.navigateUp(navController, mAppBarConfiguration)
//			|| super.onSupportNavigateUp();
//	}
}
