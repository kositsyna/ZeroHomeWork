package Manager.hbm;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name="addressbook")

public class ContactRecord {

    @Id
    public int id;
    public String nickname;
    public String firstname;
    public String middlename;
    public String lastname;
    public String mobile;
    public String email;
    public String phone2;
    public String address = new String ();
    public String company = new String ();
    public String title = new String ();
    public String home = new String ();
    public String work = new String ();
   // public String mobile = new String();
    public String photo = new String();
    public String fax =new String ();
    //public String email =new String ();
    public String email2 =new String ();
    public String email3 =new String ();
    public String homepage = new String ();

    @ManyToMany(mappedBy = "contacts")
    public List<GroupRecord> groups;


    public ContactRecord(){

    }
    public ContactRecord(int id,
                         String firstname,
                         String lastname,
                         String address,
                         String email,
                         String email2,
                         String email3,
                         String home,
                         String mobile,
                         String work,
                         String phone2){

        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.address = address;
        this.email = email;
        this.email2 = email2;
        this.email3 = email3;
        this.home = home;
        this.mobile = mobile;
        this.work = work;
        this.phone2 = phone2;
        this.groups = groups;
    }



}
