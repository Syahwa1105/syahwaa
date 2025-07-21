import java.time.LocalDateTime;

public class ConfessFess extends Menfess {
    private String penerima;

    public ConfessFess(User user, LocalDateTime waktu, String idMenfess, String pesan, String penerima) {

        super(user, waktu, idMenfess, pesan, "Confession"); 
        this.penerima = penerima;
    }

    public String getPenerima() {
        return penerima;
    }

    @Override
    public String displayFess() {
        return getPesan(); 
    }
}