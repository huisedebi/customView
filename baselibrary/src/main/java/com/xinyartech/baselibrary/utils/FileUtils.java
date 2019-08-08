package com.xinyartech.baselibrary.utils;

import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.text.DecimalFormat;
import java.util.Comparator;


public class FileUtils {
    private String getFileName(File[] files) {
        String str = "";
        if (files != null) { // 先判断目录是否为空，否则会报空指针
            for (File file : files) {
                if (file.isDirectory()) {//检查此路径名的文件是否是一个目录(文件夹)
                    Log.i("zeng", "若是文件目录。继续读1"
                            + file.getName().toString() + file.getPath().toString());
                    getFileName(file.listFiles());
                    Log.i("zeng", "若是文件目录。继续读2"
                            + file.getName().toString() + file.getPath().toString());
                } else {
                    String fileName = file.getName();
                    if (fileName.endsWith(".txt")) {
                        String s = fileName.substring(0, fileName.lastIndexOf(".")).toString();
                        Log.i("zeng", "文件名txt：：   " + s);
                        str += fileName.substring(0, fileName.lastIndexOf(".")) + "\n";
                    }
                }
            }

        }
        return str;
    }


    //flie：要删除的文件夹的所在位置
    public static void deleteFile(File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
//            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            file.delete();
        }
    }

    /**
     * 将文件按时间降序排列
     */
    static class FileComparator2 implements Comparator<File> {

        @Override
        public int compare(File file1, File file2) {
            if (file1.lastModified() < file2.lastModified()) {
                return 1;// 最后修改的文件在前
            } else {
                return -1;
            }
        }
    }

    /**
     * 读取文件的大小
     */

    public static long getFileSize(File f) throws Exception {

        long l = 0;

        if (f.exists()) {

            FileInputStream mFIS = new FileInputStream(f);

            l = mFIS.available();

        } else {

            f.createNewFile();

        }

        return l;

    }

    /**
     * 转换文件大小
     *
     * @param fileS
     * @return
     */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

}
