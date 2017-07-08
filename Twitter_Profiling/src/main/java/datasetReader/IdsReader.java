package datasetReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.Iterator;

public class IdsReader {

	private File filePath;
	private BufferedReader reader;
	private Iterator<String> stream;

	public IdsReader(String path) {
		this.setFilePath(new File(path));
		try {
			this.setReader(new BufferedReader(new InputStreamReader(new FileInputStream(getFilePath()))));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		this.stream = this.getReader().lines().iterator();
	}

	public long nextIsAnother() {
		long ids = Long.parseLong(this.getStream().next());
		return ids;
	}
	
	public boolean hasNext(){
		return this.getStream().hasNext();
	}

	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public Iterator<String> getStream() {
		return stream;
	}

	public void setStream(Iterator<String> stream) {
		this.stream = stream;
	}

}
