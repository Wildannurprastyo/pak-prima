import java.util.ArrayList; // Mengimpor kelas ArrayList untuk membuat daftar tugas.
import java.util.Scanner;   // Mengimpor kelas Scanner untuk membaca input dari pengguna.

public class wildan_tugas4 { // Deklarasi kelas utama bernama TodoList2A1.
    private ArrayList<String> todoList; // Mendeklarasikan variabel privat todoList sebagai ArrayList string untuk menyimpan tugas.

    // Constructor to initialize the ArrayList
    public wildan_tugas4() { // Konstruktor kelas TodoList2A1.
        todoList = new ArrayList<>(); // Menginisialisasi todoList sebagai ArrayList kosong.
    }

    // Method to add a task
    public void addTask(String task) { // Metode untuk menambahkan tugas ke daftar.
        todoList.add(task); // Menambahkan tugas (task) ke dalam todoList.
        System.out.println("Task '" + task + "' added."); // Menampilkan pesan konfirmasi bahwa tugas telah ditambahkan.
    }

    // Method to remove a task by name
    public void removeTaskByName(String task) { // Metode untuk menghapus tugas berdasarkan nama.
        if (todoList.remove(task)) { // Mencoba menghapus tugas berdasarkan nama. Mengembalikan true jika berhasil, false jika tidak.
            System.out.println("Task '" + task + "' removed."); // Menampilkan pesan konfirmasi jika penghapusan berhasil.
        } else {
            System.out.println("Task '" + task + "' not found."); // Menampilkan pesan jika tugas tidak ditemukan.
        }
    }

    // Method to remove a task by index
    public void removeTaskByIndex(int index) { // Metode untuk menghapus tugas berdasarkan indeks.
        if (index >= 0 && index < todoList.size()) { // Memeriksa apakah indeks valid.
            String removedTask = todoList.remove(index); // Menghapus tugas pada indeks yang diberikan.
            System.out.println("Task '" + removedTask + "' removed."); // Menampilkan pesan konfirmasi.
        } else {
            System.out.println("Invalid index."); // Menampilkan pesan error jika indeks tidak valid.
        }
    }

    // Method to display all tasks
    public void displayTasks() { // Metode untuk menampilkan semua tugas dalam daftar.
        if (todoList.isEmpty()) { // Memeriksa apakah daftar kosong.
            System.out.println("No tasks in the list."); // Menampilkan pesan jika daftar kosong.
        } else {
            System.out.println("\nTo-Do List:"); // Menampilkan judul daftar tugas.
            for (int i = 0; i < todoList.size(); i++) { // Mengulang setiap elemen dalam todoList.
                System.out.println((i + 1) + ". " + todoList.get(i)); // Menampilkan setiap tugas dengan nomor urut.
            }
        }
    }

       // mencari task berdasarkan index
       public void searchTaskByIndex(int index) {
        boolean chek = false; //  deklarasi dan inisialisasi, apakah index ditemukan
        System.out.print("Hasil search: "); // mencetak tulisan hasil search
        for (int i = 0; i < todoList.size(); i++) { // Iterasi untuk mencari index
            if (index==i) { // menngecek apakah index cocok
                System.out.println(todoList.get(i)); // Menampilkan task pada index tersebut
                chek = true; // mengubah nilai cek menjadi true, berarti index ditemukan
            }
        } 
        if (!chek) { // Jika index tidak ditemukan
            System.out.println("Tidak ditemukan"); // mencetak Pesan kesalahan
        }
    }


    public static void main(String[] args) { // Metode main, titik awal eksekusi program.
        TodoList2A1 app = new TodoList2A1(); // Membuat objek dari kelas TodoList2A (harusnya TodoList2A1). **KESALAHAN: Nama kelas tidak konsisten.**
        Scanner scanner = new Scanner(System.in); // Membuat objek Scanner untuk membaca input dari konsol.
        boolean running = true; // Variabel boolean untuk mengontrol loop utama.

        while (running) { // Loop utama program yang akan terus berjalan sampai running menjadi false.
            System.out.println("\nTo-Do List Application"); // Menampilkan judul aplikasi.
            System.out.println("1. Add Task"); // Menampilkan menu pilihan.
            System.out.println("2. Remove Task By Task Name");
            System.out.println("3. Remove Task By Index");
            System.out.println("4. Search Task By Task Name");
            System.out.println("5. Display Tasks");
            System.out.println("6. Exit"); // Pilihan untuk keluar dari aplikasi.
            System.out.print("Enter your choice: "); // Meminta pengguna memasukkan pilihan.
            int choice = scanner.nextInt(); // Membaca pilihan pengguna.
            scanner.nextLine();  // Membersihkan buffer input.

            switch (choice) { // Pernyataan switch untuk menjalankan kode berdasarkan pilihan pengguna.
                case 1: // Jika pengguna memilih 1.
                    System.out.print("Enter task to add: "); // Prompt untuk menambah task
                    String taskToAdd = scanner.nextLine(); // Membaca input task
                    app.addTask(taskToAdd); // Memanggil metode untuk menambah task
                    break; // keluar dari case

                case 2: // Jika pengguna memilih 2.
                    System.out.print("Enter task to remove: "); // Prompt untuk menghapus berdasarkan nama
                    String taskToRemove = scanner.nextLine(); // Membaca input nama task
                    app.removeTaskByName(taskToRemove); // Memanggil metode untuk menghapus task berdasarkan nama
                    break; // keluar dari case

                case 3: // Jika pengguna memilih 3.
                    System.out.print("Enter the index of the task to remove: "); // Promp untuk menghapus berdasarkan index
                    int indexToRemove = scanner.nextInt(); // Membaca input index
                    app.removeTaskByIndex(indexToRemove - 1); // Mengurangi 1 karena indeks array dimulai dari 0.
                    scanner.nextLine(); // Membersihkan buffer input.
                    break; // keluar dari case

                case 4: // Jika pengguna memilih 4. **KESALAHAN: `searchTask` tidak menerima parameter.**
                    System.out.print("Enter task to search for: "); // Prompt untuk mencari task
                    int taskToSearch = scanner.nextInt(); // Membaca inputan index
                    app.searchTaskByIndex(taskToSearch-1); // Memanggil metode pencarian dengan index
                    break; // keluar dari case

                case 5: // Jika pengguna memilih 5.
                    app.displayTasks(); // Menampilkan daftar task
                    break; // keluar dari case

                case 6: // Jika pengguna memilih 6.
                    running = false; // Menghentikan loop aplikasi
                    System.out.println("Exiting the application..."); // Pesan keluar
                    break; // keluar dari case

                default: // Jika pengguna memasukkan pilihan yang tidak valid.
                    System.out.println("Invalid choice. Please try again."); // Jika yang diinputkan tidak valid
            }
        }

        scanner.close(); // Menutup objek Scanner.
    }
}