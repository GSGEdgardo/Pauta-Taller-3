public class InsPercusion extends Instrumento{
    private String tipoPercusion;
    private String altura;

    public InsPercusion(String material, int stock, String instrumento, int precio, String codigo, String tipoPercusion, String altura) {
        super(material, stock, instrumento, precio, codigo);
        this.tipoPercusion = tipoPercusion;
        this.altura = altura;
    }

    public String getTipoPercusion() {
        return tipoPercusion;
    }

    public String getAltura() {
        return altura;
    }

}
