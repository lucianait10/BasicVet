/********************************************************
  This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *********************************************************/

package cuAcercaDe;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;


public class GUIAcercaDe extends JFrame {
  
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel labelNombre = null;
	private JLabel labelVersion = null;
	private JLabel labelImagen = null;
	public static String BASE = (new File("")).getAbsolutePath(); 
	private String PATH_IMAGE = BASE + "/src/imagenes/";  
	private JLabel labelLicencia = null;
	private JLinkButton botonLicencia = null;
	private JLabel labelBasicVet = null;
	private JLabel labelAutores = null;
	private JLabel labelDirector = null;
	private JLabel labelCoDirector = null;
	private JTextArea labelDescripcion = null;
	private JLinkButton botonSitioWeb = null;
	private JLabel labelEmail = null;
	private JLabel labelCoop = null;
	
	/**
	 * This is the default constructor
	 */
	public GUIAcercaDe() {
		super();
		initialize();
		cerrarVentana();
	}
	
	@SuppressWarnings("serial")
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){
	        public void actionPerformed(ActionEvent e){
	        	setVisible(false);
	        	dispose();
	        }
	    });
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	
			public void windowClosing(java.awt.event.WindowEvent evt){
	    		setVisible(false);
	        	dispose();
	        }
	    });
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(550, 430);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("ACERCA DE BASICVET");
		this.setLocationRelativeTo(null);
		//this.setModal(true);
		this.setResizable(false);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 1;
			gridBagConstraints12.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints12.gridy = 10;
			labelCoop = new JLabel();
			labelCoop.setText("MEJORADO POR: \"IT10\" COOPERATIVA DE TRABAJO LIMITADA (RIO CUARTO)");
			labelCoop.setForeground(Color.BLUE);
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 1;
			gridBagConstraints11.gridy = 11;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.insets = new Insets(5, 0, 5, 0);
			gridBagConstraints10.gridy = 12;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.fill = GridBagConstraints.NONE;
			gridBagConstraints9.gridy = 9;
			gridBagConstraints9.weightx = 1.0;
			gridBagConstraints9.weighty = 1.0;
			gridBagConstraints9.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints9.anchor = GridBagConstraints.WEST;
			gridBagConstraints9.gridx = 1;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 1;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints6.gridy = 8;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 1;
			gridBagConstraints51.anchor = GridBagConstraints.WEST;
			gridBagConstraints51.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints51.gridy = 7;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 1;
			gridBagConstraints41.anchor = GridBagConstraints.WEST;
			gridBagConstraints41.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints41.gridy = 6;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 1;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints.gridy = 5;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 1;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.insets = new Insets(0, 75, 0, 0);
			gridBagConstraints3.gridy = 4;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridy = 0;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.ipadx = 0;
			gridBagConstraints1.gridheight = 1;
			gridBagConstraints1.gridwidth = 2;
			gridBagConstraints1.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.gridy = 4;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			gridBagConstraints5.gridy = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 1;
			gridBagConstraints4.insets = new Insets(0, 20, 0, 0);
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridy = 1;
			jContentPane = new JPanel();
			jContentPane.setLayout(new GridBagLayout());
			jContentPane.add(getLabelNombre(), gridBagConstraints4);
			jContentPane.add(getLabelVersion(), gridBagConstraints5);
			jContentPane.add(getLabelLicencia(), gridBagConstraints1);
			jContentPane.add(getLabelImagen(), gridBagConstraints2);
			jContentPane.add(getBotonLicencia(), gridBagConstraints3);
			jContentPane.add(getLabelBasicVet(), gridBagConstraints);
			jContentPane.add(getLabelAutores(), gridBagConstraints41);
			jContentPane.add(getLabelDirector(), gridBagConstraints51);
			jContentPane.add(getLabelCoDirector(), gridBagConstraints6);
			jContentPane.add(getLabelDescripcion(), gridBagConstraints9);
			jContentPane.add(getBotonSitioWeb(), gridBagConstraints10);
			jContentPane.add(getLabelEmail(), gridBagConstraints11);
			jContentPane.add(labelCoop, gridBagConstraints12);
		}
		return jContentPane;
	}

	/**
	 * This method initializes labelNombre	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelNombre() {
		if (labelNombre == null) {
			labelNombre = new JLabel();
			labelNombre.setText("BASIC-VET");
			labelNombre.setForeground(new Color(0, 130, 255));
			labelNombre.setFont(new Font("Snap ITC", Font.BOLD, 30));
		}
		return labelNombre;
	}

	/**
	 * This method initializes labelVersion	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelVersion() {
		if (labelVersion == null) {
			labelVersion = new JLabel();
			labelVersion.setText("Version: 1.1");
		}
		return labelVersion;
	}

	/**
	 * This method initializes labelImagen	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelImagen() {
		if (labelImagen == null) {
			labelImagen = new JLabel();
			labelImagen.setIcon(new ImageIcon(PATH_IMAGE+"acercaDe.png"));
		}
		return labelImagen;
	}

	/**
	 * This method initializes labelLicencia	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelLicencia() {
		if (labelLicencia == null) {
			labelLicencia = new JLabel();
			labelLicencia.setText("Licencia:");
		}
		return labelLicencia;
	}

	/**
	 * This method initializes botonLicencia	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JLinkButton getBotonLicencia() {
		if (botonLicencia == null) {
			botonLicencia = new JLinkButton("GNU GPL");
			botonLicencia.setBorder(null);
		}
		return botonLicencia;
	}

	public void setListenerButons(ActionListener al){
		this.botonLicencia.addActionListener(al);
		this.botonSitioWeb.addActionListener(al);
	}

	/**
	 * This method initializes labelBasicVet	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelBasicVet() {
		if (labelBasicVet == null) {
			labelBasicVet = new JLabel();
			labelBasicVet.setText("BasicVet: Sistema de Gestion para Pequeñas Veterinarias");
		}
		return labelBasicVet;
	}

	/**
	 * This method initializes labelAutores	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelAutores() {
		if (labelAutores == null) {
			labelAutores = new JLabel();
			labelAutores.setText("Autores: Luciana Crippa y Fernando Villar");
		}
		return labelAutores;
	}

	/**
	 * This method initializes labelDirector	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelDirector() {
		if (labelDirector == null) {
			labelDirector = new JLabel();
			labelDirector.setText("Director: Lic. Ariel Ferreira Szpiniak");
		}
		return labelDirector;
	}

	/**
	 * This method initializes labelCoDirector	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelCoDirector() {
		if (labelCoDirector == null) {
			labelCoDirector = new JLabel();
			labelCoDirector.setText("Co-director: Lic. Ariel Arsaute");
		}
		return labelCoDirector;
	}

	/**
	 * This method initializes labelDescripcion	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JTextArea getLabelDescripcion() {
		if (labelDescripcion == null) {
			labelDescripcion = new JTextArea();
			labelDescripcion.setText("El proyecto BasiVet ha sido realizado en la Universidad Nacional de Rio Cuarto,\nen el marco del Trabajo Final de Analista en Computacion, y el Proyecto de \nExtension Universitaria BasicVet, destinado al desarrollo y difusion \ndel Sistema de Gestion para Pequeñas Veterinarias, que fue aprobado por el Consejo \nDirectivo de la Facultad de Agronomia y Veterinaria mediante Resolucion Nro. 153/10. \nAño 2011");
			labelDescripcion.setBackground(null);
		}
		return labelDescripcion;
	}

	/**
	 * This method initializes botonSitioWeb	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JLinkButton getBotonSitioWeb() {
		if (botonSitioWeb == null) {
			botonSitioWeb = new JLinkButton("www.it10coop.com.ar");
			botonSitioWeb.setBorder(null);
			botonSitioWeb.setText("it10coop.com.ar");
		}
		return botonSitioWeb;
	}

	/**
	 * This method initializes labelEmail	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelEmail() {
		if (labelEmail == null) {
			labelEmail = new JLabel();
			labelEmail.setText("contacto@it10coop.com.ar");
			labelEmail.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return labelEmail;
	}
}  //  @jve:decl-index=0:visual-constraint="56,7"
