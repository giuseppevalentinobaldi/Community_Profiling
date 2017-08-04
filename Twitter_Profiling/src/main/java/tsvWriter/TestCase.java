package tsvWriter;

public class TestCase {
	
	public static void main(String [] args){
		TsvPrinter tsv = new TsvPrinter();
		tsv.init("timestamp.tsv");
		
		String[] arrayTime={"1","2","3","4"};
		
		tsv.writeRow(arrayTime);
		
		tsv.close();
		
		
	}

}
