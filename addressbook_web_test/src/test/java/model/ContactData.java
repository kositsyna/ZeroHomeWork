package model;

public  record ContactData(String id,String middlename, String lastname,String nickname, String firstname)
{

    public ContactData() {
        this("","","","","");
    }
    public ContactData withId(String id) {return new ContactData(id,this.middlename, this.lastname, this. firstname,this.nickname);}

    public ContactData withMname(String middlename) {
        return new ContactData(this.id,middlename,this.lastname,this.firstname,this.nickname);
    }

    public ContactData withFname(String firstname) {
        return new ContactData(this.id,this.middlename,this.lastname, firstname,this.nickname);
    }

    public ContactData withLname(String lastname) {
        return new ContactData(this.id,this.middlename, lastname, this.firstname,this.nickname);
    }
    public ContactData withNname(String nickname) {
        return new ContactData(this.id,this.middlename,this.lastname,this.firstname, nickname);
    }

}

