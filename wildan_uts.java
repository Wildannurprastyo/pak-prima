import java.util.ArrayList; // Import kelas ArrayList untuk membuat list dinamis
import java.util.Collections; // Import kelas Collections untuk mengurutkan ArrayList
import java.util.Comparator; // Import kelas Comparator untuk membandingkan objek
import java.util.Scanner; // Import kelas Scanner untuk membaca input dari pengguna

public class UTS_StrukturData {
    private static ArrayList<Patient> patientQueue = new ArrayList<>(); // ArrayList untuk menyimpan objek Patient (antrian pasien)
    private static Scanner scanner = new Scanner(System.in); // Scanner untuk membaca input dari konsol

    public static void main(String[] args) {
        boolean running = true; // Variabel untuk mengontrol loop utama program

        System.out.println("Welcome to Hospital Queue Management System"); // Menampilkan pesan selamat datang

        while (running) { // Loop utama program
            displayMenu(); // Menampilkan menu pilihan
            int choice = getValidIntInput("Enter your choice: "); // Membaca pilihan pengguna

            switch (choice) { // Memproses pilihan pengguna
                case 1:
                    addPatient(); // Menambahkan pasien baru
                    break;
                case 2:
                    serveNextPatient(); // Melayani pasien berikutnya
                    break;
                case 3:
                    displayQueue(); // Menampilkan antrian
                    break;
                case 4:
                    updatePriority(); // Memperbarui prioritas pasien
                    break;
                case 5:
                    searchPatient(); // Mencari pasien
                    break;
                case 6:
                    System.out.println("Thank you for using Hospital Queue Management System. Goodbye!"); // Menampilkan pesan perpisahan
                    running = false; // Mengakhiri loop utama
                    break;
                default:
                    System.out.println("Invalid choice. Please try again."); // Menampilkan pesan kesalahan jika pilihan tidak valid
            }
        }

        scanner.close(); // Menutup Scanner
    }

    private static void displayMenu() { // Metode untuk menampilkan menu pilihan
        System.out.println("\n===== HOSPITAL QUEUE SYSTEM =====");
        System.out.println("1. Add a new patient to the queue");
        System.out.println("2. Serve next patient");
        System.out.println("3. Display current queue");
        System.out.println("4. Update patient priority");
        System.out.println("5. Search for a patient");
        System.out.println("6. Exit");
        System.out.println("=================================");
    }

    private static void addPatient() { // Metode untuk menambahkan pasien baru ke antrian
        String name = getValidStringInput("Enter patient name: "); // Membaca nama pasien
        int age = getValidIntInput("Enter patient age: "); // Membaca umur pasien
        String condition = getValidStringInput("Enter patient condition: "); // Membaca kondisi pasien
        int priority = getValidIntInRange("Enter patient priority (1-5, 1 being critical): ", 1, 5); // Membaca prioritas pasien (1-5)
        Patient patient = new Patient(name, age, condition, priority); // Membuat objek Patient
        patientQueue.add(patient); // Menambahkan objek Patient ke antrian
        System.out.println("Patient added successfully!"); // Menampilkan pesan konfirmasi
        Collections.sort(patientQueue, Comparator.comparingInt(Patient::getPriority)); // Mengurutkan antrian berdasarkan prioritas
    }

    private static void serveNextPatient() { // Metode untuk melayani pasien berikutnya
        if (patientQueue.isEmpty()) { // Memeriksa apakah antrian kosong
            System.out.println("The queue is empty."); // Menampilkan pesan jika antrian kosong
            return; // Keluar dari metode
        }
        Patient patient = patientQueue.remove(0); // Mengambil dan menghapus pasien pertama dari antrian
        System.out.println("Serving next patient: " + patient.getName()); // Menampilkan nama pasien yang dilayani
        System.out.println("Patient details: Age - " + patient.getAge() + ", Condition - " + patient.getCondition() + ", Priority - " + getPriorityText(patient.getPriority())); // Menampilkan detail pasien
    }

    private static void displayQueue() { // Metode untuk menampilkan antrian pasien
        if (patientQueue.isEmpty()) { // Memeriksa apakah antrian kosong
            System.out.println("The queue is empty."); // Menampilkan pesan jika antrian kosong
            return; // Keluar dari metode
        }
        System.out.println("Current Queue:"); // Menampilkan judul antrian
        for (int i = 0; i < patientQueue.size(); i++) { // Mengulang setiap pasien dalam antrian
            Patient patient = patientQueue.get(i); // Mengambil objek Patient
            System.out.println((i + 1) + ". " + patient.getName() + " - " + getPriorityText(patient.getPriority())); // Menampilkan nomor urut, nama, dan prioritas pasien
        }
    }

    private static void updatePriority() { // Metode untuk memperbarui prioritas pasien
        if (patientQueue.isEmpty()) { // Memeriksa apakah antrian kosong
            System.out.println("The queue is empty."); // Menampilkan pesan jika antrian kosong
            return; // Keluar dari metode
        }
        displayQueue(); // Menampilkan antrian
        int patientIndex = getValidIntInRange("Enter the number of the patient to update: ", 1, patientQueue.size()) - 1; // Membaca nomor pasien yang akan diperbarui prioritasnya
        int newPriority = getValidIntInRange("Enter new priority (1-5): ", 1, 5); // Membaca prioritas baru
        patientQueue.get(patientIndex).setPriority(newPriority); // Memperbarui prioritas pasien
        Collections.sort(patientQueue, Comparator.comparingInt(Patient::getPriority)); // Mengurutkan antrian berdasarkan prioritas
        System.out.println("Priority updated successfully!"); // Menampilkan pesan konfirmasi
    }

    private static void searchPatient() { // Metode untuk mencari pasien berdasarkan nama
        if (patientQueue.isEmpty()) { // Memeriksa apakah antrian kosong
            System.out.println("The queue is empty."); // Menampilkan pesan jika antrian kosong
            return; // Keluar dari metode
        }
        String name = getValidStringInput("Enter the name of the patient to search for: "); // Membaca nama pasien yang akan dicari
        boolean found = false; // Variabel untuk menandai apakah pasien ditemukan
        for (Patient patient : patientQueue) { // Mengulang setiap pasien dalam antrian
            if (patient.getName().equalsIgnoreCase(name)) { // Memeriksa apakah nama pasien cocok (tanpa memperhatikan huruf besar/kecil)
                System.out.println("Patient found: " + patient.getName()); // Menampilkan pesan jika pasien ditemukan
                System.out.println("Patient details: Age - " + patient.getAge() + ", Condition - " + patient.getCondition() + ", Priority - " + getPriorityText(patient.getPriority())); // Menampilkan detail pasien
                found = true; // Mengubah variabel found menjadi true
                break; // Keluar dari loop
            }
        }
        if (!found) { // Memeriksa apakah pasien tidak ditemukan
            System.out.println("Patient not found."); // Menampilkan pesan jika pasien tidak ditemukan
        }
    }

    private static String getPriorityText(int priority) { // Metode untuk mengubah nilai integer prioritas menjadi teks deskripsi
        switch (priority) { // Menggunakan switch-case untuk menentukan teks deskripsi berdasarkan nilai prioritas
            case 1:
                return "1-Critical";
            case 2:
                return "2-Urgent";
            case 3:
                return "3-High";
            case 4:
                return "4-Medium";
            case 5:
                return "5-Non-urgent";
            default:
                return "Unknown";
        }
    }

    private static int getValidIntInput(String prompt) { // Metode untuk membaca input integer dari pengguna dan menangani kesalahan input
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = Integer.parseInt(scanner.nextLine().trim());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
        return value;
    }

    private static int getValidIntInRange(String prompt, int min, int max) { // Metode untuk membaca input integer dalam rentang tertentu dari pengguna dan menangani kesalahan input
        int value;
        while (true) {
            value = getValidIntInput(prompt);
            if (value >= min && value <= max) {
                break;
            }
            System.out.println("Please enter a value between " + min + " and " + max + ".");
        }
        return value;
    }

    private static String getValidStringInput(String prompt) { // Metode untuk membaca input string dari pengguna dan menangani input kosong
        String value;
        while (true) {
            System.out.print(prompt);
            value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                break;
            }
            System.out.println("Input cannot be empty. Please try again.");
        }
        return value;
    }
}

class Patient { // Kelas untuk merepresentasikan pasien
    private String name; // Nama pasien
    private int age; // Umur pasien
    private String condition; // Kondisi pasien
    private int priority; // Prioritas pasien (1-5)

    public Patient(String name, int age, String condition, int priority) { // Constructor kelas Patient
        this.name = name;
        this.age = age;
        this.condition = condition;
        this.priority = priority;
    }

    public String getName() { // Getter untuk nama pasien
        return name;
    }

    public int getAge() { // Getter untuk umur pasien
        return age;
    }

    public String getCondition() { // Getter untuk kondisi pasien
        return condition;
    }

    public int getPriority() { // Getter untuk prioritas pasien
        return priority;
    }

    public void setPriority(int priority) { // Setter untuk prioritas pasien
        this.priority = priority;
    }
}