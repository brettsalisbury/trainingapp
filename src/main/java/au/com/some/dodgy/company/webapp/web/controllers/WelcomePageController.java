package au.com.some.dodgy.company.webapp.web.controllers;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formmappers.WelcomePageModelToDomainMapper;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomePageController extends AbstractController{

	private final TrainingSiteDomainObject trainingSiteDomainObject;
	private final WelcomePageModelToDomainMapper welcomePageModelToDomainMapper;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return onPageLoad();
    }

	public WelcomePageController(TrainingSiteDomainObject trainingSiteDomainObject, WelcomePageModelToDomainMapper welcomePageModelToDomainMapper)
	{
		this.trainingSiteDomainObject = trainingSiteDomainObject;
		this.welcomePageModelToDomainMapper = welcomePageModelToDomainMapper;
	}

	public ModelAndView onPageLoad() {
		ModelAndView pageLoadModelAndView = new ModelAndView("welcomePage");
		pageLoadModelAndView.addObject("welcomePageModel", this.welcomePageModelToDomainMapper.mapDomainToForm(this.trainingSiteDomainObject));
		return pageLoadModelAndView;
	}

}