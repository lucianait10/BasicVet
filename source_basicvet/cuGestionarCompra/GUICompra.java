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

package cuGestionarCompra;

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
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("unused")
public class GUICompra extends JDialog {
  
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelDatosFijos = null;
	private JTextField textoFecha = null;
	private JLabel labelFecha = null;
	private JTextField textoPrecio = null;
	private JTextField textoCantProd = null;
	private JButton botonBuscar = null;
	private JButton botonAgregarProv = null;
	private JPanel panelBotones = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private DefaultTableModel modelo;
	private JLabel labelProv = null;
	private JLabel labelProd = null;
	private JComboBox comboProveedor = null;
	private JLabel labelIdProd = null;
	private JLabel labelNombreProd = null;
	private JTextField textoIdProd = null;
	private JTextField textoNombreProd = null;
	private JPanel panelProducto = null;
	private JLabel labelCantProd = null;
	private JLabel labelPrecioProd = null;
	/**
	 * @param owner
	 */
	public GUICompra(DefaultTableModel m) {
		super();
		this.modelo=m;
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
		this.setSize(800, 298);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("");
		this.setContentPane(getJContentPane());
		this.setModal(true);
		this.setLocationRelativeTo(null);  
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
			jContentPane.add(getPanelDatosFijos(), BorderLayout.NORTH);
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getPanelProducto(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelDatosFijos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelDatosFijos() {
		if (panelDatosFijos == null) {
			panelDatosFijos= new JPanel();
			GridBagConstraints gridBagConstraints132 = new GridBagConstraints();
			gridBagConstraints132.fill = GridBagConstraints.NONE;
			gridBagConstraints132.gridy = 2;
			gridBagConstraints132.weightx = 1.0;
			gridBagConstraints132.anchor = GridBagConstraints.WEST;
			gridBagConstraints132.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints132.ipadx = 180;
			gridBagConstraints132.gridx = 11;
			GridBagConstraints gridBagConstraints122 = new GridBagConstraints();
			gridBagConstraints122.fill = GridBagConstraints.NONE;
			gridBagConstraints122.gridy = 2;
			gridBagConstraints122.weightx = 1.0;
			gridBagConstraints122.anchor = GridBagConstraints.WEST;
			gridBagConstraints122.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints122.ipadx = 70;
			gridBagConstraints122.gridx = 8;
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 9;
			gridBagConstraints9.anchor = GridBagConstraints.EAST;
			gridBagConstraints9.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints9.gridy = 2;
			labelNombreProd = new JLabel();
			labelNombreProd.setText("NOMBRE:");
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints8.gridy = 2;
			gridBagConstraints8.weightx = 1.0;
			gridBagConstraints8.gridx = 10;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 7;
			gridBagConstraints6.anchor = GridBagConstraints.EAST;
			gridBagConstraints6.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints6.gridy = 2;
			labelIdProd = new JLabel();
			labelIdProd.setText("ID:");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.gridy = 2;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints4.ipadx = 140;
			gridBagConstraints4.gridx = 0;
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.gridx = 7;
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.gridy = 1;
			labelProd = new JLabel();
			labelProd.setText("PRODUCTO");
			labelProd.setFont(new Font("Arial", Font.BOLD, 16));
			GridBagConstraints gridBagConstraints15 = new GridBagConstraints();
			gridBagConstraints15.gridx = 0;
			gridBagConstraints15.anchor = GridBagConstraints.WEST;
			gridBagConstraints15.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints15.gridy = 1;
			labelProv = new JLabel();
			labelProv.setText("PROVEEDOR");
			labelProv.setFont(new Font("Arial", Font.BOLD, 16));
			GridBagConstraints gridBagConstraints131 = new GridBagConstraints();
			gridBagConstraints131.anchor = GridBagConstraints.WEST;
			gridBagConstraints131.gridx = 12;
			gridBagConstraints131.gridy = 2;
			gridBagConstraints131.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
			gridBagConstraints121.anchor = GridBagConstraints.WEST;
			gridBagConstraints121.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints121.gridx = 10;
			gridBagConstraints121.gridy = 2;
			gridBagConstraints121.ipadx = 150;
			gridBagConstraints121.weightx = 1.0;
			gridBagConstraints121.fill = GridBagConstraints.NONE;
			GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
			gridBagConstraints111.insets = new Insets(0, 0, 5, 5);
			gridBagConstraints111.gridy = 2;
			gridBagConstraints111.gridx = 9;
			GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
			gridBagConstraints91.anchor = GridBagConstraints.EAST;
			gridBagConstraints91.gridx = 7;
			gridBagConstraints91.gridy = 2;
			gridBagConstraints91.insets = new Insets(5, 25, 5, 5);
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.insets = new Insets(0, 5, 5, 5);
			gridBagConstraints7.gridy = 2;
			gridBagConstraints7.gridx = 5;
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.anchor = GridBagConstraints.EAST;
			gridBagConstraints1.gridx = 11;
			gridBagConstraints1.gridy = 0;
			gridBagConstraints1.insets = new Insets(5, 5, 30, 5);
			labelFecha = new JLabel();
			labelFecha.setText("FECHA:");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(5, 5, 30, 5);
			gridBagConstraints.gridx = 12;
			gridBagConstraints.ipadx = 100;
			//gridBagConstraints.weightx = 1.0;
			gridBagConstraints.gridy = 0;
			gridBagConstraints.fill = GridBagConstraints.NONE;
			panelDatosFijos.setLayout(new GridBagLayout());
			panelDatosFijos.add(getTextoFecha(), gridBagConstraints);
			panelDatosFijos.add(labelFecha, gridBagConstraints1);
			panelDatosFijos.add(getBotonAgregarProv(), gridBagConstraints7);
			panelDatosFijos.add(getBotonBuscar(), gridBagConstraints131);
			panelDatosFijos.add(labelProv, gridBagConstraints15);
			panelDatosFijos.add(labelProd, gridBagConstraints2);
			panelDatosFijos.add(getComboProveedor(), gridBagConstraints4);
			panelDatosFijos.add(labelIdProd, gridBagConstraints6);
			panelDatosFijos.add(labelNombreProd, gridBagConstraints9);
			panelDatosFijos.add(getTextoIdProd(), gridBagConstraints122);
			panelDatosFijos.add(getTextoNombreProd(), gridBagConstraints132);
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
	 * This method initializes textoPrecio	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoPrecio() {
		if (textoPrecio == null) {
			textoPrecio = new JTextField();
			textoPrecio.setBackground(Color.green);
			textoPrecio.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !(ev.getKeyChar()=='.')) ev.consume();}
			});
		}
		return textoPrecio;
	}

	/**
	 * This method initializes textoCantProd	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoCantProd() {
		if (textoCantProd == null) {
			textoCantProd = new JTextField();
			textoCantProd.setBackground(Color.green);
			textoCantProd.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')) ev.consume();}
			});
		}
		return textoCantProd;
	}

	/**
	 * This method initializes botonBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonBuscar() {
		if (botonBuscar == null) {
			botonBuscar = new JButton();
			botonBuscar.setToolTipText("Buscar Producto");
			botonBuscar.setText("");
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

	/**
	 * This method initializes botonAgregarProv	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAgregarProv() {
		if (botonAgregarProv == null) {
			botonAgregarProv = new JButton();
			botonAgregarProv.setToolTipText("Agregar Proveedor");
			botonAgregarProv.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
			botonAgregarProv.registerKeyboardAction(botonAgregarProv.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);
			botonAgregarProv.registerKeyboardAction(botonAgregarProv.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAgregarProv;
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
			//botonAceptar.setPreferredSize(new Dimension(108, 30));
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
			//botonCancelar.setPreferredSize(new Dimension(116, 30));
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
	
	public void setListenerButtons(ActionListener al){
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonBuscar.addActionListener(al);
		this.botonAgregarProv.addActionListener(al);
	}


	/**
	 * This method initializes comboProveedor	
	 * 	
	 * @return javax.swing.JComboBox	
	 */

	public JComboBox getComboProveedor() {
		if (comboProveedor == null) {
			comboProveedor = new JComboBox();
		}
		return comboProveedor;
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
	 * This method initializes panelProducto	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelProducto() {
		if (panelProducto == null) {
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 0;
			gridBagConstraints12.insets = new Insets(20, 5, 5, 5);
			gridBagConstraints12.anchor = GridBagConstraints.EAST;
			gridBagConstraints12.gridy = 1;
			labelPrecioProd = new JLabel();
			labelPrecioProd.setText("PRECIO UNITARIO:    $");
			labelPrecioProd.setFont(new Font("Arial", Font.ITALIC, 12));
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.anchor = GridBagConstraints.WEST;
			gridBagConstraints11.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints11.gridy = 0;
			labelCantProd = new JLabel();
			labelCantProd.setText("CANTIDAD COMPRA:");
			labelCantProd.setFont(new Font("Arial", Font.ITALIC, 12));
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.fill = GridBagConstraints.NONE;
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.gridy = 1;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.insets = new Insets(20, 5, 5, 5);
			gridBagConstraints10.ipadx = 100;
			gridBagConstraints10.weightx = 1.0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			gridBagConstraints5.gridx = 1;
			gridBagConstraints5.anchor = GridBagConstraints.WEST;
			gridBagConstraints5.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints5.ipadx = 100;
			gridBagConstraints5.weightx = 1.0;
			panelProducto = new JPanel();
			panelProducto.setLayout(new GridBagLayout());
			panelProducto.add(getTextoCantProd(), gridBagConstraints5);
			panelProducto.add(getTextoPrecio(), gridBagConstraints10);
			panelProducto.add(labelCantProd, gridBagConstraints11);
			panelProducto.add(labelPrecioProd, gridBagConstraints12);
		}
		return panelProducto;
	}


	


}  //  @jve:decl-index=0:visual-constraint="10,10"
