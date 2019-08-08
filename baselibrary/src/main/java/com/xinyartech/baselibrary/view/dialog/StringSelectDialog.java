package com.xinyartech.baselibrary.view.dialog;

import android.content.Context;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.easyrecyclerview.EasyRecyclerView;
import com.xinyartech.baselibrary.easyrecyclerview.adapter.BaseViewHolder;
import com.xinyartech.baselibrary.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xinyartech.baselibrary.viewholder.StringSelectViewHolder;

public class StringSelectDialog {

    private static RecyclerArrayAdapter<String> adapter;

    public static void show(Context context, String title, List<String> data, final RecyclerArrayAdapter.OnItemClickListener onItemClickListener) {
        if (data == null) {
            Toast.makeText(context, "数组为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (data.size() == 0) {
            Toast.makeText(context, "数组长度为0", Toast.LENGTH_SHORT).show();
            return;
        }
        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_string_select, null);
        TextView textTitle = view.findViewById(R.id.textTitle);
        textTitle.setText(title);
        EasyRecyclerView recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.getRecyclerView().setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setRefreshingColorResources(R.color.basic_color);
        recyclerView.getSwipeToRefresh().setProgressViewOffset(true, 30, 220);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<String>(context) {
            @Override
            public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                int layout = R.layout.item_string;
                return new StringSelectViewHolder(parent, layout);
            }
        });
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(position);
                    dialog.dismiss();
                }
            }
        });
        adapter.addAll(data);
        dialog.setContentView(view);
        dialog.show();
    }
}
