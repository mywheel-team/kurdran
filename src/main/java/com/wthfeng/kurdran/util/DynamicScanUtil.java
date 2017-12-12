package com.wthfeng.kurdran.util;

import com.wthfeng.kurdran.ioc.ClassInfo;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 动态扫描类
 *
 * @author wangtonghe
 * @date 2017/12/2 17:51
 */
public class DynamicScanUtil {

    public static Set<ClassInfo> scanByPackageName(String packageName) throws Exception {

        URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
        if (url == null) {
            throw new ClassNotFoundException();
        }

        File file = new File(url.getFile());
        return doScan(file, packageName, new HashSet<>());

    }

    public static Set<ClassInfo> scanByClassName(Class<?> clazz) throws Exception {
        String packName = clazz.getPackage().getName();
        return scanByPackageName(packName);

    }

    private static Set<ClassInfo> doScan(File file, String packageName, Set<ClassInfo> clazzs) {

        if (file.isDirectory()) {
            File[] files1 = file.listFiles(f1 -> f1.isDirectory() || f1.getName().endsWith(".class"));
            if (files1 != null) {
                Arrays.stream(files1).forEach(f -> {
                    String fileName = f.getName();
                    if (f.isDirectory()) {  //若是目录
                        String packName = packageName + "." + fileName;
                        doScan(f, packName, clazzs);
                    } else {   //若是文件

                        String clazzName = packageName + "." + fileName.substring(0, fileName.indexOf('.'));
                        try {
                            Class<?> clazz = Class.forName(clazzName);
                            clazzs.add(new ClassInfo(clazz));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        } else {
            String fileName = file.getName();
            String clazzName = packageName + "." + fileName.substring(0, fileName.indexOf('.'));
            try {
                Class<?> clazz = Class.forName(clazzName);
                clazzs.add(new ClassInfo(clazz));
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return clazzs;
    }

}
