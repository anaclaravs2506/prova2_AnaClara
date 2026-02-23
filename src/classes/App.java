package classes;

import java.util.List;
import java.util.Scanner;

public class App {

    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        Transacao transacao = new Transacao();
        Validacao validacao = new Validacao();

        Cliente cliente = new Cliente("Lucas", "12374539181");

        //Definindo o tipo da conta.
        //1 comum, 2 especial.
        //Alterar para 2 quando quiser que o cliente tenha uma conta especial.

        int tipo = 2;

        float saldoInicial = 500.0f;
        Conta conta = new Conta(102030, cliente, saldoInicial);

        Especial especial = null;

        if (tipo == 1) {
            // transações para conta comum
            transacao.realizarTransacao("15/02/2026", conta, "Depósito em dinheiro.", 100.0f, Conta.DEPOSITAR);
            transacao.realizarTransacao("16/02/2026", conta, "Pagamento conta luz.", 50.0f, Conta.SACAR);
            transacao.realizarTransacao("17/02/2026", conta, "Pagamento conta telefone.", 120.0f, Conta.SACAR);
            transacao.realizarTransacao("18/02/2026", conta, "Transferência entre contas.", 850.0f, Conta.DEPOSITAR);
        } else if(tipo == 2) {
            float limite = 200.0f;
            int tempo = 18;
            especial = new Especial(limite, tempo);
            
            // transações para conta especial
            transacao.realizarTransacao("15/02/2026", conta, especial, "Depósito em dinheiro.", 100.0f, Conta.DEPOSITAR);
            transacao.realizarTransacao("16/02/2026", conta, especial, "Pagamento conta luz.", 50.0f, Conta.SACAR);
            transacao.realizarTransacao("17/02/2026", conta, especial, "Pagamento conta telefone.", 120.0f, Conta.SACAR);
            transacao.realizarTransacao("18/02/2026", conta, especial, "Transferência entre contas.", 850.0f, Conta.DEPOSITAR);
        }

        boolean autorizado = autenticarUsuario(sc,validacao);

        if (!autorizado) {
            String mensagem = "Deseja redefinir usuário e senha? (s/n): ";
            boolean desejaRedefinir = perguntarSimNao(sc, mensagem);

            if (!desejaRedefinir) {
                System.out.println("Aplicação finalizada.");
                sc.close();
                return;
            }

            redefinirCredenciais(sc, validacao);
            System.out.println("\nTente acessar novamente o extrato: ");
            autorizado = autenticarUsuario(sc,validacao);

            if (!autorizado) {
                System.out.println("Acesso negado. Aplicação finalizada.");
                sc.close();
                return;
            }
        }

        //Deu tudo certo, usuario está autenticado, podemos mostrar o relatório.
        imprimirExtrato(tipo, conta, saldoInicial, transacao.getMovimentos());
        sc.close();
    }

    private static boolean autenticarUsuario (Scanner sc, Validacao validacao) {
        for (int tentativa = 1; tentativa <= 3; tentativa++) {
            System.out.print("Digite o usuário: ");
            String usuario = sc.nextLine();
            System.out.print("Digite a senha: ");
            String senha = sc.nextLine();

            boolean validaUsuario = validacao.autenciar(usuario, senha);
            if (validaUsuario) {
                return true;
            }
            System.out.println("Credenciais inválidas.");
            System.out.println("Tentativa " +tentativa + " de 3.");

        }
        return false;
    }
    private static boolean perguntarSimNao(Scanner sc, String mensagem) {
        while (true) {
            System.out.println(mensagem);
            String resp = sc.nextLine().trim().toLowerCase();
            if (resp.equals("s")) {
                return true;
            }
            if (resp.equals("n")) {
                return false;
            }
            System.out.println("Resposta inválida. Digite 's' ou 'n'");
        }
    }
    private static void redefinirCredenciais(Scanner sc, Validacao validacao) {
        while (true) {
            System.out.println("Informe o usuário para redefinir ou inserir: ");
            String usuario = sc.nextLine();
            System.out.println("Informe a nova senha (Min. 6 caracteres, 1 dígito númerico, 1 especial $ # @): ");
            String novaSenha = sc.nextLine();

            boolean senhaValida = validacao.redefinirSenha(usuario, novaSenha);
            if (senhaValida) {
                System.out.println("Senha redefinida com sucesso.");
                return;
            }
            System.out.println("Senha inválida, tente novamente.");
        }

    }
    private static void imprimirExtrato(int tipo,Conta conta, float saldoAnterior,List<Movimento> movimentos) {

        long inicio = System.currentTimeMillis();
        String tipoConta = tipo == 1 ? "comum": "especial";

        System.out.println("\nEmitindo Extrato da Conta " + tipoConta + " Número: " + conta.getNumero());
        System.out.println("Correntista: " + conta.getCorrentista().getNome());
        System.out.println("Saldo anterior: " + saldoAnterior);
        System.out.println("------------------");

        for (Movimento m : movimentos) {
            if (m == null) continue;

            System.out.println("Data: " + m.getData());
            System.out.println("Histórico: " + m.getHistorico());
            System.out.println("Valor: " + m.getValor());
            System.out.println("Operação: " + (m.getOperacao() == conta.DEPOSITAR ? "Depósito" : "Saque"));
            System.out.println("------------------");
        }

        System.out.println("Saldo atual: " + conta.getSaldo());
        long fim = System.currentTimeMillis();
        long tempoTotalEmSegundos = (fim - inicio) / 1000;
        System.out.println("CONSTRUÍDO COM SUCESSO (tempo total: " +tempoTotalEmSegundos + " segundos)");
    }
}
