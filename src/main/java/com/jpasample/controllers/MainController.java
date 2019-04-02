
package com.jpasample.controllers;

import com.jpasample.dao.HiberDAO;
import com.jpasample.model.Cat;
import java.util.Collections;
import java.util.List;

import com.jpasample.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MainController {
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    HiberDAO dao;
    
    @ModelAttribute("status")
    public String getStatus() {
        return dao.pullStatus();
    }
    
    @RequestMapping("list.do")
    public ModelAndView showAll() {
        ModelAndView mv = new ModelAndView("listalldata");
        List<Cat> allCats = dao.getAllCats();
        mv.addObject("cats", allCats);
        mv.addObject("persons", dao.getAllPersons());
        log.info(allCats.toString());
        return mv;              
    }
    
    @RequestMapping("showCat.do")
    public ModelAndView showOne(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        //Cat c = dao.getCatById(1);
        Cat c = dao.find(id);
        mv.addObject("cats", Collections.singletonList(c));
        return mv;              
    }

    @RequestMapping("deleteCat.do")
    public ModelAndView deleteCatId(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        //Cat c = dao.getCatById(1);
        //Cat c = dao.find(id);
        dao.deleteCat(id);
        //mv.addObject("cats", Collections.singletonList(c));
        List<Cat> allCats = dao.getAllCats();
        mv.addObject("cats", allCats);

        return mv;
        //return showAll();
    }

    @RequestMapping("deletePerson.do")
    public ModelAndView deletePersonId(long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        //Cat c = dao.getCatById(1);
        //Cat c = dao.find(id);
        dao.deletePerson(id);
        //mv.addObject("cats", Collections.singletonList(c));
        List<Person> allPersons = dao.getAllPersons();
        mv.addObject("persons", allPersons);

        return mv;
        //return showAll();
    }
        
    @RequestMapping("init.do")
    public String init() {
        dao.init();
        return "redirect:list.do";
    }
    
    @RequestMapping("addcat.do")
    public ModelAndView addCat() {
        dao.addRandomCat();
        return showAll();             
    }
    
    

    
    
    
    
}
