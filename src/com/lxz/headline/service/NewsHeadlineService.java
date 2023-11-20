package com.lxz.headline.service;

import com.lxz.headline.pojo.vo.HeadlineDetailVo;
import com.lxz.headline.pojo.vo.HeadlineQueryVo;

import java.util.Map;

public interface NewsHeadlineService {
    /**
     * 根据分页信息，对查询的数据进行封装的方法
     * @param headlineQueryVo 分页信息
     * @return  成功返回map集合，失败返回null
     */
    Map findNewsPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据新闻id查询所需要要展示的信息，并使查看的新闻浏览量加1的方法
     * @param hid   新闻id
     * @return  成功返回HeadlineDetailVo对象，失败返回null
     */
    HeadlineDetailVo showHeadlineDetail(int hid);
}
