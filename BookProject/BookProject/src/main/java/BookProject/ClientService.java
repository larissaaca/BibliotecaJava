package BookProject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientService {
    private String ip;
    private int port;

    private Socket socket;
    private InputStream input;
    private OutputStream out;

    public ClientService(String ip, int port) {
        this.ip = ip;
        this.port = port;
    }

    public void start() {
        System.out.println("Client Loading...");
        try {
            socket = new Socket(ip, port);
            input = socket.getInputStream();
            out = socket.getOutputStream();
        } catch (Exception e) {
            System.out.println("Client connection failed");
        }
    }

    public Book[] getAllBooks() {
        Book[] allBooks = null;

        try {
            byte[] buffer = new byte[10000];
            int responseLen = input.read(buffer);
            String response = new String(buffer, 0, responseLen);
            allBooks = BookParser.toBookList(response);
        } catch(Exception e) {
            System.out.println("Client failed to receive list");
            e.printStackTrace();
        }

        return allBooks;
    }

    public void registerNewBook(Book book) {
        String string = "N" + BookParser.toString(book);
        sendData(string.getBytes());
    }

    public void rent(int index) {
        String string = "A" + Integer.toString(index);
        sendData(string.getBytes());
    }

    public void returnBook(int index) {
        String string = "D" + Integer.toString(index);
        sendData(string.getBytes());
    }

    private void sendData(byte[] bytes) {
        try {
            out.write(bytes);
            out.flush();
        } catch (Exception e) {
            System.out.println("Client failed to send data");
        }
    }
}
