package com.example.a24h_coffee_client.adapter;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.databinding.ItemBannerBinding;
import com.example.a24h_coffee_client.databinding.ItemBillDetailBinding;
import com.example.a24h_coffee_client.model.Banner;
import com.example.a24h_coffee_client.model.BillDetail;
import com.example.a24h_coffee_client.utils.FormatUtils;
import com.example.a24h_coffee_client.view.activity.table.TableDetailContract;

import java.util.List;

public class AdapterBillDetail extends RecyclerView.Adapter<AdapterBillDetail.viewHolder> {
    private final List<BillDetail> mList;
    private final TableDetailContract.Presenter mPresenter;
    private final TableDetailContract.View mView;
    private int check;

    public AdapterBillDetail(List<BillDetail> mList, TableDetailContract.Presenter mPresenter, TableDetailContract.View mView, int check) {
        this.mList = mList;
        this.mPresenter = mPresenter;
        this.mView = mView;
        this.check = check;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemBillDetailBinding mBinding = ItemBillDetailBinding.inflate(LayoutInflater.from(parent.getContext()),parent, false);
        return new viewHolder(mBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        BillDetail billDetail = mList.get(position);
        if (billDetail == null){
            return;
        }
        holder.bind(billDetail);
        holder.binding.ivMenuBillDetail.setOnClickListener(view -> showPopupMenu(holder.binding, billDetail, position));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void showPopupMenu(ItemBillDetailBinding mBinding, BillDetail billDetail, int position){
        PopupMenu popupMenu = new PopupMenu(mBinding.getRoot().getContext(), mBinding.ivMenuBillDetail);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.popup_menu_options, popupMenu.getMenu());

        popupMenu.setOnMenuItemClickListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.action_update:
                    openDialog(mBinding, billDetail, position);
                    return true;
                case R.id.action_delete:
                    AlertDialog.Builder builder = new AlertDialog.Builder(mBinding.getRoot().getContext());
                    builder.setTitle("Xóa đồ uống")
                            .setMessage("Bạn chắc chắn muốn xóa đồ uống này ?")
                            .setPositiveButton("Đồng ý", (dialog, which) -> {
                               if (check == 1){
                                   mPresenter.deleteBillDetail(billDetail);
                               }
                                mList.remove(billDetail);
                                notifyDataSetChanged();
                            })
                            .setNegativeButton("Hủy", (dialog, which) -> dialog.dismiss());

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();

                    return true;
                default:
                    return false;
            }
        });

        popupMenu.show();

    }

    @SuppressLint("SetTextI18n")
    public void openDialog(ItemBillDetailBinding mBinding, BillDetail billDetail, int position){
        Dialog dialog = new Dialog(mBinding.getRoot().getContext());
        dialog.setContentView(R.layout.layout_dialog_update);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        Button btnUpdate= dialog.findViewById(R.id.btn_update);
        EditText etQuantity = dialog.findViewById(R.id.et_quantity_update);

        etQuantity.setText(String.valueOf(billDetail.getQuantityProduct()));

        btnCancel.setOnClickListener(view -> dialog.dismiss());
        btnUpdate.setOnClickListener(view -> {

            if (etQuantity.getText().toString().isEmpty()){
                Toast.makeText(mBinding.getRoot().getContext(), "Bạn cần nhập số lượng", Toast.LENGTH_SHORT).show();
                return;
            }

            try {

                int quantity = Integer.parseInt(etQuantity.getText().toString().trim());

                // Nếu số lượng hợp lệ, tiếp tục xử lý
                if (check == 1) {
                    mPresenter.updateQuantityBillDetail(billDetail, quantity, mList);
                }

                mBinding.tvQuantityBillDetail.setText("x" + quantity);
                mList.get(position).setQuantityProduct(quantity);
                mView.onUpdateBillDetail(mList);
                dialog.dismiss();
            } catch (NumberFormatException e) {
                Toast.makeText(mBinding.getRoot().getContext(), "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    public static class viewHolder extends RecyclerView.ViewHolder {
        private final ItemBillDetailBinding binding;

        public viewHolder(ItemBillDetailBinding  binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        @SuppressLint("SetTextI18n")
        public void bind(BillDetail billDetail) {
          Glide.with(binding.getRoot()).load(billDetail.getImageProduct()).centerCrop().into(binding.ivProductBillr);
          binding.tvNameProductIvProductBillDetail.setText(billDetail.getNameProduct());
          binding.tvPriceBillDetail.setText(FormatUtils.formatCurrency(billDetail.getPriceProduct()));
          binding.tvQuantityBillDetail.setText("x"+billDetail.getQuantityProduct());
        }
    }
}
