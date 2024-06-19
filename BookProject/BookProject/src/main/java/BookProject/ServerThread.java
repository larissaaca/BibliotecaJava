package BookProject;

public class ServerThread extends Thread {
    public Server server;
    public int port;
    public void run() {
        server.start(port);
    }
}
