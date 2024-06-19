package BookProject;

import java.util.Scanner;

public class Client {
    private ClientService service;
    private Book[] booksCache;
    private Scanner scanner = new Scanner(System.in); // Evitar fechar o Scanner

    public Client(ClientService service) {
        this.service = service;
    }

    public void start() {
        service.start();
        boolean running = true;
        while (running) {
            Book[] books = service.getAllBooks();
            booksCache = books;
            showBookList(books);
            System.out.println("Deseja realizar outra operação? (s/n): ");
            String continueInput = scanner.nextLine();
            if (!continueInput.equalsIgnoreCase("s")) {
                running = false;
            }
        }
        scanner.close();
    }

    private void showBookList(Book[] books) {
        printSeparator();
        System.out.println("Qual o ID do livro para alugar ou devolver");
        System.out.println("Digite '00' para registrar um livro");

        for (int i = 0; i < books.length; i++) {
            System.out.print("ID: ");
            System.out.print(i + " - ");
            print(books[i]);
        }

        String input = scanner.nextLine();

        switch (input) {
            case "00":
                registerNewBook();
                break;

            default:
                try {
                    int index = Integer.parseInt(input);
                    if (index >= 0 && index < books.length) {
                        showBookData(books[index], index);
                    } else {
                        System.out.println("ID inválido.");
                        showBookList(booksCache);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida.");
                    showBookList(booksCache);
                }
                break;
        }
    }

    private void showBookData(Book book, int index) {
        printSeparator();
        print("Escolha uma dessas opções:");
        if (book.getNumCopies() > 0) {
            print("1-Alugar um Livro");
        }
        print("2-Devolver um livro");
        print("Voltar, digite qualquer coisa");

        String input = scanner.nextLine();

        switch (input) {
            case "1":
                if (book.getNumCopies() > 0) {
                    rentBook(index);
                } else {
                    System.out.println("Livro não disponível para aluguel.");
                    showBookData(book, index);
                }
                break;
            case "2":
                returnBook(index);
                break;
            default:
                showBookList(booksCache);
                break;
        }
    }

    private void registerNewBook() {
        print("Qual o nome do autor: ");
        String author = scanner.nextLine();
        print("Qual o nome do título da obra: ");
        String title = scanner.nextLine();
        print("Qual o nome do gênero: ");
        String theme = scanner.nextLine();
        print("Qual o número de exemplares disponíveis: ");
        int numCopies = readInt();

        Book newBook = new Book(author, title, theme, numCopies);

        service.registerNewBook(newBook);
    }

    private void rentBook(int index) {
        service.rent(index);
    }

    private void returnBook(int index) {
        service.returnBook(index);
    }

    private int readInt() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Por favor, insira um número válido.");
            }
        }
    }

    public void print(Book book) {
        System.out.print(book.getAuthor() + ", " + book.getTitle() + " || " + book.getTheme() + " || Exemplares: " + book.getNumCopies());

        if (book.getNumCopies() <= 0) {
            System.out.print(" Não disponível");
        }

        System.out.println();
    }

    private void print(String string) {
        System.out.println(string);
    }

    private void printSeparator() {
        System.out.println("");
    }
}
