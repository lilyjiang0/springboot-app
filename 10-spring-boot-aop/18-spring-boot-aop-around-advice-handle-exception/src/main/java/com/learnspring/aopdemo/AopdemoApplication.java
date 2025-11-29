package com.learnspring.aopdemo;

import com.learnspring.aopdemo.dao.AccountDAO;
import com.learnspring.aopdemo.dao.MembershipDAO;
import com.learnspring.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(TrafficFortuneService trafficFortuneService,
											   AccountDAO accountDAO,
											   MembershipDAO membershipDAO) {
		return runner -> {
//			demoTheBeforeAdvice(accountDAO, membershipDAO);
//			demoAfterReturningAdvice(accountDAO);
//			demoAfterThrowingAdvice(accountDAO);
//			demoAfterAdvice(accountDAO);
//			demoAroundAdvice(trafficFortuneService);
			demoAroundAdviceHandleException(trafficFortuneService);
		};
	}

	private void demoAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demo around advice handling exception");
		System.out.println("Main Program: Calling getFortune()");
		// Simulate exception.
		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);
		System.out.println("Main Program: My fortune is " + data);
		System.out.println("Main Program: Finished");
	}

	private void demoAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("\nMain Program: demo around advice");
		System.out.println("Main Program: Calling getFortune()");
		String data = trafficFortuneService.getFortune();
		System.out.println("Main Program: My fortune is " + data);
		System.out.println("Main Program: Finished");
	}

	private void demoAfterAdvice(AccountDAO accountDAO) {
		List<Account> accounts = null;

		try {
			// Add a boolean flag to simulate exceptions.
			boolean tripWire = false;
			accounts = accountDAO.findAccounts(tripWire);
			// Print out accounts.
			System.out.println("\n Main Program: demo the after advice ");
			for (Account acc : accounts) {
				System.out.println(acc);
			}
		} catch (Exception e) {
			System.out.println("\n Main Program: caught exception " + e);
		}
	}

	private void demoAfterThrowingAdvice(AccountDAO accountDAO) {
		List<Account> accounts = null;

		try {
			// Add a boolean flag to simulate exceptions.
			boolean tripWire = true;
			accounts = accountDAO.findAccounts(tripWire);
			// Print out accounts.
			System.out.println("\n Main Program: demo the after throwing advice ");
			for (Account acc : accounts) {
				System.out.println(acc);
			}
		} catch (Exception e) {
			System.out.println("\n Main Program: caught exception " + e);
		}

	}

	private void demoAfterReturningAdvice(AccountDAO accountDAO) {
		List<Account> accounts = accountDAO.findAccounts();
		// Print out accounts.
		System.out.println("\n Main Program: demo the after returning advice ");
		for (Account acc : accounts) {
			System.out.println(acc);
		}
	}

	private void demoTheBeforeAdvice(AccountDAO accountDAO, MembershipDAO membershipDAO) {
		Account acc = new Account();
		acc.setName("Merlin");
		acc.setLevel("Gold");
		accountDAO.addAccount(acc, true);
		accountDAO.doWork();
		String name = accountDAO.getName();
		accountDAO.setName("n");
		String account = accountDAO.getAccount();
		accountDAO.setAccount("a");
		membershipDAO.addMemberAccount();
		membershipDAO.getMembershipPoint();
	}
}
