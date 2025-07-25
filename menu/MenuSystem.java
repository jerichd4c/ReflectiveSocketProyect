package menu;

// auxiliary class to test the methods in main BusinessApp so it can be less cluttered

import java.util.Scanner;
import lib.ClientLibrary; // library for the client

public class MenuSystem {
    private ClientLibrary lib;
    private Scanner sc;

    public MenuSystem() {
        this.sc =  new Scanner(System.in);
    }

    // test calculator

    public void testCalculator () {

           lib = new ClientLibrary("localhost", 5000);

        while (true) {

            System.out.println("\nSelecciona una operación\n");
            System.out.println("1. Sumar");
            System.out.println("2. Restar");   
            System.out.println("3. Dividir");
            System.out.println("4. Salir");
            System.out.print("\nOpción: ");
            
            int choice = sc.nextInt();
            if (choice == 4) break;

            // adding this so it doesnt run all the methods in a row

            String methodName = "";
            Class<?>[] paramTypes = null;
            Object[] params = new Object[2];

            switch (choice) {

            case 1:  

                methodName = "add";
                paramTypes = new Class[] {int.class, int.class};
                System.out.println("Ingrese primer numero: ");
                params[0] = sc.nextInt();
                System.out.println("Ingrese segundo numero: ");
                params[1] = sc.nextInt();
                break;

            case 2:
                
                methodName = "substract";
                paramTypes = new Class[]{int.class, int.class};
                System.out.print("Ingrese primer número: ");
                params[0] = sc.nextInt();
                System.out.print("Ingrese segundo número: ");
                params[1] = sc.nextInt();
                break;

            case 3:
                
                methodName = "divide";
                paramTypes = new Class[]{double.class, double.class};
                System.out.print("Ingrese primer número: ");
                params[0] = sc.nextDouble();
                System.out.print("Ingrese segundo número: ");
                params[1] = sc.nextDouble();
                break;

            default:
                System.out.println("Opción no válida.");
                continue; // skip to the next iteration of the loop

        }

            // now with the method selected, calls the method in the server

            String result = lib.callMethod("Calculadora", methodName, params, paramTypes);
            System.out.println("Resultado: " + result);
        }
    }

    // test text modifier

    public void testTextModifier() {
        
        lib = new ClientLibrary("localhost", 5001);

        while (true) { 

            System.out.println("\nSelecciona una opción\n");
            System.out.println("1. Invertir");
            System.out.println("2. Poner en mayúsculas");
            System.out.println("3. Poner en minúsculas");
            System.out.println("4. Salir");
            System.out.print("\nOpción: ");
            
            int choice = sc.nextInt();
            if (choice == 4) break;

             sc.nextLine(); // clean buffer so it doesnt read the leftover thext of another class/method

              System.out.print("Ingrese el texto a modificar: ");
              String text = sc.nextLine();

            String methodName = "";
            Class<?>[] paramTypes = {String.class}; // type of the parameter: string
            Object[] params = {text}; // value of the parameter

             switch (choice) {
                case 1: 
                    methodName = "invertText";
                    break;
                case 2:
                    methodName = "editUpper";
                    break;
                case 3:
                    methodName = "editLower";
                    break;
                default:
                    System.out.println("Opción inválida");
                    continue;
            }

            String result = lib.callMethod("TextUtils", methodName, params, paramTypes);
            System.out.println("Resultado: " + result);
            
        }

    }

    // test conversor

    public void testConverter() {
        
        lib = new ClientLibrary("localhost", 5002);

         while (true) { 

            System.out.println("\nSelecciona una opción\n");
            System.out.println("1. celsius a fahrenheit");
            System.out.println("2. fahrenheit a celsius");
            System.out.println("3. kilometros a millas");
            System.out.println("4. Salir");
            System.out.print("\nOpción: ");
            
            int choice = sc.nextInt();
            if (choice == 4) break;
            

            String methodName = "";
            Class<?>[] paramTypes = {double.class}; // type of the parameter: string
            Object[] params = new Object[1]; // value of the parameter

             switch (choice) {
                case 1: 
                    methodName = "celsiusToFahrenheit";
                    System.out.print("Ingrese grados celcius: ");
                    params[0] = sc.nextDouble();
                    break;
                case 2:
                    methodName = "fahrenheitToCelsius";
                    System.out.print("Ingrese grados fahrenheit: ");
                    params[0] = sc.nextDouble();
                    break;
                case 3:
                    methodName = "celsiusToKelvin";
                    System.out.print("Ingrese grados celcius: ");
                    params[0] = sc.nextDouble();
                    break;
                default:
                    System.out.println("Opción inválida");
                    continue;
            }

            String result = lib.callMethod("Converter", methodName, params, paramTypes);
            System.out.println("Resultado: " + result);
            
        }

    }
}
