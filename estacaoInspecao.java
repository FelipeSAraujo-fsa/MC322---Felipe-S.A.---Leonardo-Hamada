public class estacaoInspecao {
    private boolean ativa;
    private int produtosInspecionados;

    public estacaoInspecao(boolean ativa, int produtosInspecionados){
        this.ativa = ativa;
        this.produtosInspecionados = 0;
    }

    public void ativar(){
        if (ativa) {
            System.out.println("A estação de inspeção ja esta ativa");
        }else {
            ativa = true;
            System.out.println("A estação de inspeção foi ativada");
        }
    }

    public void desativar(){
        if (ativa) {
            ativa = false;
            System.out.println("A estação de inspeção foi desativada");
        }else {
            System.out.println("A estação de inspeção ja esta desativada");
        }
    }

    public void inspecionar(produto produtoEscolhido) {
        if (ativa) {
            produtoEscolhido.inspecionarProd();
            produtosInspecionados = +produtoEscolhido.getquantProd();
            System.out.println("O Produto "+produtoEscolhido.getId()+" foi inspecionado");
        }else {
            System.out.println("A estação de inspeção esta desativada");
        }
    }
    public int getTotalInspecionados() {
        return produtosInspecionados;
    }


}
