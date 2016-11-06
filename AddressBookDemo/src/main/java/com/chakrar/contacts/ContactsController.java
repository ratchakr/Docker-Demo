package com.chakrar.contacts;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactsController {

	@Autowired 
	ContactRepository repository;
	
	@RequestMapping("/save")
	public String process() {
		repository.save(new Contact("Jack", "1239872334", "jsnow@gmail.com"));
		repository.save(new Contact("Adam", "3219872345", "adam@gc.com"));
		repository.save(new Contact("Kim", "9723456773", "kim@gossip.com"));

		return "Done";
	}
	
	@PostMapping("/add")
	public String add(@ModelAttribute Contact contact, Model m) {
		System.out.println(" contact to save = "+ contact.getName());
		if (contact.getId() == 0) {
			repository.save(contact);
		} else {
			update(contact);
		}
		
        m.addAttribute("contact", new Contact());
		List<Contact> contacts = repository.findAll();
		//Collections.sort(contacts);
		System.out.println(" contacts = "+ contacts.size());
		m.addAttribute("contacts", contacts);
		return "contacts";
	}
	
	@GetMapping("/add")
	public String findAllContacts(Model model) {
		 System.out.println(" findAllContacts in db ");
		model.addAttribute("contact", new Contact());
		List<Contact> contacts = repository.findAll();
		System.out.println(" contacts = "+ contacts.size());
		model.addAttribute("contacts", contacts);
		return "contacts";
	}	
	
	
	public void update(Contact c) {
		System.out.println(" Update called with ID = "+ c.getId());
		repository.save(c);
	}
	
	@RequestMapping("/edit")
	public String edit(@RequestParam("id") int id) {
		System.out.println(" Edit called with ID = "+ id);
		Contact c = repository.findOne(id); 
		repository.save(c);

		return "contact";
	}
	
	@RequestMapping("/findall")
	public String findAll(Model model) {
		String result = "<html>";
		model.addAttribute("contact", new Contact());
		List<Contact> contacts = repository.findAll();
		System.out.println(" contacts = "+ contacts.size());
		/*for(Contact contact : repository.findAll()){
			result += "<div>" + contact.toString() + "</div>";
		}*/
		model.addAttribute("contacts", contacts);
		//return result + "</html>";
		return "contact";
	}
	
	@RequestMapping("/findbyid")
	public String findById(@RequestParam("id") int id) {
		String result = "";
		result = repository.findOne(id).toString();
		return result;
	}
	
	@RequestMapping("/findbyname")
	public String fetchDataByName(@RequestParam("name") String name) {
		String result = "<html>";
		
		for(Contact contact: repository.findByName(name)){
			result += "<div>" + contact.toString() + "</div>"; 
		}
		
		return result + "</html>";
	}
	
	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int id, Model model) {
		System.out.println(" Delete called with ID = "+ id);
		Contact c = repository.findOne(id); 
		repository.delete(c);

		
		model.addAttribute("contact", new Contact());
		List<Contact> contacts = repository.findAll();
		System.out.println(" contacts = "+ contacts.size());
		model.addAttribute("contacts", contacts);
		return "contacts";
	}
	
}
