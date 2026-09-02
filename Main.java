import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // ---- Instanciação dos elementos da planta (definidos no código) ----
        materiaPrima madeira = new materiaPrima("MP001", "Madeira de Cedro");
        madeira.addEstoque(100); // estoque inicial

        produto[] produtos = new produto[3];
        produtos[0] = new produto("P001", "Ukulele Soprano", 8, 0);
        produtos[1] = new produto("P002", "Cavaquinho", 12, 0);
        produtos[2] = new produto("P003", "Violao Classico", 25, 0);

        maquina torno = new maquina("Torno Luthieria 3000", false, 30);
        esteira esteiraTransporte = new esteira(null, false, 50);
        estacaoInspecao inspecao = new estacaoInspecao(false, 0);

        exibirIntroducao();

        boolean sair = false;
        while (!sair) {
            exibirMenu(madeira, produtos);
            int opcao = lerInteiro(sc, "Escolha: ");

            switch (opcao) {
                case 1:
                    iniciarProducao(sc, madeira, produtos, torno, esteiraTransporte, inspecao);
                    break;
                case 2:
                    consultarEstoque(madeira);
                    break;
                case 3:
                    adicionarEstoque(sc, madeira);
                    break;
                case 4:
                    sair = true;
                    System.out.println("\nEncerrando a producao. Ate a proxima!");
                    break;
                default:
                    System.out.println("Opcao invalida. Tente novamente.");
            }
        }

        sc.close();
    }

    // ---------------------------------------------------------------
    // Telas
    // ---------------------------------------------------------------

    private static void exibirIntroducao() {
        System.out.println("========================================");
        System.out.println("        LUTHIERIA DO VALE");
        System.out.println("  \"Da madeira bruta a musica em suas maos\"");
        System.out.println("========================================");
        System.out.println("Bem-vindos a nossa fabrica de instrumentos");
        System.out.println("de corda artesanais! Aqui transformamos");
        System.out.println("madeira de cedro em ukuleles, cavaquinhos");
        System.out.println("e violoes.");
        System.out.println();
        System.out.println("Desenvolvido por: [Nome 1] e [Nome 2]");
        System.out.println("========================================\n");
    }

    private static void exibirMenu(materiaPrima madeira, produto[] produtos) {
        System.out.println("========================================");
        System.out.println("Materia-Prima: " + madeira.getId() + " - " + madeira.getTipo());
        System.out.println("Quantidade em estoque: " + madeira.getQuant() + " kg");
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

    // ---------------------------------------------------------------
    // Fluxo de producao (etapas 7 a 15 do enunciado)
    // ---------------------------------------------------------------

    private static void iniciarProducao(Scanner sc, materiaPrima madeira, produto[] produtos,
                                         maquina torno, esteira esteiraTransporte, estacaoInspecao inspecao) {

        int escolha = lerInteiro(sc, "Selecione o produto (1-" + produtos.length + "): ");
        if (escolha < 1 || escolha > produtos.length) {
            System.out.println("Produto invalido.\n");
            return;
        }
        produto produtoEscolhido = produtos[escolha - 1];

        int demanda = lerInteiro(sc, "Informe a demanda de materia-prima (kg): ");
        if (demanda <= 0) {
            System.out.println("A demanda deve ser um numero positivo.\n");
            return;
        }

        System.out.println("\n[OK] Verificando disponibilidade de " + madeira.getTipo() + "...");
        if (!madeira.verificDisp(demanda)) {
            System.out.println("[ERRO] Estoque insuficiente de " + madeira.getTipo()
                    + ". Disponivel: " + madeira.getQuant() + " kg.\n");
            return;
        }
        System.out.println("[OK] Demanda de " + demanda + " kg pode ser atendida.");

        if (!esteiraTransporte.estaEmMovimento()) {
            esteiraTransporte.ligar();
        }
        torno.ligar();
        inspecao.ativar();

        // Transporte 1: materia-prima ate a maquina
        if (!esteiraTransporte.adicionarItem(madeira)) {
            System.out.println("[ERRO] Nao foi possivel transportar a materia-prima.\n");
            return;
        }
        esteiraTransporte.removerItem();
        System.out.println("[OK] Materia-prima transportada ate a maquina.");

        // Processamento
        produto resultado = torno.processar(madeira, demanda, produtoEscolhido);
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
        System.out.println("Estoque restante de " + madeira.getTipo() + ": " + madeira.getQuant() + " kg\n");
    }

    private static void consultarEstoque(materiaPrima madeira) {
        System.out.println("\n---- ESTOQUE ATUAL ----");
        System.out.println(madeira.getId() + " - " + madeira.getTipo() + ": " + madeira.getQuant() + " kg");
        System.out.println("------------------------\n");
    }

    private static void adicionarEstoque(Scanner sc, materiaPrima madeira) {
        int quantidade = lerInteiro(sc, "Quantidade a adicionar ao estoque (kg): ");
        if (quantidade <= 0) {
            System.out.println("Quantidade invalida.\n");
            return;
        }
        madeira.addEstoque(quantidade);
        System.out.println("[OK] Estoque atualizado: " + madeira.getQuant() + " kg\n");
    }

    // ---------------------------------------------------------------
    // Leitura de entrada numerica (apenas numeros sao aceitos)
    // ---------------------------------------------------------------

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
                System.out.println("Entrada invalida. Digite apenas numeros inteiros.");
            }
        }
        return valor;
    }
}
