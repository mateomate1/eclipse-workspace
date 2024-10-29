package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

public class Alta extends JDialog {

	private static final long serialVersionUID = 1L;
	private Pantalla pantalla;

	private JLabel textoNombre;
	private JLabel textoNIA;
	private JLabel textoEdad;

	private JTextField nombre;
	private JTextField NIA;

	private JSpinner edad;

	private JButton alta;

	private JPanel panelAlta, panel;

	public Alta(Frame owner, boolean modal) {
		super(owner, modal);
		pantalla = (Pantalla) owner;
		initComponents();
	}

	public void initComponents() {

		textoEdad = new JLabel();
		textoEdad.setText("Edad:");
		edad = new JSpinner();
		edad.setModel(new SpinnerNumberModel());

		textoNombre = new JLabel();
		textoNombre.setText("Nombre:");
		nombre = new JTextField(10);

		textoNIA = new JLabel();
		textoNIA.setText("NIA:");
		NIA = new JTextField(10);

		alta = new JButton("Anadir");
		alta.setPreferredSize(new Dimension(150, 40)); // Tamaño deseado
		alta.setMinimumSize(new Dimension(150, 40));
		alta.setMaximumSize(new Dimension(150, 40));
		alta.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				recogerValores();
			}
		});

		panelAlta = new JPanel();
		panelAlta.setLayout(new GridLayout(3, 2, 5, 0));
		panelAlta.add(textoNombre);
		panelAlta.add(nombre);
		panelAlta.add(textoNIA);
		panelAlta.add(NIA);
		panelAlta.add(textoEdad);
		panelAlta.add(edad);

		panel = new JPanel();
		panel.setLayout(new BorderLayout(0, 15));
		panel.add(panelAlta, BorderLayout.NORTH);
		panel.add(alta, BorderLayout.CENTER);

		setLayout(new FlowLayout(FlowLayout.CENTER, 15, 50));
		add(panel);

		pack();
	}

	public void recogerValores() {
		String nombre, NIA;
		int edad;
		nombre = this.nombre.getText();
		NIA = this.NIA.getText();
		edad = (int) this.edad.getValue();
		Alumno al = new Alumno(nombre, NIA, edad);
		pantalla.addAlumno(al);
		JOptionPane.showMessageDialog(this, "Alumno anadido correctamente...");
		this.nombre.setText("");
		this.NIA.setText("");
		this.edad.setValue(0);
	}

}
