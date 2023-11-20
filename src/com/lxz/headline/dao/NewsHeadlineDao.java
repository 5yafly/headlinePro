package com.lxz.headline.dao;

import com.lxz.headline.pojo.vo.HeadlineDetailVo;
import com.lxz.headline.pojo.vo.HeadlinePageVo;
import com.lxz.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;

public interface NewsHeadlineDao {
    /**
     * 分页查询的方法
     * @param headlineQueryVo 分页查询信息
     * @return 成功返回List集合，失败返回null
     */
    List<HeadlinePageVo> findNewsPage(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据分页信息条件查询数据的总行数
     * @param headlineQueryVo 分页查询信息
     * @return 成功返回总数，失败返回0
     */
    int findAccount(HeadlineQueryVo headlineQueryVo);

    /**
     * 根据新闻id查找信息
     * @param hid 新闻id
     * @return  成功返回HeadlineDetailVo对象，失败返回null
     */
    HeadlineDetailVo showHeadlineDetail(int hid);

    /**
     * 更新新闻浏览量
     * @param hid 新闻id
     */
    void updateHeadline(int hid);
}
