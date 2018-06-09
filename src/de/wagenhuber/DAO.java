package de.wagenhuber;

import java.sql.*;

public class DAO {

    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private MyTableModel tableModel;

    public DAO(MyTableModel tableModel) throws SQLException {

        this.tableModel = tableModel;
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/autos", "root2", "mysql");
        statement = connection.createStatement();
        preparedStatement = connection.prepareStatement("select eigentuemer, kennzeichen, model, ps from auto ");
    }

     public void getAllAutos() throws SQLException {
         String sql = "select eigentuemer, kennzeichen, model, ps from auto";
         ResultSet resultSet = statement.executeQuery(sql);
         while (resultSet.next()) {
             String eigentuemer = resultSet.getString(0);
             String kennzeichen = resultSet.getString(1);
             String model = resultSet.getString(2);
             int ps = resultSet.getInt(3);
             tableModel.addAutoToList(new Auto(eigentuemer,kennzeichen,model,ps));
             tableModel.fireTableDataChanged();
         }

     }



}
