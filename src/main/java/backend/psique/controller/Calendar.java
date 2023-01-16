package backend.psique.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class Calendar {
    @GetMapping("/calendar")
    public ModelAndView calendario(){
        ModelAndView mv = new ModelAndView("calendar");
        return mv;
    }
}
