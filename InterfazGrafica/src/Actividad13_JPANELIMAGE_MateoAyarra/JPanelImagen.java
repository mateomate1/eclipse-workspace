package Actividad13_JPANELIMAGE_MateoAyarra;

import java.awt.Graphics;
import java.io.File;
import java.io.Serializable;

import javax.accessibility.AccessibleContext;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class JPanelImagen extends JPanel implements Serializable{

	private static final long serialVersionUID = 1L;
	private File img = new File("blackMarket.jpg");

	/**
	 * Create the panel.
	 */
	public JPanelImagen() {

	}

	public File getImg() {
		return img;
	}

	public void setImg(File img) {
		this.img = img;
	}

	@Override
	public AccessibleContext getAccessibleContext() {
		// TODO Auto-generated method stub
		return super.getAccessibleContext();
	}

	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		super.paintComponent(g);
		
		if (img!=null && img.exists()) {
			ImageIcon icon = new ImageIcon(img.getAbsolutePath());
			g.drawImage(icon.getImage(), 0, 0, null);
		}
	}

}
