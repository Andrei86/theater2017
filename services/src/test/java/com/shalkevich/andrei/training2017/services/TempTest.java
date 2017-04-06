package com.shalkevich.andrei.training2017.services;

public class TempTest {
	

	public static void main(String[] args) {
		
		String s1 = "wer";
		s1 += "1";
		String s2 = new String("wer");
		String s3 = new String("wer");
		//s1 += "1"; 
		
		//System.out.println(s2.equals(s1));
		
		System.out.println(s1.hashCode() + " " + s2.hashCode());
		

	}

}
