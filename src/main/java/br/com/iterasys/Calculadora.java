package br.com.iterasys;

public class Calculadora {

    public static int somarInteiros(int primeiroNumero, int segundoNumero) {
        int resultado = primeiroNumero + segundoNumero;
        System.out.println("A soma de " + primeiroNumero + " + " + segundoNumero + " é " + resultado + ".");
        return resultado;
    }

    public static int subtrairInteiros(int primeiroNumero, int segundoNumero) {
        int resultado = primeiroNumero - segundoNumero;
        System.out.println("A subtração de " + primeiroNumero + " - " + segundoNumero + " é " + resultado + ".");
        return resultado;
    }

    public static int multiplicarInteiros(int primeiroNumero, int segundoNumero) {
        int resultado = primeiroNumero * segundoNumero;
        System.out.println("A multiplicação de " + primeiroNumero + " * " + segundoNumero + " é " + resultado + ".");
        return resultado;
    }

    public static int dividirInteiros(int primeiroNumero, int segundoNumero) {
        int resultado = primeiroNumero / segundoNumero;
        System.out.println("A divisão de " + primeiroNumero + " / " + segundoNumero + " é " + resultado + ".");
        return resultado;
    }
}
