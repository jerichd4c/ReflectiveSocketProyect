package lib;
import sockets.*;

public class ClientLibrary {
    private SocketClient client;

    // constructor for the ClientLibrary
    public ClientLibrary(String serverAdress, int serverPort) {
        this.client = new SocketClient(serverAdress, serverPort);
    }

    // sends the string request to the server and returns the response

    // append: this method constructs a request string that includes the class name, method name, paramTypes or a simple separator

    public String callMethod(String className, String methodName, Object[] params, Class<?>[] paramTypes) {
        
        try {
            StringBuilder requestData = new StringBuilder();
            requestData.append(className).append("|");
            requestData.append(methodName).append("|");

            // travels through the parameters and appends them to the requestData
            for (int i = 0; i< paramTypes.length; i++) {
                // iterate through the parameters and gets their name 
                requestData.append(paramTypes[i].getName());
                // add comma if not the last parameter (between types)
                if (i < paramTypes.length - 1) requestData.append(",");
            }
            requestData.append("|");

            for (int i = 0; i < params.length; i++) {
                requestData.append(params[i].toString());
                if (i < params.length - 1) requestData.append(",");
            }

            return client.sendRequest(requestData.toString());

            }  catch (Exception e) {
                return "Error request: " + e.getMessage(); 
            }   
        }
    }
