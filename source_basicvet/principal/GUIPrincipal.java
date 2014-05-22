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

package principal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.util.Date;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

public class GUIPrincipal extends JFrame {  

	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JPanel panelPrincipal = null;
	private JButton botonGestionarFichaClinica = null;
	private JButton botonGestionarCartilla = null;  
	private JButton botonGestionarTurno = null;
	private JPanel panelImagen = null;
	private JButton botonGestionarPropietario = null;
	private JButton botonGestionarAnimal = null;
	private JScrollPane panelAlarmas = null;
	private JTable tablaAlarmas = null;
	private DefaultTableModel modelo;
	private JButton botonConfiguracion = null;
	private JMenuBar menuPrincipal = null;
	private JMenu menuConfiguracion;
	private JMenu menuGestion;
	private JMenu menuClinica;
	private JMenu menuTurno;
	private JMenuItem itemConfiguracion;
	private JMenuItem itemGestionarPropietario;
	private JMenuItem itemGestionarAnimal;
	private JMenuItem itemGestionarVeterinario;
	private JButton botonGestionarProducto;
	private JMenuItem itemGestionarProducto;
	private JMenuItem itemGestionarUsuario;
	private JMenuItem itemGestionarCartilla;
	private JMenuItem itemGestionarFichaClinica;
	private JMenuItem itemGestionarProveedor;
	private JMenuItem itemGestionarTurno;
	private JButton botonGestionarProveedor = null;  
	private JButton botonGestionarVeterinario = null;
	private JButton botonGestionarCompra = null;
	private JMenu menuStock = null;
	private JMenuItem itemGestionarCompra = null;
	private JMenuItem itemGestionarVenta = null;
	private JButton botonGestionarCliente = null;
	private JMenuItem itemGestionarCliente = null;
	private JButton botonGestionarVenta = null;
	private JMenu menuBackup;
	private JMenuItem itemGuardarBackup;
	private JMenuItem itemCargarBackup;
	private JLabel labelAlarma = null;
	private JMenu menuAcercaDe;
	private JMenuItem itemAcerca;

	public GUIPrincipal(DefaultTableModel m) {
		super();
		this.modelo = m;
		initialize();
		cerrarVentana();
	}
	
	@SuppressWarnings("serial")
	private void cerrarVentana(){
		getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
	    KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "Cancel");
	    getRootPane().getActionMap().put("Cancel", new AbstractAction(){
	        
			public void actionPerformed(ActionEvent e){
				  int opcion = JOptionPane.showConfirmDialog(null, "SEGURO DESEA CERRAR BASIC-VET", "", JOptionPane.YES_NO_OPTION);
				  if(opcion == JOptionPane.YES_OPTION){System.exit(0);}
	        }
	    });
	    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
	    addWindowListener(new WindowAdapter() {
	    	public void windowClosing(java.awt.event.WindowEvent evt){
				  int opcion = JOptionPane.showConfirmDialog(null, "SEGURO DESEA CERRAR BASIC-VET", "", JOptionPane.YES_NO_OPTION);
				  if(opcion == JOptionPane.YES_OPTION){System.exit(0);}
	    	}
	    });
	}
	
	private void initialize() {
		this.setSize(800, 600);
		this.setJMenuBar(getManuPrincipal());
		this.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icono/icon.jpg")));
		this.setPreferredSize(new Dimension(640, 480));
		this.setContentPane(getJContentPane());
		this.setTitle("BASIC-VET");
		this.setLocationRelativeTo(null);
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(new BorderLayout());
			jContentPane.add(getPanelPrincipal(),BorderLayout.NORTH);
			jContentPane.add(getPanelImagen(), BorderLayout.CENTER);
			
		}
		return jContentPane;
	}

	private JPanel getPanelPrincipal() {
		if (panelPrincipal == null) {
			FlowLayout flowLayout = new FlowLayout();
			flowLayout.setAlignment(java.awt.FlowLayout.LEFT);
			flowLayout.setVgap(5);
			flowLayout.setHgap(5);
			panelPrincipal = new JPanel();
			panelPrincipal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			panelPrincipal.setLayout(flowLayout);
			panelPrincipal.add(getBotonGestionarFichaClinica(), null);
			panelPrincipal.add(getBotonGestionarCartilla(), null);
			panelPrincipal.add(getBotonGestionarTurno(), null);
			panelPrincipal.add(getBotonGestionarAnimal(), null);
			panelPrincipal.add(getBotonGestionarProducto(), null);
			panelPrincipal.add(getBotonGestionarCompra(), null);
			panelPrincipal.add(getBotonGestionarVenta(), null);
			panelPrincipal.add(getBotonGestionarPropietario(), null);
			panelPrincipal.add(getBotonGestionarCliente(), null);
			panelPrincipal.add(getBotonGestionarProveedor(),null);
			panelPrincipal.add(getBotonGestionarVeterinario(), null);
			panelPrincipal.add(getBotonConfiguracion(), null);
		}
		return panelPrincipal;
	}

	public JButton getBotonGestionarFichaClinica() {
		if (botonGestionarFichaClinica == null) {
			botonGestionarFichaClinica = new JButton();
			botonGestionarFichaClinica.setIcon(new ImageIcon(getClass().getResource("/imagenes/ficha.png")));
			botonGestionarFichaClinica.setPreferredSize(new Dimension(40, 40));
			botonGestionarFichaClinica.setToolTipText("GESTIONAR FICHA CLINICA");
			botonGestionarFichaClinica.registerKeyboardAction(botonGestionarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarFichaClinica.registerKeyboardAction(botonGestionarFichaClinica.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarFichaClinica;
	}

	public JButton getBotonGestionarCartilla() {
		if (botonGestionarCartilla == null) {
			botonGestionarCartilla = new JButton();
			botonGestionarCartilla.setIcon(new ImageIcon(getClass().getResource("/imagenes/cartilla.png")));
			botonGestionarCartilla.setPreferredSize(new Dimension(40, 40));
			botonGestionarCartilla.setToolTipText("GESTIONAR CARTILLA");
			botonGestionarCartilla.registerKeyboardAction(botonGestionarCartilla.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarCartilla.registerKeyboardAction(botonGestionarCartilla.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarCartilla;
	}

	public JButton getBotonGestionarTurno() {
		if (botonGestionarTurno == null) {
			botonGestionarTurno = new JButton();
			botonGestionarTurno.setIcon(new ImageIcon(getClass().getResource("/imagenes/turno.png")));
			botonGestionarTurno.setPreferredSize(new Dimension(40, 40));
			botonGestionarTurno.setToolTipText("GESTIONAR TURNO");
			botonGestionarTurno.registerKeyboardAction(botonGestionarTurno.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarTurno.registerKeyboardAction(botonGestionarTurno.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarTurno;
	}
	
	

	@SuppressWarnings("deprecation")
	private JPanel getPanelImagen() {
		if (panelImagen == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 0);
			gridBagConstraints1.fill = GridBagConstraints.HORIZONTAL;
			gridBagConstraints1.gridy = 0;
			Date hoy=new Date();
			String ahora=hoy.getDate()+"/"+(hoy.getMonth()+1)+"/"+(hoy.getYear()+1900);
			labelAlarma = new JLabel();
			labelAlarma.setText("LISTA DE ALARMAS: "+ahora);
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.fill = GridBagConstraints.BOTH;
			gridBagConstraints.gridy = 1;
			gridBagConstraints.weightx = 1.0;
			gridBagConstraints.weighty = 1.0;
			gridBagConstraints.gridx = 0;
			panelImagen = new JPanel();
			panelImagen.setLayout(new GridBagLayout());
			panelImagen.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			panelImagen.add(getPanelAlarmas(), gridBagConstraints);
			panelImagen.add(labelAlarma, gridBagConstraints1);
		}
		return panelImagen;
	}

	public JButton getBotonGestionarPropietario() {
		if (botonGestionarPropietario == null) {
			botonGestionarPropietario = new JButton();
			botonGestionarPropietario.setIcon(new ImageIcon(getClass().getResource("/imagenes/propietario.png")));
			botonGestionarPropietario.setPreferredSize(new Dimension(40, 40));
			botonGestionarPropietario.setToolTipText("GESTIONAR PROPIETARIO");
			botonGestionarPropietario.registerKeyboardAction(botonGestionarPropietario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarPropietario.registerKeyboardAction(botonGestionarPropietario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			
		}
		return botonGestionarPropietario;
	}

	public JButton getBotonGestionarAnimal() {
		if (botonGestionarAnimal == null) {
			botonGestionarAnimal = new JButton();
			botonGestionarAnimal.setIcon(new ImageIcon(getClass().getResource("/imagenes/animal.png")));
			botonGestionarAnimal.setPreferredSize(new Dimension(40, 40));
			botonGestionarAnimal.setToolTipText("GESTIONAR ANIMAL");
			botonGestionarAnimal.registerKeyboardAction(botonGestionarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarAnimal.registerKeyboardAction(botonGestionarAnimal.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarAnimal;
	}

	private JScrollPane getPanelAlarmas() {
		if (panelAlarmas == null) {
			panelAlarmas = new JScrollPane();
			panelAlarmas.setViewportView(getTablaAlarmas());
		}
		return panelAlarmas;
	}
	
	@SuppressWarnings("serial")
	public JTable getTablaAlarmas() {
		if (tablaAlarmas == null) {
			tablaAlarmas = new JTable(modelo){
				
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column){
				Component returnComp = super.prepareRenderer(renderer, row,column);
				if(row%2==0){
					returnComp.setBackground(Color.LIGHT_GRAY);
				}else{returnComp.setBackground(Color.WHITE);}
				return returnComp;
			}
			};
			tablaAlarmas.addMouseListener(new MouseAdapter(){
			      public void mouseClicked(MouseEvent e){
			         int fila = tablaAlarmas.rowAtPoint(e.getPoint());
			         int columna = tablaAlarmas.columnAtPoint(e.getPoint());
		               if (e.getClickCount() == 2)
			            System.out.println(modelo.getValueAt(fila,columna));
			      }
			   });
			tablaAlarmas.getTableHeader().setReorderingAllowed(false);
		}
		return tablaAlarmas;
	}

	public JButton getBotonConfiguracion() {
		if (botonConfiguracion == null) {
			botonConfiguracion = new JButton();
			botonConfiguracion.setIcon(new ImageIcon(getClass().getResource("/imagenes/configurar.png")));
			botonConfiguracion.setPreferredSize(new Dimension(40, 40));
			botonConfiguracion.setToolTipText("CONFIGURAR");
			botonConfiguracion.registerKeyboardAction(botonConfiguracion.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonConfiguracion.registerKeyboardAction(botonConfiguracion.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonConfiguracion;
	}

	private JMenuBar getManuPrincipal() {
		if (menuPrincipal == null) {
			menuPrincipal = new JMenuBar();
			menuPrincipal.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			menuPrincipal.add(getMenuGestion());
			menuPrincipal.add(getMenuClinica());
			menuPrincipal.add(getMenuTurno());
			menuPrincipal.add(getMenuStock());
			menuPrincipal.add(getMenuBackup());
			menuPrincipal.add(getMenuConfiguracion());
			menuPrincipal.add(getMenuAcercaDe());
		}
		return menuPrincipal;
	}
	
	private JMenu getMenuConfiguracion(){
		if(menuConfiguracion==null){
			menuConfiguracion = new JMenu("Configuracion");
			menuConfiguracion.setMnemonic(KeyEvent.VK_F);
			menuConfiguracion.add(getMenuItemConfiguracion());
		}
		return menuConfiguracion;
	}
	
	private JMenu getMenuAcercaDe(){
		if(menuAcercaDe==null){
			menuAcercaDe = new JMenu("Acerca");
			menuAcercaDe.setMnemonic(KeyEvent.VK_A);
			menuAcercaDe.add(getMenuItemAcerca());
		}
		return menuAcercaDe;
	}
	
	public JMenuItem getMenuItemAcerca(){
		if(itemAcerca == null){
			itemAcerca = new JMenuItem("Acerca De BasicVet");
			itemAcerca.setMnemonic('A');
		}
		return itemAcerca;
	}
	
	private JMenu getMenuGestion(){
		if(menuGestion == null){
			menuGestion = new JMenu("Gestion");
			menuGestion.setMnemonic(KeyEvent.VK_G);
			menuGestion.add(getMenuItemGestionarPropietario());
			menuGestion.add(getMenuItemGestionarAnimal());
			menuGestion.add(getMenuItemGestionarVeterinario());
			menuGestion.add(getMenuItemGestionarProducto());
			menuGestion.add(getMenuItemGestionarUsuario());
			menuGestion.add(getMenuItemGestionarProveedor());
			menuGestion.add(getMenuItemGestionarCliente());
		}
		return menuGestion;
	}
	
	private JMenu getMenuClinica(){
		if(menuClinica == null){
			menuClinica = new JMenu("Clinica");
			menuClinica.setMnemonic(KeyEvent.VK_C);
			menuClinica.add(getMenuItemGestionarCartilla());
			menuClinica.add(getMenuItemGestionarFichaClinica());
		}
		return menuClinica;
	}
	
	private JMenu getMenuTurno(){
		if(menuTurno == null){
			menuTurno = new JMenu("Turno");
			menuTurno.setMnemonic(KeyEvent.VK_T);
			menuTurno.add(getMenuItemGestionarTurno());
		}
		return menuTurno;
	}
	
	private JMenu getMenuBackup(){
		if(menuBackup == null){
			menuBackup = new JMenu("Backup");
			menuBackup.setMnemonic(KeyEvent.VK_B);
			menuBackup.add(getMenuItemGuardarBackup());
			menuBackup.add(getMenuItemCargarBackup());
		}
		return menuBackup;
	}
	
	public JMenuItem getMenuItemGuardarBackup(){
		if(itemGuardarBackup == null){
			itemGuardarBackup = new JMenuItem("Guardar Backup");
			itemGuardarBackup.setMnemonic('G');
		}
		return itemGuardarBackup;
	}
	
	public JMenuItem getMenuItemCargarBackup(){
		if(itemCargarBackup == null){
			itemCargarBackup = new JMenuItem("Cargar Backup");
			itemCargarBackup.setMnemonic('B');
		}
		return itemCargarBackup;
	}
		
	public JMenuItem getMenuItemConfiguracion(){
		if(itemConfiguracion == null){
			itemConfiguracion = new JMenuItem("Configuracion");
			itemConfiguracion.setMnemonic('F');
		}
		return itemConfiguracion;
	}
	
	public JMenuItem getMenuItemGestionarPropietario(){
		if(itemGestionarPropietario == null){
			itemGestionarPropietario = new JMenuItem("Gestionar Propietario");
			itemGestionarPropietario.setMnemonic('I');
		}
		return itemGestionarPropietario;
	}
	
	public JMenuItem getMenuItemGestionarAnimal(){
		if(itemGestionarAnimal == null){
			itemGestionarAnimal = new JMenuItem("Gestionar Animal");
			itemGestionarAnimal.setMnemonic('A');
		}
		return itemGestionarAnimal;
	}
	
	public JMenuItem getMenuItemGestionarVeterinario(){
		if(itemGestionarVeterinario == null){
			itemGestionarVeterinario = new JMenuItem("Gestionar Veterinario");
			itemGestionarVeterinario.setMnemonic('E');
		}
		return itemGestionarVeterinario;
	}
	
	public JMenuItem getMenuItemGestionarProducto(){
		if(itemGestionarProducto == null){
			itemGestionarProducto = new JMenuItem("Gestionar Producto");
			itemGestionarProducto.setMnemonic('R');
		}
		return itemGestionarProducto;
	}
	
	public JMenuItem getMenuItemGestionarUsuario(){
		if(itemGestionarUsuario == null){
			itemGestionarUsuario = new JMenuItem("Gestionar Usuario");
			itemGestionarUsuario.setMnemonic('U');
		}
		return itemGestionarUsuario;
	}
	
	public JMenuItem getMenuItemGestionarCartilla(){
		if(itemGestionarCartilla == null){
			itemGestionarCartilla = new JMenuItem("Gestionar Cartilla");
			itemGestionarCartilla.setMnemonic('T');
		}
		return itemGestionarCartilla;
	}
	
	public JMenuItem getMenuItemGestionarFichaClinica(){
		if(itemGestionarFichaClinica == null){
			itemGestionarFichaClinica = new JMenuItem("Gestionar Ficha Clinica");
			itemGestionarFichaClinica.setMnemonic('F');
		}
		return itemGestionarFichaClinica;
	}
	
	public JMenuItem getMenuItemGestionarTurno(){
		if(itemGestionarTurno == null){
			itemGestionarTurno = new JMenuItem("Gestionar Turno");
			itemGestionarTurno.setMnemonic('T');
		}
		return itemGestionarTurno;
	}
	
	public JMenuItem getMenuItemGestionarProveedor(){
		if(itemGestionarProveedor == null){
			itemGestionarProveedor = new JMenuItem("Gestionar Proveedor");
			itemGestionarProveedor.setMnemonic('P');
		}
		return itemGestionarProveedor;
	}
	
	public JButton getBotonGestionarProducto() {
		if (botonGestionarProducto == null) {
			botonGestionarProducto = new JButton();
			botonGestionarProducto.setIcon(new ImageIcon(getClass().getResource("/imagenes/producto.png")));
			botonGestionarProducto.setPreferredSize(new Dimension(40, 40));
			botonGestionarProducto.setToolTipText("GESTIONAR PRODUCTO");
			botonGestionarProducto.registerKeyboardAction(botonGestionarProducto.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarProducto.registerKeyboardAction(botonGestionarProducto.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarProducto;
	}

	public JButton getBotonGestionarProveedor() {
		if (botonGestionarProveedor == null) {
			botonGestionarProveedor = new JButton();
			botonGestionarProveedor.setIcon(new ImageIcon(getClass().getResource("/imagenes/proveedor.png")));
			botonGestionarProveedor.setPreferredSize(new Dimension(40, 40));
			botonGestionarProveedor.setToolTipText("GESTIONAR PROVEEDOR");
			botonGestionarProveedor.registerKeyboardAction(botonGestionarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarProveedor.registerKeyboardAction(botonGestionarProveedor.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarProveedor;
	}

	public JButton getBotonGestionarVeterinario() {
		if (botonGestionarVeterinario == null) {
			botonGestionarVeterinario = new JButton();
			botonGestionarVeterinario.setIcon(new ImageIcon(getClass().getResource("/imagenes/veterinario.png")));
			botonGestionarVeterinario.setPreferredSize(new Dimension(40, 40));
			botonGestionarVeterinario.setToolTipText("GESTIONAR VETERINARIO");
			botonGestionarVeterinario.registerKeyboardAction(botonGestionarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarVeterinario.registerKeyboardAction(botonGestionarVeterinario.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarVeterinario;
	}

	public JButton getBotonGestionarCompra() {
		if (botonGestionarCompra == null) {
			botonGestionarCompra = new JButton();
			botonGestionarCompra.setIcon(new ImageIcon(getClass().getResource("/imagenes/compra.png")));
			botonGestionarCompra.setToolTipText("GESTION DE COMPRAS");
			botonGestionarCompra.setPreferredSize(new Dimension(40, 40));
			botonGestionarCompra.registerKeyboardAction(botonGestionarCompra.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarCompra.registerKeyboardAction(botonGestionarCompra.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
			
			
		}
		return botonGestionarCompra;
	}

	private JMenu getMenuStock() {
		if (menuStock == null) {
			menuStock = new JMenu();
			menuStock.setText("Stock");
			menuStock.setMnemonic(KeyEvent.VK_S);
			menuStock.add(getMenuItemGestionarCompra());
			menuStock.add(getMenuItemGestionarVenta());
			menuStock.add(getMenuItemGestionarProducto());
		}
		return menuStock;
	}

	public JMenuItem getMenuItemGestionarCompra() {
		if (itemGestionarCompra == null) {
			itemGestionarCompra = new JMenuItem("Gestionar Compra");
			itemGestionarCompra.setMnemonic('O');
		}
		return itemGestionarCompra;
	}

	public JMenuItem getMenuItemGestionarVenta() {
		if (itemGestionarVenta == null) {
			itemGestionarVenta = new JMenuItem("Gestionar Venta");
			itemGestionarVenta.setMnemonic('V');
		}
		return itemGestionarVenta;
	}

	public JButton getBotonGestionarCliente() {
		if (botonGestionarCliente == null){
			botonGestionarCliente = new JButton();
			botonGestionarCliente.setIcon(new ImageIcon(getClass().getResource("/imagenes/cliente.jpg")));
			botonGestionarCliente.setPreferredSize(new Dimension(40, 40));
			botonGestionarCliente.setToolTipText("GESTIONAR CLIENTE");
			botonGestionarCliente.registerKeyboardAction(botonGestionarCliente.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarCliente.registerKeyboardAction(botonGestionarCliente.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarCliente;
	}

	public JMenuItem getMenuItemGestionarCliente() {
		if (itemGestionarCliente == null) {
			itemGestionarCliente = new JMenuItem("Gestionar Cliente");
			itemGestionarCliente.setMnemonic('C');
		}
		return itemGestionarCliente;
	}

	public JButton getBotonGestionarVenta() {
		if (botonGestionarVenta == null) {
			botonGestionarVenta = new JButton();
			botonGestionarVenta.setIcon(new ImageIcon(getClass().getResource("/imagenes/venta.png")));
			botonGestionarVenta.setToolTipText("GESTION DE VENTAS");
			botonGestionarVenta.setPreferredSize(new Dimension(40, 40));
			botonGestionarVenta.registerKeyboardAction(botonGestionarVenta.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonGestionarVenta.registerKeyboardAction(botonGestionarVenta.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonGestionarVenta;
	}
	
	public void setListenerButtons(ActionListener al){
		this.botonGestionarFichaClinica.addActionListener(al);
		this.botonGestionarCartilla.addActionListener(al);
		this.botonGestionarTurno.addActionListener(al);
		this.botonGestionarPropietario.addActionListener(al);
		this.botonGestionarAnimal.addActionListener(al);
		this.botonConfiguracion.addActionListener(al);
		this.botonGestionarProducto.addActionListener(al);
		this.botonGestionarProveedor.addActionListener(al);
		this.botonGestionarVeterinario.addActionListener(al);
		this.botonGestionarCompra.addActionListener(al);
		this.botonGestionarCliente.addActionListener(al);
		this.itemConfiguracion.addActionListener(al);
		this.itemGestionarPropietario.addActionListener(al);
		this.itemGestionarAnimal.addActionListener(al);
		this.itemGestionarVeterinario.addActionListener(al);
		this.itemGestionarProducto.addActionListener(al);
		this.itemGestionarUsuario.addActionListener(al);
		this.itemGestionarCartilla.addActionListener(al);
		this.itemGestionarFichaClinica.addActionListener(al);
		this.itemGestionarProveedor.addActionListener(al);
		this.itemCargarBackup.addActionListener(al);
		this.itemGuardarBackup.addActionListener(al);
		this.itemGestionarTurno.addActionListener(al);
		this.itemGestionarCliente.addActionListener(al);
		this.itemGestionarCompra.addActionListener(al);
		this.itemGestionarVenta.addActionListener(al);
		this.itemAcerca.addActionListener(al);
		this.botonGestionarVenta.addActionListener(al);
	}
}
