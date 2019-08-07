import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class Calculator {

    Formula formula;
    HashMap<String, Double> parameters = new HashMap<>();
    ParameterSaver parameterSaver;

    public void setParameters(String parameters) {
        parameterSaver = new ParameterSaver(parameters, this.parameters);
    }

    public void setFormula(String formulaString) {
        for (HashMap.Entry<Character,String> entry : OperationFabric.operationsMap.entrySet()) {
            formulaString = formulaString.replace(entry.getValue(), entry.getKey().toString());
        }
        this.formula = new Formula(formulaString, parameters);
    }

    public LinkedHashMap<String,Double> calculateMap() {
        LinkedHashMap<String, Double> map = new LinkedHashMap<>();
        do {
            parameterSaver.getValues();
            Double res = formula.calculate();
            map.put(parameterSaver.toString(), res);
        }
        while (parameterSaver.nextValues());
        return  map;
    }

    public boolean nextValues() {
        return parameterSaver.nextValues();
    }

    public double calculate() {
        parameterSaver.getValues();
        return formula.calculate();
    }

    public String getCurrentParametres() {
        return parameters.toString();
    }
}
