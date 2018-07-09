package com.unistrong.baselibs.ui;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * 可设置圆角的drawable
 *
 * @author wWX405921
 */
public class RoundRectDrawable extends Drawable {

    private int solidColor;
    private int boundsColor;
    private int boundsWidth = 1;// default pix
    private int radius;

    /**
     * 可设置圆角的drawable<暂时不支持透明>
     *
     * @param solidColor  内圈颜色
     * @param radius      圆角半径
     * @param boundsColor 外圈颜色
     * @param boundsWidth 线的宽度
     */
    public RoundRectDrawable(int solidColor, int radius, int boundsColor, int boundsWidth) {
        this.solidColor = solidColor;
        this.boundsColor = boundsColor;
        this.boundsWidth = boundsWidth;
        this.radius = radius;
    }

    @Override
    public void draw(Canvas canvas) {

        // bounds
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(boundsColor);
        RectF outerRect = new RectF(getBounds());
        canvas.drawRoundRect(outerRect, radius, radius, paint);
        // solid
        paint.reset();
        paint.setAntiAlias(true);
        paint.setColor(solidColor);
        RectF innerRect = new RectF(new Rect((int) outerRect.left + boundsWidth, (int) outerRect.top + boundsWidth,
                (int) outerRect.right - boundsWidth, (int) outerRect.bottom - boundsWidth));
        canvas.drawRoundRect(innerRect, radius, radius, paint);
    }

    @Override
    public void setAlpha(int alpha) {
        // TODO Auto-generated method stub

    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        // TODO Auto-generated method stub

    }

    @Override
    public int getOpacity() {
        // TODO Auto-generated method stub
        return PixelFormat.TRANSPARENT;
    }

}
