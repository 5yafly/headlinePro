package com.lxz.headline.filters;

import com.lxz.headline.common.Result;
import com.lxz.headline.common.ResultCodeEnum;
import com.lxz.headline.util.JwtHelper;
import com.lxz.headline.util.WebUtil;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/headline/*")
public class LoginFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String token = req.getHeader("token");
        if (null != token && !JwtHelper.isExpiration(token)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            WebUtil.writeResponse(resp,Result.build(null,ResultCodeEnum.NOT_LOGIN));
        }
    }
}
