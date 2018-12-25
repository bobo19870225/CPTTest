package com.jinkan.www.cpttest.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jinkan.www.cpttest.BR;

import java.util.List;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Sampson on 2018/12/24.
 * CPTTest
 */
public class MyBaseAdapter<IDB extends ViewDataBinding> extends RecyclerView.Adapter<MyBaseAdapter.ViewHolder> {

    @LayoutRes
    private int layoutId;
    private List mList;
    private Object clickCallback;

    public MyBaseAdapter(int layoutId, Object clickCallback) {
        this.layoutId = layoutId;

        this.clickCallback = clickCallback;
    }

    public void setList(final List list) {
        if (mList == null) {
            mList = list;
            notifyItemRangeInserted(0, list.size());
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return mList.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return false;
//                    return ifItemsTheSame(oldItemPosition, newItemPosition);
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    return false;
//                    return ifContentsTheSame(oldItemPosition, newItemPosition);
//                    Product newProduct = productList.get(newItemPosition);
//                    Product oldProduct = mProductList.get(oldItemPosition);
//                    return newProduct.getId() == oldProduct.getId()
//                            && Objects.equals(newProduct.getDescription(), oldProduct.getDescription())
//                            && Objects.equals(newProduct.getName(), oldProduct.getName())
//                            && newProduct.getPrice() == oldProduct.getPrice();
                }
            });
            mList = list;
            result.dispatchUpdatesTo(this);
        }
    }

//    protected abstract boolean ifContentsTheSame(int oldItemPosition, int newItemPosition);

//    protected abstract boolean ifItemsTheSame(int oldItemPosition, int newItemPosition);

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IDB itemBinDing = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), layoutId,
                parent, false);
        itemBinDing.setVariable(BR.clickCallback, clickCallback);
        return new ViewHolder(itemBinDing);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.binding.setModel(mList.get(position));
        holder.binding.setVariable(BR.model, mList.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder<IDB extends ViewDataBinding> extends RecyclerView.ViewHolder {
        final IDB binding;

        ViewHolder(@NonNull IDB viewDataBinding) {
            super(viewDataBinding.getRoot());
            binding = viewDataBinding;
        }
    }
}
