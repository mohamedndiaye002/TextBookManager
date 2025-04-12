package models;
public class Personne {
    protected  String firstName, lastName, email, phoneNumber;


    // Methode Contructrice d'une personne avec ses coordonnees
    public Personne(String firstName, String lastName, String email, String phoneNumber){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Methode toString
    @Override
    public String toString(){
        return (this.firstName+" "+this.lastName+" "+this.email+" "+this.phoneNumber);
    }


    // Les getter et les setters
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
