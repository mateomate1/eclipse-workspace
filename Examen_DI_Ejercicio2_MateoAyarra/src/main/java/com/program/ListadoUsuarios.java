package com.program;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ListadoUsuarios extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private List<Usuario> usuarios;
	private Main pantalla;

	private JTable tabla;

	public ListadoUsuarios(Frame owner, boolean modal) {
		super(owner, modal);
		pantalla = (Main) owner;
		usuarios = GestorBin.leer(pantalla.getRuta());
		initComponents();
	}

	public void initComponents() {
		String[] columnNames = { "Usuario"};

		// Crear un modelo de tabla
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		for (Usuario usuario : usuarios) {
			model.addRow(new Object[] {usuario.getUsername() });
		}

		tabla = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(tabla);
		scrollPane.setPreferredSize(new Dimension(60, 200));
		JButton closeButton = new JButton("Cerrar");
		closeButton.addActionListener(e -> dispose());

		// Configurar el layout
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(closeButton, BorderLayout.SOUTH);

		pack(); // Ajustar tamaño
		setLocationRelativeTo(pantalla); // Centrar en el owner
		setVisible(true); // Hacer visible el diálogo
	}

}
