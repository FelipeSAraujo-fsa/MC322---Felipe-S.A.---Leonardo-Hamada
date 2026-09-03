import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        materiaPrima cobre = new materiaPrima("CU001", "Cobre");
        cobre.addEstoque(100); // estoque inicial

        produto[] produtos = new produto[3];
        produtos[0] = new produto("PCB001", "PCB de Seguranca", 5, 0);
        produtos[1] = new produto("PCB002", "PCB de Controle", 10, 0);
        produtos[2] = new produto("PCB003", "PCB de Telemetria", 15, 0);

        maquina torno = new maquina("Laminadora TS1600", false, 45);
        esteira esteiraTransporte = new esteira(null, false, 50);
        estacaoInspecao inspecao = new estacaoInspecao(false, 0);

        exibirIntroducao();

        boolean sair = false;
        while (!sair) {
            exibirMenu(cobre, produtos);
            int opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1:
                    iniciarProducao(sc, cobre, produtos, torno, esteiraTransporte, inspecao);
                    break;
                case 2:
                    consultarEstoque(cobre, produtos);
                    break;
                case 3:
                    adicionarEstoque(sc, cobre);
                    break;
                case 4:
                    sair = true;
                    System.out.println("\n[OK] Encerrando a producao. Ate a proxima!");
                    break;
                default:
                    System.out.println("[ERRO] Opcao invalida. Tente novamente.");
            }
        }

        sc.close();
    }

    private static void exibirIntroducao() {
        System.out.println("========================================");
        System.out.println("            PCB FH FACTORY");
        System.out.println("   \"Circuitos eletronicos impressos\"");
        System.out.println("========================================");
        System.out.println("Bem-vindos a nossa fabrica de placas de");
        System.out.println("circuito impresso! Aqui transformamos");
        System.out.println("cobre em PCBs de seguranca, controle");
        System.out.println("e telemetria.");
        System.out.println();
        System.out.println("Desenvolvido por: Felipe Araujo e Leonardo Tetsuo");
        System.out.println("========================================\n");
    }

    private static void exibirMenu(materiaPrima cobre, produto[] produtos) {
        System.out.println("========================================");
        System.out.println("Materia-Prima: " + cobre.getId() + " - " + cobre.getTipo());
        System.out.println("Quantidade em estoque: " + cobre.getQuant() + " kg");
        System.out.println();
        System.out.println("Produtos Disponiveis:");
        for (int i = 0; i < produtos.length; i++) {
            System.out.println((i + 1) + " - " + produtos[i].getNome()
                    + " (demanda: " + produtos[i].getDemandaMateriaPrima() + " kg)");
        }
        System.out.println("========================================");
        System.out.println("MENU PRINCIPAL");
        System.out.println("========================================");
        System.out.println("1 - Iniciar producao");
        System.out.println("2 - Consultar estoque");
        System.out.println("3 - Adicionar materia-prima ao estoque");
        System.out.println("4 - Sair");
    }


    private static void iniciarProducao(Scanner sc, materiaPrima cobre, produto[] produtos,
                                         maquina torno, esteira esteiraTransporte, estacaoInspecao inspecao) {

        int escolha = lerInteiro(sc, "Selecione o produto (1-" + produtos.length + "): ");
        if (escolha < 1 || escolha > produtos.length) {
            System.out.println("[ERRO] Produto invalido.\n");
            return;
        }
        produto produtoEscolhido = produtos[escolha - 1];

        int demanda = lerInteiro(sc, "Informe a demanda de materia-prima (kg): ");
        if (demanda <= 0) {
            System.out.println("[ERRO] A demanda deve ser um numero positivo.\n");
            return;
        }
        if (demanda < produtoEscolhido.getDemandaMateriaPrima()) {
            System.out.println("[ERRO] Demanda de " + demanda + " kg e insuficiente para produzir ao menos"
                    + " 1 unidade de " + produtoEscolhido.getNome() + " (necessario: "
                    + produtoEscolhido.getDemandaMateriaPrima() + " kg).\n");
            return;
        }

        System.out.println("\n[OK] Verificando disponibilidade de " + cobre.getTipo() + "...");
        if (!cobre.verificDisp(demanda)) {
            System.out.println("[ERRO] Estoque insuficiente de " + cobre.getTipo()
                    + ". Disponivel: " + cobre.getQuant() + " kg.\n");
            return;
        }
        System.out.println("[OK] Demanda de " + demanda + " kg pode ser atendida.");

        if (!esteiraTransporte.estaEmMovimento()) {
            esteiraTransporte.ligar();
        }
        torno.ligar();
        inspecao.ativar();

        // Transporte 1: materia-prima ate a maquina
        if (!esteiraTransporte.adicionarItem(cobre)) {
            System.out.println("[ERRO] Nao foi possivel transportar a materia-prima.\n");
            return;
        }
        esteiraTransporte.removerItem();
        System.out.println("[OK] Materia-prima transportada ate a maquina.");

        // Processamento
        produto resultado = torno.processar(cobre, demanda, produtoEscolhido);
        if (resultado == null) {
            System.out.println("[ERRO] A producao nao pode ser concluida.\n");
            return;
        }

        // Transporte 2: produto ate a inspecao
        if (!esteiraTransporte.adicionarItem(resultado)) {
            System.out.println("[ERRO] Nao foi possivel transportar o produto ate a inspecao.\n");
            return;
        }
        esteiraTransporte.removerItem();
        System.out.println("[OK] Produto " + resultado.getId() + " transportado para inspecao.");

        // Inspecao
        inspecao.inspecionar(resultado);

        System.out.println("========================================");
        System.out.println("PRODUCAO CONCLUIDA COM SUCESSO");
        System.out.println("========================================");
        System.out.println("Status final do produto: " + resultado.getStatus());
        System.out.println("Estoque restante de " + cobre.getTipo() + ": " + cobre.getQuant() + " kg\n");
    }

    private static void consultarEstoque(materiaPrima cobre, produto[] produtos) {
        System.out.println("\n---- ESTOQUE ATUAL ----");
        System.out.println(cobre.getId() + " - " + cobre.getTipo() + ": " + cobre.getQuant() + " kg");
        System.out.println("------------------------");
        System.out.println("PLACAS PRODUZIDAS ATE AGORA");
        for (produto p : produtos) {
            System.out.println(p.getId() + " - " + p.getNome() + ": " + p.getquantTotal() + " unidades");
        }
        System.out.println("------------------------\n");
    }

    private static void adicionarEstoque(Scanner sc, materiaPrima cobre) {
        int quantidade = lerInteiro(sc, "Quantidade a adicionar ao estoque (kg): ");
        if (quantidade <= 0) {
            System.out.println("[ERRO] Quantidade invalida.\n");
            return;
        }
        cobre.addEstoque(quantidade);
        System.out.println("[OK] Estoque atualizado: " + cobre.getQuant() + " kg\n");
    }

    private static int lerInteiro(Scanner sc, String mensagem) {
        int valor = -1;
        boolean valido = false;
        while (!valido) {
            System.out.print(mensagem);
            String entrada = sc.nextLine().trim();
            try {
                valor = Integer.parseInt(entrada);
                valido = true;
            } catch (NumberFormatException e) {
                System.out.println("[ERRO] Entrada invalida. Digite apenas numeros inteiros.");
            }
        }
        return valor;
    }
}