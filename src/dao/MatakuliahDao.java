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
import siak.Matakuliah;
/**
 *
 * @author ades
 */
public class MatakuliahDao {
    private PreparedStatement insertStatement;
    private PreparedStatement updateStatement;
    private PreparedStatement deleteStatement;
    private PreparedStatement getAllStatement;
    private PreparedStatement getByIdStatement;
    private Connection connection;
    
    private final String insertQuery = "insert into matkul (kode_mk,nama_mk,sks)"
            + "values (?,?,?)";
    private final String updateQuery = "update matkul set nama_mk=?,sks=? where kode_mk=?";
    private final String deleteQuery = "delete from matkul where kode_mk=?";
    private final String getByIdQuery = "select from matkul where kode_mk=?";
    private final String getAllQuery = "select * from matkul";
    
    public void setConnection(Connection connection) throws SQLException {
        this.connection = connection;
        insertStatement = this.connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS);
        updateStatement = this.connection.prepareStatement(updateQuery);
        deleteStatement = this.connection.prepareStatement(deleteQuery);
        getAllStatement = this.connection.prepareStatement(getAllQuery);
    }
    public Matakuliah save(Matakuliah matakuliah) throws SQLException{
        insertStatement.setString(1, matakuliah.getKode_mk());
        insertStatement.setString(2, matakuliah.getNama_mk());
        insertStatement.setInt(3, matakuliah.getSks());
        insertStatement.executeUpdate();
        return matakuliah;
    }
    
    public Matakuliah update(Matakuliah matakuliah) throws SQLException {
        updateStatement.setString(1, matakuliah.getNama_mk());
        updateStatement.setInt(2, matakuliah.getSks());
        updateStatement.setString(3, matakuliah.getKode_mk());
        updateStatement.executeUpdate();
        return matakuliah;
    }
    
    public Matakuliah delete(Matakuliah matakuliah) throws SQLException {
        deleteStatement.setString(1, matakuliah.getKode_mk());
        deleteStatement.executeUpdate();
        return matakuliah;
    }
    public Matakuliah getByKode_mk(String kode_mk) throws SQLException{
        getByIdStatement.setString(1, kode_mk);
        ResultSet rs = getByIdStatement.executeQuery();
        if (rs.next()) {
            Matakuliah matakuliah = new Matakuliah();
            matakuliah.setKode_mk(rs.getString("kode_mk"));
            matakuliah.setNama_mk(rs.getString("nama_mk"));
            matakuliah.setSks(rs.getInt("sks"));
            return matakuliah;
        }
        return null;
    }
    public List<Matakuliah> getAll() throws SQLException{
        List<Matakuliah> matakuliahR = new ArrayList<>();
        ResultSet rs = getAllStatement.executeQuery();
        while(rs.next()){
            Matakuliah matakuliah = new Matakuliah();
            matakuliah.setKode_mk(rs.getString("kode_mk"));
            matakuliah.setNama_mk(rs.getString("nama_mk"));
            matakuliah.setSks(rs.getInt("sks"));
            matakuliahR.add(matakuliah);
        }
        return matakuliahR;
    }
    
}
