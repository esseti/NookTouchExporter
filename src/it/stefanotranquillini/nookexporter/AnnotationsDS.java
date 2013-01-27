package it.stefanotranquillini.nookexporter;
import java.util.ArrayList;
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
	  private String[] allColumns = { DataBaseHelper.COLUMN_BOOK,DataBaseHelper.COLUMN_HTTEXT };

	  public AnnotationsDS(Context context) {
	    dbHelper = new DataBaseHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }


	  public List<Annotation> getAllAnnotations() {
	    List<Annotation> annotations = new ArrayList<Annotation>();

	    Cursor cursor = database.query(DataBaseHelper.TABLE_COMMENTS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Annotation  annotation = cursorToAnnotation(cursor);
	      annotations.add(annotation);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return annotations;
	  }

	  private Annotation cursorToAnnotation(Cursor cursor) {
	    Annotation comment = new Annotation();
	    comment.setBook(cursor.getString(0));
	    comment.setHltext(cursor.getString(1));
	    return comment;
	  }
}
