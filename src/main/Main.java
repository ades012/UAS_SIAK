/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import database.Jdbc;
import java.util.Iterator;
import siak.Mahasiswa;
import siak.Matakuliah;
import siak.Nilai;

/**
 *
 * @author ades
 */
public class Main {
    public static void main(String[] args) {
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUser("root");
        dataSource.setPassword("");
        dataSource.setDatabaseName("siak?serverTimezone=UTC");
        dataSource.setServerName("localhost");

        dataSource.setPortNumber(3306);

        Jdbc database = new Jdbc();
        database.setDataSource(dataSource);
        Scanner in = new Scanner(System.in);
        System.out.println("SIAK");
        Boolean mainmenu = true;
        while (mainmenu) {
            System.out.println("\nSistem Informasi Akademik STMIK AMIK BANDUNG");
            System.out.println("\n1. Data Mahasiswa");
            System.out.println("2. Data Mata Kuliah");
            System.out.println("3. Input Nilai Mahasiswa");
            System.out.print("\nPilih Menu : ");
            String pilihanmenu = in.nextLine();
            switch(Integer.parseInt(pilihanmenu)) {
                case 1:
                    getMenuMahasiswa(database);
                    break;
                case 2:
                    getMenuMatakuliah(database);
                    break;
                case 3:
                    getMenuNilai(database);
                    break;
                default:
                    break;
            }
            
        }
    }
    
    public static void getMenuMahasiswa(Jdbc database) {
    Scanner in = new Scanner(System.in);
        Boolean active = true;
        while(active == true) {
            System.out.println("\nMenu Data Mahasiswa : \n");
            System.out.println("1. Daftar Mahasiswa");
            System.out.println("2. Tambah Data");
            System.out.println("3. Ubah Data");
            System.out.println("4. Hapus Data");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<Mahasiswa> anggotaD = database.getAllMahasiswa();
                        System.out.println("NPM\tNama\t\t\tJurusan\t\t\tAlamat");
                        anggotaD.stream().forEach((anggota) -> {
                            System.out.println(anggota.getNpm()+"\t"+anggota.getNama()+"\t\t\t"+anggota.getJurusan()+"\t\t\t"+anggota.getAlamat());
            });

                    break;
                case "2":
                    System.out.print("NPM : ");
                    String npm = in.nextLine();
                    System.out.print("Nama : ");
                    String nama = in.nextLine();
                    System.out.print("Jurusan : ");
                    String jurusan = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat = in.nextLine();
                    System.out.print("Data sudah benar? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                    Mahasiswa mhs = new Mahasiswa();
                     mhs.setNpm(Integer.parseInt(npm));
                        mhs.setNama(nama);
                        mhs.setJurusan(jurusan);
                        mhs.setAlamat(alamat);
                        database.save(mhs);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan NPM : ");
                    String npm_z = in.nextLine();
                    Mahasiswa mhs_z = database.getMahasiswa(Integer.parseInt(npm_z));
                    if (mhs_z == null) {
                        System.out.println("NPM tidak terdaftar.");
                        break;
                    }
                    System.out.print("Nama : ");                    
                    String nama_z = in.nextLine();
                    System.out.print("Jurusan : ");
                    String jurusan_z = in.nextLine();
                    System.out.print("Alamat : ");
                    String alamat_z = in.nextLine();
                    System.out.print("Data sudah benar? (Y/N) : ");
                    String tambah_z = in.nextLine();
                    if (tambah_z.toLowerCase().equals("y")) {
                        mhs_z.setNpm(Integer.parseInt(npm_z));
                        mhs_z.setNama(nama_z);
                        mhs_z.setJurusan(jurusan_z);
                        mhs_z.setAlamat(alamat_z);
                        database.update(mhs_z);
                    }
                break;
                case "4":
                    System.out.print("Masukkan NPM : ");
                    String npm_b = in.nextLine();
                    Mahasiswa mhs_b = database.getMahasiswa(Integer.parseInt(npm_b));
                    if (mhs_b == null) {
                        System.out.println("Data tidak ditemukan.");
                        break;
                    }
                    System.out.print("Apakah anda yakin? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        database.delete(mhs_b);
                    }
                break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
    public static void getMenuMatakuliah(Jdbc database) {
    Scanner in = new Scanner(System.in);
        Boolean active = true;
        while(active == true) {
            System.out.println("\nData Mata Kuliah : \n");
            System.out.println("1. Daftar Mata Kuliah");
            System.out.println("2. Tambah Data");
            System.out.println("3. Ubah Data");
            System.out.println("4. Hapus Data");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
                    List<Matakuliah> matkulD = database.getAllMatakuliah();
                        System.out.println("Kode Matkul\tNama Matkul\t\t\tSKS");
                        matkulD.stream().forEach((matkul) -> {
                            System.out.println(matkul.getKode_mk()+"\t"+matkul.getNama_mk()+"\t\t\t"+matkul.getSks());
            });

                    break;
                case "2":
                    System.out.print("Kode Mata Kuliah : ");
                    String kode_mk = in.nextLine();
                    System.out.print("Nama Mata Kuliah : ");
                    String nama_mk = in.nextLine();
                    System.out.print("SKS : ");
                    String sks = in.nextLine();
                    System.out.print("Data sudah benar? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                    Matakuliah mtk = new Matakuliah();
                     mtk.setKode_mk(kode_mk);
                        mtk.setNama_mk(nama_mk);
                        mtk.setSks(Integer.parseInt(sks));
                        database.save(mtk);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan Kode Mata Kuliah : ");
                    String kode_mk_z = in.nextLine();
                    Matakuliah mtk_z = database.getMatakuliah(kode_mk_z);
                    if (null != mtk_z) {
                    } else {
                        System.out.println("NPM tidak terdaftar.");
                        break;
            }
                    System.out.print("Nama Mata Kuliah : ");                    
                    String nama_mk_z = in.nextLine();
                    System.out.print("SKS : ");
                    String sks_z = in.nextLine();
                    System.out.print("Apakah Data sudah benar? (Y/N) : ");
                    String tambah_z = in.nextLine();
                    if (tambah_z.toLowerCase().equals("y")) {
                        mtk_z.setKode_mk(kode_mk_z);
                        mtk_z.setNama_mk(nama_mk_z);
                        mtk_z.setSks(Integer.parseInt(sks_z));
                        database.update(mtk_z);
                    }
                break;
                case "4":
                    System.out.print("Masukkan Kode Matkul : ");
                    String kode_mk_b = in.nextLine();
                    Matakuliah mtk_b = database.getMatakuliah(kode_mk_b);
                    if (mtk_b == null) {
                        System.out.println("Data tidak ditemukan.");
                        break;
                    }
                    System.out.print("Apakah anda yakin? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        database.delete(mtk_b);
                    }
                break;
                case "0":
                    active = false;
                    break;
            }
        }
    }
    public static void getMenuNilai(Jdbc database) {
    Scanner in = new Scanner(System.in);
        Boolean active = true;
        while(active == true) {
            System.out.println("\nData Nilai Mahasiswa : \n");
            System.out.println("1. Daftar Nilai");
            System.out.println("2. Tambah Data Nilai");
            System.out.println("3. Ubah Data Nilai");
            System.out.println("4. Hapus Data Nilai");
            System.out.println("\n0. Kembali ke Menu Utama");

            System.out.print("\nPilihan : ");
            String pilih = in.nextLine();
            switch (pilih) {
                case "1":
//                    List<Mahasiswa> mahasiswaD = database.getAllMahasiswa();
//                    List<Matakuliah> matkulD = database.getAllMatakuliah();
                    List<Nilai> nilaiD = database.getAllNilai();
                        System.out.println("Id\tNpm\t\tNama Mahasiswa\t\t\tNama Matakuliah\t\t\tNilai\t\tNilai Akhir");
//                        for (Mahasiswa mahasiswa : mahasiswaD){
                        nilaiD.stream().forEach((nilai) -> {
                            String x = null;
                            if (nilai.getNilai()>=80){
                                x = "A";                                
                            }
                            else if (nilai.getNilai()>=70){
                                x = "B";
                            }
                            else if (nilai.getNilai()>=60){
                                x = "C";
                            }
                            else if (nilai.getNilai()>=60){
                                x = "D";
                            }
                            else x = "E";
                            System.out.println(nilai.getId()+"\t"+nilai.getNpm()+"\t\t"+nilai.getNama()+"\t\t"+nilai.getNama_mk()+"\t\t"
                            + nilai.getNilai()+"\t\t\t"+ x);
            });
                    break;
                    case "2":
                    System.out.print("Id : ");
                    String id = in.nextLine();
                    System.out.print("NPM : ");
                    String npm = in.nextLine();
                    System.out.print("Kode Mata Kuliah : ");
                    String kode_mk = in.nextLine();
                    System.out.print("Nilai : ");
                    String nilai = in.nextLine();
                    System.out.print("Data sudah benar? (Y/N) : ");
                    String tambah = in.nextLine();
                    if (tambah.toLowerCase().equals("y")) {
                    Nilai na = new Nilai();
                     na.setId(Integer.parseInt(id));
                     na.setNpm(Integer.parseInt(npm));
                     na.setKode_mk(kode_mk);
                     na.setNilai(Integer.parseInt(nilai));
                        database.save(na);
                    }
                    break;
                case "3":
                    System.out.print("Masukkan ID Nilai : ");
                    String id_z = in.nextLine();
                    Nilai na_z = database.getNilai(Integer.parseInt(id_z));
                    if (na_z == null) {
                        System.out.println("NPM tidak terdaftar.");
                        break;
                    }
                    System.out.print("NPM : ");                    
                    String npm_z = in.nextLine();
                    System.out.print("Kode Mata Kuliah : ");
                    String kode_mk_z = in.nextLine();
                    System.out.print("Nilai : ");
                    String nilai_z = in.nextLine();
                    System.out.print("Data sudah benar? (Y/N) : ");
                    String tambah_z = in.nextLine();
                    if (tambah_z.toLowerCase().equals("y")) {
                        na_z.setId(Integer.parseInt(id_z));
                        na_z.setNpm(Integer.parseInt(npm_z));
                        na_z.setKode_mk(kode_mk_z);
                        na_z.setNilai(Integer.parseInt(nilai_z));
                        database.update(na_z);
                    }
                break;
                case "4":
                    System.out.print("Masukkan ID Nilai : ");
                    String id_b = in.nextLine();
                    Nilai na_b = database.getNilai(Integer.parseInt(id_b));
                    if (na_b == null) {
                        System.out.println("Data tidak ditemukan.");
                        break;
                    }
                    System.out.print("Apakah anda yakin? (Y/N) : ");
                    String hapus = in.nextLine();
                    if (hapus.toLowerCase().equals("y")) {
                        database.delete(na_b);
                    }
                break;
                    case "0":
                    active = false;
                    break;
        }
    }
    }
}