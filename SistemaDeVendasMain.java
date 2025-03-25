import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

class SistemaDeVendas {
    int quantidade;
    double valorVenda;
    double descontoAplicado;
    String dataVenda;  

    public SistemaDeVendas(int quantidade, double valorVenda, double descontoAplicado, String dataVenda) {
        this.quantidade = quantidade;
        this.valorVenda = valorVenda;
        this.descontoAplicado = descontoAplicado;
        this.dataVenda = dataVenda;
    }

    @Override
    public String toString() {
        return "Data da Venda: " + dataVenda + " | Quantidade: " + quantidade + " | Valor da Venda: R$" + String.format("%.2f", valorVenda) 
                + " | Desconto: R$" + String.format("%.2f", descontoAplicado);
    }
}

public class SistemaDeVendasMain {

    private ArrayList<SistemaDeVendas> registroVendas;

    public SistemaDeVendasMain() {
        registroVendas = new ArrayList<>();
    }

    public double[] calcularDesconto(int quantidade, double preco) {
        double valorTotal = quantidade * preco;
        double desconto = 0;

        if (quantidade > 10) {
            desconto = valorTotal * 0.05; s
        }

        double valorComDesconto = valorTotal - desconto;
        return new double[]{valorComDesconto, desconto};
    }

    public void registrarVenda(int quantidade, double preco, String dataVenda) {
        double[] resultado = calcularDesconto(quantidade, preco);
        double valorComDesconto = resultado[0];
        double descontoAplicado = resultado[1];

        SistemaDeVendas venda = new SistemaDeVendas(quantidade, valorComDesconto, descontoAplicado, dataVenda);
        registroVendas.add(venda);

    
        System.out.println("Venda registrada com sucesso!");
        System.out.println(venda);
    }

    public void mostrarRegistroVendas() {
        if (registroVendas.isEmpty()) {
            System.out.println("Nenhuma venda registrada ainda.");
            return;
        }
        System.out.println("\nRegistro de Vendas:");
        for (SistemaDeVendas venda : registroVendas) {
            System.out.println(venda);
        }
    }

    public void consultarVendasPorData(String dataConsulta) {
        double totalVendas = 0;
        int totalQuantidade = 0;

        for (SistemaDeVendas venda : registroVendas) {
            if (venda.dataVenda.equals(dataConsulta)) {
                totalVendas += venda.valorVenda;
                totalQuantidade += venda.quantidade;
            }
        }

        if (totalQuantidade == 0) {
            System.out.println("Nenhuma venda registrada para a data " + dataConsulta);
        } else {
            System.out.println("Vendas para " + dataConsulta + ":");
            System.out.println("Total de Vendas: R$" + String.format("%.2f", totalVendas));
            System.out.println("Quantidade Total de Vendas: " + totalQuantidade);
        }
    }

    public void consultarVendasPorMes(String mesConsulta) {
        double totalVendas = 0;
        int totalQuantidade = 0;

        for (SistemaDeVendas venda : registroVendas) {
            if (venda.dataVenda.substring(3, 5).equals(mesConsulta)) {
                totalVendas += venda.valorVenda;
                totalQuantidade += venda.quantidade;
            }
        }

        if (totalQuantidade == 0) {
            System.out.println("Nenhuma venda registrada para o mês " + mesConsulta);
        } else {
            System.out.println("Vendas para o mês " + mesConsulta + ":");
            System.out.println("Total de Vendas: R$" + String.format("%.2f", totalVendas));
            System.out.println("Quantidade Total de Vendas: " + totalQuantidade);
        }
    }

    public void menu() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Registrar venda");
            System.out.println("2. Mostrar registro de vendas");
            System.out.println("3. Consultar vendas por data");
            System.out.println("4. Consultar vendas por mês");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            String opcao = scanner.nextLine();

            if (opcao.equals("1")) {
                System.out.print("Digite a quantidade de plantas: ");
                int quantidade = Integer.parseInt(scanner.nextLine());

                System.out.print("Digite o preço da planta: ");
                double preco = Double.parseDouble(scanner.nextLine());

                System.out.print("Digite a data da venda (dd/MM/yyyy): ");
                String dataVenda = scanner.nextLine();

                registrarVenda(quantidade, preco, dataVenda);

            } else if (opcao.equals("2")) {
                mostrarRegistroVendas();
            } else if (opcao.equals("3")) {
                System.out.print("Digite a data para consulta (dd/MM/yyyy): ");
                String dataConsulta = scanner.nextLine();
                consultarVendasPorData(dataConsulta);
            } else if (opcao.equals("4")) {
                System.out.print("Digite o mês para consulta (MM): ");
                String mesConsulta = scanner.nextLine();
                consultarVendasPorMes(mesConsulta);
            } else if (opcao.equals("5")) {
                System.out.println("Saindo do sistema...");
                break;
            } else {
                System.out.println("Opção inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        SistemaDeVendasMain sistema = new SistemaDeVendasMain();
        sistema.menu();
    }
}
