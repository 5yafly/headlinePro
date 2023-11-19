package com.lxz.headline.dao.impl;

import com.lxz.headline.dao.BaseDao;
import com.lxz.headline.dao.NewsHeadlineDao;
import com.lxz.headline.pojo.vo.HeadlinePageVo;
import com.lxz.headline.pojo.vo.HeadlineQueryVo;

import java.util.ArrayList;
import java.util.List;

public class NewsHeadlineDaoImpl extends BaseDao implements NewsHeadlineDao {
    /**
     * private Integer hid;
     *     private String title;
     *     private Integer type;
     *     private Integer pageViews;
     *     private Long pastHours;
     *     private Integer publisher;
     * @param headlineQueryVo 分页查询信息
     * @return
     */
    @Override
    public List<HeadlinePageVo> findNewsPage(HeadlineQueryVo headlineQueryVo) {
        List param = new ArrayList();
        String sql = """
                select
                    hid,
                    title,
                    type,
                    page_views pageViews,
                    TIMESTAMPDIFF(HOUR,create_time,NOW()) pastHours,
                    publisher
                from news_headline
                where
                    is_deleted = 0
                """;
        if (0 != headlineQueryVo.getType()) {
            sql = sql.concat(" and type = ? ");
            param.add(headlineQueryVo.getType());
        }
        if (null != headlineQueryVo.getKeyWords() && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            param.add("%"+headlineQueryVo.getKeyWords()+"%");
        }
        sql = sql.concat(" order by pastHours ASC , pageViews DESC ");
        sql = sql.concat(" limit ? , ? ");
        param.add((headlineQueryVo.getPageNum()-1)* headlineQueryVo.getPageSize());
        param.add(headlineQueryVo.getPageSize());
        return executeQuerys(HeadlinePageVo.class, sql, param.toArray());
    }

    @Override
    public int findAccount(HeadlineQueryVo headlineQueryVo) {
        List param = new ArrayList();
        String sql = """
                select
                  count(1)
                from news_headline
                where
                    is_deleted = 0
                """;
        if (0 != headlineQueryVo.getType()) {
            sql = sql.concat(" and type = ? ");
            param.add(headlineQueryVo.getType());
        }
        if (null != headlineQueryVo.getKeyWords() && !headlineQueryVo.getKeyWords().equals("")) {
            sql = sql.concat(" and title like ? ");
            param.add("%"+headlineQueryVo.getKeyWords()+"%");
        }
        return executeQuery(Long.class, sql, param.toArray()).intValue();
    }
}
