package com.cache.main;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.stat.Statistics;

import com.cache.pojo.Employee;
import com.cache.util.HibernateUtil;

public class HibernateMain {
	public static void main(String[] args) {
		System.out.println("Temp Dir:" + System.getProperty("java.io.tmpdir"));

		// Initialize Sessions
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		/*
		 * org.hibernate.stat.Statistics provides the statistics of Hibernate
		 * SessionFactory, we are using it to print the fetch count and second level
		 * cache hit, miss and put count. Statistics are disabled by default for better
		 * performance, that’s why I am enabling it at the start of the program.
		 */

		Statistics stats = sessionFactory.getStatistics();
		System.out.println("Stats enabled=" + stats.isStatisticsEnabled());
		stats.setStatisticsEnabled(true);
		System.out.println("Stats enabled=" + stats.isStatisticsEnabled());

		Session session = sessionFactory.openSession();
		Session otherSession = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		Transaction otherTransaction = otherSession.beginTransaction();

		printStats(stats, 0);

		// fetch the department entity from database first time
		Employee emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 1);

		// fetch the department entity again; Fetched from first level cache
		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 2);

		// clear first level cache, so that second level cache is used
		session.evict(emp);
		emp = (Employee) session.load(Employee.class, 1L);
		printData(emp, stats, 3);

		emp = (Employee) session.load(Employee.class, 3L);
		printData(emp, stats, 4);

		emp = (Employee) otherSession.load(Employee.class, 1L);
		printData(emp, stats, 5);

		// Release resources
		transaction.commit();
		otherTransaction.commit();
		sessionFactory.close();
	}

	private static void printStats(Statistics stats, int i) {
		System.out.println("***** " + i + " *****");
		System.out.println("Fetch Count=" + stats.getEntityFetchCount());
		System.out.println("Second Level Hit Count=" + stats.getSecondLevelCacheHitCount());
		System.out.println("Second Level Miss Count=" + stats.getSecondLevelCacheMissCount());
		System.out.println("Second Level Put Count=" + stats.getSecondLevelCachePutCount());
	}

	private static void printData(Employee emp, Statistics stats, int count) {
		System.out.println(count + ":: Name=" + emp.getName() + ", Zipcode=" + emp.getAddress().getZipcode());
		printStats(stats, count);
	}
}
