package com.smart.web;

import com.smart.domain.LoginCommand;
import com.smart.domain.User;
import com.smart.service.UserService;
import com.smart.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
public class LoginController {

    private UserService userService = new UserServiceImpl();

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    //配置首页
    @RequestMapping(value={"/","/index.html"})
    public ModelAndView loginPage(){
        return new ModelAndView("login");
    }

    //配置loginCheck页面
    @RequestMapping(value={"/loginCheck.html"})
    public ModelAndView loginCheck(HttpServletRequest request, LoginCommand loginCommand){
        boolean isValidUser = userService.hasMatchUser(loginCommand.getUserName(),loginCommand.getPassword());
        if(!isValidUser){
            return new ModelAndView("login","error","用户名密码错误！");
        }else{
            User user = userService.findUserByUserName(loginCommand.getUserName());
            user.setLastIp(request.getLocalAddr());
            user.setLastVisit(new Date());
            userService.LoginLSuccess(user);
            request.getSession().setAttribute("user",user);
            return new ModelAndView("main");
        }
    }
}
