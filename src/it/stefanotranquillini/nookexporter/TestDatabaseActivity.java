package it.stefanotranquillini.nookexporter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class TestDatabaseActivity extends Activity {
    private AnnotationsDS datasource;
    private List<Book> books;
    private void writeFile(List<Annotation> values) {

        try {
            File file = new File("/sdcard/annotations.txt");
            if (!file.exists())
                file.createNewFile();
            FileWriter writer = new FileWriter(file);
            for (Annotation a : values) {
                writer.append(a.getText() + System.getProperty("line.separator"));
                writer.append(System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();

            Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_database);

        RootStuff root = new RootStuff();
        Boolean ret = root.CopyDB();
		DataBaseHelper myDbHelper = new DataBaseHelper(this);
        myDbHelper = new DataBaseHelper(this);
        datasource = new AnnotationsDS(this);
        datasource.open();
        books = datasource.getBooksWithAnnotations();
//        books = new ArrayList<Book>();
//        Book b = new Book("prova");
//        books.add(b);
//        b.addAnnotation(new Annotation("a1"));
//        b.addAnnotation(new Annotation("a2"));
        TextView tv = (TextView) findViewById(R.id.textView);
        String s = "";
        Integer total = 0;
        System.out.print("books " + books.size());
        for (Book book : books) {
            total+= book.getAnnotations().size();
//                s+=("\t" + a.getText() + System.getProperty("line.separator"));
        }
        tv.setText(getString(R.string.message_text_field,books.size(),total));
        //			writeFile(values);
//			datasource.close();

        // Use the SimpleCursorAdapter to show the
        // elements in a ListView

//		}

    }

    public void onBtnClicked(View v) {
        if (v.getId() == R.id.button) {
            Toast.makeText(this, R.string.export_message, Toast.LENGTH_SHORT).show();
            try {
                File file = new File("/sdcard/annotations.txt");
                if (!file.exists())
                    file.createNewFile();
                FileWriter writer = new FileWriter(file);
                for (Book book : books) {
                    writer.append("Book:" + book.getBookTitle() + System.getProperty("line.separator"));
                    for(Annotation a : book.getAnnotations())
                        writer.append("\t" + a.getText() + System.getProperty("line.separator"));
                    writer.append(System.getProperty("line.separator"));
                }
                writer.flush();
                writer.close();
                Toast.makeText(this, getString(R.string.export_message_end), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
            super.finish();
        }
    }


}
