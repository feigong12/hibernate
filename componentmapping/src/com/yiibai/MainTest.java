package com.yiibai;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class MainTest {
	public static void main(String[] args) {
		// �����3.x.x�汾hibernate��������4.x.x�������·�ʽ��ȡ���ǵĻỰ������
		// 1. ����������hibernate.cfg.xml�е�����
		// Configuration configuration = new Configuration().configure();
		// 2. ��������ע����,��һ��ע���ʼ�����������ļ��е�����
		// ServiceRegistry serviceRegistry = new
		// ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
		// 3. �������ǵ����ݿ���ʻỰ����
		// SessionFactory sessionFactory =
		// configuration.buildSessionFactory(serviceRegistry);

		// ����5.1.0�汾���ܣ�hibernate����������·�ʽ��ȡ��
		// 1. �������Ͱ�ȫ��׼����ע���࣬���ǵ�ǰӦ�õĵ������󣬲����޸ģ���������Ϊfinal
		// ��configure("cfg/hibernate.cfg.xml")�����У������ָ����Դ·����Ĭ������·����Ѱ����Ϊhibernate.cfg.xml���ļ�
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure("hibernate.cfg.xml").build();
		// 2. ���ݷ���ע���ഴ��һ��Ԫ������Դ����ͬʱ����Ԫ���ݲ�����Ӧ��һ��Ψһ�ĵ�session����
		SessionFactory sessionFactory = new MetadataSources(registry)
				.buildMetadata().buildSessionFactory();

		/**** ����������׼�������濪ʼ���ǵ����ݿ���� ******/
		Session s = sessionFactory.openSession();// �ӻỰ������ȡһ��session

		// creating transaction object
		Transaction t = s.beginTransaction();

		Employee e1 = new Employee("Mina Sun", new Address("Haikou", "China", 221233));
		Employee e2 = new Employee("Max Su", new Address("Haikou", "india",
				224123));

		s.save(e1);
		s.save(e2);

		t.commit();
		s.close();

		System.out.println("success...");
	}
}