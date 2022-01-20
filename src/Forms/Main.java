package Forms;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main extends JFrame {
	private JDesktopPane desktopPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setForeground(Color.DARK_GRAY);
		this.setExtendedState(MAXIMIZED_BOTH);
		
		desktopPane = new JDesktopPane();
		desktopPane.setForeground(Color.GRAY);
		desktopPane.setBackground(new Color(102, 102, 102));
		getContentPane().add(desktopPane, BorderLayout.CENTER);
		desktopPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Arial", Font.BOLD, 16));
		menuBar.setBorderPainted(false);
		menuBar.setForeground(new Color(255, 255, 255));
		menuBar.setBackground(new Color(102, 102, 102));
		menuBar.setBounds(0, 0, 101, 22);
		desktopPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("Gestion");
		mnNewMenu.setFont(new Font("Arial", Font.BOLD, 18));
		mnNewMenu.setForeground(new Color(255, 255, 255));
		mnNewMenu.setBackground(new Color(102, 102, 102));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("-->SmartPhones");
		mntmNewMenuItem.setIcon(new ImageIcon("C:\\wildfly eclipse\\Workspace\\Client\\appel-sur-smartphone.png"));
		mntmNewMenuItem.setFont(new Font("Arial", Font.BOLD, 15));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GSmartPhone s=new GSmartPhone();
				desktopPane.add(s);
				s.setVisible(true);
				Dimension d=desktopPane.getSize();
		        Dimension jIF=s.getSize();
		        s.setLocation((d.width-jIF.width)/2,(d.height-jIF.height)/2);
			}
		});
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("-->Users");
		mntmNewMenuItem_1.setIcon(new ImageIcon("C:\\wildfly eclipse\\Workspace\\Client\\utilisateur-de-lhomme.png"));
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GUser s=new GUser();
				desktopPane.add(s);
				s.setVisible(true);
				Dimension d=desktopPane.getSize();
		        Dimension jIF=s.getSize();
		        s.setLocation((d.width-jIF.width)/2,(d.height-jIF.height)/2);
			}
		});
		mntmNewMenuItem_1.setFont(new Font("Arial", Font.BOLD, 15));
		mntmNewMenuItem_1.setForeground(new Color(0, 0, 0));
		mntmNewMenuItem_1.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNewMenuItem_1);
		mntmNewMenuItem.setForeground(new Color(0, 0, 0));
		mntmNewMenuItem.setBackground(new Color(255, 255, 255));
		mnNewMenu.add(mntmNewMenuItem);
		
		
	}
}
