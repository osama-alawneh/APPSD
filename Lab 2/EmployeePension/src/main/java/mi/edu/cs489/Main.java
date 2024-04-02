package mi.edu.cs489;

import mi.edu.cs489.models.Employee;
import mi.edu.cs489.models.PensionPlan;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        List<Employee> employees = generateDummyData();

        printEmployeesInJSON(employees);
        printEmployeesWithContributionPlansSorted(employees);
        printEmployeesWithPensionPlanNextMonth(employees);
    }


    private static List<Employee> generateDummyData(){

        PensionPlan danielPensionPlan = new PensionPlan("EX1089", LocalDate.of(2023, 1, 17), 100.00);
        PensionPlan carlyPensionPlan = new PensionPlan("SM2307", LocalDate.of(2019, 11, 04), 1555.50);

        return Arrays.asList(
                new Employee(1,"Daniel", "Agar", LocalDate.of(2018, 1, 17), 105945.50, danielPensionPlan),
                new Employee(2,"Benard", "Shaw", LocalDate.of(2019, 4, 3), 197750.00, null),
                new Employee(3,"Wesley", "Schneider", LocalDate.of(2019, 5, 2), 47500.00, null),
                new Employee(4,"Carly", "Agar", LocalDate.of(2014, 5, 16), 842000.75, carlyPensionPlan)
        );
    }
    private static void printEmployeesWithContributionPlansSorted(List<Employee> employees){
        sortEmployeesLastNameYearlySalary(employees);

        System.out.println("Employees in JSON format:");
        System.out.println("[");

        for(Employee employee : employees){
            if(employee.pensionPlan() == null)
                continue;
            printEmployeeInJSON(employee);
        }
        System.out.println("]");
    }
    private static void printEmployeesWithPensionPlanNextMonth(List<Employee> employees){
        sortEmployeesEmploymentDate(employees);

        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("Eligible employees for contribution:");

        System.out.println("Employees in JSON format:");
        System.out.println("[");

        for(Employee employee : employees){

            LocalDate now = LocalDate.now();
            LocalDate employmentDate = employee.employmentDate();

            if(employmentDate.plusYears(5).getYear() != now.getYear()) continue;
            if(employmentDate.getMonth() == now.getMonth() || employmentDate.plusMonths(-1).getMonth() == now.getMonth()){
                printEmployeeInJSON(employee);
            }
        }
        System.out.println("]");

    }

    private static void sortEmployeesLastNameYearlySalary(List<Employee> employees) {
        // Sort employees in ascending order of last names and descending order of yearly salaries
        employees.sort(Comparator.comparing(Employee::lastName).thenComparing(Employee::yearlySalary).reversed());
    }

    private static void sortEmployeesEmploymentDate(List<Employee> employees) {
        // Sort employees in ascending order of last names and descending order of yearly salaries
        employees.sort(Comparator.comparing(Employee::employmentDate));
    }
    private static void printEmployeeInJSON(Employee employee) {
        // Print employee in JSON format
        System.out.println("Employee in JSON format:");

        String pensionPlanJson;
        if (employee.pensionPlan() != null) {
            pensionPlanJson = String.format("\"planRefNumber\": \"%s\", \"enrollmentDate\": \"%s\", \"monthlyContribution\": %.2f",
                    employee.pensionPlan().planRefNumber(), employee.pensionPlan().enrollmentDate(), employee.pensionPlan().monthlyContribution());
        } else {
            pensionPlanJson = "\"planRefNumber\": null, \"enrollmentDate\": null, \"monthlyContribution\": null";
        }

        String json = String.format("\t { \"employeeId\": %d, \"firstName\": \"%s\", \"lastName\": \"%s\", \"employmentDate\": \"%s\", \"yearlySalary\": %.2f, \"pensionPlan\": { %s } }",
                employee.employeeId(), employee.firstName(), employee.lastName(), employee.employmentDate(), employee.yearlySalary(),
                pensionPlanJson);
        System.out.println(json);
    }
    private static void printEmployeesInJSON(List<Employee> employees) {
        // Print employees in JSON format
        System.out.println("Employees in JSON format:");
        System.out.println("[");

        for (Employee employee : employees) {
            printEmployeeInJSON(employee);
        }

        System.out.println("]");
    }

}