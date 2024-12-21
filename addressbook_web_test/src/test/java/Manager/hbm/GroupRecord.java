package Manager.hbm;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "group_list")
public class GroupRecord {

    @Id
    @Column(name = "group_id")
    public int id;

    @Column(name = "group_name")
    public String name;
    @Column(name = "group_header")
    public String header;
    @Column(name = "group_footer")
    public String footer;

    public Date deprecated = new Date();

    @ManyToMany
    @JoinTable(name="address_in_groups",
            joinColumns = @JoinColumn(name="group_id"),
            inverseJoinColumns = @JoinColumn(name="id"))//таблица связей, joinColumns указывает на группу, inverseJoinColumns указывает на контакты
    public List<ContactRecord> contacts; //описывает связь с объектами типа контакт_рекорд

    public GroupRecord(){ //пустой конструктор

    }
    public GroupRecord(int id, String name, String header, String footer){
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

}
