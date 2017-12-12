package com.wthfeng.kurdran.ioc;

/**
 * 类文件包装类
 * @author wangtonghe
 * @date 2017/12/12 23:05
 */
public class ClassInfo {

    private Class<?> clazz;

    private String name;

    public ClassInfo(Class<?> clazz) {
        this.clazz = clazz;
        this.name = clazz.getName();
    }

    public Class<?> getClazz() {
        return clazz;
    }

    public void setClazz(Class<?> clazz) {
        this.clazz = clazz;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
