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
            if (instrumentos[i].getCodigo().trim().equalsIgnoreCase(codigo.trim())) {
                return instrumentos[i];
            }
        }
        return null;
    }
    public void vender(String[] codigosVenta, int contadorVenta) {
        boolean imprimirBoleta = false;
        int totalVenta = 0;
        String boleta = ("----------------------------------\n");
        boleta += ("BOLETA ELECTRÓNICA BEAT THE RHYTHM\n");
        boleta += ("----------------------------------\n");
        for (int i = 0; i < contadorVenta; i++) {
            Instrumento instrumento = buscar(codigosVenta[i]);
            if (instrumento != null && instrumento.getStock() >= 1) {
                instrumento.setStock(instrumento.getStock() - 1);
                totalVenta += instrumento.getPrecio();
                boleta += ("Código del instrumento: " + instrumento.getCodigo()+"\n");
                boleta += ("Instrumento: " + instrumento.getInstrumento()+"\n");
                boleta += ("Precio del instrumento: " + instrumento.getPrecio()+"\n");
                boleta += ("----------------------------------\n");
                imprimirBoleta = true;
            } else {
                if(instrumento.getStock()==0){
                    System.out.println("No hay stock del instrumento");
                }else{
                    System.out.println("Codigo invalido");
                }
            }
        }if(imprimirBoleta == true) {
            boleta += ("total: $"+ totalVenta +"\n");
            boleta += ("----------------------------------\n");
            System.out.println(boleta);
        }else{
            System.out.println("No se vendieron instrumentos");
        }
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

}
