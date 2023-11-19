package com.lxz.headline.controller;

import com.lxz.headline.common.Result;
import com.lxz.headline.common.ResultCodeEnum;
import com.lxz.headline.pojo.NewsUser;
import com.lxz.headline.service.NewsUserService;
import com.lxz.headline.service.impl.NewsUserServiceImpl;
import com.lxz.headline.util.JwtHelper;
import com.lxz.headline.util.WebUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/user")
public class NewsUserController extends BaseController{
    private NewsUserService userService = new NewsUserServiceImpl();
    /**
     * 处理登录表单提交的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readRequest(req, NewsUser.class);
        NewsUser loginUser = userService.findByUsername(newsUser);
        Result result = null;
        if (loginUser != null) {
            Map map = new HashMap();
            map.put("token",JwtHelper.createToken(loginUser.getUid().longValue()));
            result = Result.ok(map);
        }else {
            result = Result.build(null, ResultCodeEnum.ERROR);
        }
        WebUtil.writeResponse(resp,result);
    }
}
