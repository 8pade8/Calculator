import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ParameterSaver {

    HashMap<String, Double> parametersMap;
    ArrayList<Parameter> parametersList = new ArrayList<>();

    public ParameterSaver(String parameters, HashMap<String,Double> parametersMap) { //T=1; R=2;
        this.parametersMap = parametersMap;
        String[] strings = parameters.split(";");
        for (String string : strings) {
            string = string.trim();
            if (!string.equals("")) {
                Parameter p = new Parameter(string);
                parametersList.add(p);
            }
        }

        for (int i = 0; i < parametersList.size()-1; i++) {
           parametersList.get(i).setParent(parametersList.get(i+1));
        }

    }

    public void getValues() {
        for (Parameter parameter : parametersList) {
            String key = parameter.getName();
            double value = parameter.getValue();
            if (parametersMap.containsKey(key)) {
                parametersMap.replace(key, value);
            } else {
                parametersMap.put(key, value);
            }
        }
    }

    public boolean nextValues() {
        return parametersList.get(0).next();
    }

    @Override
    public String toString() {
        return  parametersList.toString();
    }

    static class Parameter {
        String name;
        ArrayList<Double> values = new ArrayList<>();
        int currentValueCount = 0;
        Parameter parent;

        Parameter(String s) { // T = 1 2 3 7 // K = { 1 100 2
            parent = null;
            String[] strings = s.split("\\s*=\\s*");
            s = strings[1];
            name = strings[0].trim();
            if (s.contains("{")) {
                s = s.replace("{", "");
                s = s.trim();
                String sarr[] = s.split("\\s+");
                try {

                    double step = Double.parseDouble(sarr[2]);
                    double start = Double.parseDouble(sarr[0]);
                    double end = Double.parseDouble(sarr[1]);
                    for (double i = start; i <= end; i += step) {
                        values.add(i);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка входных данных");
                    throw new  NumberFormatException();
                }
            } else {
                String sarr[] = s.split("\\s+");
                for (String s1 : sarr) {
                    values.add(Double.parseDouble(s1));
                }
            }
        }

        public void setParent(Parameter parent) {
            this.parent = parent;
        }

        public String getName() {
            return name;
        }

        public double getValue() {
            return values.get(currentValueCount);
        }

        private boolean next() {
            if (currentValueCount == values.size() - 1) {
                currentValueCount = 0;
                if (parent == null) {
                    return false;
                }
                return parent.next();
            } else {
                currentValueCount++;
                return true;
            }
        }

        @Override
        public String toString() {
            return "{"  + name +
                    " = " + values.get(currentValueCount) +
                    '}';
        }
    }
}

