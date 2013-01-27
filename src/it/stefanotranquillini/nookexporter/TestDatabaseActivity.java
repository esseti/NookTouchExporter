package it.stefanotranquillini.nookexporter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class TestDatabaseActivity extends Activity {
	private AnnotationsDS datasource;

	private void writeFile(List<Annotation> values){
	    
	    try
	    {
		    File file = new File("/sdcard/annotations.txt");
	        if (!file.exists())
	        	file.createNewFile();
	        FileWriter writer = new FileWriter(file);
	        for(Annotation a:values){
		        writer.append(a.getBookTitle() + System.getProperty( "line.separator" ));
		        writer.append(a.getHltext() + System.getProperty( "line.separator" ));
		        writer.append(System.getProperty( "line.separator" ));
		    }
	        writer.flush();
	        writer.close();

	        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
	    }
	    catch(IOException e)
	    {
	         e.printStackTrace();
	    }
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_database);
		RootStuff root = new RootStuff();
		Boolean ret = root.CopyDB();
//		if (ret) {
//			
//		DataBaseHelper myDbHelper = new DataBaseHelper(this);
//		try {
//			 
//	 		myDbHelper.openDataBase();
//	 
//	 	}catch(SQLException sqle){
//	 
//	 		throw sqle;
//	 
//	 	}
//        myDbHelper = new DataBaseHelper(this);
		
			datasource = new AnnotationsDS(this);
			datasource.open();

			List<Annotation> values = datasource.getAllAnnotations();
			writeFile(values);
			datasource.close();
			
			// Use the SimpleCursorAdapter to show the
			// elements in a ListView
			
//		}
		
	}

	
}
