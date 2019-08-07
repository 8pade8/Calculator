import java.util.HashMap;

public class Formula {

    private Formula operand1;
    private Formula operand2;
    private Operation operation;
    double value;
    String variable;
    HashMap<String, Double> map;
    boolean isValue = false;
    boolean isVariable = false;

    public Formula(String formula, HashMap<String, Double> map) {

        this.map = map;

        if (formula.equals("")) {
            value = 0;
            isValue = true;
            return;
        }

        if (formula.toCharArray()[0] == '(' && formula.toCharArray()[formula.length() - 1] == ')')
            formula = formula.substring(1, formula.length()- 1);

        formula = formula.trim();

        if (formula.matches("([0-9]*[.])?[0-9]+")){
            isValue = true;
            value = Double.parseDouble(formula);
            return;
        }

        if (formula.matches("[\\w,А-ЯЁ,а-яё]+[0-9]*")) {
            isVariable = true;
            variable = formula;
            return;
        }

        formulaAnalizing(formula);
    }

    private void formulaAnalizing(String s)
    {
        int counterOpenBracers=0;
        int counterCloseBrasers=0;

        for (HashMap.Entry<Character,String> entry : OperationFabric.operationsMap.entrySet()) {
            char ch = entry.getKey();
            char[] s1 = s.toCharArray();
            for (int i=0; i<s.length();i++)
            {
                if (s1[i] == '(') counterOpenBracers++;
                if (s1[i] == ')') counterCloseBrasers++;
                if (s1[i] == ch && (counterCloseBrasers == counterOpenBracers))
                {
                    operand1 = new Formula(s.substring(0, i),map);
                    operand2 = new Formula(s.substring(i + 1, s.length()), map);
                    operation = OperationFabric.getOperation(ch);
                    return;
                }
            }
        }
    }

    public double calculate() {
        if (isValue) {
            return value;
        }
        if (isVariable) {
            return map.get(variable);
        }
        return operation.calculate(operand1.calculate(), operand2.calculate());
    }

}
