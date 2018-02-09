package spittr.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class HomeController {

	@RequestMapping(method = GET)
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/register", method = GET)
	public String register() {
		return "register";
	}

	@RequestMapping(value = "/login", method = GET)
	public String login(@RequestParam(value = "error", required = false) String error, HttpServletRequest request,
			Model model) {
		if (null != error) {
			Object objError = request.getSession(false).getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

			String errorMessage = "未知错误";

			if (objError instanceof DisabledException) {
				errorMessage = "用户未通过审核";
			}

			if (objError instanceof BadCredentialsException) {
				errorMessage = "密码错误";
			}

			if (objError instanceof UsernameNotFoundException) {
				errorMessage = "用户不存在";
			}
			model.addAttribute("loginError", errorMessage);
		}

		return "login";
	}

}
