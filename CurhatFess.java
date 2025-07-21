import java.time.LocalDateTime;

public class CurhatFess extends Menfess {

    public CurhatFess(User user, LocalDateTime waktu, String idMenfess, String pesan) {
        super(user, waktu, idMenfess, pesan, "Curhat"); 
    }

    @Override
    public String displayFess() {
        return getPesan();
    }
}