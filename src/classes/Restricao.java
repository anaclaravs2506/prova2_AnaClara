package classes;

public class Restricao extends Exception {
    private float saque;
    private float saldo;

    public Restricao (float saque, float saldo){
        super("Saldo insuficiente.");
        this.saque = saque;
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Saldo insuficiente.\n" +
                "Tentativa de saque: " + saque +
                "\nSaldo atual: " + saldo;
    }
}
