package com.program;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private final String ruta = "Passwords.dat";

	private ArrayList<Usuario> usuarios;

	private JLabel ErrorLabel;
	private JButton Listado;
	private JButton Login;
	private JButton NewUser;
	private JPasswordField PasswordInput;
	private JTextField UserInput;
	private JLabel jLabel1;
	private JLabel jLabel2;

	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		usuarios = (ArrayList<Usuario>) GestorBin.leer(ruta);
		initComponents();
		
	}

	private void initComponents() {

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();
		UserInput = new javax.swing.JTextField();
		PasswordInput = new javax.swing.JPasswordField();
		NewUser = new javax.swing.JButton();
		Login = new javax.swing.JButton();
		Listado = new javax.swing.JButton();
		ErrorLabel = new javax.swing.JLabel();

		jLabel1.setText("Usuario");

		jLabel2.setText("Password");

		NewUser.setText("Nuevo Usuario");
		NewUser.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				NewUserActionPerformed(evt);
			}
		});

		Login.setText("Login");
		Login.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				LoginActionPerformed(evt);
			}
		});

		Listado.setText("Listado");
		Listado.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListadoActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addGap(61, 61, 61).addComponent(ErrorLabel))
						.addGroup(layout.createSequentialGroup().addGap(50, 50, 50)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(NewUser, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(Login, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(Listado, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
						.addGroup(layout.createSequentialGroup().addGap(61, 61, 61)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addComponent(jLabel2).addComponent(jLabel1))
								.addGap(18, 18, 18)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(UserInput, javax.swing.GroupLayout.DEFAULT_SIZE, 120,
												Short.MAX_VALUE)
										.addComponent(PasswordInput))))
				.addContainerGap(36, Short.MAX_VALUE)));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addGap(54, 54, 54)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel1)
						.addComponent(UserInput, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE).addComponent(jLabel2)
						.addComponent(PasswordInput, javax.swing.GroupLayout.PREFERRED_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
				.addGap(38, 38, 38).addComponent(NewUser)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(Login)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(Listado)
				.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
				.addComponent(ErrorLabel).addGap(51, 51, 51)));

		pack();
	}

	protected void ListadoActionPerformed(ActionEvent evt) {
		ListadoUsuarios ver = new ListadoUsuarios(this, true);
	}

	protected void LoginActionPerformed(ActionEvent evt) {
		if (!UserInput.getText().isBlank() && !PasswordInput.getText().isBlank()) {
			Usuario user = new Usuario(UserInput.getText());
			if (usuarios.contains(user)) {
				int index = usuarios.indexOf(user);
				if (!usuarios.get(index).checkPassword(PasswordInput.getText()))
					mensaje("Contrasena incorrecta", Color.RED);
				else
					mensaje("Login realizado con exito", Color.GREEN);
			} else
				mensaje("Usuario no encontrado.", Color.RED);
		} else
			mensaje("No has introducido todos los campos", Color.RED);
	}

	private void NewUserActionPerformed(ActionEvent evt) {
		if (!UserInput.getText().isBlank() && !PasswordInput.getText().isBlank()) {
			Usuario user = new Usuario(UserInput.getText(), PasswordInput.getText());
			if (!usuarios.contains(user)) {
				usuarios.add(user);
				GestorBin.add(ruta, user);
				mensaje("Usuario "+user+" creado con exito", Color.GREEN);
			} else
				mensaje("El usuario "+user+" ya existe.", Color.RED);
		} else
			mensaje("No has introducido todos los campos", Color.RED);
	}

	public void mensaje(String mensaje, Color color) {
		ErrorLabel.setText(mensaje);
		ErrorLabel.setForeground(color);
	}

	public String getRuta() {
		return ruta;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
