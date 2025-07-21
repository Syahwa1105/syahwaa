public class Admin extends User {

    public Admin(int id, String username, String password) {
        super(id, username, password, "Admin"); 
    }

    // Metode untuk mereset password pengguna lain atau diri sendiri
    public void resetPassword(User targetUser) {
        if (this.equals(targetUser)) {
            System.out.println("Anda akan mengubah password Anda sendiri.");
        } else {
            System.out.println("Anda akan mereset password untuk pengguna: " + targetUser.getUsername());
        }
        
        // Memanggil metode changePassword yang ada di kelas User
        targetUser.changePassword(targetUser); 
    }

    @Override
    public String toString() {
        return "Admin [id=" + getId() + ", username=" + getUsername() + "]";
    }
}