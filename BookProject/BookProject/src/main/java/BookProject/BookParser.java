package BookProject;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class BookParser {

    static String[] keys = new String[] {"titulo", "autor", "genero", "exemplares"};
    static String fileStart = "{\"livros\": [\n";

    public static String toString(Book book) {
        String string = "{";

        String title = "\"" + book.getTitle() + "\"";
        String author = "\"" + book.getAuthor() + "\"";
        String theme = "\"" + book.getTheme() + "\"";
        String numCopies = Integer.toString(book.getNumCopies());

        String[] values = new String[] {title, author, theme, numCopies};

        for(int i = 0; i < keys.length; i++) {
            string += "\"" + keys[i] + "\"" + ": " + values[i];
            if(i < keys.length - 1) {
                string += ", ";
            } else {
                string += "}";
            }
        }
        return string;
    }

    public static String toString(Book[] books) {
        String string = fileStart;

        for(int i = 0; i < books.length; i++) {
            String bookString = toString(books[i]);
            string += bookString;

            if(i < books.length - 1) {
                string += ",\n";
            } else {
                string += "\n]}";
            }

        }

        return string;
    }

    public static Book toBook(String string) {
        Gson gson = new Gson();
        Book book = gson.fromJson(string, Book.class);
        return book;
    }

    public static Book[] toBookList(String string) {
        Gson gson = new Gson();
        BooksJSONFile file = gson.fromJson(string, BooksJSONFile.class);
        Book[] books = file.list;
       
        return books;
    }

    class BooksJSONFile {
        @SerializedName("livros")
        private Book[] list;

        public BooksJSONFile(Book[] list) {
            this.list = list;
        }
    }
}
