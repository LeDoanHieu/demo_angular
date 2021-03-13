package com.example.demo.service;

import com.example.demo.model.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public abstract class PersonServiceIpl implements PersonService {
    @Autowired
    private PersonRepository personRepository;
    @Override
    public List<Person> getAllPeople() {
        return personRepository.findAll();
    }

    @Override
    public void savePerson(Person person) {
        this.personRepository.save(person);
    }

    @Override
    public Person getPersonById(Long id) {
        Optional<Person> optional = personRepository.findById(id);
        Person person = null;
        if (optional.isPresent()){
            person =optional.get();

        }else {
            throw new RuntimeException("Person not found for id ::" +id);
        }
        return person;
    }

    @Override
    public void deletePersonById(Long id) {
        this.personRepository.deleteById(id);

    }

    @Override
    public Page<Person> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo-1,pageSize,sort);
        return this.personRepository.findAll(pageable);
    }

//    @Override
//    public List<Person> search(String keyword) {
//        return personRepository.search(keyword);
//    }


}


