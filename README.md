# DEMOBLAZE AUTOMATION TESTING  
UAS PENGUJIAN PERANGKAT LUNAK (PPL) 2025

Proyek ini merupakan implementasi **Automation Testing** pada website **Demoblaze E-Commerce**  
yang dikembangkan sebagai bagian dari **Ujian Akhir Semester (UAS)** mata kuliah  
**Pengujian Perangkat Lunak (PPL) Tahun 2025**.

Pengujian dilakukan untuk memverifikasi fungsi utama sistem menggunakan  
**Selenium WebDriver** dengan pendekatan **Automation Testing berbasis Page Object Model (POM)**.

## Tujuan Pengujian

- Memastikan fitur utama aplikasi berjalan sesuai kebutuhan
- Mengidentifikasi bug pada proses bisnis e-commerce
- Meningkatkan efisiensi pengujian melalui automation testing
- Membandingkan hasil manual testing dan automation testing

## Fitur yang Diuji

- Login dengan kredensial tidak valid
- Validasi form Contact
- Proses pembelian produk (Complete Purchase Flow)
- Persistensi cart (session handling)
- Validasi checkout dengan cart kosong

## Struktur Project
```bash
demoblaze-automation-testing/
├── src/
│ ├── main/java/com/praktikum/automation/testing
│ │ ├── BasePage.java
│ │ ├── HomePage.java
│ │ ├── LoginPage.java
│ │ ├── ProductPage.java
│ │ ├── CartPage.java
│ │ └── CheckoutPage.java
│ │
│ └── test/java/com/praktikum/automation/testing
│ ├── BaseTest.java
│ ├── TC_AUTH_001_InvalidLoginTest.java
│ ├── TC_CONT_001_ContactValidationTest.java
│ ├── TC_PURCH_001_CompleteFlowTest.java
│ ├── TC_SESS_001_CartPersistenceTest.java
│ └── TC_VALID_001_EmptyCartTest.java
│
├── pom.xml
├── testng.xml
├── .gitignore
└── README.md
```

## Tools dan Teknologi
- **Java 11**
- **Maven** (dependency management & build tool)
- **Selenium WebDriver**
- **TestNG**
- **Page Object Model (POM)**
- **IntelliJ IDEA**

## Cara Menjalankan Automation Test

### Prasyarat
- Java 11 telah terinstal
- Maven telah terinstal
- Web browser Google Chrome
- ChromeDriver sesuai versi browser

### Menjalankan Test
Jalankan perintah berikut pada root project:

```bash
mvn clean test
```

Atau jalankan langsung menggunakan file konfigurasi TestNG:
testng.xml

### Daftar Test Case
1. TC_AUTH_001 - Validasi login menggunakan data tidak valid
2. TC_CONT_001 - Validasi form contact tanpa input nama
3. TC_PURCH_001 - Pengujian proses pembelian produk sampai selesai
4. TC_SESS_001 - Pengujian persistensi data cart setelah reload
5. TC_VALID_001 - Validasi checkout dengan cart kosong

### Hasil Pengujian
Total Test Case : 5
Passed : 3
Failed : 2
Status Aplikasi : Not Ready for Production

### Bug ditemukan pada:
- Validasi form Contact tanpa input nama
- Proses checkout saat cart kosong

### Catatan Penting
- Folder target/ tidak disertakan dalam repository karena merupakan hasil build otomatis.
- Project ini dibuat khusus untuk keperluan akademik (UAS PPL 2025).
- Tidak dilakukan perubahan pada source code website Demoblaze.
- Automation testing membantu meningkatkan konsistensi dan efisiensi pengujian.

### Author
Naura Yaffa Kamila
Program Studi Rekayasa Keamanan Siber
Politeknik Negeri Cilacap
UAS Pengujian Perangkat Lunak – 2025
