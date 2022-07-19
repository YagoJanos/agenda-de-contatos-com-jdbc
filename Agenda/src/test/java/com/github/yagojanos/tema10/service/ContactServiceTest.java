package com.github.yagojanos.tema10.service;

import com.github.yagojanos.tema10.domain.Contact;
import com.github.yagojanos.tema10.repository.ContactRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactServiceTest {

    private ContactService contactService;
    private ContactRepository contactRepositoryMock;

    private Contact contact;
    private Contact contact2;

    @BeforeEach
    public void setup(){
        contactRepositoryMock = Mockito.mock(ContactRepository.class);
        contactService = new ContactService(contactRepositoryMock);

        contact = Contact.builder().id(1).name("Yago").phoneNumber("30492034").email("yjquaranta.gmail").build();
        contact2 = Contact.builder().id(0).name("Isabella").phoneNumber("928374").email("bellaquaranta@gmail.com").build();
    }

    @Test
    public void save_shouldInvokeContactRepositorySaveMethod(){

        contactService.save(contact);

        Mockito.verify(contactRepositoryMock).save(contact);

    }

    @Test
    public void delete_shouldInvokeContactRepositoryDeleteMethod(){



        contactService.delete(1);

        Mockito.verify(contactRepositoryMock).delete(1);

    }

    @Test
    public void delete_shouldRequestAValidId() {

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->

                contactService.delete(0)
        );

        assertThat(thrown.getMessage(), is(equalTo("Invalid value for id")));
    }

    @Test
    public void update_shouldInvokeContactRepositoryUpdateMethod(){

        contactService.update(contact);

        Mockito.verify(contactRepositoryMock).update(contact);
    }

    @Test
    public void update_shouldRequestAValidId(){

        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->

                contactService.update(contact2)
        );

        assertThat(thrown.getMessage(), is(equalTo("Invalid value for id")));
    }

    @Test
    public void findAllSortedById_shouldInvokeRepositoryMethod(){
        contactService.findAllSortedById();

        Mockito.verify(contactRepositoryMock).findAllSortedById();
    }

    @Test
    public void findAllSortedByName_shouldInvokeRepositoryMethod(){
        contactService.findAllSortedByName();

        Mockito.verify(contactRepositoryMock).findAllSortedByName();
    }

    @Test
    public void findContactByName_shouldInvokeRepositoryMethod(){
        contactService.findContactByName(contact.getName());

        Mockito.verify(contactRepositoryMock).findByName(contact.getName());
    }

    @Test
    public void findContactById_shouldInvokeRepositoryMethod(){
        contactService.findContactById(1);

        Mockito.verify(contactRepositoryMock).findById(1);
    }

    @Test
    public void findContactById_shouldRequireAValidId(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->

                contactService.findContactById(0)
        );

        assertThat(thrown.getMessage(), is(equalTo("Invalid value for id")));
    }

    @Test
    public void requireValidId_shouldThrowIllegalArgumentExceptionWhenIdIsZero(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->

                ContactService.requireValidId(0)
        );

        assertThat(thrown.getMessage(), is(equalTo("Invalid value for id")));
    }

    @Test
    public void requireValidId_shouldThrowIllegalArgumentExceptionWhenIdIsLessThenZero(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->

                ContactService.requireValidId(-1)
        );

        assertThat(thrown.getMessage(), is(equalTo("Invalid value for id")));
    }

    @Test
    public void requireValidId_shouldThrowIllegalArgumentExceptionWhenIdIsNull(){
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class, () ->

                ContactService.requireValidId(null)
        );

        assertThat(thrown.getMessage(), is(equalTo("Invalid value for id")));
    }
}
