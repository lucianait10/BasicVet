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

package cuGestionarVenta;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

public class GUIVenta extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JPanel panelDatosFijos = null;
	private JTextField textoFecha = null;
	private JLabel labelFecha = null;
	private JLabel labelCliente = null;
	private JTextField textoDni = null;
	private JLabel labelDni = null;
	private JTextField textoNombre = null;
	private JLabel labelNombreCli = null;
	private JTextField textoApellidoCli = null;
	private JLabel labelApellido = null;
	private JButton botonBuscarCliente = null;
	private JLabel labelProd = null;
	private JTextField textoIdProd = null;
	private JLabel labelId = null;
	private JLabel labelNombreProd = null;
	private JTextField textoNombreProd = null;
	private JLabel labelTipo = null;
	private JTextField textoTipoProd = null;
	private JButton botonBuscarProd = null;
	private JPanel panelDatosVenta = null;
	private JLabel labelPrecioVta = null;
	private JLabel labelCant = null;
	@SuppressWarnings("unused")
	private DefaultTableModel modelo;
	private JTextField textoCant;
	private JTextField textoPrecio;

	/**
	 * This is the default constructor
	 */
	public GUIVenta(DefaultTableModel m) {
		super();
		this.modelo=m;
		cerrarVentana();
		initialize();
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 331);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setContentPane(getJContentPane());
		this.setTitle("");
		this.setModal(true);
		this.setLocationRelativeTo(null);
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
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getPanelDatosFijos(), BorderLayout.NORTH);
			jContentPane.add(getPanelDatosVenta(), BorderLayout.CENTER);
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
	public JButton getBotonAceptar() {
		if (botonAceptar == null) {
			botonAceptar = new JButton();
			botonAceptar.setText("ACEPTAR");
			botonAceptar.setMnemonic('A');
			botonAceptar.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
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
	public JButton getBotonCancelar() {
		if (botonCancelar == null) {
			botonCancelar = new JButton();
			botonCancelar.setText("CANCELAR");
			botonCancelar.setMnemonic('C');
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
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
	 * This method initializes panelDatosFijos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosFijos() {
		if (panelDatosFijos == null) {
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.gridx = 7;
			gridBagConstraints81.gridy = 4;
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.fill = GridBagConstraints.NONE;
			gridBagConstraints71.gridy = 4;
			gridBagConstraints71.weightx = 1.0;
			gridBagConstraints71.anchor = GridBagConstraints.WEST;
			gridBagConstraints71.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints71.ipadx = 120;
			gridBagConstraints71.gridx = 6;
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.gridx = 5;
			gridBagConstraints61.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints61.anchor = GridBagConstraints.EAST;
			gridBagConstraints61.gridy = 4;
			labelTipo = new JLabel();
			labelTipo.setText("TIPO:");
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.fill = GridBagConstraints.NONE;
			gridBagConstraints51.gridy = 4;
			gridBagConstraints51.weightx = 1.0;
			gridBagConstraints51.anchor = GridBagConstraints.WEST;
			gridBagConstraints51.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints51.ipadx = 170;
			gridBagConstraints51.gridx = 3;
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 2;
			gridBagConstraints41.anchor = GridBagConstraints.EAST;
			gridBagConstraints41.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints41.gridy = 4;
			labelNombreProd = new JLabel();
			labelNombreProd.setText("NOMBRE:");
			GridBagConstraints gridBagConstraints31 = new GridBagConstraints();
			gridBagConstraints31.gridx = 0;
			gridBagConstraints31.anchor = GridBagConstraints.EAST;
			gridBagConstraints31.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints31.gridy = 4;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.fill = GridBagConstraints.NONE;
			gridBagConstraints21.gridy = 4;
			gridBagConstraints21.weightx = 1.0;
			gridBagConstraints21.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.ipadx = 80;
			gridBagConstraints21.gridx = 1;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints11.gridy = 3;
			labelProd = new JLabel();
			labelProd.setText("PRODUCTO:");
			labelProd.setFont(new Font("Arial", Font.BOLD, 16));
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 7;
			gridBagConstraints9.gridy = 2;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.gridx = 5;
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints8.gridy = 2;
			labelApellido = new JLabel();
			labelApellido.setText("APELLIDO:");
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.fill = GridBagConstraints.NONE;
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.weightx = 1.0;
			gridBagConstraints7.anchor = GridBagConstraints.WEST;
			gridBagConstraints7.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints7.ipadx = 120;
			gridBagConstraints7.gridx = 6;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 2;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints6.gridy = 2;
			labelNombreCli = new JLabel();
			labelNombreCli.setText("NOMBRE:");
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			gridBagConstraints5.gridy = 2;
			gridBagConstraints5.weightx = 1.0;
			gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints5.ipadx = 170;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.gridx = 3;
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.gridx = 0;
			gridBagConstraints4.anchor = GridBagConstraints.EAST;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints4.gridy = 2;
			labelDni = new JLabel();
			labelDni.setText("DNI:");
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.NONE;
			gridBagConstraints3.gridy = 2;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints3.ipadx = 80;
			gridBagConstraints3.gridx = 1;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 0;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(5, 10, 5, 5);
			gridBagConstraints2.gridy = 1;
			labelCliente = new JLabel();
			labelCliente.setText("CLIENTE:");
			labelCliente.setFont(new Font("Arial", Font.BOLD, 16));
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 6;
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.insets = new Insets(5, 100, 5, 0);
			gridBagConstraints1.gridy = 0;
			labelFecha = new JLabel();
			labelFecha.setText("FECHA:");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.NONE;
			gridBagConstraints.anchor = GridBagConstraints.EAST;
			gridBagConstraints.insets = new Insets(5, 5, 5, 10);
			gridBagConstraints.ipadx = 80;
			gridBagConstraints.gridx = 7;
			gridBagConstraints.weightx = 1.0;
			panelDatosFijos = new JPanel();
			panelDatosFijos.setLayout(new GridBagLayout());
			panelDatosFijos.add(getTextoFecha(), gridBagConstraints);
			panelDatosFijos.add(labelFecha, gridBagConstraints1);
			panelDatosFijos.add(labelCliente, gridBagConstraints2);
			panelDatosFijos.add(getTextoDni(), gridBagConstraints3);
			panelDatosFijos.add(labelDni, gridBagConstraints4);
			panelDatosFijos.add(getTextoNombre(), gridBagConstraints5);
			panelDatosFijos.add(labelNombreCli, gridBagConstraints6);
			panelDatosFijos.add(getTextoApellidoCli(), gridBagConstraints7);
			panelDatosFijos.add(labelApellido, gridBagConstraints8);
			panelDatosFijos.add(getBotonBuscarCliente(), gridBagConstraints9);
			panelDatosFijos.add(labelProd, gridBagConstraints11);
			panelDatosFijos.add(getTextoIdProd(), gridBagConstraints21);
			panelDatosFijos.add(getLabelId(), gridBagConstraints31);
			panelDatosFijos.add(labelNombreProd, gridBagConstraints41);
			panelDatosFijos.add(getTextoNombreProd(), gridBagConstraints51);
			panelDatosFijos.add(labelTipo, gridBagConstraints61);
			panelDatosFijos.add(getTextoTipoProd(), gridBagConstraints71);
			panelDatosFijos.add(getBotonBuscarProd(), gridBagConstraints81);
		}
		return panelDatosFijos;
	}

	/**
	 * This method initializes textoFecha	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoFecha() {
		if (textoFecha == null) {
			textoFecha = new JTextField();
			textoFecha.setEditable(false);
			textoFecha.setBackground(new Color(238, 238, 238));
		}
		return textoFecha;
	}

	/**
	 * This method initializes textoDni	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoDni() {
		if (textoDni == null) {
			textoDni = new JTextField();
			textoDni.setEditable(false);
			textoDni.setBackground(new Color(238, 238, 238));
		}
		return textoDni;
	}

	/**
	 * This method initializes textoNombre	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoNombre() {
		if (textoNombre == null) {
			textoNombre = new JTextField();
			textoNombre.setEditable(false);
			textoNombre.setBackground(new Color(238, 238, 238));
		}
		return textoNombre;
	}

	/**
	 * This method initializes textoApellidoCli	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoApellidoCli() {
		if (textoApellidoCli == null) {
			textoApellidoCli = new JTextField();
			textoApellidoCli.setEditable(false);
			textoApellidoCli.setBackground(new Color(238, 238, 238));
		}
		return textoApellidoCli;
	}

	/**
	 * This method initializes botonBuscarCliente	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonBuscarCliente() {
		if (botonBuscarCliente == null) {
			botonBuscarCliente = new JButton();
			botonBuscarCliente.setIcon(new ImageIcon(getClass().getResource("/icono/search.jpg")));
			botonBuscarCliente.registerKeyboardAction(botonBuscarCliente.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonBuscarCliente.registerKeyboardAction(botonBuscarCliente.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonBuscarCliente;
	}

	/**
	 * This method initializes textoIdProd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoIdProd() {
		if (textoIdProd == null) {
			textoIdProd = new JTextField();
			textoIdProd.setEditable(false);
			textoIdProd.setBackground(new Color(238, 238, 238));
		}
		return textoIdProd;
	}

	/**
	 * This method initializes labelId	
	 * 	
	 * @return javax.swing.JLabel	
	 */
	private JLabel getLabelId() {
		if (labelId == null) {
			labelId = new JLabel();
			labelId.setText("ID:");
		}
		return labelId;
	}

	/**
	 * This method initializes textoNombreProd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoNombreProd() {
		if (textoNombreProd == null) {
			textoNombreProd = new JTextField();
			textoNombreProd.setEditable(false);
			textoNombreProd.setBackground(new Color(238, 238, 238));
		}
		return textoNombreProd;
	}

	/**
	 * This method initializes textoTipoProd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoTipoProd() {
		if (textoTipoProd == null) {
			textoTipoProd = new JTextField();
			textoTipoProd.setEditable(false);
			textoTipoProd.setBackground(new Color(238, 238, 238));
		}
		return textoTipoProd;
	}

	/**
	 * This method initializes botonBuscarProd	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonBuscarProd() {
		if (botonBuscarProd == null) {
			botonBuscarProd = new JButton();
			botonBuscarProd.setIcon(new ImageIcon(getClass().getResource("/icono/search.jpg")));
			botonBuscarProd.registerKeyboardAction(botonBuscarProd.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonBuscarProd.registerKeyboardAction(botonBuscarProd.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonBuscarProd;
	}

	/**
	 * This method initializes panelDatosVenta	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosVenta() {
		if (panelDatosVenta == null) {
			GridBagConstraints gridBagConstraints14 = new GridBagConstraints();
			gridBagConstraints14.fill = GridBagConstraints.NONE;
			gridBagConstraints14.gridx = 1;
			gridBagConstraints14.gridy = 1;
			gridBagConstraints14.weightx = 1.0;
			gridBagConstraints14.anchor = GridBagConstraints.WEST;
			gridBagConstraints14.ipadx = 100;
			gridBagConstraints14.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints13.gridy = 1;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.gridx = 0;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.NONE;
			gridBagConstraints12.gridx = 1;
			gridBagConstraints12.gridy = 0;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.ipadx = 100;
			gridBagConstraints12.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints10.gridy = 0;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.gridx = 0;
			panelDatosVenta = new JPanel();
			panelDatosVenta.setLayout(new GridBagLayout());
			labelCant = new JLabel();
			labelCant.setHorizontalAlignment(SwingConstants.RIGHT);
			labelCant.setText("CANTIDAD VENTA:");
			labelCant.setFont(new Font("Arial", Font.ITALIC, 12));
			labelPrecioVta = new JLabel();
			labelPrecioVta.setText("PRECIO UNITARIO:     $");
			labelPrecioVta.setFont(new Font("Arial", Font.ITALIC, 12));
			panelDatosVenta.add(labelCant, gridBagConstraints10);
			panelDatosVenta.add(getTextoCant(), gridBagConstraints12);
			panelDatosVenta.add(labelPrecioVta, gridBagConstraints13);
			panelDatosVenta.add(getTextoPrecio(), gridBagConstraints14);
		}
		return panelDatosVenta;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonBuscarCliente.addActionListener(al);
		this.botonBuscarProd.addActionListener(al);
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
	}
	
	public JTextField getTextoCant() {
		if (textoCant == null) {
			textoCant = new JTextField();
			textoCant.setSize(new Dimension(20, 0));
			textoCant.setBackground(Color.GREEN);
			textoCant.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')) ev.consume();}
			});
		}
		return textoCant;
	}
	public JTextField getTextoPrecio() {
		if (textoPrecio == null) {
			textoPrecio = new JTextField();
			textoPrecio.setSize(new Dimension(20, 0));
			textoPrecio.setBackground(Color.GREEN);
			textoPrecio.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='.')) ev.consume();}
			});
		}
		return textoPrecio;
	}
}  
