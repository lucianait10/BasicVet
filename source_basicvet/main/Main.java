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

package main;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.birosoft.liquid.LiquidLookAndFeel;
  


public class Main {

	public static void main(String[] args) {    	
    	//Inicializacion tema interfaz de la aplicacion principal
    	LiquidLookAndFeel lookAndFeel = new LiquidLookAndFeel();
    	try {
			UIManager.setLookAndFeel(lookAndFeel);
		}catch(UnsupportedLookAndFeelException e){
			e.printStackTrace();
		}
		try{
			iniciarAplicacion();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private static void iniciarAplicacion() {
		PantallaInicio pantallaInicio = new PantallaInicio();
		pantallaInicio.setLocationRelativeTo(null);
		pantallaInicio.setProgresoMax(100);
		pantallaInicio.setVisible(true);
		pantallaInicio.velocidadDeCarga();
	}
}
