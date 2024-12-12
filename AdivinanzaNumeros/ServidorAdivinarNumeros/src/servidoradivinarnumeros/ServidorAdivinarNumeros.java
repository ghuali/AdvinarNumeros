package servidoradivinarnumeros;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class ServidorAdivinarNumeros {

    
    public static void main(String[] args) {
        // Puerto del servidor
        int port = 1234;
        
        Random rand = new Random();
        int numeroAleatorio = rand.nextInt(100);
        
        System.out.println("El número aleatorio ha sido generado: " + numeroAleatorio);
        
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Servidor iniciado en el puerto " + port + ".");
            
            boolean continuar = true;

            
            while (true) {
                // Aceptar una conexion
                Socket client = server.accept();
                System.out.println("Cliente conectado: " + client.getInetAddress());
                
                // Leer y responder al cliente
                BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                
                
                while (continuar) {
                    String message = input.readLine();
                    System.out.println("Se ha recibido este mensaje: " + message);
                    if (Integer.parseInt(message) == numeroAleatorio) {
                        String answer = "correcto";
                        output.println(answer);
                        continuar = false;
                    }
                    if (Integer.parseInt(message) > numeroAleatorio) {
                        String answer = "menor";
                        output.println(answer);
                    }
                    if (Integer.parseInt(message) < numeroAleatorio) {
                        String answer = "mayor";
                        output.println(answer);
                    }
                }
                client.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
