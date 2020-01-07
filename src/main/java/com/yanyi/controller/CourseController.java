package com.yanyi.controller;

import com.yanyi.dao.CourseDAO;
import com.yanyi.entity.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CourseController {
    @Autowired
    private CourseDAO courseDAO;
    //只有Post请求才可以进入这个方法
    @PostMapping(value = "/add")
    public String add(Course course){
        System.out.println(course);
        courseDAO.add(course);
        return "redirect:/getAll";
    }
    @GetMapping(value = "/getAll")
    public ModelAndView getAll(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("/index");
        modelAndView.addObject("course",courseDAO.getAll());
        return modelAndView;
    }
    @GetMapping(value = "/getById/{id}")
    public ModelAndView getById(@PathVariable(value = "id")int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("edit");
        modelAndView.addObject("course", courseDAO.getById(id));
        return modelAndView;
    }
    @PutMapping(value = "/update")
    public String update(Course course){
        courseDAO.update(course);
        return "redirect:/getAll";

    }
    @DeleteMapping(value = "/delete/{id}")
    public String delete(@PathVariable(value = "id") int id){
        courseDAO.deleteById(id);
        return "redirect:/getAll";
    }
}
