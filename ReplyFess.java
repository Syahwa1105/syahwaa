// ReplyFess.java
import java.time.LocalDateTime;

public class ReplyFess extends Menfess {
    private User originalConfessionSender; 
    private ConfessFess originalConfession; 

    public ReplyFess(User replier, User originalConfessionSender, ConfessFess originalConfession, LocalDateTime waktu, String idMenfess, String pesan) {
        super(replier, waktu, idMenfess, pesan, "Balasan Confession"); 
        this.originalConfessionSender = originalConfessionSender;
        this.originalConfession = originalConfession; 
    }

    public User getOriginalConfessionSender() {
        return originalConfessionSender;
    }

    public ConfessFess getOriginalConfession() {
        return originalConfession;
    }

    @Override
    public String displayFess() {
        return getPesan();
    }
}