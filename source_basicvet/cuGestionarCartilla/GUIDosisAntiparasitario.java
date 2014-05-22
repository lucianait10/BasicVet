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
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;


public class GUIDosisAntiparasitario extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JPanel panelDatos = null;
	private JTextField textoNombre = null;  
	private JTextField textoCantidad = null;
	private JLabel labelFecha = null;
	private JLabel labelNombre = null;
	private JLabel labelCantidad = null;
	private JLabel labelFechaProxDosis = null;
	private JTextField textoVia = null;
	private JLabel labelVia = null;
	private JDateChooser fecha = null;
	private JDateChooser fechaProx = null;
	private JTextField textoTipo = null;
	private JLabel labelTipo = null;
	private JButton botonBuscar = null;
	@SuppressWarnings("unused")
	private DefaultTableModel modeloAntip;
	/**
	 * @param owner
	 */
	public GUIDosisAntiparasitario(DefaultTableModel modelo) {
		super();
		this.modeloAntip=modelo;
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
	public void initialize() {
		this.setSize(439, 330);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("ANTIPARASITARIO");
		this.setContentPane(getJContentPane());
		this.setLocationRelativeTo(null);
		this.setModal(true);
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	public JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getPanelDatos(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes panelBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPanelBotones() {
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
			botonAceptar.setMnemonic('a');
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
			botonCancelar.setMnemonic('c');
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
	 * This method initializes panelDatos	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	public JPanel getPanelDatos() {
		if (panelDatos == null) {
			GridBagConstraints gridBagConstraints121 = new GridBagConstraints();
			gridBagConstraints121.gridx = 2;
			gridBagConstraints121.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints121.gridy = 0;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			gridBagConstraints5.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints5.gridy = 1;
			labelTipo = new JLabel();
			labelTipo.setText("Tipo Medicamento:");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 150;
			gridBagConstraints.gridx = 1;
			GridBagConstraints gridBagConstraints22 = new GridBagConstraints();
			gridBagConstraints22.gridx = 1;
			gridBagConstraints22.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints22.ipadx = 70;
			gridBagConstraints22.anchor = GridBagConstraints.WEST;
			gridBagConstraints22.gridy = 4;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 1;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints13.ipadx = 70;
			gridBagConstraints13.gridy = 2;
			GridBagConstraints gridBagConstraints61 = new GridBagConstraints();
			gridBagConstraints61.gridx = 1;
			gridBagConstraints61.anchor = GridBagConstraints.WEST;
			gridBagConstraints61.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints61.ipadx = 150;
			gridBagConstraints61.gridy = 11;
			GridBagConstraints gridBagConstraints42 = new GridBagConstraints();
			gridBagConstraints42.gridx = 0;
			gridBagConstraints42.anchor = GridBagConstraints.EAST;
			gridBagConstraints42.fill = GridBagConstraints.NONE;
			gridBagConstraints42.gridy = 11;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 0;
			gridBagConstraints21.anchor = GridBagConstraints.EAST;
			gridBagConstraints21.fill = GridBagConstraints.NONE;
			gridBagConstraints21.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints21.gridy = 10;
			labelVia = new JLabel();
			labelVia.setText("Via:");
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints12.gridy = 10;
			gridBagConstraints12.weightx = 1.0;
			gridBagConstraints12.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.ipadx = 150;
			gridBagConstraints12.gridx = 1;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 0;
			gridBagConstraints10.anchor = GridBagConstraints.EAST;
			gridBagConstraints10.gridy = 4;
			labelFechaProxDosis = new JLabel();
			labelFechaProxDosis.setText("Proxima Dosis:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 4;
			gridBagConstraints9.ipady = 76;
			gridBagConstraints9.ipadx = 131;
			gridBagConstraints9.anchor = GridBagConstraints.NORTH;
			gridBagConstraints9.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints9.insets = new Insets(0, 0, 0, 103);
			gridBagConstraints9.gridwidth = 0;
			gridBagConstraints9.gridheight = 3;
			gridBagConstraints9.gridy = 0;
			GridBagConstraints gridBagConstraints8 = new GridBagConstraints();
			gridBagConstraints8.anchor = GridBagConstraints.EAST;
			gridBagConstraints8.gridy = 2;
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.fill = GridBagConstraints.NONE;
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.ipady = 0;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 8;
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 0;
			gridBagConstraints51.ipady = 0;
			gridBagConstraints51.anchor = GridBagConstraints.WEST;
			gridBagConstraints51.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints51.gridy = 7;
			labelCantidad = new JLabel();
			labelCantidad.setText("Cantidad:");
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 0;
			gridBagConstraints41.anchor = GridBagConstraints.EAST;
			gridBagConstraints41.fill = GridBagConstraints.NONE;
			gridBagConstraints41.ipadx = 0;
			gridBagConstraints41.ipady = 0;
			gridBagConstraints41.gridy = 0;
			labelNombre = new JLabel();
			labelNombre.setText("Nombre:");
			labelFecha = new JLabel();
			labelFecha.setText("Fecha:");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.gridx = 4;
			gridBagConstraints4.gridy = 8;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.ipadx = 150;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridx = 4;
			gridBagConstraints3.gridy = 7;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.ipadx = 150;
			gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.ipadx = 150;
			gridBagConstraints2.anchor = GridBagConstraints.WEST;
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.weightx = 1.0;
			panelDatos = new JPanel();
			panelDatos.setLayout(new GridBagLayout());
			panelDatos.add(labelFecha, gridBagConstraints8);
			panelDatos.add(getTextoNombre(), gridBagConstraints2);
			panelDatos.add(getTextoCantidad(), gridBagConstraints61);
			panelDatos.add(labelNombre, gridBagConstraints41);
			panelDatos.add(labelCantidad, gridBagConstraints42);
			panelDatos.add(labelFechaProxDosis, gridBagConstraints10);
			panelDatos.add(getTextoVia(), gridBagConstraints12);
			panelDatos.add(labelVia, gridBagConstraints21);
			panelDatos.add(getFecha(), gridBagConstraints13);
			panelDatos.add(getFechaProx(), gridBagConstraints22);
			panelDatos.add(getTextoTipo(), gridBagConstraints);
			panelDatos.add(labelTipo, gridBagConstraints5);
			panelDatos.add(getBotonBuscar(), gridBagConstraints121);
		}
		return panelDatos;
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
			textoNombre.selectAll();
		}
		return textoNombre;
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
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9')&& !( ev.getKeyChar()=='.')) ev.consume();}
			});
			textoCantidad.selectAll();
		}
		return textoCantidad;
	}

	/**
	 * This method initializes textoVia	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoVia() {
		if (textoVia == null) {
			textoVia = new JTextField();
			textoVia.selectAll();
		}
		return textoVia;
	}



	/**
	 * This method initializes fecha	
	 * 	
	 * @return com.toedter.calendar.JDateChooser	
	 */
	public JDateChooser getFecha() {
		if (fecha == null) {
			fecha = new JDateChooser();
		}
		return fecha;
	}
	
	public void setFecha(Date d){
		if (fecha != null) {
			fecha.setDate(d);
		}
	}

	/**
	 * This method initializes fechaProx	
	 * 	
	 * @return com.toedter.calendar.JDateChooser	
	 */
	public JDateChooser getFechaProx() {
		if (fechaProx == null) {
			fechaProx = new JDateChooser();
		}
		return fechaProx;
	}

	/**
	 * This method initializes textoTipo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoTipo() {
		if (textoTipo == null) {
			textoTipo = new JTextField();
			textoTipo.setEditable(false);
			textoTipo.setBackground(new Color(238, 238, 238));
		}
		textoTipo.selectAll();
		return textoTipo;
	}
		
	 	
	
	
	public void setListenerButtons(ActionListener al){
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonBuscar.addActionListener(al);
	}

	/**
	 * This method initializes botonBuscar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonBuscar() {
		if (botonBuscar == null) {
			botonBuscar = new JButton();
			botonBuscar.setText("BUSCAR");
			botonBuscar.setMnemonic('b');
			botonBuscar.setToolTipText("Buscar Medicamento");
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
}  //  @jve:decl-index=0:visual-constraint="178,13"
