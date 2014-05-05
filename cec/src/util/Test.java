package util;

import java.util.Calendar;

import org.hibernate.Query;
import org.hibernate.Session;

public class Test { 
	static Session session = null;
	
	public static int findInstanceByDate(String date) {
		// TODO Auto-generated method stub
		int x=0;
		System.out.println("jglcvkjmhbgl");
		if (session == null)
			session = HibernateUtil.getSessionFactory();
		
		Query q = session.createQuery("from DeclarationDeces a where a.date_creation like  :annee and etat = :etat")
				.setString("annee", "%"+"2014"+"%").setString("etat", "Instance");
		x = q.list().size();
		System.out.println(q.list());
        return x;
	}
	
public static void main(String[] args) {
	Calendar cal = Calendar.getInstance();
	 System.out.println(findInstanceByDate(Tools.getCurrentDateDDMMYYYY().substring(3))+",v:n,vb;b");
}
}