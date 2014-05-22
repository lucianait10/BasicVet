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

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;

class BasicLinkButtonUI extends MetalButtonUI {
	  private static final BasicLinkButtonUI ui = new BasicLinkButtonUI();

	  public BasicLinkButtonUI() {
	  }

	  public static ComponentUI createUI(JComponent jcomponent) {
	    return ui;
	  }  

	  
	protected void paintText(Graphics g, JComponent com, Rectangle rect,String s) {
	    JLinkButton bn = (JLinkButton) com;
	    ButtonModel bnModel = bn.getModel();
	    if (bnModel.isEnabled()) {
	      if (bnModel.isPressed())
	        bn.setForeground(bn.getActiveLinkColor());
	      else if (bn.isLinkVisited())
	        bn.setForeground(bn.getVisitedLinkColor());

	      else
	        bn.setForeground(bn.getLinkColor());
	    } else {
	      if (bn.getDisabledLinkColor() != null)
	        bn.setForeground(bn.getDisabledLinkColor());
	    }
	    super.paintText(g, com, rect, s);
	    int behaviour = bn.getLinkBehavior();
	    boolean drawLine = false;
	    if (behaviour == JLinkButton.HOVER_UNDERLINE) {
	      if (bnModel.isRollover())
	        drawLine = true;
	    } else if (behaviour == JLinkButton.ALWAYS_UNDERLINE || behaviour == JLinkButton.SYSTEM_DEFAULT)
	      drawLine = true;
	    if (!drawLine)
	      return;
	    FontMetrics fm = g.getFontMetrics();
	    int x = rect.x + getTextShiftOffset();
	    int y = (rect.y + fm.getAscent() + fm.getDescent() + getTextShiftOffset()) - 1;
	    if (bnModel.isEnabled()) {
	      g.setColor(bn.getForeground());
	      g.drawLine(x, y, (x + rect.width) - 1, y);
	    } else {
	      g.setColor(bn.getBackground().brighter());
	      g.drawLine(x, y, (x + rect.width) - 1, y);
	    }
	  }
	}
