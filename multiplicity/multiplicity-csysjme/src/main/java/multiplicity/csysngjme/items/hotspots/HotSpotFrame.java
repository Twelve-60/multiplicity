package multiplicity.csysngjme.items.hotspots;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

import multiplicity.csysng.behaviours.BehaviourMaker;
import multiplicity.csysng.behaviours.RotateTranslateScaleBehaviour;
import multiplicity.csysng.items.IColourRectangle;
import multiplicity.csysng.items.IItem;
import multiplicity.csysng.items.IPalet;
import multiplicity.csysng.items.events.ItemListenerAdapter;
import multiplicity.csysng.items.hotspot.IHotLink;
import multiplicity.csysng.items.hotspot.IHotSpotFrame;
import multiplicity.csysng.items.hotspot.IHotSpotItem;
import multiplicity.csysngjme.items.JMEColourRectangle;
import multiplicity.csysngjme.items.JMEFrame;
import multiplicity.input.events.MultiTouchCursorEvent;

import org.apache.log4j.Logger;

import com.jme.math.Vector2f;
import com.jme.renderer.ColorRGBA;
import com.jme.renderer.Renderer;
import com.jme.scene.Line;

public class HotSpotFrame extends JMEFrame implements IHotSpotFrame {
	
	private static final long serialVersionUID = 8114328886119432460L;
	private final static Logger logger = Logger.getLogger(HotSpotFrame.class.getName());

	public List<IHotSpotItem> hotSpots = new CopyOnWriteArrayList<IHotSpotItem>(); 
	public List<IHotLink> hotLinks = new CopyOnWriteArrayList<IHotLink>();
	protected List<Line> lines = new CopyOnWriteArrayList<Line>();
	protected boolean isLocked = false;
	private IColourRectangle frameOverlay;

    private boolean isVisable;

    private IPalet palet; ;

	public HotSpotFrame(String name, UUID uuid, int width, int height) {
		super(name, uuid, width, height);
	}

	@Override
	public void addFrameOverlay() {
		int width = (int) this.getSize().x;
		int height = (int) this.getSize().y;
		setFrameOverlay(new JMEColourRectangle("frameOverlay",UUID.randomUUID(), width , height));
		getFrameOverlay().initializeGeometry();
		getFrameOverlay().setSolidBackgroundColour(new ColorRGBA(0f, 0f, 0f, 0.2f));
		this.addItem(getFrameOverlay());
		this.getZOrderManager().sendToBottom(getFrameOverlay(), null);
		BehaviourMaker.addBehaviour(getFrameOverlay(), RotateTranslateScaleBehaviour.class);
	}
	
	@Override
	public void updateOverLay() {
        Vector2f frameOriginLocation = this.getRelativeLocation();
        Vector2f itemDisplacement = this.getFrameOverlay().getRelativeLocation();
        this.setRelativeLocation(new Vector2f(frameOriginLocation.x + itemDisplacement.x, frameOriginLocation.y + itemDisplacement.y));
        this.getFrameOverlay().setRelativeLocation(new Vector2f(0f, 0f));
        
        float itemScale = this.getFrameOverlay().getRelativeScale();
        if(itemScale < 2.5f && itemScale>0.2f)  {
            this.setRelativeScale(itemScale);
        }
        
        float relativeRotation = this.getFrameOverlay().getRelativeRotation();
        this.setRelativeRotation(relativeRotation);
        
//        overlayAction();
    }
	
	public void overlayAction() {
	    this.sendHotLinksToTop();
	    this.bringHotSpotsToTop();
	    this.bringPaletToTop();
	    this.sendHotLinksToTop();

	}

	public List<IHotSpotItem> getHotSpots() {
		return hotSpots;
	}
	
	public void setHotSpots(List<IHotSpotItem> hotSpots) {
		this.hotSpots = hotSpots;
	}

	public void addHotSpot(IItem item) {
		hotSpots.add((IHotSpotItem) item);
	}

	public void bringHotSpotsToTop() {
		for (IHotSpotItem iHotSpotItem : hotSpots) {
			this.getZOrderManager().bringToTop(iHotSpotItem, null);  
//			iHotSpotItem.redrawHotlink(iHotSpotItem);
			 
		}
//		this.sendHotLinksToTop();
	}

	public void addHotLink(IHotLink hotLink) {
	    this.hotLinks.add(hotLink);
	}
	
	public void removeHotLink(IHotLink hotLink) {
	    if( !hotLinks.isEmpty() && hotLinks.contains(hotLink)) {
	        hotLinks.remove(hotLink);
	    }
	        
	}
	
	@Override
    public void setHotLinks(List<IHotLink> hotLinks) {
        this.hotLinks = hotLinks;
    }

    public List<IHotLink> getHotLinks() {
        return hotLinks;
    }

	@Override
	public void bringPaletToTop() {
		// TODO Auto-generated method stub
		if(isLocked() == false ) {
		    logger.debug("palet to the top; is NOT locked");
		    sendOverlayToBottom();
//		    sendHotLinksToTop();
//		    bringHotSpotsToTop();
		} else  {
		    logger.debug("palet to the top; is locked");
		    sendOverlayToTop();
		    
//		    bringHotSpotsToTop();
//		    sendHotLinksToTop();
		}
		
//		  for (IHotLink hl : hotLinks) {
//	            hl.getZOrderManager().setItemZOrder(palet.getZOrderManager().getItemZOrder()-1);
//	       }
		this.getZOrderManager().bringToTop(palet, null);  
		
	}

    @Override
    public void setVisible(boolean isVisable) {
        this.isVisable = isVisable;
        
        if( isVisable ) {
            this.getManipulableSpatial().setRenderQueueMode(Renderer.QUEUE_ORTHO);
            this.getMaskGeometry().setRenderQueueMode(Renderer.QUEUE_ORTHO);
        } else {
            this.getMaskGeometry().setRenderQueueMode(Renderer.QUEUE_TRANSPARENT);
            this.getManipulableSpatial().setRenderQueueMode(Renderer.QUEUE_TRANSPARENT);

        }
    }

    @Override
    public boolean isVisable() {
        return this.isVisable;
    }

	@Override
	public boolean isLocked() {
		return isLocked;
	}

	@Override
	public void setLocked(boolean isLocked) {
		this.isLocked = isLocked;
		this.toggleLock();
	}

	@Override
	public void toggleLock() {
		if(isLocked) {
			//our frame is in lock mode, let's unlock it
			this.sendOverlayToBottom();
			border.setColor(new ColorRGBA(1f, 1f, 1f, 0.6f));
		}
		else {
			//our frame is in unlock mode, let's lock it
			this.sendOverlayToTop();
			border.setColor(new ColorRGBA(0f, 0f, 0f, 0f));
		}
	}

	@Override
	public void sendOverlayToBottom() {
		this.getZOrderManager().sendToBottom(getFrameOverlay(), null);
	}
	
	@Override
	public void sendOverlayToTop() {
	    this.getZOrderManager().bringToTop(getFrameOverlay(), null);
	}

	@Override
	public IColourRectangle getFrameOverlay() {
		return frameOverlay;
	}
	
	@Override
    public IPalet getPalet() {
	    return palet;
	}

    @Override
    public void addPalet(IPalet palet) {
        this.palet = palet;
        this.addItem(palet);
    }
    
    @Override
    public void sendHotLinksToBottom() {
        
        for (IHotLink hl : hotLinks) {
            this.getZOrderManager().sendToBottom(hl, null);
        }
    }
    
    @Override
    public void sendHotLinksToTop() {
        for (IHotLink hl : hotLinks) {
            this.getZOrderManager().bringToTop(hl, null);
        }
    }

    public void setFrameOverlay(JMEColourRectangle frameOverlay) {
        this.frameOverlay = frameOverlay;
    }
    
}
