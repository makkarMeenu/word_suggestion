
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class spellSuggestion {
		static ArrayList<String> dictionary=new ArrayList<String>();
	static HashMap<String , Integer> stringScore=new HashMap<String, Integer>();
	static HashMap<Integer, ArrayList<String>> getFast=new HashMap<Integer, ArrayList<String>>();
	static int dictHash[][]=new int[100000][27];
	static int wordHash[]=new int[27];
	static int lowest=9999;

	static Boolean readCSV(String fileInput){
		try {
			int l=0;
			BufferedReader br = new BufferedReader(new FileReader("./input.csv"));
            while (l<1000) {
            	l++;
            			String line=br.readLine();
            			if(line==null)
            				break;
            			//parsing , removing ',' and double quotes
            			line=line.replaceAll(",", "");
            			line=line.replaceAll("\"", "");
            			dictionary.add(line);
            }
			}catch(Exception e){
				//if any error occurs , filnotfound or error while reading then return false
				return false;
			}
		return true;
	}
		//prefix
		//hash match score
	static void hashGenerator(){
		for(int i=0;i<dictionary.size();i++){
			String s=dictionary.get(i);
			stringScore.put(s, 0);
			for(int j=0;j<s.length();j++){
				char ss=Character.toLowerCase(s.charAt(j));
				if(ss-97>0)
				dictHash[i][ss-97]++;
			}
		}
	}
	static void wordHash(String s){
		for(int i=0;i<s.length();i++){
			char ss=Character.toLowerCase(s.charAt(i));
			wordHash[ss-97]++;
		}
	}
	static void calculate(String s, int a){
		if(a>=dictionary.size())
			return;
		int counter=0;
		for(int i=0;i<=26;i++){
			if(wordHash[i]!=dictHash[a][i])
				counter++;
		}
		if(dictionary.get(a).startsWith(s))
			counter=counter-5;
		else if(dictionary.get(a).contains(s))
			counter=counter-1;
		if (dictionary.get(a).length()==s.length())
		counter=counter-1;
		else if(dictionary.get(a).length()>=s.length()+1)
			counter=counter+1;
		
		stringScore.put(dictionary.get(a), counter);
	
		if(getFast.get(counter)==null)
			getFast.put(counter, new ArrayList<String>());
		getFast.get(counter).add(dictionary.get(a));
		if(counter<lowest)
			lowest=counter;
		
		calculate(s, a+1);
	}
	static void printClosest(){
		int count=0;
		for(int i=lowest;i<=lowest+20;i++){
			if(getFast.containsKey(i)){
			ArrayList<String> res=getFast.get(i);
			for(String s:res){
				if(count>5)
					return;
				System.out.println(s+" ");
				count++;
			}
			}
		}
	}
	public static void main(String[] args) {
		Scanner scan=new Scanner(System.in);
		if(args.length<2){
			System.out.println("Please add Input-file and WordString as Command LIne args. \n format example : \"./input.csv word\"");
			return;
		}
		String fileName=args[0];
		String s=args[1];
	
		if(!readCSV(fileName))
			System.out.println("Error While reading FILE, or doesnt exist!!!!!!\n format example : \"./input.csv word\"");
		hashGenerator();
			wordHash(s);
			calculate(s, 0);
				printClosest();	
	}
}
