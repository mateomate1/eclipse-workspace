package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class Alta extends JDialog {

	private static final long serialVersionUID = 1L;
	private Pantalla pantalla;
	
	private JLabel textoNombre;
	private JLabel textoNIA;
	private JLabel textoEdad;
	
	private JButton alta;
	
	public Alta(Frame owner, boolean modal) {
		super(owner, modal);
		pantalla = (Pantalla)owner;
		initComponents();
	}
	
	public void initComponents() {
		alta = new JButton("Anadir");
		alta.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
	}


	

}
