import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

public class TP4 {

    private static ArrayList<User> daftarUser = new ArrayList<>();
    private static User currentUser = null;
    private static Scanner scanner = new Scanner(System.in);
    private static int nextUserId = 0;

    private static boolean globalProgramShouldRun = true; 

    public static Scanner getScanner() {
        return scanner;
    }

    public static void main(String[] args) {
        System.out.print("Berapa user yang ingin didaftarkan? ");
        int jumlahUser = scanner.nextInt();
        scanner.nextLine();

        daftarUser(jumlahUser);

        while (globalProgramShouldRun) {
            currentUser = null;
            
            while (currentUser == null && globalProgramShouldRun) {
                System.out.println("\nSilakan login untuk menggunakan fitur kami");
                System.out.print("username: ");
                String username = scanner.nextLine();
                System.out.print("password: ");
                String password = scanner.nextLine();

                if (username.equalsIgnoreCase("End Game") && password.equalsIgnoreCase("End Game")) {
                    globalProgramShouldRun = false;
                    System.out.println("Terima kasih telah memainkan BurhanFess.");
                    continue; 
                }
                
                currentUser = login(username, password);
                if (currentUser != null) {
                    System.out.println("Berhasil login!");
                } else {
                    System.out.println("Maaf, username atau password yang anda masukkan salah. Tolong masukkan kembali dengan benar");
                }
            }

            if (!globalProgramShouldRun) {
                break;
            }

            if (currentUser != null) {
                if (currentUser instanceof Admin) {
                    displayAdminMenu((Admin) currentUser);
                } else if (currentUser instanceof Cosmic) {
                    displayCosmicMenu((Cosmic) currentUser);
                }
            }
        }

        scanner.close();
    }

    private static void daftarUser(int jumlah) {
        for (int i = 0; i < jumlah; i++) {
            String role;
            while (true) {
                System.out.print("Masukkan role user dengan ID " + nextUserId + " (Admin/Cosmic): ");
                role = scanner.nextLine();
                if (role.equalsIgnoreCase("Admin") || role.equalsIgnoreCase("Cosmic")) {
                    break;
                } else {
                    System.out.println("Role tidak valid. Silakan masukkan 'Admin' atau 'Cosmic'.");
                }
            }

            System.out.print("Masukkan username user dengan ID " + nextUserId + ": ");
            String username = scanner.nextLine();
            System.out.print("Masukkan password user dengan ID " + nextUserId + ": ");
            String password = scanner.nextLine();

            if (role.equalsIgnoreCase("Admin")) {
                daftarUser.add(new Admin(nextUserId++, username, password));
            } else {
                daftarUser.add(new Cosmic(nextUserId++, username, password));
            }
        }

        System.out.println("\nTelah dibuat " + daftarUser.size() + " user dengan masing-masing ID dan username:");
        for (User user : daftarUser) {
            System.out.println(user.getId() + ". " + user.getUsername());
        }
    }

    private static User login(String username, String password) {
        for (User user : daftarUser) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    private static void displayAdminMenu(Admin adminUser) {
        int pilihan;
        do {
            System.out.println("\nPilih salah satu menu kami!");
            System.out.println("1. Melihat Daftar Pengguna");
            System.out.println("2. Reset Password Pengguna");
            System.out.println("3. Sembunyikan Menfess");
            System.out.println("4. Tampilkan Kembali Menfess");
            System.out.println("5. Hapus Akun"); // Opsi baru
            System.out.println("6. Logout");
            System.out.println("7. End Game"); 
            System.out.print("\nMasukkan pilihanmu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    lihatDaftarPenggunaAdmin(); 
                    break;
                case 2:
                    resetPasswordPenggunaAdmin(adminUser); 
                    break;
                case 3:
                    sembunyikanMenfessAdmin(adminUser); 
                    break;
                case 4:
                    tampilkanKembaliMenfessAdmin(adminUser); 
                    break;
                case 5: 
                    hapusAkunAdmin(); 
                    break;
                case 6:
                    System.out.println("Anda berhasil Logout.");
                    currentUser = null;
                    break;
                case 7: 
                    System.out.println("Terima kasih telah memainkan BurhanFess.");
                    currentUser = null;
                    globalProgramShouldRun = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-7."); 
            }
        } while (currentUser != null && globalProgramShouldRun);
    }

    private static void displayCosmicMenu(Cosmic cosmicUser) {
        int pilihan;
        do {
            System.out.println("\nPilih salah satu menu kami!");
            System.out.println("1. Follow Pengguna Lain");
            System.out.println("2. Mengirim Menfess");
            System.out.println("3. Melihat Daftar Followers dan Following");
            System.out.println("4. Melihat Daftar Menfess");
            System.out.println("5. Ubah Password");
            System.out.println("6. Logout");
            System.out.println("7. End Game");
            System.out.print("\nMasukkan pilihanmu: ");
            pilihan = scanner.nextInt();
            scanner.nextLine();

            switch (pilihan) {
                case 1:
                    followPenggunaLainCosmic(cosmicUser);
                    break;
                case 2:
                    mengirimMenfessCosmic(cosmicUser);
                    break;
                case 3:
                    melihatDaftarFollowersFollowingCosmic();
                    break;
                case 4:
                    melihatDaftarMenfessCosmic();
                    break;
                case 5:
                    ubahPasswordCosmic(cosmicUser);
                    break;
                case 6:
                    System.out.println("Anda berhasil Logout.");
                    currentUser = null;
                    break;
                case 7:
                    System.out.println("Terima kasih telah memainkan BurhanFess.");
                    currentUser = null;
                    globalProgramShouldRun = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid. Silakan masukkan angka 1-7.");
            }
        } while (currentUser != null && globalProgramShouldRun);
    }

    private static void lihatDaftarPenggunaAdmin() {
        System.out.println("\n--------------------------------------------------------");
        System.out.printf("| %-8s | %-25s | %-23s |\n", "ID", "Username", "Role");
        System.out.println("--------------------------------------------------------");

        if (daftarUser.isEmpty()) {
            System.out.println("|                                                      |");
            System.out.printf("| %-54s |\n", "Tidak ada pengguna terdaftar.");
            System.out.println("|                                                      |");
        } else {
            for (User user : daftarUser) {
                System.out.printf("| %-8d | %-25s | %-23s |\n", user.getId(), user.getUsername(), user.getRole());
            }
        }
        System.out.println("--------------------------------------------------------");
    }

    private static void resetPasswordPenggunaAdmin(Admin adminUser) {
        System.out.print("Masukkan username pengguna yang ingin direset password-nya: ");
        String targetUsername = scanner.nextLine();
        User targetUser = findUserByUsername(targetUsername);

        if (targetUser == null) {
            System.out.println("Pengguna dengan username " + targetUsername + " tidak ditemukan.");
            return;
        }
        adminUser.resetPassword(targetUser); 
    }

    private static void sembunyikanMenfessAdmin(Admin adminUser) {
        System.out.println("\n== Semua Menfess yang Sudah Terkirim ==");
        
        Map<String, List<Menfess>> publicMenfessPerUser = new LinkedHashMap<>();
        Map<String, List<Integer>> publicMenfessOriginalIndicesPerUser = new LinkedHashMap<>(); 

        for (User user : daftarUser) {
            List<Menfess> userMenfessList = user.getMenfess();
            if (userMenfessList != null) {
                List<Menfess> userPublicMenfess = new ArrayList<>();
                List<Integer> userPublicMenfessIndices = new ArrayList<>();

                for (int i = 0; i < userMenfessList.size(); i++) {
                    Menfess m = userMenfessList.get(i);
                    if ((m.getWaktu().isBefore(LocalDateTime.now()) || m.getWaktu().isEqual(LocalDateTime.now())) && !m.isHidden()) {
                        userPublicMenfess.add(m);
                        userPublicMenfessIndices.add(i); 
                    }
                }
                
                if (!userPublicMenfess.isEmpty()) {
                    publicMenfessPerUser.put(user.getUsername(), userPublicMenfess);
                    publicMenfessOriginalIndicesPerUser.put(user.getUsername(), userPublicMenfessIndices);
                }
            }
        }

        if (publicMenfessPerUser.isEmpty()) {
            System.out.println("Tidak ada menfess publik yang sudah terkirim atau semua sudah tersembunyi.");
            return;
        }

        for (Map.Entry<String, List<Menfess>> entry : publicMenfessPerUser.entrySet()) {
            String username = entry.getKey();
            List<Menfess> userMenfessList = entry.getValue(); 
            List<Integer> userOriginalIndices = publicMenfessOriginalIndicesPerUser.get(username); 

            for (int i = 0; i < userMenfessList.size(); i++) {
                Menfess menfess = userMenfessList.get(i);
                int originalIndex = userOriginalIndices.get(i); 
                String displayedMessage = menfess.getPesan();
                
                String tipeFessDisplay = menfess.getTipeFess();
                if (menfess instanceof ConfessFess) {
                    displayedMessage = "\"" + displayedMessage + "\"";
                } else if (menfess instanceof PromosiFess) {
                    tipeFessDisplay = "Promosi"; 
                } else if (menfess instanceof CurhatFess) {
                    tipeFessDisplay = "Curhat";
                }

                System.out.printf("[%s] [%d] [%s) oleh %s: %s\n", 
                                  username, originalIndex, tipeFessDisplay, username, displayedMessage);
            }
        }
        
        System.out.print("Masukkan username pemilik fess: ");
        String targetUsername = scanner.nextLine();
        User targetUser = findUserByUsername(targetUsername);

        if (targetUser == null) {
            System.out.println("Pengguna dengan username " + targetUsername + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan index fess yang ingin disembunyikan: ");
        int indexToHide = scanner.nextInt();
        scanner.nextLine();

        // Cari menfess dengan indeks asli yang diberikan oleh user
        List<Menfess> targetUserAllMenfess = targetUser.getMenfess();
        if (indexToHide < 0 || indexToHide >= targetUserAllMenfess.size()) {
            System.out.println("Indeks fess tidak valid.");
            return;
        }

        Menfess menfessToHide = targetUserAllMenfess.get(indexToHide);
        
        // Cek jika menfess sudah tersembunyi atau belum terkirim
        if (menfessToHide.isHidden()) {
            System.out.println("Fess ini sudah tersembunyi.");
        } else if (menfessToHide.getWaktu().isAfter(LocalDateTime.now())) {
            System.out.println("Fess ini belum terkirim.");
        }
        else {
            menfessToHide.hide(); 
            System.out.println("Fess berhasil disembunyikan.");
        }
    }

    private static void tampilkanKembaliMenfessAdmin(Admin adminUser) {
        System.out.println("\n== Semua Menfess yang Sudah Tersembunyi ==");
        
        Map<String, List<Menfess>> hiddenMenfessPerUser = new LinkedHashMap<>();
        Map<String, List<Integer>> hiddenMenfessOriginalIndicesPerUser = new LinkedHashMap<>(); 

        for (User user : daftarUser) {
            List<Menfess> userMenfessList = user.getMenfess();
            if (userMenfessList != null) {
                List<Menfess> userHiddenMenfess = new ArrayList<>();
                List<Integer> userHiddenMenfessIndices = new ArrayList<>();

                for (int i = 0; i < userMenfessList.size(); i++) {
                    Menfess m = userMenfessList.get(i);
                    if (m.isHidden() && (m.getWaktu().isBefore(LocalDateTime.now()) || m.getWaktu().isEqual(LocalDateTime.now()))) {
                        userHiddenMenfess.add(m);
                        userHiddenMenfessIndices.add(i); 
                    }
                }
                
                if (!userHiddenMenfess.isEmpty()) {
                    hiddenMenfessPerUser.put(user.getUsername(), userHiddenMenfess);
                    hiddenMenfessOriginalIndicesPerUser.put(user.getUsername(), userHiddenMenfessIndices);
                }
            }
        }

        if (hiddenMenfessPerUser.isEmpty()) {
            System.out.println("Tidak ada menfess yang tersembunyi.");
            return;
        }

        for (Map.Entry<String, List<Menfess>> entry : hiddenMenfessPerUser.entrySet()) {
            String username = entry.getKey();
            List<Menfess> userMenfessList = entry.getValue();
            List<Integer> userOriginalIndices = hiddenMenfessOriginalIndicesPerUser.get(username); 
            
            for (int i = 0; i < userMenfessList.size(); i++) {
                Menfess menfess = userMenfessList.get(i);
                int originalIndex = userOriginalIndices.get(i); 
                String displayedMessage = menfess.getPesan();
                String tipeFessDisplay = menfess.getTipeFess();

                if (menfess instanceof ConfessFess) {
                    displayedMessage = "\"" + displayedMessage + "\"";
                } else if (menfess instanceof PromosiFess) {
                    tipeFessDisplay = "Promosi";
                } else if (menfess instanceof CurhatFess) {
                    tipeFessDisplay = "Curhat";
                }

                System.out.printf("[%s] [%d] [%s) oleh %s: %s\n", 
                                  username, originalIndex, tipeFessDisplay, username, displayedMessage);
            }
        }

        System.out.print("Masukkan username pemilik fess: ");
        String targetUsername = scanner.nextLine();
        User targetUser = findUserByUsername(targetUsername);

        if (targetUser == null) {
            System.out.println("Pengguna dengan username " + targetUsername + " tidak ditemukan.");
            return;
        }

        System.out.print("Masukkan index fess yang ingin di-unhide: ");
        int indexToUnhide = scanner.nextInt();
        scanner.nextLine();

        // Cari menfess dengan indeks asli yang diberikan oleh user
        List<Menfess> targetUserAllMenfess = targetUser.getMenfess();
        if (indexToUnhide < 0 || indexToUnhide >= targetUserAllMenfess.size()) {
            System.out.println("Indeks fess tidak valid.");
            return;
        }

        Menfess menfessToUnhide = targetUserAllMenfess.get(indexToUnhide);
        
        // Cek jika menfess sudah tidak tersembunyi atau belum terkirim
        if (!menfessToUnhide.isHidden()) {
            System.out.println("Fess ini sudah tidak tersembunyi (public).");
        } else if (menfessToUnhide.getWaktu().isAfter(LocalDateTime.now())) {
            System.out.println("Fess ini belum terkirim.");
        }
        else {
            menfessToUnhide.unhide();
            System.out.println("Fess berhasil ditampilkan kembali.");
        }
    }

    private static void hapusAkunAdmin() {
        System.out.print("Masukkan akun yang ingin dihapus: ");
        String usernameToDelete = scanner.nextLine();

        User userToDelete = null;
        for (int i = 0; i < daftarUser.size(); i++) {
            if (daftarUser.get(i).getUsername().equalsIgnoreCase(usernameToDelete)) {
                userToDelete = daftarUser.get(i);
                break;
            }
        }

        if (userToDelete == null) {
            System.out.println("Akun dengan username " + usernameToDelete + " tidak ditemukan.");
            return;
        }

        
        final User finalUserToDelete = userToDelete; 

        for (User user : daftarUser) {
            user.removeFollower(finalUserToDelete);
            user.removeFollowing(finalUserToDelete);
        }

    
        for (User user : daftarUser) {
            List<Menfess> userMenfess = user.getMenfess();
            userMenfess.removeIf(menfess -> menfess instanceof ConfessFess && 
                                             ((ConfessFess) menfess).getPenerima().equalsIgnoreCase(usernameToDelete));
            userMenfess.removeIf(menfess -> menfess instanceof ReplyFess && 
                                             (menfess.getUser().equals(finalUserToDelete) || ((ReplyFess) menfess).getOriginalConfessionSender().equals(finalUserToDelete)));
        }


        daftarUser.remove(finalUserToDelete);

        System.out.println("Akun berhasil terhapus");
        if (currentUser != null && currentUser.equals(finalUserToDelete)) {
            currentUser = null;
            System.out.println("Anda telah logout karena akun Anda dihapus.");
        }
    }

    private static void followPenggunaLainCosmic(Cosmic cosmicUser) {
        System.out.print("\nBerapa kali akan dilakukan follow user? ");
        int jumlahFollow = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < jumlahFollow; i++) {
            System.out.print("Masukkan username " + (i + 1) + " yang ingin anda follow: ");
            String targetUsername = scanner.nextLine();

            if (targetUsername.equals(cosmicUser.getUsername())) {
                System.out.println("Tidak bisa mem-follow diri sendiri! Silakan masukkan ulang");
                i--;
                continue;
            }

            User targetUser = findUserByUsername(targetUsername);
            if (targetUser == null) {
                System.out.println("Ada username yang tidak ditemukan! Silakan masukkan ulang");
                i--;
                continue;
            }

            if (!cosmicUser.addFollowing(targetUser)) {
                System.out.println("Anda sudah mem-follow " + targetUsername + ".");
                i--;
                continue;
            }
            targetUser.addFollower(cosmicUser);

            System.out.println("Anda berhasil mem-follow " + targetUsername + "!");
        }
    }

    private static void mengirimMenfessCosmic(Cosmic cosmicUser) {
        System.out.print("\nBerapa fess yang ingin dijadwalkan? (maks 5): ");
        int jumlahFess = scanner.nextInt();
        scanner.nextLine();

        if (jumlahFess > 5) {
            System.out.println("Maksimal 5 fess saja yang bisa dijadwalkan.");
            jumlahFess = 5;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();

        ArrayList<Menfess> scheduledMenfess = new ArrayList<>();

        for (int i = 0; i < jumlahFess; i++) {
            System.out.print("Masukkan delay fess #" + (i + 1) + " (detik): ");
            int delay = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Masukkan pesan fess #" + (i + 1) + ": ");
            String pesan = scanner.nextLine();

            String tipeFess;
            while (true) {
                System.out.print("Masukkan tipe fess (confession/promo/curhat): ");
                tipeFess = scanner.nextLine().toLowerCase();
                if (tipeFess.equals("confession") || tipeFess.equals("promo") || tipeFess.equals("curhat")) {
                    break;
                } else {
                    System.out.println("Tipe fess tidak valid. Silakan masukkan 'confession', 'promo', atau 'curhat'.");
                }
            }

            LocalDateTime waktuKirim = now.plusSeconds(delay);
            String idMenfess = "FESS" + System.currentTimeMillis() + i;

            Menfess newMenfess = null;
            String penerimaConfession = null;

            switch (tipeFess) {
                case "confession":
                    while (true) {
                        System.out.print("Isi username penerima menfess: ");
                        penerimaConfession = scanner.nextLine();
                        if (findUserByUsername(penerimaConfession) != null) {
                            newMenfess = new ConfessFess(cosmicUser, waktuKirim, idMenfess, pesan, penerimaConfession);
                            break;
                        } else {
                            System.out.println("Username penerima tidak ditemukan. Silakan masukkan username yang valid.");
                        }
                    }
                    break;
                case "promo":
                    newMenfess = new PromosiFess(cosmicUser, waktuKirim, idMenfess, "Produk", pesan);
                    break;
                case "curhat":
                    newMenfess = new CurhatFess(cosmicUser, waktuKirim, idMenfess, pesan);
                    break;
            }

            if (newMenfess != null) {
                scheduledMenfess.add(newMenfess);
                cosmicUser.addMenfess(newMenfess);
            }
        }

        Collections.sort(scheduledMenfess, Comparator.comparing(Menfess::getWaktu));

        System.out.println("\nMengurutkan dan mengirimkan fess...");
        for (Menfess fess : scheduledMenfess) {
            System.out.println("Fess akan dikirim pada: " + fess.getWaktu().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            String displayedMessage = fess.getPesan(); 
            if (fess instanceof ConfessFess) {
                displayedMessage = "\"" + displayedMessage + "\"";
                System.out.println("Dengan pesan: " + displayedMessage);
            } else if (fess instanceof PromosiFess) {
                 System.out.println("Dengan pesan: Promosi produk " + ((PromosiFess)fess).getNamaProduk() + ": " + displayedMessage);
            } else { // CurhatFess
                System.out.println("Dengan pesan: " + displayedMessage);
            }
            System.out.println("Tipe: " + fess.getTipeFess().toLowerCase());
            if (fess instanceof ConfessFess) {
                System.out.println("Penerima: " + ((ConfessFess)fess).getPenerima());
            }
            System.out.println();
        }
    }

    private static void melihatDaftarFollowersFollowingCosmic() {
        System.out.println("\nDaftar mutual burhanFess:\n");

        for (User user : daftarUser) {
            System.out.println("== User: " + user.getUsername() + ", ID: " + user.getId() + " ==\n");

            System.out.println("Followers:");
            if (user.getFollowers() != null && !user.getFollowers().isEmpty()) {
                for (User follower : user.getFollowers()) {
                    System.out.println("- " + follower.getUsername());
                }
            } else {
                System.out.println("Pengguna belum di-follow siapapun");
            }
            
            System.out.println("\nFollowing:");
            if (user.getFollowing() != null && !user.getFollowing().isEmpty()) {
                for (User followed : user.getFollowing()) {
                    System.out.println("- " + followed.getUsername());
                }
            } else {
                System.out.println("Pengguna belum mem-follow siapapun");
            }
            System.out.println();
        }
    }


    private static void melihatDaftarMenfessCosmic() {
        System.out.println("\nPilih salah satu daftar menfess!");
        System.out.println("1. Semua Menfess");
        System.out.println("2. Menfess Buat Kamu");
        System.out.println("3. Menfess Balasan Buat Kamu"); // Opsi baru
        System.out.print("Masukkan pilihanmu: ");
        int pilihan = scanner.nextInt();
        scanner.nextLine();

        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Menfess> menfessToDisplay = new ArrayList<>();
        
        if (pilihan == 1) { // Semua Menfess
            System.out.println("\n== Semua Menfess Publik yang Sudah Terkirim ==");
            for (User user : daftarUser) {
                if (user.getMenfess() != null) {
                    for (Menfess menfess : user.getMenfess()) {
                        if (!(menfess instanceof ReplyFess) && !menfess.isHidden() && (menfess.getWaktu().isBefore(currentTime) || menfess.getWaktu().isEqual(currentTime))) {
                            menfessToDisplay.add(menfess);
                        }
                    }
                }
            }

            if (menfessToDisplay.isEmpty()) {
                System.out.println("Tidak ada menfess publik yang sudah terkirim.");
            } else {
                Collections.sort(menfessToDisplay, Comparator.comparing(Menfess::getWaktu));
                for (Menfess menfess : menfessToDisplay) {
                    String senderName = menfess.getUser().getUsername();
                    
                    String tipeFessFormatted = menfess.getTipeFess();

                    if (menfess instanceof ConfessFess) {
                        senderName = "anonim";
                    } 
                    
                    System.out.println("[" + tipeFessFormatted + "] oleh " + senderName + ": " + menfess.displayFess());
                    System.out.println("Waktu kirim: " + menfess.getWaktu().format(formatter));
                    System.out.println();
                }
            }

        } else if (pilihan == 2) { // Menfess Buat Kamu (Confession untuk current user)
            System.out.println("\n== Semua Menfess Confession untuk Kamu ==");
            String currentUsername = currentUser.getUsername();
            

            List<ConfessFess> confessionsForCurrentUser = new ArrayList<>();

            for (User user : daftarUser) {
                if (user.getMenfess() != null) {
                    for (Menfess menfess : user.getMenfess()) {
                        if (menfess instanceof ConfessFess) {
                            ConfessFess confessFess = (ConfessFess) menfess;
                            if (confessFess.getPenerima().equals(currentUsername) && 
                                !confessFess.isHidden() && 
                                (confessFess.getWaktu().isBefore(currentTime) || confessFess.getWaktu().isEqual(currentTime))) {
                                confessionsForCurrentUser.add(confessFess);
                            }
                        }
                    }
                }
            }

            if (confessionsForCurrentUser.isEmpty()) {
                System.out.println("Tidak ada menfess confession yang ditujukan untuk Anda.");
            } else {
                Collections.sort(confessionsForCurrentUser, Comparator.comparing(Menfess::getWaktu));
                for (int i = 0; i < confessionsForCurrentUser.size(); i++) {
                    ConfessFess menfess = confessionsForCurrentUser.get(i);
                    System.out.println("[" + (i + 1) + "][" + menfess.getTipeFess() + "] oleh anonim: \"" + menfess.getPesan() + "\"");
                    System.out.println("Waktu kirim: " + menfess.getWaktu().format(formatter));
                    System.out.println();
                }

                System.out.print("Pilih nomor confession yang ingin dibalas: ");
                int chosenIndex = scanner.nextInt();
                scanner.nextLine();

                if (chosenIndex > 0 && chosenIndex <= confessionsForCurrentUser.size()) {
                    ConfessFess selectedConfession = confessionsForCurrentUser.get(chosenIndex - 1);
                    
                    System.out.print("Tulis balasan kamu: ");
                    String replyMessage = scanner.nextLine();

                    // Pengirim confession asli adalah penerima balasan
                    User originalConfessionSender = selectedConfession.getUser(); 
                    
                    // CurrentUser (Cosmic yang sedang login) adalah pengirim balasan
                    ReplyFess newReply = new ReplyFess(currentUser, originalConfessionSender, selectedConfession, // Meneruskan selectedConfession
                                                       LocalDateTime.now(), 
                                                       "REPLY" + System.currentTimeMillis(), 
                                                       replyMessage);
                    
                    originalConfessionSender.addMenfess(newReply);
                    System.out.println("Balasan berhasil dikirim ke pengirim.");

                } else {
                    System.out.println("Nomor confession tidak valid.");
                }
            }

        } else if (pilihan == 3) { 
            System.out.println("\n== Semua Balasan Confession Buat Kamu =="); // Mengubah judul
            String currentUsername = currentUser.getUsername();

            for (User user : daftarUser) {
                if (user.getMenfess() != null) {
                    for (Menfess menfess : user.getMenfess()) {
                        if (menfess instanceof ReplyFess) {
                            ReplyFess replyFess = (ReplyFess) menfess;
                           
                            if (replyFess.getOriginalConfessionSender().equals(currentUser) && 
                                !replyFess.isHidden() && 
                                (replyFess.getWaktu().isBefore(currentTime) || replyFess.getWaktu().isEqual(currentTime))) {
                                menfessToDisplay.add(replyFess);
                            }
                        }
                    }
                }
            }

            if (menfessToDisplay.isEmpty()) {
                System.out.println("Tidak ada balasan menfess confession untuk Anda.");
            } else {
                Collections.sort(menfessToDisplay, Comparator.comparing(Menfess::getWaktu));
                for (Menfess menfess : menfessToDisplay) {
                    ReplyFess replyFess = (ReplyFess) menfess; 
                    System.out.println("[Balasan untuk Confession: \"" + replyFess.getOriginalConfession().getPesan() + "\"]");
                    System.out.println("oleh " + replyFess.getUser().getRole() + " (penerima): \"" + replyFess.displayFess() + "\"");
                    System.out.println("Waktu balas: " + replyFess.getWaktu().format(formatter)); 
                    System.out.println();
                }
            }

        } else {
            System.out.println("Pilihan tidak valid. Silakan masukkan 1, 2, atau 3.");
        }
    }

    private static void ubahPasswordCosmic(Cosmic cosmicUser) {
        System.out.print("Masukkan username pengguna yang ingin diubah password-nya: ");
        String targetUsername = scanner.nextLine();
        User targetUser = findUserByUsername(targetUsername);

        if (targetUser == null) {
            System.out.println("Pengguna dengan username " + targetUsername + " tidak ditemukan.");
            return;
        }

        if (targetUser.equals(cosmicUser)) {
             cosmicUser.changePassword(targetUser);
        } else {
            System.out.println("Anda tidak memiliki izin untuk mengubah password pengguna lain.");
        }
    }

    private static User findUserByUsername(String username) {
        for (User user : daftarUser) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }
}