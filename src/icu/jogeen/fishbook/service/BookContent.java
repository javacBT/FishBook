package icu.jogeen.fishbook.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class BookContent {

	private List<String> chapter = new ArrayList();
	private List<String> content = new ArrayList();
	
	public BookContent(String address){
		getBook(address);
	}
	
	
	private void getBook(String x){

		chapter.clear();
		content.clear();

		File file = new File(x);
		StringBuilder result = new StringBuilder();
		
		try{
			
			BufferedReader br = new BufferedReader(new FileReader(file));
			String s = null;
			String datas = "";
            while((s = br.readLine())!=null){

				if(!chuli(s)){

					datas = datas + s + "\r\n";

				}else{

					if (chapter.size()>1)content.add(datas);
					datas = "";
				}

            }

			if (chapter.size()>1)content.add(datas);
            
            br.close();
			
        }catch(Exception e){
            e.printStackTrace();
        }
		
		
	}

	
	
	private boolean chuli(String s){

		if(s.length()<=30){
			
			int Verification = 0;
			for(int i=0;i<s.length();i++){
				if(s.substring(i,i+1).equals("第"))Verification++;
				if(Verification>0)
					if(s.substring(i,i+1).equals("章") || s.substring(i,i+1).equals("节") || s.substring(i,i+1).equals("卷") || s.substring(i,i+1).equals("篇")){
						chapter.add(s);
						return true;
					}
			}
			
		}
		
		return false;
	}
	
	
	
	
	
	public List<String> getChapter(){
		return chapter;
	}
	
	public List<String> getContent(){
		return content;
	}
	
}
