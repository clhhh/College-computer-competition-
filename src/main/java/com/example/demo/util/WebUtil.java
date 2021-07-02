package com.example.demo.util;

import com.alibaba.druid.support.json.JSONUtils;
import com.example.demo.result.Result;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class WebUtil {
    public static void Send_AUTHORZE_HttpState(HttpServletResponse response, Result result) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("code",result.getCode());
        map.put("msg",result.getMsg());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().print(JSONUtils.toJSONString(map));
    }

}
