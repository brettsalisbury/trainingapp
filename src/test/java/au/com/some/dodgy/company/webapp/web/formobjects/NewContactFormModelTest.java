package au.com.some.dodgy.company.webapp.web.formobjects;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

public class NewContactFormModelTest {

	private static final String VALID_NAME = "Test Name";
	private static final String INVALID_NAME_LESS_1 = "";
	private static final String INVALID_NAME_GREATER_30 = "1234567890123456789012345678901";
	
	private static final int VALID_AGE = 10;
	private static final int INVALID_AGE_LESS_THAN_MIN = Integer.MIN_VALUE;
	private static final int INVALID_AGE_MIN = 0;
	private static final int INVALID_AGE_MAX = 120;
	private static final int INVALID_AGE_GREATER_THAN_MAX = Integer.MAX_VALUE;
	
	private static final String ERROR_MSG_NAME_NULL = "may not be null";
	private static final String ERROR_MSG_NAME_LENGTH = "length must be between 1 and 30";
	private static final String ERROR_MSG_AGE_LESS_THAN_OR_EQUAL_TO_MIN = "must be greater than or equal to 1";
	private static final String ERROR_MSG_AGE_GREATER_THAN_OR_EQUAL_TO_MAX = "must be less than or equal to 119";
	
	
	private Validator validator;

	@Before
	public void setUp() {
		ValidatorFactory validatorFactory = Validation
				.buildDefaultValidatorFactory();
		this.validator = validatorFactory.getValidator();
	}

	@Test
	public void shouldBeInvalidWhenNameIsNull() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				null, VALID_AGE);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(hasSingleViolationWithMessage(constraintViolations,
				ERROR_MSG_NAME_NULL));
	}

	@Test
	public void shouldBeInvalidWhenNameIsLongerThan30Characters() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				INVALID_NAME_GREATER_30, VALID_AGE);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(hasSingleViolationWithMessage(constraintViolations,
				ERROR_MSG_NAME_LENGTH));
	}

	@Test
	public void shouldBeInvalidWhenNameIsEmptyString() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				INVALID_NAME_LESS_1, VALID_AGE);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(hasSingleViolationWithMessage(constraintViolations,
				ERROR_MSG_NAME_LENGTH));
	}
	
	@Test
	public void shouldBeInvalidWhenAgeIsLessThanMinimum() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				VALID_NAME, INVALID_AGE_LESS_THAN_MIN);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(hasSingleViolationWithMessage(constraintViolations,
				ERROR_MSG_AGE_LESS_THAN_OR_EQUAL_TO_MIN));
	}
	
	@Test
	public void shouldBeInvalidWhenAgeIsEqualToMin() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				VALID_NAME, INVALID_AGE_MIN);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(hasSingleViolationWithMessage(constraintViolations,
				ERROR_MSG_AGE_LESS_THAN_OR_EQUAL_TO_MIN));
	}
	
	@Test
	public void shouldBeInvalidWhenAgeIsEqualToMax() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				VALID_NAME, INVALID_AGE_MAX);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(hasSingleViolationWithMessage(constraintViolations,
				ERROR_MSG_AGE_GREATER_THAN_OR_EQUAL_TO_MAX));
	}
	
	@Test
	public void shouldBeInvalidWhenAgeIsGreaterThanMax() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				VALID_NAME, INVALID_AGE_GREATER_THAN_MAX);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(hasSingleViolationWithMessage(constraintViolations,
				ERROR_MSG_AGE_GREATER_THAN_OR_EQUAL_TO_MAX));
	}
	
	@Test
	public void shouldBeValidWhenNameAndAgeAreValid() {
		// given
		NewContactFormModel newContact = createNewContactFormModelWithNameAndAge(
				VALID_NAME, VALID_AGE);

		// when
		Set<ConstraintViolation<NewContactFormModel>> constraintViolations = this.validator
				.validate(newContact);

		// then
		assertTrue(constraintViolations.isEmpty());
	}

	private NewContactFormModel createNewContactFormModelWithNameAndAge(
			String name, int age) {
		NewContactFormModel newContact = new NewContactFormModel();
		newContact.setName(name);
		newContact.setAge(age);
		return newContact;
	}

	private boolean hasSingleViolationWithMessage(
			Set<ConstraintViolation<NewContactFormModel>> constraintViolations,
			String message) {
		return (new Integer(1).equals(constraintViolations.size()))
				&& constraintViolations.iterator().next().getMessage()
						.equals(message);
	}
}
