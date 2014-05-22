package cuGestionarAnimal;

import java.awt.BorderLayout;
import java.awt.Color;
//import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowAdapter;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;

public class GUIRecuperarAnimal extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JLabel jLabelOpcion = null;
	private JButton botonAceptar = null;
	private JButton botonCancelar = null;
	private JPanel panelBotones = null;
	private JScrollPane jScrollPanelTabla = null;
	private JTable tablaAnimales = null;
	/**
	 * @param owner  
	 */
	public GUIRecuperarAnimal(DefaultTableModel m) {
		super();
		this.tablaAnimales = new JTable(m);
		cerrarVentana();
		initialize();
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
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(800, 300);
		this.setTitle("LISTA DE ANIMALES DEL PROPIETARIO");
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
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
			jLabelOpcion = new JLabel();
			jLabelOpcion.setText("SELECCIONE UN ANIMAL ELIMINADO PARA SU RECUPERACION DE LO CONTRARIO CANCELE LA ACCION:");
			jLabelOpcion.setHorizontalTextPosition(SwingConstants.CENTER);
			jLabelOpcion.setForeground(Color.red);
			jLabelOpcion.setFont(new Font("Dialog", Font.BOLD, 14));
			jLabelOpcion.setHorizontalAlignment(SwingConstants.CENTER);
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(jLabelOpcion, BorderLayout.NORTH);
			jContentPane.add(getPanelBotones(), BorderLayout.SOUTH);
			jContentPane.add(getJScrollPanelTabla(), BorderLayout.CENTER);
		}
		return jContentPane;
	}

	/**
	 * This method initializes botonAceptar	
	 * 	
	 * @return javax.swing.JButton	
	 */
	public JButton getBotonAceptar() {
		if (botonAceptar == null) {
			botonAceptar = new JButton();botonAceptar.setIcon(new ImageIcon(getClass().getResource("/icono/accept.png")));
			botonAceptar.setMnemonic('R');
			//botonAceptar.setPreferredSize(new Dimension(108, 30));
			botonAceptar.registerKeyboardAction(botonAceptar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAceptar.registerKeyboardAction(botonAceptar.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			botonAceptar.setText("RECUPERAR");
		
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
	 * This method initializes panelBotones	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getPanelBotones() {
		if (panelBotones == null) {
			GridLayout gridLayout1 = new GridLayout();
			gridLayout1.setRows(1);
			panelBotones = new JPanel();
			panelBotones.setLayout(gridLayout1);
			panelBotones.add(getBotonAceptar(), null);
			panelBotones.add(getBotonCancelar(), null);
		}
		return panelBotones;
	}

	/**
	 * This method initializes jScrollPanelTabla	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getJScrollPanelTabla() {
		if (jScrollPanelTabla == null) {
			jScrollPanelTabla = new JScrollPane();
			jScrollPanelTabla.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			jScrollPanelTabla.setViewportView(getTablaAnimales());
		}
		return jScrollPanelTabla;
	}

	/**
	 * This method initializes tablaAnimales	
	 * 	
	 * @return javax.swing.JTable	
	 */
	public JTable getTablaAnimales() {
		if (tablaAnimales == null) {
			tablaAnimales = new JTable();
		}
		return tablaAnimales;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonAceptar.addActionListener(al);
		this.botonCancelar.addActionListener(al);
	}
	
	public void setListenerMouse(MouseAdapter ma){
		this.tablaAnimales.addMouseListener(ma);
	}

}  //  @jve:decl-index=0:visual-constraint="53,7"
