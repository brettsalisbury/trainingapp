package au.com.some.dodgy.company.webapp.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formmappers.WelcomePageModelToDomainMapper;

@Controller
@RequestMapping("/welcomePage")
@Scope("request")
public class WelcomePageController {

	private final TrainingSiteDomainObject trainingSiteDomainObject;
	private final WelcomePageModelToDomainMapper welcomePageModelToDomainMapper;

	@Autowired
	public WelcomePageController(TrainingSiteDomainObject trainingSiteDomainObject, WelcomePageModelToDomainMapper welcomePageModelToDomainMapper)
	{
		this.trainingSiteDomainObject = trainingSiteDomainObject;
		this.welcomePageModelToDomainMapper = welcomePageModelToDomainMapper;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onPageLoad() {
		ModelAndView pageLoadModelAndView = new ModelAndView("welcomePage");
		pageLoadModelAndView.addObject("welcomePageModel", this.welcomePageModelToDomainMapper.mapDomainToForm(this.trainingSiteDomainObject));
		return pageLoadModelAndView;
	}

}