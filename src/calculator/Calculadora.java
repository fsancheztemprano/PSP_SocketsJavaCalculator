package calculator;

public class Calculadora {
    public static int calcular(int dato1, char operador, int dato2) {
        int result = 0;
        switch (operador) {
            case '+':
                result = dato1 + dato2;
                break;
            case '-':
                result = dato1 - dato2;
                break;
            case '*':
                result = dato1 * dato2;
                break;
            case '/':
                result = dato1 / dato2;
                break;
            default:
                result = 0;
        }
        return result;
    }
}
