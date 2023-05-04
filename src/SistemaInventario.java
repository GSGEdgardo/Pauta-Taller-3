import java.io.IOException;

public interface SistemaInventario {
    void mainMenu() throws IOException, IOException;
    void leerCSV(String nombreArchivo) throws IOException;
    void imprimirDetallesInstrumento(Instrumento instrumento);
    void imprimirInstrumentosPorTipo(String tipo);
}
