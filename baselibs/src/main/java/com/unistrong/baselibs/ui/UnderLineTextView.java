package com.unistrong.baselibs.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import com.unistrong.baselibs.utils.DensityUtils;

@SuppressLint("AppCompatCustomView")
public class UnderLineTextView extends TextView {
    private static final String TAG = "UnderLineTextView";
    private int width;
    private int height;
    private int underline_height;
    private int horExtends;

    private boolean enableUnderLine;

    public UnderLineTextView(Context context) {
        this(context, null);
    }

    public UnderLineTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UnderLineTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initUnderLineParams();
    }

    private void initUnderLineParams() {
        underline_height = DensityUtils.dp2px(getContext(), 3);
        horExtends = DensityUtils.dp2px(getContext(), 10);
    }

    /**
     * 设置启用下划线
     *
     * @param enable
     */
    public void setEnableUnderLine(boolean enable) {
        this.enableUnderLine = enable;
        postInvalidate();
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Paint paint = getPaint();
        if (enableUnderLine) {
            float horizontalPadding = calcHorizontalPadding(getTextWidth(paint) + 2 * horExtends, width);
            float l = horizontalPadding;
            int t = height - underline_height;
            float r = width - horizontalPadding;
            int b = this.height;
            RectF rectF = new RectF(l, t, r, b);
            canvas.drawRect(rectF, paint);
        }
    }

    private float calcHorizontalPadding(float textWidth, int viewWidth) {
        return (viewWidth - textWidth) * .5f;
    }

    private float getTextWidth(Paint paint) {
        return paint.measureText(getText().toString());
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getSize(widthMeasureSpec, DensityUtils.dp2px(getContext(), 150));
        height = getSize(heightMeasureSpec, DensityUtils.dp2px(getContext(), 30));
    }

    private int getSize(int sizeMeasureSpec, int atMost) {
        int size = 0;
        switch (MeasureSpec.getMode(sizeMeasureSpec)) {
            case MeasureSpec.EXACTLY:
                size = MeasureSpec.getSize(sizeMeasureSpec);
                break;
            case MeasureSpec.UNSPECIFIED:
                size = atMost;
                DensityUtils.dp2px(getContext(), 30);
                break;
        }
        return size;
    }


}
