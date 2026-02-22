package classes;

import static classes.Movimento.SACAR;
import static classes.Movimento.DEPOSITAR;

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

    // Saque

    public boolean sacar(float valor) throws Restricao {
        if (valor <= 0) return false;

        float taxa = valor * 0.005f;
        float valorTotal = valor + taxa;
        if (saldo < valorTotal) {
            throw new Restricao (valor, saldo);
        }

        saldo = saldo - valorTotal;
        return true;
    }

    public boolean sacar(float valor, Especial contaEspecial) throws Restricao {
        if (valor <= 0) return false;

        float percentual = contaEspecial.defineTaxacao(saldo);
        float taxa = valor * percentual;
        float valorTotal = valor + taxa;

        float totalDisponivel = saldo + contaEspecial.getLimite();
        if (totalDisponivel < valorTotal) {
            throw new Restricao(valor, saldo);
        }
        saldo = saldo - valorTotal;
        return true;
    }

    // movimentação

    public boolean movimentar(float valor, int operacao) throws Restricao {
        if (operacao == SACAR) {
            boolean resultadoSaque = sacar(valor);
            return resultadoSaque;
        }
        if (operacao == DEPOSITAR) {
            depositar(valor);
            return true;
        }
        return false;
    }

    public boolean movimentar(float valor, int operacao, Especial contaEspecial) throws Restricao {
        if (operacao == SACAR) {
            boolean resultadoSaque = sacar(valor, contaEspecial);
            return resultadoSaque;
        }
        if (operacao == DEPOSITAR) {
            depositar(valor, contaEspecial);
            return true;
        }
        return false;
    }

    public float getSaldo() {
        return saldo;
    }
}
