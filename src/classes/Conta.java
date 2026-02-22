package classes;

public class Conta {
    private int numero;
    private Cliente correntista;
    private float saldo;

    public Conta(){

    }

    public Conta(int numero, Cliente correntista, float saldo) {
        this.numero = numero;
        this.correntista = correntista;
        this.saldo = saldo;
    }

    // Deposito

    public void depositar(float valor) {
        if (valor <= 0) return;
        saldo = saldo+valor;
    }

    public void depositar(float valor, Especial contaEspecial){
        if (valor <= 0) return;

        // Estou considerando que "usar o limite" significa que o saldo é menor do que 0.

        boolean naoUsouLimite = saldo >= 0;

        if (naoUsouLimite) {
            float taxa = valor * 0.0005f;
            float valorFinalDeposito = valor - taxa;
            saldo = saldo + valorFinalDeposito;
            return;
        }

        saldo = saldo + valor;
    }

}
