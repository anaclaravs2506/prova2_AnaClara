package classes;

public class Movimento {
    private String data;
    private Conta conta;
    private String historico;
    private float valor;
    private int operacao;
    private float saldoAnterior;

    public static final int SACAR = 0;
    public static final int DEPOSITAR = 1;

    public Movimento(String data, Conta conta, String historico, float valor, int operacao) {
        this.data = data;
        this.conta = conta;
        this.historico = historico;
        this.valor = valor;
        this.operacao = operacao;
    }
    public boolean movimentar()throws Restricao {
        saldoAnterior = conta.getSaldo();
        boolean resultadoMovimentacao = conta.movimentar(valor, operacao);
        return resultadoMovimentacao;
    }

    public boolean movimentar(Especial contaEspecial) throws Restricao {
        saldoAnterior = conta.getSaldo();
        boolean resultadoMovimentacao = conta.movimentar(valor, operacao, contaEspecial);
        return resultadoMovimentacao;
    }
}
