public class produto {
    private String id;
    private String nome;
    private String status;
    private int quantidadeMateriaPrimaNecessaria;

    public produto(String id, String nome, int quantidadeMateriaPrimaNecessaria) {
        this.id = id;
        this.nome = nome;
        this.quantidadeMateriaPrimaNecessaria = quantidadeMateriaPrimaNecessaria;
        this.status = "Aguardando processamento";
    }

    public void processar() {
        status = "Processado";

        System.out.println("O produto " + nome + " foi processado");
    }

    public void definirDemandaMateriaPrima(int demanda) {
        quantidadeMateriaPrimaNecessaria = demanda;

        System.out.println(
            "A demanda de materia prima do produto " 
            + nome 
            + " foi definida como " 
            + demanda
        );
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
}