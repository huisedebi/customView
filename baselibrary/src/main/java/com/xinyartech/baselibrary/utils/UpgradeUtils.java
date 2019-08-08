package com.xinyartech.baselibrary.utils;

import android.Manifest;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.os.Process;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.xinyartech.baselibrary.R;
import com.xinyartech.baselibrary.application.MyApplication;
import com.xinyartech.baselibrary.bean.AppInfoBean;
import com.xinyartech.baselibrary.bean.ConfigParam;
import com.xinyartech.baselibrary.bean.OkObject;
import com.xinyartech.baselibrary.constant.Constant;
import com.xinyartech.baselibrary.utils.gson.GenericType;
import com.xinyartech.baselibrary.utils.gson.GsonUtils;


public class UpgradeUtils {

    public UpgradeUtils(Context mContext) {
        this.mContext = mContext;
        APK_UPGRADE = Environment
                .getExternalStorageDirectory() + "/xinya/upgrade/"+mContext.getResources().getString(R.string.app_name);
    }

    public String APK_UPGRADE = Environment
            .getExternalStorageDirectory() + "/xinya/upgrade/xinyashoukuan";
    private Context mContext;
    private NotificationManager mNotifiMgr;
    private Notification mNotifi;
    private RemoteViews mNotifiviews;
    private AppInfoBean upgrade;
    private int contentLength;
    private ProgressDialog progressDialog;
    private boolean isForce;

    //    private static boolean wifi;
    public interface OnResulListener {
        void cancle();
    }

    OnResulListener onResulListener;

    public void setOnResulListener(OnResulListener onResulListener) {
        this.onResulListener = onResulListener;
    }

    private OkObject getOkObject(String url) {
        HashMap<String, String> params = new HashMap<>();
        String shopNum = ACacheX.getAsString(mContext, Constant.Acache.shopNum, Constant.Acache.shopNum);
        params.put("merchantNum", TextUtils.isEmpty(shopNum) ? "信雅代理商" : shopNum);
        return new OkObject(params, url);
    }

    /**
     * 使用此方法，json格式参考assets/upgrade.txt文件格式
     *
     * @param url
     */
    public void checkUpgrade(String url) {
        ApiClient.postNormal(mContext, getOkObject(url), new ApiClient.CallBack() {
            @Override
            public void onSuccess(String s) {
                Log.e("UpgradeUtils", "UpgradeUtils--onSuccess--升级返回" + s);
                try {
                    upgrade = GsonUtils.parseToGenericObject(s, new GenericType<AppInfoBean>() {
                    });
                    if (upgrade.getCode() == 200) {
                        int currVersion = VersionUtils.getCurrVersion(mContext);
                        if (Integer.parseInt(upgrade.getData().getLastVersion().getVersion()) > currVersion) {
//                            final File file = new File(APK_UPGRADE + upgrade.getData().getLastVersion().getVersion() + ".apk");
//                            if (file.exists()) {
//                                TwoBtnDialog twoBtnDialog = new TwoBtnDialog(mContext, false, "新版本已下载完成，是否立即安装", "是", "否");
//                                twoBtnDialog.setClickListenerInterface(new TwoBtnDialog.ClickListenerInterface() {
//                                    @Override
//                                    public void doConfirm() {
//                                        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
//                                                != PackageManager.PERMISSION_GRANTED) {
//                                            //申请WRITE_EXTERNAL_STORAGE权限
//                                            ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                                    0);
//                                        } else {
////                                            twoBtnDialog.dismiss();
//                                            openFile(file);
//                                        }
//                                    }
//
//                                    @Override
//                                    public void doCancel() {
//                                        twoBtnDialog.dismiss();
//                                        if (onResulListener != null) {
//                                            onResulListener.cancle();
//                                            FileUtils.deleteFile(file);
//                                        }
//                                    }
//                                });
//                                twoBtnDialog.show();
//                                return;
//                            }
                            checkUpDialog(s);
                        } else {
                            if (onResulListener != null) {
                                onResulListener.cancle();
                            }
                            Toast.makeText(mContext, "已是最新版本", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        if (onResulListener != null) {
                            onResulListener.cancle();
                        }
                        Toast.makeText(mContext, upgrade.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    if (onResulListener != null) {
                        onResulListener.cancle();
                    }
                }
            }

            @Override
            public void onError() {
                if (onResulListener != null) {
                    onResulListener.cancle();
                }
            }
        });
    }

    private void checkUpDialog(String json) {
        if (!TextUtils.isEmpty(json)) {
            upgrade = GsonUtils.parseToGenericObject(json, new GenericType<AppInfoBean>() {
            });
        }
        List<ConfigParam> configParamList = GsonUtils.parseToGenericObject(upgrade.getData().getProject().getParams(), new GenericType<List<ConfigParam>>() {
        });
        for (int i = 0; i < configParamList.size(); i++) {
            if (TextUtils.equals(configParamList.get(i).getParamKey(), "isForce")) {
                if (TextUtils.equals(configParamList.get(i).getParamValue(), "false")) {
                    isForce = false;
                } else {
                    isForce = true;
                }
                break;
            }
        }

        LayoutInflater inflater = LayoutInflater.from(mContext);
        View dialog_shengji = inflater.inflate(R.layout.dialog_shengji, null);
        TextView tvShengJi = (TextView) dialog_shengji.findViewById(R.id.tvShengJi);
        tvShengJi.setText(upgrade.getData().getLastVersion().getDescription());
        tvShengJi.setMovementMethod(ScrollingMovementMethod.getInstance());
        final androidx.appcompat.app.AlertDialog alertDialog = new androidx.appcompat.app.AlertDialog.Builder(mContext, R.style.dialog)
                .setView(dialog_shengji)
                .create();
        alertDialog.setCancelable(false);
        alertDialog.show();
        alertDialog.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
                    if (isForce) {
                        alertDialog.dismiss();
                        MyApplication.getInstance().exit();
                        // 杀掉进程
                        Process.killProcess(Process.myPid());
                        System.exit(0);
                    } else {
                        alertDialog.dismiss();
                        if (onResulListener != null) {
                            onResulListener.cancle();
                        }
                    }
                }
                return false;
            }
        });
        Button buttonShengJi = dialog_shengji.findViewById(R.id.buttonShengJi);
        buttonShengJi.setText("立即升级");
        buttonShengJi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions((Activity) mContext, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                            0);
                } else {
                    upgrade(upgrade);
                    alertDialog.dismiss();
//                        if (upgrade.getUpStatus() == 1) {
                    progressDialog = new ProgressDialog(mContext);
                    progressDialog.setMessage("正在下载……");
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                    progressDialog.setProgress(0);
                    progressDialog.setMax(100);
                    progressDialog.setCancelable(false);
                    progressDialog.show();
//                        }
                }
            }
        });
        ImageView imageViewCancle = dialog_shengji.findViewById(R.id.imageViewCancle);
        imageViewCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isForce) {
                    alertDialog.dismiss();
                    MyApplication.getInstance().exit();
                    // 杀掉进程
                    Process.killProcess(Process.myPid());
                    System.exit(0);
                } else {
                    alertDialog.dismiss();
                    if (onResulListener != null) {
                        onResulListener.cancle();
                    }
                }
            }
        });
        Window dialogWindow = alertDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = mContext.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    protected void upgrade(AppInfoBean upgrade) {
        APK_UPGRADE = APK_UPGRADE + upgrade.getData().getLastVersion().getVersion() + ".apk";
        // 下载
        new UpgradeTask().execute(upgrade.getData().getLastVersion().getUrl());
    }

    class UpgradeTask extends AsyncTask<String, Integer, File> {
        @Override
        protected void onPreExecute() {
            // 发送通知显示升级进度

//            if (!wifi) {
            sendNotify();
//            }
        }

        @Override
        protected File doInBackground(String... params) {
            File apkFile = new File(APK_UPGRADE + ".download");
            String apkUrl = params[0];
            InputStream is = null;
            FileOutputStream fos = null;
            try {
                URL url = new URL(apkUrl);
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                // 设置连接超时时间
                conn.setConnectTimeout(25000);
                // 设置下载数据超时时间
                conn.setReadTimeout(25000);
                if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                    // 服务端错误响应
                    return null;
                }

                is = conn.getInputStream();
                contentLength = conn.getContentLength();

                // 如果文件夹不存在则创建
                if (!apkFile.getParentFile().exists()) {
                    apkFile.getParentFile().mkdirs();
                }
                fos = new FileOutputStream(apkFile);
                byte[] buffer = new byte[1024];
                int len = 0;
                int loadedLen = 0;// 当前已下载文件大小
                // 更新13次
//				int updateSize = upgrade.filelen / 13;
                int updateSize = contentLength / 100;
                int num = 0;
                while (-1 != (len = is.read(buffer))) {
                    loadedLen += len;
                    fos.write(buffer, 0, len);
                    if (loadedLen >= updateSize * num) {
                        num++;
                        publishProgress(loadedLen);
                    }
                }
                fos.flush();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (SocketTimeoutException e) {
                // 处理超时异常，提示用户在网络良好情况下重试

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return apkFile;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            // 更新通知
            LogUtil.LogShitou("UpgradeTask--onProgressUpdate", "" + values[0]);
//            if (!wifi) {
            updateNotify(values[0]);
//            }
        }

        @Override
        protected void onPostExecute(final File result) {
            try {
                result.renameTo(new File(APK_UPGRADE));
                finishNotify();
                openFile(new File(APK_UPGRADE));
            } catch (Exception e) {
                finishNotify();
                Toast.makeText(mContext, "下载出错", Toast.LENGTH_SHORT).show();
            }
//            Toast.makeText(mContext, "下载完成，请点击通知栏完成升级", Toast.LENGTH_LONG)
//                    .show();
//            if (wifi) {
//                TwoBtnDialog twoBtnDialog = new TwoBtnDialog(mContext, "新版本已下载完成，是否立即安装", "是", "否");
//                twoBtnDialog.setClicklistener(new TwoBtnDialog.ClickListenerInterface() {
//                    @Override
//                    public void doConfirm() {
//                        openFile(result);
//                    }
//
//                    @Override
//                    public void doCancel() {
//
//                    }
//                });
//                twoBtnDialog.show();
//            } else {
//            }
        }
    }

    private void sendNotify() {
        Intent intent = new Intent();
        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        mNotifiviews = new RemoteViews(mContext.getPackageName(), R.layout.custom_notify);
        mNotifiviews.setViewVisibility(R.id.tv_subtitle, View.VISIBLE);
        mNotifiviews.setViewVisibility(R.id.progressBar1, View.VISIBLE);

        mNotifi = new NotificationCompat.Builder(mContext)
                .setContent(mNotifiviews)
                .setAutoCancel(true)
                // 单击后自动删除
                // .setOngoing(true)// 无法删除的通知
                // 定制通知布局
                .setSmallIcon(R.mipmap.logo)
                .setTicker("正在下载……")
                .setWhen(System.currentTimeMillis())
//                .setSound(Uri.parse("")) //声音
//				.setVibrate(new long[] { 0, 100, 300, 400 })//设置更新振动
                .setContentIntent(contentIntent).build();
        mNotifiMgr = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotifiMgr.notify(12345, mNotifi);
    }

    private void updateNotify(int loadedLen) {
//		int progress = loadedLen * 100 / upgrade.filelen;
        int progress = (int) (((double) loadedLen / (double) contentLength) * 100);
        if (progressDialog != null) {
            progressDialog.setProgress(progress);
        }
        mNotifiviews.setTextViewText(R.id.tv_subtitle, progress + "%");
//		mNotifiviews.setProgressBar(R.id.progressBar1, upgrade.filelen,loadedLen, false);
        mNotifiviews.setProgressBar(R.id.progressBar1, contentLength, loadedLen, false);
        // mNotifiviews.setViewVisibility(R.id.tv_title, View.INVISIBLE);
        mNotifiMgr.notify(12345, mNotifi);
    }

    private void finishNotify() {
        try {
            if (progressDialog != null) {
                progressDialog.dismiss();
            }
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(new File(APK_UPGRADE)),
                    "application/vnd.android.package-archive");
            PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
            mNotifi.contentIntent = contentIntent;
            mNotifiviews.setTextViewText(R.id.tv_title, "下载完成，请点击完成升级");
//		mNotifiviews.setViewVisibility(R.id.tv_subtitle, View.INVISIBLE);
//		mNotifiviews.setViewVisibility(R.id.progressBar1, View.INVISIBLE);
            mNotifiMgr.notify(12345, mNotifi);
        } catch (Exception e) {
        }
    }

    /**
     * 重点在这里
     */
    private void openFile(File var0) {
        Intent var2 = new Intent();
        var2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        var2.setAction(Intent.ACTION_VIEW);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Uri uriForFile = FileProvider.getUriForFile(mContext, mContext.getApplicationContext().getPackageName() + ".provider", var0);
            var2.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            var2.setDataAndType(uriForFile, mContext.getContentResolver().getType(uriForFile));
        } else {
            var2.setDataAndType(Uri.fromFile(var0), getMIMEType(var0));
        }
        try {
            mContext.startActivity(var2);
        } catch (Exception var5) {
            var5.printStackTrace();
            Toast.makeText(mContext, "没有找到打开此类文件的程序", Toast.LENGTH_SHORT).show();
        }
//        if (!wifi) {
        if (mNotifiMgr != null) {
            mNotifiMgr.cancel(12345);
//            }
        }
    }

    private String getMIMEType(File var0) {
        String var1 = "";
        String var2 = var0.getName();
        String var3 = var2.substring(var2.lastIndexOf(".") + 1, var2.length()).toLowerCase();
        var1 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(var3);
        return var1;
    }

}
