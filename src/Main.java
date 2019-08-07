import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) {

        Calculator calculator = new Calculator();
        calculator.setFormula(" k * x + b ");
        calculator.setParameters("k = 2 ; b = 3; x = {0 1000 1");
        do {
            double res = calculator.calculate();
            System.out.println(res + "   " + calculator.getCurrentParametres());
        } while (calculator.nextValues());
    }
}
