package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ItemProductBinding;
import com.example.a24h_coffee_client.databinding.ItemSelectProductBinding;
import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.model.BillTemporary;
import com.example.a24h_coffee_client.model.Product;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.view.activity.product.ProductSelectContract;

import java.util.List;

public class AdapterSeclectProduct extends RecyclerView.Adapter<AdapterSeclectProduct.ViewHolder> {
    private List<Product> mList;
    private ProductSelectContract.View mView;

    public AdapterSeclectProduct(List<Product> mList, ProductSelectContract.View mView) {
        this.mList = mList;
        this.mView = mView;
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
        holder.binding.btnAddQuantityCart.setOnClickListener(view -> addQuantity(holder.binding, product));
        holder.binding.btnMinusQuantityCart.setOnClickListener(view -> minusQuantity(holder.binding, product));
        holder.binding.checkBox.setOnClickListener(view -> clickCheckBox(holder.binding, product));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void minusQuantity(ItemSelectProductBinding mBinding, Product product){
        int quantity =  Integer.parseInt(mBinding.tvQuantityCart.getText().toString().trim());
        if (quantity == 1){
            Toast.makeText(mBinding.getRoot().getContext(), "Số lượng giới hạn", Toast.LENGTH_SHORT).show();
        }else {
            mBinding.tvQuantityCart.setText(String.valueOf(quantity - 1));
            mView.onUpdateItemClickListener(product.getId(), product.getName(), quantity - 1);
        }

    }

    public void addQuantity(ItemSelectProductBinding mBinding, Product product){
        int quantity =  Integer.parseInt(mBinding.tvQuantityCart.getText().toString().trim());
        mBinding.tvQuantityCart.setText(String.valueOf(quantity + 1));
        mView.onUpdateItemClickListener(product.getId(), product.getName(), quantity + 1);
    }

    public void clickCheckBox(ItemSelectProductBinding mBinding, Product product){
        int quantity =  Integer.parseInt(mBinding.tvQuantityCart.getText().toString().trim());
        BillTemporary billTemporary = new BillTemporary(quantity, product.getPrice(), product.getId());
        BillDetail billDetail = new BillDetail(quantity, product.getImage(), product.getName(), product.getPrice(), product.getId());
        mView.onItemClickListener(billTemporary, billDetail, mBinding.checkBox.isChecked());
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        ItemSelectProductBinding binding;
        public ViewHolder(ItemSelectProductBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        @SuppressLint({"SetTextI18n", "ResourceType"})
        public void bind(Product product){
            Glide.with(binding.getRoot()).load(product.getImage()).centerCrop().into(binding.ivSelectProduct);
            binding.tvNameSelectProduct.setText(product.getName());
            binding.tvPriceSelectProduct.setText(FormatUtils.formatCurrency(product.getPrice()));
            binding.tvStatusSelectProduct.setText(product.getStatus());
            binding.tvStatusSelectProduct.setTextColor(ContextCompat.getColor(binding.getRoot().getContext(), R.color.GreenPrimary));

            }
        }
}
