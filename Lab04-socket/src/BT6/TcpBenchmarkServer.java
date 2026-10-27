package BT6;

import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class TcpBenchmarkServer {
    public static void main(String[] args) {
        int port = 5006;
        System.out.println(">>> TCP Benchmark Server đang lắng nghe trên cổng " + port + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                // Dùng đa luồng để xử lý kết nối từ client
                new Thread(() -> handleClient(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleClient(Socket socket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
             PrintWriter out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8), true)) {
            
            String line;
            while ((line = in.readLine()) != null) {
                out.println("ACK: " + line);
            }
        } catch (IOException e) {
            // Client ngắt kết nối
        }
    }
}