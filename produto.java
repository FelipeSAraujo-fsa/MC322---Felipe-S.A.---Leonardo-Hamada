public class Produto {

    private String idProd;
    private String nomeProd;
    private String statProd;
    private double quantMatPrimaNesc;

    public Produto (String idProd, String nomeProd, String statProd){

        this.idProd = idProd;
        this.nomeProd = nomeProd;
        this.statProd = statProd;
        this.quantMatPrimaNesc = 0.0;
    }

    public getDemandaMatPrima (){
        return quantMatPrimaNesc;
    }

    public getIdP(){
        return idProd;
    }

    public getNomeP(){
        return nomeProd;
    }

    public getStatP(){
        return nomeProd;
    }

    public definirQuantMatPrima(){
        
    }
    
}
