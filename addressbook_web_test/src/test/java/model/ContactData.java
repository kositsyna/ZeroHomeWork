package model;

public  record ContactData(String id, String firstname,String lastname, String photo)
{

    public ContactData() {
        this("","","","");
    }
    public ContactData withId(String id) {
        return new ContactData(id, this.firstname,this.lastname,this.photo);
    }
    public ContactData withFname(String firstname) {
        return new ContactData(this.id, firstname,this.lastname,this.photo);  //возвращаем НОВЫЙ объект
    }

    public ContactData withLname(String lastname) {
        return new ContactData(this.id, this.firstname, lastname,this.photo);
    }
    public ContactData withPhoto(String photo) {
        return new ContactData(this.id, this.firstname,this.lastname,photo);
    }


}

