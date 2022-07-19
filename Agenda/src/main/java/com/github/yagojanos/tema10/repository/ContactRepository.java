package com.github.yagojanos.tema10.repository;

import com.github.yagojanos.tema10.connection.ConnectionFactory;
import com.github.yagojanos.tema10.domain.Contact;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

class ContactByNameComparator implements Comparator<Contact>{

    @Override
    public int compare(Contact contact, Contact otherContact) {
        return contact.getName().compareTo(otherContact.getName());
    }
}
@Log4j2
public class ContactRepository {

    public void save(Contact contact) {

        String sql = "INSERT INTO `agenda`.`contato` (`contact_name`, `phone`, `email`) VALUES ('%s', '%s', '%s');"
                .formatted(contact.getName(), contact.getPhoneNumber(), contact.getEmail());

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);
            log.info("Inserted contact '{}' in database, rows affected by this change '{}'", contact.getName(), rowsAffected);
        } catch (SQLException e) {
            log.error("Error while trying to insert contact '{}'", contact.getName());
        }
    }

    public void delete(int id) {

        String sql = "DELETE FROM `agenda`.`contato` WHERE (`id` = '%d');".formatted(id);

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);
            log.info("Rows affected by this change '{}'", rowsAffected);
        } catch (SQLException e) {
            log.error("Error while trying to delete contact '{}'", id);
        }
    }

    public void update(Contact contact) {

        String sql = "UPDATE `agenda`.`contato` SET `contact_name` = '%s', `phone` = '%s', `email` = '%s' WHERE (`id` = '%d');"
                .formatted(contact.getName(), contact.getPhoneNumber(), contact.getEmail(), contact.getId());

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement()) {

            int rowsAffected = statement.executeUpdate(sql);
            log.info("Rows affected by this change '{}'", rowsAffected);
        } catch (SQLException e) {

            log.error("Error while trying to update contact '{}'", contact.getId(), e);
        }
    }

    public List<Contact> findAllSortedById() {

        String sql = "SELECT `id`,`contact_name`,`phone`,`email` FROM `agenda`.`contato`;";
        List<Contact> contactList = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                Contact contact = Contact.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("contact_name"))
                        .phoneNumber(resultSet.getString("phone"))
                        .email(resultSet.getString("email"))
                        .build();

                contactList.add(contact);
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all contacts sorted by id");
        }

        return Collections.unmodifiableList(contactList);
    }

    public List<Contact> findAllSortedByName() {

        String sql = "SELECT `id`,`contact_name`,`phone`,`email` FROM `agenda`.`contato`;";
        List<Contact> contactList = new ArrayList<>();

        try(Connection connection = ConnectionFactory.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)){

            while (resultSet.next()){
                Contact contact = Contact.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("contact_name"))
                        .phoneNumber(resultSet.getString("phone"))
                        .email(resultSet.getString("email"))
                        .build();

                contactList.add(contact);
            }
        } catch (SQLException e) {
            log.error("Error while trying to find all contacts sorted by name");
        }

        contactList.sort(new ContactByNameComparator());
        return Collections.unmodifiableList(contactList);
    }

    public List<Contact> findByName(String name) {

        String sql = "SELECT `id`,`contact_name`,`phone`,`email` FROM `agenda`.`contato` WHERE `contact_name` like '%%%s%%';".formatted(name);
        List<Contact> contactList = new ArrayList<>();

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while(resultSet.next()) {
                Contact contact = Contact.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("contact_name"))
                        .phoneNumber(resultSet.getString("phone"))
                        .email(resultSet.getString("email"))
                        .build();

                contactList.add(contact);
            }
        } catch (SQLException e) {
            log.error("Error while trying to find contact by name", e);
        }

        return Collections.unmodifiableList(contactList);
    }

    public Optional<Contact> findById(int id) {

        String sql = "SELECT `id`,`contact_name`,`phone`,`email` FROM `agenda`.`contato` WHERE `id` = '%d';".formatted(id);
        Contact desiredContact = null;

        try (Connection connection = ConnectionFactory.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {


            if(resultSet.next()) {
                desiredContact = Contact.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("contact_name"))
                        .phoneNumber(resultSet.getString("phone"))
                        .email(resultSet.getString("email"))
                        .build();

            }

        } catch (SQLException e) {
            log.error("Error while trying to find contact by id", e);
        }

        return Optional.ofNullable(desiredContact);
    }
}
