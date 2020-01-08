package com.michael.pan.foodorder.ViewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.michael.pan.foodorder.Interface.ItemClickListener;
import com.michael.pan.foodorder.R;

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


	public TextView txtMenuName;
	public ImageView imageView;
	private ItemClickListener clickListener;
	public MenuViewHolder(@NonNull View itemView) {
		super(itemView);
		txtMenuName = itemView.findViewById(R.id.menu_name);
		imageView = itemView.findViewById(R.id.menu_image);
		itemView.setOnClickListener(this);
	}

	public void setClickListener(ItemClickListener clickListener) {
		this.clickListener = clickListener;
	}

	@Override
	public void onClick(View v) {
		clickListener.onClick(v, getAdapterPosition(), false);
	}
}
