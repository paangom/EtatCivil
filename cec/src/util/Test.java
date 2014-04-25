package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import models.DeclarationNaissance;

import org.hibernate.Query;
import org.hibernate.Session;

import beans.DecANBean;
import services.ActeNaissanceServices;

public class Test {

	private static Session session = null;
	@SuppressWarnings("unused")
	private static ActeNaissanceServices s=new ActeNaissanceServices();
	public static DecANBean d=new DecANBean();
	 @SuppressWarnings("unchecked")
	public static boolean numeroActe(String num, String annee) {
		// TODO Auto-generated method stub
		 boolean flag=false;
		 List<DeclarationNaissance> list = null;
	       if (session == null)
			session = HibernateUtil.getSessionFactory();
			
			Query q = session.createQuery("from DeclarationDeces a where a.date_jugement like :dateJ and a.num_jugement = :num")
					.setString("dateJ", "%"+annee+"%").setString("num", num);
			list = q.list();
			if(list.size() > 0){
				System.out.println("Thu Jan 01 00:59:00 UTC 1970".substring(11, 16));
				flag=true;
			}
			return flag;
		}
	    
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date t=null;
		try {
			t = dateFormat.parse("Tue Apr 01 00:00:00 UTC 2014");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		System.out.println(dateFormat.format(t));
	}

}
