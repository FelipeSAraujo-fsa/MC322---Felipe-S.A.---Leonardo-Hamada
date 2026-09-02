public class esteira {
    private String item;
    private boolean emMovimento;
    private int capacidadeMax;

    public esteira(String item, boolean emMovimento, int capacidadeMax){
        this.item = item;
        this.emMovimento = emMovimento;
        this.capacidadeMax = capacidadeMax;
    }

    public void ligar(){
        if (emMovimento) {
            System.out.println("A Esteira" +item+ " ja esta ligada");
        }
        else {
            emMovimento = true;
            System.out.println("A Esteira" +item+ " ligada");
        }
    }

    public void desligar(){
        if(emMovimento) {
            emMovimento = false;
            System.out.println("A Esteira " +item+ " desligada");
        } else {
            System.out.println("A Esteira " +item+ " ja esta desligada");
        }
    }
}