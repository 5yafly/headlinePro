package com.lxz.headline.controller;

import jakarta.servlet.annotation.WebServlet;

/**
 * 门户 控制器
 * 那些不需要登录，不需要做增删改查的门户页的请求都放这里
 */
@WebServlet("/portal/*")
public class PortalController extends BaseController{
}
