abstract class Employee {
    String name, id;

    Employee(String name, String id) {
        this.name = name;
        this.id = id;
    }

    abstract double monthlySalary();
}

class FullTime extends Employee {
    double salary;
    FullTime(String name, String id, double salary) {
        super(name, id); this.salary = salary;
    }
    double monthlySalary() { return salary; }
}

class PartTime extends Employee {
    int hours;
    double rate;
    PartTime(String name, String id, int hours, double rate) {
        super(name, id); this.hours = hours; this.rate = rate;
    }
    double monthlySalary() { return hours * rate; }
}

class Intern extends Employee {
    double stipend;
    Intern(String name, String id, double stipend) {
        super(name, id); this.stipend = stipend;
    }
    double monthlySalary() { return stipend; }
}

public class Payroll {
    public static void main(String[] args) {
        Employee[] employees = {
            new FullTime("A", "E01", 50000),
            new PartTime("B", "E02", 80, 300),
            new Intern("C", "E03", 10000)
        };

        double total = 0;
        for (Employee e : employees) {
            double salary = e.monthlySalary();
            System.out.println(e.name + ": " + salary);
            if (e instanceof Intern) System.out.println("This employee is an intern.");
            total += salary;
        }
        System.out.println("Total salary: " + total);
    }
}
