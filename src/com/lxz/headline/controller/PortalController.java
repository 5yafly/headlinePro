package com.lxz.headline.controller;

import com.lxz.headline.common.Result;
import com.lxz.headline.common.ResultCodeEnum;
import com.lxz.headline.pojo.vo.HeadlineQueryVo;
import com.lxz.headline.service.NewsHeadlineService;
import com.lxz.headline.service.impl.NewsHeadlineServiceImpl;
import com.lxz.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 门户 控制器
 * 那些不需要登录，不需要做增删改查的门户页的请求都放这里
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
    private NewsHeadlineService headlineService = new NewsHeadlineServiceImpl();
    /**
     * 根据前端发送的信息，进行分页查询的接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void findNewsPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HeadlineQueryVo headlineQueryVo = WebUtil.readRequest(req, HeadlineQueryVo.class);
        Map pageInfo = headlineService.findNewsPage(headlineQueryVo);
        Map data = new HashMap();
        data.put("pageInfo",pageInfo);
        Result result = null;
        if (null != pageInfo) {
            result = Result.ok(data);
        }else {
            result = Result.build(null, ResultCodeEnum.ERROR);
        }
        WebUtil.writeResponse(resp,result);
    }
}
