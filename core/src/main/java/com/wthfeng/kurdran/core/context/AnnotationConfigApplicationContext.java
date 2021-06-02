package com.wthfeng.kurdran.core.context;

import com.wthfeng.kurdran.core.beans.factory.support.AnnotationBeanDefinitionReader;
import com.wthfeng.kurdran.core.beans.factory.support.ScanBeanDefinitionReader;

/**
 * 注解方式应用上下文
 *
 * @author wangtonghe
 * @since 2021/6/2 16:24
 */
public class AnnotationConfigApplicationContext extends GenericApplicationContext implements AnnotationConfigRegistry {

    private final AnnotationBeanDefinitionReader reader;


    private final ScanBeanDefinitionReader scanner;


    private String[] basePackages;

    public AnnotationConfigApplicationContext() {

        this.reader = new AnnotationBeanDefinitionReader(this);

        this.scanner = new ScanBeanDefinitionReader(this);

    }

    public AnnotationConfigApplicationContext(String[] basePackages) {

        this.basePackages = basePackages;
        this.reader = new AnnotationBeanDefinitionReader(this);

        this.scanner = new ScanBeanDefinitionReader(this);
    }

    @Override
    public String getApplicationName() {
        return null;
    }

    @Override
    public void register(Class<?>... componentClasses) {

        reader.register(componentClasses);

        refresh();

    }

    @Override
    public void scan(String... packages) {

        scanner.scan(packages);

        refresh();
    }
}
