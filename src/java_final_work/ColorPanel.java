package java_final_work;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Rectangle;
import java.awt.Scrollbar;
import java.awt.TexturePaint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.peer.ScrollbarPeer;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.BevelBorder;

public class ColorPanel extends JPanel implements  MouseListener,ActionListener, AdjustmentListener, ItemListener{
	//JButton color_border_more,color_in_more;
	Scrollbar bG,bB,iR,iG,iB;
	Scrollbar bR;
	JPanel p2,p1;
	ButtonGroup bg,bg1 ;
	Label color_in,color_border;
	 JDialog jDialog;
	 static int ibR=0,ibG=0,ibB=0,iiR=255,iiG=255,iiB=255;
	 JCheckBox checkBox1;
	 Button word_b,word_i,picture_b,picture_i;
	 static boolean ifword=false,ifpicture=false,ifword1=true,ifpicture1=true,ifword2=false,ifpicture2=false;
	 
	 
	 TexturePaint texturePaint1,texturePaint2;
	 
	public ColorPanel() {
		// TODO Auto-generated constructor stub
		setLayout(new FlowLayout(FlowLayout.LEFT,20,5));
		Painter.color_border = new Color(ibR, ibG, ibB);
		Painter.color_inside =null;
		p1 = new JPanel();
		p1.setLayout(new GridLayout(3, 1));
		JLabel l1 = new JLabel();
		l1.setText("线条颜色");
		color_border = new Label();
		//color_border.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		color_border.setPreferredSize(new Dimension(40, 40));
		color_border.setBackground(new Color(0,0,0));
		color_border.addMouseListener(this);
		//color_border_more = new JButton("more");
		//color_border_more.addActionListener(this);
		p1.add(l1);p1.add(color_border);//p1.add(color_border_more);
		add(p1);
	 p2 =new JPanel(null);
		p2.setLayout(new GridLayout(3, 1));
	
		bR = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1,0,255);
		bR.setPreferredSize(new Dimension(200, 20));
		
		
		bG = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1,0,255);
		bG.setPreferredSize(new Dimension(200, 20));
		
		bB = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1,0,255);
		bB.setPreferredSize(new Dimension(200, 20));
		
		bR.setBackground(Color.BLACK);
		bG.setBackground(Color.BLACK);
		bB.setBackground(Color.BLACK);
		
		bB.addAdjustmentListener(this);
		bR.addAdjustmentListener(this);
		bG.addAdjustmentListener(this);

		p2.add(bR);p2.add(bG);p2.add(bB);
		add(p2);
		
		JPanel p3 = new JPanel();
		p3.setLayout(new GridLayout(3, 1));
		color_in = new Label();
		color_in.setBackground(new Color(255, 255, 255));
		//color_in.setBorder(BorderFactory.createEtchedBorder(BevelBorder.RAISED));
		color_in.setPreferredSize(new Dimension(40, 40));
		color_in.addMouseListener(this);
		JLabel l2= new JLabel("填充颜色");
		checkBox1 = new JCheckBox("无填充",true);
		checkBox1.addItemListener(this);
		p3.add(l2);p3.add(color_in);p3.add(checkBox1);
		add(p3);
		JPanel p4 = new JPanel();
		p4.setLayout(new GridLayout(3, 1));
		 
		iB = new Scrollbar(Scrollbar.HORIZONTAL, 255, 1,0,256);
		iR = new Scrollbar(Scrollbar.HORIZONTAL, 255, 1,0,256);
		iG = new Scrollbar(Scrollbar.HORIZONTAL,255, 1,0,256);
		
		iR.setEnabled(false);
		iG.setEnabled(false);
		iB.setEnabled(false);
		
		iR.setPreferredSize(new Dimension(200, 20));
		iG.setPreferredSize(new Dimension(200, 20));
		iB.setPreferredSize(new Dimension(200, 20));
		
		iR.setBackground(new Color(255, 0, 0));
		iG.setBackground(new Color(0, 255, 0));
		iB.setBackground(new Color(0, 0, 255));
		
		iB.addAdjustmentListener(this);
		iR.addAdjustmentListener(this);
		iG.addAdjustmentListener(this);
		
		p4.add(iR);p4.add(iG);p4.add(iB);
		
		add(p4);
		
		word_b = new Button("线条文字");word_b.addActionListener(this);
		word_i = new Button("内部文字");word_i.addActionListener(this);
		picture_b = new Button("线条图片");picture_b.addActionListener(this);
		picture_i = new Button("内部图片");picture_i.addActionListener(this);
		JPanel p5 = new JPanel( new GridLayout(4,1));
		p5.add(word_b);p5.add(word_i);p5.add(picture_b);p5.add(picture_i);

		add(p5);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==word_b||e.getSource()==word_i)
		{
			String text=JOptionPane.showInputDialog("请输入文字","文字");//输入文字
			if(text==null) return;
			
			checkBox1.setSelected(false);
			Color FontColor=new Color(0,0,0);
			FontColor = JColorChooser.showDialog( jDialog, "请选择一个颜色当文字颜色", FontColor );//请使用者选择颜色
			
			
			BufferedImage bufferedImage = new BufferedImage(Painter.draw_panel_width,Painter.draw_panel_height,BufferedImage.TYPE_3BYTE_BGR);//创一张新的 
Graphics2D
			g2d_bufferedImage = bufferedImage.createGraphics();//把Graphics拿出来书
			
			FontRenderContext frc = g2d_bufferedImage.getFontRenderContext();//读Graphics中的Font
			Font f = new Font("新细明体",Font.BOLD,10);//新Font
			TextLayout tl = new TextLayout(text, f, frc);//创新的TextLayout，并利用f(Font)跟画至于frc(FontRenderContext)
			
			int sw = (int) (tl.getBounds().getWidth()+tl.getCharacterCount());//计算TextLayout的长
			int sh = (int) (tl.getBounds().getHeight()+3);//计算TextLayout的高
			
			bufferedImage = new BufferedImage(sw,sh,BufferedImage.TYPE_3BYTE_BGR);//再创一张新的BufferedImage，这里利用相同指标指向不同记忆体
			g2d_bufferedImage = bufferedImage.createGraphics();//拿出Graphics来画，前一张BufferedImage只是为了计算文字长度与高度，这样才 能完整填满
			g2d_bufferedImage.setPaint(Color.WHITE);//设定颜色为白色
			g2d_bufferedImage.fill(new Rectangle(0,0,sw,sh));//画一个填满白色矩型
			g2d_bufferedImage.setPaint(FontColor);//设定颜色为之前选择文字颜色
			g2d_bufferedImage.drawString(text,0,10);//画一个String于BufferedImage上
			repaint();//更新画面
			texturePaint1 = new TexturePaint(bufferedImage, new Rectangle(sw,sh) );
			if(e.getSource()==word_b){
				Painter.color_border=texturePaint1;
				ifword1=true;ifpicture2=false;
				picture_b.setBackground(null);
				word_b.setBackground(Color.GRAY);
				Painter.color_border = texturePaint1;
			}
			else{
				Painter.color_inside=texturePaint1;
				ifword2=true;ifpicture2=false;
				word_i.setBackground(Color.GRAY);
				picture_i.setBackground(null);
				Painter.color_inside = texturePaint1;
			}
		}
		else if(e.getSource()==picture_i||e.getSource()==picture_b)
		{
			FileDialog fileDialog = new FileDialog( new Frame() , "选择一个图档", FileDialog.LOAD );//利用FileDialog抓取档名
			fileDialog.show();//秀出视窗
			if(fileDialog.getFile()==null) return;//按取消的处理
			checkBox1.setSelected(false);
			ImageIcon icon = new ImageIcon(fileDialog.getDirectory()+fileDialog.getFile());//利用FileDialog传进来的档名读取图片
			BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_3BYTE_BGR);//创一 张新的BufferedImage，为了要读取读进来的图片长宽，以免有空白
			bufferedImage.createGraphics().drawImage(icon.getImage(),0,0,this);//把icon画到BufferedImage上
			repaint();//重绘屏幕
			texturePaint2 = new TexturePaint(bufferedImage, new Rectangle( icon.getIconWidth(), icon.getIconHeight() ) );
			if(e.getSource()==picture_b)//判断边线颜色或内部填满色
				{ 
				ifword1=false;
				ifpicture1=true;
				word_b.setBackground(null);
				picture_b.setBackground(Color.GRAY);
				Painter.color_border=texturePaint2;
				}
			else{
				ifword2=false;
				ifpicture2=true;
				word_i.setBackground(null);
				picture_i.setBackground(Color.gray);
				Painter.color_inside=texturePaint2;
			}
		}
	
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==color_border){
			Color a = JColorChooser.showDialog( jDialog, "请选择边线颜色", (Color)Painter.color_border );
			Painter.color_border=a;
			word_b.setBackground(null);
			picture_b.setBackground(null);
			bR.setValue(a.getRed());
			bG.setValue(a.getGreen());
			bB.setValue(a.getBlue());
			color_border.setBackground(a);
		}
		
	else if(e.getSource()==color_in&&!checkBox1.isSelected()) {
	   Color b = JColorChooser.showDialog( jDialog, "请选择填充颜色", (Color)Painter.color_inside );
		Painter.color_inside=b;
		word_i.setBackground(null);
		picture_i.setBackground(null);
		iR.setValue(b.getRed());
		iG.setValue(b.getGreen());
		iB.setValue(b.getBlue());
		color_in.setBackground(b);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void adjustmentValueChanged(AdjustmentEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==bR)
		{
			ibR = bR.getValue();
			bR.setBackground(new Color(ibR, 0, 0));
			word_b.setBackground(null);
			picture_b.setBackground(null);
		}
		else if(e.getSource()==bG)
		{
			ibG=bG.getValue();
			bG.setBackground(new Color(0, ibG, 0));
			word_b.setBackground(null);
			picture_b.setBackground(null);
		}
		else if(e.getSource()==bB)
		{
			ibB=bB.getValue();
			bB.setBackground(new Color(0, 0, ibB));
			word_b.setBackground(null);
			picture_b.setBackground(null);
		}
		else if(e.getSource()==iR)
		{
			iiR=iR.getValue();
			iR.setBackground(new Color(iiR, 0, 0));
			word_i.setBackground(null);
			picture_i.setBackground(null);
		}
		else if(e.getSource()==iG)
		{
			iiG=iG.getValue();
			iG.setBackground(new Color(0, iiG, 0));
			word_i.setBackground(null);
			picture_i.setBackground(null);
		}
		else if(e.getSource()==iB)
		{
			iiB=iB.getValue();
			iB.setBackground(new Color(0, 0, iiB));
			word_i.setBackground(null);
			picture_i.setBackground(null);
		}
		color_border.setBackground(new Color(ibR, ibG, ibB));
		color_in.setBackground(new Color(iiR, iiG, iiB));
		Painter.color_border=new Color(ibR, ibG, ibB);
		Painter.color_inside=new Color(iiR, iiG, iiB);
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==checkBox1)
		{
			if(( checkBox1).isSelected()==true)
			{
				Painter.color_inside = null;
				iR.setEnabled(false);
				iG.setEnabled(false);
				iB.setEnabled(false);
				color_in.setBackground(Color.white);
				
			}
			else 
			{
				Painter.color_inside = new Color(iiR,iiG,iiB);
				iR.setEnabled(true);
				iG.setEnabled(true);
				iB.setEnabled(true);
				color_in.setBackground(new Color(iiR, iiG, iiB));
			}
		}
	}
	
}
