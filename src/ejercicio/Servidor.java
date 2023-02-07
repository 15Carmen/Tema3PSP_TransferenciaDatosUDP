package ejercicio;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Servidor {
    public static void main(String[] args) {

        //Declaramos las variables
        DatagramSocket socket;
        DatagramPacket packet;
        BufferedWriter bw;
        String mensaje = "";
        InetAddress direccion;
        byte[] buffer;

        try {
            //Obtener direccion IP local
            direccion = InetAddress.getLocalHost();

            //Creacion del socket
            System.out.println("Creacion del socket");
            socket = new DatagramSocket(41700);

            bw = new BufferedWriter(new FileWriter("src/ejercicio/fichero.txt"));

            while (true){

                while (!mensaje.equals("FIN")){     //Mientras que mensaje sea distinto de la palabra "FIN"
                    buffer = new byte[64];
                    System.out.println();
                    System.out.println("Creamos el datagrama");
                    packet = new DatagramPacket(buffer, buffer.length);
                    System.out.println("A la espera de recibir datagrama");
                    socket.receive(packet);

                    System.out.println();
                    System.out.println("Leemos el mensaje");
                    mensaje = new String(packet.getData()).trim();
                    System.out.println("Mensaje que ha enviado el cliente: " + mensaje.trim());

                    bw.write(mensaje);  //Con este escribe en el fichero tal cual el mensaje de la consola
                    //bw.write(mensaje.split(" ")[1]); Con esta línea de código se quita la parte escrita cuando se escribe en
                    //el fichero, pero salta una excepción y deja de escribir en el fichero alrededor del número 8376
                    bw.newLine();
                }
                //Cerramos el bw
                bw.close();
            }
            
        } catch (
                SocketException e) {
            System.err.println("Error en la creacion del socket");
        } catch (
                IOException e) {
            System.err.println("Error en la recepcion del paquete");
        }
    }

}