package assignment_8a;

import java.util.ArrayList;

public class Q_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
      System.out.println(get_ss_ASCII("ab"));
		//prin_ss_ASCII("ab","  ");
	}
	public static ArrayList<String> get_ss_ASCII(String str){
		if(str.length()==0){
			ArrayList<String> base = new ArrayList<>();
			base.add(" ");
			return base;
		}
		char cc=str.charAt(0);
		ArrayList<String> f_res=new ArrayList<>();
		ArrayList<String>rec_res=new ArrayList<>();
		rec_res=get_ss_ASCII(str.substring(1));
		for(int i=0;i<rec_res.size();i++){
			f_res.add(rec_res.get(i));
			f_res.add(cc+rec_res.get(i));
			f_res.add((int)(cc)+rec_res.get(i));
		}
		return f_res;
	}
	public static void prin_ss_ASCII(String str, String res){
		if(str.length()==0){
			System.out.println(res);
			return;
		}
		char cc= str.charAt(0);
		prin_ss_ASCII(str.substring(1),res);
		prin_ss_ASCII(str.substring(1),res+cc);
		prin_ss_ASCII(str.substring(1),res+(int)(cc));
	}
	public static int count_ss_ASCII(String str){
		if(str.length()==0){
			return 1;
		}
		int count=3*(count_ss_ASCII(str.substring(1)));
		return count;
	}
}
