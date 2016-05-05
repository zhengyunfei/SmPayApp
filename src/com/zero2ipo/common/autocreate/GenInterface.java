package com.zero2ipo.common.autocreate;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Date;

public class GenInterface {
	
	private String packageOutPath = "com.zero2ipo.cfj.common";//指定实体生成所在包的路径
	private String authorName = "zhengyunfei";//作者名字
	private String tablename = "cfj_collection";//表名
	private String[] colnames; // 列名数组
	private String[] colTypes; //列名类型数组
	private int[] colSizes; //列名大小数组
    
	   //数据库连接
	private static final String URL ="jdbc:mysql://172.168.6.10:3306/caifujie";
	private static final String NAME = "root";
	private static final String PASS = "zhengyunfei";
	private static final String DRIVER ="com.mysql.jdbc.Driver";

	/*
	 * 构造函数
	 */
	public GenInterface(){
    	//创建连接
    	Connection con;
		//查要生成实体类的表
    	String sql = "select * from " + tablename;
    	PreparedStatement pStemt = null;
    	try {
    		try {
				Class.forName(DRIVER);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
    		con = DriverManager.getConnection(URL,NAME,PASS);
			pStemt = con.prepareStatement(sql);
			ResultSetMetaData rsmd = pStemt.getMetaData();
			int size = rsmd.getColumnCount();	//统计列
			colnames = new String[size];
			colTypes = new String[size];
			colSizes = new int[size];
			for (int i = 0; i < size; i++) {
				colnames[i] = rsmd.getColumnName(i + 1);
				colTypes[i] = rsmd.getColumnTypeName(i + 1);
				colSizes[i] = rsmd.getColumnDisplaySize(i + 1);
			}
			String content = parse(colnames,colTypes,colSizes);
			try {
				File directory = new File("");
				String path=this.getClass().getResource("").getPath();
				String outputPath = directory.getAbsolutePath()+ "/src/"+this.packageOutPath.replace(".", "/")+"/"+"I"+initcap(tablename) + ".java";
				FileWriter fw = new FileWriter(outputPath);
				PrintWriter pw = new PrintWriter(fw);
				pw.println(content);
				pw.flush();
				pw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
//			try {
//				con.close();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
    }

	/**
	 * 功能：生成实体类主体代码
	 * @param colnames
	 * @param colTypes
	 * @param colSizes
	 * @return
	 */
	private String parse(String[] colnames, String[] colTypes, int[] colSizes) {
		StringBuffer sb = new StringBuffer();
		sb.append("package " + this.packageOutPath + ";\r\n");
		tablename=replaceUnderlineAndfirstToUpper(tablename.toLowerCase(),"_","");
		importPackage(sb,this.packageOutPath+"."+initcap(tablename));
		//注释部分
		sb.append("   /**\r\n");
		sb.append("    * "+tablename+" 实体类\r\n");
		sb.append("    * "+new Date()+" "+this.authorName+"\r\n");
		sb.append("    */ \r\n");
		//实体部分
		sb.append("\r\n\r\npublic interface I" + initcap(tablename) + "{\r\n");
		add(sb,tablename);
	    update(sb,tablename);
		delete(sb,tablename);
		findById(sb,tablename);
		findAllList(sb,tablename);
		sb.append("}\r\n");
		
		return sb.toString();
	}
	/**
	 * 导入包
	 *@title
	 *@date 2014-10-17
	 *@author ZhengYunfei
	 * @param sb
	 */
	public static void importPackage(StringBuffer sb,String tablename){
		sb.append("import java.util.List;\r\n");
		sb.append("import java.util.Map;\r\n");
		sb.append("import "+tablename+"Bo"+";\r\n");

	}
	/**
	 * 页面跳转初始化
	 *@title
	 *@date 2014-10-17
	 *@author ZhengYunfei
	 * @param sb
	 * @param tablename
	 */
	public static void init(StringBuffer sb,String tablename){
		infoMsg(sb,"页面跳转初始化");
		sb.append("\t@Autowired @Qualifier(\"article\")\r\n");
		sb.append("\rprivate I"+initcap(tablename)+" "+tablename+";\r\n");
		sb.append("\t@RequestMapping(\"forInit.shtml\")\r\n");
		sb.append("\tpublic ModelAndView forInit.shtml(){\t\n");
		sb.append("\tModelAndView mv=new ModelAndView();\r\n");
		sb.append("\treturn mv;\r\n");
		sb.append("\t}\r\n");
	}
	public static void add(StringBuffer sb,String tablename){
		infoMsg(sb,"新增");
	    sb.append("\tpublic String add("+tablename+"Bo bo);\r\n");
	}
	public static void update(StringBuffer sb,String tablename){
		infoMsg(sb,"修改");
	    sb.append("\tpublic String update("+initcap(tablename)+" bo);\r\n");
	}
	public static void delete(StringBuffer sb,String tablename){
		infoMsg(sb,"删除");
	    sb.append("\tpublic String delete(String id);\r\n");
	}
	/**
	 * 根据id查询
	 *@title
	 *@date 2014-10-17
	 *@author ZhengYunfei
	 * @param sb
	 * @param tablename
	 */
	public static void findById(StringBuffer sb,String tablename){
		infoMsg(sb,"根据Id查询");
	    sb.append("\tpublic "+tablename+"Bo"+" findById(String id);\r\n");
	}
	/**
	 * 查询所有信息
	 *@title
	 *@date 2014-10-17
	 *@author ZhengYunfei
	 * @param sb
	 * @param tablename
	 */
	public static void findAllList(StringBuffer sb,String tablename){
		infoMsg(sb,"查询所有信息");
	    sb.append("\tpublic String findAllList(Map<String,Object> queryMap,String curNo, String curSize);\r\n");
	}
	/**
	 * 自动生成注释
	 *@title
	 *@date 2014-10-17
	 *@author ZhengYunfei
	 */
	public static void infoMsg(StringBuffer sb,String msg){
		sb.append("\t/**\r\n");
		sb.append("\t*"+msg+"\r\n");
		sb.append("\t*@author zhengYunFei\r\n");
		sb.append("\t*@date "+new Date()+"\r\n");
		sb.append("\t*/\r\n");
	}
	/** 
	* 首字母大写 
	*  
	* @param srcStr 
	* @return 
	*/  
	public static String firstCharacterToUpper(String srcStr) {  
	   return srcStr.substring(0, 1).toUpperCase() + srcStr.substring(1);  
	}  
	/** 
	* 替换字符串并让它的下一个字母为大写 
	* @param srcStr 
	* @param org 
	* @param ob 
	* @return 
	*/  
	public static String replaceUnderlineAndfirstToUpper(String srcStr,String org,String ob)  
	{  
	   String newString = "";  
	   int first=0;  
	   while(srcStr.indexOf(org)!=-1)  
	   {  
	    first=srcStr.indexOf(org);  
	    if(first!=srcStr.length())  
	    {  
	     newString=newString+srcStr.substring(0,first)+ob;  
	     srcStr=srcStr.substring(first+org.length(),srcStr.length());  
	     srcStr=firstCharacterToUpper(srcStr);  
	    }  
	   }  
	   newString=newString+srcStr;  
	   return newString;  
	}  
	

	/**
	 * 功能：将输入字符串的首字母改成大写
	 * @param str
	 * @return
	 */
	private static String initcap(String str) {
		
		char[] ch = str.toCharArray();
		if(ch[0] >= 'a' && ch[0] <= 'z'){
			ch[0] = (char)(ch[0] - 32);
		}
		
		return new String(ch);
	}

	/**
	 * 出口
	 * TODO
	 * @param args
	 */
	public static void main(String[] args) {
		
		new GenInterface();
		
	}

}