public class Cosmic extends User {

    public Cosmic(int id, String username, String password) {
        super(id, username, password, "Cosmic"); 
    }


    @Override
    public String toString() {
        return "Cosmic [id=" + getId() + ", username=" + getUsername() + "]";
    }
}