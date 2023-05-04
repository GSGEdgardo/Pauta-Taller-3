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

    public void setTipoPercusion(String tipoPercusion) {
        this.tipoPercusion = tipoPercusion;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }
}
