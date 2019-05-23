
package com.kursov.controllers;

import com.kursov.dao.HiberDAO;
import com.kursov.model.Cars;

import java.util.Collections;
import java.util.List;

import com.kursov.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        List<Cars> allCars = dao.getAllCars();
        mv.addObject("cars", allCars);
        mv.addObject("persons", dao.getAllPersons());
        log.info(allCars.toString());
        return mv;              
    }
    
    @RequestMapping("showCar.do")
    public ModelAndView showOne(long id) {
        ModelAndView mv = new ModelAndView("showCars");
        //Cat c = dao.getCatById(1);

        Cars c = dao.findCar(id);
        mv.addObject("cars", Collections.singletonList(c));
        //mv.addObject("persons", dao.getAllPersons());
        return mv;              
    }

    @RequestMapping("/delete/car/{id}")
    public ModelAndView deleteCarId(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        //Cat c = dao.getCatById(1);
        //Cat c = dao.find(id);
        dao.deleteCar(id);
        //mv.addObject("cats", Collections.singletonList(c));
        List<Cars> allCars = dao.getAllCars();
        mv.addObject("cars", allCars);
        mv.addObject("persons", dao.getAllPersons());

        return mv;
        //return showAll();
    }

    @RequestMapping("/delete/person/{id}")
    public ModelAndView deletePersonId(@PathVariable("id") long id) {
        ModelAndView mv = new ModelAndView("listalldata");
        //Cat c = dao.getCatById(1);
        //Cat c = dao.find(id);
       dao.deletePerson(id);
        //mv.addObject("cats", Collections.singletonList(c));
        List<Cars> allCars = dao.getAllCars();
        List<Person> allPersons = dao.getAllPersons();
        mv.addObject("persons", allPersons);

        return mv;
        //return showAll();
    }

    @RequestMapping("showPersons")
    public String showPersons(){
        return "showPersons";
    }
        
    @RequestMapping("init.do")
    public String init() {
        dao.init();
        return "redirect:list.do";
    }

    @RequestMapping(value = "/add/person", method = RequestMethod.POST)
    public String  addUser(@ModelAttribute("user") Person person)  {
       // dao.addPerson(person.getFam(), person.getName(), person.getOt(), person.getDr() );
        dao.addPerson(person);
        return "afterRegister";
        //return showAll();
    }


    @RequestMapping(value = "/add/car", method = RequestMethod.POST)
    public ModelAndView addCar(@ModelAttribute("cars") Cars cars)  {
        dao.addCars(cars.getName(), cars.getModel(), cars.getKorobka(), cars.getYear() );
        return showAll();
    }

    @RequestMapping("addCar.do")
    public String addCar(){
        return "addCar";
    }

    @RequestMapping("login.do")
    public String login(){
        return "login";
    }


    @RequestMapping("register.do")
    public String addPerson(){
        return "register";
    }



    @RequestMapping("changeOwner.do")
    public ModelAndView changeOwner(long pid, long cid) {
        ModelAndView mv = new ModelAndView("listalldata");

        dao.changePerson(pid, cid);
        //Cat c = dao.getCatById(1);
//        Cat c = dao.findCat(cid);
//        Person p = dao.findPerson(pid);
//        c.setOwner(p);
        //dao.deletePerson(id);
        //mv.addObject("cats", Collections.singletonList(c));
        List<Cars> allCars = dao.getAllCars();
        List<Person> allPersons = dao.getAllPersons();
        mv.addObject("persons", allPersons);

        mv.addObject("cats", allCars);
        return mv;
        //return showAll();
    }

    //
    

    
    
    
    
}
