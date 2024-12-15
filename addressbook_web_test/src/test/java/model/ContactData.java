package model;

public  record ContactData(String id, String firstname,String lastname)
{

    public ContactData() {
        this("","","");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstname,this.lastname);
    }
    public ContactData withFname(String firstname) {
        return new ContactData(this.id, firstname,this.lastname);  //возвращаем НОВЫЙ объект
    }

    public ContactData withLname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname);
    }

}

