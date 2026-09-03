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
            System.out.println("[OK] A Esteira" + item + " ja esta ligada");
        } else {
            emMovimento = true;
            System.out.println("[OK] A Esteira" + item + " ligada");
        }
    }

    public void desligar() {
        if (emMovimento) {
            emMovimento = false;
            System.out.println("[OK] A Esteira " + item + " desligada");
        } else {
            System.out.println("[ERRO] A Esteira " + item + " ja esta desligada");
        }
    }

    public boolean adicionarItem(materiaPrima materiaTransportada) {
        if (!emMovimento) {
            System.out.println("[OK] A Esteira esta desligada");
            return false;
        }
        if (item != null) {
            System.out.println("[ERRO] A Esteira ja possui um item, nao pode aceitar outro");
            return false;
        }
        item = materiaTransportada.getId();
        System.out.println("[OK] A materia-prima " + materiaTransportada.getId() + " foi colocada na esteira");
        return true;
    }

    public boolean adicionarItem(produto produtoTransportado) {

        if (!emMovimento) {
            System.out.println("[OK] A Esteira esta desligada");
            return false;
        }
        if (item != null) {
            System.out.println("[ERRO] A Esteira ja possui um item, nao pode aceitar outro");
            return false;
        }
        item = produtoTransportado.getId();
        System.out.println("[OK] O produto "+ produtoTransportado.getId()+ " esta sendo transportado pela esteira "+ item);
        return true;
    }

    public void verificarCapacidade(produto produtoTransportado, int quantidade){
        if (capacidadeMax < quantidade) {
            System.out.println("[ERRO] A esteira Excedeu o limite de quantidade" );
        } else{
            System.out.println("[OK] O produto " + produtoTransportado.getId()+ " foi adcionado" );
        }
    }

    public String removerItem(){
        if (item == null) {
            System.out.println("[ERRO] A esteira esta vazia, não há itens para remover");
            return null;
        }
        String itemRemovido = item;
        item = null;
        System.out.println("[OK] O item "+itemRemovido+" foi removido da esteira");
        return itemRemovido;
    }

    public boolean estaEmMovimento() {
        return emMovimento;
    }
        

}
