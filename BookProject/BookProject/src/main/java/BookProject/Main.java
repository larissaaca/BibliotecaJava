package BookProject;

class Main {
    public static void main(String args[]) {
        int port = 8080;
        String ip = "127.0.0.1";
        ServerThread thread = new ServerThread();
        thread.server = new Server();
        thread.port = port;
        thread.start();

        ClientService service = new ClientService(ip, port);
        Client client = new Client(service);
        client.start();
    }

    static void parsingTests() {
        Book book = new Book("toia", "nana", "terror", 2);
        String string = BookParser.toString(book);

        Client client = new Client(null);
        Book parsedBook = BookParser.toBook(string);

        client.print(parsedBook);

        Book[] books = new Book[] {book, book, book};
        string = BookParser.toString(books);

        Server server = new Server();
        server.saveFromString(string);

        Book[] list = BookParser.toBookList(string);
        
        for(Book item: list) {
            client.print(item);
        }
    }
}