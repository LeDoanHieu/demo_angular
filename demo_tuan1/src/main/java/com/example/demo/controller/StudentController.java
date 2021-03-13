package com.example.demo.controller;

import com.example.demo.entity.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    @GetMapping("/student")
    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    @GetMapping("/student/name")
    public List<Student> getAllStudentByName(){
        return studentRepository.findallOderByFirstName();
    }
    /*
    * To Do
    *
    * */
    @GetMapping("/student/query")
    public List<Student> getAllStudentQuery(){
        return studentRepository.listQuery();

    }

    @PostMapping("/student")
    public Student create(@RequestBody Student student){
        return studentRepository.save(student);
    }
    // hien thi theo id
    @GetMapping("/student/{id}")
    public Student show(@PathVariable (value = "id") Integer studentId){
        Student student= studentRepository.findStudentById(studentId);
        return student;
    }
    @GetMapping("/student/s/{firstName}")
    public List<Student> getByFirst(@PathVariable (value = "firstName") String keyword){
        return studentRepository.findStudentByFirstName(keyword);
    }

    @PutMapping("/student/{id}")
    public Student update(@PathVariable Integer id, @RequestBody Student student){
        Student student1 = studentRepository.findStudentById(id);
        if (student==null){
            return null;
        }
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        return studentRepository.save(student1);
    }
    @DeleteMapping("/student/{id}")
    public boolean delete(@PathVariable Integer id){
        Student student = studentRepository.getOne(id);
        if(student==null){
            return false;
        }
        studentRepository.delete(student);
        return true;
    }
    @GetMapping("/student/search")
    public List<Student> search(@RequestParam (value = "search")  String s){

        return null;
    }


}
