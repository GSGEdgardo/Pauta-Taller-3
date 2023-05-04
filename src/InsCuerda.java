public class InsCuerda extends Instrumento{
    private String tipoCuerda;
    private int numeroCuerda;
    private String tipo;

    public InsCuerda(String material, int stock, String instrumento, int precio, String codigo, String tipoCuerda, int numeroCuerda, String tipo) {
        super(material, stock, instrumento, precio, codigo);
        this.tipoCuerda = tipoCuerda;
        this.numeroCuerda = numeroCuerda;
        this.tipo = tipo;
    }

    public String getTipoCuerda() {
        return tipoCuerda;
    }
    public int getNumeroCuerda() {
        return numeroCuerda;
    }

    public String getTipo() {
        return tipo;
    }

}
