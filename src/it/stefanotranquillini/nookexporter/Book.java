package it.stefanotranquillini.nookexporter;

import java.util.ArrayList;

/**
 * Created by stefanotranquillini on 5/16/13.
 */
public class Book {
    private String book = "";
    private ArrayList<Annotation> annotations = null;


    public Book(String s){
        this.book=s;
        this.annotations= new ArrayList<Annotation>();
    }
    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public void addAnnotation(Annotation a){
        this.annotations.add(a);
    }

    public ArrayList<Annotation> getAnnotations(){
        return this.annotations;
    }
    public String getBookTitle() {
        int start = book.lastIndexOf("/") < 0 ? 0 : book.lastIndexOf("/") + 1;
        int end = book.lastIndexOf(".") < 0 ? book.length() : book.lastIndexOf(".");
        return book.substring(start, end);
    }

    @Override
    public boolean equals(Object o) {
        Book b = (Book)o;
        return b.book.equals(this.book);
    }
}
