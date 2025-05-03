package teamplo_program_level_objective_setting;

import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PLOOperations {
    private static Statement stmt;

    public PLOOperations() {
        stmt = DBConnection.getStatement();
    }

    public void TeamPLO_plo_retrieve(DefaultTableModel tableModel) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM program_lo;");
            tableModel.setRowCount(0); // Clear table

            while (rs.next()) {
                Object[] row = {
                    rs.getInt("ID"),
                    rs.getString("prog_lo_code"),
                    rs.getString("prog_lo_no"),
                    rs.getString("prog_lo_name"),
                    rs.getString("prog_lo_details")
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading data: " + e.getMessage());
        }
    }

    public boolean TeamPLO_plo_create(String code, String no, String name, String details) {
        try {
            String sql = "INSERT INTO program_lo (prog_lo_code, prog_lo_no, prog_lo_name, prog_lo_details) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.setString(2, no);
            pstmt.setString(3, name);
            pstmt.setString(4, details);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean TeamPLO_plo_update(int id, String code, String no, String name, String details) {
        try {
            String sql = "UPDATE program_lo SET prog_lo_code=?, prog_lo_no=?, prog_lo_name=?, prog_lo_details=? WHERE ID=?";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, code);
            pstmt.setString(2, no);
            pstmt.setString(3, name);
            pstmt.setString(4, details);
            pstmt.setInt(5, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean TeamPLO_plo_delete(int id) {
        try {
            String sql = "DELETE FROM program_lo WHERE ID=?";
            PreparedStatement pstmt = DBConnection.getConnection().prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
} 