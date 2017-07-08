package datasetReader;

public class TestCase {
	
	public static void main(String [] args){
		IdsReader ir = new IdsReader("data/checkedUsers.txt");
		
		while (ir.hasNext()){
			System.out.println(ir.nextIsAnother());
		}
	}

}
