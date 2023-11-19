package com.lxz.headline.service;

import com.lxz.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 根据分页信息，对查询的数据进行封装的方法
     * @param headlineQueryVo 分页信息
     * @return  成功返回map集合，失败返回null
     */
    Map findNewsPage(HeadlineQueryVo headlineQueryVo);
}
