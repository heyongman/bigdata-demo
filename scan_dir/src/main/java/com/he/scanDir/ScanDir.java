package com.he.scanDir;

import com.he.invoke.InvokeProcess;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * 扫描目录下文件的类
 */
public class ScanDir {
    /**
     * 目的目录
     */
    private static final String desPath = "/keduox/bankData";

    /**
     * 主方法
     * @param args
     */
    public static void main(String[] args) {
        try {
            File directory = new File(desPath);
            // 轮询间隔 5 秒
            long interval = TimeUnit.SECONDS.toMillis(5);
            // 创建一个文件观察器用于处理文件的格式
            FileAlterationObserver observer = new FileAlterationObserver(directory, FileFilterUtils.and(
                    FileFilterUtils.fileFileFilter(),FileFilterUtils.suffixFileFilter(".success")));
            //设置文件变化监听器
            observer.addListener(new MyFileListener());
            FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
            monitor.start();
            System.out.println("开启monitor");
            //Thread.sleep(30000);
            //monitor.stop();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

final class MyFileListener implements FileAlterationListener {
    @Override
    public void onStart(FileAlterationObserver fileAlterationObserver) {
        System.out.println("monitor start scan files..");
    }


    @Override
    public void onDirectoryCreate(File file) {
//        System.out.println(file.getName() + " director created.");
    }


    @Override
    public void onDirectoryChange(File file) {
//        System.out.println(file.getName() + " director changed.");
    }


    @Override
    public void onDirectoryDelete(File file) {
//        System.out.println(file.getName() + " director deleted.");
    }


    @Override
    public void onFileCreate(File file) {
        System.out.println(file.getName() + " created.");
        String fileName = file.getName();
        String date = fileName.substring(0,fileName.lastIndexOf("."));
        InvokeProcess.invoke(date);
    }


    @Override
    public void onFileChange(File file) {
//        System.out.println(file.getName() + " changed.");
    }


    @Override
    public void onFileDelete(File file) {
//        System.out.println(file.getName() + " deleted.");
    }


    @Override
    public void onStop(FileAlterationObserver fileAlterationObserver) {
//        System.out.println("monitor stop scanning..");
    }
}