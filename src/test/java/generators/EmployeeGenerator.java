package generators;

import static org.quicktheories.generators.SourceDSL.integers;
import static org.quicktheories.generators.SourceDSL.longs;

import org.quicktheories.core.Gen;
import org.quicktheories.generators.Generate;

import edu.eci.pdsw.model.Employee;
import edu.eci.pdsw.model.SocialSecurityType;

public class EmployeeGenerator {
	
	public static Gen<Employee> employes() {
		return ids().zip(salaries(),socialSecurities(),(id,salary,socialSecurity)
				-> new Employee(id,salary,socialSecurity));
	}

	private static Gen<SocialSecurityType> socialSecurities() {
		return Generate.enumValues(SocialSecurityType.class);
	}

	private static Gen<Long> salaries() {
		return longs().between(100, 50000);
	}

	private static Gen<Integer> ids() {
		return integers().between(1000, 100000);
	}
	
}
