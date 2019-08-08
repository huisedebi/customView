package com.xinyartech.baselibrary.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.style.ScaleXSpan;
import android.util.AttributeSet;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatTextView;


/**
 * Created by HLQ on 2017/5/12
 */
public class LetterSpacingTextView extends AppCompatTextView {

    private float spacing = Spacing.NORMAL;
    private CharSequence originalText = "";
    private CharSequence contentText = "";

    private int mLineY;
    private int mViewWidth;
    private boolean isFenSan = false;

    public LetterSpacingTextView(Context context) {
        super(context);
        init();
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LetterSpacingTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }


    private void init() {
        setIncludeFontPadding(false);
    }

    /**
     * 获取字间距
     *
     * @return
     */
    public float getSpacing() {
        return this.spacing;
    }

    /**
     * 设置间距
     *
     * @param spacing
     */
    public void setSpacing(float spacing) {
        this.spacing = spacing;
        if (!isFenSan){
            applySpacing();
        }
    }

    @Override
    public void setText(CharSequence text, BufferType type) {
        if (!isFenSan){
            originalText = text;
            if (text!=null){
                contentText = text;
            }
            applySpacing();
        }else {
            super.setText(text, type);
        }
    }

    @Override
    public CharSequence getText() {
        if (!isFenSan){
            return contentText;
        }else {
            return super.getText();
        }
    }

    /**
     * 添加应用空间
     */
    private void applySpacing() {
        if (this == null || this.originalText == null) {
            return;
        }
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < originalText.length(); i++) {
            builder.append(originalText.charAt(i));
            if (i + 1 < originalText.length()) {
                // \u00A0 不间断空格
                // 追加空格
                builder.append("\u00A0");
            }
        }
        // TextView通常用来显示普通文本，但是有时候需要对其中某些文本进行样式、事件方面的设置。Android系统通过SpannableString类来对指定文本进行相关处理，具体有以下功能：
        // 1、BackgroundColorSpan 背景色
        // 2、ClickableSpan 文本可点击，有点击事件
        // 3、ForegroundColorSpan 文本颜色（前景色）
        // 4、MaskFilterSpan 修饰效果，如模糊(BlurMaskFilter)、浮雕(EmbossMaskFilter)
        // 5、MetricAffectingSpan 父类，一般不用
        // 6、RasterizerSpan 光栅效果
        // 7、StrikethroughSpan 删除线（中划线）
        // 8、SuggestionSpan 相当于占位符
        // 9、UnderlineSpan 下划线
        // 10、AbsoluteSizeSpan 绝对大小（文本字体）
        // 11、DynamicDrawableSpan 设置图片，基于文本基线或底部对齐。
        // 12、ImageSpan 图片
        // 13、RelativeSizeSpan 相对大小（文本字体）
        // 14、ReplacementSpan 父类，一般不用
        // 15、ScaleXSpan 基于x轴缩放
        // 16、StyleSpan 字体样式：粗体、斜体等
        // 17、SubscriptSpan 下标（数学公式会用到）
        // 18、SuperscriptSpan 上标（数学公式会用到）
        // 19、TextAppearanceSpan 文本外貌（包括字体、大小、样式和颜色）
        // 20、TypefaceSpan 文本字体
        // 21、URLSpan 文本超链接
        // 我们也是通过这个，去设置空格
        SpannableString finalText = new SpannableString(builder.toString());
        if (builder.toString().length() > 1) { // 如果当前TextView内容长度大于1，则进行空格添加
            for (int i = 1; i < builder.toString().length(); i += 2) { // 小demo：100  1 0 0
                // 按照x轴等比例进行缩放 通过我们设置的字间距+1除以10进行等比缩放
                finalText.setSpan(new ScaleXSpan((spacing + 1) / 10), i, i + 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        super.setText(finalText, TextView.BufferType.SPANNABLE);
    }

    public class Spacing {
        public final static float NORMAL = 0;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (isFenSan){
            TextPaint paint = getPaint();
            paint.setColor(getCurrentTextColor());
            paint.drawableState = getDrawableState();
            mViewWidth = getMeasuredWidth();
            String text = getText().toString();
            mLineY = 0;
            mLineY += getTextSize();
            Layout layout = getLayout();

            // layout.getLayout()在4.4.3出现NullPointerException
            if (layout == null) {
                return;
            }

            Paint.FontMetrics fm = paint.getFontMetrics();

            int textHeight = (int) (Math.ceil(fm.descent - fm.ascent));
            textHeight = (int) (textHeight * layout.getSpacingMultiplier() + layout
                    .getSpacingAdd());

            for (int i = 0; i < layout.getLineCount(); i++) {
                int lineStart = layout.getLineStart(i);
                int lineEnd = layout.getLineEnd(i);
                float width = StaticLayout.getDesiredWidth(text, lineStart,
                        lineEnd, getPaint());
                String line = text.substring(lineStart, lineEnd);
                if (needScale(line)) {
                    drawScaledText(canvas, lineStart, line, width);
                } else {
                    canvas.drawText(line, 0, mLineY, paint);
                }
                mLineY += textHeight;
            }
        }else {
            super.onDraw(canvas);
        }
    }

    private void drawScaledText(Canvas canvas, int lineStart, String line,
                                float lineWidth) {
        float x = 0;
        if (isFirstLineOfParagraph(lineStart, line)) {
            String blanks = "  ";
            canvas.drawText(blanks, x, mLineY, getPaint());
            float bw = StaticLayout.getDesiredWidth(blanks, getPaint());
            x += bw;

            line = line.substring(3);
        }

        int gapCount = line.length() - 1;
        int i = 0;
        if (line.length() > 2 && line.charAt(0) == 12288
                && line.charAt(1) == 12288) {
            String substring = line.substring(0, 2);
            float cw = StaticLayout.getDesiredWidth(substring, getPaint());
            canvas.drawText(substring, x, mLineY, getPaint());
            x += cw;
            i += 2;
        }

        float d = (mViewWidth - lineWidth) / gapCount;
        for (; i < line.length(); i++) {
            String c = String.valueOf(line.charAt(i));
            float cw = StaticLayout.getDesiredWidth(c, getPaint());
            canvas.drawText(c, x, mLineY, getPaint());
            x += cw + d;
        }
    }

    private boolean isFirstLineOfParagraph(int lineStart, String line) {
        return line.length() > 3 && line.charAt(0) == ' '
                && line.charAt(1) == ' ';
    }

    private boolean needScale(String line) {
        if (line == null || line.length() == 0) {
            return false;
        } else {
            return line.charAt(line.length() - 1) != '\n';
        }
    }

    public void setFenSan(boolean fenSan) {
        isFenSan = fenSan;
        invalidate();
    }

}