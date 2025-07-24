import java.io.*;
import java.lang.reflect.*;
import java.net.*; //library for reflection
import java.util.Arrays;

class SocketServer {
    private int port;
    private ServerSocket serverSocket;
    private Object businessObject; // this could be any object that handles business logic (calcs, db queries, etc.)

    // constructor for the SocketServer
    public SocketServer(int port, Object businessObject) throws IOException {
        this.port = port;
        this.businessObject = businessObject;
        // creates a new ServerSocket that listens on the specified port
        this.serverSocket = new ServerSocket(port);
        System.out.print ("\nServer iniciado en puerto: " + port + "\n");
    }

    // thread to start the server and listen for incoming connections 
    public void start() {
        new Thread(() -> {
            // while (true) means it will run indefinitely until stopped (like a server) 
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    // same logic as in the SocketClient, but now it is reading from the client and sending a response
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    // sends the data to the client
                    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {

                        String requestData = in.readLine();

                        // debug 

                        System.out.println("[SERVIDOR] recibiendo del cliente los siguientes datos: " + requestData);



                        //it is important to use a method that processes the request because it will modify the reequestData such as parsing it or validating it
                        String response = processRequest(requestData);

                        // debug

                        System.out.println("[SERVER] enviando al cliente la siguiente respuesta : " + response);

                          out.println(response);  // debug
                          out.flush();            // debug
                
                    // handle the client connection in a separate thread
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //method to parse the request and return a response
    private String processRequest(String requestData) {
        // logic to process the request and parse it
        try {
            // array of strings (| is used as a delimiter)
            String[] parts = requestData.split("\\|");
            String className = parts[0];
            String methodName = parts[1];
            String[] paramTypes = parts[2].split(",");
            String[] paramValues = parts[3].split(",");
            
            // uses 'clazz' because class is a reserved word
            // bussinessObject is an instance of the class that handles the business logic
            Class<?> clazz= businessObject.getClass();
            
            // get array using reflection
            Class<?>[] parameterTypes = new Class[paramTypes.length];
            Object[] parameters = new Object[paramValues.length];

            for (int i =0; i<paramTypes.length; i++) {
                parameterTypes[i] = getClassForName(paramTypes[i]);
                parameters[i] = convertValue(paramValues[i], parameterTypes[i]);
            }

        // debug
        System.out.println("Clase: " + className);
        System.out.println("Método: " + methodName);
        System.out.println("Tipos: " + Arrays.toString(paramTypes));
        System.out.println("Valores: " + Arrays.toString(paramValues));
        

            Method method = clazz.getMethod(methodName, parameterTypes);
            Object result = method.invoke(businessObject, parameters);
        
        // debug 
         System.out.println("Resultado: " + result); 



            // return result (CANT BE NULL)

            return result != null ? result.toString() : "null";
        }
        catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    } 

    // method that converts the primitive types to their corresponding wrapper classes
    private Class<?> getClassForName(String type) throws ClassNotFoundException {
        
        switch (type) {
            case "int":
                return int.class;
            case "double":
                return double.class;
            case "boolean":
                return boolean.class;
            case "String":
                return String.class;
            default:
                return Class.forName(type); // for custom classes or interfaces
        }
    }
        
    // now with that the class is selected, parses the value
    private Object convertValue(String value, Class<?> type) {
        if (type == int.class) return Integer.parseInt(value);
        if (type == double.class) return Double.parseDouble(value);
        if (type == boolean.class) return Boolean.parseBoolean(value);
        if (type == String.class) return value;
        return null; // if the type is not recognized, return null
        }
    }