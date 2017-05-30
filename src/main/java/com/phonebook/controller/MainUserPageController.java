package com.phonebook.controller;

import com.phonebook.dao.JdbcPhoneBookDao;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MainUserPageController {

    private JdbcPhoneBookDao phoneBookDao;

    @Autowired
    private UserService userService;

    private User getCurrentUser() {
        final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return userService.getUser(auth.getName());
    }

    private ModelMap getNewPhoneBookModel(ModelMap model, List<PhoneBookRecord> phoneBookRecordList) {
        User user = getCurrentUser();
        model.addAttribute("userlogin", user.getUserLogin());
        model.addAttribute("phoneBookRecordList", phoneBookRecordList);
        return model;
    }

    @RequestMapping(value = "/userPage", method = RequestMethod.GET)
    public String mainUserPage(ModelMap model) {
        User user = getCurrentUser();
        getNewPhoneBookModel(model, phoneBookDao.getAll(user.getUserID()));
        return "mainUserPage";
    }

    @RequestMapping(value = "/userPage/phoneBookRecords", method = RequestMethod.GET)
    public String phoneRecord(ModelMap model) {
        PhoneBookRecord phoneBookRecord = new PhoneBookRecord();
        User user = getCurrentUser();
        phoneBookRecord.setUserid(user.getUserID());
        model.put("phoneBookRecord", phoneBookRecord);
        return "newPhoneBookRecord";
    }

    @RequestMapping(value = "/userPage/phoneBookRecords", method = RequestMethod.POST)
    public String newPhoneRecord(@Valid final PhoneBookRecord phoneBookRecord, final BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "newPhoneBookRecord";
        } else {
            phoneBookDao.create(phoneBookRecord);
            User user = getCurrentUser();
            getNewPhoneBookModel(model, phoneBookDao.getAll(user.getUserID()));
            return "mainUserPage";
        }
    }

    @RequestMapping(value = "/userPage/phoneBookRecords/", method = RequestMethod.GET)
    public String seekRecord(ModelMap model) {
        PhoneBookRecord phoneBookRecord = new PhoneBookRecord();
        phoneBookRecord.setUserid(getCurrentUser().getUserID());
        model.put("phoneBookRecord", phoneBookRecord);
        return "seekPhoneBookRecord";
    }

    @RequestMapping(value = "/userPage/phoneBookRecords/", method = RequestMethod.POST)
    public String showMatchesRecord(final PhoneBookRecord phoneBookRecord, ModelMap model) {
        getNewPhoneBookModel(model, phoneBookDao.getAll(phoneBookRecord));
        return "mainUserPage";
    }

    @RequestMapping(value = "/userPage/phoneBookRecords/edit", method = RequestMethod.GET)
    public String getEmptyEditRecordForm(@RequestParam("recordID") long recordID, ModelMap model) {
        PhoneBookRecord targetPhoneBookRecord = phoneBookDao.getRecord(recordID);
        model.put("phoneBookRecord", targetPhoneBookRecord);
        return "editBookRecord";
    }


    @RequestMapping(value = "/userPage/phoneBookRecords/edit", method = RequestMethod.POST)
    public String applyChangesBookRecord(@Valid final PhoneBookRecord phoneBookRecord, final BindingResult bindingResult, ModelMap model) {
        if (bindingResult.hasErrors()) {
            return "editBookRecord";
        } else {
            phoneBookDao.update(phoneBookRecord);
            User user = getCurrentUser();
            getNewPhoneBookModel(model, phoneBookDao.getAll(user.getUserID()));
            return "redirect:/userPage";
        }
    }

    @RequestMapping(value = "/userPage/phoneBookRecords/delete", method = RequestMethod.GET)
    public String matchingDeleteRecord(@RequestParam("recordID") long recordID, ModelMap model) {
        PhoneBookRecord targetPhoneBookRecord = phoneBookDao.getRecord(recordID);
        model.put("phoneBookRecord", targetPhoneBookRecord);
        return "deleteBookRecord";
    }

    @RequestMapping(value = "/userPage/phoneBookRecords/delete", method = RequestMethod.DELETE)
    public String deleteRecordForm(final PhoneBookRecord phoneBookRecord, ModelMap model) {
        phoneBookDao.delete(phoneBookRecord.getId());
        return "redirect:/userPage";
    }

    @Autowired
    @Qualifier("jdbcPhoneBookDao")
    public void setPhoneBookDao(JdbcPhoneBookDao phoneBookDao) {
        this.phoneBookDao = phoneBookDao;
    }
}
