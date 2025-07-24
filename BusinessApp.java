import java.io.*;
import java.util.Scanner;

public class BusinessApp {
    public static void main(String[] args) throws IOException {

    // start the server on different ports/sockets

    new SocketServer (5000, new Calculadora()).start();
    new SocketServer (5001, new TextModifier()).start();
    new SocketServer (5002, new Converter()).start();

    // library for the client

    ClientLibrary lib = new ClientLibrary("localhost", 5000);

    // switch case menu
    Scanner sc = new Scanner(System.in);
    while (true) { 
        System.out.println("\nSelecciona una clase\n");
        System.out.println("1. Calculadora");
        System.out.println("2. Modificador de texto");
        System.out.println("3. Conversor");
        System.out.println("4. Salir");
        System.out.print("\nOpción: ");

         int choice = sc.nextInt();
            if (choice == 4) break;
            
            switch (choice) {
                case 1:
                    testCalculator(lib, sc);
                    break;
                case 2:
                    //testTextUtils(lib, sc);
                    break;
                case 3:
                    //testConversor(lib, sc);
                    break;
            }
        }
        sc.close();
    }

    private static void testCalculator (ClientLibrary lib, Scanner sc) {

        System.out.println("Ingrese dos números para la suma:");
       
        System.out.print("Ingrese el primer número: ");
        int add1= sc.nextInt();
        System.out.print("Ingrese el segundo número: ");
        int add2= sc.nextInt();

        // this is where the method is really tested

        System.out.println("[CLIENTE] Suma: " + lib.callMethod("Calculadora", "add", new Object[] {add1, add2}, new Class[] {int.class, int.class}));

        System.out.println("Ingrese dos números para la resta:");
    
        System.out.print("Ingrese el primer número: ");
        int sub1= sc.nextInt();
        System.out.print("Ingrese el segundo número: ");
        int sub2= sc.nextInt();

        // this is where the method is really tested

        System.out.println("[CLIENTE] Resta: " + lib.callMethod("Calculadora", "substract", new Object[] {sub1, sub2}, new Class[] {int.class, int.class}));

        System.out.println("Ingrese dos números para la division:");
 
        System.out.print("Ingrese el primer número: ");
        double div1= sc.nextDouble();
        System.out.print("Ingrese el segundo número: ");
        double div2= sc.nextDouble();

        // this is where the method is really tested

        System.out.println("[CLIENTE] Division: " + lib.callMethod("Calculadora", "divide", new Object[] {div1, div2}, new Class[] {double.class, double.class}));




    }
}
