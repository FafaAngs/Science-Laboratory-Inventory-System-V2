package sciencelab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class FirstHomePage {

	
	
	
	JPanel panel;
	static HomePage homePage = new HomePage();
	static AdminPanel adminPanel = new AdminPanel();
	
	
	
	JFrame frame = new JFrame("Science Laboratory Inventory System");
	public void StartFirstHomePage() {
		
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.setSize(1400, 800);
	        frame.setLocationRelativeTo(null);

	        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/sciencelab7.jpg"));
	        Image image = imageIcon.getImage();
	        Image scaledImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
	        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

	         panel = new JPanel() {
	      
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(scaledImageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
	            }
	        }; 
	        
	        panel.setLayout(new BorderLayout());
	        JLabel label = new JLabel("SCIENCE LABORATORY INVENTORY SYSTEM");
	        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 60)); 
	        label.setForeground(Color.black.brighter());
	        label.setFont(new Font("Serif", Font.BOLD, 36));
	        label.setBackground(Color.orange.darker()); 
	        label.setOpaque(true); 
	        
	        
	       
	        

	    

	        JPanel leftSidePanel = new JPanel(); 
	        leftSidePanel.setLayout(new BorderLayout());
	        leftSidePanel.setBackground(new Color(64, 64, 64, 128).darker().darker().darker().darker());
	        leftSidePanel.setPreferredSize(new Dimension(700, 200)); 

	        ImageIcon icon = new ImageIcon("CTU.png"); 
	        JLabel imageLabel = new JLabel(icon);
	        imageLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));
	        

	        
	        
	        JPanel leftsideinside = new JPanel();
	        leftsideinside.setLayout(new BoxLayout(leftsideinside, BoxLayout.Y_AXIS));
	        leftsideinside.setBackground(new Color(64, 64, 64, 0)); 

	       
	        leftsideinside.add(Box.createVerticalGlue());
	        Font customFont = new Font("Arial", Font.BOLD, 24);
	        
	        
	        JPanel rightSidePanel = new JPanel(); 
	        rightSidePanel.setLayout(new BorderLayout());
	        rightSidePanel.setBackground(new Color(64, 64, 64, 128));
	        rightSidePanel.setPreferredSize(new Dimension(700, 200)); 

	        JButton button1 = new JButton("ADMIN");
	        button1.setAlignmentX(JButton.CENTER_ALIGNMENT); 
	        button1.setMaximumSize(new Dimension(200, 5000)); 
	        button1.setFont(customFont); 
	        button1.setBackground(Color.orange);   
	        button1.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	
	            	
	            	showLoginDialog();
	            	
	            	
	            }
	        });
	        
	        JButton button2 = new JButton("STUDENT");
	        button2.setAlignmentX(JButton.CENTER_ALIGNMENT); 
	        button2.setMaximumSize(new Dimension(200, 5000)); 
	        button2.setFont(customFont); 
	        button2.setBackground(Color.orange); 
	        button2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                homePage.ShowGUI();
	                frame.dispose();
	                
	            }
	        });

	        
	        
	        leftsideinside.add(button1);
	        leftsideinside.add(Box.createVerticalStrut(50)); 
	        leftsideinside.add(button2);

	        
	        leftsideinside.add(Box.createVerticalGlue());

            leftSidePanel.add(leftsideinside,BorderLayout.CENTER);
	        leftSidePanel.add(imageLabel,BorderLayout.NORTH);
	        
	        
	        ImageIcon imageIcon1 = new ImageIcon(getClass().getResource("/resources/CTU2.png"));
	
	        JLabel imageLabel1 = new JLabel(imageIcon1);

	        rightSidePanel.add(imageLabel1,BorderLayout.CENTER);
	 
	        
	    
	        
	        
	        
	        
	        
	        
	        
	        panel.add(rightSidePanel,BorderLayout.CENTER);
	        panel.add(leftSidePanel,BorderLayout.WEST);
	        label.setHorizontalAlignment(SwingConstants.CENTER); 
	        panel.add(label, BorderLayout.NORTH);
	        frame.add(panel);
	        frame.setVisible(true);
	        frame.setResizable(false);
	}
	private void showLoginDialog() {
	    JDialog loginDialog = new JDialog((Frame) null, "Login", true);
	    loginDialog.setUndecorated(true);
	    loginDialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

	    JPanel loginPanel = new JPanel(new GridBagLayout());
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbc.insets = new Insets(5, 5, 5, 5);
	    gbc.fill = GridBagConstraints.HORIZONTAL;

	    JLabel usernameLabel = new JLabel("Username:");
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    loginPanel.add(usernameLabel, gbc);

	    JTextField usernameField = new JTextField(15);
	    gbc.gridx = 1;
	    gbc.gridy = 0;
	    loginPanel.add(usernameField, gbc);

	    JLabel passwordLabel = new JLabel("Password:");
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    loginPanel.add(passwordLabel, gbc);

	    JPasswordField passwordField = new JPasswordField(15);
	    gbc.gridx = 1;
	    gbc.gridy = 1;
	    loginPanel.add(passwordField, gbc);

	    JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");
	    gbc.gridx = 1;
	    gbc.gridy = 2;
	    loginPanel.add(showPasswordCheckBox, gbc);

	    showPasswordCheckBox.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	            if (e.getStateChange() == ItemEvent.SELECTED) {
	                passwordField.setEchoChar((char) 0);
	            } else {
	                passwordField.setEchoChar('•');
	            }
	        }
	    });

	    JButton okButton = new JButton("OK");
	    okButton.addActionListener(e -> {
	        String username = usernameField.getText();
	        String password = new String(passwordField.getPassword());

	        if (validateLogin(username, password)) {
	            JOptionPane.showMessageDialog(null, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
	            adminPanel.StartAdminPanel();
	            frame.dispose();
	            loginDialog.dispose();
	        } else {
	            JOptionPane.showMessageDialog(null, "Invalid username or password.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
	    });

	    JButton cancelButton = new JButton("Cancel");
	    cancelButton.addActionListener(e -> loginDialog.dispose());

	    JPanel buttonPanel = new JPanel();
	    buttonPanel.add(okButton);
	    buttonPanel.add(cancelButton);

	    gbc.gridx = 0;
	    gbc.gridy = 3;
	    gbc.gridwidth = 2;
	    loginPanel.add(buttonPanel, gbc);

	    loginDialog.add(loginPanel);
	    loginDialog.pack();
	    loginDialog.setLocationRelativeTo(null);
	    loginPanel.setBackground(Color.orange);
	    buttonPanel.setBackground(Color.orange);
	    showPasswordCheckBox.setBackground(Color.orange);
	    

	    loginDialog.addWindowListener(new WindowAdapter() {
	   
	        public void windowClosing(WindowEvent e) {
	      
	        }
	    });

	    loginDialog.setVisible(true);
	}

	    private boolean validateLogin(String username, String password) {
	 
	        return "admin".equalsIgnoreCase(username) && "admin".equalsIgnoreCase(password);
	    }
	
	
	
}
