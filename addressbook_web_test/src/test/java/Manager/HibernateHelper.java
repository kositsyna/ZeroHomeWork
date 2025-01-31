package Manager;

import Manager.hbm.ContactRecord;
import Manager.hbm.GroupRecord;
import io.qameta.allure.Step;
import model.ContactData;
import model.GroupData;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.stream.Collectors;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
            sessionFactory =  new Configuration()
                    .addAnnotatedClass(ContactRecord.class)
                    .addAnnotatedClass(GroupRecord.class)
                    .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                    .setProperty(AvailableSettings.USER, "root")
                    .setProperty(AvailableSettings.JAKARTA_JDBC_PASSWORD, "")
                    .buildSessionFactory();

}
static List<GroupData> convertListC(List<GroupRecord> records){
    return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
}

    private static GroupData convert(GroupRecord record) {
        return new GroupData("" + record.id, record.name, record.header, record.footer);
    }

    private static GroupRecord convert(GroupData data) {
        var id = data.id();
        if ("".equals(id)){
            id = "0";
        }
        return new GroupRecord(Integer.parseInt(id),data.name(),data.header(),data.footer());
    }
    private static List<ContactData> convertContactList(List<ContactRecord> records) {
        return records.stream().map(HibernateHelper::convert).collect(Collectors.toList());
    }

    private static ContactData convert(ContactRecord record) {
        return new ContactData()
                .withId("" + record.id)
                .withMname(record.middlename)
                .withLname(record.lastname)
                .withNname(record.nickname)
                .withAddress(record.address)
                .WithEmail(record.email)
               .withFname(record.firstname)
                .WithPhoto(record.photo)
                .WithHome(record.home)
               .WithMobile(record.mobile)
               .WithWork(record.work)
                .WithSecondary(record.phone2);
    }
    private static ContactRecord convert(ContactData data){
        var id = data.id();
        if ("".equals(id)){
            id = "0";
        }
        return new ContactRecord(Integer.parseInt(id),
                data.firstname(),
                data.lastname(),
                data.address(),
                data.email(),
                data.email2(),
                data.email3(),
                data.home(),
                data.mobile(),
                data.work(),
                data.phone2());
    }

@Step
    public List<GroupData> getGroupList() {
        return  convertListC(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupRecord",GroupRecord.class).list();
        }));
    }

    public long getGroupCount() {
        return  sessionFactory.fromSession(session -> { //считаем кол-во групп в результате запроса
            return session.createQuery("select count(*) from GroupRecord",long.class).getSingleResult();
        });
    }

    public void createGroup(GroupData groupData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin(); //открывает транзакцию в бд при создании группы
            session.persist(convert(groupData));
            session.getTransaction().commit(); //коммитим изменения
        });
    }

    public void createContact(ContactData contactData) {
        sessionFactory.inSession(session -> {
            session.getTransaction().begin(); //открывает транзакцию в бд при создании контакта
            session.persist(convert(contactData));
            session.getTransaction().commit(); //коммитим изменения
        });
    }

    public List<ContactData> getContactList() {
        return  convertContactList(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactRecord",ContactRecord.class).list();
        }));
    }

    public long getContactCount() {
        return  sessionFactory.fromSession(session -> { //считаем кол-во групп в результате запроса
            return session.createQuery("select count(*) from ContactRecord",long.class).getSingleResult();
        });
    }

    public List<ContactData> getContactsInGroup(GroupData group) {
             return (sessionFactory.fromSession(session -> {
                 return convertContactList(session.get(GroupRecord.class,group.id()).contacts);

        }));
    }
    public List<GroupData> getGroupsByContact(ContactData contactData) {
        return sessionFactory.fromSession(session -> {
            return convertListC(session.get(ContactRecord.class, contactData.id()).groups);
        });
    }

    public List<ContactData> getContactsNotInGroup() {
        var allContacts = getContactList();
        allContacts.removeIf(contactData -> {
            var groups = getGroupsByContact(contactData);
            return (groups != null) && (!groups.isEmpty());
        });
        return allContacts;
    }

    public String getIdContactByName(String firstname) {
        return sessionFactory.fromSession(session -> {
            return session.createQuery(String.format("select id from ContactRecord where firstname='%s'", firstname), Integer.class).getSingleResult().toString();
        });
    }
}
