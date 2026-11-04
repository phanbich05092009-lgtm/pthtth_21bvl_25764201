import java.rmi.Remote;
import java.rmi.RemoteException;

public interface    FlightService extends Remote {
    int availaSeats (String flightNumber) throws RemoteException;
    boolean boolTicket (String flightNumber, int seats) throws RemoteException;
    boolean cancelBooking(String flingtNumber, int seats) throws RemoteException;
}