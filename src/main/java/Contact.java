import java.io.Serializable;
import java.util.ArrayList;
/**
 * Created by spbw0-rep6 on 05.09.2016.
 */
public class Contact implements Serializable{
    protected ArrayList <String> phone = new ArrayList <>();
    protected String name;
    protected String email;

    public void setName (String name){
        this.name=name;
    }
    public String getName (){
        return name;
    }
    public void setPhone(String phone) {

        this.phone.add(phone);

    }
    public ArrayList <String> getPhone(){
        return phone;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail (){
        return email;
    }
    public boolean equals (Object another){
        Contact contact = (Contact) another;
        return this.name.equals(contact.name);
    }
}
