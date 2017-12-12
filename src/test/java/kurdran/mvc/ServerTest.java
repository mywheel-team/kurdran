package kurdran.mvc;

import com.sun.tools.javac.util.StringUtils;
import com.wthfeng.kurdran.Kurdran;
import io.netty.util.internal.StringUtil;
import org.junit.Test;

import java.io.File;
import java.io.FileFilter;
import java.net.URL;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author wangtonghe
 * @date 2017/12/2 15:16
 */
public class ServerTest {

    @Test
    public void start() {
        Kurdran.me().start(ServerTest.class);

    }

    @Test
    public void refGetPack() throws Exception {
        Class<?> clazz = ServerTest.class;
        String packname = clazz.getPackage().getName();
        System.out.println(packname);
        String packageDirName = packname.replace('.', '/');
        URL url = Thread.currentThread().getContextClassLoader().getResource(packageDirName);
        if (url == null) {
            System.out.println("null");
            return;
        }
        System.out.println(url);
        if ("file".equals(url.getProtocol())) {
            File file = new File(url.getFile());
            if (!file.exists() || !file.isDirectory()) {
                System.out.println(file);
                return;
            }
            File[] files = file.listFiles(file1 ->
                    file1.isDirectory() || file1.getName().endsWith(".class")
            );
            if (files != null) {
                Arrays.stream(files).forEach(file2 -> {
                    if (file2.isDirectory()) {

                    } else {
                        System.out.println(file2.getName());
                        try {
                            String name = file2.getName();
                            System.out.println(name);
                            Class<?> clasz = Class.forName(packname + "." + name.substring(0, name.indexOf('.')));
                            System.out.println(clasz.getName());
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        }


    }

    @Test
    public void test3() {

        String pack = "kurdran";

        URL url = Thread.currentThread().getContextClassLoader().getResource(pack.replace(".", "/"));

        File file = new File(url.getFile());
        Set<Class<?>> list = doScan(file, pack, new HashSet<>());
        list.forEach(System.out::println);


    }


    private Set<Class<?>> doScan(File file, String packageName, Set<Class<?>> clazzs) {

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
                            clazzs.add(clazz);
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
                clazzs.add(clazz);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return clazzs;
    }
}
