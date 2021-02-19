package com.template.simplerecyclerviewjava;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private MyAdapter adapter = null;
    private final List<OrderItem> resources = new ArrayList<>(
            Arrays.asList(
                    new OrderItem(R.drawable.ic_emoji_food_beverage, "beverage", 1000, 1),
                    new OrderItem(R.drawable.ic_cake, "cake", 1000, 3),
                    new OrderItem(R.drawable.ic_fastfood, "fastFood", 1000, 5),
                    new OrderItem(R.drawable.ic_food_bank, "foodBank", 1000, 10)
            )
    );

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new MyAdapter(generateDummyItems(10));
        recyclerView.setAdapter(adapter);
        displayTotalPrice();

        adapter.setOnItemClickListener(position -> {
            showToast("$position Item Clicked!!");
            updateItem(position);
        });
    }

    private void displayTotalPrice() {
        int totalPrice = 0;
        for (Item item : adapter.items) {
            totalPrice += item.getPrice() * item.getAmount();
        }
        NumberFormat format = NumberFormat.getCurrencyInstance();
        String totalPriceStr = format.format(totalPrice);
        TextView textTotalPrice = findViewById(R.id.total_price);
        textTotalPrice.setText(totalPriceStr);
    }

    private void updateItem(int position) {
        if (adapter == null) return;
        adapter.updateItem(position);
    }

    public void insertItem(View view) {
        if (adapter == null) return;

        int until;
        if (adapter.items.size() == 0) {
            until = 1;
        } else {
            until = adapter.items.size();
        }
        int index = new Random().nextInt(until);
        int resIndex = new Random().nextInt(resources.size());
        Item newItem = new Item(
                resources.get(resIndex).getImageResource(),
                "insert: " + resources.get(resIndex).getTitle(),
                resources.get(resIndex).getPrice(),
                resources.get(resIndex).getAmount()
        );
        adapter.insertItem(index, newItem);
        displayTotalPrice();
        Log.d("aaa", "title: " + resources.get(resIndex).getTitle() + " : index: " + index);
    }

    public void removeItem(View view) {
        if (adapter == null) return;

        if (adapter.items.size() == 0) {
            showToast("list is empty and cannot be deleted.");
            return;
        }
        int index = new Random().nextInt(adapter.items.size());
        adapter.removeItem(index);
        displayTotalPrice();
        Log.d("aaa", "index: " + index);
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private ArrayList<Item> generateDummyItems(int size) {
        ArrayList<Item> items = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int resIndex = new Random().nextInt(resources.size());
            items.add(
                    new Item(
                            resources.get(resIndex).getImageResource(),
                            resources.get(resIndex).getTitle(),
                            resources.get(resIndex).getPrice(),
                            resources.get(resIndex).getAmount()
                    )
            );
        }
        return items;
    }
}