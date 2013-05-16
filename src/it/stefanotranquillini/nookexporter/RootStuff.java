package it.stefanotranquillini.nookexporter;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.widget.Toast;

public class RootStuff {
	public void copy(File src, File dst) throws IOException {
	    InputStream in = new FileInputStream(src);
	    OutputStream out = new FileOutputStream(dst);

	    // Transfer bytes from in to out
	    byte[] buf = new byte[1024];
	    int len;
	    while ((len = in.read(buf)) > 0) {
	        out.write(buf, 0, len);
	    }
	    in.close();
	    out.close();
	}
	
	
	public boolean CopyDB(){
		Process p;  
		try {  
		   // Preform su to get root privledges  
		   p = Runtime.getRuntime().exec("su");   
		   System.out.println("here we are");
		   // Attempt to write a file to a root-only  
		   copy(new File("/data/data/com.bn.nook.reader.activities/databases/annotations.db"),new File("/data/data/it.stefanotranquillini.nookexporter/databases/databases.db"));
		   DataOutputStream os = new DataOutputStream(p.getOutputStream());  
		  
		   // Close the terminal  
		   os.writeBytes("exit\n");  
		   os.flush();  
		   
		   System.out.println("copy is finished");
		   try {  
		      p.waitFor();  
		           if (p.exitValue() != 255) {  
		              // TODO Code to run on success 
		        	   System.out.println("root");
		        	   return true;
		           }  
		           else {  
		        	   System.out.println("no root");
		        	   return false;
		           }  
		   } catch (InterruptedException e) {  
		      // TODO Code to run in interrupted exception 
			   System.err.println("interupped ");
			   e.printStackTrace();
			   return false;
			   
		   }  
		} catch (IOException e) {  
		   // TODO Code to run in input/output exception 
			System.err.println("io ex");
			e.printStackTrace();
			   return false;
		}  
	}
}
