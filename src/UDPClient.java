import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
    public static void main(String args[]){
        DatagramSocket aSocket = null;

        Scanner in = new Scanner(System.in);        // Scanner object for user input
        System.out.println("Please enter the IP Address of the server: ");
        String ipAddr = in.nextLine();              // Gets IP address from input
        System.out.println("Please enter the Port Number of the server: ");
        int portNum = in.nextInt();                 // Gets port number from input

        // args give message contents and server hostname
        try {
            in.nextLine();
            aSocket = new DatagramSocket();

            while (true) {
                System.out.println("Enter a message to send to the server: ");
                String message = in.nextLine();

                byte[] m = message.getBytes();
                InetAddress aHost = InetAddress.getByName(ipAddr);      // Creates IP address from inputted IP string

                DatagramPacket request = new DatagramPacket(m, m.length, aHost, portNum);   // Creates packet with
                                                                                        // contents of the new message
                aSocket.send(request);
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()) + "\n");
            }
        }
        catch (SocketException e){
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e){
            System.out.println("IO: " + e.getMessage());
        }
        finally {
            if(aSocket != null) aSocket.close();
        }
    }
}

