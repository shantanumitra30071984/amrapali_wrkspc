package com.amrapalicastle.controller;


import java.util.ArrayList;
import java.util.HashMap;
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
@RequestMapping(value="AmrapaliUsersPaymentInsert.html",method=RequestMethod.POST)
public ModelAndView amrapaliCastleUsersPaymentInsertOnlyPost(@ModelAttribute("amrapaliBean")AmrapaliCastleBean amrapaliBean,HttpServletRequest request,HttpServletResponse response){
	System.out.println("amrapaliCastleUsersPaymentInsertOnlyPost");
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
	
	String currentMonthNo=amrapaliDao.getCurrentMonth();
	int monthNo=Integer.parseInt(currentMonthNo);
	amrapaliBean.setCurrentMonthNo(currentMonthNo);
	System.out.println("LIST SIZE CONTROLLER===="+list.size());
	System.out.println("Block====="+amrapaliBean.getBlock());
	System.out.println("FLAG==="+amrapaliBean.getFlag());
	for(AmrapaliCastleBean aBean:list){
		System.out.println("SQRFT==="+aBean.getSqrFt());
		System.out.println("USERNAME=="+aBean.getUserName());
		System.out.println("MONTH0==="+aBean.getMonth0());
		aBean.setFinancialYear(amrapaliBean.getYear());
		if( (aBean.getAmountToBePaid()==null || (!aBean.getAmountToBePaid().equals("No dues")&&Integer.parseInt(aBean.getAmountToBePaid())>0)) && Integer.parseInt(amrapaliBean.getYear())<=currentYear 
		 ){
			aBean.setRowColor("#FDB3FD");
		}else if(Integer.parseInt(amrapaliBean.getYear())!=currentYear && aBean.getMonth0().equals("0")&& aBean.getMonth1().equals("0")&& aBean.getMonth2().equals("0")&& aBean.getMonth3().equals("0")
				
				&& aBean.getMonth4().equals("0")&& aBean.getMonth5().equals("0")&& aBean.getMonth6().equals("0")&& aBean.getMonth7().equals("0")&& aBean.getMonth8().equals("0")
				&& aBean.getMonth9().equals("0")&& aBean.getMonth10().equals("0")&& aBean.getMonth11().equals("0")){
			aBean.setRowColor("#FDB3FD");
		}else{
			aBean.setAmountToBePaid("No dues");
			aBean.setRowColor("#80FF00");
		}
		/*aBean.setJan("0");
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
		aBean.setDec("0");*/
		
	}
	amrapaliBean.setBlock(amrapaliBean.getBlock());
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("amrapaliuserPaymentBeanList",list);
	mav.addObject("yearMap",yearMap);
	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
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
	/*System.out.println("LIST SIZE CONTROLLER===="+list.size());
	System.out.println("Block====="+amrapaliBean.getBlock());
	for(AmrapaliCastleBean aBean:list){
		System.out.println("SQRFT==="+aBean.getSqrFt());
		System.out.println("USERNAME=="+aBean.getUserName());
		
	}*/
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("yearMap",yearMap);
	//mav.addObject("amrapaliuserPaymentBeanList",list);

	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}

}
