package au.com.some.dodgy.company.webapp.web.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/newContact")
@Scope("request")
public class NewContactController {

	public String onPageLoad() {
		return "newContact";
	}

	
}
