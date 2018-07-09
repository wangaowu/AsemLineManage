package com.unistrong.baselibs.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.unistrong.baselibs.utils.DensityUtils;
import com.unistrong.baselibs.utils.NumberUtils;

/**
 * 进度条view
 */
public class ProgressView extends View {
    private static final int SOLID_COLOR = Color.LTGRAY;//进度条颜色
    private static final int BG_COLOR = Color.WHITE;//画布颜色
    private static final int TEXT_COLOR = Color.DKGRAY;//指示文字颜色
    private static final int INNER_SCALE = 1;//flag
    private static final int OUTER_SCALE = 2;//flag
    private static final float LR_PERCENT = .8f;//进度条占画布宽度的百分比
    private int TEXT_SIZE;

    private int width;
    private int height;
    private Paint paint;

    private float max;
    private float current;
    private int scaleMode;
    private Rect outRect;
    private float progress;

    public ProgressView(Context context) {
        this(context, null);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //get_progress
        float progress = dynamicProgress();
        //background
        paint.setColor(BG_COLOR);
        canvas.drawRect(outRect, paint);
        //progress_solid
        paint.setColor(SOLID_COLOR);
        canvas.drawRect(getSolidRect(progress), paint);
        //progress_text
        drawProgressText(canvas, progress);
        //before_start or after_finish
        if (progress == 1 || progress == 0) {
            String indicator = progress == 0 ? "等待下载.." : "下载完成!";
            drawIndicator(canvas, indicator);
        }
    }

    private void drawProgressText(Canvas canvas, float progress) {
        paint.setTextSize(TEXT_SIZE);
        paint.setColor(TEXT_COLOR);
        paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        paint.setTextAlign(Paint.Align.CENTER);
        int textRectHorCenter = (int) ((1 + LR_PERCENT) * .5f * width);
        int textRectVerCenter = (int) (height * .5f + TEXT_SIZE / 4);
        canvas.drawText(getSolidText(progress), textRectHorCenter, textRectVerCenter, paint);
    }

    private void drawIndicator(Canvas canvas, String indicator) {
        int x = outRect.centerX();
        int y = outRect.centerY();
        paint.setColor(TEXT_COLOR);
        paint.setTextSize(TEXT_SIZE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setFlags(Paint.FAKE_BOLD_TEXT_FLAG);
        canvas.drawText(indicator, x, y + TEXT_SIZE / 4, paint);
    }

    private String getSolidText(float progress) {
        String progressText = NumberUtils.keepPrecision(progress * 100, 2) + "%";
        return progressText;
    }

    /**
     * 设置进度
     *
     * @param progress 当前执行进度bytes
     * @param max      最大进度bytes
     */
    public void setProgress(int progress, int max) {
        this.scaleMode = INNER_SCALE;
        this.max = max;
        this.current = progress;
        postInvalidate();
    }

    /**
     * 设置进度
     *
     * @param progress 以100为max，设置当前进度
     */
    public void setProgress(int progress) {
        this.scaleMode = OUTER_SCALE;
        this.progress = progress * .01f;
        postInvalidate();
    }

    private RectF getSolidRect(float progress) {
        RectF rect = new RectF();
        rect.left = outRect.left;
        rect.top = outRect.top;
        rect.bottom = outRect.bottom;
        rect.right = outRect.left + progress * outRect.width();
        return rect;
    }

    private float dynamicProgress() {
        if (INNER_SCALE == scaleMode) {
            if (max == 0) return 0;
            float progress = current / max;
            progress = progress < 0 ? 0 : progress;
            progress = progress > 1 ? 1 : progress;
            return progress;
        }
        return this.progress;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        width = getSize(widthMeasureSpec, DensityUtils.dp2px(getContext(), 150));
        height = getSize(heightMeasureSpec, DensityUtils.dp2px(getContext(), 30));
        TEXT_SIZE = (int) (height * .4f);
        initBackgroundRect();
        setProgress(0);
        postInvalidate();
    }

    private void initBackgroundRect() {
        int padding = height / 10;
        outRect = new Rect(padding, padding, (int) (width * LR_PERCENT - padding), height - padding);
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
