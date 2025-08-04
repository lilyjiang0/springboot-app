package com.learnspring.cruddemo;

import com.learnspring.cruddemo.dao.StudentDAO;
import com.learnspring.cruddemo.entity.Student;
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
	public CommandLineRunner commandLineRunner(StudentDAO studentDao) {
		return runner -> {
//			createStudent(studentDao);
			createMultiStudent(studentDao);
//			readStudent(studentDao);
//			queryForAllStudents(studentDao);
//			queryForStudentsByLastName(studentDao);
//			updateStudent(studentDao);
//			deleteStudent(studentDao);
//			deleteAllStudents(studentDao);
		};
	}

	private void deleteAllStudents(StudentDAO studentDao) {
		System.out.println("Deleting all students..");
		int numRowsDeleted = studentDao.deleteAll();
		System.out.println("Deleted row count: " + numRowsDeleted);
	}

	private void deleteStudent(StudentDAO studentDao) {
		int studentId = 3;
		System.out.println("Deleting student id: " + studentId);
		studentDao.delete(studentId);
	}

	private void updateStudent(StudentDAO studentDao) {
		// Retrieve student based on id.
		int studentId = 1;
		System.out.println("Getting student with id:" + studentId);
		Student student = studentDao.findById(studentId);
		// Change first name.
		System.out.println("Updating student...");
		student.setFirstName("John");
		// Update the student.
		studentDao.update(student);
		// Display the updated student.
		System.out.println("Updated student: " + student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDao) {
		// Get a list of students.
		List<Student> students = studentDao.findByLastName("Smith");
		// Display list of students.
		for (Student s: students) {
			System.out.println(s);
		}
	}

	private void queryForAllStudents(StudentDAO studentDao) {
		// Get a list of students
		List<Student> students = studentDao.findAll();
		// Display list of students
		for (Student s : students) {
			System.out.println(s);
		}

	}

	private void readStudent(StudentDAO studentDao) {
		// Create a student object.
		System.out.println("Creating new student object..");
		Student student = new Student("Peter", "Duck", "peter@hello.com");

		// Save the student.
		System.out.println("Saving the student...");
		studentDao.save(student);

		// Display id of the saved student.
		int id = student.getId();
		System.out.println("Saved student id: " + id);

		// Retrieve student based on the id.
		System.out.println("Retrieving student with id: " + id);
		Student student1 = studentDao.findById(id);

		// Display the student.
		System.out.println("Found the student: " + student1);
	}

	private void createMultiStudent(StudentDAO studentDao) {
		// Create multiple students.
		System.out.println("Creating 3 student object...");
		Student student1 = new Student("John", "Smith", "john@hello.com");
		Student student2 = new Student("Ella", "Public", "ella@hello.com");
		Student student3 = new Student("Bonita", "Applebum", "bonita@hello.com");

		// Save the student objects.
		System.out.println("Saving 3 students...");
		studentDao.save(student1);
		studentDao.save(student2);
		studentDao.save(student3);
	}

	private void createStudent(StudentDAO studentDao) {
		// Create the student object.
		System.out.println("Creating new student object...");
		Student student = new Student("Paul", "Doe", "paul@hello.com");

		// Save the student object.
		System.out.println("Saving the student...");
		studentDao.save(student);

		// Display id of the saved student.
		System.out.println("Saved student. Generated id: " + student.getId());
	}

}
