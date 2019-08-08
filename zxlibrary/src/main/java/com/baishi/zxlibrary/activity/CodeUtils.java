package com.baishi.zxlibrary.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.os.Bundle;
import android.text.TextUtils;

import com.baishi.zxlibrary.camera.BitmapLuminanceSource;
import com.baishi.zxlibrary.camera.CameraManager;
import com.baishi.zxlibrary.decoding.DecodeFormatManager;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.oned.Code128Writer;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;

/**
 * Created by aaron on 16/7/27.
 * 二维码扫描工具类
 */
public class CodeUtils {

    public static final String RESULT_TYPE = "result_type";
    public static final String RESULT_CODE_TYPE = "result_code_type";
    public static final String RESULT_STRING = "result_string";
    public static final int RESULT_SUCCESS = 1;
    public static final int RESULT_FAILED = 2;

    public static final String LAYOUT_ID = "layout_id";


    /**
     * 解析二维码图片工具类
     *
     * @param analyzeCallback
     */
    public static void analyzeBitmap(String path, AnalyzeCallback analyzeCallback) {

        /**
         * 首先判断图片的大小,若图片过大,则执行图片的裁剪操作,防止OOM
         */
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true; // 先获取原大小
        Bitmap mBitmap = BitmapFactory.decodeFile(path, options);
        options.inJustDecodeBounds = false; // 获取新的大小

        int sampleSize = (int) (options.outHeight / (float) 400);

        if (sampleSize <= 0) {
            sampleSize = 1;
        }
        options.inSampleSize = sampleSize;
        mBitmap = BitmapFactory.decodeFile(path, options);

        MultiFormatReader multiFormatReader = new MultiFormatReader();

        // 解码的参数
        Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>(2);
        // 可以解析的编码类型
        Vector<BarcodeFormat> decodeFormats = new Vector<BarcodeFormat>();
        if (decodeFormats == null || decodeFormats.isEmpty()) {
            decodeFormats = new Vector<BarcodeFormat>();

            // 这里设置可扫描的类型，我这里选择了都支持
            decodeFormats.addAll(DecodeFormatManager.ONE_D_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.QR_CODE_FORMATS);
            decodeFormats.addAll(DecodeFormatManager.DATA_MATRIX_FORMATS);
        }
        hints.put(DecodeHintType.POSSIBLE_FORMATS, decodeFormats);
        // 设置继续的字符编码格式为UTF8
        // hints.put(DecodeHintType.CHARACTER_SET, "UTF8");
        // 设置解析配置参数
        multiFormatReader.setHints(hints);

        // 开始对图像资源解码
        Result rawResult = null;
        try {
            rawResult = multiFormatReader.decodeWithState(new BinaryBitmap(new HybridBinarizer(new BitmapLuminanceSource(mBitmap))));
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (rawResult != null) {
            if (analyzeCallback != null) {
                analyzeCallback.onAnalyzeSuccess(mBitmap,rawResult.getBarcodeFormat().name(), rawResult.getText());
            }
        } else {
            if (analyzeCallback != null) {
                analyzeCallback.onAnalyzeFailed();
            }
        }
    }

    /**
     * 生成二维码图片
     *
     * @param text
     * @param w
     * @param h
     * @param logo
     * @return
     */
    public static Bitmap createImage(String text, int jiBie, int w, int h, Bitmap logo) {
        if (TextUtils.isEmpty(text)) {
            return null;
        }
        try {
            Bitmap scaleLogo = getScaleLogo(logo, w, h);

            int offsetX = w / 2;
            int offsetY = h / 2;

            int scaleWidth = 0;
            int scaleHeight = 0;
            if (scaleLogo != null) {
                scaleWidth = scaleLogo.getWidth();
                scaleHeight = scaleLogo.getHeight();
                offsetX = (w - scaleWidth) / 2;
                offsetY = (h - scaleHeight) / 2;
            }
            Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
            hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
            //容错级别
            switch (jiBie) {
                case 0:
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
                    break;
                case 1:
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                    break;
                case 2:
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
                    break;
                case 3:
                    hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.Q);
                    break;
                default:
                    break;

            }
            //设置空白边距的宽度
            hints.put(EncodeHintType.MARGIN, 0);
            BitMatrix bitMatrix = new QRCodeWriter().encode(text, BarcodeFormat.QR_CODE, w, h, hints);
            int[] pixels = new int[w * h];
            for (int y = 0; y < h; y++) {
                for (int x = 0; x < w; x++) {
                    if (x >= offsetX && x < offsetX + scaleWidth && y >= offsetY && y < offsetY + scaleHeight) {
                        int pixel = scaleLogo.getPixel(x - offsetX, y - offsetY);
                        if (pixel == 0) {
                            if (bitMatrix.get(x, y)) {
                                pixel = 0xff000000;
                            } else {
                                pixel = 0x00ffffff;
                            }
                        }
                        pixels[y * w + x] = pixel;
                    } else {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * w + x] = 0xff000000;
                        } else {
                            pixels[y * w + x] = 0x00ffffff;
                        }
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(w, h,
                    Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, w, 0, 0, w, h);
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static Bitmap getScaleLogo(Bitmap logo, int w, int h) {
        if (logo == null) {
            return null;
        }
        Matrix matrix = new Matrix();
        float scaleFactor = Math.min(w * 1.0f / 5 / logo.getWidth(), h * 1.0f / 5 / logo.getHeight());
        matrix.postScale(scaleFactor, scaleFactor);
        Bitmap result = Bitmap.createBitmap(logo, 0, 0, logo.getWidth(), logo.getHeight(), matrix, true);
        return result;
    }

    /**
     * 解析二维码结果
     */
    public interface AnalyzeCallback {

        public void onAnalyzeSuccess(Bitmap mBitmap,String type, String result);

        public void onAnalyzeFailed();
    }


    /**
     * 为CaptureFragment设置layout参数
     *
     * @param captureFragment
     * @param layoutId
     */
    public static void setFragmentArgs(CaptureFragment captureFragment, int layoutId) {
        if (captureFragment == null || layoutId == -1) {
            return;
        }

        Bundle bundle = new Bundle();
        bundle.putInt(LAYOUT_ID, layoutId);
        captureFragment.setArguments(bundle);
    }

    public static void isLightEnable(boolean isEnable) {
        if (isEnable) {
            Camera camera = CameraManager.get().getCamera();
            if (camera != null) {
                Camera.Parameters parameter = camera.getParameters();
                parameter.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(parameter);
            }
        } else {
            Camera camera = CameraManager.get().getCamera();
            if (camera != null) {
                Camera.Parameters parameter = camera.getParameters();
                parameter.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(parameter);
            }
        }
    }

//    BarcodeFormat.CODE_128; // 表示高密度数据， 字符串可变长，符号内含校验码
//    BarcodeFormat.CODE_39;
//    BarcodeFormat.CODE_93;
//    BarcodeFormat.CODABAR; // 可表示数字0 - 9，字符$、+、 -、还有只能用作起始/终止符的a,b,c d四个字符，可变长度，没有校验位
//    BarcodeFormat.DATA_MATRIX;
//    BarcodeFormat.EAN_8;
//    BarcodeFormat.EAN_13;
//    BarcodeFormat.ITF;
//    BarcodeFormat.PDF417; // 二维码
//    BarcodeFormat.QR_CODE; // 二维码
//    BarcodeFormat.RSS_EXPANDED;
//    BarcodeFormat.RSS14;
//    BarcodeFormat.UPC_E; // 统一产品代码E:7位数字,最后一位为校验位
//    BarcodeFormat.UPC_A; // 统一产品代码A:12位数字,最后一位为校验位
//    BarcodeFormat.UPC_EAN_EXTENSION;

    /**
     * @param expectWidth 期望的宽度
     * @param contents    生成条形码的内容
     * @param height
     * @return
     */
    public static Bitmap getBarCodeWithoutPadding(int mode, String contents, int expectWidth, int height) {


        int realWidth = getBarCodeNoPaddingWidth(expectWidth, contents, expectWidth);

        return syncEncodeBarcode(contents, mode, realWidth, height);
    }

    private static int getBarCodeNoPaddingWidth(int expectWidth, String contents, int maxWidth) {
        boolean[] code = new Code128Writer().encode(contents);

        int inputWidth = code.length;

        //code:210000000000000082 code.length:134 expectWidth:397 maxWidth:435
        // Add quiet zone on both sides.
        //int fullWidth = inputWidth + 0;

        double outputWidth = (double) Math.max(expectWidth, inputWidth);
        double multiple = outputWidth / inputWidth;
        //multiple:2.962686567164179

        //优先取大的
        int returnVal = 0;
        int ceil = (int) Math.ceil(multiple);
        if (inputWidth * ceil <= maxWidth) {
            returnVal = inputWidth * ceil;
        } else {
            int floor = (int) Math.floor(multiple);
            returnVal = inputWidth * floor;
        }

        return returnVal;
    }

    /**
     * 同步创建条形码图片
     *
     * @param content 要生成条形码包含的内容
     * @param width   条形码的宽度，单位px
     * @param height  条形码的高度，单位px
     * @return 返回生成条形的位图
     * <p>
     * 白边问题:
     * https://blog.csdn.net/sunshinwong/article/details/50156017
     * 已知高度,计算宽度:
     */
    private static Bitmap syncEncodeBarcode(String content, int mode, int width, int height) {
        if (TextUtils.isEmpty(content)) {
            return null;
        }
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.MARGIN, 0);

        try {
            BitMatrix bitMatrix;
            switch (mode) {
                case 0:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.AZTEC, width, height, hints);
                    break;
                case 1:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODABAR, width, height, hints);
                    break;
                case 2:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_39, width, height, hints);
                    break;
                case 3:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_93, width, height, hints);
                    break;
                case 4:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, width, height, hints);
                    break;
                case 5:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.DATA_MATRIX, width, height, hints);
                    break;
                case 6:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.EAN_8, width, height, hints);
                    break;
                case 7:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.EAN_13, width, height, hints);
                    break;
                case 8:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.ITF, width, height, hints);
                    break;
                case 9:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.MAXICODE, width, height, hints);
                    break;
                case 10:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.PDF_417, width, height, hints);
                    break;
                case 11:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
                    break;
                case 12:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.RSS_14, width, height, hints);
                    break;
                case 13:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.RSS_EXPANDED, width, height, hints);
                    break;
                case 14:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.UPC_A, width, height, hints);
                    break;
                case 15:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.UPC_E, width, height, hints);
                    break;
                case 16:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.UPC_EAN_EXTENSION, width, height, hints);
                    break;
                default:
                    bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, width, height, hints);
                    break;
            }
            int[] pixels = new int[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (bitMatrix.get(x, y)) {
                        pixels[y * width + x] = 0xff000000;
                    } else {
                        pixels[y * width + x] = 0x00ffffff;
                    }
                }
            }
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
            return bitmap;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

}
