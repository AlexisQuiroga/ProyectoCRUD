package com.tuempresa.crudapp;

import com.tuempresa.crudapp.models.Registro;
import com.tuempresa.crudapp.service.RegistroService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class RegistroForm extends JFrame {
    private RegistroService registroService = new RegistroService();
    private JTextField nombreTextField;
    private JList<Registro> registrosList;
    private DefaultListModel<Registro> listModel;

    public RegistroForm() {
        setTitle("Aplicación CRUD");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initComponents();
        updateRegistrosList();

        setVisible(true);
    }

    private void initComponents() {
        JPanel panel = new JPanel(new BorderLayout());

        // Panel de entrada de datos
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreTextField = new JTextField(15);
        JButton crearButton = new JButton("Crear");
        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearRegistro();
            }
        });

        inputPanel.add(nombreLabel);
        inputPanel.add(nombreTextField);
        inputPanel.add(crearButton);

        // Lista de registros
        listModel = new DefaultListModel<>();
        registrosList = new JList<>(listModel);

        // Panel de acciones
        JPanel actionsPanel = new JPanel(new FlowLayout());
        JButton actualizarButton = new JButton("Actualizar");
        JButton borrarButton = new JButton("Borrar");

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarRegistro();
            }
        });

        borrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                borrarRegistro();
            }
        });

        actionsPanel.add(actualizarButton);
        actionsPanel.add(borrarButton);

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(registrosList), BorderLayout.CENTER);
        panel.add(actionsPanel, BorderLayout.SOUTH);

        add(panel);
    }

    private void updateRegistrosList() {
        listModel.clear();
        List<Registro> registros = registroService.listarRegistros();
        for (Registro registro : registros) {
            listModel.addElement(registro);
        }
    }

    private void crearRegistro() {
        String nombre = nombreTextField.getText().trim();
        Registro nuevoRegistro = new Registro(nombre);
        registroService.crearRegistro(nuevoRegistro);
        updateRegistrosList();
        limpiarCampos();
    }

    private void actualizarRegistro() {
        Registro seleccionado = registrosList.getSelectedValue();
        if (seleccionado != null) {
            String nuevoNombre = JOptionPane.showInputDialog("Nuevo nombre para " + seleccionado.getNombre() + ":");
            if (nuevoNombre != null) {
                seleccionado.setNombre(nuevoNombre);
                registroService.actualizarRegistro(seleccionado);
                updateRegistrosList();
            }
        }
    }

    private void borrarRegistro() {
        Registro seleccionado = registrosList.getSelectedValue();
        if (seleccionado != null) {
            int confirmacion = JOptionPane.showConfirmDialog(this, "¿Estás seguro de borrar el registro?", "Confirmación", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                registroService.borrarRegistro(seleccionado.getId());
                updateRegistrosList();
            }
        }
    }

    private void limpiarCampos() {
        nombreTextField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new RegistroForm();
            }
        });
    }
}
