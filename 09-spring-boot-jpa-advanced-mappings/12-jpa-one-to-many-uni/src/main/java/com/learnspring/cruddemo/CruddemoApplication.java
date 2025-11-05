package com.learnspring.cruddemo;

import com.learnspring.cruddemo.dao.AppDAO;
import com.learnspring.cruddemo.entity.Course;
import com.learnspring.cruddemo.entity.Instructor;
import com.learnspring.cruddemo.entity.InstructorDetail;
import com.learnspring.cruddemo.entity.Review;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
//			createCourseAndReviews(appDAO);
//			findCoursesAndReviewsById(appDAO);
			removeCourseAndReviews(appDAO);
		};
	}

	private void removeCourseAndReviews(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course id: " + id);
		// Delete both course and reviews because of casacade type = all.
		appDAO.deleteCourseById(id);
		System.out.println("Course and related reviews deleted!");
	}

	private void findCoursesAndReviewsById(AppDAO appDAO) {
		// Get the course and reviews.
		int id = 10;
		Course course = appDAO.findCourseAndReviewsByCourseId(id);

		// Print the course.
		System.out.println("Course: " + course);
		// Print the reviews.
		System.out.println("Reviews: " + course.getReviews());

	}

	private void createCourseAndReviews(AppDAO appDAO) {
		// Create a course.
		Course course = new Course("Hello world!");
		// Add some reviews.
		course.addReview(new Review("Great course, loved it!"));
		course.addReview(new Review("Cool course, job well done."));
		course.addReview(new Review("What a dumb course, you are an idiot."));

		System.out.println("Saving the course");
		System.out.println("Course: " + course);
		System.out.println("Reviews: " + course.getReviews());
		// Save the course.
		appDAO.save(course);
	}

	private void deleteCourseById(AppDAO appDAO) {
		int id = 10;
		System.out.println("Deleting course id: " + id);
		appDAO.deleteCourseById(id);
		System.out.println("Course deleted!");
	}

	private void updateCourse(AppDAO appDAO) {
		int id = 10;
		// Find instructor.
		System.out.println("Finding course id: " + id);
		Course course = appDAO.findCourseById(id);
		// Update data.
		System.out.println("Updating course id: " + id);
		course.setTitle("Algorithm");

		appDAO.updateCourse(course);
		System.out.println("Course updated!");
	}

	private void updateInstructor(AppDAO appDAO) {
		int id = 1;
		// Find instructor.
		System.out.println("Finding instructor id: " + id);
		Instructor instructor = appDAO.findInstructorById(id);
		// Update data.
		System.out.println("Updating instructor id: " + id);
		instructor.setEmail("susan@good.com");

		appDAO.updateInstructor(instructor);
		System.out.println("Instructor updated!");
	}

	private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {
		int id = 1;
		// Find instructor.
		System.out.println("Finding instructor id: " + id);
		Instructor tempInstructor = appDAO.findInstructorByIdJoinFetch(id);
		System.out.println("Instructor: " + tempInstructor);
		System.out.println("Course: " + tempInstructor.getCourses());
	}

	private void findCoursesForInstructor(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: " + tempInstructor);

		// Find courses for instructor for lazy loading.
		List<Course> courses = appDAO.findCoursesByInstructorId(id);
		tempInstructor.setCourses(courses);
		System.out.println("Associated courses: " + tempInstructor.getCourses());

	}

	// Eager fetch type.
	private void findInstructorWithCourses(AppDAO appDAO) {
		int id = 1;
		System.out.println("Finding instructor id: " + id);

		Instructor tempInstructor = appDAO.findInstructorById(id);

		System.out.println("Instructor: " + tempInstructor);
		System.out.println("Associated courses: " + tempInstructor.getCourses());
	}

	private void createInstructorWithCourses(AppDAO appDAO) {
		// Create instructor.
		Instructor tempInstructor = new Instructor("Susan", "Public", "susan.public@hello.com");
		// Create the instructor detail.
		InstructorDetail tempInstructorDetail = new InstructorDetail(
				"http://www.susan.com/youtube", "video games");

		// Associate the objects.
		tempInstructor.setInstructorDetail(tempInstructorDetail);

		// Create and add courses.
		Course course1 = new Course("Software engineering Principles");
		Course course2 = new Course("Design Patterns");
		tempInstructor.add(course1);
		tempInstructor.add(course2);

		// Save the instructor.
		// This will also save the details and courses because of CascadeType
		System.out.println("Saving instructor: " + tempInstructor);
		System.out.println("List of courses: " + tempInstructor.getCourses());
		appDAO.save(tempInstructor);

		System.out.println("Instructor saved.");
	}

	private void deleteInstructorDetailById(AppDAO appDAO) {
		int id = 3;
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
