import java.awt.datatransfer.StringSelection;
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
    public void mainMenu() throws IOException{
        listaInstrumento = new ListaInstrumento(9999);
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        while(!exit){
            System.out.println("Bienvenido al sistema de inventario de Beat the Rhythm, que desea hacer?\n" +
                    " 1) Agregar Instrumento\n 2) Vender Instrumento \n 3) Consultar Inventario \n 4) Salir");

            int opcion = input.nextInt();
            input.nextLine();
            switch(opcion){
                case 1 -> {
                    System.out.println("Se ha ingresado a Agregar Instrumento");
                    System.out.println("Ingrese la ruta del archivo CSV:");
                    String ubicacionArchivo = input.nextLine();
                    if(listaInstrumento.estaVacia()) {
                        leerCSV(ubicacionArchivo);
                    }else{
                        modificarCSV(ubicacionArchivo);
                    }
                }
                case 2 -> {
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
                }
                case 3 -> {
                    System.out.println("Opcion 3");
                    listaInstrumento.imprimirInstrumentos();
                }
                case 4 -> {System.out.println("Opcion 4");}
                default -> System.out.println("Has seleccionado una opción incorrecta.");
            }
        }
    }
    public void comprobarStock() throws IOException{

    }
    public void leerCSV(String nombreArchivo) throws IOException {
        try {
            this.lector = new BufferedReader(new FileReader(nombreArchivo));
            while ((linea = lector.readLine()) != null) {
                String[] partes = linea.split(",");
                String codigo = partes[0];
                int precio = Integer.parseInt(partes[1]);
                int stock = Integer.parseInt(partes[2]);
                String instrumento = partes[3];

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
            lector.close();
            linea = null;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
    public void modificarCSV(String nombreArchivo) throws IOException{

    }
}
