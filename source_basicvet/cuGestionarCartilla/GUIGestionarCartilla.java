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

package cuGestionarCartilla;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;

public class GUIGestionarCartilla extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelDatosAnimal = null;
	private JButton botonBuscar = null;
	private JButton botonAgregarDosis = null;
	private JButton botonEliminarDosis = null;
	private JButton botonModificarDosis = null;
	private JTabbedPane panelPestanias = null;
	private PanelVacuna vacunas= new PanelVacuna();
	private PanelAntiparasitario antiparasitarios= new PanelAntiparasitario();
	private PanelAnticonceptivo anticonceptivos = new PanelAnticonceptivo();
	private JTextField textoNombreAnimal = null;
	private JLabel labelNombre = null;
	private JTextField textoIdAnimal = null;
	private JLabel labeIdAnimal = null;
	private JPanel panelBoton = null;
	private JButton botonVer = null;
	private JButton botonCrearCartilla = null;
	private JButton botonEliminarCartilla = null;
	private JPanel panelBotonesCartilla = null;
	private JPanel contenedor = null;
	private JLabel labelId = null;
	private JTextField textoIdCartilla = null;
	private JLabel labelFecha = null;
	private JTextField textoFecha = null;
	private JButton botonReporte = null;

	public GUIGestionarCartilla() {
		super();
		initialize();
		cerrarVentana();
	}
	
	@SuppressWarnings({ "serial" })
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

	private void initialize() {
		this.setSize(800, 600);
		this.setPreferredSize(new Dimension(800, 600));
		this.setContentPane(getJContentPane());
		this.setTitle("GESTIONAR CARTILLA");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBoton(), BorderLayout.SOUTH);
			jContentPane.add(getContenedor(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	private JPanel getPanelDatosAnimal() {
		if (panelDatosAnimal == null) {
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints5.gridy = 1;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints5.ipadx = 150;
			gridBagConstraints5.gridx = 7;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 6;
			gridBagConstraints41.gridy = 1;
			labelFecha = new JLabel();
			labelFecha.setText("FECHA CREACION:");
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.fill = GridBagConstraints.NONE;
			gridBagConstraints31.gridy = 1;
			gridBagConstraints31.weightx = 1.0;
			gridBagConstraints31.ipadx = 150;
			gridBagConstraints31.anchor = GridBagConstraints.WEST;
			gridBagConstraints31.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints31.gridx = 5;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 3;
			gridBagConstraints21.gridy = 1;
			labelId = new JLabel();
			labelId.setText("ID CARTILLA:");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 3;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.gridy = 0;
			labeIdAnimal = new JLabel();
			labeIdAnimal.setText("ID ANIMAL");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.NONE;
			gridBagConstraints3.gridy = 0;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints3.ipadx = 150;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.gridx = 5;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 6;
			gridBagConstraints2.anchor = GridBagConstraints.EAST;
			gridBagConstraints2.gridy = 0;
			labelNombre = new JLabel();
			labelNombre.setText("NOMBRE:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.insets = new Insets(10, 10, 10, 10);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 150;
			gridBagConstraints.gridx = 7;
			panelDatosAnimal = new JPanel();
			panelDatosAnimal.setLayout(new GridBagLayout());
			panelDatosAnimal.add(getBotonBuscar(), gridBagConstraints1);
			panelDatosAnimal.add(getTextoNombreAnimal(), gridBagConstraints);
			panelDatosAnimal.add(labelNombre, gridBagConstraints2);
			panelDatosAnimal.add(getTextoIdAnimal(), gridBagConstraints3);
			panelDatosAnimal.add(labeIdAnimal, gridBagConstraints4);
			panelDatosAnimal.add(labelId, gridBagConstraints21);
			panelDatosAnimal.add(getTextoIdCartilla(), gridBagConstraints31);
			panelDatosAnimal.add(labelFecha, gridBagConstraints41);
			panelDatosAnimal.add(getTextoFecha(), gridBagConstraints5);
		}
		return panelDatosAnimal;
	}

	public JButton getBotonBuscar() {
		if (botonBuscar == null) {
			botonBuscar = new JButton();
			botonBuscar.setText("Buscar Animal");
			botonBuscar.setMnemonic('B');
			botonBuscar.setIcon(new ImageIcon(getClass().getResource("/icono/search.jpg")));
			botonBuscar.registerKeyboardAction(botonBuscar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonBuscar.registerKeyboardAction(botonBuscar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonBuscar;
	}

	public JButton getBotonAgregarDosis() {
		if (botonAgregarDosis == null) {
			botonAgregarDosis = new JButton();
			botonAgregarDosis.setPreferredSize(new Dimension(60, 50));
			botonAgregarDosis.setToolTipText("Agregar Dosis");
			botonAgregarDosis.setMnemonic('A');
			botonAgregarDosis.setIcon(new ImageIcon(getClass().getResource("/icono/AgregarDosis.png")));
			botonAgregarDosis.registerKeyboardAction(botonAgregarDosis.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarDosis.registerKeyboardAction(botonAgregarDosis.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAgregarDosis;
	}

	public JButton getBotonEliminarDosis() {
		if (botonEliminarDosis == null) {
			botonEliminarDosis = new JButton();
			botonEliminarDosis.setPreferredSize(new Dimension(60, 50));
			botonEliminarDosis.setToolTipText("Eliminar Dosis");
			botonEliminarDosis.setMnemonic('M');
			botonEliminarDosis.setIcon(new ImageIcon(getClass().getResource("/icono/EliminarDosis.png")));
			botonEliminarDosis.registerKeyboardAction(botonEliminarDosis.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarDosis.registerKeyboardAction(botonEliminarDosis.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonEliminarDosis;
	}

	public JTabbedPane getPanelPestanias() {
		if (panelPestanias == null) {
			panelPestanias = new JTabbedPane();
			panelPestanias.addTab("Vacunas", vacunas);
			panelPestanias.addTab("Antiparasitarios",antiparasitarios);
			panelPestanias.addTab("Anticonceptivos",anticonceptivos);
		}
		return panelPestanias;
	}

	public JTextField getTextoNombreAnimal() {
		if (textoNombreAnimal == null) {
			textoNombreAnimal = new JTextField();
			textoNombreAnimal.setText("");
			textoNombreAnimal.setPreferredSize(new Dimension(200, 20));
			textoNombreAnimal.setEditable(false);
			textoNombreAnimal.setBackground(new Color(238, 238, 238));
		}
		return textoNombreAnimal;
	}

	public JTextField getTextoIdAnimal() {
		if (textoIdAnimal == null) {
			textoIdAnimal = new JTextField();
			textoIdAnimal.setText("");
			textoIdAnimal.setPreferredSize(new Dimension(200, 20));
			textoIdAnimal.setEditable(false);
			textoIdAnimal.setBackground(new Color(238, 238, 238));
		}
		return textoIdAnimal;
	}

	private JPanel getPanelBoton() {
		if (panelBoton == null) {
			FlowLayout flowLayout1 = new FlowLayout();
			flowLayout1.setAlignment(java.awt.FlowLayout.CENTER);
			flowLayout1.setHgap(16);
			panelBoton = new JPanel();
			panelBoton.setLayout(flowLayout1);
			panelBoton.add(getBotonAgregarDosis(), null);
			panelBoton.add(getBotonEliminarDosis(), null);
			panelBoton.add(getBotonModificarDosis(), null);
			panelBoton.add(getBotonReporte(), null);
		}
		return panelBoton;
	}
	
	public JButton getBotonVer() {
		if (botonVer == null) {
			botonVer = new JButton();
			botonVer.setMnemonic('V');
			botonVer.setPreferredSize(new Dimension(45, 40));
			botonVer.setToolTipText("Ver Cartilla");
			botonVer.setIcon(new ImageIcon(getClass().getResource("/icono/ver.png")));
			botonVer.registerKeyboardAction(botonVer.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonVer.registerKeyboardAction(botonVer.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonVer;
	}

	public JButton getBotonCrearCartilla() {
		if (botonCrearCartilla == null) {
			botonCrearCartilla = new JButton();
			botonCrearCartilla.setIcon(new ImageIcon(getClass().getResource("/icono/document_add.png")));
			botonCrearCartilla.setFont(new Font("Dialog", Font.BOLD, 12));
			botonCrearCartilla.setName("");
			botonCrearCartilla.setToolTipText("Crear Cartilla");
			botonCrearCartilla.setPreferredSize(new Dimension(45, 40));
			botonCrearCartilla.registerKeyboardAction(botonCrearCartilla.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonCrearCartilla.registerKeyboardAction(botonCrearCartilla.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonCrearCartilla;
	}

	public JButton getBotonEliminarCartilla() {
		if (botonEliminarCartilla == null) {
			botonEliminarCartilla = new JButton();
			botonEliminarCartilla.setIcon(new ImageIcon(getClass().getResource("/icono/document_delete.png")));
			botonEliminarCartilla.setToolTipText("Eliminar Cartilla");
			botonEliminarCartilla.setPreferredSize(new Dimension(45, 40));
			botonEliminarCartilla.registerKeyboardAction(botonEliminarCartilla.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonEliminarCartilla.registerKeyboardAction(botonEliminarCartilla.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonEliminarCartilla;
	}

	private JPanel getPanelBotonesCartilla() {
		if (panelBotonesCartilla == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.insets = new Insets(13, 249, 13, 2);
			gridBagConstraints6.gridy = 0;
			gridBagConstraints6.gridx = 0;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = -1;
			gridBagConstraints10.gridy = -1;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = -1;
			gridBagConstraints9.gridy = -1;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = -1;
			gridBagConstraints7.gridy = -1;
			panelBotonesCartilla = new JPanel();
			panelBotonesCartilla.setLayout(flowLayout);
			panelBotonesCartilla.add(getBotonVer(), null);
			panelBotonesCartilla.add(getBotonCrearCartilla(), null);
			panelBotonesCartilla.add(getBotonEliminarCartilla(), null);
		}
		return panelBotonesCartilla;
	}

	private JPanel getContenedor() {
		if (contenedor == null) {
			contenedor = new JPanel();
			contenedor.setLayout(new BorderLayout());
			contenedor.add(getPanelBotonesCartilla(), BorderLayout.NORTH);
			contenedor.add(getPanelPestanias(), BorderLayout.SOUTH);
			contenedor.add(getPanelDatosAnimal(), BorderLayout.CENTER);
		}
		return contenedor;
	}

	public JTextField getTextoIdCartilla() {
		if (textoIdCartilla == null) {
			textoIdCartilla = new JTextField();
			textoIdCartilla.setText("");
			textoIdCartilla.setPreferredSize(new Dimension(200, 20));
			textoIdCartilla.setEditable(false);
			textoIdCartilla.setBackground(new Color(238, 238, 238));
		}
		return textoIdCartilla;
	}

	public JTextField getTextoFecha() {
		if (textoFecha == null) {
			textoFecha = new JTextField();
			textoFecha.setText("");
			textoFecha.setPreferredSize(new Dimension(200, 20));
			textoFecha.setEditable(false);
			textoFecha.setBackground(new Color(238, 238, 238));
			
		}
		return textoFecha;
	}
	
	
	public void setListenerButtons(ActionListener al){
		this.botonVer.addActionListener(al);
		this.botonCrearCartilla.addActionListener(al);
		this.botonEliminarCartilla.addActionListener(al);
		this.botonBuscar.addActionListener(al);
		this.botonAgregarDosis.addActionListener(al);
		this.botonEliminarDosis.addActionListener(al);
		this.botonModificarDosis.addActionListener(al);
		this.botonReporte.addActionListener(al);
	}

	public PanelVacuna getVacunas() {
		return (PanelVacuna)this.panelPestanias.getComponentAt(0);
	}


	public PanelAntiparasitario getAntiparasitarios() {
		return (PanelAntiparasitario)this.panelPestanias.getComponentAt(1);
	}


	public PanelAnticonceptivo getAnticonceptivos() {
		return (PanelAnticonceptivo)this.panelPestanias.getComponentAt(2);
	}

	public JButton getBotonReporte() {
		if (botonReporte == null) {
			botonReporte = new JButton();
			botonReporte.setIcon(new ImageIcon(getClass().getResource("/icono/impresora.png")));
			botonReporte.setPreferredSize(new Dimension(60, 50));
			botonReporte.setToolTipText("Reporte-Impresion");
			botonReporte.setMnemonic('R');
			botonReporte.registerKeyboardAction(botonReporte.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonReporte.registerKeyboardAction(botonReporte.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonReporte;
	}
	
	public PanelVacuna getPanelVacuna(){
		return vacunas;
	}
	public PanelAntiparasitario getPanelAntiparasitario(){
		return antiparasitarios;
	}
	public PanelAnticonceptivo getPanelAnticonceptivo(){
		return anticonceptivos;
	}
	
	public JButton getBotonModificarDosis() {
		if (botonModificarDosis == null) {
			botonModificarDosis = new JButton();
			botonModificarDosis.setPreferredSize(new Dimension(60, 50));
			botonModificarDosis.setToolTipText("Modificar Dosis");
			botonModificarDosis.setMnemonic('M');
			botonModificarDosis.setIcon(new ImageIcon(getClass().getResource("/icono/ModDosis.png")));
			botonModificarDosis.registerKeyboardAction(botonModificarDosis.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonModificarDosis.registerKeyboardAction(botonModificarDosis.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		
		return botonModificarDosis;
	}
}
