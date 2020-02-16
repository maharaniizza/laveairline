import java.awt.Component;
import javax.swing.JList;
import javax.swing.plaf.basic.BasicComboBoxRenderer;

class ItemRenderer extends BasicComboBoxRenderer {

	public Component getListCellRendererComponent(
            JList list, Object value, int index,
            boolean isSelected, boolean cellHasFocus)
        {
            super.getListCellRendererComponent(list, value, index,
                isSelected, cellHasFocus);
                ComboRoles item = (ComboRoles)value;
                setText( item.getDescription() );
            return this;
        }

}
