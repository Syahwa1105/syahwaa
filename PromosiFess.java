import java.time.LocalDateTime;

public class PromosiFess extends Menfess {
    private String namaProduk;

    public PromosiFess(User user, LocalDateTime waktu, String idMenfess, String namaProduk, String pesan) {
        
        super(user, waktu, idMenfess, pesan, "Promosi"); 
        this.namaProduk = namaProduk;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    @Override
    public String displayFess() {
        return "Promosi produk " + namaProduk + ": " + getPesan();
    }
}