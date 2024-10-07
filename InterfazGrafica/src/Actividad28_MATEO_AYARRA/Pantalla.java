package Actividad28_MATEO_AYARRA;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Pantalla extends JFrame{
	
	private String texto = "";
	private boolean mayus = true;
	private final String teclado[] = {"1234567890", "QWERTYUIOP", "ASDFGHJKL¡", "ZXCVBNM¿?!"};
	
	private JTextArea TextArea;
	
	private JPanel panelLetrasNumeros;
	private JPanel panelEspeciales;
	
	private JButton ButtonEnter;
	private JButton ButtonBack;
	private JButton ButtonClear;
	private JButton ButtonMayus;
	private JButton ButtonSpace;
	
	public Pantalla() {
		initComponents();
		setSize(600,400);
	}
	
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		TextArea = new JTextArea(5,20);
		TextArea.setLineWrap(true);
		TextArea.setEditable(false);
		
		panelLetrasNumeros = new JPanel();
		panelEspeciales = new JPanel();
		
		ButtonBack = new JButton("BACK");
		ButtonBack.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				texto = texto.length() > 0? texto.substring(0, texto.length()-1):texto;
				TextArea.setText(texto);
			}
		});
		
		ButtonClear = new JButton("CLEAR");
        ButtonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = "";
                TextArea.setText(texto);
            }
        });

        ButtonEnter = new JButton("ENTER");
        ButtonEnter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto += "\n";
                TextArea.setText(texto);
            }
        });
        
        ButtonSpace = new JButton("|___|");
        ButtonSpace.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				texto += " ";
			}
		});
		
        ButtonMayus = new JButton("Mayus");
        ButtonMayus.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(mayus) {
					mayus=(!mayus);
				}
			}
		});
        
		panelLetrasNumeros.setLayout(new GridLayout(4,10));
		for (int i=0; i<teclado.length; i++) {
			for(char tecla : teclado[i].toCharArray()) {
				JButton buton = new JButton(String.valueOf(tecla));
				buton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						texto += buton.getText();
						TextArea.setText(texto);
						
					}
				});
				
				panelLetrasNumeros.add(buton);
				
			}
		}
		
		panelEspeciales.setLayout(new GridLayout(4,1));
		panelEspeciales.add(ButtonBack);
		panelEspeciales.add(ButtonEnter);
		panelEspeciales.add(ButtonMayus);
		panelEspeciales.add(ButtonClear);
		panelEspeciales.add(ButtonSpace);
		
		setLayout(new BorderLayout());
		
		add(TextArea,BorderLayout.NORTH);
		add(panelLetrasNumeros,BorderLayout.CENTER);
		add(panelEspeciales,BorderLayout.EAST);
		
	}

	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				Pantalla pantalla = new Pantalla();
				pantalla.setVisible(true);
	        }
	    });
	}

}
