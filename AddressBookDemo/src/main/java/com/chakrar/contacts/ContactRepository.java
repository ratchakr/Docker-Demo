package com.chakrar.contacts;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Integer> {

	List<Contact> findByName(String name);
	List<Contact> findAll();
}
