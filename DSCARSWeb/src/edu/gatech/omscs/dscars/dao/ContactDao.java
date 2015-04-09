package edu.gatech.omscs.dscars.dao;


import org.hibernate.Query;
import org.hibernate.classic.Session;

import edu.gatech.omscs.dscars.model.Contact;
import edu.gatech.omscs.dscars.util.HibernateUtil;

public class ContactDao extends HibernateUtil {
	/**
	 * Add a new Contact.
	 * @param Contact
	 * @return
	 */
	public Contact add(Contact contact) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();
		session.save(contact);
		session.getTransaction().commit();
		return contact;
	}
	
	public Contact getContact(int gtid) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		session.beginTransaction();/*lets hope an id of 1 exists!*/
		String queryString = "from Contact where gtid = :gtid";
		Query query = session.createQuery(queryString);
		query.setInteger("gtid", gtid);
		Object queryResult = query.uniqueResult();
		Contact Contact = (Contact)queryResult;
		session.getTransaction().commit();
		return Contact;
	}
}
