package com.tuempresa.crudapp.service;

import com.tuempresa.crudapp.dao.RegistroDAO;
import com.tuempresa.crudapp.models.Registro;

import java.util.List;

public class RegistroService {
    private RegistroDAO registroDAO = new RegistroDAO();

    public void crearRegistro(Registro registro) {
        if (registro.getNombre() != null && !registro.getNombre().isEmpty()) {
            registroDAO.crearRegistro(registro);
        } else {
            System.out.println("Nombre de registro no válido.");
        }
    }

    public List<Registro> listarRegistros() {
        return registroDAO.listarRegistros();
    }

    public void actualizarRegistro(Registro registro) {
        if (registro.getId() > 0 && registro.getNombre() != null && !registro.getNombre().isEmpty()) {
            registroDAO.actualizarRegistro(registro);
        } else {
            System.out.println("Datos de registro no válidos para actualizar.");
        }
    }

    public void borrarRegistro(int id) {
        if (id > 0) {
            registroDAO.borrarRegistro(id);
        } else {
            System.out.println("ID de registro no válido para eliminar.");
        }
    }
}
