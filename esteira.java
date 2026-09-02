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
            System.out.println("A Esteira" + item + " ja esta ligada");
        } else {
            emMovimento = true;
            System.out.println("A Esteira" + item + " ligada");
        }
    }

    public void desligar() {
        if (emMovimento) {
            emMovimento = false;
            System.out.println("A Esteira " + item + " desligada");
        } else {
            System.out.println("A Esteira " + item + " ja esta desligada");
        }
    }

    public void adcicionarItem(produto produtoTransportado) {

        if (!emMovimento) {
            System.out.println("A Esteira esta desligada");
            return;
        }

        System.out.println(
                "O produto "
                        + produtoTransportado.getId()
                        + " esta sendo transportado pela esteira "
                        + item);
    }

    public void verificarCapacidade(produto produtoTransportado, int quantidade){
        if (capacidadeMax < quantidade) {
            System.out.println("A esteira Excedeu o limite de quantidade" );
        } else{
            System.out.println("O produto " + produtoTransportado.getId()+ " foi adcionado" );
        }
    }

    public void removerItem(produto produtoRetirado){
        System.out.println("O produto" + produtoRetirado.getId()+ "foi retirado" );
    }
}