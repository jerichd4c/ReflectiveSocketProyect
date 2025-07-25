package src;

import java.io.*;
import java.util.*;
import menu.MenuSystem; //socket server and client
import sockets.*; // test classes and methods
import testclasses.*; // menu system for testing

public class BusinessApp {
    public static void main(String[] args) throws IOException {

    // start the server on different ports/sockets

    new SocketServer (5000, new Calculator()).start();
    new SocketServer (5001, new TextModifier()).start();
    new SocketServer (5002, new Converter()).start();

    // library for the client

    //ClientLibrary lib = new ClientLibrary("localhost", 5000); it isnt necesary because the socket its already declared in the MenuSystem class

    // create a new aux class to call the sub methods of the business objects

    MenuSystem menu = new MenuSystem();

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
                    menu.testCalculator();
                    break;
                case 2:
                    menu.testTextModifier();
                    break;
                case 3:
                    menu.testConverter();
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
        sc.close();
    }
}
