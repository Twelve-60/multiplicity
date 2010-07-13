package multiplicity.csysng.items.keyboard.behaviour;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jme.math.Vector3f;

import multiplicity.csysng.behaviours.IBehaviour;
import multiplicity.csysng.items.IItem;
import multiplicity.csysng.items.keyboard.IKeyboard;
import multiplicity.csysng.items.keyboard.model.KeyModifiers;
import multiplicity.csysng.items.keyboard.model.KeyboardKey;
import multiplicity.input.IMultiTouchEventListener;
import multiplicity.input.events.MultiTouchCursorEvent;
import multiplicity.input.events.MultiTouchEvent;
import multiplicity.input.events.MultiTouchObjectEvent;
import multiplicity.jmeutils.UnitConversion;

public class KeyboardBehaviour implements IBehaviour, IMultiTouchEventListener {

	private IKeyboard item;
	private List<IMultiTouchKeyboardListener> listeners = new ArrayList<IMultiTouchKeyboardListener>();
	private Map<Long,KeyboardKey> trackedKeyPresses = new HashMap<Long,KeyboardKey>();
	private boolean shiftDown = false;
	private boolean ctlDown = false;
	private boolean altDown = false;

	@Override
	public void removeItemActingOn() {
		if(item != null) {
			item.getMultiTouchDispatcher().remove(this);
		}
		this.item = null;
	}

	@Override
	public void setItemActingOn(IItem item) {
		if(item instanceof IKeyboard) {
			this.item = (IKeyboard) item;
			item.getMultiTouchDispatcher().addListener(this);
		}else{
			//TODO: log severe
		}
	}

	public void addListener(IMultiTouchKeyboardListener l) {
		if(!listeners.contains(l)) {
			listeners.add(l);			
		}
	}

	@Override
	public void cursorChanged(MultiTouchCursorEvent event) {	
		KeyboardKey kk = trackedKeyPresses.get(event.getCursorID());
		if(kk != null) {
			KeyboardKey newKey = getKeyUnderEvent(event);
			if(newKey == null || !(newKey.getKeyCode() == kk.getKeyCode())) {
				
				if(kk.getModifiers() == KeyModifiers.SHIFT) {
					shiftDown = false;
				}
				
				for(IMultiTouchKeyboardListener kl : listeners) {
					kl.keyReleased(kk, shiftDown, altDown, ctlDown);
				}
			}
		}

	}

	@Override
	public void cursorClicked(MultiTouchCursorEvent event) {

	}

	@Override
	public void cursorPressed(MultiTouchCursorEvent event) {
		KeyboardKey kk = getKeyUnderEvent(event);
		if(kk != null) {
			if(kk.getModifiers() == KeyModifiers.SHIFT) {
				shiftDown = true;
				trackedKeyPresses.put(event.getCursorID(), kk);
			}
			for(IMultiTouchKeyboardListener kl : listeners) {
				kl.keyPressed(kk, shiftDown, altDown, ctlDown);
			}

		}		
	}

	@Override
	public void cursorReleased(MultiTouchCursorEvent event) {
		KeyboardKey kk = getKeyUnderEvent(event);
		if(kk != null) {
			if(kk.getModifiers() == KeyModifiers.SHIFT) {
				shiftDown = false;
			}
			for(IMultiTouchKeyboardListener kl : listeners) {
				kl.keyReleased(kk, shiftDown, altDown, ctlDown);
			}
		}

	}

	private KeyboardKey getKeyUnderEvent(MultiTouchEvent event) {
		Vector3f in = new Vector3f();
		UnitConversion.tableToScreen(event.getPosition().x, event.getPosition().y, in);		
		Vector3f out = new Vector3f();
		item.getManipulableSpatial().getParent().worldToLocal(in, out);
		Point2D p = new Point2D.Float((float)(item.getKeyboardDefinition().getBounds().getMaxX()/2  + out.x),
				(float)-(out.y - item.getKeyboardDefinition().getBounds().getMaxY()/2));
		return item.getKeyboardDefinition().getKeyAt(p);
	}

	@Override
	public void objectAdded(MultiTouchObjectEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void objectChanged(MultiTouchObjectEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public void objectRemoved(MultiTouchObjectEvent event) {
		// TODO Auto-generated method stub

	}


}