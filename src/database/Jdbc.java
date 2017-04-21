/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;
import dao.MahasiswaDao;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;
import siak.Mahasiswa;
import siak.Matakuliah;
import siak.Nilai;
import dao.MatakuliahDao;
import dao.NilaiDao;

/**
 *
 * @author ades
 */
public class Jdbc {
    private MahasiswaDao mahasiswaDao;
    private Connection connection;
    private MatakuliahDao matakuliahDao;
    private NilaiDao nilaiDao;
    public void setDataSource(DataSource dataSource){
        try {
            connection = dataSource.getConnection();
            mahasiswaDao = new MahasiswaDao();
            mahasiswaDao.setConnection(connection);
            matakuliahDao = new MatakuliahDao();
            matakuliahDao.setConnection(connection);
            nilaiDao = new NilaiDao();
            nilaiDao.setConnection(connection); 
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public Mahasiswa save(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.save(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    public Mahasiswa update(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.update(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    public Mahasiswa delete(Mahasiswa mahasiswa) {
        try {
            connection.setAutoCommit(false);
            mahasiswaDao.delete(mahasiswa);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return mahasiswa;
    }
    public Mahasiswa getMahasiswa(int npm) {
        try {
            return mahasiswaDao.getByNpm(npm);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Mahasiswa> getAllMahasiswa() {
        try {
            return mahasiswaDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Matakuliah save(Matakuliah matakuliah) {
        try {
            connection.setAutoCommit(false);
            matakuliahDao.save(matakuliah);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matakuliah;
    }
    public Matakuliah update(Matakuliah matakuliah) {
        try {
            connection.setAutoCommit(false);
            matakuliahDao.update(matakuliah);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matakuliah;
    }
    public Matakuliah delete(Matakuliah matakuliah) {
        try {
            connection.setAutoCommit(false);
            matakuliahDao.delete(matakuliah);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return matakuliah;
    }
    public Matakuliah getMatakuliah(String kode_mk) {
        try {
            return matakuliahDao.getByKode_mk(kode_mk);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Matakuliah> getAllMatakuliah() {
        try {
            return matakuliahDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
     public Nilai save(Nilai nilai) {
        try {
            connection.setAutoCommit(false);
            nilaiDao.save(nilai);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nilai;
    }
    public Nilai update(Nilai nilai) {
        try {
            connection.setAutoCommit(false);
            nilaiDao.update(nilai);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nilai;
    }
    public Nilai delete(Nilai nilai) {
        try {
            connection.setAutoCommit(false);
            nilaiDao.delete(nilai);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException ex) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return nilai;
    }
    public Nilai getNilai(int id) {
        try {
            return nilaiDao.getById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public List<Nilai> getAllNilai() {
        try {
            return nilaiDao.getAll();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    }
