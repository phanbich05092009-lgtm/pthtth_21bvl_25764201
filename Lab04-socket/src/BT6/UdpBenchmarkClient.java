package BT6;

import java.net.*;
import java.nio.charset.StandardCharsets;

public class UdpBenchmarkClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 6006;
        int totalMessages = 1000;

        System.out.println("=== BẮT ĐẦU ĐO LƯỜNG UDP (Gửi " + totalMessages + " thông điệp) ===");

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(1000); // Cấu hình timeout 1 giây để tránh bị treo khi mất gói tin
            InetAddress address = InetAddress.getByName(host);

            long startTime = System.currentTimeMillis();
            int successCount = 0;

            for (int i = 0; i < totalMessages; i++) {
                String msg = "Message_" + i;
                byte[] sendData = msg.getBytes(StandardCharsets.UTF_8);
                
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, port);
                socket.send(sendPacket);

                try {
                    byte[] receiveBuffer = new byte[1024];
                    DatagramPacket receivePacket = new DatagramPacket(receiveBuffer, receiveBuffer.length);
                    socket.receive(receivePacket);
                    successCount++;
                } catch (SocketTimeoutException e) {
                    // Xử lý khi gói tin bị thất lạc hoặc quá thời gian timeout
                }
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("--- KẾT QUẢ UDP ---");
            System.out.println("Tổng thời gian: " + duration + " ms");
            System.out.println("Số phản hồi nhận được: " + successCount + "/" + totalMessages);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}