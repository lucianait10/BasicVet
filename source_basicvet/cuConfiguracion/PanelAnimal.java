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

package cuConfiguracion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionListener;
  
public class PanelAnimal extends JPanel {
  
	private static final long serialVersionUID = 1L;
	private JPanel panelEspecies;
	private JPanel panelRazas;
	private JPanel panelBoton;
	private JButton botonAgregarEspecie;
	private JButton botonQuitarEspecie;
	private JPanel panelBotonRazas = null;
	private JButton botonAgregarRaza = null;
	private JButton botonQuitarRaza = null;
	private JList listaEspecies = null;
	private JList listaRazas = null;
	private DefaultListModel modeloEspecie = new DefaultListModel();
	private DefaultListModel modeloRaza = new DefaultListModel();
	private JScrollPane panelScrollEspecies = null;
	private JScrollPane panelScrollRazas = null;  

	public PanelAnimal() {
		super();
		initialize();
	}

	private void initialize() {
		this.setSize(645, 350);
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setBorder(BorderFactory.createMatteBorder(0, 2, 0, 0, new Color(162,211,255)));
		this.add(getPanelEspecies(), null);
		this.add(getPanelRazas(), null);
	}
	
	private JPanel getPanelEspecies() {
		if (panelEspecies == null) {
			panelEspecies = new JPanel();
			panelEspecies.setLayout(new BorderLayout());
			panelEspecies.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "ESPECIES", TitledBorder.CENTER, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(5, 13, 152)));
			panelEspecies.setName("panelEspecies");
			panelEspecies.add(getPanelScrollEspecies(), BorderLayout.CENTER);
			panelEspecies.add(getPanelBotonEspecie(), BorderLayout.EAST);
		}
		return panelEspecies;
	}
	
	private JPanel getPanelRazas() {
		if (panelRazas == null) {
			panelRazas = new JPanel();
			panelRazas.setLayout(new BorderLayout());
			panelRazas.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED), "RAZAS", TitledBorder.CENTER, TitledBorder.TOP, new Font("Dialog", Font.BOLD, 12), new Color(5, 13, 152)));
			panelRazas.setName("panelRazas");
			panelRazas.add(getPanelScrollRazas(), BorderLayout.CENTER);
			panelRazas.add(getPanelBotonRazas(), BorderLayout.EAST);
		}
		return panelRazas;
	}
	
	private JPanel getPanelBotonEspecie() {
		if (panelBoton == null) {
			GridBagConstraints gridBagConstraints1 = new GridBagConstraints();
			gridBagConstraints1.gridx = 0;
			gridBagConstraints1.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints1.gridy = 1;
			GridBagConstraints gridBagConstraints = new GridBagConstraints();
			gridBagConstraints.gridx = 0;
			gridBagConstraints.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints.gridy = 0;
			panelBoton = new JPanel();
			panelBoton.setLayout(new GridBagLayout());
			panelBoton.add(getBotonAgregarEspecie(), gridBagConstraints);
			panelBoton.add(getBotonQuitarEspecie(), gridBagConstraints1);
		}
		return panelBoton;
	}
	public JButton getBotonAgregarEspecie() {
		if (botonAgregarEspecie == null) {
			botonAgregarEspecie = new JButton();
			botonAgregarEspecie.setIcon(new ImageIcon(getClass().getResource("/icono/agregar.png")));
			botonAgregarEspecie.registerKeyboardAction(botonAgregarEspecie.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonAgregarEspecie.registerKeyboardAction(botonAgregarEspecie.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonAgregarEspecie;
	}

	public JButton getBotonQuitarEspecie() {
		if (botonQuitarEspecie == null) {
			botonQuitarEspecie = new JButton();
			botonQuitarEspecie.setIcon(new ImageIcon(getClass().getResource("/icono/quitar.png")));
			botonQuitarEspecie.registerKeyboardAction(botonQuitarEspecie.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                    JComponent.WHEN_FOCUSED);

			botonQuitarEspecie.registerKeyboardAction(botonQuitarEspecie.getActionForKeyStroke(
                    KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0, true)),
                    KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true),
                    JComponent.WHEN_FOCUSED);
		}
		return botonQuitarEspecie;
	}

	private JPanel getPanelBotonRazas() {
		if (panelBotonRazas == null) {
			GridBagConstraints gridBagConstraints11 = new GridBagConstraints();
			gridBagConstraints11.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints11.gridy = 1;
			gridBagConstraints11.gridx = 0;
			GridBagConstraints gridBagConstraints2 = new GridBagConstraints();
			gridBagConstraints2.insets = new Insets(5, 5, 5, 5);
			gridBagConstraints2.gridy = 0;
			gridBagConstraints2.gridx = 0;
			panelBotonRazas = new JPanel();
			panelBotonRazas.setLayout(new GridBagLayout());
			panelBotonRazas.add(getBotonAgregarRaza(), gridBagConstraints2);
			panelBotonRazas.add(getBotonQuitarRaza(), gridBagConstraints11);
		}
		return panelBotonRazas;
	}

	public JButton getBotonAgregarRaza() {
		if (botonAgregarRaza == null) {
			botonAgregarRaza = new JButton();
			botonAgregarRaza.setIcon(new ImageIcon(getClass().getResource("/icono/agregar.png")));
		}
		return botonAgregarRaza;
	}

	public JButton getBotonQuitarRaza() {
		if (botonQuitarRaza == null) {
			botonQuitarRaza = new JButton();
			botonQuitarRaza.setIcon(new ImageIcon(getClass().getResource("/icono/quitar.png")));
		}
		return botonQuitarRaza;
	}

	
	public JList getListaEspecies() {
		if (listaEspecies == null) {
			listaEspecies = new JList(this.modeloEspecie);
		}
		return listaEspecies;
	}

	
	public JList getListaRazas() {
		if (listaRazas == null) {
			listaRazas = new JList(this.modeloRaza);
		}
		return listaRazas;
	}
	
	public void setListenerButtons(){
		
	}
	
	public void setListenerButtons(ActionListener al){
		botonAgregarEspecie.addActionListener(al);
		botonQuitarEspecie.addActionListener(al);
		botonAgregarRaza.addActionListener(al);
		botonQuitarRaza.addActionListener(al);
	}
	
	
	public void agregarEspecie(String obj){
		this.modeloEspecie.addElement(obj);
	}
	
	
	public void agregarRaza(String obj){
		this.modeloRaza.addElement(obj);
	}
	
	public void setListSelectionListener(ListSelectionListener lsl){
		ListSelectionModel listSelectionModel = this.listaEspecies.getSelectionModel();
		listSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		listSelectionModel.addListSelectionListener(lsl);
	}
	
	
	public DefaultListModel getModeloEspecie(){
		return this.modeloEspecie;		
	}
	
	
	public DefaultListModel getModeloRaza(){
		return this.modeloRaza;		
	}

	public void quitarEspecie(int fila) {
		this.modeloEspecie.remove(fila);
	}
	public void quitarRaza(int fila) {
		this.modeloRaza.remove(fila);
	}

	private JScrollPane getPanelScrollEspecies() {
		if (panelScrollEspecies == null) {
			panelScrollEspecies = new JScrollPane();
			panelScrollEspecies.setSize(new Dimension(253, 76));
			panelScrollEspecies.setViewportView(getListaEspecies());
		}
		return panelScrollEspecies;
	}

	/**
	 * This method initializes panelScrollRazas	
	 * 	
	 * @return javax.swing.JScrollPane	
	 */
	private JScrollPane getPanelScrollRazas() {
		if (panelScrollRazas == null) {
			panelScrollRazas = new JScrollPane();
			panelScrollRazas.setSize(new Dimension(276, 113));
			panelScrollRazas.setViewportView(getListaRazas());
		}
		return panelScrollRazas;
	}
}
