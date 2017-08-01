package com.sistema.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.sistema.model.Person;
import com.sistema.service.ExampleService;

@Service("exampleService")
public class ExampleServiceImpl implements ExampleService{

	public static final Log LOG = LogFactory.getLog(ExampleServiceImpl.class); 
	
	@Override
	public List<Person> getPeopleList() {
			List<Person> people = new ArrayList<>();
				people.add(new Person("Jose",22));
				people.add(new Person("Pablo",32));
				people.add(new Person("Pedro",18));
				people.add(new Person("Juan",24));
				people.add(new Person("Maria",27));
				people.add(new Person("Ana",24));
				people.add(new Person("Laura",29));
				
			LOG.info("getPeopleList FROM SERVICE");
			return people;
		}
}