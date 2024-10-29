package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Pantalla extends JFrame {

	private final String ruta = "archivo.dat";

	private JButton ButtonAdd;
	private JButton ButtonShow;
<<<<<<< Updated upstream
	private JButton ButtonDelete; //Hacer q el metodo que recoge el checkbox coja la posicion para cuadrar con el del array
	
	private JPanel panel;
	
=======
	private JButton ButtonDelete;

	private ArrayList<Alumno> alumnos = new ArrayList<Alumno>();

>>>>>>> Stashed changes
	/**
	 * Create the frame.
	 */
	public Pantalla() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		initComponents();
	}

	private void initComponents() {

		ButtonAdd = new JButton("Anadir alumno");
		ButtonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionAlta(e);
			}
		});

		ButtonShow = new JButton("Ver alumnos");
		ButtonShow.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionShow(e);
			}
		});

		ButtonDelete = new JButton("Eliminar alumno");
		ButtonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				actionDelete(e);
			}
		});

		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 50));
//		panel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(ButtonAdd);
		add(ButtonDelete);
		add(ButtonShow);

		pack();
	}

	public void actionAlta(ActionEvent e) {
		Alta alta = new Alta(this, true);
		alta.setVisible(true);
	}

	public void actionShow(ActionEvent e) {
		VerAlumnos ver = new VerAlumnos(this, true);
	}

	public void actionDelete(ActionEvent e) {
		Eliminar del = new Eliminar(this, true);
	}

	public void addAlumno(Alumno al) {
		alumnos.add(al);
		GestorBin.add(ruta, al);
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Pantalla frame = new Pantalla();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String getRuta() {
		return ruta;
	}

}
