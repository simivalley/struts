package org.superbiz.struts;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;


@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/addUser")
    public String addUserForm() {
        return "addUserForm";
    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute User user,  Model model) {

        try {
            userService.add(user);
        } catch (Exception e) {
             model.addAttribute("errorMessage", e.getMessage());
            return "addUserForm";
        }

        return "addedUser";
    }


    @GetMapping("/findUser")
    public String findUserForm() {
        return "findUserForm";
    }

    @PostMapping("/findUser")
    public String findUser(@RequestParam long id, Model model) {
        User user = userService.find(id);

        if (user!=null) {
            model.addAttribute("user", user);
        } else {
            model.addAttribute("errorMessage", "User not found");
            return "findUserForm";
        }

        return "displayUser";
    }

    @GetMapping("/list")
    public String listUsers(Model model) {
        model.addAttribute("users", userService.findAll());
        return "displayUsers";
    }

}
