package edu.eci.pdsw.validator;

import static org.quicktheories.QuickTheory.qt;

import java.util.Optional;

import org.junit.Test;

import edu.eci.pdsw.model.SocialSecurityType;
import generators.EmployeeGenerator;

/**
 * Test class for {@linkplain SalaryValidator} class
 */
public class SalaryValidatorTest {

	/**
	 * The class under test.
	 */
	private SalaryValidator validator = new SalaryValidator();

	/**
	 * {@inheritDoc}}
	 */
	@Test
	public void validateTest() {
		qt()
		.forAll(EmployeeGenerator.employes())
		.check(employee -> {
			Optional<ErrorType> optional;
			optional = validator.validate(employee);
			if (!(1000 <= employee.getPersonId() && employee.getPersonId() <= 100000 )) {
				return optional.equals(Optional.of(ErrorType.INVALID_ID));
			}
			if (!(100 <= employee.getSalary() && employee.getSalary() >= 50000)) {
				return optional.equals(Optional.of(ErrorType.INVALID_SALARY));
			}
			if (employee.getSocialSecurityType() == SocialSecurityType.SISBEN && employee.getSalary() >= 1500) {
				return optional.equals(Optional.of(ErrorType.INVALID_SISBEN_AFFILIATION));
			}
			if (employee.getSocialSecurityType() == SocialSecurityType.EPS && (employee.getSalary() < 1500 || employee.getSalary() >= 10000)) {
					return optional.equals(Optional.of(ErrorType.INVALID_EPS_AFFILIATION));
			}
			if (employee.getSocialSecurityType() == SocialSecurityType.PREPAID && employee.getSalary() < 10000) {
					return optional.equals(Optional.of(ErrorType.INVALID_PREPAID_AFFILIATION));
			}			
			return optional == null;
		});
	}
}	
