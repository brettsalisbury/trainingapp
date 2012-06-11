package au.com.some.dodgy.company.webapp.web.controllers;

import au.com.some.dodgy.company.domain.TrainingSiteDomainObject;
import au.com.some.dodgy.company.webapp.web.formmappers.NewContactFormToDomainMapper;
import au.com.some.dodgy.company.webapp.web.formobjects.NewContactFormModel;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class NewContactController extends SimpleFormController {

    private final TrainingSiteDomainObject trainingSiteDomainObject;
    private final NewContactFormToDomainMapper newContactFormToDomainMapper;

    public NewContactController(TrainingSiteDomainObject trainingSiteDomainObject, NewContactFormToDomainMapper newContactFormToDomainMapper) {
        this.trainingSiteDomainObject = trainingSiteDomainObject;
        this.newContactFormToDomainMapper = newContactFormToDomainMapper;

        this.setCommandName("newContactFormModel");
        this.setCommandClass(NewContactFormModel.class);
    }

    @Override
    protected ModelAndView onSubmit(Object command) throws Exception {

        NewContactFormModel newContactFormModel = (NewContactFormModel) command;

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        javax.validation.Validator validator = factory.getValidator();
        Set<ConstraintViolation<NewContactFormModel>> violations = validator.validate(newContactFormModel);

        BindingResult bindingResult = new DirectFieldBindingResult(newContactFormModel, "newContactFormModel");
        convert(bindingResult, violations);

		return onNewContact((NewContactFormModel) command, bindingResult);
	}

    public static <T> void convert(Errors errors, Collection<ConstraintViolation<T>> violations) {
        for (ConstraintViolation<?> violation : violations) {
            String field = violation.getPropertyPath().toString();
            FieldError fieldError = errors.getFieldError(field);
            if (fieldError == null || !fieldError.isBindingFailure()) {
                errors.rejectValue(field, violation.getConstraintDescriptor().getAnnotation().annotationType()
                        .getSimpleName(), getArgumentsForConstraint(errors.getObjectName(), field, violation
                        .getConstraintDescriptor()), violation.getMessage());
            }
        }
    }

    private static Object[] getArgumentsForConstraint(String objectName, String field,
            ConstraintDescriptor<?> descriptor) {
        List<Object> arguments = new LinkedList<Object>();
        String[] codes = new String[] { objectName + Errors.NESTED_PATH_SEPARATOR + field, field };
        arguments.add(new DefaultMessageSourceResolvable(codes, field));
        arguments.addAll(descriptor.getAttributes().values());
        return arguments.toArray(new Object[arguments.size()]);
    }
//
//    @Override
//    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        if (METHOD_POST.equals(request.getMethod())) {
////            request.getParameter("newContactFormModel")
//            NewContactFormModel newContactFormModel = new NewContactFormModel();
//            WebDataBinder webDataBinder = new WebDataBinder(newContactFormModel);
//            return onNewContact(newContactFormModel, null);
//        }
//        return onPageLoad();
//    }


    private ModelAndView onPageLoad() {
        ModelAndView pageLoadModelAndView = new ModelAndView("newContact");
        pageLoadModelAndView.addObject("newContactFormModel", new NewContactFormModel());
        return pageLoadModelAndView;
    }

    private ModelAndView onNewContact(NewContactFormModel newContact, BindingResult result) {
        ModelAndView newContactPageLoad = new ModelAndView();
        if (result.hasErrors()) {
            newContactPageLoad.setViewName("newContact");
            newContactPageLoad.addObject("newContactFormModel", result.getTarget());
            newContactPageLoad.addObject(result.MODEL_KEY_PREFIX, result);
        } else {
            addNewContactToTrainingSiteDomainObject(newContact);
            newContactPageLoad.setViewName("redirect:welcomePage");
        }

        return newContactPageLoad;
    }

    private void addNewContactToTrainingSiteDomainObject(
            NewContactFormModel newContact) {
        this.trainingSiteDomainObject.addUser(newContactFormToDomainMapper.mapFormToDomain(newContact));
    }

}
