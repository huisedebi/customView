package com.xinyartech.baselibrary.easyrecyclerview.adapter;

import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by zhuchenxi on 2016/12/22.
 */

public class FixDataObserver extends RecyclerView.AdapterDataObserver {

    private RecyclerView recyclerView;

    public FixDataObserver(RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }



    @Override
    public void onItemRangeInserted(int positionStart, int itemCount) {
        if (recyclerView.getAdapter() instanceof RecyclerArrayAdapter) {
            RecyclerArrayAdapter adapter = (RecyclerArrayAdapter) recyclerView.getAdapter();
            if (adapter.getFooterCount() > 0 && adapter.getCount() == itemCount) {
                recyclerView.scrollToPosition(0);
            }
        }
    }

}
