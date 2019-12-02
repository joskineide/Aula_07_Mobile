package usjt.com.aula07restjson;

import java.io.Serializable;

public class Book implements Serializable {
    private Long id;
    private String title;
    private String writer;
    private int numberPages;
    private String edition;

    // construtor
    public Book(String title, String writer, String edition) {
        setTitle(title);
        setWriter(writer);
        setEdition(edition);
    }

    public Book(String title, String writer, int numberPages, String edition) {
        setTitle(title);
        setWriter(writer);
        setNumberPages(numberPages);
        setEdition(edition);
    }

    public Book() {

    }

    // gets e sets
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public int getNumberPages() {
        return numberPages;
    }

    public void setNumberPages(int numberPages) {
        this.numberPages = numberPages;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }
}
