package com.loader.openrsc.frame;

import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;

import java.awt.Dimension;
import javax.swing.JProgressBar;
import com.loader.openrsc.frame.elements.ControlButton;
import com.loader.openrsc.frame.elements.LaunchButton;
import com.loader.openrsc.frame.elements.LinkButton;
import com.loader.openrsc.frame.elements.NewsBox;
import com.loader.openrsc.frame.listeners.PositionListener;
import com.loader.openrsc.net.xml.XMLReader;
import com.loader.openrsc.util.Utils;

import javax.swing.JLabel;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class AppFrame extends JFrame
{
	public static AppFrame instance;
    private NewsBox box;
    private JLabel text;
    private JLabel subText;
    private JLabel bg;
    private LaunchButton launch;
    private JProgressBar progress;
    private JLabel status;
    private JLabel postedDate;
    private JLabel checkLabel;
    
    public AppFrame() {
        this.setPreferredSize(new Dimension(980, 560));
        this.setUndecorated(true);
        this.setTitle("Open RSC");
        this.setIconImage(Utils.getImage("icon.png").getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        AppFrame.instance = this;
    }
    
    public void build() {
        (this.bg = new JLabel(Utils.getImage("background.png"))).setBounds(0, 0, 980, 560);
        
        this.add(this.bg);
        this.addLogo();
        this.addButtons();
        this.addNewsBox();
        /*(this.postedDate = new JLabel(XMLReader.getNews().getMessages().get(0).getSplitDate())).setBounds(131, 116, 128, 8);
        this.postedDate.setFont(Utils.getFont("runescape_uf.ttf", 1, 10.0f));
        this.postedDate.setHorizontalAlignment(0);
        this.bg.add(this.postedDate);*/
        this.addMouseListener(new PositionListener(this));
        this.addMouseMotionListener(new PositionListener(this));
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void addNewsBox() {
        (this.box = new NewsBox()).setBounds(36, 100, 451, 340);
        this.bg.add(this.box);
        XMLReader.init(this.box);
    }
    
    private void addLogo() {
        (this.text = new JLabel("Open RSC".toUpperCase())).setBounds(30, 24, 100, 15);
        this.text.setForeground(new Color(255, 223, 0));
        this.text.setFont(Utils.getFont("runescape_uf.ttf", 1, 16.0f));
        this.bg.add(this.text);
        (this.subText = new JLabel("Game Launcher")).setBounds(30, 35, 100, 15);
        this.subText.setForeground(new Color(200, 200, 200));
        this.subText.setFont(Utils.getFont("runescape_uf.ttf", 1, 14.0f));
        this.bg.add(this.subText);
        (this.status = new JLabel("Server Status: ---")).setForeground(Color.WHITE);
        this.status.setFont(Utils.getFont("runescape_uf.ttf", 0, 16.0f));
        this.status.setHorizontalAlignment(4);
        this.status.setBounds(625, 74, 315, 19);
        this.bg.add(this.status);
    }
    
    public JLabel getCheckLabel() {
        return this.checkLabel;
    }

	public void setDownloadProgress(String f, float percent) {
		(this.progress = new JProgressBar(0, 100)).setBounds(27, 530 , 640, 18);
		if (percent > 90) {
                    this.progress.setForeground(new Color(0x009900));
                    this.progress.repaint();
                } else if (percent > 40 && percent < 80) {
                    this.progress.setForeground(new Color(0xffad33));
                    this.progress.repaint();
                } else {
                    this.progress.setForeground(new Color(0x990000));
                    this.progress.repaint();
                }
                this.progress.setBackground(new Color(0x2D2E2A));
		this.progress.setFont(Utils.getFont("runescape_uf.ttf", 1, 14.0f));
		this.progress.setOpaque(true);
		this.progress.setStringPainted(true);
		this.progress.setBorderPainted(false);
		this.progress.setValue((int) percent);
		this.progress.setString(f + " - " + (int) percent + "%");
		this.bg.add(this.progress);
		this.progress.repaint();

	}
	
    
    private void addButtons() {
        this.bg.add(new LinkButton("News" , new Rectangle(27, 480, 119, 40)));
        this.bg.add(new LinkButton("Bug Reports", new Rectangle(158, 480, 119, 40)));
        this.bg.add(new LinkButton("Discord", new Rectangle(288, 480, 119, 40)));
        this.bg.add(new LinkButton("GitHub", new Rectangle(418, 480, 119, 40)));
        this.bg.add(new LinkButton("FAQ", new Rectangle(548, 480, 119, 40)));
        (this.launch = new LaunchButton()).setBounds(797, 481, 174, 69);
        this.bg.add(this.launch);
        this.bg.add(new ControlButton(2, 958, 8, 10, 11));
        this.bg.add(new ControlButton(1, 940, 8, 10, 11));
    }
    
    public NewsBox getBox() {
        return this.box;
    }
    
    public static AppFrame get() {
        return AppFrame.instance;
    }
    
    public JProgressBar getProgress() {
        return this.progress;
    }
    
    public LaunchButton getLaunch() {
        return this.launch;
    }
    
    public JLabel getStatus() {
        return this.status;
    }
}
