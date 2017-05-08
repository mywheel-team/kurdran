package servlet;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangtonghe
 * @date 2017/5/8 18:20
 */
public class ClassCaseTest {

    @Test
    public void test(){

        Object obj = "sdfsdf";

        Class clazz = String.class;
        List<Object> list = new ArrayList<>();

        System.out.println(clazz.cast(obj));
        list.add(clazz.cast(obj));

    }
}
