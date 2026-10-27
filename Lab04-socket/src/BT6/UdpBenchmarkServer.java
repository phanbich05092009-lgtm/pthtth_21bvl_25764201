package BT6;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpBenchmarkServer {
    public static void main(String[] args) {
        int port = 6006;
        System.out.println(">>> UDP Benchmark Server đang lắng nghe trên cổng " + port + "...");
        
        try (DatagramSocket serverSocket = new DatagramSocket(port)) {
            byte[] receiveBuffer = new byte[1024];
            
            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                serverSocket.receive(receivePacket);

                String message = new String(receivePacket.getData(), 0, receivePacket.getLength(), StandardCharsets.UTF_8);
                
                // Phản hồi gói tin ACK lại cho Client
                String responseStr = "ACK: " + message;
                byte[] sendData = responseStr.getBytes(StandardCharsets.UTF_8);
                
                DatagramPacket sendPacket = new DatagramPacket(
                        sendData, sendData.length, 
                        receivePacket.getAddress(), receivePacket.getPort()
                );
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}