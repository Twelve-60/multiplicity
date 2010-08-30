package multiplicity.appgallery.stitcher.listeners;

import multiplicity.appgallery.stitcher.StitcherApp;
import multiplicity.csysng.items.IHotSpotText;
import multiplicity.csysng.items.hotspot.IHotLink;
import multiplicity.csysng.items.hotspot.IHotSpotFrame;
import multiplicity.csysng.items.hotspot.IHotSpotItem;
import multiplicity.input.MultiTouchEventAdapter;
import multiplicity.input.events.MultiTouchCursorEvent;

import org.apache.log4j.Logger;

public class HotLinkMultiTouchListener extends MultiTouchEventAdapter {
    
    private final static Logger logger = Logger.getLogger(HotLinkMultiTouchListener.class.getName());
    private IHotLink hotLink;
    private StitcherApp stitcherApp;
    
    public HotLinkMultiTouchListener(IHotLink hotLink, StitcherApp stitcherApp) {
        this.hotLink = hotLink;
        this.stitcherApp = stitcherApp;
        this.hotLink.getMultiTouchDispatcher().addListener(this);
    }
    
    @Override
    public void cursorPressed(MultiTouchCursorEvent event) {
        super.cursorPressed(event);
        logger.debug("hotlink PRESSED");
        
        //hide show
        if( hotLink.tap() > 4 ) {
          logger.debug("deleting hotlink");
          
          
          IHotSpotItem hotSpotItem = hotLink.getHotSpotItem();
          
          hotSpotItem.removeHotLink(hotLink);
          
          IHotSpotFrame sourceFrame = (IHotSpotFrame) hotLink.getHotSpotItem().getParentItem();
          IHotSpotFrame hotSpotFrameContent = hotLink.getHotSpotItem().getHotSpotFrameContent();
          if(!(hotSpotFrameContent instanceof IHotSpotText) ) {
              hotSpotFrameContent.removeHotSpot(hotSpotItem);
          }
          //remove hotspot
          sourceFrame.removeHotSpot(hotSpotItem);
          
          stitcherApp.remove(hotLink);
          stitcherApp.getHotspotContentFrames().remove(hotSpotFrameContent);
          stitcherApp.remove(hotSpotFrameContent);
        
          sourceFrame.bringHotSpotsToTop();
          
        }
    }


}
