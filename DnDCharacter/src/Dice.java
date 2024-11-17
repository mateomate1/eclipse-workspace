import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class Dice extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel texto;
    private JButton tirar;
    private JComboBox<Integer> opciones;
    private int anterior=0;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Dice frame = new Dice();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Dice() {
        initComponents();
        setSize(600, 400);
    }

    public void initComponents() {
        // Configuración del cierre
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        // Crear y configurar el contentPane
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout());
        setContentPane(contentPane);

        // Crear etiqueta (JLabel)
        texto = new JLabel("Elige un número", JLabel.CENTER);
        texto.setFont(new Font("Arial", Font.BOLD, 80)); // Letra grande
        contentPane.add(texto, BorderLayout.CENTER); // Centro principal para ocupar más espacio

        // Crear comboBox con opciones
        Integer[] valores = {100, 20, 12, 10, 8, 6, 4, 2};
        opciones = new JComboBox<>(valores);
        opciones.setFont(new Font("Arial", Font.PLAIN, 20)); // Ajustar tamaño de letra del comboBox
        contentPane.add(opciones, BorderLayout.NORTH);

        // Crear botón (JButton)
        tirar = new JButton("Tirar");
        tirar.setFont(new Font("Arial", Font.PLAIN, 20)); // Tamaño del texto del botón
        tirar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Obtener valor seleccionado y generar número aleatorio
                int maximo = (int) opciones.getSelectedItem();
                int numeroAleatorio = 1 + (int) (Math.random() * maximo);
                // Mostrar resultado en el JLabel
                if(anterior==numeroAleatorio)
                	texto.setText(texto.getText() + "+");
                else
                	texto.setText("" + numeroAleatorio);
                anterior=numeroAleatorio;
            }
        });
        contentPane.add(tirar, BorderLayout.SOUTH);
    }
}
