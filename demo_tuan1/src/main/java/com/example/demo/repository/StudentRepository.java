package com.example.demo.repository;

import com.example.demo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    //@Query("select c from  Student c order by c.firstName ASC ")
    Student findStudentById(Integer id);
    List<Student> findStudentByFirstName(String name);
    // In ra danh sach sinh vien sap xep theo ten
    @Query("select student from Student  student  order by student.firstName asc ")
    List<Student> findallOderByFirstName();

    //Truy van
    @Query("select c from Student c where c.age > 22")
    List<Student> listQuery();

   // List<Student> findStudentByFirstNameStatsWith(String keyword);


}
