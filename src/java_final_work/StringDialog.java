package java_final_work;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class StringDialog extends JDialog implements ItemListener,ActionListener,ChangeListener
{
	JSpinner size = new JSpinner();
	JCheckBox bold, italic;
	JButton ok, cancel;
	JComboBox type;
	JPanel jPanel0,jPanel1,jPanel2,jPanel3,jPanel4,jPanel5;
	public StringDialog(JFrame jFrame){	
		
	 super( jFrame, "请选择文字、字型、大小与属性", true);
	 
	 GraphicsEnvironment e = GraphicsEnvironment.getLocalGraphicsEnvironment();
	 String[] fontName = e.getAvailableFontFamilyNames();
	 type = new JComboBox<>();
	 for(int i=0;i<fontName.length;i++)
		 type.addItem(fontName[i]);
	 type.addItemListener(this);
	 
	size.setValue(new Integer(100));
	bold = new JCheckBox( "粗体" ,true);
	italic = new JCheckBox( "斜体" ,true);
	ok = new JButton("确定");
	cancel = new JButton("取消");
	jPanel0= new JPanel(new GridLayout(5,1));
	jPanel1 = new JPanel(new FlowLayout());
	jPanel2 = new JPanel(new FlowLayout());
	jPanel3 = new JPanel(new FlowLayout());
	jPanel4 = new JPanel(new FlowLayout());
	jPanel5 = new JPanel(new FlowLayout());
	Container jDialog_c = getContentPane();
		
	jDialog_c.setLayout(new FlowLayout());
	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE );
	setSize(350, 300);
	setLocation(300,300);
	jPanel1.add(new JLabel("文字:"));
	jPanel1.add(Draw.textField_word);
	jPanel2.add(new JLabel("字体:"));
	jPanel2.add(type);
	jPanel3.add(new JLabel("大小:"));
	jPanel3.add(size);
	jPanel4.add(new JLabel("属性:"));
	jPanel4.add(bold);
	jPanel4.add(italic);
	jPanel5.add(ok);
	jPanel5.add(cancel);
	jPanel0.add(jPanel1);
	jPanel0.add(jPanel2);
	jPanel0.add(jPanel3);
	jPanel0.add(jPanel4);
	jPanel0.add(jPanel5);
	jPanel0.setPreferredSize(new Dimension( 180 , 150 ));
	
	jDialog_c.add(jPanel0);
	    
	
	bold.addItemListener( this );
	italic.addItemListener( this );
	size.addChangeListener( this );
	ok.addActionListener(this);
	cancel.addActionListener(this);
	jPanel0.setPreferredSize(new Dimension( 300 , 250 ));
	}

	
	
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if ( e.getSource() == bold )
			if ( e.getStateChange() == ItemEvent.SELECTED )
				Draw.valBold = Font.BOLD;
			else
				Draw.valBold = Font.PLAIN;
		if ( e.getSource() == italic )
			if ( e.getStateChange() == ItemEvent.SELECTED )
				Draw.valItalic = Font.ITALIC;
			else
				Draw.valItalic = Font.PLAIN;
		if(e.getStateChange() == ItemEvent.SELECTED)
        {
            String s=(String)type.getSelectedItem();
            Draw.textField_font.setText(s);
        }    
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		Draw.size = Integer.parseInt(size.getValue().toString());
		if(Draw.size <= 0) {
			size.setValue(new Integer(1));
			Draw.size = 1;
		}
	}
}