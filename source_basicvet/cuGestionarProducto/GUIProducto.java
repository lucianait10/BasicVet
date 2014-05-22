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

package cuGestionarProducto;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class GUIProducto extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JPanel panelDatos = null;
	private JTextField textoNombre = null;
	private JLabel labelNombre = null;
	private JTextField textoDescripcion = null;
	private JLabel labelDescripcion = null;
	private JTextField textoPrecio = null;
	private JLabel labelPrecio = null;
	private JRadioButton opcionVacuna = null;
	private JRadioButton opcionAntip = null;
	private JRadioButton opcionAntic = null;
	private JRadioButton opcionOtro = null;
	private JLabel labelLab = null;
	private JLabel labelSerie = null;
	private JTextField textoLab = null;
	private JTextField textoSerie = null;
	private JTextField textoOtro = null;
	private JPanel panelTipo = null;
	private ButtonGroup group = new ButtonGroup();
	private JTextField textoCantidad = null;
	private JLabel labelCant = null;
	@SuppressWarnings("unused")
	private DefaultTableModel modeloProd;
	/**
	 * @param owner
	 */
	public GUIProducto(DefaultTableModel m) {

		super();
		this.modeloProd=m;
		cerrarVentana();
		initialize();
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
	    	
			public void windowClosing(WindowEvent evt){
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
		this.setSize(725, 306);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("ALTA PRODUCTO");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setModal(true);
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getPanelTipo(), BorderLayout.NORTH);
			jContentPane.add(getPanelDatos(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(getBotonAceptar(), null);
			panelBotones.add(getBotonCancelar(), null);
		}
		return panelBotones;
	}

	/**
	 * This method initializes botonAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	JButton getBotonAceptar() {
		if (botonAceptar == null) {
			botonAceptar = new JButton();
			botonAceptar.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
			botonAceptar.setMnemonic('a');
			botonAceptar.setText("ACEPTAR");
			botonAceptar.registerKeyboardAction(botonAceptar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAceptar.registerKeyboardAction(botonAceptar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);		

			
		}
		return botonAceptar;
	}

	/**
	 * This method initializes botonCancelar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton();
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
			botonCancelar.setMnemonic('c');
			botonCancelar.setText("CANCELAR");
			botonCancelar.registerKeyboardAction(botonCancelar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonCancelar.registerKeyboardAction(botonCancelar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);		
		}
		return botonCancelar;
	}

	/**
	 * This method initializes panelDatos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatos() {
		if (panelDatos == null) {
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 2;
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints21.gridy = 7;
			labelCant = new JLabel();
			labelCant.setText("CANTIDAD:");
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints14.gridy = 7;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints14.anchor = GridBagConstraints.WEST;
			gridBagConstraints14.ipadx = 70;
			gridBagConstraints14.gridx = 3;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints13.gridy = 9;
			gridBagConstraints13.weightx = 1.0;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints13.ipadx = 200;
			gridBagConstraints13.gridx = 3;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints12.gridy = 8;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints12.ipadx = 200;
			gridBagConstraints12.gridx = 3;
			GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
			gridBagConstraints111.gridx = 2;
			gridBagConstraints111.anchor = GridBagConstraints.WEST;
			gridBagConstraints111.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints111.gridy = 9;
			labelSerie = new JLabel();
			labelSerie.setText("SERIE:");
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints10.gridy = 8;
			labelLab = new JLabel();
			labelLab.setText("LABORATORIO:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 2;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints7.gridy = 6;
			labelPrecio = new JLabel();
			labelPrecio.setText("PRECIO:");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints6.gridy = 6;
			gridBagConstraints6.weightx = 1.0;
			gridBagConstraints6.ipadx = 100;
			gridBagConstraints6.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.gridx = 3;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 2;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints5.gridy = 3;
			labelDescripcion = new JLabel();
			labelDescripcion.setText("DESCRIPCION:");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.gridy = 3;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.ipadx = 350;
			gridBagConstraints4.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.gridx = 3;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 2;
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints1.gridy = 2;
			labelNombre = new JLabel();
			labelNombre.setText("NOMBRE:");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.gridy = 2;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.ipadx = 200;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(5, 10, 5, 0);
			gridBagConstraints.gridx = 3;
			panelDatos = new JPanel();
			panelDatos.setLayout(new GridBagLayout());
			panelDatos.add(getTextoNombre(), gridBagConstraints);
			panelDatos.add(labelNombre, gridBagConstraints1);
			panelDatos.add(getTextoDescripcion(), gridBagConstraints4);
			panelDatos.add(labelDescripcion, gridBagConstraints5);
			panelDatos.add(getTextoPrecio(), gridBagConstraints6);
			panelDatos.add(labelPrecio, gridBagConstraints7);
			panelDatos.add(labelLab, gridBagConstraints10);
			panelDatos.add(labelSerie, gridBagConstraints111);
			panelDatos.add(getTextoLab(), gridBagConstraints12);
			panelDatos.add(getTextoSerie(), gridBagConstraints13);
			panelDatos.add(getTextoCantidad(), gridBagConstraints14);
			panelDatos.add(labelCant, gridBagConstraints21);
		}
		return panelDatos;
	}



	/**
	 * This method initializes panelTipo	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelTipo() {
		if (panelTipo == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.fill = GridBagConstraints.NONE;
			gridBagConstraints11.gridy = 0;
			gridBagConstraints11.weightx = 1.0;
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.insets = new Insets(5, 10, 5, 10);
			gridBagConstraints11.ipadx = 150;
			gridBagConstraints11.gridx = 5;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.anchor = GridBagConstraints.WEST;
			gridBagConstraints9.gridx = -1;
			gridBagConstraints9.gridy = -1;
			gridBagConstraints9.insets = new Insets(5, 10, 5, 10);
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.anchor = GridBagConstraints.WEST;
			gridBagConstraints8.gridx = -1;
			gridBagConstraints8.gridy = -1;
			gridBagConstraints8.insets = new Insets(5, 10, 5, 0);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.gridx = -1;
			gridBagConstraints2.gridy = -1;
			gridBagConstraints2.insets = new Insets(5, 10, 5, 0);
			panelTipo = new JPanel();
			panelTipo.setLayout(new GridBagLayout());
			panelTipo.add(getOpcionVacuna(), new GridBagConstraints());
			panelTipo.add(getOpcionAntip(), gridBagConstraints2);
			panelTipo.add(getOpcionAntic(), gridBagConstraints8);
			panelTipo.add(getOpcionOtro(), gridBagConstraints9);
			panelTipo.add(getTextoOtro(), gridBagConstraints11);
		}
		return panelTipo;
	}

	/**
	 * This method initializes textoNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoNombre() {
		if (textoNombre == null) {
			textoNombre = new JTextField();
		}
		return textoNombre;
	}

	/**
	 * This method initializes textoDescripcion	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoDescripcion() {
		if (textoDescripcion == null) {
			textoDescripcion = new JTextField();
		}
		return textoDescripcion;
	}

	/**
	 * This method initializes textoPrecio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoPrecio() {
		if (textoPrecio == null) {
			textoPrecio = new JTextField();
			textoPrecio.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='.')) ev.consume();}
			});
		}
		return textoPrecio;
	}

	public void setListenerButtons(ActionListener al){
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.opcionVacuna.addActionListener(al);
		this.opcionAntic.addActionListener(al);
		this.opcionAntip.addActionListener(al);
		this.opcionOtro.addActionListener(al);
	}

	/**
	 * This method initializes opcionVacuna	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getOpcionVacuna() {
		if (opcionVacuna == null) {
			opcionVacuna = new JRadioButton("VACUNA");
			opcionVacuna.setMnemonic(KeyEvent.VK_B);
			opcionVacuna.setActionCommand("VACUNA");
			this.group.add(opcionVacuna);

		}
		return opcionVacuna;
	}

	/**
	 * This method initializes opcionAntip	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getOpcionAntip() {
		if (opcionAntip == null) {
			opcionAntip = new JRadioButton("ANTIPARASITARIO");
			opcionAntip.setMnemonic(KeyEvent.VK_B);
			opcionAntip.setActionCommand("ANTIPARASITARIO");
			this.group.add(opcionAntip);
		}
		return opcionAntip;
	}

	/**
	 * This method initializes opcionAntic	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getOpcionAntic() {
		if (opcionAntic == null) {
			opcionAntic = new JRadioButton("ANTICONCEPTIVO");
			opcionAntic.setMnemonic(KeyEvent.VK_B);
			opcionAntic.setActionCommand("ANTICONCEPTIVO");
			this.group.add(opcionAntic);
		}
		return opcionAntic;
	}

	/**
	 * This method initializes opcionOtro	
	 * 	
	 * @return javax.swing.JRadioButton	
	 */
	public JRadioButton getOpcionOtro() {
		if (opcionOtro == null) {
			opcionOtro = new JRadioButton("OTRO");
			this.group.add(opcionOtro);
		}
		return opcionOtro;
	}


	public ButtonGroup getButtonGroup(){
		return this.group;
	}
	/**
	 * This method initializes textoLab	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoLab() {
		if (textoLab == null) {
			textoLab = new JTextField();
			textoLab.setEditable(false);
			textoLab.setBackground(new Color(238, 238, 238));
		}
		return textoLab;
	}

	/**
	 * This method initializes textoSerie	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoSerie() {
		if (textoSerie == null) {
			textoSerie = new JTextField();
			textoSerie.setEditable(false);
			textoSerie.setBackground(new Color(238, 238, 238));
		}
		return textoSerie;
	}

	/**
	 * This method initializes textoOtro	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoOtro() {
		if (textoOtro == null) {
			textoOtro = new JTextField();
			textoOtro.setEditable(false);
			textoOtro.setBackground(new Color(238, 238, 238));
		}
		return textoOtro;
	}

	/**
	 * This method initializes textoCantidad	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoCantidad() {
		if (textoCantidad == null) {
			textoCantidad = new JTextField();
			textoCantidad.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')) ev.consume();}
			});
		}
		return textoCantidad;
	}

}  //  @jve:decl-index=0:visual-constraint="22,-35"
