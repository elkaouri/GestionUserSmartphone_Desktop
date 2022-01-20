package Forms;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

import dao.ServerDaoRemote;
import entities.User;
import entities.SmartPhone;


import java.awt.Color;
import java.awt.Font;
import javax.swing.JTextField;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JTable;

import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.toedter.calendar.JDateChooser;
import javax.swing.ImageIcon;

public class GUser extends JInternalFrame {
	private DefaultTableModel model;
    private int id = -1;
    private JTextField nomField;
    private int i;
    private Long idS;
    private String[] l= null;
    private String[] l2= null;
    private String type;
    private String bloc=null;
    private Long idB;
    private JTable table;
    private JTextField prenomField;
    private JTextField emailField;
    private JTextField telField;
    private JDateChooser dateChooser;
    
    public static ServerDaoRemote lookUpSalleDaoRemote() throws NamingException {
		Hashtable<Object, Object> jndiProperties = new Hashtable<Object, Object>();
		
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		jndiProperties.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
		final Context context = new InitialContext(jndiProperties);

		return (ServerDaoRemote) context.lookup("appEAR/Server/ServiceDao!dao.ServerDaoRemote");}
	/**
	 * Launch the application.
	 */
 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					GUser frame = new GUser();
					frame.setVisible(true);
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	private void recharger() {
        model.setRowCount(0);
        try {
        	ServerDaoRemote stub = lookUpSalleDaoRemote();
			List<User> users =stub.findAllUsers();
            for (User u : users) {
                model.addRow(new Object[]{
                    u.getIdUser(),
                    u.getNom(),
                    u.getPrenom(),
                    u.getEmail(),
                    u.getTel(),
                    u.getDate()
                       
                }
                );

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

	/**
	 * Create the frame.
	 * 
	 */
	
	public GUser() {
		setFrameIcon(new ImageIcon("C:\\wildfly eclipse\\Workspace\\Client\\utilisateur-de-lhomme.png"));
		setTitle("Gestion des Utilisateurs");
		
		getContentPane().setBackground(Color.LIGHT_GRAY);
		//updateComboBoxBloc();
		
		setClosable(true);
		
		setMaximizable(true);
		setIconifiable(true);
		setResizable(true);
		setBounds(100, 100, 824, 435);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(0, 0, 808, 161);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Nom :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(34, 12, 53, 14);
		panel.add(lblNewLabel);
		
		nomField = new JTextField();
		nomField.setBounds(198, 12, 114, 20);
		panel.add(nomField);
		nomField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("ADD");
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setForeground(Color.BLACK);
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					ServerDaoRemote stub = lookUpSalleDaoRemote();
					String nom=nomField.getText();
					String prenom=prenomField.getText();
					String email=emailField.getText();
					String tel=telField.getText();
					Date dateNais=dateChooser.getDate();

					User b=new User(nom,prenom,email,tel,dateNais);
					stub.create(b);
					recharger();
					 nomField.setText("");
						prenomField.setText("");
						emailField.setText("");
						telField.setText("");
						dateChooser.setCalendar(null);
					
									
				} catch (NamingException s) {
					s.printStackTrace();
				}
			}
				

			
		});
		
		
		btnNewButton.setBounds(497, 42, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("UPDATE");
		btnNewButton_1.setBackground(Color.WHITE);
		btnNewButton_1.setForeground(Color.BLACK);
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ServerDaoRemote stub = lookUpSalleDaoRemote();
					
					 stub.update(stub.findUserById(idB),nomField.getText(),prenomField.getText(),emailField.getText(),telField.getText(),dateChooser.getDate());
						
					 recharger();
					
					 nomField.setText("");
						prenomField.setText("");
						emailField.setText("");
						telField.setText("");
						dateChooser.setCalendar(null);
					
									
				} catch (NamingException s) {
					s.printStackTrace();
				}
			}
		});
		btnNewButton_1.setBounds(497, 72, 89, 23);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("DELETE");
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton_2.setForeground(Color.BLACK);
		btnNewButton_2.setBackground(Color.WHITE);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ServerDaoRemote stub = lookUpSalleDaoRemote();
					  System.out.println(idB);
					  stub.delete(stub.findUserById(idB));
					
					 recharger();
					 nomField.setText("");
						prenomField.setText("");
						emailField.setText("");
						telField.setText("");
						dateChooser.setCalendar(null);
					
									
				} catch (NamingException s) {
					s.printStackTrace();
				}
				
			}
		});
		btnNewButton_2.setBounds(497, 101, 89, 23);
		panel.add(btnNewButton_2);
		
		prenomField = new JTextField();
		prenomField.setColumns(10);
		prenomField.setBounds(198, 42, 114, 20);
		panel.add(prenomField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(198, 71, 114, 20);
		panel.add(emailField);
		
		telField = new JTextField();
		telField.setColumns(10);
		telField.setBounds(198, 102, 114, 20);
		panel.add(telField);
		
		JLabel lblPrenom = new JLabel("Prenom :");
		lblPrenom.setForeground(Color.WHITE);
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPrenom.setBounds(34, 44, 77, 14);
		panel.add(lblPrenom);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email :");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(34, 73, 53, 14);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Tel :");
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(34, 104, 53, 14);
		panel.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Date Naissance :");
		lblNewLabel_1_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1_1.setBounds(34, 135, 124, 14);
		panel.add(lblNewLabel_1_1_1_1);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(198, 133, 114, 20);
		panel.add(dateChooser);
		
		JScrollPane scrollPane = new JScrollPane();
		
		scrollPane.setBounds(10, 164, 788, 230);
		getContentPane().add(scrollPane);
////////////////////////////////////////////////////////	
		table = new JTable();
		table.setForeground(Color.WHITE);
		table.setBackground(Color.DARK_GRAY);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				i=table.getSelectedRow();
				 idB=Long.valueOf(model.getValueAt(table.getSelectedRow(), 0).toString());
				 System.out.println(idB.toString());
				nomField.setText(model.getValueAt(i, 1).toString());
				prenomField.setText(model.getValueAt(i, 2).toString());
				emailField.setText(model.getValueAt(i, 3).toString());
				telField.setText(model.getValueAt(i, 4).toString());	
				
			}
		});
		model=new DefaultTableModel();
		Object[] column = {"Id_User","Nom","Prenom","Email","Tel","Date_Naissance"};
		Object[] row=new Object[0];
		model.setColumnIdentifiers(column);
		table.setModel(model);
		scrollPane.setViewportView(table);
		recharger();
		
		
	}
}
