package com.sokoloma777.mynewapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import java.util.ArrayList;


public class MyRecyclerAdapter extends Adapter<MyRecyclerAdapter.myViewHolder> {
    private ArrayList<ListItem> myArray;
    private Context viewContext;
    private int category_index;

    public MyRecyclerAdapter(ArrayList<ListItem> array, int category_index, Context viewContext) {
        this.myArray = array;
        this.category_index = category_index;
        this.viewContext = viewContext;
    }

    class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivPerson;
        TextView tvPerson;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPerson = itemView.findViewById(R.id.ivPerson);
            tvPerson = itemView.findViewById(R.id.tvPerson);
            itemView.setOnClickListener(this);
        }

        public void bind(@NonNull ListItem listItem) {
            ivPerson.setImageResource(listItem.iv);
            tvPerson.setText(listItem.tv);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(viewContext, ContentActivity.class);
            intent.putExtra("position", position);
            intent.putExtra("category", category_index);
            viewContext.startActivity(intent);
        }
    }

   /* class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPerson = itemView.findViewById(R.id.ivPerson);
        private TextView tvPerson = itemView.findViewById(R.id.tvPerson);
        private ListItem myListItem;
        private Context myContext;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(@NonNull ListItem listItem, Context context) {
            this.myListItem = listItem;
            this.myContext = context;
            ivPerson.setImageResource(listItem.iv);
            tvPerson.setText(listItem.tv);
        }
    } */

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewContext);
        View view = inflater.inflate(R.layout.item_main, parent,false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        ListItem listItem = myArray.get(position);
        holder.bind(listItem);
    }

    @Override
    public int getItemCount() {
        return myArray.size();
    }

    public void updateAdapter(ArrayList<ListItem> newArray, int category) {
        myArray.clear();
        myArray.addAll(newArray);
        notifyDataSetChanged();
        category_index = category;
    }
}