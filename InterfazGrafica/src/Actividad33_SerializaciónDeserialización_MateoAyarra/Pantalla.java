package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Pantalla extends JFrame implements Serializable{
	
	private JButton ButtonAdd;
	private JButton ButtonShow;
	private JButton ButtonDelete;
	
	private JPanel panel;
	
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
				actionAlta(e);
			}
		});
		
		ButtonShow = new JButton("Ver alumnos");
		
		ButtonDelete = new JButton("Eliminar alumno");
		
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


}
