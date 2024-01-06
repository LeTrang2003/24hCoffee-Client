package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ItemCaterogyBinding;
import com.example.a24h_coffee_client.model.Category;
import com.example.a24h_coffee_client.view.fragment.home.HomeContract;

import java.util.List;

public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.ViewHolder> {
    List<Category> mList ;
    HomeContract.View view;

    private int selectedItem = 0; // vị trí chọn item
    Category category = new Category(0,"Tất cả");
    public AdapterCategory(List<Category> list, HomeContract.View view ) {
        this.mList = list;
        this.view = view;
        mList.add(0,category);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemCaterogyBinding binding = ItemCaterogyBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Category category = mList.get(position);
        if (category == null){
            return;
        }
        holder.bind(category);

        holder.binding.btnTypeProduct.setOnClickListener(view1 -> {
            view.onItemClickListener(category.getId());
            selectedItem = holder.getAdapterPosition();
            notifyDataSetChanged();
        });

        // item được chọn
        holder.binding.btnTypeProduct.setBackgroundResource(
                position == selectedItem ? R.drawable.bg_select_category : R.drawable.bg_no_select_category
        );

        // item ko được chọn
        holder.binding.tvTypeProduct.setTextColor(ContextCompat.getColor(holder.binding.getRoot().getContext(),
                position == selectedItem ? R.color.white : R.color.black));
    }
    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemCaterogyBinding binding;

        public ViewHolder(ItemCaterogyBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Category category){
            binding.tvTypeProduct.setText(category.getName());
        }
    }
}
