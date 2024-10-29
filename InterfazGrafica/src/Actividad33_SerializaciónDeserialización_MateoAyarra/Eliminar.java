package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class Eliminar extends JDialog {

	private static final long serialVersionUID = 1L;
	private List<Alumno> alumnos;
	private JList<Alumno> listaAlumnos;
	private DefaultListModel<Alumno> modeloLista;
	private Pantalla pantalla;
	
	private JPanel panel;
	
	private JButton buttonDelete, buttonVaciar;

	public Eliminar(Frame owner, boolean modal) {
		super(owner, modal);
		pantalla = (Pantalla) owner;
		initComponents();
	}

	private void initComponents() {
		setTitle("Eliminar Alumnos");
		setLayout(new BorderLayout());
		
		alumnos = GestorBin.leer(pantalla.getRuta());
		
		modeloLista = new DefaultListModel<>();
		for (Alumno alumno : alumnos) {
			modeloLista.addElement(alumno);
		}
		
		listaAlumnos = new JList<>(modeloLista);
		listaAlumnos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane = new JScrollPane(listaAlumnos);
		
		buttonDelete = new JButton("Eliminar");
		buttonDelete.addActionListener(this::eliminarAlumno);
		buttonVaciar = new JButton("Vaciar");
		buttonVaciar.addActionListener(this::vaciarAlumnos);
		
		add(scrollPane, BorderLayout.CENTER);
		add(buttonDelete, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(pantalla);
		setVisible(true);
	}

	private void eliminarAlumno(ActionEvent e) {
		Alumno alumnoSeleccionado = listaAlumnos.getSelectedValue();
		if (alumnoSeleccionado != null) {
			// Eliminar el alumno de la lista y del archivo
			modeloLista.removeElement(alumnoSeleccionado);
			alumnos.remove(alumnoSeleccionado);

			// Actualizar el archivo
			GestorBin.escribir(pantalla.getRuta(), alumnos);

			JOptionPane.showMessageDialog(this, "Alumno eliminado correctamente...");
		} else {
			JOptionPane.showMessageDialog(this, "Por favor, selecciona un alumno para eliminar...");
		}
	}
	
	private void vaciarAlumnos(ActionEvent e) {
		if (alumnos.isEmpty()) 
			JOptionPane.showMessageDialog(this, "No hay alumnos que vaciar...");
		else {
			JOptionPane.showMessageDialog(this, "Alumnos eliminados correctamente...");
			GestorBin.vaciar(pantalla.getRuta());			
		}
	}
}
