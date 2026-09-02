public class materiaPrima {

    private String id;
    private String tipo;
    private String unidade;
    private int quant;
    private int quantMin;

    public materiaPrima (String id, String tipo){
        this.id = id;
        this.tipo = tipo;
        this.unidade = "KG";
        this.quant = 0;
        this.quantMin = 0;
    }

    public String getId(){
        return id;
    }
    public String getTipo(){
        return tipo;
    }
    public double getQuant(){
        return quant;
    }

    public double addEstoque (double valor){
        return this.quant += valor;
    }

    public boolean verificDisp(int demanda){
        if (quant >= demanda){
            return true;
        } else{
            return false;
        }
    }

    public void consumir(int demanda, produto produtoEscolhido){
        if (verificDisp(demanda)) {
            quant = quant - ((demanda/produtoEscolhido.getDemandaMateriaPrima())*produtoEscolhido.getDemandaMateriaPrima());
        }
        else{
            System.out.println("Não há a quantidade necessária no estoque para suprir a demanda");
        }
    }
}
