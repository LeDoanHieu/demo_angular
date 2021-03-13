package com.example.demo.repository;

import com.example.demo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository  extends JpaRepository<Person,Long> {
//     @Query(value = "select c FROM Person c WHERE c.firstName like %:keyword"
//             +" or c.lastName like %:keyword%"
//             +"or c.email like %:keyword%"
//             +" or c.point like %:keyword%")
//      List<Person> search( String keyword);
}
