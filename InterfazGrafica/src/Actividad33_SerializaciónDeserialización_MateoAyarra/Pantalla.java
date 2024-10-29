package Actividad33_SerializaciónDeserialización_MateoAyarra;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Pantalla extends JFrame {

    private static final long serialVersionUID = 1L;
    private final String ruta = "archivo.dat";

    private JButton ButtonAdd;
    private JButton ButtonShow;
    private JButton ButtonDelete;
    
    private JPanel panel;
    private ArrayList<Alumno> alumnos = new ArrayList<>();

    public Pantalla() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {

        ButtonAdd = new JButton("Añadir alumno");
        ButtonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionAlta(e);
            }
        });

        ButtonShow = new JButton("Ver alumnos");
        ButtonShow.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionShow(e);
            }
        });

        ButtonDelete = new JButton("Eliminar alumno");
        ButtonDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionDelete(e);
            }
        });

        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 50));
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
