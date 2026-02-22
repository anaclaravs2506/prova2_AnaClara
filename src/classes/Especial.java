package classes;

public class Especial {
    private float limite;
    private int tempo;

    public Especial(){

    }

    public Especial(float limite, int tempo) {
        this.limite = limite;
        this.tempo = tempo;
    }

    /*
    Crie o método defineTaxacao(float) do tipo float cuja regra de cálculo é:
    Se tempo < 12 meses então a taxa vale 0.2%
    Se tempo  >= 12 meses e tempo <= 23 meses e saldo <= 0 então a taxa vale 0.2%
    Se tempo  >= 12 meses e tempo <= 23 meses e saldo > 0 então a taxa vale 0.15%
    Se tempo > 23 meses e saldo <= 0 então a taxa vale 0.15%
    Se tempo > 23 meses e saldo > 0 então a taxa vale 0.1%
     */

    public float defineTaxacao(float saldo) {
        if(tempo < 12) {
            return 0.002f;
        }

        if(tempo >= 12 && tempo <= 23) {
            if(saldo <= 0) {
                return 0.002f;
            }
            return 0.0015f;
        }

        // O tempo maior que 23

        if(saldo <=0) {
            return 0.0015f;
        }
        return 0.001f;

    }
}
