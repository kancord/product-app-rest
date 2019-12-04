package productapp.controller;

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

    @RequestMapping(value =  "/product", method = RequestMethod.GET)
    public ModelAndView productList() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_list");
        return modelAndView;
    }

    @RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
    public ModelAndView productDetail(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_detail");
        modelAndView.addObject("productId", id);
        return modelAndView;
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.GET)
    public ModelAndView productNewDetail() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("product_new_detail");
        return modelAndView;
    }
}
