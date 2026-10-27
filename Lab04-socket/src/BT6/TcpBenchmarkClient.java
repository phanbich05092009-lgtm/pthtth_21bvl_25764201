package BT6;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TcpBenchmarkClient {
    public static void main(String[] args) {
        String host = "localhost";
        int port = 5006;
        int totalMessages = 1000;

        System.out.println("=== BẮT ĐẦU ĐO LƯỜNG TCP (Gửi " + totalMessages + " thông điệp) ===");
        
        try (Socket socket = new Socket(host, port);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {

            long startTime = System.currentTimeMillis();
            int successCount = 0;

            for (int i = 0; i < totalMessages; i++) {
                String msg = "Message_" + i;
                out.println(msg);
                String response = in.readLine();
                if (response != null) {
                    successCount++;
                }
            }

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            System.out.println("--- KẾT QUẢ TCP ---");
            System.out.println("Tổng thời gian: " + duration + " ms");
            System.out.println("Số phản hồi nhận được: " + successCount + "/" + totalMessages);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}