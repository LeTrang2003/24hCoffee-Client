package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ItemProductBinding;
import com.example.a24h_coffee_client.databinding.ItemSelectProductBinding;
import com.example.a24h_coffee_client.model.Product;
import com.example.a24h_coffee_client.utils.FormatUtils;

import java.util.List;

public class AdapterSeclectProduct extends RecyclerView.Adapter<AdapterSeclectProduct.ViewHolder> {
    private List<Product> mList;

    public AdapterSeclectProduct(List<Product> list) {
        this.mList = list;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<Product> products){
        this.mList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemSelectProductBinding mBinding = ItemSelectProductBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mList.get(position);
        if (product == null){
            return;
        }

        holder.bind(product);

    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemSelectProductBinding binding;
        public ViewHolder(ItemSelectProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        @SuppressLint("SetTextI18n")
        public void bind(Product product){
            Glide.with(binding.getRoot()).load(product.getImage()).centerCrop().into(binding.ivSelectProduct);
            binding.tvNameSelectProduct.setText(product.getName());
            binding.tvPriceSelectProduct.setText(FormatUtils.formatCurrency(product.getPrice()));

            if (product.getStatus().equals("Còn hàng")){
                binding.tvStatusSelectProduct.setText("Còn hàng");
                binding.tvStatusSelectProduct.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.GreenPrimary));
            }else {
                binding.tvStatusSelectProduct.setText("Hết hàng");
                binding.tvStatusSelectProduct.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.RedPrimary));
            }
        }
    }
}
