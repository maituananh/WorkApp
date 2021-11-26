package com.interview.workapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Swagger redirect.
 */
@Controller
public class SwaggerRedirect {

    /**
     * Method model and view.
     *
     * @return the model and view
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView method() {
        String string = "redirect:" +
                "/swagger-ui.html";
        return new ModelAndView(string);
    }
}
