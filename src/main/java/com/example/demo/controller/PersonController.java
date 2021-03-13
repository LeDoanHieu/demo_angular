package com.example.demo.controller;

import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@Controller
public class PersonController {
    @Autowired
    private PersonService personService;

    // hien thi danh sach cua person
    @GetMapping("/")
    public String viewHomePage(Model model){
        return findPaginated(1,"firstName","asc", model);

    }
    @GetMapping("/showNewPerson")
    public String showNewPerson(Model model){
        //creat model
        Person person = new Person();
        model.addAttribute("person",person);
        return "new_person";
    }
//    @RequestMapping("/search")
//    public ModelAndView search(@RequestParam String keyword){
//        List<Person> result = personService.search(keyword);
//        ModelAndView modelAndView = new ModelAndView("search");
//        modelAndView.addObject("result",result);
//        return modelAndView;
//
//    }
//    @GetMapping("/search")
//    public String search(Model model, @RequestParam String keyword){
//        List<Person> person = personService.search(keyword);
//        model.addAttribute("person", person);
//        return "search";
//
//    }
    @PostMapping("/savePerson")
    public String savePerson(@ModelAttribute("person") Person person){
        //save person to database
        personService.savePerson(person);
        return "redirect:/";
    }
    @GetMapping("/showPersonUpdate/{id}")
    public String showPersonUpdate(@PathVariable(value = "id")Long id,Model model){
        // get person from the service
        Person person = personService.getPersonById(id);
        //set person
        model.addAttribute("person",person);
        return "update_person";

    }
    @GetMapping("/deletePerson/{id}")
    public String deletePerson(@PathVariable(value = "id") Long id){
        // call delete person method
        personService.deletePersonById(id);
        return "redirect:/";
    }
    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam("sortField") String sortField,
                                @RequestParam("sortDir") String sortDir,
                                Model model){

        int pageSize = 5;
        Page<Person> page = personService.findPaginated(pageNo,pageSize,sortField,sortDir);
        List<Person> personList = page.getContent();
        model.addAttribute("currentPage",pageNo);
        model.addAttribute("totalPages",page.getTotalPages());
        model.addAttribute("totalItems",page.getTotalElements());

        model.addAttribute("sortField",sortField);
        model.addAttribute("sortDir",sortDir);
        model.addAttribute("reverseSortDir",sortDir.equals("asc")?"desc":"asc");
        model.addAttribute("personList",personList);
        return "index";
    }


}
