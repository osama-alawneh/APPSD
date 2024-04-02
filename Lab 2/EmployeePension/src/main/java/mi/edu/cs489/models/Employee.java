package mi.edu.cs489.models;

import java.time.LocalDate;

public record Employee(long employeeId,
                       String firstName,
                       String lastName,
                       LocalDate employmentDate,
                       double yearlySalary,
                       PensionPlan pensionPlan) {

}