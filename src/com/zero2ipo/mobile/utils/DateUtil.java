package com.zero2ipo.mobile.utils;

import java.text.SimpleDateFormat; 
import java.util.ArrayList; 
import java.util.Calendar; 
import java.util.Date; 
import java.util.List;

public class DateUtil {

    /** 
     * 获得当天之后的N天日期 
     * @return 
     */ 
    public static List<String> getNextDays(int num) { 
        List<String> list = new ArrayList<String>(); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0; i < num; i++) {
            String dd=sdf.format(getAfterDate(i));
            for(int j=8;j<=22;j++){
                if(j<10){
                    for(int m=0;m<=55;m+=5){
                        if(m<10){
                            String value=dd+" 0"+j+":"+"0"+m;
                            list.add(value);
                        }else{
                            String value=dd+" 0"+j+":"+m;
                            list.add(value);
                        }
                    }
                }else{
                    for(int m=0;m<=55;m+=5){
                        if(m<10){
                            String value=dd+" "+j+":"+"0"+m;
                            list.add(value);
                        }else{
                            String value=dd+" "+j+":"+m;
                            list.add(value);
                        }
                    }
                }
            }
        }
        return list;
    }

    /** 
     * 获取当前日期n天后的日期 
     * @param n:返回当天后的第N天 
     * @return 返回的日期 
     */ 
    public static Date getAfterDate(int n) { 
        Calendar c = Calendar.getInstance(); 
        c.add(Calendar.DAY_OF_MONTH, n); 
        return c.getTime(); 
    }
    public static String getCurrentDateStr(){
      SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
   	  Date date = new Date();    
   	  Calendar   dar=Calendar.getInstance();     
   	  dar.setTime(date);     
   	  return dft.format(dar.getTime());
    }
    public static String getDateOrderNo(){
    	 SimpleDateFormat dft=new SimpleDateFormat("yyyyMMddHHmmssSSS");    
      	  Date date = new Date();    
      	  Calendar   dar=Calendar.getInstance();     
      	  dar.setTime(date);     
      	  return dft.format(dar.getTime());
    }
 public static void main(String args[]){
	  SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	  Date date = new Date();    
	  Calendar   dar=Calendar.getInstance();     
	  dar.setTime(date);     
	  System.out.println("当前时间："+dft.format(dar.getTime()));
	  
	   dar.add(Calendar.HOUR_OF_DAY, 2);
	   System.out.println(dft.format(dar.getTime()));
	   String darTime=dft.format(dar.getTime());

	 List<String> list= getNextDays(2);
	 for(int i=0;i<list.size();i++){
		// System.out.println(list.get(i).replace("-", "").replace(" ",""));
		 long d2=Long.parseLong(darTime.replace("-", "").replace(" ","").substring(0,10));
		 long dd=Long.parseLong(list.get(i).replace("-", "").replace(" ","").substring(0,10));
		 if(dd>d2){
			 System.out.println(list.get(i));
		 }
	 }
 }
 public static  List<String> getLast2Hours(){
	 List<String> last2Hours=new ArrayList<String>();
	  SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");    
	  Date date = new Date();    
	  Calendar   dar=Calendar.getInstance();     
	  dar.setTime(date);     
      dar.add(Calendar.HOUR_OF_DAY, 2);
	   String darTime=dft.format(dar.getTime());
	 List<String> list= getNextDays(2);
	 for(int i=0;i<list.size();i++){
		 long d2=Long.parseLong(darTime.replace("-", "").replace(" ","").substring(0,10));
		 long dd=Long.parseLong(list.get(i).replace("-", "").replace(" ","").substring(0,10));
		 if(dd>d2){
			 last2Hours.add(list.get(i));
		 }
	 }
	 return last2Hours;
 }
    public static  List<String> getLast2Hours(int days,int hours){
        List<String> last2Hours=new ArrayList<String>();
        SimpleDateFormat dft=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        Calendar   dar=Calendar.getInstance();
        dar.setTime(date);
        dar.add(Calendar.MINUTE, hours);
        String darTime=dft.format(dar.getTime());
        List<String> list= getNextDays(days);
        for(int i=0;i<list.size();i++){
            long d2=Long.parseLong(darTime.replace("-", "").replace(" ", "").replace(":", "").substring(0, 12));
            long dd=Long.parseLong(list.get(i).replace("-", "").replace(" ","").replace(":", "").substring(0,12));
            if(dd>d2){
                last2Hours.add(list.get(i));
            }
        }
        return last2Hours;
    }
}