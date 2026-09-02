public class esteira {
    private String item;
    private boolean emMovimento;
    private int capacidadeMax;

    public esteira(String item, boolean emMovimento, int capacidadeMax) {
        this.item = item;
        this.emMovimento = emMovimento;
        this.capacidadeMax = capacidadeMax;
    }

    public void ligar() {
        if (emMovimento) {
            System.out.println("A Esteira ja esta ligada");
        } else {
            emMovimento = true;
            System.out.println("A Esteira foi ligada");
        }
    }

    public void desligar() {
        if (emMovimento) {
            emMovimento = false;
            System.out.println("A Esteira foi desligada");
        } else {
            System.out.println("A Esteira ja esta desligada");
        }
    }

    public boolean adicionarItem(materiaPrima materiaTransportada) {
        if (!emMovimento) {
            System.out.println("A Esteira esta desligada");
            return false;
        }
        if (item != null) {
            System.out.println("A Esteira ja possui um item, nao pode aceitar outro");
            return false;
        }
        item = materiaTransportada.getId();
        System.out.println("A materia-prima " + materiaTransportada.getId() + " foi colocada na esteira");
        return true;
    }

    public boolean adicionarItem(produto produtoTransportado) {
        if (!emMovimento) {
            System.out.println("A Esteira esta desligada");
            return false;
        }
        if (item != null) {
            System.out.println("A Esteira ja possui um item, nao pode aceitar outro");
            return false;
        }
        item = produtoTransportado.getId();
        System.out.println("O produto " + produtoTransportado.getId() + " esta sendo transportado pela esteira");
        return true;
    }

    // Verifica se a quantidade cabe na capacidade maxima da esteira.
    public boolean verificarCapacidade(int quantidade) {
        if (quantidade > capacidadeMax) {
            System.out.println("A esteira excedeu o limite de capacidade");
            return false;
        }
        return true;
    }

    public String removerItem() {
        if (item == null) {
            System.out.println("A esteira esta vazia, nao ha itens para remover");
            return null;
        }
        String itemRemovido = item;
        item = null;
        System.out.println("O item " + itemRemovido + " foi removido da esteira");
        return itemRemovido;
    }

    public boolean estaEmMovimento() {
        return emMovimento;
    }
}
