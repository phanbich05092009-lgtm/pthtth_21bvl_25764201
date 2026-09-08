import static java.lang.Thread.*;
import java.io.InputStream;
import java.io.IOException;

public class InStream2b {
    public static void main (String[] args) throws InteruptedException, IOException {
        InputStream is = System.in;
        try {
            while (true) {
                //Kiem tra xem co bao nhieu byte du lieu co san maf khong bi chan
                if (is.available()>0) {
                    byte[] buffer = new byte[is.available()];
                    int bytesRead = is.read(buffer);
                    if (bytesRead == -1) {
                        break; // ket thuc inputstream
                    }
                    String str = new String(buffer, offset:0, bytesRead);
                    System.out.print(str); //in ra du lieu doc duoc
                    } else {
                        //neu khong co du lieu san co, in mot ky tu '.' va doi mot chut
                        System.out.print(c:'.');
                        sleep(millis:100); //ngung mot chut de gian tai CPU
                    }
            }
        } catch (IOException e) {
            
        }
    }
}