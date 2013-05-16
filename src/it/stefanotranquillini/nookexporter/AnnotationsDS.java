package it.stefanotranquillini.nookexporter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class AnnotationsDS {


    // Database fields
    private SQLiteDatabase database;
    private DataBaseHelper dbHelper;
    private String[] allColumns = {DataBaseHelper.COLUMN_BOOK, DataBaseHelper.COLUMN_HTTEXT};

    public AnnotationsDS(Context context) {
        dbHelper = new DataBaseHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }



    public List<Book> getBooksWithAnnotations() {
        List<Book> books = new ArrayList<Book>();

        Cursor cursor = database.query(DataBaseHelper.TABLE_COMMENTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Book book = getBook(cursor);
            if (books.contains(book))
                book = books.get(books.indexOf(book));
            else
                books.add(book);
            Annotation annotation = getAnnotation(cursor);
            book.addAnnotation(annotation);
            cursor.moveToNext();
        }
        // Make sure to close the cursor
        cursor.close();
        return books;
    }

    private Book getBook(Cursor cursor) {
        return new Book(cursor.getString(0));
    }

    private Annotation getAnnotation(Cursor cursor) {
        return new Annotation(cursor.getString(1));


    }
}
