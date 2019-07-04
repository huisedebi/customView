package com.zjb.home.boxingtu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zjb.home.boxingtu.view.BoXingTu06;
import com.zjb.home.boxingtu.view.ObservableScrollView;

public class BoXingTu06Activity extends AppCompatActivity {

    private ObservableScrollView scrollView;
    private BoXingTu06 boXingTu;
    private float[] line01 = new float[]{
            0.11f,
            0.38f,
            0.18f,
            0.83f,
            0.26f,
            0.92f,
            0.60f,
            0.43f,
            0.11f,
            0.38f,
            0.18f,
            0.83f,
            0.26f,
            0.92f,
            0.60f,
            0.43f,
            0.11f,
            0.38f,
            0.18f,
            0.83f,
            0.26f,
            0.92f,
            0.60f,
            0.83f,
            0.23f,
            0.43f,
            0.53f,
            0.93f,
            0.53f,
            0.13f,
    };
    private String[] text0101 = new String[]{
            "01日",
            "02日",
            "03日",
            "04日",
            "05日",
            "06日",
            "07日",
            "08日",
            "09日",
            "10日",
            "11日",
            "12日",
            "13日",
            "14日",
            "15日",
            "16日",
            "17日",
            "18日",
            "19日",
            "20日",
            "21日",
            "22日",
            "23日",
            "24日",
            "25日",
            "26日",
            "27日",
            "28日",
            "29日",
            "30日",
    };
    private String[] text0102 = new String[]{
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
            "07月",
    };
    private float[] line02 = new float[]{
            0.61f,
            0.38f,
            0.88f,
            0.33f,
            0.56f,
            0.22f,
            1.00f,
            0.13f,
    };
    private String[] text0201 = new String[]{
            "04/22",
            "04/22",
            "04/22",
            "04/22",
            "04/22",
            "04/22",
            "04/22",
            "上周"
    };
    private String[] text0202 = new String[]{
            "04/28",
            "04/28",
            "04/28",
            "04/28",
            "04/28",
            "04/28",
            "04/28",
            ""
    };
    private String[] text0301 = new String[]{
            "01月",
            "02月",
            "03月",
            "04月",
            "05月",
            "06月",
            "07月",
            "08月",
            "09月",
            "10月",
            "11月",
            "12月"
    };
    private String[] text0302 = new String[]{
            "2019",
            "2019",
            "2019",
            "2019",
            "2019",
            "2019",
            "2019",
            "2019",
            "2019",
            "2019",
            "2019",
            "2019"
    };
    private float[] line03 = new float[]{
            0.11f,
            0.54f,
            0.30f,
            0.70f,
            0.10f,
            0.50f,
            0.90f,
            0.33f,
            0.53f,
            0.13f,
            0.73f,
            0.23f,
    };
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bo_xing_tu06);
        scrollView = findViewById(R.id.scrollView);
        boXingTu = findViewById(R.id.boXingTu);
        boXingTu.setBaiFenBiDuAnim(line01, text0101, text0102);
        position= line01.length-1;
        scroll();
        scrollView.setOnScrollListener(new ObservableScrollView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(ObservableScrollView view, int scrollState) {
                if (scrollState == ObservableScrollView.OnScrollListener.SCROLL_STATE_IDLE) {
                    scroll();
                }
            }

            @Override
            public void onScroll(ObservableScrollView view, boolean isTouchScroll, int l, int t, int oldl, int oldt) {
                position = boXingTu.getPosition(l);
                boXingTu.setPosition(position);
            }
        });
    }

    private void scroll() {
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo((int) (position * boXingTu.getWidthJianGe()), 0);
            }
        });
        boXingTu.setPosition(position);
    }
}
