package com.template.simplerecyclerviewjava;

import android.icu.text.NumberFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    public final List<Item> items;
    private OnItemClickListener listener;

    public MyAdapter(ArrayList<Item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Item currentItem = items.get(position);
        holder.imageView.setImageResource(currentItem.getImageResource());
        holder.textTitle.setText(currentItem.getTitle());
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance();
        holder.textTotalPrice.setText(numberFormat.format(currentItem.getPrice() * currentItem.getAmount()));
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    public void updateItem(int position) {
        Item updateItem = items.get(position);
        updateItem.setTitle("update: " + updateItem.getTitle());
        notifyItemChanged(position);
    }

    public void insertItem(int index, Item newItem) {
        items.add(index, newItem);
        notifyItemInserted(index); // 変更がある箇所だけ差分更新する.
        // notifyDataSetChanged(); // これだとリスト全体の更新が走る.
    }

    public void removeItem(int index) {
        items.remove(index);
        notifyItemRemoved(index);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textTitle;
        private final TextView textTotalPrice;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            textTitle = itemView.findViewById(R.id.text_title);
            textTotalPrice = itemView.findViewById(R.id.text_total_price);
            itemView.setOnClickListener((view) -> listener.onItemClick(getAdapterPosition()));
        }
    }

    interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener l) {
        this.listener = l;
    }
}
