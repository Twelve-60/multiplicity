package multiplicity.csysng.items.mutablelabel;

import multiplicity.csysng.items.item.IItem;

public interface IMutableLabel extends IItem {
	public void setText(String text);
	public void setFont(String resourcePath);
	public void removeChar();
	public String getText();
	public void appendChar(char charAt);
}
