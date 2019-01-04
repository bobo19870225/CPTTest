package com.jinkan.www.cpttest.view.adapter;

import com.jinkan.www.cpttest.databinding.ItemFileBinding;

import java.util.List;

/**
 * Created by Sampson on 2019/1/4.
 * CPTTest
 */
public class OpenFileAdapter extends MyBaseAdapter<ItemFileBinding, ItemFile> {
    public OpenFileAdapter(int layoutId, Object clickCallback) {
        super(layoutId, clickCallback);
    }

    @Override
    protected boolean ifContentsTheSame(int oldItemPosition, int newItemPosition, List list) {
        return false;
    }


}
