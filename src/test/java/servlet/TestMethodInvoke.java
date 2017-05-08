package servlet;

import com.wthfeng.kurdran.servlet.annotation.RequestMapping;
import com.wthfeng.kurdran.servlet.handler.RequestResult;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/8 16:18
 */
public class TestMethodInvoke {

    @RequestMapping(value = "/test")
    public String hello(int number, String email) {
        return number + ":" + email;
    }

    @Test
    public void test() {

        Class clazz = TestMethodInvoke.class;
        Method[] methods = clazz.getMethods();
        List<Method> matchMethodList = new ArrayList<>();
        Arrays.stream(methods).forEach(method -> {
            if (method.getAnnotation(RequestMapping.class) != null) {
                matchMethodList.add(method);
            }
        });
        RequestResult requestResult = new RequestResult();
        matchMethodList.forEach((m -> {
            if (m.getAnnotation(RequestMapping.class).value()[0].equals("/test")) {
                requestResult.setInvokeMethod(m);
            }
        }));

//        requestResult.getInvokeMethod()
        Method method = requestResult.getInvokeMethod();

        Class<?>[] classes = method.getParameterTypes();
        Arrays.stream(classes).forEach(System.out::println);
        System.out.println(method.getReturnType());
        Parameter[] parameters = method.getParameters();
        Arrays.stream(parameters).forEach(parameter ->System.out.println(parameter.getName()));

        try {
            System.out.println( method.invoke(clazz.newInstance(),23,"hello"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
