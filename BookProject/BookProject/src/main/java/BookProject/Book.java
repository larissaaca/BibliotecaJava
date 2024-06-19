package BookProject;

import com.google.gson.annotations.SerializedName;

public class Book {
    @SerializedName("autor")
    private String author;

    @SerializedName("titulo")
    private String title;

    @SerializedName("genero")
    private String theme;

    @SerializedName("exemplares")
    private int numCopies;

    public Book(String author, String title, String theme, int numCopies) {
        this.author = author;
        this.title = title;
        this.theme = theme;
        this.numCopies = numCopies;
    }

    public String getAuthor() { return author; }
    public String getTitle() { return title; }
    public String getTheme() { return theme; }
    public int getNumCopies() { return numCopies; }

    public int takeAllCopies() {
        int all = numCopies;
        numCopies = 0;
        return all;
    }

    public int takeCopies(int quantity) {
        if(quantity > numCopies) {
            return takeAllCopies();
        }
        numCopies -= quantity;
        return quantity;
    }

    public void returnCopies(int quantity) {
        numCopies += quantity;
    }
}
