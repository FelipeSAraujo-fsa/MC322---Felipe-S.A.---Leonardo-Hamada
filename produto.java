public class produto {
    private String id;
    private String nome;
    private String status;
    private int quantidadeMateriaPrimaNecessaria;
    private int quantProd;
    private int quantTotal;

    public produto(String id, String nome, int quantidadeMateriaPrimaNecessaria,int quantProd) {
        this.id = id;
        this.nome = nome;
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
        this.status = "Aguardando processamento";
        this.quantProd= quantProd;
        this.quantTotal = 0;
    }

    public void processar(int quantProduzida) {
        status = "Processado";
        quantProd = quantProduzida;
        quantTotal += quantProduzida;
        System.out.println("[OK] O produto " + nome + " foi processado");
    }

    public void inspecionarProd() {
        this.status = "Inspecionado";
    }

    public void definirDemandaMateriaPrima(int demanda) {
        quantidadeMateriaPrimaNecessaria = demanda;

        System.out.println("[OK] A demanda de materia prima do produto "  + nome  + " foi definida como " + demanda);
    }

    public int getDemandaMateriaPrima() {
        return quantidadeMateriaPrimaNecessaria;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getStatus() {
        return status;
    }
    public int getquantProd() {
        return quantProd;
    }
    public int getquantTotal() {
        return quantTotal;
    }
}
