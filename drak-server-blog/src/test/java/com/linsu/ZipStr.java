package com.linsu;

public class ZipStr {

    public static void main(String[] args) {
        String str = "aabbccabc";
        System.out.println(zipString(str));
        System.out.println(zipString2(str));
    }

    public static String zipString(String iniString) {
        // write code here
        int low = 0 , high = 0 ;
        int len = iniString.length();
        StringBuilder sb = new StringBuilder();
        char c = ' ';
        int count = 0;
        while(low < len){
            high = low;
            c = iniString.charAt(low);
            while((high < len)&&(iniString.charAt(high) == c)){// 这句话说明连续的相同字符
                high ++;
            }
            count = high - low ;
            sb.append(c);
            sb.append(count);
            low = high;// 这句话跳过相同的
        }
        return sb.toString();
    }

    public static String zipString2(String str){
        char[] charts = str.toCharArray();
        StringBuilder result = new StringBuilder();
        for(int i=0;i<charts.length;i++){
            char c = charts[i];
            int count = 0;
            for(int j=i;j<charts.length;j++){
                if(c == charts[j]) {
                    count++;
                }
            }
            if(!result.toString().contains(String.valueOf(c))){
                result.append(c);
                result.append(count);
            }

        }
        return result.toString();
    }
}
