package reproductor;

public class reproductor extends javax.swing.JFrame {
	
    private javax.swing.JPanel jPanel;
    private javax.swing.JButton jButtonOn;
    private javax.swing.JButton jButtonPlay;
    private javax.swing.JButton jButtonRecord;
    private javax.swing.JButton jButtonAnterior;
    private javax.swing.JButton jButtonPause;
    private javax.swing.JButton jButtonSiguiente;
    private javax.swing.JButton jButtonRebobinar;
    private javax.swing.JButton jButtonStop;
    private javax.swing.JButton jButtonAvanzar;
	
	public reproductor() {
		initComponents();
	}
	
	private void initComponents() {
		jPanel = new javax.swing.JPanel();
		
		jButtonOn = new javax.swing.JButton();
		jButtonPlay = new javax.swing.JButton();
		jButtonRecord = new javax.swing.JButton();
		jButtonAnterior = new javax.swing.JButton();
		jButtonPause = new javax.swing.JButton();
		jButtonSiguiente = new javax.swing.JButton();
		jButtonRebobinar = new javax.swing.JButton();
		jButtonStop = new javax.swing.JButton();
		jButtonAvanzar = new javax.swing.JButton();
		
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		
		jPanel.setLayout(new java.awt.GridLayout(3,3,1,1));
		
		
		
	}

	public static void main(String[] args) {
		
		
		
	}

}
