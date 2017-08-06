package com.amrapalicastle.controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.amrapalicastle.bean.AmrapaliCastleBean;
import com.amrapalicastle.dao.AmrapaliCastleDao;

@Controller
public class AmrapaliCastleController {
@RequestMapping(value="/AmrapaliCastleUsersCreation.html",method=RequestMethod.GET)
public ModelAndView amrapaliCastleUsersCreation(HttpServletRequest request,HttpServletResponse response){
	System.out.println("In Controller");
	ModelAndView mav=new ModelAndView("amrapali_users_creation");
	AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	System.out.println("TEST");
	mav.addObject("amrapaliBean",amrapaliBean);
	return mav;
}

@RequestMapping(value="AmrapaliCastleUsersCreation.html",method=RequestMethod.POST,params="save")
public ModelAndView amrapaliCastleUsersCreationPost(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("In Controller1");
	System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("amrapali_users_creation");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	mav.addObject("amrapaliBean",amrapaliBean);
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);
	return mav;
}

@RequestMapping(value="/AmrapaliUsersPaymentStatus.html",method=RequestMethod.GET)
public ModelAndView amrapaliCastleUsersPaymentStatusGet(HttpServletRequest request,HttpServletResponse response){
	System.out.println("In Search Controller1");
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("amrapali_user_payment_status");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	mav.addObject("amrapaliBean",amrapaliBean);
	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}

@RequestMapping(value="/AmrapaliUsersPaymentInsert.html",method=RequestMethod.GET)
public ModelAndView amrapaliCastleUsersPaymentInsertGet(HttpServletRequest request,HttpServletResponse response){
	System.out.println("In Search Controller1");
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("ampr_user_payment_insert");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	amrapaliBean.setFeePerSqrFeet("1");
	List<AmrapaliCastleBean>list=new ArrayList<AmrapaliCastleBean>();
	for(int i=0;i<15;i++){
	AmrapaliCastleBean ampBean=new AmrapaliCastleBean();
	list.add(ampBean);
	}
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	int currentYear=Integer.parseInt(amrapaliDao.getCurrentYear());
	System.out.println("CurrentYear===="+currentYear);
	Map<String,Object>yearMap=new TreeMap<String,Object>();
	for(int i=currentYear-1;i<=currentYear+1;i++){
		yearMap.put(i+"", i);
	}
	amrapaliBean.setYear(currentYear+"");
	String currentMonthNo=amrapaliDao.getCurrentMonth();
	amrapaliBean.setCurrentMonthNo(currentMonthNo);
	amrapaliBean.setCurrentYear(currentYear+"");
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("amrapaliuserPaymentBeanList",list);
	mav.addObject("yearMap",yearMap);
	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}
/*@RequestMapping(value="AmrapaliUsersPaymentInsert.html",method=RequestMethod.POST)
public ModelAndView amrapaliCastleUsersPaymentInsertOnlyPost(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("amrapaliCastleUsersPaymentInsertOnlyPost");
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("ampr_user_payment_insert");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	List<AmrapaliCastleBean>list=new ArrayList<AmrapaliCastleBean>();
	for(int i=0;i<15;i++){
	AmrapaliCastleBean ampBean=new AmrapaliCastleBean();
	list.add(ampBean);
	}
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	int currentYear=Integer.parseInt(amrapaliDao.getCurrentYear());
	System.out.println("CurrentYear===="+currentYear);

	String threshHoldDate="";
	String todaysDate="";
	Date threshHold=null;
	Date todaysDateDate=null;
	String currentMonthNo=amrapaliDao.getCurrentMonth();
	System.out.println("CurrentMont==="+currentMonthNo);
	int monthNo=Integer.parseInt(currentMonthNo);
	try{ threshHoldDate="15/"+(monthNo+1)+""+"/"+currentYear+"";
	DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	threshHold=dateFormat.parse(threshHoldDate);
	Date date = new Date();
	
 todaysDate=dateFormat.format(date);
	 todaysDateDate=dateFormat.parse(todaysDate);
	}catch(Exception ex){
		ex.printStackTrace();
	}
	
		
	amrapaliBean.setYear(amrapaliBean.getYear());
	List<AmrapaliCastleBean>list=amrapaliDao.getUserDetails(amrapaliBean);
	
	System.out.println("LIST SIZE==="+list.size());
	if(list.size()==0){
		String yearTemp=amrapaliBean.getYear();
		amrapaliBean.setFlag("INSERT");
		System.out.println("In if list size=="+list.size());
		System.out.println("Current year==="+currentYear);
		amrapaliBean.setYear(currentYear+"");
		list=amrapaliDao.getUserDetails(amrapaliBean);
		System.out.println("In if new list size=="+list.size());
		amrapaliBean.setYear(yearTemp);
		for(AmrapaliCastleBean aBean:list){
			aBean.setMonth0("0");
			aBean.setMonth1("0");
			aBean.setMonth2("0");
			aBean.setMonth3("0");
			aBean.setMonth4("0");
			aBean.setMonth5("0");
			aBean.setMonth6("0");
			aBean.setMonth7("0");
			aBean.setMonth8("0");
			aBean.setMonth9("0");
			aBean.setMonth10("0");
			aBean.setMonth11("0");
			int dueAmount=Integer.parseInt(aBean.getSqrFt())*12;
			aBean.setAmountToBePaid(dueAmount+"");
		}
	}else{
		amrapaliBean.setFlag("UPDATE");
	}
	Map<String,Object>yearMap=new TreeMap<String,Object>();
	for(int i=currentYear-1;i<=currentYear+1;i++){
		yearMap.put(i+"", i);
	}
	
	
	amrapaliBean.setCurrentMonthNo(currentMonthNo);
	System.out.println("LIST SIZE CONTROLLER===="+list.size());
	System.out.println("Block====="+amrapaliBean.getBlock());
	System.out.println("FLAG==="+amrapaliBean.getFlag());
	int mountCount=0;
	for(AmrapaliCastleBean aBean:list){
		System.out.println("SQRFT==="+aBean.getSqrFt());
		System.out.println("USERNAME=="+aBean.getUserName());
		System.out.println("MONTH0==="+aBean.getMonth0());
		aBean.setFinancialYear(amrapaliBean.getYear());
		if( (aBean.getAmountToBePaid()==null || (!aBean.getAmountToBePaid().equals("No dues")&&Integer.parseInt(aBean.getAmountToBePaid())>0)) && Integer.parseInt(amrapaliBean.getYear())<=currentYear 
		 ){
			System.out.println("IN IF DUES");
			aBean.setRowColor("#FDB3FD");
		}else if(aBean.getAmountToBePaid().equals("0") && Integer.parseInt(amrapaliBean.getYear())<=currentYear){
			System.out.println("IN NO DUES");
			
			System.out.println("TODAYSDATE===="+todaysDateDate);
			System.out.println("THRESHHOLD===="+threshHold);
			if(todaysDateDate.compareTo(threshHold)>0){
				
				System.out.println("DATE GREATER");
				if(currentMonthNo.equals("0") && aBean.getMonth0().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("1")&& aBean.getMonth1().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("2") && aBean.getMonth2().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setLateFine("100");
					aBean.setAmountToBePaid("");
				}else if(currentMonthNo.equals("3") && aBean.getMonth3().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("4") && aBean.getMonth4().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("5") && aBean.getMonth5().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("6") && aBean.getMonth6().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("7") && aBean.getMonth7().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("8") && aBean.getMonth8().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("9") && aBean.getMonth9().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("10") && aBean.getMonth10().equals("0")){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else if(currentMonthNo.equals("11")&& aBean.getMonth11().equals("0") ){
					aBean.setRowColor("#FDB3FD");
					aBean.setAmountToBePaid("");
					aBean.setLateFine("100");
				}else{
					aBean.setAmountToBePaid("No dues");
					aBean.setRowColor("#80FF00");
				}
				
			}
			
			
		}
		else if(Integer.parseInt(amrapaliBean.getYear())!=currentYear && aBean.getMonth0().equals("0")&& aBean.getMonth1().equals("0")&& aBean.getMonth2().equals("0")&& aBean.getMonth3().equals("0")
				
				&& aBean.getMonth4().equals("0")&& aBean.getMonth5().equals("0")&& aBean.getMonth6().equals("0")&& aBean.getMonth7().equals("0")&& aBean.getMonth8().equals("0")
				&& aBean.getMonth9().equals("0")&& aBean.getMonth10().equals("0")&& aBean.getMonth11().equals("0")){
			aBean.setRowColor("#FDB3FD");
		}else{
			aBean.setAmountToBePaid("No dues");
			aBean.setRowColor("#80FF00");
		}
		
		
		aBean.setJan("0");
		aBean.setFeb("0");
		aBean.setMar("0");
		aBean.setApr("0");
		aBean.setMay("0");
		aBean.setJun("0");
		aBean.setJul("0");
		aBean.setAug("0");
		aBean.setSep("0");
		aBean.setOct("0");
		aBean.setNov("0");
		aBean.setDec("0");
		
	}for(AmrapaliCastleBean aBean:list){
		if(todaysDateDate.compareTo(threshHold)>0){
			
			System.out.println("DATE GREATER");
			if(currentMonthNo.equals("0") && aBean.getMonth0().equals("0")){
				aBean.setRowColor("#FDB3FD");
				aBean.setAmountToBePaid("");
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("1")&& aBean.getMonth1().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("2") && aBean.getMonth2().equals("0")){
				aBean.setRowColor("#FDB3FD");
				aBean.setLateFine("100");
				
			}else if(currentMonthNo.equals("3") && aBean.getMonth3().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("4") && aBean.getMonth4().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("5") && aBean.getMonth5().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("6") && aBean.getMonth6().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("7") && aBean.getMonth7().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("8") && aBean.getMonth8().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("9") && aBean.getMonth9().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("10") && aBean.getMonth10().equals("0")){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}else if(currentMonthNo.equals("11")&& aBean.getMonth11().equals("0") ){
				aBean.setRowColor("#FDB3FD");
				
				aBean.setLateFine("100");
			}
		}
	}
	
	amrapaliBean.setBlock(amrapaliBean.getBlock());
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("amrapaliuserPaymentBeanList",list);
	mav.addObject("yearMap",yearMap);
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);
	return mav;
}*/

@RequestMapping(value="AmrapaliUsersPaymentInsert.html",method=RequestMethod.POST)
public ModelAndView amrapaliCastleUsersPaymentInsertOnlyPost(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	String currentMonthNo=amrapaliDao.getCurrentMonth();
	System.out.println("CurrentMont==="+currentMonthNo);
	ModelAndView mav=new ModelAndView("ampr_user_payment_insert");
	List<AmrapaliCastleBean>list=amrapaliDao.getUserDetails(amrapaliBean);
	System.out.println("List size in controller=="+list.size());
	String curDate=amrapaliDao.getCurrentDate();
	System.out.println("Current date===0"+curDate);
	int totalDue=0;
	int currentMonth=Integer.parseInt(currentMonthNo);
	int month=0;
	if(list.size()==0){
		amrapaliBean.setFlag("INSERT");
	}else
	{
		amrapaliBean.setFlag("UPDATE");
	}
	
	for(AmrapaliCastleBean aBean:list){
		System.out.println("Name==="+aBean.getUserName());
		
		if(aBean.getMonth0().equals("0")){
			month=0;
			
			totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
			System.out.println("In month0 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("31/01/"+aBean.getYear())){
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}if(aBean.getMonth1().equals("0")){
			month=1;
			System.out.println("Previous due==="+aBean.getAmountToBePaid());
			/*totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);*/
			System.out.println("In month1 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("28/02/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}if(aBean.getMonth2().equals("0")){
			month=2;
			
			//totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
			System.out.println("In month2 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("31/03/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}if(aBean.getMonth3().equals("0")){
			month=3;
			//totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
			System.out.println("In month3 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("30/04/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}if(aBean.getMonth4().equals("0")){
			month=4;
			//totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
			System.out.println("In month4 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("31/05/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}if(aBean.getMonth5().equals("0")){
			month=5;
			totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
			System.out.println("In month5 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("30/06/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}if(aBean.getMonth6().equals("0")){
			month=6;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
			System.out.println("In month6 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("31/07/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					if(month<6)
					{
						totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
					}else{
						totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
					}
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}else if(!aBean.getMonth6().equals("0") && Integer.parseInt(aBean.getMonth6())% Integer.parseInt(aBean.getSqrFt())==0)
		{
			month=6;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*0.25);
			System.out.println("In month6 totalDue=="+totalDue);
			if(month==currentMonth-1 ){
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}
			if(aBean.getMonth7().equals("0")){
			month=7;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
			System.out.println("In month7 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("31/08/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					if(month<7)
					{
						totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
					}else{
						totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
					}
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}else if(!aBean.getMonth7().equals("0") && Integer.parseInt(aBean.getMonth7())% Integer.parseInt(aBean.getSqrFt())==0)
		{
			month=7;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*0.25);
			System.out.println("In month7 totalDue=="+totalDue);
			if(month==currentMonth-1 ){
				
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}if(aBean.getMonth8().equals("0")){
			month=8;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
			System.out.println("In month8 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("30/09/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					if(month<8)
					{
						totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
					}else{
						totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
					}
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}else if(!aBean.getMonth8().equals("0") && Integer.parseInt(aBean.getMonth8())% Integer.parseInt(aBean.getSqrFt())==0)
		{
			month=8;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*0.25);
			System.out.println("In month8 totalDue=="+totalDue);
			if(month==currentMonth-1){
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}if(aBean.getMonth9().equals("0")){
			month=9;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
			System.out.println("In month9 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("31/10/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					if(month<9)
					{
						totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
					}else{
						totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
					}
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}else if(!aBean.getMonth9().equals("0") && Integer.parseInt(aBean.getMonth9())% Integer.parseInt(aBean.getSqrFt())==0)
		{
			month=9;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*0.25);
			System.out.println("In month9 totalDue=="+totalDue);
			if(month==currentMonth-1 ){
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}if(aBean.getMonth10().equals("0")){
			month=10;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
			System.out.println("In month10 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("30/11/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					if(month<10)
					{
						totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
					}else{
						totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
					}
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}else if(!aBean.getMonth10().equals("0") && Integer.parseInt(aBean.getMonth10())% Integer.parseInt(aBean.getSqrFt())==0)
		{
			month=10;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*0.25);
			System.out.println("In month10 totalDue=="+totalDue);
			if(month==currentMonth-1){
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}if(aBean.getMonth11().equals("0")){
			month=11;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
			System.out.println("In month11 totalDue=="+totalDue);
			if(month==currentMonth-1 && curDate.equals("31/12/"+aBean.getYear())){
				for(int i=0;i<month;i++){
					if(month<11)
					{
						totalDue=totalDue+(Integer.parseInt(aBean.getSqrFt())*1);
					}else{
						totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*1.25);
					}
				}
				aBean.setAmountToBePaid(totalDue+"");
			}
		}else if(!aBean.getMonth11().equals("0") && Integer.parseInt(aBean.getMonth11())% Integer.parseInt(aBean.getSqrFt())==0)
		{
			month=11;
			totalDue=totalDue+(int)Math.ceil(Double.parseDouble(aBean.getSqrFt())*0.25);
			System.out.println("In month7 totalDue=="+totalDue);
			if(month==currentMonth-1){
				aBean.setAmountToBePaid(totalDue+"");
			}
			
		}
				
		totalDue=0;
	}
	int currentYear=Integer.parseInt(amrapaliDao.getCurrentYear());
	Map<String,Object>yearMap=new TreeMap<String,Object>();
	for(int i=currentYear-1;i<=currentYear+1;i++){
		yearMap.put(i+"", i);
	}
	amrapaliBean.setYear(amrapaliBean.getYear());
	mav.addObject("yearMap",yearMap);
	mav.addObject("amrapaliuserPaymentBeanList",list);
	mav.addObject("amrapaliBean",amrapaliBean);
	return mav;
}

@RequestMapping(value="AmrapaliUsersPaymentInsert.html",method=RequestMethod.POST,params="save")
public ModelAndView amrapaliCastleUsersPaymentInsertSave(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("amrapaliCastleUsersPaymentInsertOnlyPostSAVE");
	System.out.println("XML DATA CONTROLLER====="+amrapaliBean.getXmlData());
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("ampr_user_payment_insert");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	/*List<AmrapaliCastleBean>list=new ArrayList<AmrapaliCastleBean>();
	for(int i=0;i<15;i++){
	AmrapaliCastleBean ampBean=new AmrapaliCastleBean();
	list.add(ampBean);
	
	}*/
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	int currentYear=Integer.parseInt(amrapaliDao.getCurrentYear());
	System.out.println("CurrentYear===="+currentYear);
	amrapaliBean.setYear(amrapaliBean.getYear());
	
	Map<String,Object>yearMap=new TreeMap<String,Object>();
	for(int i=currentYear-1;i<=currentYear+1;i++){
		yearMap.put(i+"", i);
	}
	
	
	amrapaliDao.savePayment(amrapaliBean);
	System.out.println("Returned after saving");
	/*System.out.println("LIST SIZE CONTROLLER===="+list.size());
	System.out.println("Block====="+amrapaliBean.getBlock());
	for(AmrapaliCastleBean aBean:list){
		System.out.println("SQRFT==="+aBean.getSqrFt());
		System.out.println("USERNAME=="+aBean.getUserName());
		
	}*/
	mav.addObject("amrapaliBean",amrapaliBean);
	System.out.println("Setting Year Map");
	System.out.println("year map===="+yearMap);
	mav.addObject("yearMap",yearMap);
	//mav.addObject("amrapaliuserPaymentBeanList",list);

	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}
@RequestMapping(value="AmrapaliUsersPaymentInsert.html",method=RequestMethod.POST,params="totalDuesButton")
public ModelAndView amrapaliCastleTotalDues(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("In totalDuesFunction");
	ModelAndView mav=new ModelAndView();
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	List<AmrapaliCastleBean>list=amrapaliDao.totalDuesBlockWise(amrapaliBean);
	for(AmrapaliCastleBean aBean:list){
		System.out.println("Year="+aBean.getYear()+" Block="+aBean.getBlock()+" TotalDue="+aBean.getSumYearBlockWise());
	}
	return null;
}


@RequestMapping(value="/totalDuesBlockWise.html",method=RequestMethod.GET)
public ModelAndView totalDuesBlockWise(HttpServletRequest request,HttpServletResponse response){
	System.out.println("totalDuesBlockWise");
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("total-dues-blockwise");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	int currentYear=Integer.parseInt(amrapaliDao.getCurrentYear());
	System.out.println("CurrentYear===="+currentYear);
	Map<String,Object>yearMap=new TreeMap<String,Object>();
	for(int i=currentYear-1;i<=currentYear+1;i++){
		yearMap.put(i+"", i);
	}
	
	amrapaliBean.setCurrentYear(currentYear+"");
	mav.addObject("amrapaliBean",amrapaliBean);
	
	mav.addObject("yearMap",yearMap);
	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}

@RequestMapping(value="totalDuesBlockWise.html",method=RequestMethod.POST,params="show")
public ModelAndView totalDuesBlockWise(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("In totalDuesFunction show");
	ModelAndView mav=new ModelAndView("total-dues-blockwise");
	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	List<AmrapaliCastleBean>list=amrapaliDao.totalDuesYearWise(amrapaliBean);
	System.out.println("list size=="+list.size());
	int currentYear=Integer.parseInt(amrapaliDao.getCurrentYear());
	System.out.println("CurrentYear===="+currentYear);
	Map<String,Object>yearMap=new TreeMap<String,Object>();
	for(int i=currentYear-1;i<=currentYear+1;i++){
		yearMap.put(i+"", i);
	}
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("list",list);
	mav.addObject("yearMap",yearMap);
	return mav;
}


@RequestMapping(value="/otherDuesUserWiseInsert.html",method=RequestMethod.POST)
public ModelAndView otherDuesUserWiseInsertPost(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("In Search Controller1");
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("other-dues-user-wise");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	

	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	String year=amrapaliDao.getCurrentYear();
	amrapaliBean.setYear(year);
	List<AmrapaliCastleBean>list=amrapaliDao.getUserDetails(amrapaliBean);
	
	for(AmrapaliCastleBean aBean:list){
		aBean.setOtherDues("");
	}
	
	
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("amrapaliuserPaymentBeanList",list);
	
	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}
@RequestMapping(value="/otherDuesUserWiseInsert.html",method=RequestMethod.GET)
public ModelAndView otherDuesUserWiseInsert(HttpServletRequest request,HttpServletResponse response){
	System.out.println("In Search Controller1");
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("other-dues-user-wise");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	amrapaliBean.setBlock("A");

	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	String year=amrapaliDao.getCurrentYear();
	amrapaliBean.setYear(year);
	List<AmrapaliCastleBean>list=amrapaliDao.getUserDetails(amrapaliBean);
	
	for(AmrapaliCastleBean aBean:list){
		aBean.setOtherDues("");
	}
	
	
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("amrapaliuserPaymentBeanList",list);
	
	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}


@RequestMapping(value="/otherDuesUserWiseInsert.html",method=RequestMethod.POST,params="save")
public ModelAndView otherDuesUserWiseInsertSavePost(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("In Search Controller1 save");
	//System.out.println("XML DATA====="+amrapaliBean.getXmlData());
	ModelAndView mav=new ModelAndView("other-dues-user-wise");
	//AmrapaliCastleBean amrapaliBean=new AmrapaliCastleBean();
	

	AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.saveOtherDues(amrapaliBean);
	
	mav.addObject("amrapaliBean",amrapaliBean);
	//mav.addObject("amrapaliuserPaymentBeanList",list);
	
	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}





}
