import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; // Import Scanner jika changePassword ada di sini dan butuh input

public class User {
    private int id;
    private String username;
    private String password;
    private String role;
    private List<User> followers;
    private List<User> following;
    private List<Menfess> menfessList;

    public User(int id, String username, String password, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.followers = new ArrayList<>();
        this.following = new ArrayList<>();
        this.menfessList = new ArrayList<>();
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public List<User> getFollowers() {
        return followers;
    }

    public List<User> getFollowing() {
        return following;
    }

    public List<Menfess> getMenfess() {
        return menfessList;
    }

    // Setters
    public void setPassword(String password) {
        this.password = password;
    }

    // Methods
    public boolean addFollower(User user) {
        if (!followers.contains(user)) {
            followers.add(user);
            return true;
        }
        return false;
    }

    public boolean removeFollower(User user) {
        return followers.remove(user);
    }

    public boolean addFollowing(User user) {
        if (!following.contains(user)) {
            following.add(user);
            return true;
        }
        return false;
    }

    public boolean removeFollowing(User user) {
        return following.remove(user);
    }

    public void addMenfess(Menfess menfess) {
        this.menfessList.add(menfess);
    }

    // Metode untuk mengubah password 
    public void changePassword(User user) { 
        System.out.print("Masukkan password baru: ");
        String newPassword = TP4.getScanner().nextLine(); 

        if (newPassword.isEmpty()) {
            System.out.println("Password baru tidak boleh kosong!");
        } else if (newPassword.equals(user.getPassword())) {
            System.out.println("Password harus berbeda dari sebelumnya!");
        } else {
            user.setPassword(newPassword);
            System.out.println("Password berhasil diperbarui.");
        }
    }
}