
import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {

    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private String name;
    private String room;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public String getClientName() {
        return name;
    }

    public String getClientRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    @Override
    public void run() {

        try {
            in = new BufferedReader(
                    new InputStreamReader(
                            socket.getInputStream(), "UTF-8"));

            out = new PrintWriter(
                    new OutputStreamWriter(
                            socket.getOutputStream(), "UTF-8"),
                    true);

            // Doc ten client
            name = in.readLine();

            if (name == null || name.trim().isEmpty()) {
                return;
            }

            name = name.trim();
            sendMessage("Chao mung " + name
                    + "! Hay chon phong chat.");

            String message;

            while ((message = in.readLine()) != null) {

                if (message.startsWith("JOIN|")) {
                    String newRoom =
                            message.substring(5).trim();

                    if (!ChatServer.join(this, newRoom)) {
                        sendMessage("Phong khong hop le!");
                        sendMessage("ROOMS|Study,Game,TraiNghiem");
                    }

                } else if (message.equals("/quit")) {

                    ChatServer.leave(this);

                    sendMessage(
                            "ROOMS|Study,Game,TraiNghiem");

                } else if (message.equals("/exit")) {

                    break;

                } else if (message.startsWith("MSG|")) {

                    String content = message.substring(4);

                    if (room == null) {
                        sendMessage(
                                "Ban chua tham gia phong chat.");
                    } else {
                        ChatServer.broadcast(
                                room, name + ": " + content);
                    }

                } else {
                    sendMessage("Lenh khong hop le!");
                }
            }

        } catch (IOException e) {
            System.out.println(
                    "Client da ngat ket noi.");
        } finally {
            ChatServer.leave(this);

            try {
                socket.close();
            } catch (IOException e) {
                System.out.println(
                        "Khong the dong socket.");
            }

            System.out.println(name + " da ngat ket noi.");
        }
    }
}
