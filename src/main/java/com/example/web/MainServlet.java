package com.example.web;

import com.example.web.domain.HistWifi;
import com.example.web.service.HistService;

import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/")
public class MainServlet extends HttpServlet {

    public void init() {
        System.out.println("Init!!!");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("doGet!!!");
        response.setContentType("text/html");

    }

    public void destroy() {
        System.out.println("destroy!!!");
    }

}