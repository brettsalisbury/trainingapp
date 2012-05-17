package au.com.some.dodgy.company.webapp.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formmappers.NewContactFormToDomainMapper;
import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;

@Controller
@RequestMapping("/newContact")
@Scope("request")
public class NewContactController {

	private final TrainingSiteDomainObject trainingSiteDomainObject;
	private final NewContactFormToDomainMapper newContactFormToDomainMapper;

	@Autowired
	public NewContactController(TrainingSiteDomainObject trainingSiteDomainObject, NewContactFormToDomainMapper newContactFormToDomainMapper)
	{
		this.trainingSiteDomainObject = trainingSiteDomainObject;
		this.newContactFormToDomainMapper = newContactFormToDomainMapper;
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView onPageLoad() {
		ModelAndView pageLoadModelAndView = new ModelAndView("newContact");
		pageLoadModelAndView.addObject("newContactFormModel", new NewContactFormModel());
		return pageLoadModelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onNewContact(@Valid NewContactFormModel newContact, BindingResult result) {
		if (result.hasErrors()) {
			return "newContact";
		}
		
		addNewContactToTrainingSiteDomainObject(newContact);
		return "redirect:welcomePage";
	}

	private void addNewContactToTrainingSiteDomainObject(
			NewContactFormModel newContact) {
		this.trainingSiteDomainObject.addUser(newContactFormToDomainMapper.mapFormToDomain(newContact));
	}	
}
