import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class FlightServiceImpl extends UnicastRemoteObject implements FlightService {
    // Lưu trữ danh sách các chuyến bay: Key = Mã chuyến bay, Value = Số ghế trống
    private Map<String, Integer> flightDatabase;

    public FlightServiceImpl() throws RemoteException {
        super();
        flightDatabase = new HashMap<>();
        // Khởi tạo một vài dữ liệu mẫu chuyến bay ban đầu
        flightDatabase.put("VN123", 50);
        flightDatabase.put("VJ456", 30);
        flightDatabase.put("QH789", 20);
    }

    @Override
    public int availableSeats(String flightNumber) throws RemoteException {
        // Trả về số ghế trống, nếu không tìm thấy chuyến bay thì trả về -1
        return flightDatabase.getOrDefault(flightNumber, -1);
    }

    @Override
    public synchronized boolean bookTicket(String flightNumber, int seats) throws RemoteException {
        if (flightDatabase.containsKey(flightNumber)) {
            int currentSeats = flightDatabase.get(flightNumber);
            // Kiểm tra xem còn đủ ghế trống hay không
            if (currentSeats >= seats && seats > 0) {
                flightDatabase.put(flightNumber, currentSeats - seats);
                return true; // Đặt vé thành công
            }
        }
        return false; // Đặt vé thất bại
    }

    @Override
    public synchronized boolean cancelBooking(String flightNumber, int seats) throws RemoteException {
        if (flightDatabase.containsKey(flightNumber) && seats > 0) {
            int currentSeats = flightDatabase.get(flightNumber);
            // Cộng lại số ghế đã hủy vào kho ghế trống
            flightDatabase.put(flightNumber, currentSeats + seats);
            return true; // Hủy vé thành công
        }
        return false;
    }
}