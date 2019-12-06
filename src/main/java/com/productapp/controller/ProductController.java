package com.productapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ProductController {

    @RequestMapping(value = {"/login", "/"}, method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView productList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_list");
        return modelAndView;
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ModelAndView productDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_detail");
        modelAndView.addObject("productById", true);
        modelAndView.addObject("productId", id);
        return modelAndView;
    }

    @RequestMapping(value = "/products/add", method = RequestMethod.GET)
    public ModelAndView productNewDetail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("productById", false);
        modelAndView.addObject("productId", 0);
        modelAndView.setViewName("product_detail");
        return modelAndView;
    }
}
