package com.wthfeng.kurdran.render;

import com.wthfeng.kurdran.servlet.ApplicationContent;

import java.util.Map;

/**
 * 默认渲染器，返回json数据
 *
 * @author wangtonghe
 * @date 2017/5/7 15:54
 */
public class JSONRenderer implements Renderer {

    private Map<String, Object> resultMap;

    public JSONRenderer(Map<String, Object> resultMap) {
        this.resultMap = resultMap;
    }

    @Override
    public void render(ApplicationContent content) {



    }
}
