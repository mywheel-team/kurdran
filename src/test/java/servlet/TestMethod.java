package servlet;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangtonghe
 * @date 2017/5/7 13:28
 */
public class TestMethod {

    @Test
    public void test() {

        System.out.println(TestRequestMth.GET.name());


    }

    @Test
    public void test2() {
        String[] methods = {};
        String mth = methods.length > 0 ? methods[0] : "";
        System.out.println(mth);

    }

    @Test
    public void test3() {
        String[] str = {"asd", "fgh", "yhj"};
        List<String> list = Arrays.stream(str).map((e) -> e = "a" + e).collect(Collectors.toList());
        System.out.println(list);


    }
}
