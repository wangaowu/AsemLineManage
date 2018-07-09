package com.unistrong.baselibs.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 该类处理图片的角度，大小，overrideImage为统筹方法
 */
public class BitmapUtils {

    public static final int SPECIAL_WIDTH = 400;//px

    public int getWidth(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        return options.outWidth;
    }

    /**
     * 合适图片的bitmap
     */
    public Bitmap getInSampleBitmap(String filePath) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);

        // Calculate inSampleSize
        options.inSampleSize = getInSampleSize(options, SPECIAL_WIDTH);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;

        return BitmapFactory.decodeFile(filePath, options);
    }

    //计算图片的缩放值
    private int getInSampleSize(BitmapFactory.Options options, int reqWidth) {
        final float height = options.outHeight;
        final int width = options.outWidth;
        int reqHeight = (int) (height / width * reqWidth);
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round(height / reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    /**
     * bytes
     */
    public byte[] getSampleBytes(String imagePath) {
        Bitmap srcBitmap = getInSampleBitmap(imagePath);
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            srcBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            return bytes.toByteArray();
        } catch (Exception e) {
            return null;
        } finally {
            recyleBitmap(srcBitmap);
        }
    }

    /**
     * 正确照片将原始路径覆盖
     *
     * @param srcPath
     */
    public boolean overrideImage(String srcPath) {
        FileOutputStream fs = null;
        int bitmapDegree = getBitmapDegree(srcPath);

        Bitmap srcBitmap = getInSampleBitmap(srcPath);
        Bitmap bitmap = rotateBitmap(srcBitmap, bitmapDegree);//根据角度旋转的bitmap

        try {
            fs = new FileOutputStream(srcPath);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fs);
        } catch (Exception e) {
            return false;
        } finally {
            closeStream(fs);
            recyleBitmap(srcBitmap);
            recyleBitmap(bitmap);
        }
        return true;
    }

    private void closeStream(Closeable stream) {
        try {
            stream.close();
        } catch (Exception e) {
        }
    }

    private void recyleBitmap(Bitmap bitmap) {
        if (bitmap != null && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
    }

    /**
     * 将图片按照某个角度进行旋转
     *
     * @param bitmap 需要旋转的图片
     * @param degree 旋转角度
     */
    private Bitmap rotateBitmap(Bitmap bitmap, int degree) {
        Bitmap result = null;
        // 根据旋转角度，生成旋转矩阵
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        try {
            // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
            result = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        } catch (Exception e) {
        }
        return result;
    }

    /**
     * 获取图片的旋转角度
     */
    private int getBitmapDegree(String imagePath) {
        int degree = 0;
        try {
            // 从指定路径下读取图片，并获取其EXIF信息
            ExifInterface exifInterface = new ExifInterface(imagePath);
            // 获取图片的旋转信息
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);
            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

}
