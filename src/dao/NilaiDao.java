/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import siak.Nilai;
/**
 *
 * @author ades
 */
public class NilaiDao {
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getByIdStatement;
    private PreparedStatement getAllStatement;
    private Connection connection;
    
    private final String insertQuery = "insert into nilai (id,npm,kode_mk,nilai)"
            + "values (?,?,?,?)";
    private final String updateQuery = "update nilai set npm=?,kode_mk=?,nilai=?"
            + "where id=?";
    private final String deleteQuery = "delete from nilai where id=?";
    private final String getByIdQuery = "select from nilai where id=?";
    private final String getAllQuery =  " select nilai.id,mahasiswa.npm,mahasiswa.nama,matkul.nama_mk,nilai.nilai "
            + "from mahasiswa,matkul,nilai where mahasiswa.npm = nilai.npm and matkul.kode_mk = nilai.kode_mk";
    
    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    public Nilai save(Nilai nilai) throws SQLException{
        insertStatement.setInt(1, nilai.getId());
        insertStatement.setInt(2, nilai.getNpm());
        insertStatement.setString(3, nilai.getKode_mk());
        insertStatement.setInt(4, nilai.getNilai());
        insertStatement.executeUpdate();
        return nilai;   
    }
    public Nilai update(Nilai nilai) throws SQLException{
        updateStatement.setInt(1, nilai.getNpm());
        updateStatement.setString(2, nilai.getKode_mk());
        updateStatement.setInt(3, nilai.getNilai());
        updateStatement.setInt(4, nilai.getId());
        updateStatement.executeUpdate();
        return nilai;   
    }
    public Nilai delete(Nilai nilai) throws SQLException {
        deleteStatement.setInt(1, nilai.getId());
        deleteStatement.executeUpdate();
        return nilai;
    }
     public Nilai getById(int id) throws SQLException{
        getByIdStatement.setInt(1, id);
        ResultSet rs = getByIdStatement.executeQuery();
        if (rs.next()) {
            Nilai nilai = new Nilai();
            nilai.setId(rs.getInt("id"));
            nilai.setNpm(rs.getInt("npm"));
            nilai.setKode_mk(rs.getString("kode_mk"));
            nilai.setNilai(rs.getInt("nilai"));
            return nilai;
        }
        return null;
    }
    public List<Nilai> getAll() throws SQLException{
        List<Nilai> nilaiR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Nilai nilai = new Nilai();
            nilai.setId(rs.getInt("id"));
            nilai.setNpm(rs.getInt("npm"));
//            nilai.setKode_mk(rs.getString("kode_mk"));
            nilai.setNama(rs.getString("nama"));
            nilai.setNama_mk(rs.getString("nama_mk"));
            nilai.setNilai(rs.getInt("nilai"));
            nilaiR.add(nilai);
        }
        return nilaiR;
    }  
}