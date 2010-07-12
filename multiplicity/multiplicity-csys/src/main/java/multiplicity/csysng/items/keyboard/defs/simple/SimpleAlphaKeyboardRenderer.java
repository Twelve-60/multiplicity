package multiplicity.csysng.items.keyboard.defs.simple;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import multiplicity.csysng.items.keyboard.IKeyboardGraphicsRenderer;
import multiplicity.csysng.items.keyboard.model.KeyboardDefinition;
import multiplicity.csysng.items.keyboard.model.KeyboardKey;

public class SimpleAlphaKeyboardRenderer implements IKeyboardGraphicsRenderer {
	
	private Font keyboardFont = new Font("Arial", Font.BOLD, 12);
	private FontMetrics fontMetrics;
	private KeyboardDefinition kbd;
	
	public SimpleAlphaKeyboardRenderer(KeyboardDefinition kbd) {
		this.kbd = kbd;
	}

	@Override
	public void drawKeyboard(Graphics2D g2d, boolean shiftDown, boolean altDown, boolean ctlDown) {
		
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		if(fontMetrics == null) {
			fontMetrics = g2d.getFontMetrics(keyboardFont);
		}

		g2d.setColor(Color.white);
		g2d.fillRect(0, 0, (int)kbd.getBounds().getMaxX(), (int)kbd.getBounds().getMaxY());
		g2d.setColor(Color.black);
		
		for(KeyboardKey k : kbd.getKeysIterator()) {				
			Point p = getShapeCenter(k.getKeyShape());			
			g2d.draw(k.getKeyShape());				
			g2d.setFont(keyboardFont);
			if(shiftDown) {
				int strWidth = fontMetrics.stringWidth(k.getKeyStringRepresentation().toUpperCase());
				g2d.drawString(k.getKeyStringRepresentation().toUpperCase(), p.x - strWidth/2, p.y + fontMetrics.getAscent()/2);
			}else{
				int strWidth = fontMetrics.stringWidth(k.getKeyStringRepresentation().toLowerCase());
				g2d.drawString(k.getKeyStringRepresentation().toLowerCase(), p.x - strWidth/2, p.y + fontMetrics.getAscent()/2);
			}
			
			
		}
		
	}

	private Point getShapeCenter(Shape keyShape) {
		Rectangle2D bounds = keyShape.getBounds2D();
		Point p = new Point((int)bounds.getCenterX(), (int)bounds.getCenterY());
		return p;
	}

	@Override
	public void keyPressed(KeyboardKey k, boolean shiftDown, boolean altDown, boolean ctlDown) {
		if(k.getKeyCode() == KeyEvent.VK_SHIFT) {
			shiftDown = true;
		}		
	}

	@Override
	public void keyReleased(KeyboardKey k, boolean shiftDown, boolean altDown, boolean ctlDown) {
		if(k.getKeyCode() == KeyEvent.VK_SHIFT) {
			shiftDown = false;
		}		
	}
}
