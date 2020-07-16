package com.example.demo.student;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/students")
public class StudentController {

	private final List<Student> list = new ArrayList<Student>();
	
	public StudentController() {
		list.add(new Student(1, "Carl"));
		list.add(new Student(2, "Mark"));
		list.add(new Student(3, "Daryl"));
	}
	
	@GetMapping("/{studentId}")
	@PreAuthorize("hasAuthority('course:read')")
	public Student getStudent(@PathVariable("studentId") Integer studentId) {
		return list.stream()
					.filter(stud -> stud.getStudentId().equals(studentId))
					.findFirst()
					.orElseThrow(() -> new IllegalStateException());
	}
	
	@PostMapping
	@PreAuthorize("hasAuthority('course:write')")
	public void createStudent(@RequestBody Student student) {
		System.out.println("Post: "+ student);
	}
	
	@PutMapping("/{studentId}")
	@PreAuthorize("hasAuthority('course:write')")
	public void updateStudent(@PathVariable("studentId") Integer studentId, @RequestBody Student student) {
		System.out.println("Put: "+ student);
	}
	
	@DeleteMapping("/{studentId}")
	@PreAuthorize("hasAuthority('course:write')")
	public void deleteStudent(@PathVariable("studentId") Integer studentId) {
		System.out.println("delete: "+ studentId);
	}

}
