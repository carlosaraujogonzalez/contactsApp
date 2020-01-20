package com.sinbugs.contacts.api;

import javax.validation.Valid;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sinbugs.contacts.dto.Contact;
import com.sinbugs.contacts.service.ContactService;

@RestController
public class ContactsApi {

	@Autowired
	ContactService contactService;
	@Autowired
	Mapper mapper;
	
	@RequestMapping(value = "/product", method = RequestMethod.GET)
	public ContactResponse getById() {
		return new ContactResponse(1L, "Araújo", "González", "607716786", "carlosaraujogonzalez@gmail.com");
	}
	
	@RequestMapping(value = "/contact", method = RequestMethod.POST)
	public ContactResponse updateOrSave(@RequestBody @Valid ContactRequest contactRequest) {
		
		// Mapeas la request en el dto
		Contact contact = mapper.map(contactRequest, Contact.class);
		
		Contact updatedContact = contactService.save(contact);
		
		// Devuelves al usuario  un contacto de respuesta
		ContactResponse contactResponse = mapper.map(updatedContact, ContactResponse.class); 
		
		return contactResponse;
	}
	
}
