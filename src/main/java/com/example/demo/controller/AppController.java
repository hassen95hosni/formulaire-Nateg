package com.example.demo.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.connectors.ConnectionDb;
import com.example.demo.interfac.EmailServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import com.rethinkdb.net.Cursor;

import entity.Responds;
import entity.User;

@Controller
public class AppController  {
	User admin = new User("", "admin", "admin", "admin", "admin@gmail.com", "admin");
	EmailServiceImpl emailServiceImpl = new EmailServiceImpl();
	
	
	 @RequestMapping(value = "/allforms", produces = "text/csv")
	    public String getall(HttpSession session ,HttpServletResponse response) throws IOException {
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			return "404";
			} else {
			List<Responds> list = getall();
	        response.setHeader("Content-Disposition", "attachment; file=customers.csv");
	        WriteDataToCSV.writeDataToCsvUsingStringArray(response.getWriter(), list);
	    
		}
return "main";	        }

	 @RequestMapping(value = "/days7", produces = "text/csv")
	    public String leftseven(HttpSession session ,HttpServletResponse response) throws IOException, ParseException {
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			 
			 return "404";
		 }
		 
		 else {
			    List<Responds> list = getall();
		        list = getlisOfEmailsdaysleft(list, 7);
		        response.setHeader("Content-Disposition", "attachment; file=customerswith7daysleft.csv");
		        WriteDataToCSV.writeDataToCsvUsingStringArray(response.getWriter(), list);
		    	 
			 return "warning";
		 }
	    }
	 
	 @RequestMapping(value = "/warning")
	    public String warning(HttpSession session ,Model model) throws IOException, ParseException {
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			    
			    return "404";
			   }
			   
			   else {
				   List<Responds> list = getall();
				     //System.out.println("firstlist"+list.toString());
				        list = getlisOfEmailsdaysleft(list, 7);
				       // System.out.println("second list"+list.toString());
				        model.addAttribute("list", list);
				        return "warning";
				           }
		 
	        		}

	 @RequestMapping(value = "/didntpay")
	    public String didntpay(HttpSession session ,Model model) throws IOException, ParseException {

		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			    
			    return "404";
			   }
			   
			   else {
				   
			        List<Responds> list = getall();
			        list = getlisOfEmailsdaysleft(list, 14);
			        if (list.size()==0) {
			        	model.addAttribute("true",false);
			        }
			        else {
			        	model.addAttribute("true",true);
			        	model.addAttribute("list", list);	
			        }
			        
			        return "final";
			    	   }
		    		}
	 
	 
	 @RequestMapping(value = "/dayspass", produces = "text/csv")
	    public String dayspass(HttpSession session ,HttpServletResponse response) throws IOException, ParseException {
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			    
			    return "404";
			   }
			   
			   else {

			        List<Responds> list = getall();
			        list = getlisOfEmailsdaysleft(list, 14);
			        response.setHeader("Content-Disposition", "attachment; file=customerspasseddelay.csv");
			        WriteDataToCSV.writeDataToCsvUsingStringArray(response.getWriter(), list);
			    	    
			    return "final";
			   }
		 }

	
	
	
	public List<Responds> getlisOfEmailsdaysleft(List<Responds> listall,int plus) throws ParseException{
		List<Responds>list = new ArrayList<>();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");  
		LocalDateTime now = LocalDateTime.now(); 
		
		now =now.plusDays(plus);
		String s =dtf.format(now);
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date =  sdf.parse(s);
		
		for(int i=0;i<listall.size();i++) {
			if (listall.get(i).getPayed().equals("false")) {
				try {
					if(plus>=14) {
						if(listall.get(i).getFinalstate().equals("false")) {
							
							
							Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(listall.get(i).getDate());
							if (date.compareTo(date1)>0) {
								list.add(listall.get(i));
							}}
							
					}
					else {
						if(listall.get(i).getState().equals("false")) {
							
							
							Date date1=new SimpleDateFormat("yyyy/MM/dd").parse(listall.get(i).getDate());
							if (date.compareTo(date1)>0) {
								list.add(listall.get(i));
							}}
								
					}
					
					
					
					
				} catch (ParseException e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
				
			} 			}
		
		return list ;
	}
	
	public Boolean sendmails(List<Responds> list,String subject,String text) {
		boolean result=false ;
	    for (int i = 0 ; i<list.size();i++) {
	    	result =emailServiceImpl.sendSimpleMessage(list.get(i).getEmail(), subject, text);
	    	System.out.println(result);
	    }
		
		
		return result;
	}
	
	public List<Responds> getall(){
		ConnectionDb cox = new ConnectionDb();
		   Connection conn = cox.getConnection();
		 RethinkDB r = cox.getR();
		List<Responds> t = new ArrayList<>();
		Cursor<Map<String, String>> cursor=r.db("nateg").table("inscrit").run(conn);
		String a ="";
		for (Map<String, String> respond :cursor) {
			System.out.println(respond);
			//Responds res =  Responds.class.cast(respond);
			Responds res =  new Responds();
			res.setId(respond.get("id"));
			res.setComment(respond.get("comment"));
			res.setDate(respond.get("date"));
			res.setEmail(respond.get("email"));
			res.setFamilyName(respond.get("familyname"));
			res.setName(respond.get("name"));
			res.setVolenteeringeExperience(respond.get("experience"));
			res.setWhyDoyouWwannajoinNateg(respond.get("why"));
			res.setState(respond.get("state"));
			res.setFinalstate(respond.get("finalstate"));
			res.setPayed(respond.get("payed"));
			res.setId(respond.get("id"));
			
			t.add(res);
			a=respond.toString();
	
		}
			return t;			
		
	}
	public void setstate (List<Responds> list) {
		ConnectionDb cox = new ConnectionDb();
		   Connection conn = cox.getConnection();
		 RethinkDB r = cox.getR();
		 for (int i=0;i<list.size();i++) {
			 r.db("nateg").table("inscrit").get(list.get(i).getId()).update(r.hashMap("state", "true")).run(conn);
		 }
	}
	
public void setfinalstate (List<Responds> list) {
	ConnectionDb cox = new ConnectionDb();
	   Connection conn = cox.getConnection();
	 RethinkDB r = cox.getR();
	 for (int i=0;i<list.size();i++) {
		 r.db("nateg").table("inscrit").get(list.get(i).getId()).update(r.hashMap("finalstate", "true")).run(conn);
	 }	
}
	
	@Scheduled(cron = "0 1 1 * * ?")
	public void senddelayed() throws ParseException {
		List<Responds> list = getall();
		list = getlisOfEmailsdaysleft(list, 7);
		sendmails(list, "nateg membership","you still have one week left");
		setstate(list);
		list = getlisOfEmailsdaysleft(list, 14);
		sendmails(list, "nateg membership", "you cannot pay anymore");
		setfinalstate(list);
	}
	
	@RequestMapping("/login")
	public String log() {
		
		return "login";
	}
	
	@RequestMapping("/main")
	public String dash(HttpSession session ,Model model , String username , String password) {
		 if ((session.getAttribute("user")==null ) || (session.getAttribute("user").equals(""))) {
			    
			    return "404";
			   }
			   
			   else {

					ConnectionDb cox = new ConnectionDb();
					   Connection conn = cox.getConnection();
					 RethinkDB r = cox.getR();
					List<Responds> t = new ArrayList<>();
					Cursor<Map<String, String>> cursor=r.db("nateg").table("inscrit").run(conn);
					String a ="";
					for (Map<String, String> respond :cursor) {
						System.out.println(respond);
						//Responds res =  Responds.class.cast(respond);
						Responds res =  new Responds();
						res.setId(respond.get("id"));
						res.setPayed(respond.get("payed"));
						res.setComment(respond.get("comment"));
						res.setDate(respond.get("date"));
						res.setEmail(respond.get("email"));
						res.setFamilyName(respond.get("familyname"));
						res.setName(respond.get("name"));
						res.setVolenteeringeExperience(respond.get("experience"));
						res.setWhyDoyouWwannajoinNateg(respond.get("why"));
						res.setState(respond.get("state"));
						res.setFinalstate(respond.get("finalstate"));
						
						t.add(res);
						a=respond.toString();
				
					}
						model.addAttribute("list", t);			
					return "main";
					   }
		
		}
	
	@RequestMapping("/paying")
	public String paying(HttpSession session ,Model model , String id ) {
		
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			    
			    return "404";
			   }
			   
			   else {

					ConnectionDb cox = new ConnectionDb();
					   Connection conn = cox.getConnection();
					 RethinkDB r = cox.getR();
					try {
						r.db("nateg").table("inscrit").get(id).update(r.hashMap("payed", "true")).run(conn);
							
					} catch (Exception e) {
						// TODO: handle exception
					}
					Cursor<Map<String, String>> cursor=r.db("nateg").table("inscrit").run(conn);
					
					List<Responds> t = new ArrayList<>();
					for (Map<String, String> respond :cursor) {
						System.out.println(respond);
						//Responds res =  Responds.class.cast(respond);
						Responds res =  new Responds();
						res.setComment(respond.get("comment"));
						res.setDate(respond.get("date"));
						res.setEmail(respond.get("email"));
						res.setFamilyName(respond.get("familyname"));
						res.setName(respond.get("name"));
						res.setVolenteeringeExperience(respond.get("experience"));
						res.setWhyDoyouWwannajoinNateg(respond.get("why"));
						res.setState(respond.get("state"));
						res.setFinalstate(respond.get("finalstate"));
						res.setPayed(respond.get("payed"));
						res.setId(respond.get("id"));
						t.add(res);
					
					}
						model.addAttribute("list", t);
				
					
					return "main";
					   }
		}
	
	@RequestMapping("/loginin")
	public String loginin(HttpSession session ,Model model,String username , String password) {
		ConnectionDb cox = new ConnectionDb();
		   Connection conn = cox.getConnection();
		 RethinkDB r = cox.getR();
		List<Responds> t = new ArrayList<>();
		if (username.equals(admin.getUsername()) && password.equals(admin.getPassword()))						
		{ 	
			session.setAttribute("user", admin);
			model.addAttribute("admin",admin);
		Cursor<Map<String, String>> cursor=r.db("nateg").table("inscrit").run(conn);
		String a ="";
		for (Map<String, String> respond :cursor) {
			System.out.println(respond);
			//Responds res =  Responds.class.cast(respond);
			Responds res =  new Responds();
			res.setComment(respond.get("comment"));
			res.setDate(respond.get("date"));
			res.setEmail(respond.get("email"));
			res.setFamilyName(respond.get("familyname"));
			res.setName(respond.get("name"));
			res.setVolenteeringeExperience(respond.get("experience"));
			res.setWhyDoyouWwannajoinNateg(respond.get("why"));
			res.setState(respond.get("state"));
			res.setFinalstate(respond.get("finalstate"));
			res.setPayed(respond.get("payed"));
			res.setId(respond.get("id"));
			
			t.add(res);
			a=respond.toString();
	
		}
			model.addAttribute("list", t);
			return "main";
		}else {
			model.addAttribute("error", "you are not authorized");
			return "login";
		}
			
	}
	@RequestMapping("/ajouter")
	public String ajouter(HttpSession session ,String date , String depart , String destination , String heure , String nombredeplace , String nombredeplacedisponible ) {
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			    
			    return "404";
			   }
			   
			   else {

					return "ajouter";
					   }	
		
		}

	@RequestMapping("/ajouterpage")
	public String ajouterpage(HttpSession session ) {
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			    
			    return "404";
			   }
			   
			   else {

					return "ajouter";
					   }
		}

	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		 if ((session.getAttribute("user")==null ) || session.getAttribute("user").equals("")) {
			    
			    return "404";
			   }
			   
			   else {

					session.setAttribute("user","");
					return "login";}
	}
	@RequestMapping("/")
	public String register() {
		
		return "register";
	}
	@RequestMapping("/addform")
	public String adduser( String comment,String why , String name , String familyname, String experience , String email ) {
		System.out.println(why+name+familyname+experience+email);
		Responds respond = new Responds();
		respond.setComment(comment);
		respond.setEmail(email);
		respond.setFamilyName(familyname);
		respond.setName(name);
		respond.setVolenteeringeExperience(experience);
		respond.setWhyDoyouWwannajoinNateg(why);
		respond.setFinalstate("false");
		respond.setState("false");
		respond.setPayed("false");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd ");  
		LocalDateTime now = LocalDateTime.now(); 
		
		respond.setDate(dtf.format(now));
		try {
					ConnectionDb c = new ConnectionDb();
					  Connection conn = c.getConnection();
					  Date date = new Date();
					RethinkDB r = c.getR();
					System.out.println("done");
					r.db("nateg").table("inscrit")
					.insert(r.hashMap("name", respond.getName())
					.with("familyname",respond.getFamilyName())
					.with("email",respond.getEmail())
					.with("experience",respond.getVolenteeringeExperience())
					.with("why",respond.getWhyDoyouWwannajoinNateg())
					.with("comment", respond.getComment())
					.with("date", respond.getDate())
					.with("state", respond.getState())
					.with("finalstate", respond.getFinalstate())
					.with("payed", respond.getPayed()))
					.run(conn);
					System.out.println("yeap");
	
				} catch (Exception e) {
System.out.println("error"+e.getMessage());					// TODO: handle exception
				} 
					List<Responds> list =new ArrayList<>();
					list.add(respond);
		sendmails(list, "nateg memebership", "your request has been saved plaese pay in the next 2 weeks");
		return "thankyou";
	}
	
	

}
