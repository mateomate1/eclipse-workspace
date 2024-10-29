package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class VerAlumnos extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private List<Alumno> alumnos;
	private Pantalla pantalla;

	private JTable tabla;

	public VerAlumnos(Frame owner, boolean modal) {
		super(owner, modal);
		pantalla = (Pantalla) owner;
		alumnos = GestorBin.leer(pantalla.getRuta());
		initComponents();
	}

	public void initComponents() {
		String[] columnNames = { "Nombre", "NIA", "Edad" };

		// Crear un modelo de tabla
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		for (Alumno alumno : alumnos) {
			model.addRow(new Object[] { alumno.getNombre(), alumno.getNia(), alumno.getEdad() });
		}

		tabla = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(tabla);
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
