package classes;

import java.util.ArrayList;
import java.util.List;

public class Transacao {
    private List<Movimento> movimentos;

    public Transacao() {
        movimentos = new ArrayList<>();
    }

   public boolean realizarTransacao(String data,Conta conta,String historico,float valor,int operacao) {
        Movimento movimento = new Movimento(data, conta, historico, valor,operacao);

        try {
            boolean resultadoMovimentacao = movimento.movimentar();
            if (resultadoMovimentacao) {
                movimentos.add(movimento);
                return true;
            }
            return false;

        } catch(Restricao e) {
            System.out.println(e);
            return false;
        }
   }

   public boolean realizarTransacao(String data,Conta conta, Especial contaEspecial, String historico,float valor,int operacao) {
       Movimento movimento = new Movimento(data, conta, historico, valor,operacao);

       try {
           boolean resultadoMovimentacao = movimento.movimentar(contaEspecial);
           if (resultadoMovimentacao) {
               movimentos.add(movimento);
               return true;
           }
           return false;
       } catch(Restricao e) {
           System.out.println(e);
           return false;
       }
   }

   public void estornarTransacao() {
       for (int i = 0; i < movimentos.size(); i++) {
           movimentos.set(i, null);
       }
   }

   public List<Movimento> getMovimentos() {
       return movimentos;
   }
}
