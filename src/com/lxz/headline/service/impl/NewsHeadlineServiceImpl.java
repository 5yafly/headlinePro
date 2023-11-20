package com.lxz.headline.service.impl;

import com.lxz.headline.dao.NewsHeadlineDao;
import com.lxz.headline.dao.impl.NewsHeadlineDaoImpl;
import com.lxz.headline.pojo.NewsHeadline;
import com.lxz.headline.pojo.vo.HeadlineDetailVo;
import com.lxz.headline.pojo.vo.HeadlinePageVo;
import com.lxz.headline.pojo.vo.HeadlineQueryVo;
import com.lxz.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl implements NewsHeadlineService {
    private NewsHeadlineDao headlineDao = new NewsHeadlineDaoImpl();
    @Override
    public Map findNewsPage(HeadlineQueryVo headlineQueryVo) {
        int pageNum = headlineQueryVo.getPageNum();
        int pageSzie = headlineQueryVo.getPageSize();
        List<HeadlinePageVo> pageData =  headlineDao.findNewsPage(headlineQueryVo);
        int totalSize = headlineDao.findAccount(headlineQueryVo);
        int totalPage = totalSize % pageSzie == 0 ? totalSize / pageSzie : totalSize / pageSzie + 1;
        Map pageInfo = new HashMap();
        pageInfo.put("pageData",pageData);
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSzie);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("totalSize",totalSize);
        return pageInfo;
    }
    @Override
    public HeadlineDetailVo showHeadlineDetail(int hid) {
        headlineDao.updateHeadline(hid);
        return headlineDao.showHeadlineDetail(hid);
    }

    @Override
    public int publish(NewsHeadline newsHeadline) {
        return headlineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findByHid(int hid) {
        return headlineDao.findByHid(hid);
    }

    @Override
    public int update(NewsHeadline newsHeadline) {
        return headlineDao.update(newsHeadline);
    }
}
