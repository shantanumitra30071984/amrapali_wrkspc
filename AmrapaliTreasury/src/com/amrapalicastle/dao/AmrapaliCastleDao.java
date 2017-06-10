package com.amrapalicastle.dao;

import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.CharacterData;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.amrapalicastle.bean.AmrapaliCastleBean;
import com.amrapalicastle.ibatis.IBatisClientFactory;
import com.ibatis.sqlmap.client.SqlMapClient;

public class AmrapaliCastleDao {
public void parXmlData(AmrapaliCastleBean amrapaliBean){
	try {
		SqlMapClient sqlClient=IBatisClientFactory.getIBatisClient();
		String count=(String)sqlClient.queryForObject("amrapali.totalcurrentusers");
		int totalUsers=Integer.parseInt(count);
		System.out.println("TOTAL USERS==="+totalUsers);
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		Map<String,Object>map=new HashMap<String,Object>();
	    is.setCharacterStream(new StringReader(amrapaliBean.getXmlData()));
	    Document doc = db.parse(is);
	    NodeList nodes = doc.getElementsByTagName("User");
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	totalUsers++;
	    	map.put("userId", (totalUsers)+"");
	    	//map.put("userId", "50");
	        Element element = (Element) nodes.item(i);

	        NodeList Name = element.getElementsByTagName("UserName");
	        Element line = (Element) Name.item(0);
	        map.put("userName", getCharacterDataFromElement(line));

	        NodeList FlatNo = element.getElementsByTagName("FlatNo");
	        line = (Element) FlatNo.item(0);
	        map.put("flatNo", getCharacterDataFromElement(line));
	        NodeList Block = element.getElementsByTagName("Block");
	        line = (Element) Block.item(0);
	        map.put("block", getCharacterDataFromElement(line));
	        
	        NodeList SquareFt = element.getElementsByTagName("SquareFt");
	        line = (Element) SquareFt.item(0);
	        map.put("sqrFt", getCharacterDataFromElement(line));
	        System.out.println("MAP===="+map);
	        
	        sqlClient.insert("amrapali.insertUsers", map);
	        System.out.println("MAP===="+map);
	      }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    
}

public static String getCharacterDataFromElement(Element e) {
    Node child = e.getFirstChild();
    if (child instanceof CharacterData) {
      CharacterData cd = (CharacterData) child;
      if(cd.getData().equals("No dues")){
    	  cd.setData("0");
      }
      return cd.getData();
    }
    return "";
  }
public List<AmrapaliCastleBean>getUserDetails(AmrapaliCastleBean amrapaliCastleBean){
	SqlMapClient sqlClient=IBatisClientFactory.getIBatisClient();
	List<AmrapaliCastleBean>list=null;
	Map<String,Object>map=new HashMap<String,Object>();
	try{
		map.put("block", amrapaliCastleBean.getBlock());
		map.put("year", amrapaliCastleBean.getYear());
		System.out.println("MAP===="+map);
list=sqlClient.queryForList("amrapali.userPaymentDetails",map);
System.out.println("LIST SIZE DAO===="+list.size());
	}catch(Exception ex){
		ex.printStackTrace();
	}
	return list;
}
public void savePayment(AmrapaliCastleBean aBean){
	SqlMapClient sqlClient=IBatisClientFactory.getIBatisClient();
	try{
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource is = new InputSource();
		Map<String,Object>map=new HashMap<String,Object>();
	    is.setCharacterStream(new StringReader(aBean
	    		.getXmlData()));
	    Document doc = db.parse(is);
	    NodeList nodes = doc.getElementsByTagName("User");
	    for (int i = 0; i < nodes.getLength(); i++) {
	    	
	    	//map.put("userId", "50");
	        Element element = (Element) nodes.item(i);

	        NodeList Name = element.getElementsByTagName("UserName");
	        Element line = (Element) Name.item(0);
	        map.put("userName", getCharacterDataFromElement(line));

	        NodeList FlatNo = element.getElementsByTagName("FlatNo");
	        line = (Element) FlatNo.item(0);
	        map.put("flatNo", getCharacterDataFromElement(line));
	        NodeList Block = element.getElementsByTagName("Block");
	        line = (Element) Block.item(0);
	        map.put("block", getCharacterDataFromElement(line));
	        
	        NodeList SquareFt = element.getElementsByTagName("SquareFt");
	        line = (Element) SquareFt.item(0);
	        map.put("sqrFt", getCharacterDataFromElement(line));
	        
	        
	        NodeList Year = element.getElementsByTagName("Year");
	        line = (Element) Year.item(0);
	        map.put("year", getCharacterDataFromElement(line));
	        
	        
	        
	        NodeList Jan = element.getElementsByTagName("Jan");
	        line = (Element) Jan.item(0);
	        map.put("jan", getCharacterDataFromElement(line));
	        
	        NodeList Feb = element.getElementsByTagName("Feb");
	        line = (Element) Feb.item(0);
	        map.put("feb", getCharacterDataFromElement(line));
	        
	        NodeList Mar = element.getElementsByTagName("Mar");
	        line = (Element) Mar.item(0);
	        map.put("mar", getCharacterDataFromElement(line));
	        
	        NodeList Apr = element.getElementsByTagName("Apr");
	        line = (Element) Apr.item(0);
	        map.put("apr", getCharacterDataFromElement(line));
	        
	        NodeList May = element.getElementsByTagName("May");
	        line = (Element) May.item(0);
	        map.put("may", getCharacterDataFromElement(line));
	        
	        NodeList Jun = element.getElementsByTagName("Jun");
	        line = (Element) Jun.item(0);
	        map.put("jun", getCharacterDataFromElement(line));
	        
	        NodeList Jul = element.getElementsByTagName("Jul");
	        line = (Element) Jul.item(0);
	        map.put("jul", getCharacterDataFromElement(line));
	        
	        NodeList Aug = element.getElementsByTagName("Aug");
	        line = (Element) Aug.item(0);
	        map.put("aug", getCharacterDataFromElement(line));
	        
	        
	        NodeList Sep = element.getElementsByTagName("Sep");
	        line = (Element) Sep.item(0);
	        map.put("sep", getCharacterDataFromElement(line));
	        
	        NodeList Oct = element.getElementsByTagName("Oct");
	        line = (Element) Oct.item(0);
	        map.put("oct", getCharacterDataFromElement(line));
	        
	        NodeList Nov = element.getElementsByTagName("Nov");
	        line = (Element) Nov.item(0);
	        map.put("nov", getCharacterDataFromElement(line));
	        
	        NodeList Dec = element.getElementsByTagName("Dec");
	        line = (Element) Dec.item(0);
	        map.put("dec", getCharacterDataFromElement(line));
	        
	        NodeList dueAmount = element.getElementsByTagName("DueAmount");
	        line = (Element) dueAmount.item(0);
	        map.put("dueAmount", getCharacterDataFromElement(line));
	        
	        
	        NodeList dueAmountExcel = element.getElementsByTagName("DueAmountExcel");
	        line = (Element) dueAmountExcel.item(0);
	        map.put("dueAmountExcel", getCharacterDataFromElement(line));
	        map.put("userId", (i+1)+"");
	        System.out.println("MAP===="+map);
	        if(aBean.getFlag().equals("UPDATE"))
	        {
	        	sqlClient.update("amrapali.updateUserPaymentDetails", map);
	        	
	        }else if(aBean.getFlag().equals("INSERT")){
	        	sqlClient.update("amrapali.insertUsersPayment", map);
	        }
	        //System.out.println("MAP===="+map);
	      }
	}catch(Exception ex){
		ex.printStackTrace();
	}
}

public String getCurrentMonth(){
	
	SqlMapClient sqlClient=IBatisClientFactory.getIBatisClient();
	String []todaysDate;
	String monthNo="";
	try{
		String date=(String)sqlClient.queryForObject("amrapali.currentMonth");
		todaysDate=date.split("/");
		int month=Integer.parseInt(todaysDate[1]);
		month=month-1;
		monthNo=month+"";
	}catch (Exception e) {
		e.printStackTrace();
		// TODO: handle exception
	}
	return monthNo;
}
public String getCurrentYear(){
	String currentYear="";
	SqlMapClient sqlClient=IBatisClientFactory.getIBatisClient();
	try{
		currentYear=(String)sqlClient.queryForObject("amrapali.currentYear");
		System.out.println("currentYear===="+currentYear);
	}catch(Exception ex){
		
	}
	return currentYear;
}
public int getPreviousYearDuesTotal(String userId,String currentYear){
	
	return 0;
}
}
