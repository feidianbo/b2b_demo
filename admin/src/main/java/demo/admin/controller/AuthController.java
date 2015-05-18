package demo.admin.controller;

import demo.admin.basic.JsonController;
import demo.admin.service.Auth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * Created by joe on 11/4/14.
 */
@Controller
public class AuthController extends JsonController {
    @Autowired
    protected Auth auth;

    @RequestMapping("/login")
    public String index(Map<String, Object> model) {
        return "login";
    }

    public static class LoginForm {
        @Size(min = 2, max = 30)
        @NotNull
        private String username;
        @Size(min = 2, max = 30)
        @NotNull
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object doLogin(@Valid LoginForm loginForm, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            if (!auth.login(loginForm.username, loginForm.password)) {
                bindingResult.rejectValue("username", null, "用户名或密码不正确或此用户已被禁用");
            }
        }
        return json(bindingResult);
    }

    @RequestMapping("/logout")
    public String logout() {
        session.logout();
        return "redirect:/login";
    }
}
