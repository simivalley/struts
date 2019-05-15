package org.superbiz.struts;

import org.springframework.web.bind.annotation.GetMapping;

public class UsersController {

    @GetMapping("/addUser")
    public String addUserForm() {
        return "addUserForm";
    }
}
