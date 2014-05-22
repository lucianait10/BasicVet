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

package configuracion;

import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import persistencia.Persistencia;
import persistencia.dominio.Especie;
import persistencia.dominio.Motivo;
import persistencia.dominio.Raza;
import persistencia.dominio.TipoEstudio;
import persistencia.dominio.Usuario;
import com.birosoft.liquid.LiquidLookAndFeel;
import control.ControlEspecie;
import control.ControlMotivo;
import control.ControlTipoEstudio;
import control.ControlUsuario;

public class MainConfiguracion {
	private static Persistencia persistencia;
	private static ControlUsuario controlUsuario;
	private static ControlEspecie controlEspecie;
	private static ControlTipoEstudio controlTipoEstudio;
	private static ControlMotivo controlMotivo;

	@SuppressWarnings("unused")
	public static void main(String[] args){
    	LiquidLookAndFeel lookAndFeel = new LiquidLookAndFeel();
    	try{
			UIManager.setLookAndFeel(lookAndFeel);
		}catch (UnsupportedLookAndFeelException e){
			e.printStackTrace();
		}
		try{
			Configuracion config = new Configuracion();
			persistencia = new Persistencia();
			persistencia.iniciarTransaccion();
			insertarLoguin();
			insetarEspeciesRazas();
			insertarTiposEstudios();
			insertarMotivosTurno();
			persistencia.concretarTransaccion();
			String fin = "LA CONFIGURACION DE LA BASE DE DATOS FINALIZO CON EXITO";
			JOptionPane.showMessageDialog(null,fin);
		}catch(Exception e){
			e.printStackTrace();
			String errorMotor = "MOTOR DE BASE DE DATOS NO INSTALADO/RECONFIGURACION";
			JOptionPane.showMessageDialog(null, "ERROR AL CREAR BASE DE DATOS \n"+errorMotor);
		}
	}
	
	private static void insertarLoguin(){
		controlUsuario = new ControlUsuario();
		Usuario admin = new Usuario("administrador", "admin");
		controlUsuario.guardarUsuario(admin);

	}
	
	private static void insertarTiposEstudios(){

		controlTipoEstudio = new ControlTipoEstudio();
		TipoEstudio estudio1 = new TipoEstudio("sangre");
		controlTipoEstudio.guardarTipoEstudio(estudio1);
		TipoEstudio estudio2 = new TipoEstudio("orina");
		controlTipoEstudio.guardarTipoEstudio(estudio2);
		TipoEstudio estudio3 = new TipoEstudio("ecografia");
		controlTipoEstudio.guardarTipoEstudio(estudio3);
		TipoEstudio estudio4 = new TipoEstudio("radiografia");
		controlTipoEstudio.guardarTipoEstudio(estudio4);
		TipoEstudio estudio5 = new TipoEstudio("otros");
		controlTipoEstudio.guardarTipoEstudio(estudio5);
	
	}
	
	private static void insetarEspeciesRazas(){
	
		controlEspecie = new ControlEspecie();
		Especie especie = new Especie("conejo");
		controlEspecie.guardarEspecie(especie);
		especie.agregarRazas(new Raza("himalayan"));
		especie.agregarRazas(new Raza("angora"));
		especie.agregarRazas(new Raza("mini rex"));
		especie.agregarRazas(new Raza("mini arlequin"));
		especie.agregarRazas(new Raza("polish"));
		especie.agregarRazas(new Raza("cabeza de leon"));
		especie.agregarRazas(new Raza("daisy"));
		especie.agregarRazas(new Raza("holland lop"));
		especie.agregarRazas(new Raza("mini lop"));
		especie.agregarRazas(new Raza("amercian fuzzy lop"));
		especie.agregarRazas(new Raza("english lop"));
		especie.agregarRazas(new Raza("neocelandes"));
		especie.agregarRazas(new Raza("gigante de flandes"));
		especie.agregarRazas(new Raza("dwarf hotot"));
		especie.agregarRazas(new Raza("dwarf holandes"));
		especie.agregarRazas(new Raza("dwarf daisy"));
		especie.agregarRazas(new Raza("jersey woolly"));
		especie.agregarRazas(new Raza("liebre enana"));
		Especie especie1 = new Especie("perro");
		controlEspecie.guardarEspecie(especie1);
		especie1.agregarRazas(new Raza("doberman"));
		especie1.agregarRazas(new Raza("labrador"));
		especie1.agregarRazas(new Raza("caniche"));
		especie1.agregarRazas(new Raza("cocker"));
		especie1.agregarRazas(new Raza("bulldog frances"));
		especie1.agregarRazas(new Raza("alano"));
		especie1.agregarRazas(new Raza("boxer"));
		especie1.agregarRazas(new Raza("bulldog ingles"));
		especie1.agregarRazas(new Raza("bulldog aleman"));
		especie1.agregarRazas(new Raza("bullterrier"));
		especie1.agregarRazas(new Raza("carlino"));
		especie1.agregarRazas(new Raza("chihuahua"));
		especie1.agregarRazas(new Raza("chow chow"));
		especie1.agregarRazas(new Raza("dogo aleman"));
		especie1.agregarRazas(new Raza("dogo argentino"));
		especie1.agregarRazas(new Raza("dogo de burdeos"));
		especie1.agregarRazas(new Raza("fox terrier"));
		especie1.agregarRazas(new Raza("mastin espa�ol"));
		especie1.agregarRazas(new Raza("mastin napolitano"));
		especie1.agregarRazas(new Raza("pastor aleman"));
		especie1.agregarRazas(new Raza("pastor belga"));
		especie1.agregarRazas(new Raza("pekines"));
		especie1.agregarRazas(new Raza("pointer"));
		especie1.agregarRazas(new Raza("rottweiler"));
		especie1.agregarRazas(new Raza("san bernardo"));
		especie1.agregarRazas(new Raza("siberiano"));
		Especie especie2 = new Especie("gato");
		controlEspecie.guardarEspecie(especie2);
		especie2.agregarRazas(new Raza("abisinio"));
		especie2.agregarRazas(new Raza("american bobtail"));
		especie2.agregarRazas(new Raza("american curl"));
		especie2.agregarRazas(new Raza("american wirehair"));
		especie2.agregarRazas(new Raza("angora turco"));
		especie2.agregarRazas(new Raza("azul ruso"));
		especie2.agregarRazas(new Raza("balines"));
		especie2.agregarRazas(new Raza("bengali"));
		especie2.agregarRazas(new Raza("bobtail japones"));
		especie2.agregarRazas(new Raza("bombay"));
		especie2.agregarRazas(new Raza("bosque de noruega"));
		especie2.agregarRazas(new Raza("british shorthair"));
		especie2.agregarRazas(new Raza("burmes"));
		especie2.agregarRazas(new Raza("burmilla"));
		especie2.agregarRazas(new Raza("california spangled"));
		especie2.agregarRazas(new Raza("ceilan"));
		especie2.agregarRazas(new Raza("chartreux"));
		especie2.agregarRazas(new Raza("comun europeo"));
		especie2.agregarRazas(new Raza("cornish rex"));
		especie2.agregarRazas(new Raza("cymric"));
		especie2.agregarRazas(new Raza("devon rex"));
		especie2.agregarRazas(new Raza("cornis"));
		especie2.agregarRazas(new Raza("exotico"));
		especie2.agregarRazas(new Raza("fold"));
		especie2.agregarRazas(new Raza("foldex"));
		especie2.agregarRazas(new Raza("german rex"));
		especie2.agregarRazas(new Raza("habana"));
		especie2.agregarRazas(new Raza("himalayo"));
		especie2.agregarRazas(new Raza("javanes"));
		especie2.agregarRazas(new Raza("korat"));
		especie2.agregarRazas(new Raza("maine coon"));
		especie2.agregarRazas(new Raza("manx"));
		especie2.agregarRazas(new Raza("mau egipcio"));
		especie2.agregarRazas(new Raza("munchkin"));
		especie2.agregarRazas(new Raza("nebelung"));
		especie2.agregarRazas(new Raza("ocicat"));
		especie2.agregarRazas(new Raza("oriental"));
		especie2.agregarRazas(new Raza("persa"));
		especie2.agregarRazas(new Raza("pixie bob"));
		especie2.agregarRazas(new Raza("ragdoll"));
		especie2.agregarRazas(new Raza("sagrado de birmania"));
		especie2.agregarRazas(new Raza("scottish fold"));
		especie2.agregarRazas(new Raza("selkirk rex"));
		especie2.agregarRazas(new Raza("serengeti"));
		especie2.agregarRazas(new Raza("seychellois"));
		especie2.agregarRazas(new Raza("siames"));
		especie2.agregarRazas(new Raza("silvestre"));
		especie2.agregarRazas(new Raza("singapura"));
		especie2.agregarRazas(new Raza("skogkatt"));
		especie2.agregarRazas(new Raza("snowshoe"));
		especie2.agregarRazas(new Raza("sokoke"));
		especie2.agregarRazas(new Raza("somali"));
		especie2.agregarRazas(new Raza("sphynx"));
		especie2.agregarRazas(new Raza("tonquines"));
		especie2.agregarRazas(new Raza("van yurco"));
		especie2.agregarRazas(new Raza("york chocolate"));
	
	}
	
	private static void insertarMotivosTurno(){
	
		controlMotivo = new ControlMotivo();
		Motivo motivo1 = new Motivo("peluqueria");
		controlMotivo.guardarMotivo(motivo1);
		Motivo motivo2 = new Motivo("envio a domicilio");
		controlMotivo.guardarMotivo(motivo2);
		Motivo motivo3 = new Motivo("colocacion de abortivo");
		controlMotivo.guardarMotivo(motivo3);
		Motivo motivo4 = new Motivo("limpieza de dientes");
		controlMotivo.guardarMotivo(motivo4);
		Motivo motivo5 = new Motivo("consulta domicilio");
		controlMotivo.guardarMotivo(motivo5);
		Motivo motivo6 = new Motivo("radiografia");
		controlMotivo.guardarMotivo(motivo6);
		Motivo motivo7 = new Motivo("ecografia");
		controlMotivo.guardarMotivo(motivo7);
		Motivo motivo8 = new Motivo("control de consulta");
		controlMotivo.guardarMotivo(motivo8);
		Motivo motivo9 = new Motivo("electocardiograma");
		controlMotivo.guardarMotivo(motivo9);
		Motivo motivo10 = new Motivo("otro");
		controlMotivo.guardarMotivo(motivo10);
	
	}
}
