package classes;

public class App {

    public static void main (String[] args) {
        Cliente cliente1 = new Cliente("Ana Clara","12345602214");
        Cliente cliente2 = new Cliente("Daniel", "01253748911");

        System.out.println("Clientes cadastrados: ");
        System.out.println("Nome do cliente: " + cliente1.getNome() + "\nCpf: " + cliente1.getCpf());
        System.out.println("Nome do cliente: " + cliente2.getNome() + "\nCpf: " + cliente2.getCpf());
    }
}
