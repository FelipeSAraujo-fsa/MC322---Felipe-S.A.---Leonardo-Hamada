public class maquina {
    private String id;
    private boolean ligada;
    private int capacidadeMax;

    public maquina(String id, boolean ligada, int capacidadeMax) {
        this.id = id;
        this.ligada = ligada;
        this.capacidadeMax = capacidadeMax;
    }

    public void ligar(){
        if (ligada) {
            System.out.println("[ERRO] A Maquina " +id+ " ja esta ligada");
        }
        else {
            ligada = true;
            System.out.println("[OK] A Maquina " +id+ " foi ligada");
        }
    }
    
    public void desligar(){
        if(ligada) {
            ligada = false;
            System.out.println("[OK] A Maquina " +id+ " foi desligada");
        } else {
            System.out.println("[ERRO] A Maquina " +id+ " ja esta desligada");
        }
    }

    public produto processar(materiaPrima materiaprima, int demanda, produto produtoEscolhido) {
        if (demanda > capacidadeMax) {
            System.out.println("[ERRO] A demanda ultrapassa a capacidade maxima da maquina");
            return null;
        }

        if (!ligada) {
            System.out.println("[ERRO] A Maquina " +id+ " esta desligada e nao pode processar");
            return null;
        }

        if (!materiaprima.verificDisp(demanda)) {
            System.out.println("[ERRO] Nao ha materia prima suficiente");
            return null;
        }
        

        int quantidadeProduzida =
            demanda / produtoEscolhido.getDemandaMateriaPrima();

        materiaprima.consumir(demanda, produtoEscolhido);

        produtoEscolhido.processar(quantidadeProduzida);
        System.out.println("[OK] Foram produzidas "+ quantidadeProduzida+ " unidades de "+ produtoEscolhido.getNome());
        
        return produtoEscolhido;
    }

    public String getNome(){
        return id;
    }

    public boolean estaLigada(){
        if (ligada) {
            System.out.println("[OK] A Maquina " +id+ " esta ligada");
            return true;
        }
        else {
            System.out.println("[ERRO] A Maquina " +id+ " nao esta ligada");
            return false;
        }
    } 
}
