package org.travel;

public class Users {

    private String id;
    private String firstName;
    private String lastName;
    private String birthDate;
    private String address;
    private String documentId;

    // SETTER
    public void setId(String id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    // GETTERS
    public String getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getAddress() {
        return address;
    }

    public String getDocumentId() {
        return documentId;
    }


}
