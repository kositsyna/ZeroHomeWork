package Manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="addressbook")

public class ContactRecord {

    @Id
    public int id;
    public String middlename;
    public String firstname;
    public String lastname ;
    public String nickname;

    public String address = new String ();
    public String company = new String ();
    public String title = new String ();
    public String home = new String ();
    public String work = new String ();
    public String mobile = new String();
    public String photo = new String();
    public String fax =new String ();
    public String email =new String ();
    public String email2 =new String ();
    public String email3 =new String ();
    public String homepage = new String ();



    public ContactRecord(){

    }
    public ContactRecord(int id,String middlename, String firstname, String lastname, String nickname){

        this.id = id;
        this.middlename = middlename;
        this.firstname = firstname;
        this.lastname = lastname;
        this.lastname = nickname;
    }



}
