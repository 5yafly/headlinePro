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

@WebServlet("/user/*")
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
        NewsUser loginUser = userService.findByNewsUser(newsUser);
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

    /**
     * 根据token口令获得用户信息的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getUserInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getHeader("token");
        Result result = Result.build(null,ResultCodeEnum.ERROR);
        if (null != token && (!"".equals(token))) {
            if (!JwtHelper.isExpiration(token)) {
                NewsUser newsUser = userService.findByUid(JwtHelper.getUserId(token).intValue());
                Map data = new HashMap();
                newsUser.setUserPwd("");
                data.put("login",newsUser);
                result = Result.ok(data);
            }
        }
        WebUtil.writeResponse(resp,result);
    }

    /**
     * 校验用户名是否被占用的业务接口的实现
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void checkUsername(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        NewsUser newsUser = userService.findByUername(username);
        Result result = Result.ok(null);
        if (null != newsUser) {
            result = Result.build(null, ResultCodeEnum.ACCOUNTREPEAT);
        }
        WebUtil.writeResponse(resp,result);
    }

    /**
     * 完成注册的业务接口
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        NewsUser newsUser = WebUtil.readRequest(req, NewsUser.class);
        Integer rows = userService.registUser(newsUser);
        Result result = Result.ok(null);
        if (rows == 0) {
            result = Result.build(null,ResultCodeEnum.ERROR);
        }
        WebUtil.writeResponse(resp,result);
    }
}
