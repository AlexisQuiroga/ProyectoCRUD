package com.tuempresa.crudapp.dao;

import com.tuempresa.crudapp.models.Registro;
import com.tuempresa.crudapp.util.MySQLConexion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistroDAO {
    private Connection connection;

    public RegistroDAO() {
        try {
            connection = MySQLConexion.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void crearRegistro(Registro registro) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO registros (nombre) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, registro.getNombre());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                registro.setId(generatedKeys.getInt(1));
            }

            System.out.println("Registro creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Registro> listarRegistros() {
        List<Registro> registros = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM registros")) {

            while (resultSet.next()) {
                Registro registro = new Registro();
                registro.setId(resultSet.getInt("id"));
                registro.setNombre(resultSet.getString("nombre"));
                registros.add(registro);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    public void actualizarRegistro(Registro registro) {
        try (PreparedStatement statement = connection.prepareStatement("UPDATE registros SET nombre=? WHERE id=?")) {
            statement.setString(1, registro.getNombre());
            statement.setInt(2, registro.getId());
            statement.executeUpdate();
            System.out.println("Registro actualizado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarRegistro(int id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM registros WHERE id=?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Registro eliminado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
