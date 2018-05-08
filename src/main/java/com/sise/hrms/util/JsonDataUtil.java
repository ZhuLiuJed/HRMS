package com.sise.hrms.util;

import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by holyfrans on 2017/3/10.
 * 该类主要用来将数据转化为严格要求的Map，通过springmvc就可
 * 以转化为json
 */
public class JsonDataUtil {
    public static Map<String, Object> toLayUiMap(Page page){
        Map map = new HashMap<String, Object>();
        map.put("code", 0);
        map.put("msg", "获取成功");
        map.put("list", page.getContent());
        map.put("count", page.getTotalElements());
        return map;
    }
}
