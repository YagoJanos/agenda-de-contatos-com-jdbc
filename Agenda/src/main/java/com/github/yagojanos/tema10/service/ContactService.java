package com.github.yagojanos.tema10.service;

import com.github.yagojanos.tema10.domain.Contact;
import com.github.yagojanos.tema10.repository.ContactRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class ContactService {

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository){
        this.contactRepository = contactRepository;
    }

    public void save(Contact contact){
        this.contactRepository.save(contact);
    }

    public void delete(Integer id){

        log.info("Initializing delete routine");

        requireValidId(id);

        this.contactRepository.delete(id);
    }

    public void update(Contact contactToUpdate){

        log.info("Initializing update routine");

        Integer contactId = contactToUpdate.getId();

        requireValidId(contactId);

        this.contactRepository.update(contactToUpdate);
    }

    public List<Contact> findAllSortedById(){

        log.info("Finding all contacts sorted by id");
        return this.contactRepository.findAllSortedById();
    }

    public List<Contact> findAllSortedByName(){

        log.info("Finding all contacts sorted by name");
        return this.contactRepository.findAllSortedByName();
    }

    public List<Contact> findContactByName(String name){

        log.info("Finding contact by name");
        return this.contactRepository.findByName(name);
    }

    public Optional<Contact> findContactById(Integer id){

        log.info("Finding contact by id");

        requireValidId(id);

        Optional<Contact>  optionalContact = this.contactRepository.findById(id);
        if(optionalContact.isEmpty()){
            log.info("No one found");
        }

        return optionalContact;
    }

    static void requireValidId(Integer id){
        if (id == null || id <= 0){
            throw new IllegalArgumentException("Invalid value for id");
        }
    }
}
