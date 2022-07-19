package com.github.yagojanos.tema10.application;

import com.github.yagojanos.tema10.domain.Contact;
import com.github.yagojanos.tema10.repository.ContactRepository;
import com.github.yagojanos.tema10.service.ContactService;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class Program {
    public static void main(String[] args) {

        ContactService contactService = new ContactService(new ContactRepository());

        Contact contact1 = Contact.builder().name("Yago").email("yago@gmail.com").phoneNumber("2738392").build();
        Contact contact2 = Contact.builder().name("Ana").email("ana@gmail.com").phoneNumber("38483").build();

        contactService.save(contact1);
        contactService.save(contact2);

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        log.info(contactService.findAllSortedById());

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        log.info(contactService.findAllSortedByName());

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        Optional<Contact> optionalContact = contactService.findContactById(1);
        if( !optionalContact.isEmpty()){
            contact1 = optionalContact.get();
            log.info(contact1.getName());
        }

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        optionalContact = contactService.findContactById(2);
        if( !optionalContact.isEmpty()){
            contact1 = optionalContact.get();
            log.info(contact1.getName());
        }

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        optionalContact = contactService.findContactById(3);
        if( !optionalContact.isEmpty()){
            contact1 = optionalContact.get();
            log.info(contact1.getName());
        }

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        List<Contact> contactList = contactService.findContactByName("An");
        for(Contact contactItem : contactList){
            log.info(contactItem);
        }

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        Contact contact3 = Contact.builder().id(2).name("Mentor").email("email do mentor").phoneNumber("392034").build();

        contactService.update(contact3);

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        Contact contact4 = Contact.builder().id(5).name("Mentor").email("email do mentor").phoneNumber("392034").build();

        contactService.update(contact4);

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        Optional<Contact> optionalContact2 = contactService.findContactById(2);
        if( !optionalContact.isEmpty()){
            contact3 = optionalContact2.get();
            log.info(contact3.getName());
        }

        System.out.println();
        System.out.println("-------------------------------------------");
        System.out.println();

        contactService.delete(1);
        contactService.delete(2);

    }
}
