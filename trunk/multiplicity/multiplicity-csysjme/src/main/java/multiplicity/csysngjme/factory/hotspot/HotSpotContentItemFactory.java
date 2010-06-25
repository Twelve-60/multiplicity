package multiplicity.csysngjme.factory.hotspot;

import java.util.UUID;

import multiplicity.csysng.factory.IHotSpotContentFactory;
import multiplicity.csysng.items.hotspot.IHotSpotFrame;
import multiplicity.csysng.items.hotspot.IHotSpotItem;
import multiplicity.csysngjme.factory.ContentItemFactoryUtil;
import multiplicity.csysngjme.items.hotspots.HotSpotFrame;
import multiplicity.csysngjme.items.hotspots.HotSpotItem;

public class HotSpotContentItemFactory implements IHotSpotContentFactory {
    
    @Override
    public IHotSpotFrame createHotSpotFrame(String name, UUID uuid, int width, int height) {
        HotSpotFrame frame = new HotSpotFrame(ContentItemFactoryUtil.validateName(name), ContentItemFactoryUtil.validateUUID(uuid), width, height);
        frame.initializeGeometry();
        return frame;
    }
    
    @Override
    public IHotSpotItem createHotSpotItem(String name, UUID uuid, int width, int height) {
        HotSpotItem hs = new HotSpotItem(name, uuid, width, height);
        hs.initializeGeometry();
        return hs;
    }

    @Override
    public IHotSpotItem createHotSpotItem(IHotSpotFrame hotSpotFrameContent,
            String name, UUID uuid, int width, int height) {
        HotSpotItem hs = new HotSpotItem(hotSpotFrameContent,name, uuid, width, height);
        hs.initializeGeometry();
        return null;
    }

}
