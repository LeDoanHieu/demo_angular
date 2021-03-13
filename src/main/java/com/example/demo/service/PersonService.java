package com.example.demo.service;

import com.example.demo.model.Person;

import org.springframework.data.domain.Page;

import java.util.List;

public interface PersonService  {
    List<Person> getAllPeople();
    void savePerson(Person person);
    Person getPersonById(Long id);
    void deletePersonById(Long id);
    Page<Person> findPaginated(int pageNo, int pageSize,String sortField, String sortDirection);
//    List<Person> search(String keyword);
}
