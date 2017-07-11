package tsvWriter;

import java.io.FileWriter;
import java.io.IOException;

import com.univocity.parsers.tsv.TsvWriter;
import com.univocity.parsers.tsv.TsvWriterSettings;

public class TsvPrinter {
	
	private TsvWriterSettings setting;
	private TsvWriter writer;
	
	public TsvPrinter(){
		this.setting = new TsvWriterSettings(); //inizializzo l'oggetto setting del parser
		setting.setNullValue("?"); //cambia i valori nulli con ?
		setting.getFormat().setComment('-'); //cambia i commenti con -
		setting.setEmptyValue("!"); //se il valore è empty metto un !
	    setting.setSkipEmptyLines(false); 		
	}
	
	public void init(String fileName){
		try {
			this.setWriter(new TsvWriter(new FileWriter(fileName), this.setting)); //crea l'oggetto writer con i settaggi fatti nel costruttore
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void writeRow(String[] value){
		this.getWriter().writeRow(value);
	}
	
	public void close(){
		this.getWriter().close();
	}

	public TsvWriter getWriter() {
		return writer;
	}

	public void setWriter(TsvWriter writer) {
		this.writer = writer;
	}

}
