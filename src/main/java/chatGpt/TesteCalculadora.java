package chatGpt;

public class TesteCalculadora {
    public static void main(String[] args) {
        double a = 20;
        double b = 10;

        System.out.println("Adição: " + CalculadoraGpt.adicao(a, b));
        System.out.println("Subtração: " + CalculadoraGpt.subtracao(a, b));
        System.out.println("Multiplicação: " + CalculadoraGpt.multiplicacao(a, b));
        System.out.println("Divisão: " + CalculadoraGpt.divisao(a, b));
    }
}