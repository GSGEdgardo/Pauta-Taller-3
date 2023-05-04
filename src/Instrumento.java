public class Instrumento {
    private String codigo;
    private int precio;
    private int stock;
    private String instrumento;
    private String material;

    public Instrumento(String material, int stock, String instrumento, int precio, String codigo) {
        this.material = material;
        this.stock = stock;
        this.instrumento = instrumento;
        this.precio = precio;
        this.codigo = codigo;
    }

    public String getMaterial() {
        return material;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public int getPrecio() {
        return precio;
    }

    public String getCodigo() {
        return codigo;
    }
}
