package com.lxz.headline.controller;

import com.lxz.headline.common.Result;
import com.lxz.headline.common.ResultCodeEnum;
import com.lxz.headline.pojo.NewsHeadline;
import com.lxz.headline.service.NewsHeadlineService;
import com.lxz.headline.service.impl.NewsHeadlineServiceImpl;
import com.lxz.headline.util.JwtHelper;
import com.lxz.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/headline/*")
public class NewsHeadlineController extends BaseController{
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();
    /**
     * 发布新闻的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void publish(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readRequest(req, NewsHeadline.class);
        String token = req.getHeader("token");
        newsHeadline.setPublisher(JwtHelper.getUserId(token).intValue());
        int rows = headlineService.publish(newsHeadline);
        Result result = Result.build(null, ResultCodeEnum.ERROR);
        if (rows > 0) {
            result = Result.ok(null);
        }
        WebUtil.writeResponse(resp,result);
    }

    /**
     * 修改头条回显业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findHeadlineByHid(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int hid = Integer.parseInt(req.getParameter("hid"));
        NewsHeadline newsHeadline = headlineService.findByHid(hid);
        Result result = Result.build(null,ResultCodeEnum.ERROR);
        if (null != newsHeadline) {
            Map data = new HashMap();
            data.put("headline",newsHeadline);
            result=Result.ok(data);
        }
        WebUtil.writeResponse(resp,result);
    }

    /**
     * 修改新闻的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsHeadline newsHeadline = WebUtil.readRequest(req, NewsHeadline.class);
        int rows = headlineService.update(newsHeadline);
        Result result = Result.build(null,ResultCodeEnum.ERROR);
        if (rows > 0) {
            result = Result.ok(null);
        }
        WebUtil.writeResponse(resp,result);
    }
}
