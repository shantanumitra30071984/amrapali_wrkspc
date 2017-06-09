package com.amrapalicastle.controller;


import java.util.ArrayList;
import java.util.List;

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
	String currentMonthNo=amrapaliDao.getCurrentMonth();
	amrapaliBean.setCurrentMonthNo(currentMonthNo);
	mav.addObject("amrapaliBean",amrapaliBean);
	mav.addObject("amrapaliuserPaymentBeanList",list);

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
	List<AmrapaliCastleBean>list=amrapaliDao.getUserDetails(amrapaliBean);
	String currentMonthNo=amrapaliDao.getCurrentMonth();
	int monthNo=Integer.parseInt(currentMonthNo);
	amrapaliBean.setCurrentMonthNo(currentMonthNo);
	System.out.println("LIST SIZE CONTROLLER===="+list.size());
	System.out.println("Block====="+amrapaliBean.getBlock());
	
	for(AmrapaliCastleBean aBean:list){
		System.out.println("SQRFT==="+aBean.getSqrFt());
		System.out.println("USERNAME=="+aBean.getUserName());
		System.out.println("MONTH0==="+aBean.getMonth0());
		aBean.setFinancialYear("2017");
		if(Integer.parseInt(aBean.getAmountToBePaid())>0){
			aBean.setRowColor("#FDB3FD");
		}else{
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
	amrapaliDao.savePayment(amrapaliBean);
	/*System.out.println("LIST SIZE CONTROLLER===="+list.size());
	System.out.println("Block====="+amrapaliBean.getBlock());
	for(AmrapaliCastleBean aBean:list){
		System.out.println("SQRFT==="+aBean.getSqrFt());
		System.out.println("USERNAME=="+aBean.getUserName());
		
	}*/
	mav.addObject("amrapaliBean",amrapaliBean);
	//mav.addObject("amrapaliuserPaymentBeanList",list);

	/*AmrapaliCastleDao amrapaliDao=new AmrapaliCastleDao();
	amrapaliDao.parXmlData(amrapaliBean);*/
	return mav;
}

}
