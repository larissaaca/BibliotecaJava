package BookProject;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    private String filename = "livros.json";
    private ServerSocket server;
    private Socket socket;

    private InputStream input;
    private OutputStream out;

    private File database = new File(filename);

    public void start(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Iniciado");
            System.out.println("Sem clinte");

            socket = server.accept();
            input = socket.getInputStream();
            out = socket.getOutputStream();

            System.out.println("1 cliente");

            sendData();
            waitForResponse();

        } catch(Exception e) {
            System.out.println("Serve não iniciado ");
        }
    }

    private void waitForResponse() {
        try {
            byte[] buffer = new byte[1024];
            int responseLen = input.read(buffer);
            String response = new String(buffer, 0, responseLen);

            Character command = response.charAt(0);
            String data = response.substring(1);

            switch(command) {
                case 'N':
                    registerBook(data);
                    break;
                case 'A':
                    rentBook(data);
                    break;
                case 'D':
                    returnBook(data);
                    break;
                default:
                    break;
            }

        } catch(Exception e) {
            System.out.println("serve não pode ler o input");
            e.printStackTrace();
        }
    }

    private void registerBook(String data) {
        Book newBook = BookParser.toBook(data);

        String databaseString = scanFile();
        Book[] list = BookParser.toBookList(databaseString);

        Book[] newList = new Book[list.length + 1];
        for(int i = 0; i<list.length; i++) {
            newList[i] = list[i];
        }
        newList[list.length] = newBook;

        saveFile(newList);
        sendData();
        System.out.println("Novo livro salvo, recarrege" + filename);
    }

    private void rentBook(String data) {
        int index = Integer.parseInt(data);
        String databaseString = scanFile();

        Book[] list = BookParser.toBookList(databaseString);
        list[index].takeCopies(1);

        saveFile(list);
        System.out.println("Livro alugado, recarrege" + filename);
    }

    private void returnBook(String data) {
        int index = Integer.parseInt(data);
        String databaseString = scanFile();

        Book[] list = BookParser.toBookList(databaseString);
        list[index].returnCopies(1);

        saveFile(list);
        System.out.println("Livro devolvido, recarrege" + filename);
    }

    private void sendData() {
        String data = scanFile();
        byte[] bytes = data.getBytes();
        try {
            out.write(bytes);
            out.flush();
        } catch (Exception e) {
            System.out.println("Não pode usar os dados");
        }
    }

    private String scanFile() {
        String databaseString = null;

        try {
            Scanner scanner = new Scanner(database);

            databaseString = "";
            while(scanner.hasNextLine()) {
                databaseString += scanner.nextLine();
            }
            
            scanner.close();
        } catch(Exception e) {
            System.out.println("Falha em ler o json");
            e.printStackTrace();
        }

        return databaseString;
    }

    private void saveFile(Book[] list) {
        String newData = BookParser.toString(list);
        database.delete();
        database = new File(filename);
        try {
            database.createNewFile();
            FileWriter writer = new FileWriter(filename);
            writer.write(newData);
            writer.close();
        } catch (Exception e) {
            System.out.println("Falha em salva");
        }
    }

    public void saveFromString(String data) {
        try {
            database.createNewFile();
            FileWriter writer = new FileWriter(filename);
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            System.out.println("Falha em salva ");
        }
    }
}
