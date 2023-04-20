package org.example;

import org.example.model.Employee;
import org.example.model.Job;
import org.example.service.EmployeeService;
import org.example.service.JobService;
import org.example.service.impl.EmployeeServiceImpl;
import org.example.service.impl.JobServiceImpl;
import org.example.util.Util;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        JobService service = new JobServiceImpl();
        EmployeeService employee = new EmployeeServiceImpl();
        while (true) {
            System.out.println("" +
                    "\n1-> connection DATABASE!!!!" +
                    "\n2-> create table jobs:" +
                    "\n3-> add job:" +
                    "\n4-> get job by id:" +
                    "\n5-> sort by experience  asc and desc:" +
                    "\n6-> get job by employee id:" +
                    "\n7-> delete description column:" +
                    "\n8-> create Employee Table:" +
                    "\n9-> add Employee:" +
                    "\n10-> drop table Employee:" +
                    "\n11-> clean table Employee:" +
                    "\n12-> update Employee:" +
                    "\n13-> get all Employees:" +
                    "\n14-> find by email Employee:" +
                    "\n15-> get Employee by Id:" +
                    "\n16-> get Employee by position:");
            int num = new Scanner(System.in).nextInt();
            switch (num) {
                case 1 -> Util.getConnection();
                case 2 -> service.createJobTable();
                case 3 -> {
                    service.addJob(new Job("Mentor", "Java", "Backend developer", 2));
                    service.addJob(new Job("Management", "JavaScript", "Frontend developer", 1));
                    service.addJob(new Job("Instructor", "Java", "Backend developer", 3));
                }
                case 4 -> System.out.println(service.getJobById(2L));
                case 5 -> System.out.println(service.sortByExperience("asc"));
                case 6 -> System.out.println(service.getJobByEmployeeId(1L));
                case 7 -> service.deleteDescriptionColumn();
                case 8 -> employee.createEmployee();
                case 9 -> {
                    employee.addEmployee(new Employee("Rahim", "Kurbanov", 19, "rah@gmail.com", 1));
                    employee.addEmployee(new Employee("Aijamal", "Asangazieva", 19, "ai@gmail.com", 2));
                    employee.addEmployee(new Employee("Aijan", "Eje", 19, "jan@gmail.com", 3));
                }
                case 10 -> employee.dropTable();
                case 11 -> employee.cleanTable();
                case 12 ->
                        employee.updateEmployee(4L, new Employee("Janyshbek", "Akbalaev", 19, "janysh@gmail.com", 1));
                case 13 -> System.out.println(employee.getAllEmployees());
                case 14 -> System.out.println(employee.findByEmail("ai@gmail.com"));
                case 15 -> System.out.println(employee.getEmployeeById(4L));
                case 16 -> System.out.println(employee.getEmployeeByPosition("Mentor"));
                default -> System.out.println(num + "NO!!!");
            }
        }
    }
}
