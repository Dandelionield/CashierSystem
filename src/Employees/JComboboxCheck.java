import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

public class JComboboxCheck extends JComboBox<Item> {
    
    public JComboboxCheck(String title) {
        setRenderer(new ComboBoxRenderer(title));
    }


    public void addItem(String name) {
        addItem(new Item(name));
    }

    public Object[] getCheckList() {
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < getItemCount(); i++) {
            if (getItemAt(i).getCheck()) {
                list.add(getItemAt(i).toString());
            }
        }

        return list.toArray();
    }

    private class ComboBoxRenderer extends Component implements ListCellRenderer<Object> {
        String title;

        public ComboBoxRenderer(String title) {
            this.title = title;
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Object> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            
            if (value instanceof Item) {
                JCheckBox checkBox = new JCheckBox();
                Item item = (Item) value;

                checkBox.setBackground(item.getCheck() ? Color.RED : Color.WHITE);
                checkBox.setOpaque(true);
                checkBox.setText(item.toString());
                checkBox.setSelected(item.getCheck());

                return checkBox;
            }

            return (index == -1) ? new JLabel(title) : this;
        }
        
    }
}

class Item {
    private String text;
    private boolean isCheck = false;

    public Item(String text) {
        this.text = text;
    }

    public void setCheck() {
        isCheck = !isCheck;
    }

    public boolean getCheck() {
        return isCheck;
    }

    public String toString() {
        return text;
    }
}