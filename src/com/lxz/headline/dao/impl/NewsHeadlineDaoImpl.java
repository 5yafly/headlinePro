package com.lxz.headline.dao.impl;

import com.lxz.headline.dao.BaseDao;
import com.lxz.headline.dao.NewsHeadlineDao;
import com.lxz.headline.pojo.NewsHeadline;
import com.lxz.headline.pojo.vo.HeadlineDetailVo;
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

    /**
     * hid;
     * title;
     * article;
     * type;
     * pageViews;
     * publisher;
     * pastHours; news_headline
     * typeName;  news_type        tid=type
     * author;    news_name        uid=publisher
     * TIMESTAMPDIFF
     * timestampdiff
     */
    @Override
    public HeadlineDetailVo showHeadlineDetail(int hid) {
        String sql = """
                select
                    hid,
                    title,
                    article,
                    type,
                    page_views pageViews,
                    publisher,
                    TIMESTAMPDIFF(HOUR,create_time, NOW()) pastHours,
                    tname typeName,
                    nick_name author
                from news_headline,news_type,news_user
                where
                    hid = ? and news_headline.type = news_type.tid and news_headline.publisher = news_user.uid;
                """;
        List<HeadlineDetailVo> headlineDetailVos = executeQuerys(HeadlineDetailVo.class, sql, hid);
        return headlineDetailVos != null && headlineDetailVos.size() != 0 ? headlineDetailVos.get(0) : null;
    }

    @Override
    public void updateHeadline(int hid) {
        String sql = "update news_headline set page_views = page_views + 1 where hid = ?;";
        executeUpdate(sql,hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        String sql = "insert into news_headline values(DEFAULT,?,?,?,?,0,NOW(),NOW(),0);";
        return executeUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(), newsHeadline.getPublisher());
    }

    @Override
    public NewsHeadline findByHid(int hid) {
        String sql = """
                select 
                    hid,
                    title,
                    article,
                    type,
                    publisher,
                    page_views pageViews,
                    create_time createTime,
                    update_time updateTime,
                    is_deleted isDeleted
                from news_headline
                where
                    hid = ?;
                """;
        List<NewsHeadline> newsHeadlines = executeQuerys(NewsHeadline.class, sql, hid);
        return newsHeadlines != null && newsHeadlines.size() != 0 ? newsHeadlines.get(0) : null;
    }

    @Override
    public int update(NewsHeadline newsHeadline) {
        String sql = """
                update news_headline
                set 
                    title = ?,
                    article = ?,
                    type = ?,
                    update_time = NOW()
                where hid = ?
                ;""";
        return executeUpdate(sql, newsHeadline.getTitle(), newsHeadline.getArticle(), newsHeadline.getType(),newsHeadline.getHid());
    }

    @Override
    public int removeByHid(int hid) {
        String sql = "update news_headline set is_deleted = 1 where hid = ?;";
        return executeUpdate(sql,hid);
    }
}
