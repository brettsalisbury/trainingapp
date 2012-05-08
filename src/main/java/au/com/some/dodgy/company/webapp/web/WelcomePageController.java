package au.com.some.dodgy.company.webapp.web;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/welcomepage.htm")
@Scope("request")
public class WelcomePageController {

	protected String onInitialPageLoad() {
		return "helloWorld";
	}

}
