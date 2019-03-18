package com.vincent.example.controller;

import com.vincent.example.domain.UserBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IDEA.
 * User: vincent
 * Date: 2017/7/19
 * Comment:
 */

@Controller
@RequestMapping(value = "/user")
public class UserController extends BaseController {

    private static List<UserBean> userBeans = new ArrayList<>();

    static {
        for (int i = 0; i < 10; i++) {
            userBeans.add(new UserBean("Vincent", 18, i));
        }
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public boolean addUserBean(@RequestBody UserBean userBean) {
        return userBeans.add(userBean);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE)
    public boolean removeUserBean(@RequestParam Integer id) {
        for (UserBean userBean : userBeans) {
            if (userBean.getId().equals(id)) {
                return userBeans.remove(userBean);
            }
        }
        return false;
    }

    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public UserBean updateUserBean(@RequestBody UserBean newUserBean) {
        for (UserBean userBean : userBeans) {
            if (userBean.getId().equals(newUserBean.getId())) {
                userBean.setName(newUserBean.getName());
                userBean.setAge(newUserBean.getAge());
                return userBean;
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public List<UserBean> userBeanList(@RequestParam Integer count) {
        return userBeans.subList(0, count);
    }
}
