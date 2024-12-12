package clienteadivinarnumeros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

public class ClienteAdivinarNumeros {

    public static void main(String[] args) {
        // IP y puerto del servidor
        String host = "localhost";
        int port = 1234;
        int LimiteInferior = 1;
        int LimiteSuperior = 100;
        Random rand = new Random();
        int numeroAleatorio; 
        
        try {
            //Conectarse al servidor
            Socket socket = new Socket(host, port);
            System.out.println("Conectado al servidor " + host + " en el puerto " + port + ".");
            
            // Enviarle un mensaje
            PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            
            boolean continuar = true;
            while (continuar) {
                
                numeroAleatorio = rand.nextInt(LimiteInferior, LimiteSuperior); 
                System.out.println("propongo el numero: " + numeroAleatorio);
                output.println(numeroAleatorio);

                String answer = input.readLine();
                System.out.println("La respuesta del servidor es: " + answer); 
                if ("menor".equals(answer)) {
                    LimiteSuperior = numeroAleatorio;
                }
                if ("mayor".equals(answer)) {
                    LimiteInferior = numeroAleatorio;
                }
                if ("correcto".equals(answer)) {
                    continuar = false;
                    System.out.println("Salí");
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
