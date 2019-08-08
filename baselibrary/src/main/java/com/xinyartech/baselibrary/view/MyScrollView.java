package com.xinyartech.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ScrollView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MyScrollView extends ScrollView {
  private ArrayList<OnMyScrollListener> listeners;
 
  private int currentState;
  private boolean isLastBottom;  //上次刷新是否底部
  private int lastDrawPos;     //上次刷新的纵向位移
 
  public interface OnMyScrollListener {
    int SCROLL_STATE_FLING = 2;   //手指滑动后松开，自动滑动
    int SCROLL_STATE_IDLE = 0;   //不滑动
    int SCROLL_STATE_TOUCH_SCROLL = 1;   //手指按着屏幕滑动
 
    void onScrollStateChanged(MyScrollView view, int state);
 
    void onScroll(MyScrollView view, int y); //滑动距离
 
    void onScrollToTop();   //滑到顶部
 
    void onScrollToBottom(); //滑到底部
  }
 
  public MyScrollView(Context context) {
    super(context);
  }
 
  public MyScrollView(Context context, AttributeSet attrs) {
    super(context, attrs);
    listeners = new ArrayList<>();
  }
 
  public MyScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }
 
  @Override public boolean onTouchEvent(MotionEvent ev) {
    boolean lastDragState = getDragState();
 
    boolean ret = super.onTouchEvent(ev);
    if (ev.getActionMasked() == MotionEvent.ACTION_MOVE) {
      for (OnMyScrollListener listener:listeners) {
        listener.onScroll(this, getScrollY());
      }
    }
 
    if ((ev.getActionMasked() == MotionEvent.ACTION_UP
        || ev.getActionMasked() == MotionEvent.ACTION_CANCEL)
        && currentState == OnMyScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
      //取消滑动
      currentState = OnMyScrollListener.SCROLL_STATE_IDLE;
      for (OnMyScrollListener listener:listeners) {
        listener.onScrollStateChanged(this, OnMyScrollListener.SCROLL_STATE_IDLE);
      }
 
    }
    boolean curDragState = getDragState();
 
    //拖动
    if (!lastDragState && curDragState) {
      currentState = OnMyScrollListener.SCROLL_STATE_TOUCH_SCROLL;
      for (OnMyScrollListener listener:listeners) {
        listener.onScrollStateChanged(this, OnMyScrollListener.SCROLL_STATE_TOUCH_SCROLL);
      }
    }
    return ret;
  }
 
  @Override protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
 
    if (lastDrawPos == getScrollY()
        && getScrollY() > 0
        && currentState == OnMyScrollListener.SCROLL_STATE_FLING ) {
      currentState = OnMyScrollListener.SCROLL_STATE_IDLE;  //设置状态为空闲
      for (OnMyScrollListener listener:listeners) {
        listener.onScrollStateChanged(this, OnMyScrollListener.SCROLL_STATE_IDLE);
      }
    }
 
    if (getScrollY() == 0 && lastDrawPos == 0
        && currentState != OnMyScrollListener.SCROLL_STATE_IDLE) {
      currentState = OnMyScrollListener.SCROLL_STATE_IDLE;  //设置状态为空闲
      for (OnMyScrollListener listener:listeners) {
        listener.onScrollStateChanged(this, OnMyScrollListener.SCROLL_STATE_IDLE);
        listener.onScrollToTop();
      }
    }
 
    lastDrawPos = getScrollY();
 
    if (isCurrentBottom()
        && !isLastBottom) {
      for (OnMyScrollListener listener:listeners) {
        listener.onScrollToBottom();
      }
    }
    isLastBottom = isCurrentBottom();
  }
 
  @Override public void fling(int velocityY) {
    super.fling(velocityY);
 
    if (getChildCount() > 0) {
      currentState = OnMyScrollListener.SCROLL_STATE_FLING;
      for (OnMyScrollListener listener:listeners) {
        listener.onScrollStateChanged(this, OnMyScrollListener.SCROLL_STATE_FLING);
      }
    }
  }
 
  //判断是否滑到底部
  private boolean isCurrentBottom() {
    boolean ret = false;
 
    int targetSdkVersion = getContext().getApplicationInfo().targetSdkVersion;
    int heightPadding;  //scrollview上下padding之和
    View child = getChildAt(0);
    final FrameLayout.LayoutParams lp = (LayoutParams) child.getLayoutParams();
    if (targetSdkVersion >= Build.VERSION_CODES.M) {
      heightPadding = getPaddingTop() + getPaddingBottom() + lp.topMargin + lp.bottomMargin;
    } else {
      heightPadding = getPaddingTop() + getPaddingBottom();
    }
 
    if (getMeasuredHeight() - heightPadding + getScrollY() == child.getMeasuredHeight()) {
      ret = true;
    }
    return ret;
  }
 
  //反射查询mIsBeingDragged
  private boolean getDragState() {
    boolean state = false;
    try {
      Class clz = ScrollView.class;
      Field field = clz.getDeclaredField("mIsBeingDragged");
      field.setAccessible(true);
      state = field.getBoolean(this);
    } catch (Exception ex) {
      ex.printStackTrace();
    }
    return state;
  }
 
  //滑动加速器是否停止
  private boolean isfinishScroll() {
    boolean isfinish=false;
    Class scrollview=ScrollView.class;
    try {
      Field scrollField=scrollview.getDeclaredField("mScroller");
      scrollField.setAccessible(true);
      Object scroller=scrollField.get(this);
      Class overscroller= scrollField.getType();
      Method finishField=overscroller.getMethod("isFinished");
      finishField.setAccessible(true);
      isfinish= (boolean) finishField.invoke(scroller);
    } catch (NoSuchFieldException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (NoSuchMethodException e) {
 
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }
    return isfinish;
  }
 
  /**
   * 添加滑动监听
   * @param listener
   */
  public void addOnScrollListner(OnMyScrollListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("invalid listener");
    }
 
    if (!listeners.contains(listener)) {
      listeners.add(listener);
    }
  }
 
  public void removeOnScrollListener(OnMyScrollListener listener) {
    if (listener == null) {
      throw new IllegalArgumentException("invalid listener");
    }
 
    listeners.remove(listener);
  }
}