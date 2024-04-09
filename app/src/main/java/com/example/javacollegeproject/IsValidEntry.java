package com.example.javacollegeproject;

public interface IsValidEntry {

     public static int seatno = 0;

    public static boolean isvalidName(String name){
        int n=name.length();
        int[] arr=new int[n];

        for (int i=0;i<n;i++){
            arr[i]=(int) name.charAt(i);
        }

        for (int i=0;i<n;i++){
            if ((arr[i] >= 65 && arr[i]<=90 ) || (arr[i]>=97 && arr[i]<=122)){
            }else {
                return false;
            }
        }
        return true;
    }


    public static boolean isvalidSex(String sex){
        String male="Male";
        String male1="male";
        String female ="Female";
        String female1 ="female";

        boolean ans= male.equals(sex);
        boolean ans1= male1.equals(sex);
        boolean ans2=female.equals(sex);
        boolean ans3=female1.equals(sex);

        if (ans || ans1 || ans2 || ans3 ){
            return true;
        }
        else
            return false;
    }


}
