package mi.edu.cs489.models;

import java.time.LocalDate;

public record PensionPlan(
        String planRefNumber,
        LocalDate enrollmentDate,
        double monthlyContribution) {
}
