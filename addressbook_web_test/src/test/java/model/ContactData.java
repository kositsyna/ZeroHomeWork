package model;

public  record ContactData(String id, String lastname, String firstname)
{

    public ContactData() {
        this("","","");
    }
    public ContactData withId(String id) {return new ContactData(id, this.lastname, this. firstname);}//метод,

    public ContactData withFname(String firstname) {
        return new ContactData(this.id,this.lastname, firstname);
    }

    public ContactData withLname(String lastname) {
        return new ContactData(this.id, lastname, this.firstname);
    }
}

