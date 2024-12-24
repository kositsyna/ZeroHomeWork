package model;

public record ContactData(String id,
                          String middlename,
                          String lastname,
                          String nickname,
                          String address,
                          String email,
                          String email2,
                          String email3,
                          String firstname,
                          String photo,
                          String home,
                          String mobile,
                          String work,
                          String phone2)
{

    public ContactData() {
        this ("", "", "", "", "", "", "", "", "", "", "", "","","");
    }
    public ContactData withId(String id)  {return new ContactData(id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, firstname, this.photo, this.home, this.mobile, this.work, this.phone2);}

    public ContactData withMname(String middlename) {
        return new ContactData(this.id, middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, this.firstname, this.photo, this.home, this.mobile, this.work, this.phone2);
    }

    public ContactData withFname(String firstname) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, firstname, this.photo, this.home, this.mobile, this.work, this.phone2);
    }

    public ContactData withLname(String lastname) {
        return new ContactData(this.id, this.middlename, lastname, this.nickname, this.address, this.email,this.email2,this.email3, this.firstname, this.photo, this.home, this.mobile, this.work, this.phone2);
    }
    public ContactData withNname(String nickname) {
        return new ContactData(this.id, this.middlename, this.lastname, nickname, this.address, this.email,this.email2,this.email3, this.firstname, this.photo, this.home, this.mobile, this.work, this.phone2);
    }
    public ContactData withAddress(String address) {
        return new ContactData(this.id, this.middlename, lastname, this.nickname, address, this.email,this.email2,this.email3, this.firstname, this.photo, this.home, this.mobile, this.work, this.phone2);}

    public ContactData WithEmail(String email) {
            return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, email,this.email2,this.email3, this.firstname, this.photo, this.home, this.mobile, this.work, this.phone2);
        }

    public ContactData WithPhoto(String photo) {
            return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, this.firstname, photo, this.home, this.mobile, this.work, this.phone2);
        }
    public ContactData WithHome(String home) {//домашний телефон
            return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, this.firstname, photo, home, this.mobile, this.work, this.phone2);}

    public ContactData WithMobile(String mobile){
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, this.firstname, this.photo, this.home, mobile, this.work, this.phone2);
    }
    public ContactData WithWork(String work) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, this.firstname, photo, this.home, this.mobile, work, this.phone2);
    }
    public ContactData WithSecondary(String phone2) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email,this.email2,this.email3, this.firstname, photo, this.home, this.mobile, this.work, phone2);
    }

    public ContactData withEmail12(String email2) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, email2, this.email3, this.firstname, photo, this.home, this.mobile, this.work, this.phone2);
    }
    public ContactData withEmail13(String email3) {
        return new ContactData(this.id, this.middlename, this.lastname, this.nickname, this.address, this.email, this.email2, email3, this.firstname, photo, this.home, this.mobile, this.work, this.phone2);
    }
}
