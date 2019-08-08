package com.zjb.home.boxingtu.view.datepicker;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xinyartech.baselibrary.easyrecyclerview.EasyRecyclerView;
import com.xinyartech.baselibrary.easyrecyclerview.adapter.BaseViewHolder;
import com.xinyartech.baselibrary.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xinyartech.baselibrary.easyrecyclerview.decoration.SpaceDecoration;
import com.xinyartech.baselibrary.utils.DpUtils;
import com.zjb.home.boxingtu.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyDatePickerDialog extends Dialog {

    private Context context;
    private ViewHolder viewHolder;
    private List<Integer> positionList;
    private int currentYear = 0;
    private int currentMonth = 0;
    private int currentDay = 0;

    public interface OnCheckListener {
        void check(int currentYear, int currentMonth, int currentDay);
    }

    OnCheckListener onCheckListener;

    public void setOnCheckListener(OnCheckListener onCheckListener) {
        this.onCheckListener = onCheckListener;
    }

    public MyDatePickerDialog(Context context, int currentYear, int currentMonth, int currentDay) {
        super(context, R.style.dialog);
        this.context = context;
        this.currentYear = currentYear;
        this.currentMonth = currentMonth;
        this.currentDay = currentDay;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_date_picker, null);
        setContentView(view);
        viewHolder = new ViewHolder(view);

        positionList = new ArrayList<>();
        for (int i = 1900; i < 2100; i++) {
            for (int j = 0; j < 12; j++) {
                positionList.add((i - 1900) * 12 + j);
            }
        }
        viewHolder.viewPager.setAdapter(new SamplePagerAdapter(context));
        viewHolder.viewPager.setCurrentItem((currentYear - 1900) * 12 + currentMonth - 1);
        Window dialogWindow = getWindow();
        dialogWindow.setGravity(Gravity.BOTTOM);
        dialogWindow.setWindowAnimations(R.style.dialogFenXiang);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 1); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    static
    class ViewHolder {
        @BindView(R.id.viewPager)
        ViewPager viewPager;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    /**
     * des： 图片adapter
     * author： ZhangJieBo
     * date： 2017/7/6 0006 下午 2:18
     */
    class SamplePagerAdapter extends PagerAdapter {

        private Context mContext;

        public SamplePagerAdapter(Context context) {
            this.mContext = context;
        }

        @Override
        public int getCount() {
            return positionList.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, final int position) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.item_date_picker, null);
            container.addView(view, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            TextView textMonth = view.findViewById(R.id.textMonth);
            TextView textYear = view.findViewById(R.id.textYear);
            EasyRecyclerView recyclerView = view.findViewById(R.id.recyclerView);
            view.findViewById(R.id.viewDismiss).setOnClickListener(v -> dismiss());
            CalendarTool calendarTool = new CalendarTool(getContext());
            int year = (position) / 12 + 1900;
            int month = position % 12;
            switch (month) {
                case 0:
                    textMonth.setText("一月");
                    break;
                case 1:
                    textMonth.setText("二月");
                    break;
                case 2:
                    textMonth.setText("三月");
                    break;
                case 3:
                    textMonth.setText("四月");
                    break;
                case 4:
                    textMonth.setText("五月");
                    break;
                case 5:
                    textMonth.setText("六月");
                    break;
                case 6:
                    textMonth.setText("七月");
                    break;
                case 7:
                    textMonth.setText("八月");
                    break;
                case 8:
                    textMonth.setText("九月");
                    break;
                case 9:
                    textMonth.setText("十月");
                    break;
                case 10:
                    textMonth.setText("十一月");
                    break;
                case 11:
                    textMonth.setText("十二月");
                    break;
            }
            textYear.setText(String.valueOf(year));

            List<DateEntity> dateEntityList = calendarTool.getDateEntityList(year, month + 1);
            for (int i = 0; i < dateEntityList.size(); i++) {
                if (dateEntityList.get(i).year == currentYear && dateEntityList.get(i).month == currentMonth && dateEntityList.get(i).day == currentDay) {
                    dateEntityList.get(i).select = true;
                } else {
                    dateEntityList.get(i).select = false;
                }
            }
            GridLayoutManager manager = new GridLayoutManager(getContext(), 7);
            recyclerView.setLayoutManager(manager);

            SpaceDecoration itemDecoration = new SpaceDecoration((int) DpUtils.convertDpToPixel(12f, mContext));
            itemDecoration.setPaddingEdgeSide(false);
            itemDecoration.setPaddingHeaderFooter(true);
            itemDecoration.setPaddingStart(true);
            recyclerView.addItemDecoration(itemDecoration);
            RecyclerArrayAdapter<DateEntity> adapter;
            recyclerView.setAdapterWithProgress(adapter = new RecyclerArrayAdapter<DateEntity>(getContext()) {
                @Override
                public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
                    int layout = R.layout.item_date_picker_item;
                    DateItemViewHolder dateItemViewHolder = new DateItemViewHolder(parent, layout, month);
                    return dateItemViewHolder;
                }
            });
            manager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(7));
            adapter.setOnItemClickListener(position1 -> {
                if (onCheckListener != null) {
                    DateEntity dateEntity = adapter.getItem(position1);
                    if (dateEntity.month == month + 1) {
                        onCheckListener.check(dateEntity.year, dateEntity.month, dateEntity.day);
                        dismiss();
                    }
                }
            });
            adapter.clear();
            adapter.addAll(dateEntityList);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

    }
}