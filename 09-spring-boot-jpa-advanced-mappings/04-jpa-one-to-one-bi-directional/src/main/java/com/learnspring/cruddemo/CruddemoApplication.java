package com.learnspring.cruddemo;

import com.learnspring.cruddemo.dao.AppDAO;
import com.learnspring.cruddemo.entity.Instructor;
import com.learnspring.cruddemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createInstructor(appDAO);
//			findInstructorById(appDAO);
//			deleteInstructorById(appDAO);
//			findInstructorDetailById(appDAO);
			deleteInstructorDetailById(appDAO);
		};
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int id = 2;
		System.out.println("Deleting instructor detail id: " + id);
		appDAO.deleteInstructorDetailById(id);
		System.out.println("Instructor detail deleted!");
	}

	private void findInstructorDetailById(AppDAO appDAO) {
		int id = 2;
		// Get instructor detail object.
		InstructorDetail tempInstructorDetail = appDAO.findInstructorDetailById(id);
		// Print detail.
		System.out.println("Instructor detail: " + tempInstructorDetail);
		// Print associate instructor.
		System.out.println("Instructor: " + tempInstructorDetail.getInstructor());
	}

	private void deleteInstructorById(AppDAO appDAO) {
		int id = 1;
		System.out.println("Deleting instructor with id: " + id);
		appDAO.deleteInstructorById(id);
		System.out.println("Instructor deleted.");
	}

	private void findInstructorById(AppDAO appDAO) {
		int id = 2;
		System.out.println("Finding instructor with id: " + id);
		Instructor tempInstructor = appDAO.findInstructorById(id);
		System.out.println("Instructor Info: " + tempInstructor);
		System.out.println("Instructor detail: " + tempInstructor.getInstructorDetail());
	}

	private void createInstructor(AppDAO appDAO) {
		// Create instructor.
		Instructor tempInstructor = new Instructor("Cindy", "Wong", "cindy.wong@hello.com");
		// Create the instructor detail.
		InstructorDetail tempInstructorDetail = new InstructorDetail("http://www.cindy.com/youtube", "coding");

		// Associate the objects.
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Save the instructor.
		// This will also save the details object because of CascadeType.ALL
		System.out.println("Saving instructor: " + tempInstructor);
		appDAO.save(tempInstructor);

		System.out.println("Instructor saved.");
	}

}
