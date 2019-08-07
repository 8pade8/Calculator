import java.util.LinkedHashMap;

public class OperationFabric {

    public static LinkedHashMap<Character, String> operationsMap = new LinkedHashMap<Character, String>() {{
        put('\u1AC8', "+");// Сложение);
        put('\u1AC7', "-");// Вычитание);
        put('/', "/"); //Деление);
        put('*', "*"); //Умножение);
        put('\u1AC6', "tan"); //Тангенс);
        put('\u1AC5', "cos"); //Косинус);
        put('\u1AC4', "sin"); //Синус);
        put('\u1AC3', "atan"); //АркТангенс);
        put('\u1AC2', "acos"); //АркКосинус);
        put('\u1AC1', "asin"); //АркСинус);
        put('\u1AC0', "abs"); //Модуль);
        put('^', "^"); //ВозведениеВСтепень);
        put('\u1AC9', "ln"); //Логарифм натуральный);
        put('\u1AD0', "lg"); //Логарифм десятичый);
    }};

    public static Operation getOperation(char operator) {
        switch (operator) {
            case '\u1AC8':
                return (a, b) -> a + b;
            case '\u1AC7':
                return (a, b) -> a - b;
            case '*':
                return (a, b) -> a * b;
            case '/':
                return (a, b) -> a / b;
            case '^':
                return (a, b) -> Math.pow(a, b);
            case '\u1AC9':
                return (a, b) -> Math.log(b);
            case '\u1AD0':
                return (a, b) -> Math.log10(b);
            case '\u1AC0':
                return (a, b) -> Math.abs(b);
            case '\u1AC6':
                return (a, b) -> Math.tan(Math.toRadians(b));
            case '\u1AC5':
                return (a, b) -> Math.cos(Math.toRadians(b));
            case '\u1AC4':
                return (a, b) -> Math.sin(Math.toRadians(b));
            case '\u1AC3':
                return (a, b) ->Math.toDegrees(Math.atan(b));
            case '\u1AC2':
                return (a, b) -> Math.toDegrees(Math.acos(b));
            case '\u1AC1':
                return (a, b) -> Math.toDegrees(Math.asin(b));
        }
        return null;
    }
}
