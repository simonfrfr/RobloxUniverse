import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.io.File;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class RobloxPHPJAVA {
	static private boolean debug = false;
	static private boolean sendSelf = false;
	private static void sendMostRecentFrame(String sdf, File Fke, int placecarry) throws FileNotFoundException
	{
		long localcnt = 0;
		long lines = 0;
		Scanner toSend = new Scanner(Fke);
		String latest = "";
		while(toSend.hasNext()){
	    	String mlde = toSend.nextLine();
	    	if (mlde.length() == 19 && mlde.charAt(4) == '/' && mlde.charAt(7) == '/' && mlde.charAt(10) == ' '){
	    		//System.out.println("Sending Data for: " +Fke.getName());
	    		latest = mlde;
	    		localcnt = lines;
	    	}
	    	lines++;
		}
		toSend.close();
		toSend = new Scanner(Fke);
		lines = 0;
		long locallinenumber = 0;
		JSONObject jo = new JSONObject();
		if (latest.isEmpty() == false){
			//System.out.println("The Latest Data was From: "+latest);
			//System.out.println("The Data starts on Line "+ localcnt);
			while(toSend.hasNext()){
		    	String mlde = toSend.nextLine();
		    	if (lines > localcnt+1){
		    		locallinenumber++;
		    		jo.put("Part"+ (lines-localcnt-1), mlde);
		    	}
		    	lines++;
			}
			jo.put("PublicKey", "Place"+placecarry);
			jo.put("NumberOfParts", locallinenumber);
			System.out.println(jo);
		}
		toSend.close();
		
	}
	public static void main(String[] args) throws ParseException, IOException {
		// TODO Auto-generated method stub
		//when executed from PHP: exec("java -jar file.jar arguments", $output);
		Calendar cal = Calendar.getInstance();
    	cal.getTime();
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    	String timeStamp = sdf.format(cal.getTime());
    	String lmk = sdf +"";
		String s;
		s = "{\"Part4\":\"Name Script Class Script Parent Workspace Archivable True\",\"PublicKey\":\"avjSZttpvBfKSoxatFiq8E5tyASaPFi6iHLcjTwLe2t7ZEsxWGOMBjj5Lo75o9ikW6gbGzwRxkeryAHViI56igEgzl6qanBFSA8nvlMF0reYGh8VOQSkzU2BwXuJS7uWTKGulXyNt5hhzRztU5W6fri42uivG5DksPqiZmzSjz7GQJ7u5y0JojD415NfSgHKcxLeKyB9yt3o2P6YrNFvZ8lUQgCLDk3fqHLqmVT8WkBfFgcfmhkV5JhkAR7eaXeItwt57effGFkmv5CQ5SjaBlERN1Vg22vaXC913jUcK7vjct2mXDQKyXfjDYUymUOimMpL1kBFeVgCkoD8hNKC1NNQAu6wWnZ03xRRfA8nAMupefWVHQKfa9MnfRJpGvPrOClhyISFnHGpPnSPpOOVb3ZYLa0rylF1azQJykeuLlAGLancsCq6tX9m9xEwxevePx3lCulxoh4JWOuO1DE6WfHQekq5O4Zx8tQJyG69MezmTGDwPTDQ1zxpt2L8jNZT\",\"NumberOfParts\":4,\"Part2\":\"Name BasePlate Class Part Parent Workspace Archivable True CFrame  X 0 Y -0.61000001430511 Z 0 p.X 0 Y -0.61000001430511 Z 0 lookVector.X -0 lookVector.Y -0 lookVector.Z -1 Size  X 512 Y 1.2000000476837 Z 512\",\"Part1\":\"Name Terrain Class Terrain Parent Workspace Archivable True\",\"Part3\":\"Name Camera Class Camera Parent Workspace Archivable True\"}";
		if (debug == false) {s = args[0];}
		JSONObject json = (JSONObject)new JSONParser().parse(s);
		long NumberOfParts = (long) json.get("NumberOfParts");
		String PublicKey = (String) json.get("PublicKey");
		boolean keyExists = false;
		Scanner file = new Scanner(new File("PublicKeys.txt"));
	    int count = 0;
	    while(file.hasNext()){
	    	String kkl = file.nextLine();
	    	if (kkl.equals(PublicKey)) {
	        keyExists = true;
	        //System.out.println("keyExists = true");
	    	}
	    	//System.out.println(kkl);
	        count++;
	    }
	    file.close();
	  //  System.out.println("There are "+count+" Public Keys");
	  //  System.out.println("yyyy/MM/dd HH:mm:ss".length());
	    if (keyExists == false) {return;}
		
	    PrintWriter out;
		//System.out.println("PublicKey = " + json.get("PublicKey"));
			File f = new File("Place"+PublicKey.substring(0,32)+".rbxdat");
			if (f.exists()){
			FileWriter fw = new FileWriter(f,true);
  	  		BufferedWriter bw = new BufferedWriter(fw);
  	  		out = new PrintWriter(bw);
		}
			else {
			out = new PrintWriter("Place"+PublicKey.substring(0,32)+".rbxdat");
		}
		//if (out.){out.println("exists!");}
		out.println();
		out.println(timeStamp);
		//System.out.println("NumberOfParts = " + json.get("NumberOfParts"));
		out.println(NumberOfParts);
		for (int i = 1; i <= NumberOfParts; i++) {
			//System.out.println("Part"+i+" = " + json.get("Part"+i));
			out.println(json.get("Part"+i));
		}
		//out.
		out.close();
		Scanner file2 = new Scanner(new File("PublicKeys.txt"));
		int keynumber = 0;
	    while(file2.hasNext()){
	    	keynumber++;
	    	String kkl2 = file2.nextLine();
	    	File fs = new File("Place"+kkl2.substring(0,32)+".rbxdat");
	    	if (fs.exists() && fs.getName().equals("Place"+PublicKey.substring(0,32)+".rbxdat") == sendSelf){
	    		sendMostRecentFrame(lmk,fs, keynumber);
	    	}
	    }
	    file2.close();
	   // return key[keynumber];
		//fw.close();
		//Float.parseFloat(float); use to parse floats
	}


}
