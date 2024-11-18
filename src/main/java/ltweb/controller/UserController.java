package ltweb.controller;

import ltweb.model.Users;
import ltweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String showSignUpForm(Users user) {
        return "signup";
    }

    @PostMapping("/signup")
    public String signUp(@Valid Users user, BindingResult result) {
        if (result.hasErrors()) {
            return "signup";
        }

        if (userService.getUserByUsername(user.getUsername()) != null) {
            result.rejectValue("username", null, "Username already taken");
            return "signup";
        }

        if (userService.getUserByEmail(user.getEmail()) != null) {
            result.rejectValue("email", null, "Email already in use");
            return "signup";
        }

        userService.registerNewUser(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/profile")
    public String userProfile(Model model) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        Users user = userService.getUserByUsername(username);
        model.addAttribute("user", user);
        return "profile";
    }
}
