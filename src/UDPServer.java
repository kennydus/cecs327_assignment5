import java.net.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class UDPServer{
    public static void main(String args[]){
        DatagramSocket aSocket = null;

        Scanner in = new Scanner(System.in);
        System.out.println("Please enter a Port Number for the server: ");
        int portNum = in.nextInt();

        try{
            aSocket = new DatagramSocket(portNum);
            while(true) {
                byte[] buffer = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                String receivedMessage = new String(request.getData());    // takes client received message and converts
                                                                           // to string

                System.out.println("Client Message: " + receivedMessage);

                buffer = receivedMessage.toUpperCase().getBytes();      // Puts the client message in Upper Case form
                                                                        // and stores it into the buffer

                DatagramPacket reply = new DatagramPacket(buffer, buffer.length,    // Creating reply packet
                        request.getAddress(), request.getPort());
                aSocket.send(reply);                                    // Sending reply packet to the client
            }
        }
        catch (SocketException e) {
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if(aSocket != null)
                aSocket.close();
        }
    }
}