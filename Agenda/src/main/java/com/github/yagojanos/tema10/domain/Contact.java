package com.github.yagojanos.tema10.domain;

import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

import java.util.Objects;

@Value
@Builder
public class Contact implements Comparable<Contact>{
    Integer id;
    @NonNull
    String name;
    String phoneNumber;
    String email;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Contact otherContact) {
        return this.id.compareTo(otherContact.id);
    }
}
