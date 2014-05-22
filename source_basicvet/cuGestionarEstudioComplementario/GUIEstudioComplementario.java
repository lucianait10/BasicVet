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

package cuGestionarEstudioComplementario;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;

public class GUIEstudioComplementario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;
		private JButton botonAgregar = null;
		private JButton botonModificar = null;
		private JScrollPane scrollPaneImagen = null;  
		private JPanel panelDatos = null;
		private JTextField campoFecha = null;
		private JLabel labelFecha = null;
		private JLabel labelTipo = null;
		private JComboBox campoTipo = null;
		private JLabel labelInforme = null;
		private JTextArea campoArea = null;
		private JPanel panelDatosPrincipales = null;
		@SuppressWarnings("unused")
		private String pathImagen;
		private JButton botonBusqueda = null;
		private GUIGestionarEstudioComplementario guiGesEstComp;
		private JPanel panelTipo = null;
		private JButton botonAgregarTipo = null;
		
		public GUIEstudioComplementario(String path,GUIGestionarEstudioComplementario guiGesEstComp){	
			super();
			this.guiGesEstComp = guiGesEstComp;
			this.pathImagen = path;
			cerrarVentana();
			initialize();
		}
		
		@SuppressWarnings("serial")
		private void cerrarVentana(){
			getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
		    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
		    getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
		        
				public void actionPerformed(ActionEvent e){
		        	setVisible(false);
		        	dispose();
		        	guiGesEstComp.setVisible(true);
		        }
		    });
		    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		    addWindowListener(new WindowAdapter() {
		    	
				public void windowClosing(WindowEvent evt){
		    		setVisible(false);
		    		dispose();
		    		guiGesEstComp.setVisible(true);
		    	}
		    });
		}

		private void initialize(){
			this.setSize(800, 600);
			this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
			this.setTitle("ESTUDIO COMPLEMENTARIO");
			this.setContentPane(getJContentPane());
			this.setContentPane(getJContentPane());
			this.setLocationRelativeTo(null);
			this.setModal(true);
		}

		private JPanel getJContentPane() {
			if (jContentPane == null) {
				jContentPane = new JPanel();
				jContentPane.setLayout(new BoxLayout(getJContentPane(), BoxLayout.Y_AXIS));
				jContentPane.add(getPanelDatos(), null);
			}
			return jContentPane;
		}

		private JPanel getPanelBotones() {
			if (panelBotones == null) {
				FlowLayout flowLayout = new FlowLayout();
				flowLayout.setAlignment(java.awt.FlowLayout.CENTER);
				flowLayout.setVgap(6);
				flowLayout.setHgap(5);
				panelBotones = new JPanel();
				panelBotones.setLayout(flowLayout);
				panelBotones.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
				panelBotones.add(getBotonBusqueda(), null);
				panelBotones.add(getBotonAgregar(), null);
				panelBotones.add(getBotonModificar(), null);
			}
			return panelBotones;
		}

		public JButton getBotonAgregar() {
			if (botonAgregar == null) {
				botonAgregar = new JButton("AGREGAR");
				botonAgregar.setMnemonic('a');
				botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
	                    JComponent.WHEN_FOCUSED);

				botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
	                    JComponent.WHEN_FOCUSED);
				//botonAgregar.setPreferredSize(new Dimension(120, 30));
				botonAgregar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			}
			return botonAgregar;
		}

		public JButton getBotonModificar() {
			if (botonModificar == null) {
				botonModificar = new JButton("MODIFICAR");
				botonModificar.setMnemonic('m');
				botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
	                    JComponent.WHEN_FOCUSED);

				botonModificar.registerKeyboardAction(botonModificar.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
	                    JComponent.WHEN_FOCUSED);
				//botonModificar.setPreferredSize(new Dimension(120, 30));
				botonModificar.setIcon(new ImageIcon(getClass().getResource("/icono/script_edit.png")));
			}
			return botonModificar;
		}
		
		public void setListenerButtons(ActionListener al){
			this.botonModificar.addActionListener(al);
			this.botonAgregar.addActionListener(al);
			this.botonBusqueda.addActionListener(al);
			this.botonAgregarTipo.addActionListener(al);
		}

		private JScrollPane getScrollPaneImagen() {
			if (scrollPaneImagen == null) {
				ImageIcon ii = new ImageIcon(getClass().getResource("/imagenes/fondo.png"));
				scrollPaneImagen = new JScrollPane(new JLabel(ii));
			}
			return scrollPaneImagen;
		}
		
		public void setImagenPanel(String pathImagen){
			ImageIcon ii = new ImageIcon(pathImagen);
			scrollPaneImagen.setViewportView(new JLabel(ii)); 
		}

		/**
		 * This method initializes panelDatos	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getPanelDatos() {
			if (panelDatos == null) {
				GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
				gridBagConstraints11.fill = GridBagConstraints.BOTH;
				gridBagConstraints11.ipadx = 0;
				gridBagConstraints11.ipady = 43;
				gridBagConstraints11.insets = new Insets(0, 0, 0, 0);
				GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
				gridBagConstraints8.gridx = 0;
				gridBagConstraints8.gridwidth = 0;
				gridBagConstraints8.fill = GridBagConstraints.HORIZONTAL;
				gridBagConstraints8.insets = new Insets(0, 0, 0, 0);
				gridBagConstraints8.gridy = 6;
				GridBagConstraints gridBagConstraints = new GridBagConstraints();
				gridBagConstraints.fill = GridBagConstraints.BOTH;
				gridBagConstraints.gridy = 5;
				gridBagConstraints.weightx = 1.0;
				gridBagConstraints.weighty = 1.0;
				gridBagConstraints.gridwidth = 0;
				gridBagConstraints.gridx = 0;
				labelInforme = new JLabel();
				labelInforme.setText("INFORME:");
				labelTipo = new JLabel();
				labelTipo.setText("TIPO:");
				labelFecha = new JLabel();
				labelFecha.setText("FECHA:");
				panelDatos = new JPanel();
				panelDatos.setLayout(new GridBagLayout());
				panelDatos.add(getPanelDatosPrincipales(), gridBagConstraints11);
				panelDatos.add(getScrollPaneImagen(), gridBagConstraints);
				panelDatos.add(getPanelBotones(), gridBagConstraints8);
			}
			return panelDatos;
		}

		/**
		 * This method initializes campoFecha	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		public JTextField getCampoFecha() {
			if (campoFecha == null) {
				campoFecha = new JTextField();
			}
			return campoFecha;
		}

		/**
		 * This method initializes campoTipo	
		 * 	
		 * @return javax.swing.JTextField	
		 */
		public JComboBox getCampoTipo() {
			if (campoTipo == null) {
				Object[] opciones = {};
				campoTipo = new JComboBox(opciones);
				campoTipo.setPreferredSize(new Dimension(160, 26));
			}
			return campoTipo;
		}

		/**
		 * This method initializes campoArea	
		 * 	
		 * @return javax.swing.JTextArea	
		 */
		public JTextArea getCampoArea() {
			if (campoArea == null) {
				campoArea = new JTextArea();
				campoArea.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			}
			return campoArea;
		}

		/**
		 * This method initializes panelDatosPrincipales	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getPanelDatosPrincipales() {
			if (panelDatosPrincipales == null) {
				GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
				gridBagConstraints9.gridx = 3;
				gridBagConstraints9.insets = new Insets(0, 3, 0, 0);
				gridBagConstraints9.gridy = 2;
				GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
				gridBagConstraints2.insets = new Insets(0, 10, 0, 0);
				gridBagConstraints2.gridy = 2;
				gridBagConstraints2.gridx = 0;
				GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
				gridBagConstraints1.anchor = GridBagConstraints.NORTH;
				gridBagConstraints1.insets = new Insets(5, 5, 5, 110);
				gridBagConstraints1.gridwidth = 1;
				gridBagConstraints1.gridx = 1;
				gridBagConstraints1.gridy = 2;
				gridBagConstraints1.ipadx = 0;
				gridBagConstraints1.weightx = 1.0;
				gridBagConstraints1.fill = GridBagConstraints.BOTH;
				GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
				gridBagConstraints3.insets = new Insets(0, 10, 0, 0);
				gridBagConstraints3.gridy = 2;
				gridBagConstraints3.gridx = 2;
				GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
				gridBagConstraints5.insets = new Insets(5, 0, 0, 0);
				gridBagConstraints5.gridx = 0;
				gridBagConstraints5.gridy = 3;
				gridBagConstraints5.ipadx = 0;
				gridBagConstraints5.gridwidth = 5;
				GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
				gridBagConstraints6.fill = GridBagConstraints.BOTH;
				gridBagConstraints6.gridy = 4;
				gridBagConstraints6.weightx = 1.0;
				gridBagConstraints6.weighty = 1.0;
				gridBagConstraints6.gridwidth = 5;
				gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
				gridBagConstraints6.gridx = 0;
				panelDatosPrincipales = new JPanel();
				panelDatosPrincipales.setLayout(new GridBagLayout());
				panelDatosPrincipales.add(getCampoArea(), gridBagConstraints6);
				panelDatosPrincipales.add(labelInforme, gridBagConstraints5);
				panelDatosPrincipales.add(labelTipo, gridBagConstraints3);
				panelDatosPrincipales.add(getCampoFecha(), gridBagConstraints1);
				panelDatosPrincipales.add(labelFecha, gridBagConstraints2);
				panelDatosPrincipales.add(getPanelTipo(), gridBagConstraints9);
			}
			return panelDatosPrincipales;
		}

		/**
		 * This method initializes botonBusqueda	
		 * 	
		 * @return javax.swing.JButton	
		 */
		public JButton getBotonBusqueda() {
			if (botonBusqueda == null) {
				botonBusqueda = new JButton("BUSCAR IMAGEN");
				botonBusqueda.setMnemonic('B');
				botonBusqueda.registerKeyboardAction(botonBusqueda.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
	                    JComponent.WHEN_FOCUSED);

				botonBusqueda.registerKeyboardAction(botonBusqueda.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
	                    JComponent.WHEN_FOCUSED);
				//botonBusqueda.setPreferredSize(new Dimension(160, 30));
				botonBusqueda.setIcon(new ImageIcon(getClass().getResource("/icono/search.jpg")));		
			}
			return botonBusqueda;
		}

		/**
		 * This method initializes panelTipo	
		 * 	
		 * @return javax.swing.JPanel	
		 */
		private JPanel getPanelTipo() {
			if (panelTipo == null) {
				GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
				gridBagConstraints4.fill = GridBagConstraints.VERTICAL;
				gridBagConstraints4.gridy = 0;
				gridBagConstraints4.weightx = 1.0;
				gridBagConstraints4.gridx = 0;
				GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
				gridBagConstraints7.gridx = 1;
				gridBagConstraints7.insets = new Insets(0, 3, 0, 0);
				gridBagConstraints7.gridy = 0;
				panelTipo = new JPanel();
				panelTipo.setLayout(new GridBagLayout());
				panelTipo.add(getBotonAgregarTipo(), gridBagConstraints7);
				panelTipo.add(getCampoTipo(), gridBagConstraints4);
			}
			return panelTipo;
		}

		/**
		 * This method initializes botonAgregarTipo	
		 * 	
		 * @return javax.swing.JButton	
		 */
		public JButton getBotonAgregarTipo() {
			if (botonAgregarTipo == null) {
				botonAgregarTipo = new JButton();
				botonAgregarTipo.registerKeyboardAction(botonAgregarTipo.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
	                    JComponent.WHEN_FOCUSED);

				botonAgregarTipo.registerKeyboardAction(botonAgregarTipo.getActionForKeyStroke(
	                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
	                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
	                    JComponent.WHEN_FOCUSED);
				botonAgregarTipo.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			}
			return botonAgregarTipo;
		}
} 
