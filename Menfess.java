import java.time.LocalDateTime;

public abstract class Menfess {
    private User user; 
    private LocalDateTime waktu;
    private String idMenfess;
    private String pesan;
    private String tipeFess;
    private boolean isHidden; 

    public Menfess(User user, LocalDateTime waktu, String idMenfess, String pesan, String tipeFess) {
        this.user = user;
        this.waktu = waktu;
        this.idMenfess = idMenfess;
        this.pesan = pesan;
        this.tipeFess = tipeFess;
        this.isHidden = false; 
    }

    // Getters
    public User getUser() {
        return user;
    }

    public LocalDateTime getWaktu() {
        return waktu;
    }

    public String getIdMenfess() {
        return idMenfess;
    }

    public String getPesan() {
        return pesan;
    }

    public String getTipeFess() {
        return tipeFess;
    }

    public boolean isHidden() { 
        return isHidden;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    // Metode untuk menyembunyikan menfess
    public void hide() {
        if (!this.isHidden) {
            this.isHidden = true;
            System.out.println("Menfess berhasil disembunyikan.");
        } else {
            System.out.println("Menfess ini sudah tersembunyi.");
        }
    }

    // Metode untuk menampilkan kembali menfess
    public void unhide() {
        if (this.isHidden) {
            this.isHidden = false;
            System.out.println("Menfess berhasil ditampilkan kembali.");
        } else {
            System.out.println("Menfess ini sudah tampil.");
        }
    }

    public abstract String displayFess();
}