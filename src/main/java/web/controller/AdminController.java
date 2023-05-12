package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import web.model.Role;
import web.model.User;
import web.service.RoleService;
import web.service.UserService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;

    @GetMapping("")
    public String getAllUsers(Model model) {
        List<User> userList = userService.getAllUsers();
        System.out.println(userList);
        model.addAttribute("userList", userList);
        return "admin";
    }

    @GetMapping("/users/{id}")
    public String getUserById(@PathVariable("id") Long id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("user", user);
        return "user";
    }

    @PostMapping("/saveUser")
    public String saveUser(@Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                           @RequestParam(name = "roles") String role, Model model) {
//        if(bindingResult.hasErrors()) {
//            return "add-user";
//        }
        userService.saveUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/showAddUserForm")
    public String showAddUserForm(Model model) {
        User user = new User();
        List<Role> listRoles= roleService.getRolesList();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "add-user";
    }

    @GetMapping("/updateUserForm/{id}")
    public String updateUserForm(@PathVariable(value = "id") long id, Model model){
        User user = userService.getUserById(id);
        userService.deleteUser(id);
        List<Role> listRoles= roleService.getRolesList();
        model.addAttribute("user", user);
        model.addAttribute("listRoles", listRoles);
        return "edit";
    }
    @PostMapping("/users/{id}/edit")
    public String updateUser( @Valid @ModelAttribute("user") User user, BindingResult bindingResult,
                              @RequestParam(name = "roles") String role, Model model){
        userService.updateUser(user, role);
        return "redirect:/admin";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
