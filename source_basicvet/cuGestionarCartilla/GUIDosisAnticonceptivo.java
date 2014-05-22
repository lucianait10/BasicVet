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

public class GUIDosisAnticonceptivo extends JDialog{

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JPanel panelDatos = null;  
	private JTextField textoNombre = null;
	private JTextField textoTipo = null;
	private JTextField textoLab = null;
	private JLabel labelFecha = null;
	private JLabel labelNombre = null;
	private JLabel labelTipo = null;
	private JLabel labelLab = null;
	private JLabel labelSerie = null;
	private JTextField textoVia = null;
	private JLabel labelVia = null;
	private JDateChooser fecha = null;
	private JDateChooser fechaProx = null;
	private JTextField textoCant = null;
	private JLabel labelCant = null;
	private JButton botonBuscar = null;
	@SuppressWarnings("unused")
	private DefaultTableModel modeloAntic;
	/**
	 * @param owner
	 */
	public GUIDosisAnticonceptivo(DefaultTableModel modelo) {
		super();
		this.modeloAntic=modelo;
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
		this.setSize(443, 330);
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setPreferredSize(new Dimension(404, 254));
		this.setTitle("ANTICONCEPTIVO");
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
			GridBagConstraints gridBagConstraints111 = new GridBagConstraints();
			gridBagConstraints111.gridx = 2;
			gridBagConstraints111.insets = new Insets(0, 0, 0, 10);
			gridBagConstraints111.gridy = 1;
			GridBagConstraints gridBagConstraints5 = new GridBagConstraints();
			gridBagConstraints5.gridx = 0;
			gridBagConstraints5.anchor = GridBagConstraints.EAST;
			gridBagConstraints5.fill = GridBagConstraints.NONE;
			gridBagConstraints5.insets = new Insets(0, 0, 0, 0);
			gridBagConstraints5.gridy = 12;
			labelCant = new JLabel();
			labelCant.setText("Cantidad:");
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridy = 12;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.ipadx = 150;
			gridBagConstraints.gridx = 1;
			GridBagConstraints gridBagConstraints21 = new GridBagConstraints();
			gridBagConstraints21.gridx = 1;
			gridBagConstraints21.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints21.ipadx = 70;
			gridBagConstraints21.anchor = GridBagConstraints.WEST;
			gridBagConstraints21.gridy = 9;
			GridBagConstraints gridBagConstraints13 = new GridBagConstraints();
			gridBagConstraints13.gridx = 1;
			gridBagConstraints13.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints13.ipadx = 70;
			gridBagConstraints13.anchor = GridBagConstraints.WEST;
			gridBagConstraints13.gridy = 8;
			GridBagConstraints gridBagConstraints12 = new GridBagConstraints();
			gridBagConstraints12.gridx = 1;
			gridBagConstraints12.anchor = GridBagConstraints.WEST;
			gridBagConstraints12.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints12.ipadx = 150;
			gridBagConstraints12.gridy = 7;
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.gridx = 0;
			gridBagConstraints11.anchor = GridBagConstraints.EAST;
			gridBagConstraints11.fill = GridBagConstraints.NONE;
			gridBagConstraints11.gridy = 7;
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 1;
			gridBagConstraints10.anchor = GridBagConstraints.WEST;
			gridBagConstraints10.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints10.ipadx = 150;
			gridBagConstraints10.gridy = 6;
			GridBagConstraints gridBagConstraints91 = new GridBagConstraints();
			gridBagConstraints91.gridx = 0;
			gridBagConstraints91.anchor = GridBagConstraints.EAST;
			gridBagConstraints91.fill = GridBagConstraints.NONE;
			gridBagConstraints91.insets = new Insets(0, 5, 0, 0);
			gridBagConstraints91.gridy = 6;
			GridBagConstraints gridBagConstraints81 = new GridBagConstraints();
			gridBagConstraints81.gridx = 0;
			gridBagConstraints81.anchor = GridBagConstraints.EAST;
			gridBagConstraints81.fill = GridBagConstraints.NONE;
			gridBagConstraints81.gridy = 13;
			labelVia = new JLabel();
			labelVia.setText("Via:");
			GridBagConstraints gridBagConstraints71 = new GridBagConstraints();
			gridBagConstraints71.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints71.gridy = 13;
			gridBagConstraints71.weightx = 1.0;
			gridBagConstraints71.anchor = GridBagConstraints.WEST;
			gridBagConstraints71.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints71.ipadx = 150;
			gridBagConstraints71.gridx = 1;
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
			gridBagConstraints8.gridy = 8;
			gridBagConstraints8.gridx = 0;
			gridBagConstraints8.fill = GridBagConstraints.NONE;
			GridBagConstraints gridBagConstraints7 = new GridBagConstraints();
			gridBagConstraints7.gridx = 0;
			gridBagConstraints7.ipady = 0;
			gridBagConstraints7.anchor = GridBagConstraints.EAST;
			gridBagConstraints7.fill = GridBagConstraints.NONE;
			gridBagConstraints7.gridy = 9;
			labelSerie = new JLabel();
			labelSerie.setText("Proxima Dosis:");
			GridBagConstraints gridBagConstraints6 = new GridBagConstraints();
			gridBagConstraints6.gridx = 0;
			gridBagConstraints6.ipady = 0;
			gridBagConstraints6.anchor = GridBagConstraints.WEST;
			gridBagConstraints6.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints6.gridy = 9;
			labelLab = new JLabel();
			labelLab.setText("Laboratorio:");
			GridBagConstraints gridBagConstraints51 = new GridBagConstraints();
			gridBagConstraints51.gridx = 0;
			gridBagConstraints51.ipady = 0;
			gridBagConstraints51.anchor = GridBagConstraints.WEST;
			gridBagConstraints51.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints51.gridy = 8;
			labelTipo = new JLabel();
			labelTipo.setText("Tipo Medicamento:");
			GridBagConstraints gridBagConstraints41 = new GridBagConstraints();
			gridBagConstraints41.gridx = 0;
			gridBagConstraints41.anchor = GridBagConstraints.EAST;
			gridBagConstraints41.fill = GridBagConstraints.NONE;
			gridBagConstraints41.ipadx = 0;
			gridBagConstraints41.ipady = 0;
			gridBagConstraints41.gridy = 1;
			labelNombre = new JLabel();
			labelNombre.setText("Nombre:");
			labelFecha = new JLabel();
			labelFecha.setText("Fecha Dosis:");
			GridBagConstraints gridBagConstraints4 = new GridBagConstraints();
			gridBagConstraints4.fill = GridBagConstraints.NONE;
			gridBagConstraints4.gridx = 4;
			gridBagConstraints4.gridy = 9;
			gridBagConstraints4.weightx = 1.0;
			gridBagConstraints4.ipadx = 150;
			gridBagConstraints4.anchor = GridBagConstraints.WEST;
			gridBagConstraints4.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints3.gridx = 4;
			gridBagConstraints3.gridy = 8;
			gridBagConstraints3.weightx = 1.0;
			gridBagConstraints3.anchor = GridBagConstraints.WEST;
			gridBagConstraints3.ipadx = 150;
			gridBagConstraints3.insets = new Insets(5, 5, 5, 5);
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.NONE;
			gridBagConstraints2.gridx = 1;
			gridBagConstraints2.gridy = 1;
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
			panelDatos.add(getTextoTipo(), gridBagConstraints10);
			panelDatos.add(getTextoLab(), gridBagConstraints12);
			panelDatos.add(labelNombre, gridBagConstraints41);
			panelDatos.add(labelTipo, gridBagConstraints91);
			panelDatos.add(labelLab, gridBagConstraints11);
			panelDatos.add(labelSerie, gridBagConstraints7);
			panelDatos.add(getTextoVia(), gridBagConstraints71);
			panelDatos.add(labelVia, gridBagConstraints81);
			panelDatos.add(getFecha(), gridBagConstraints13);
			panelDatos.add(getFechaProx(), gridBagConstraints21);
			panelDatos.add(getTextoCant(), gridBagConstraints);
			panelDatos.add(labelCant, gridBagConstraints5);
			panelDatos.add(getBotonBuscar(), gridBagConstraints111);
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
	 * This method initializes textoTipo	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoTipo() {
		if (textoTipo == null) {
			textoTipo = new JTextField();
			textoTipo.setEditable(false);
			textoTipo.setBackground(new Color(238, 238, 238));
			textoTipo.selectAll();
		}
		return textoTipo;
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
			textoLab.selectAll();
		}
		return textoLab;
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
	 * This method initializes textoCant	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	public JTextField getTextoCant() {
		if (textoCant == null) {
			textoCant = new JTextField();
			textoCant.addKeyListener(new KeyAdapter(){
				
				public void keyTyped(KeyEvent ev){if(!(ev.getKeyChar()>='0' && ev.getKeyChar()<='9') && !( ev.getKeyChar()=='.')) ev.consume();}
			});
			textoCant.selectAll();
		}
		
		return textoCant;
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
	
	public void setListenerButtons(ActionListener al){
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonBuscar.addActionListener(al);
	}

}  //  @jve:decl-index=0:visual-constraint="58,44"
