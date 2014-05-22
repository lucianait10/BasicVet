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

package cuConfiguracion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class PanelBasicVet extends JPanel {
  
	private static final long serialVersionUID = 1L;
	private JPanel panelImagen = null;
	private JButton botonBuscarArchivo = null;
	private JLabel labelImagen = null;
	private JLabel labelAclaracionImagen = null;
	private JPanel panelDatos = null;
	private JLabel labelNombreVeterinaria = null;
	private JTextField campoNombreVeterinaria = null;
	private JLabel labelTelefono = null;
	private JTextField campoTelefono = null;
	private JLabel labelDireccion = null;
	private JTextField campoDireccion = null;
	private JLabel labelCuil = null;
	private JTextField campoCuil = null;
	private JPanel panelBotones = null;  //  @jve:decl-index=0:visual-constraint="119,359"
	private JButton botonAplicar = null;
	private JLabel labelEmail = null;
	private JLabel labelWeb = null;
	private JLabel labelTelefonoEmerg1 = null;
	private JTextField campoTelEmer = null;
	private JTextField campoEmail = null;
	private JTextField campoWeb = null;
	public PanelBasicVet() {
		super();
		initialize();
	}

	private void initialize() {
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setSize(611, 400);
		this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(162,211,255)));
		this.add(getPanelDatos(), null);
		this.add(getPanelImagen(), null);
		this.add(getPanelBotones(),null);
	}

	private JPanel getPanelImagen() {
		if (panelImagen == null) {
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.insets = new Insets(0, 150, 4, 10);
			gridBagConstraints9.ipadx = 0;
			gridBagConstraints9.gridy = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 1;
			gridBagConstraints8.gridy = 0;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridwidth = 3;
			gridBagConstraints6.gridy = 1;
			gridBagConstraints6.ipadx = 4;
			gridBagConstraints6.anchor = GridBagConstraints.SOUTHWEST;
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints6.gridheight = 1;
			gridBagConstraints6.ipady = 0;
			gridBagConstraints6.gridx = 0;
			panelImagen = new JPanel();
			panelImagen.setLayout(new GridBagLayout());
			panelImagen.setBorder(BorderFactory.createTitledBorder(null, "Logo Veterinaria", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelImagen.add(getLabelAclaracionImagen(), gridBagConstraints6);
			panelImagen.add(getBotonBuscarArchivo(), gridBagConstraints8);
			panelImagen.add(getLabelImagen(), gridBagConstraints9);
		}
		return panelImagen;
	}

	public JButton getBotonBuscarArchivo() {
		if (botonBuscarArchivo == null) {
			botonBuscarArchivo = new JButton();
			botonBuscarArchivo.setMnemonic('B');
			botonBuscarArchivo.setIcon(new ImageIcon(getClass().getResource("/icono/search.jpg")));
			botonBuscarArchivo.setText("Buscar Imagen");
			botonBuscarArchivo.registerKeyboardAction(botonBuscarArchivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonBuscarArchivo.registerKeyboardAction(botonBuscarArchivo.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonBuscarArchivo;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonBuscarArchivo.addActionListener(al);
		this.botonAplicar.addActionListener(al);
	}

	public JLabel getLabelImagen() {
		if (labelImagen == null) {
			labelImagen = new JLabel();
			labelImagen.setBackground(Color.white);
			labelImagen.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			labelImagen.setMaximumSize(new Dimension(150, 150));
			labelImagen.setMinimumSize(new Dimension(150, 150));
			labelImagen.setPreferredSize(new Dimension(150, 150));
		}
		return labelImagen;
	}

	private JLabel getLabelAclaracionImagen() {
		if (labelAclaracionImagen == null) {
			labelAclaracionImagen = new JLabel();
			labelAclaracionImagen.setText("La imagen seleccionada debera tener una dimension de 300x300 de lo contrario se redimensionara");
			labelAclaracionImagen.setFont(new Font("Dialog", Font.BOLD, 10));
		}
		return labelAclaracionImagen;
	}

	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.fill = GridBagConstraints.BOTH;
			gridBagConstraints71.gridy = 6;
			gridBagConstraints71.weightx = 1.0;
			gridBagConstraints71.insets = new Insets(0, 1, 0, 0);
			gridBagConstraints71.gridx = 1;
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.fill = GridBagConstraints.BOTH;
			gridBagConstraints61.gridy = 5;
			gridBagConstraints61.weightx = 1.0;
			gridBagConstraints61.insets = new Insets(0, 1, 0, 0);
			gridBagConstraints61.gridx = 1;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.fill = GridBagConstraints.BOTH;
			gridBagConstraints51.gridy = 4;
			gridBagConstraints51.weightx = 1.0;
			gridBagConstraints51.insets = new Insets(0, 1, 0, 0);
			gridBagConstraints51.gridx = 1;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 0;
			gridBagConstraints41.insets = new Insets(0, 0, 0, 1);
			gridBagConstraints41.gridy = 4;
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.gridy = 6;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.gridy = 5;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.gridy = 4;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.BOTH;
			gridBagConstraints10.gridy = 3;
			gridBagConstraints10.weightx = 1.0;
			gridBagConstraints10.ipady = 0;
			gridBagConstraints10.ipadx = 175;
			gridBagConstraints10.insets = new Insets(0, 1, 0, 0);
			gridBagConstraints10.gridx = 1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.gridy = 3;
			labelCuil = new JLabel();
			labelCuil.setText("Cuil");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.BOTH;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.ipadx = 308;
			gridBagConstraints5.insets = new Insets(0, 1, 0, 0);
			gridBagConstraints5.gridx = 1;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.gridy = 2;
			labelDireccion = new JLabel();
			labelDireccion.setText("Direccion");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.BOTH;
			gridBagConstraints3.gridy = 1;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.ipadx = 173;
			gridBagConstraints3.insets = new Insets(0, 1, 0, 0);
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.gridy = 1;
			labelTelefono = new JLabel();
			labelTelefono.setText("Telefono");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.BOTH;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.weightx = 1.0;
			gridBagConstraints1.ipadx = 307;
			gridBagConstraints1.insets = new Insets(0, 1, 0, 0);
			gridBagConstraints1.gridx = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.gridy = 0;
			labelNombreVeterinaria = new JLabel();
			labelNombreVeterinaria.setText("Nombre Veterinaria");
			labelNombreVeterinaria.setName("");
			panelDatos = new JPanel();
			panelDatos.setLayout(new GridBagLayout());
			panelDatos.setBorder(BorderFactory.createTitledBorder(null, "Datos Veterinaria", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, new Font("Dialog", Font.BOLD, 12), new Color(51, 51, 51)));
			panelDatos.add(labelNombreVeterinaria, gridBagConstraints);
			panelDatos.add(getCampoNombreVeterinaria(), gridBagConstraints1);
			panelDatos.add(labelTelefono, gridBagConstraints2);
			panelDatos.add(getCampoTelefono(), gridBagConstraints3);
			panelDatos.add(labelDireccion, gridBagConstraints4);
			panelDatos.add(getCampoDireccion(), gridBagConstraints5);
			panelDatos.add(labelCuil, gridBagConstraints7);
			panelDatos.add(getCampoCuil(), gridBagConstraints10);
			panelDatos.add(getLabelEmail(), gridBagConstraints21);
			panelDatos.add(getLabelWeb(), gridBagConstraints31);
			panelDatos.add(getLabelTelefonoEmerg1(), gridBagConstraints41);
			panelDatos.add(getCampoTelEmer(), gridBagConstraints51);
			panelDatos.add(getCampoEmail(), gridBagConstraints61);
			panelDatos.add(getCampoWeb(), gridBagConstraints71);
		}
		return panelDatos;
	}

	public JTextField getCampoNombreVeterinaria() {
		if (campoNombreVeterinaria == null) {
			campoNombreVeterinaria = new JTextField();
		}
		return campoNombreVeterinaria;
	}

	public JTextField getCampoTelefono() {
		if (campoTelefono == null) {
			campoTelefono = new JTextField();
			campoTelefono.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='(' || ev.getKeyChar()==')' || ev.getKeyChar()=='+' || ev.getKeyChar()=='-')) ev.consume();}
			});
		}
		return campoTelefono;
	}

	public JTextField getCampoDireccion() {
		if (campoDireccion == null) {
			campoDireccion = new JTextField();
		}
		return campoDireccion;
	}

	public JTextField getCampoCuil() {
		if (campoCuil == null) {
			campoCuil = new JTextField();
		}
		return campoCuil;
	}

	public JPanel getPanelBotones() {
		if (panelBotones == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.RIGHT);
			panelBotones = new JPanel();
			panelBotones.setLayout(flowLayout);
			panelBotones.setSize(new Dimension(342, 42));
			panelBotones.add(getBotonAplicar(), null);
		}
		return panelBotones;
	}

	public JButton getBotonAplicar() {
		if (botonAplicar == null) {
			botonAplicar = new JButton();
			botonAplicar.setText("Aplicar");
			botonAplicar.setMnemonic('A');
			botonAplicar.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
			botonAplicar.registerKeyboardAction(botonAplicar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAplicar.registerKeyboardAction(botonAplicar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAplicar;
	}

	/**
	 * This method initializes labelEmail	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelEmail() {
		if (labelEmail == null) {
			labelEmail = new JLabel();
			labelEmail.setText("Email");
		}
		return labelEmail;
	}

	/**
	 * This method initializes labelWeb	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelWeb() {
		if (labelWeb == null) {
			labelWeb = new JLabel();
			labelWeb.setText("Pagina Web");
		}
		return labelWeb;
	}

	/**
	 * This method initializes labelTelefonoEmerg1	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelTelefonoEmerg1() {
		if (labelTelefonoEmerg1 == null) {
			labelTelefonoEmerg1 = new JLabel();
			labelTelefonoEmerg1.setText("Telefono De Emergencia");
		}
		return labelTelefonoEmerg1;
	}

	/**
	 * This method initializes campoTelEmer	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoTelEmer() {
		if (campoTelEmer == null) {
			campoTelEmer = new JTextField();
			campoTelEmer.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='(' || ev.getKeyChar()==')' || ev.getKeyChar()=='+' || ev.getKeyChar()=='-')) ev.consume();}
			});
		}
		return campoTelEmer;
	}

	/**
	 * This method initializes campoEmail	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoEmail() {
		if (campoEmail == null) {
			campoEmail = new JTextField();
		}
		return campoEmail;
	}

	/**
	 * This method initializes campoWeb	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getCampoWeb() {
		if (campoWeb == null) {
			campoWeb = new JTextField();
		}
		return campoWeb;
	}

}
