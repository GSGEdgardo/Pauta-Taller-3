public class ListaInstrumento {
    private Instrumento[] instrumentos;
    private int cantInstrumento;
    private int cantMax;

    public ListaInstrumento(int cantMax){
        this.cantMax = cantMax;
        instrumentos = new Instrumento[cantMax];
    }

    public Instrumento buscar(String codigo) {
        for (int i = 0; i < cantInstrumento; i++) {
            System.out.println("codigo en buscar:" + codigo +" codigo instrumento[i] buscar"+ instrumentos[i].getCodigo());
            if (instrumentos[i].getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
                return instrumentos[i];
            }
        }
        return null;
    }
    public void vender(String[] codigosVenta, int contadorVenta) {
        System.out.println("----------------------------------");
        System.out.println("BOLETA ELECTRÓNICA BEAT THE RHYTHM");
        System.out.println("----------------------------------");
        for (int i = 0; i < contadorVenta; i++) {
            System.out.println("codigoVenta en i; "+codigosVenta[i]);
            Instrumento instrumento = buscar(codigosVenta[i]);
            if (instrumento != null) {
                instrumento.setStock(instrumento.getStock() - 1);
                System.out.println("Código del instrumento: " + instrumento.getCodigo());
                System.out.println("Instrumento: " + instrumento.getInstrumento());
                System.out.println("Precio del instrumento: " + instrumento.getPrecio());
            } else {
                System.out.println("No se encontró el instrumento con código: " + codigosVenta[i]);
            }
        }
        System.out.println("-----------------------------------");
    }

    public void imprimirInstrumentos() {
        for (int i = 0; i < cantInstrumento; i++) {
            Instrumento instrumento = instrumentos[i];
            if (instrumento != null) {
                System.out.println(instrumento.getStock());
            }
        }
    }

    public void imprimirBoleta(String codigo){
        Instrumento instrumento = buscar(codigo);
    }
    public boolean estaVacia(){
        return this.cantInstrumento == 0;
    }
    public void añadirInsCuerda(InsCuerda insCuerda) {
        if (cantInstrumento < cantMax) {
            instrumentos[cantInstrumento] = insCuerda;
            cantInstrumento++;
        } else {
            System.out.println("La lista de instrumentos está llena.");
        }
    }

    public void añadirInsPercusion(InsPercusion insPercusion) {
        if (cantInstrumento < cantMax) {
            instrumentos[cantInstrumento] = insPercusion;
            cantInstrumento++;
        } else {
            System.out.println("La lista de instrumentos está llena.");
        }
    }

    public void añadirInsViento(InsViento insViento) {
        if (cantInstrumento < cantMax) {
            instrumentos[cantInstrumento] = insViento;
            cantInstrumento++;
        } else {
            System.out.println("La lista de instrumentos está llena.");
        }
    }


    public Instrumento[] getInstrumentos() {
        return instrumentos;
    }

    public void setInstrumentos(Instrumento[] instrumentos) {
        this.instrumentos = instrumentos;
    }

    public int getCantInstrumento() {
        return cantInstrumento;
    }

    public void setCantInstrumento(int cantInstrumento) {
        this.cantInstrumento = cantInstrumento;
    }

    public int getCantMax() {
        return cantMax;
    }

    public void setCantMax(int cantMax) {
        this.cantMax = cantMax;
    }
}
