
import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    public static void main(String[] args) {

        try (
            Socket socket = new Socket("localhost", 12345);

            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    socket.getInputStream(), "UTF-8"));

            PrintWriter out = new PrintWriter(
                new OutputStreamWriter(
                    socket.getOutputStream(), "UTF-8"),
                true);

            Scanner scanner = new Scanner(System.in)
        ) {
            System.out.print("Nhap ten cua ban: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Ten khong duoc de trong!");
                return;
            }

            // Gui ten cho server
            out.println(name);

            // Luong nhan tin nhan tu server
            Thread receiver = new Thread(() -> {
                try {
                    String message;

                    while ((message = in.readLine()) != null) {
                        if (message.startsWith("ROOMS|")) {
                            System.out.println(
                                "Danh sach phong: "
                                + message.substring(6));
                        } else {
                            System.out.println(message);
                        }
                    }
                } catch (IOException e) {
                    // Ket noi da dong
                }
            });

            receiver.setDaemon(true);
            receiver.start();

            boolean connected = true;

            while (connected) {

                System.out.println(
                    "\nCac phong: Study, Game, TraiNghiem");
                System.out.println(
                    "Nhap /exit de thoat chuong trinh.");

                System.out.print("Chon phong: ");
                String room = scanner.nextLine().trim();

                // Cho phep thoat ngay tai man hinh chon phong
                if (room.equals("/exit")) {
                    out.println("/exit");
                    connected = false;
                    break;
                }

                // Chua vao phong thi khong can /quit
                if (room.equals("/quit")) {
                    System.out.println(
                        "Ban chua o trong phong nao.");
                    continue;
                }

                // Kiem tra ten phong truoc khi gui len server
                if (!room.equals("Study")
                        && !room.equals("Game")
                        && !room.equals("TraiNghiem")) {

                    System.out.println(
                        "Ten phong khong hop le! Hay chon lai.");
                    continue;
                }

                // Chi gui lenh JOIN khi ten phong hop le
                out.println("JOIN|" + room);

                System.out.println(
                    "Da gui yeu cau vao phong " + room);
                System.out.println(
                    "Nhap tin nhan de chat.");
                System.out.println(
                    "Nhap /quit de doi phong.");
                System.out.println(
                    "Nhap /exit de ngat ket noi.");

                while (connected) {

                    String message = scanner.nextLine();

                    if (message.equals("/quit")) {
                        out.println("/quit");
                        break;
                    }

                    if (message.equals("/exit")) {
                        out.println("/exit");
                        connected = false;
                        break;
                    }

                    // Gui tin nhan vao phong
                    out.println("MSG|" + message);
                }
            }

            System.out.println("Da thoat chuong trinh.");

        } catch (IOException e) {
            System.out.println(
                "Khong ket noi duoc server: "
                + e.getMessage());
        }
    }
}
