//package com.wthfeng.kurdran.core.util;
//
//import com.wthfeng.kurdran.ioc.Bean;
//import com.wthfeng.kurdran.ioc.BeanImpl;
//
//import java.io.File;
//import java.net.URL;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * 动态扫描类
// *
// * @author wangtonghe
// * @date 2017/12/2 17:51
// */
//public class DynamicScanUtil {
//
//    public static Map<String,Bean<?>> scanByPackageName(String packageName) throws Exception {
//
//        URL url = Thread.currentThread().getContextClassLoader().getResource(packageName.replace(".", "/"));
//        if (url == null) {
//            throw new ClassNotFoundException();
//        }
//
//        File file = new File(url.getFile());
//        return doScan(file, packageName, new HashMap<>());
//
//    }
//
//    public static Map<String,Bean<?>> scanByClassName(Class<?> clazz) throws Exception {
//        String packName = clazz.getPackage().getName();
//        return scanByPackageName(packName);
//
//    }
//
//    private static Map<String,Bean<?>> doScan(File file, String packageName, Map<String,Bean<?>> beans) {
//
//        if (file.isDirectory()) {
//            File[] files1 = file.listFiles(f1 -> f1.isDirectory() || f1.getName().endsWith(".class"));
//            if (files1 != null) {
//                Arrays.stream(files1).forEach(f -> {
//                    String fileName = f.getName();
//                    if (f.isDirectory()) {  //若是目录
//                        String packName = packageName + "." + fileName;
//                        doScan(f, packName, beans);
//                    } else {   //若是文件
//                        String clazzName = packageName + "." + fileName.substring(0, fileName.indexOf('.'));
//                        doScan(f, packageName, beans);
//                    }
//                });
//            }
//        } else {
//            String fileName = file.getName();
//            String clazzName = packageName + "." + fileName.substring(0, fileName.indexOf('.'));
//            try {
//                Class<?> clazz = Class.forName(clazzName);
//                Bean<?> bean = new BeanImpl<>(clazz.getName(),clazz);
//                beans.put(clazz.getName(),bean);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//        }
//        return beans;
//    }
//
//}
