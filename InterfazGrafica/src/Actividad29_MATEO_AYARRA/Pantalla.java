package Actividad29_MATEO_AYARRA;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Pantalla extends JFrame implements KeyListener{
	
	private Random random = new Random();
	private String texto = "";
	private boolean mayus = true;
	private final String teclado[] = {"1234567890", "QWERTYUIOP", "ASDFGHJKL", "ZXCVBNM,.-"};
	private ArrayList<JButton> teclas;
	private String[] fuentes = {"Arial", "Verdana", "Times New Roman", "Courier New", "Georgia", "Tahoma", "Comic Sans MS", "VIVALDI"};
	
	private JTextArea TextArea;
	
	private JPanel panelTeclado;
	private JPanel panelEspeciales;
	
	private JButton ButtonClear;
	private JButton ButtonFuente;
	private JButton ButtonFondo;
	
	public Pantalla() {
		initComponents();
		setSize(600,400);
		initlaf();
		
		setFocusable(true);
		
		requestFocusInWindow();//Hacer focus en el teclado
		
		addKeyListener(this);
	}
	
	private void initComponents() {
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		TextArea = new JTextArea(5,20);
		TextArea.setLineWrap(true);
		TextArea.setEditable(false);
		
		TextArea.setFont(new Font("Arial", Font.BOLD, 16));
		
		TextArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));//up, left, bottom, right
		
		ButtonClear = new JButton("CLEAR");
        ButtonClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                texto = "";
                TextArea.setText(texto);
                
                Pantalla.this.requestFocusInWindow();//Volver a hacer focus en el teclado
            }
        });
        
        ButtonFuente = new JButton("FUENTE");
        ButtonFuente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextArea.setFont(new Font(fuentes[random.nextInt(fuentes.length)], Font.BOLD, 16));
				
				Pantalla.this.requestFocusInWindow();//Volver a hacer focus en el teclado
			}
		});
        
        ButtonFondo = new JButton("FONDO");
        ButtonFondo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				TextArea.setBackground(new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
				
				Pantalla.this.requestFocusInWindow();//Volver a hacer focus en el teclado
			}
		});
        
        teclas = new ArrayList<JButton>();
        panelTeclado = new JPanel();
        panelTeclado.setLayout(new GridLayout(4,1));
		for (int i=0; i<teclado.length; i++) {
			JPanel panel = new JPanel();
			panel.setLayout(new GridLayout(1,0));
			for(char tecla : teclado[i].toCharArray()) {
				JButton buton = new JButton(String.valueOf(tecla));
				buton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						texto += buton.getText();
						TextArea.setText(texto);
						
						Pantalla.this.requestFocusInWindow();//Volver a hacer focus en el teclado
					}
				});
				teclas.add(buton);
				panel.add(buton);
			}
			panelTeclado.add(panel);
		}
		
		panelEspeciales = new JPanel();
		panelEspeciales.setLayout(new GridLayout(0,1));
		panelEspeciales.add(ButtonClear);
		panelEspeciales.add(ButtonFondo);
		panelEspeciales.add(ButtonFuente);
		
		setLayout(new BorderLayout());
		
		add(TextArea,BorderLayout.NORTH);
		add(panelTeclado,BorderLayout.CENTER);
		add(panelEspeciales,BorderLayout.EAST);
		
		pack();
	}
	
	public void initlaf() {
		/*Lookandfields:
			"Windows"
			"Windows Classic"
			"Nimbus"
			"Metal"
			"Motif"
		*/

	try {
	    for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	        if ("Metal".equals(info.getName())) {
	            javax.swing.UIManager.setLookAndFeel(info.getClassName());
	            break;
	        }
	    }
	} 
	catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
	    java.util.logging.Logger.getLogger(Pantalla.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
	}
	
}

	@Override
	public void keyTyped(KeyEvent e) {
		// Obtener el carácter introducido
        char c = e.getKeyChar();
        // Agregarlo al String (puedes modificar cómo manejar el texto)
        texto += c;
        // Aquí puedes añadir lógica adicional si es necesario
	}
	@Override
	public void keyPressed(KeyEvent e) {
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public boolean existe(char tecla) {
		boolean salida = false;
		for(JButton t : teclas) {
			if (tecla == t.getText().toLowerCase().charAt(0)) 
				salida = true;
		}
		return salida;
	}
	
	public static void main(String[] args) {

	    // Crear y mostrar la ventana
	    javax.swing.SwingUtilities.invokeLater(new Runnable() {
	        @Override
	        public void run() {
	        	javax.swing.SwingUtilities.invokeLater(() -> {
	                Pantalla pantalla = new Pantalla();
	                pantalla.setVisible(true);
	            });
	        }
	    });
	}
	
}
