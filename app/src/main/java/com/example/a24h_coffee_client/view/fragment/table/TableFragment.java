package com.example.a24h_coffee_client.view.fragment.table;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a24h_coffee_client.R;
import com.example.a24h_coffee_client.adapter.AdapterCategory;
import com.example.a24h_coffee_client.adapter.AdapterTable;
import com.example.a24h_coffee_client.databinding.FragmentTableBinding;
import com.example.a24h_coffee_client.model.Table;
import com.example.a24h_coffee_client.view.activity.table.TableDetailActivity;

import java.util.List;

public class TableFragment extends Fragment implements TableContract.View {
    private FragmentTableBinding mBinding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = FragmentTableBinding.inflate(inflater, container, false);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TableContract.Presenter mPresenter = new TablePresenter(this);
        mPresenter.getListTable();
    }

    @Override
    public void onListTable(List<Table> tables) {
        AdapterTable adapterTable = new AdapterTable(tables, this);
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 3);
        mBinding.rcvTable.setLayoutManager(layoutManager);
        mBinding.rcvTable.setAdapter(adapterTable);
    }

    @Override
    public void nextActivity(int tableId) {
        Intent intent = new Intent(getContext(), TableDetailActivity.class);
        intent.putExtra("tableID", tableId);
        startActivity(intent);
    }
}