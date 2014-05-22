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

package cuAcercaDe;

import java.applet.Applet;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
 
  
@SuppressWarnings("serial")
public class MediadorAcercaDe extends Applet implements ActionListener{
	private GUIAcercaDe guiAcercaDe;
	private Desktop desktop;
	  
	public MediadorAcercaDe() {
		inicialzar();
	}

	@SuppressWarnings("deprecation")
	public void mostrar(){
		this.guiAcercaDe.show();
	}
	
	private void inicialzar() {
		this.guiAcercaDe = new GUIAcercaDe();
		desktop = Desktop.getDesktop();
		this.guiAcercaDe.setListenerButons(this);
	}

	
	public void actionPerformed(ActionEvent e) {
		if(this.guiAcercaDe.getBotonLicencia()==e.getSource()){
			try {
				desktop.browse(new URI("http://www.gnu.org/licenses/gpl.html"));
			} catch (IOException e1) {e1.printStackTrace();} catch (URISyntaxException e1) {e1.printStackTrace();}
		}
		if(this.guiAcercaDe.getBotonSitioWeb()==e.getSource()){
			try {
				desktop.browse(new URI("http://www.it10coop.com.ar"));
			} catch (IOException e1) {e1.printStackTrace();} catch (URISyntaxException e1) {e1.printStackTrace();}
		}
	}	
}
