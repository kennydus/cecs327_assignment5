import java.net.*;
import java.io.*;
import java.util.Scanner;

public class UDPClient{
    public static void main(String args[]){
        DatagramSocket aSocket = null;

        Scanner in = new Scanner(System.in);        // Scanner object for user input
        System.out.println("Please enter the IP Adresss of the server: ");
        String ipAddr = in.nextLine();
        System.out.println("Please enter the Port Number of the server: ");
        int portNum = in.nextInt();

        // args give message contents and server hostname
        try {
            aSocket = new DatagramSocket();

            while (true) {
                System.out.println("Enter a message to send to the server: ");
                String message = in.nextLine();


                byte[] m = message.getBytes();
                InetAddress aHost = InetAddress.getByName(ipAddr);
                int serverPort = portNum;
                DatagramPacket request = new DatagramPacket(m, m.length, aHost, serverPort);
                aSocket.send(request);
                byte[] buffer = new byte[1000];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                System.out.println("Reply: " + new String(reply.getData()));
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

