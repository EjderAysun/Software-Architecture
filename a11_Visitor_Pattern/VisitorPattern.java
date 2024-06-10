package a11_Visitor_Pattern;

import java.util.ArrayList;

// Element (Element)
// defines an Accept operation that takes a visitor a as an argument.
interface Element {
    public void Accept (Visitor visitor);
}

// "ConcreteElement" (Employee)
// implements an Accept operation that takes a visitor as an argument
abstract class Employee implements Element {
    private String name;
    private double income;
    private int vacationDays;

    // Constructor
    public Employee(String name, double income, int vacationDays) {
        this.name = name;
        this.income = income;
        this.vacationDays = vacationDays;
    }

    public String getName() {return name;}
    public void setName(String value) {name = value;}
    public double getIncome() {return income;}
    public void setIncome(double value) {income = value;}
    public int getVacationDays() {return vacationDays;}
    public void setVacationDays(int value) {vacationDays = value;}
}

class Clerk extends Employee {
    public Clerk(String name, double salary, int vacation) {
        super(name, salary, vacation);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class Director extends Employee {
    public Director(String name, double salary, int vacation) {
        super(name, salary, vacation);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}
}

class President extends Employee {
    public President(String name, double salary, int vacation) {
        super(name, salary, vacation);
    }
    public void Accept(Visitor visitor) {visitor.Visit(this);}
}

// "Object Structure" (Employees)
class Employees {
    private ArrayList<Employee> employees = new ArrayList<Employee>();
    public void Add(Employee employee){ employees.add(employee);}
    public void Remove(Employee employee) {
        for (int i = 0; i < employees.size(); i++) {
            if(employees.get(i).getName().equals(employee.getName())) {
                employees.remove(i);
                return;
            }
        }
    }
    public void Accept(Visitor visitor) {
        // elements accept the visitor
        for (Employee e : employees) {
            e.Accept(visitor);
        }
    }
}

// "Visitor"
interface Visitor {
    public void Visit(Clerk element);
    public void Visit(Director element);
    public void Visit(President element);
}

// "ConcreteVisitor 1"
class IncomeVisitor implements Visitor {
    public void Visit(Clerk element) {
        element.setIncome(element.getIncome() * 1.10);
        System.out.println(element.getName() + "'s new income:");
        System.out.printf("%6.2f\n", element.getIncome());
    }

    public void Visit(Director element) {
        element.setIncome(element.getIncome() * 1.50);
        System.out.println(element.getName() + "'s new income:");
        System.out.printf("%6.2f\n", element.getIncome());
    }

    public void Visit(President element) {
        element.setIncome(element.getIncome() * 2.0);
        System.out.println(element.getName() + "'s new income:");
        System.out.printf("%6.2f\n", element.getIncome());
    }
}

// "ConcreteVisitor 2"
class VacationVisitor implements Visitor {
    public void Visit(Clerk element) {
        // Provide 3 extra vacation days
        element.setVacationDays(element.getVacationDays() + 3);
        System.out.print(element.getName() + "'s new vacation days:");
        System.out.println(element.getVacationDays());
    }

    public void Visit(Director element) {
        // Provide 5 extra vacation days
        element.setVacationDays(element.getVacationDays() + 5);
        System.out.print(element.getName() + "'s new vacation days:");
        System.out.println(element.getVacationDays());
    }

    public void Visit(President element) {
        // Provide 7 extra vacation days
        element.setVacationDays(element.getVacationDays() + 7);
        System.out.print(element.getName() + "'s new vacation days:");
        System.out.println(element.getVacationDays());
    }
}

public class VisitorPattern {
    public static void main(String[] args) {
        // Setup Employee Collections
        Employees e = new Employees();
        e.Add(new Clerk("A", 200000, 10));
        e.Add(new Director("B", 300000, 20));
        e.Add(new President("C", 400000, 30));

        // Employees are 'visited'
        e.Accept(new IncomeVisitor());
        e.Accept(new VacationVisitor());
    }
}