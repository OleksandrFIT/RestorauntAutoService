package com.work.restorauntautoservice.controller.autorization;

import com.work.restorauntautoservice.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") @Validated User user,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }

        // додатковий код для збереження нового користувача

        return "redirect:/login?success";
    }
}
