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
            System.out.println("A Maquina " +id+ " ja esta ligada");
        }
        else {
            ligada = true;
            System.out.println("A Maquina " +id+ " ligada");
        }
    }
    
    public void desligar(){
        if(ligada) {
            ligada = false;
            System.out.println("A Maquina " +id+ " desligada");
        } else {
            System.out.println("A Maquina " +id+ " ja esta desligada");
        }
    }

    public produto processar(materiaPrima materiaprima, int demanda) {
        if (demanda > capacidadeMax) {
            System.out.println("A demanda ultrapassa a capacidade maxima da maquina");
            return null;
        }

        if (!ligada) {
            System.out.println("A Maquina " +id+ " esta desligada e nao pode processar");
            return null;
        }

        if (!materiaprima.verificarDisp(int demanda)) {
            System.out.println("Nao ha materia prima suficiente");
            return null;
        }

        materiaprima.consumir();

        Produto produtoFinal = new Produto();

        produto.processar();

        System.out.println("Produto processado com sucesso");

        return produtoFinal;
    }

    public String getNome(){
        return id;
    }

    public boolean estaLigada(){
        if (ligada) {
            System.out.println("A Maquina " +id+ " esta ligada");
            return true;
        }
        else {
            System.out.println("A Maquina " +id+ " nao esta ligada");
            return false;
        }
    } 

}