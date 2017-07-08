package main;

import twitterOntology.main.*;
import influenceOntology.main.*;
import datasetReader.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws Exception {
		
		InputStreamReader is = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(is);
		
		System.out.print("Choose your ontology\n\t 1: Twitter Ontology\n\t 2: Influence Ontology\n (1 or 2)?:");
		String ontology = br.readLine();
		
		boolean bool = true;
		
		// twitter ontology
		if(ontology.equals("1")){
			
			while(bool){
			
				System.out.print("Choose your dataset\n\t 1: Single User\n\t 2: File checkedUsers\n (1 or 2)?:");
				String data = br.readLine();
				
				// single user
				if(data.equals("1")){
					
					bool = false;
					
					System.out.print("Insert User ID:\n");
					String user = br.readLine();
					
					MainTwitterOntology.CreateTwitterOntology(Long.parseLong(user));
					
				}
				// file checkedUsers
				else if(data.equals("2")){
					
					bool = false;
					
					IdsReader reader = new IdsReader("data/checkedUsers.txt");
					
					while(reader.hasNext()){
						MainTwitterOntology.CreateTwitterOntology(reader.nextIsAnother());
					}
					
				}
				
			}
			
		}
		// influence ontology
		else if(ontology.equals("2")){
			
			while(bool){
			
				System.out.print("Choose your dataset\n\t 1: Single User\n\t 2: File checkedUsers\n (1 or 2)?:");
				String data = br.readLine();
				
				// single user
				if(data.equals("1")){
					
					bool = false;
					
					System.out.print("Insert User ID:\n");
					String user = br.readLine();
					
					MainInfluenceOntology.CreateInfluenceOntology(Long.parseLong(user));
					
				}
				// file checkedUsers
				else if(data.equals("2")){
					
					bool = false;
					
					IdsReader reader = new IdsReader("data/checkedUsers.txt");
					
					while(reader.hasNext()){
						MainInfluenceOntology.CreateInfluenceOntology(reader.nextIsAnother());
					}
					
				}
				
			}
			
		}
		else{	
			main(args);
		}

		System.exit(0);
		
	}

}
