package a5_Command_Pattern;

import java.util.ArrayList;

public class CommandPattern {
    public static void main(String[] args) {
        // Create user and let her compute
        AbstractCommand command = null;
        Calculator calculator = new Calculator();
        User user = new User();

        command = new Command(calculator, '+', 150);
        user.Compute(command);
        command = new Command(calculator, '-', 50);
        user.Compute(command);
        command = new Command(calculator, '*', 20);
        user.Compute(command);
        command = new Command(calculator, '/', 10);
        user.Compute(command);

        // Undo 4 commands
        user.Undo(4);

        // Undo 10 commands
        user.Undo(10);
        
        // Redo 2 commands
        user.Redo(2);

        // Redo 10 commands
        user.Redo(10);
    }
}

interface AbstractCommand {
    public void Execute();
    public void UnExecute();
}

class Command implements AbstractCommand {
    private Calculator _calculator;
    private char _operator;
    private int _operand;

    public Command(Calculator calculator, char op, int operand) {
        _calculator = calculator;
        _operator = op;
        _operand = operand;
    }

    public void Execute() {
        _calculator.Action(_operator, _operand);
    }
    public void UnExecute() {
        _calculator.Action(Undo(_operator), _operand);
    }

    // Private helper function. Needed to get the inverse operation.
    private char Undo(char _operator) {
        switch (_operator) {
            case '+': return '-';
            case '-': return '+';
            case '*': return '/';
            case '/': return '*';
            default: return ' ';
        }
    }
}

// Receiver
class Calculator {
    private int current_value;

    public Calculator() {
        current_value = 0;
    }

    public void Action(char _operator, int operand) {
        switch (_operator) {
            case '+': current_value += operand; break;
            case '-': current_value -= operand; break;
            case '*': current_value *= operand; break;
            case '/': current_value /= operand; break;
        }
        System.out.println("Current value: " + current_value + " (following " + _operator + " " + operand + ")");
    }
}

// Invoker
class User {
    private int current;
    private ArrayList<AbstractCommand> _commands = new ArrayList<AbstractCommand>();

    public User() {current = 0;}

    public void Redo(int levels) {
        System.out.println("\n---- Redo " + levels + " levels ");
        // Perform redo operations
        for (int i = 0; i < levels; i++) {
            if(current < _commands.size()) {
                AbstractCommand command = _commands.get(current++);
                command.Execute();
            }
        }
    }

    public void Undo(int levels) {
        System.out.println("\n---- Undo " + levels + " levels ");
        // Perform undo operations
        for (int i = 0; i < levels; i++) {
            if(current > 0) {
                AbstractCommand command = _commands.get(--current);
                command.UnExecute();
            }
        }
    }

    void Compute(AbstractCommand command) {
        command.Execute();
        // Add command to undo list
        _commands.add(command);
        current++;
    }
}