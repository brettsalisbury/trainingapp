package au.com.some.dodgy.company.webapp.web.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/welcomePage")
@Scope("request")
public class WelcomePageController {

	@RequestMapping(method = RequestMethod.GET)
	public String onInitialPageLoad() {
		return "welcomePage";
	}

}