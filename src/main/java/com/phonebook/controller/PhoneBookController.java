package com.phonebook.controller;

import com.phonebook.dao.Dao;
import com.phonebook.entity.PhoneBookRecord;
import com.phonebook.entity.User;
import com.phonebook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PhoneBookController {

    private Dao<PhoneBookRecord> phoneBookDao;
    private Dao<User> userDao;

    @Autowired
    private UserService userService;

    private User getCurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        auth.setAuthenticated(false);
        return userService.getUser(auth.getName());
    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String homePage() {
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        return new ModelAndView("login", "command", new User());
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.put("user", user);
        return "newUser";
    }


    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String addNewUser(@Valid final User user, final BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "newUser";
        } else {
            userDao.create(user);
            model.addAttribute("FIO", user.getFIO());
            model.addAttribute("userlogin", user.getUserLogin());
            return "showNewUser";
        }
    }

    @Autowired
    @Qualifier("jdbcPhoneBookDao")
    public void setPhoneBookDao(Dao<PhoneBookRecord> phoneBookDao) {
        this.phoneBookDao = phoneBookDao;
    }

    @Autowired
    @Qualifier("jdbcUserDao")
    public void setUserDao(Dao<User> userDao) {
        this.userDao = userDao;
    }
}
