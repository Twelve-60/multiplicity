package multiplicity.appgallery.selectionexample;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

import multiplicity.app.AbstractMultiplicityApp;
import multiplicity.app.AbstractSurfaceSystem;
import multiplicity.app.singleappsystem.SingleAppMultiplicitySurfaceSystem;
import multiplicity.csysng.behaviours.BehaviourMaker;
import multiplicity.csysng.behaviours.RotateTranslateScaleBehaviour;
import multiplicity.csysng.items.IImage;
import multiplicity.csysng.items.ILabel;
import multiplicity.csysng.items.overlays.ICursorOverlay;
import multiplicity.csysng.items.overlays.ICursorTrailsOverlay;
import multiplicity.input.IMultiTouchEventProducer;

import com.jme.math.Vector2f;


public class SelectionExample extends AbstractMultiplicityApp {

	public SelectionExample(AbstractSurfaceSystem ass, IMultiTouchEventProducer producer) {
		super(ass, producer);
	}

	@Override
	public void onAppStart() {
	    IImage bg = getContentFactory().createImage("backgroundimage", UUID.randomUUID());
        bg.setImage(SelectionExample.class.getResource("yellowflowers_1680x1050.png"));
        bg.centerItem();
        add(bg);
	    
	   
		
		ICursorOverlay cursors = getContentFactory().createCursorOverlay("cursorOverlay", UUID.randomUUID());
        cursors.respondToMultiTouchInput(getMultiTouchEventProducer());     
        add(cursors);

        
        ICursorTrailsOverlay trails = getContentFactory().createCursorTrailsOverlay("trails", UUID.randomUUID());
        trails.respondToItem(bg);
        trails.setFadingColour(Color.white);
        add(trails);
        
        SelectionMaker smaker = new SelectionMaker(this);
        this.getMultiTouchEventProducer().registerMultiTouchEventListener(smaker);
        addDefaultSelectableLabel("Abc", 200, 200, smaker);
        addDefaultSelectableLabel("Def", 260, 200, smaker);
        
        getZOrderManager().sendToBottom(bg, null);
        getZOrderManager().neverBringToTop(bg);
	}
	
	private void addDefaultSelectableLabel(String content, float x, float y, SelectionMaker sm) {
		ILabel lbl = getContentFactory().createLabel(content, UUID.randomUUID());
		lbl.setText(content);
		lbl.setRelativeLocation(new Vector2f(x, y));
		lbl.setFont(new Font("Arial", Font.PLAIN, 24));
		lbl.setSize(200, 100);
		lbl.setTextColour(Color.red);
		BehaviourMaker.addBehaviour(lbl, RotateTranslateScaleBehaviour.class);
		
		add(lbl);
		sm.register(lbl);
		
		getZOrderManager().bringToTop(lbl, null);
	}
	
	public static void main(String[] args) throws SecurityException, IllegalArgumentException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
		SingleAppMultiplicitySurfaceSystem.startSystem(SelectionExample.class);
	}

}
