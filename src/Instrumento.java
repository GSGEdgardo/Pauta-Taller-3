public class Instrumento {
    private String material;
    private int stock;
    private String instrumento;
    private int precio;
    private String codigo;

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

    public void setMaterial(String material) {
        this.material = material;
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

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
