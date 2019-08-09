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
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.xinyartech.baselibrary.easyrecyclerview.EasyRecyclerView;
import com.xinyartech.baselibrary.easyrecyclerview.adapter.BaseViewHolder;
import com.xinyartech.baselibrary.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.xinyartech.baselibrary.easyrecyclerview.decoration.SpaceDecoration;
import com.xinyartech.baselibrary.utils.DpUtils;
import com.xinyartech.baselibrary.view.WheelView;
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
    private ArrayList<String> stringsYear;
    private ArrayList<String> stringsMonth;
    private int wheelType = 0;
    private static int dayType = 0;
    private static int yearType = 1;
    private static int monthType = 2;

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
        wheelType = dayType;
        viewHolder.viewPager.setVisibility(View.VISIBLE);
        viewHolder.viewYearMonth.setVisibility(View.INVISIBLE);
        view.findViewById(R.id.textSure).setOnClickListener(v -> {
            viewHolder.viewPager.setVisibility(View.VISIBLE);
            viewHolder.viewYearMonth.setVisibility(View.GONE);
            String selectedText = viewHolder.wheelView1.getSelectedText();
            if (wheelType==yearType){
                viewHolder.viewPager.setCurrentItem((Integer.parseInt(selectedText) - 1900) * 12 + viewHolder.viewPager.getCurrentItem() % 12);
            }else if (wheelType==monthType){
                viewHolder.viewPager.setCurrentItem((viewHolder.viewPager.getCurrentItem() /12)*12 + viewHolder.wheelView1.getSelected());
            }
            wheelType = dayType;
        });
        stringsYear = new ArrayList<>();
        stringsMonth = new ArrayList<>();
        positionList = new ArrayList<>();
        for (int i = 1900; i < 2100; i++) {
            stringsYear.add(String.valueOf(i));
            for (int j = 0; j < 12; j++) {
                positionList.add((i - 1900) * 12 + j);
            }
        }
        stringsMonth.add("一月");
        stringsMonth.add("二月");
        stringsMonth.add("三月");
        stringsMonth.add("四月");
        stringsMonth.add("五月");
        stringsMonth.add("六月");
        stringsMonth.add("七月");
        stringsMonth.add("八月");
        stringsMonth.add("九月");
        stringsMonth.add("十月");
        stringsMonth.add("十一月");
        stringsMonth.add("十二月");
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
            textMonth.setText(stringsMonth.get(month));
            textYear.setText(String.valueOf(year));
            textYear.setOnClickListener(v -> {
                wheelType=yearType;
                viewHolder.textTitle.setText("年份选择");
                viewHolder.viewPager.setVisibility(View.GONE);
                viewHolder.viewYearMonth.setVisibility(View.VISIBLE);
                viewHolder.wheelView1.setData(stringsYear);
                viewHolder.wheelView1.setDefault(position / 12);
            });
            textMonth.setOnClickListener(v -> {
                wheelType=monthType;
                viewHolder.textTitle.setText("月份选择");
                viewHolder.viewPager.setVisibility(View.GONE);
                viewHolder.viewYearMonth.setVisibility(View.VISIBLE);
                viewHolder.wheelView1.setData(stringsMonth);
                viewHolder.wheelView1.setDefault(position % 12);
            });
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

    static
    class ViewHolder {
        @BindView(R.id.viewPager)
        ViewPager viewPager;
        @BindView(R.id.textSure)
        TextView textSure;
        @BindView(R.id.textTitle)
        TextView textTitle;
        @BindView(R.id.wheelView1)
        WheelView wheelView1;
        @BindView(R.id.viewYearMonth)
        LinearLayout viewYearMonth;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}