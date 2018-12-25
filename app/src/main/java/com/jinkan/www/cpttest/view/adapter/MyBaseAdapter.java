package com.jinkan.www.cpttest.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jinkan.www.cpttest.R;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
public class MyBaseAdapter<IDB extends ViewDataBinding> extends RecyclerView.Adapter<MyBaseAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IDB itemBinDing = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.product_item,
                parent, false);
        return new ViewHolder(itemBinDing);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
        }
    }
}
