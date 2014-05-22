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

package cuGestionarAnimal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.RowFilter;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

@SuppressWarnings({ "unchecked"})
public class GUIBuscarAnimal extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelBotones = null;  
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JScrollPane scrollPanel = null;
	private JTable tablaBusqueda = null;
	private JButton botonAgregar = null;
	private JTextField textoNro = null;
	private JTextField textoNombre = null;
	private JPanel datosBusqueda = null;
	private JPanel panelBusqueda = null;
	private JLabel labelNro = null;
	private JLabel labelNombre = null;
	private TableRowSorter<DefaultTableModel> tFiltro;
	/**
	 * This is the default constructor
	 */
	
	public GUIBuscarAnimal(DefaultTableModel t) {
		super();
		tFiltro = new TableRowSorter(t);
		this.tablaBusqueda=new JTable(t);
		this.tablaBusqueda.getTableHeader().setReorderingAllowed(false);
		this.tablaBusqueda.setRowSorter(tFiltro);
		cerrarVentana();
		initialize();
	}

	@SuppressWarnings({ "serial" })
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel"); //$NON-NLS-1$
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){ //$NON-NLS-1$
	        
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
		this.setContentPane(getJContentPane());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setTitle("BUSCAR ANIMAL");
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
			jContentPane.add(getDatosBusqueda(), BorderLayout.NORTH);
			jContentPane.add(getPanelBusqueda(), BorderLayout.CENTER);
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
			GridBagConstraints gridBagConstraints3 = new GridBagConstraints();
			gridBagConstraints3.gridx = 3;
			gridBagConstraints3.gridy = 2;
			panelBotones = new JPanel();
			panelBotones.setLayout(new FlowLayout());
			panelBotones.add(getBotonAceptar(), null);
			panelBotones.add(getBotonCancelar(), null);
			panelBotones.add(getBotonAgregar(), null);
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
			botonAceptar.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
			botonAceptar.setMnemonic('A');
			//botonAceptar.setPreferredSize(new Dimension(108, 30));
			botonAceptar.registerKeyboardAction(botonAceptar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAceptar.registerKeyboardAction(botonAceptar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			botonAceptar.setText("ACEPTAR");
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
			botonCancelar.setIcon(new ImageIcon(getClass().getResource("/icono/cancel.png")));
			botonCancelar.setMnemonic('C');
			//botonCancelar.setPreferredSize(new Dimension(116, 30));
			botonCancelar.registerKeyboardAction(botonCancelar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonCancelar.registerKeyboardAction(botonCancelar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			botonCancelar.setText("CANCELAR");
		}
		return botonCancelar;
	}

	/**
	 * This method initializes scrollPanel	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getScrollPanel() {
		if (scrollPanel == null) {
			scrollPanel = new JScrollPane();
			scrollPanel.setViewportView(getTablaBusqueda());
		}
		return scrollPanel;
	}

	public JTable getTablaBusqueda() {
		if (tablaBusqueda == null) {
			tablaBusqueda = new JTable();
			tablaBusqueda.getTableHeader().setReorderingAllowed(false);
		}
		return tablaBusqueda;
	}

	public JButton getBotonAgregar() {
		if (botonAgregar == null) {
			botonAgregar = new JButton();
			botonAgregar.setText("NUEVO ANIMAL");
			botonAgregar.setMnemonic('N');
			//botonAgregar.setPreferredSize(new Dimension(141, 30));
			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregar.registerKeyboardAction(botonAgregar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			botonAgregar.setIcon(new ImageIcon(getClass().getResource("/icono/add.png")));
		}
		return botonAgregar;
	}

	private JTextField getTextoNro() {
		if (textoNro == null) {
			textoNro = new JTextField();
			textoNro.setBackground(Color.CYAN);
			textoNro.setText("");
			textoNro.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyReleased(final java.awt.event.KeyEvent e) {
	                int columna = 1;
	                tFiltro.setRowFilter(RowFilter.regexFilter(textoNro.getText().toUpperCase(), columna));
	            }
	        });
		}
		textoNro.selectAll();
		return textoNro;
	}

	private JTextField getTextoNombre() {
		if (textoNombre == null) {
			textoNombre = new JTextField();
			textoNombre.setBackground(Color.CYAN);
			textoNombre.setText("");
			textoNombre.addKeyListener(new java.awt.event.KeyAdapter() {
				
				public void keyReleased(final java.awt.event.KeyEvent e) {
	                int columna = 2;
	                tFiltro.setRowFilter(RowFilter.regexFilter(textoNombre.getText().toUpperCase(), columna));
	            }
	        }); 
		}
		textoNombre.selectAll();
		return textoNombre;
	}
	
	private JPanel getDatosBusqueda() {
		if (datosBusqueda == null) {
			GridBagConstraints gridBagConstraints10 = new GridBagConstraints();
			gridBagConstraints10.gridx = 2;
			gridBagConstraints10.gridy = 0;
			labelNombre = new JLabel();
			labelNombre.setText("NOMBRE:");
			GridBagConstraints gridBagConstraints9 = new GridBagConstraints();
			gridBagConstraints9.gridx = 0;
			gridBagConstraints9.insets = new Insets(0, 10, 0, 0);
			gridBagConstraints9.gridy = 0;
			labelNro = new JLabel();
			labelNro.setText("NUMERO:");
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints1.gridx = 3;
			gridBagConstraints1.insets = new Insets(10, 5, 10, 5);
			gridBagConstraints1.anchor = GridBagConstraints.WEST;
			gridBagConstraints1.ipadx = 200;
			gridBagConstraints1.weightx = 1.0;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.VERTICAL;
			gridBagConstraints.gridx = 1;
			gridBagConstraints.insets = new Insets(10, 5, 10, 5);
			gridBagConstraints.anchor = GridBagConstraints.WEST;
			gridBagConstraints.ipadx = 200;
			gridBagConstraints.weightx = 1.0;
			datosBusqueda = new JPanel();
			datosBusqueda.setLayout(new GridBagLayout());
			datosBusqueda.add(getTextoNro(), gridBagConstraints);
			datosBusqueda.add(getTextoNombre(), gridBagConstraints1);
			datosBusqueda.add(labelNro, gridBagConstraints9);
			datosBusqueda.add(labelNombre, gridBagConstraints10);
		}
		return datosBusqueda;
	}

	private JPanel getPanelBusqueda() {
		if (panelBusqueda == null) {
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.fill = GridBagConstraints.BOTH;
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.weightx = 1.0;
			gridBagConstraints2.weighty = 1.0;
			gridBagConstraints2.gridx = 0;
			panelBusqueda = new JPanel();
			panelBusqueda.setLayout(new GridBagLayout());
			panelBusqueda.add(getScrollPanel(), gridBagConstraints2);
		}
		return panelBusqueda;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
		this.botonAgregar.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaBusqueda.addMouseListener(ma);
	}
	
	public TableRowSorter getTableRowSorter(){
		return this.tFiltro;  
	}
}
