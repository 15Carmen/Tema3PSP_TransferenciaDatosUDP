package ejercicio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Cliente {
    private static DatagramSocket socket;
    private static DatagramPacket packet;

    public static void main(String[] args) {

        try {
            //Obtener direccion IP local
            InetAddress direccion = InetAddress.getLocalHost();

            //Creacion del socket
            System.out.println("Creacion del socket");
            socket = new DatagramSocket(41500);

            //Creamos el mensaje
            creacionMensaje(direccion);

        } catch (SocketException e) {
            System.err.println("Error en la creacion del socket");
        } catch (IOException e) {
            System.err.println("Error en la recepcion del paquete");
        }
    }

    private static void creacionMensaje(InetAddress direccion) {
        //Declaramos las variables
        String mensaje;
        byte[] buffer;

        try {
            for (int i = 0; i <= 9999; i++) {
                //Creacion del mensaje
                mensaje = "Mensaje: " + i;
                buffer = mensaje.getBytes();

                System.out.println("Creamos el datagrama");
                packet = new DatagramPacket(buffer, buffer.length, direccion, 41700);
                socket.send(packet);
            }

            mensaje = "FIN";
            buffer = mensaje.getBytes();

            System.out.println("Creamos el datagrama");
            packet = new DatagramPacket(buffer, buffer.length, direccion, 41700);
            socket.send(packet);

        } catch (IOException e) {
            System.err.println("Error al enviar el paquete");
        }

    }


}
