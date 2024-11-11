package model;

public  record ContactData(String firstname, String middlename, String lastname, String nickname)
{

    public ContactData() {
        this("","","","");
    }
    public ContactData withFname(String firstname) {
        return new ContactData(firstname, this.middlename, this.lastname, this.nickname);  //возвращаем НОВЫЙ объект с другим именем, но хеад и футер как уже у существующего
    }
    public ContactData withMname(String middlename) {
        return new ContactData(this.firstname, middlename, this.lastname, this.nickname);
    }
    public ContactData withLname(String lastname) {
        return new ContactData(this.firstname, this.middlename, lastname, this.nickname);
    }
    public ContactData withNname(String nickname) {
        return new ContactData(this.firstname, this.middlename, this.lastname, nickname);
    }
}

