import java.awt.datatransfer.StringSelection;
import java.io.PrintWriter;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import javax.swing.*;
public class SistemaInventarioImpl implements SistemaInventario{
    private ListaInstrumento listaInstrumento;
    private BufferedReader lector;
    private String linea;
    private String partes[] = null;
    @Override
    public void mainMenu() throws IOException {
        listaInstrumento = new ListaInstrumento(9999);
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.println("Bienvenido al sistema de inventario de Beat the Rhythm, que desea hacer?\n" +
                    " 1) Agregar Instrumento\n 2) Vender Instrumento \n 3) Consultar Inventario \n 4) Salir");

            int opcion = input.nextInt();
            input.nextLine();
            switch(opcion){
                case 1:
                    System.out.println("Se ha ingresado a Agregar Instrumento");
                    System.out.println("Ingrese la ruta del archivo CSV:");
                    String ubicacionArchivo = input.nextLine();
                    leerCSV(ubicacionArchivo);

                    break;
                case 2:
                    System.out.println("Se ha ingresado a Vender Instrumento");
                    boolean cicloVenta = false;
                    int contadorVenta = 0;
                    String[] codigosVenta = new String[100];
                    String verificador = "";
                    while (!cicloVenta) {
                        System.out.println("Que instrumento se va a vender? ingrese su código");
                        String codigoBuscar = input.nextLine();
                        codigosVenta[contadorVenta] = codigoBuscar;
                        System.out.println("Desea añadir otro instrumento? S/N");
                        verificador = input.nextLine();
                        if(verificador.equalsIgnoreCase("N")){
                            cicloVenta = true;
                        }
                        contadorVenta++;
                    }
                    listaInstrumento.vender(codigosVenta,contadorVenta);
                break;
                case 3:
                    boolean menuConsultaInventario = false;
                    while (!menuConsultaInventario) {
                        System.out.println("Ingrese la opción de búsqueda:");
                        System.out.println(" 1) Buscar por tipo");
                        System.out.println(" 2) Buscar por código");
                        System.out.println(" 3) Volver al menú principal");

                        int opcionConsultaInventario = input.nextInt();
                        input.nextLine();
                        switch (opcionConsultaInventario) {
                            case 1 -> {
                                System.out.println("Ingrese el tipo de instrumento (cuerda, percusion, viento):");
                                String tipo = input.nextLine();
                                imprimirInstrumentosPorTipo(tipo);
                            }
                            case 2 -> {
                                System.out.println("Ingrese el código del instrumento:");
                                String codigo = input.nextLine();
                                Instrumento instrumento = listaInstrumento.buscar(codigo);
                                if (instrumento != null) {
                                    imprimirDetallesInstrumento(instrumento);
                                } else {
                                    System.out.println("No se encontró ningún instrumento con el código " + codigo);
                                }
                            }
                            case 3 -> {
                                menuConsultaInventario = true;
                            }
                            default -> System.out.println("Has seleccionado una opción incorrecta.");
                        }
                    }
                break;
                case 4:
                    System.out.println("Cerrando el sistema...");
                    exit = true;
                    break;
                default:
                    System.out.println("Has seleccionado una opción incorrecta.");
            }
        }
    }

    @Override
    public void leerCSV(String nombreArchivo) throws IOException {
        try {
            this.lector = new BufferedReader(new FileReader(nombreArchivo));
            lector.readLine();
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                String codigo = partes[0];
                int precio = Integer.parseInt(partes[1]);
                int stock = Integer.parseInt(partes[2]);
                String instrumento = partes[3];

                Instrumento instrumentoExistente = listaInstrumento.buscar(codigo);
                if (instrumentoExistente != null) {
                    instrumentoExistente.setStock(instrumentoExistente.getStock() + 1);
                    System.out.println("Se encontró un instrumento repetido. Se incrementó el stock");
                } else {
                    if (partes.length == 8) {
                        String tipoCuerda = partes[4];
                        int numeroCuerdas = Integer.parseInt(partes[5]);
                        String material = partes[6];
                        String tipo = partes[7];
                        InsCuerda insCuerda = new InsCuerda(material, stock, instrumento, precio, codigo, tipoCuerda, numeroCuerdas, tipo);
                        listaInstrumento.añadirInsCuerda(insCuerda);
                        System.out.println("Se añadió instrumento de cuerda con éxito");
                    } else if (partes.length == 7) {
                        String tipoPercusion = partes[4];
                        String material = partes[5];
                        String altura = partes[6];
                        InsPercusion insPercusion = new InsPercusion(material, stock, instrumento, precio, codigo, tipoPercusion, altura);
                        listaInstrumento.añadirInsPercusion(insPercusion);
                        System.out.println("Se añadió instrumento de percusión con éxito");
                    } else if (partes.length == 5) {
                        String material = partes[4];
                        InsViento insViento = new InsViento(material, stock, instrumento, precio, codigo);
                        listaInstrumento.añadirInsViento(insViento);
                        System.out.println("Se añadió instrumento de viento con éxito");
                    } else {
                        System.out.println("Se encontró una línea que no cumple con el formato");
                    }
                }
            }
            lector.close();
            linea = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    @Override
    public void imprimirDetallesInstrumento(Instrumento instrumento) {
        System.out.println("Detalles del instrumento:");
        System.out.println("Código: " + instrumento.getCodigo());
        System.out.println("Precio: " + instrumento.getPrecio());
        System.out.println("Instrumento: " + instrumento.getInstrumento());
        System.out.println("Stock disponible: " + instrumento.getStock());

        if (instrumento instanceof InsCuerda) {
            InsCuerda insCuerda = (InsCuerda) instrumento;
            System.out.println("Tipo de cuerda: " + insCuerda.getTipoCuerda());
            System.out.println("Número de cuerdas: " + insCuerda.getNumeroCuerda());
            System.out.println("Material: " + insCuerda.getMaterial());
            System.out.println("Tipo: " + insCuerda.getTipo());
        } else if (instrumento instanceof InsPercusion) {
            InsPercusion insPercusion = (InsPercusion) instrumento;
            System.out.println("Tipo de percusión: " + insPercusion.getTipoPercusion());
            System.out.println("Material: " + insPercusion.getMaterial());
            System.out.println("Altura: " + insPercusion.getAltura());
        } else if (instrumento instanceof InsViento) {
            InsViento insViento = (InsViento) instrumento;
            System.out.println("Material: " + insViento.getMaterial());
        }

        System.out.println("----------------------------------");
    }
    @Override
    public void imprimirInstrumentosPorTipo(String tipo) {
        System.out.println("Instrumentos de tipo " + tipo + ":");
        Instrumento[] instrumentos = listaInstrumento.getInstrumentos();
        for (Instrumento instrumento : instrumentos) {
            if (instrumento instanceof InsCuerda && tipo.equalsIgnoreCase("cuerda")) {
                imprimirDetallesInstrumento(instrumento);
            } else if (instrumento instanceof InsPercusion && tipo.equalsIgnoreCase("percusion")) {
                imprimirDetallesInstrumento(instrumento);
            } else if (instrumento instanceof InsViento && tipo.equalsIgnoreCase("viento")) {
                imprimirDetallesInstrumento(instrumento);
            }
        }
    }
}
