package it.stefanotranquillini.nookexporter;

public class Annotation {
	private String book;
	private String hltext;
	public String getBook() {
		return book;
	}
	public void setBook(String book) {
		this.book = book;
	}
	public String getHltext() {
		return hltext;
	}
	public void setHltext(String hltext) {
		this.hltext = hltext;
	}
	
	public String getBookTitle(){
		int start = book.lastIndexOf("/")<0 ? 0 : book.lastIndexOf("/")+1;
		int end = book.lastIndexOf(".")<0? book.length() : book.lastIndexOf(".");
		return book.substring(start,end);
	}
	
	
	
}
