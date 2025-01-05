package sciencelab;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

public class RecoveryPage {
	
	JPanel panel;
	UserInformation userInformation = new UserInformation();
	
	public void StartRecovery() {
        JFrame frame = new JFrame("Science Laboratory Inventory System");
       frame.setUndecorated(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1400, 800);
        frame.setLocationRelativeTo(null);

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/sciencelab7.jpg"));
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

         panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(scaledImageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        }; 
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("SCIENCE LABORATORY INVENTORY SYSTEM");
        label.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 60)); // Add border
        label.setForeground(Color.black.brighter());
        label.setFont(new Font("Serif", Font.BOLD, 36));
        label.setBackground(Color.orange.darker()); 
        label.setOpaque(true); 

        JPanel innerPanel = new JPanel();  
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBackground(new Color(64, 64, 64, 128).darker()); 

        JPanel innerPanel2 = new JPanel(); 
        innerPanel2.setLayout(new BorderLayout());
        innerPanel2.setBackground(new Color(64, 64, 64, 128));
        innerPanel2.setPreferredSize(new Dimension(500, 200)); 
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/CTU.png"));
    
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Add border
        

        JPanel innerPanel3 = new JPanel(); 
        innerPanel3.setLayout(new GridBagLayout());
        innerPanel3.setBackground(new Color(64, 64, 64, 0));
        innerPanel3.setPreferredSize(new Dimension(0, 450)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
      
        Font font = new Font("Serif", Font.BOLD, 24);
        JLabel headerMainPage = new JLabel("<html><font color='#CCCCCC'>Account Recovery<br>Cebu Technological University</font><br></font></html>");
        headerMainPage.setFont(font);

        headerMainPage.setHorizontalAlignment(SwingConstants.CENTER); // Center ang text horizontally

        // GridBagConstraints for POTANGG INA label
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;  
        gbcHeader.gridy = 0;  
        gbcHeader.gridwidth = 2; 
        gbcHeader.insets = new Insets(-100, 0, 0, 0); 

        innerPanel3.add(headerMainPage, gbcHeader);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 50, 10, 10);
        
        JLabel itemNameLabel1 = new JLabel("Enter your ID Number");
        itemNameLabel1.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel1, gbc);

        gbc.gridx = 1;
        JTextField textFieldIDNumber = new JTextField(16); 
        innerPanel3.add(textFieldIDNumber, gbc);//first input
        

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 50, 10, 10);

        JLabel itemNameLabel2 = new JLabel("Enter your Last Name");
        itemNameLabel2.setForeground(Color.WHITE);
        JTextField textLastName = new JTextField(16); 
        
        innerPanel3.add(itemNameLabel2, gbc);

        gbc.gridx = 1;
        innerPanel3.add(textLastName, gbc);//second input
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 50, 10, 10);
        
        JLabel itemNameLabel3 = new JLabel("Enter your Birthdate");
        itemNameLabel3.setForeground(Color.WHITE);
        JTextField textFieldBirthday = new JTextField(16); 
        
        innerPanel3.add(itemNameLabel3, gbc);
       
        gbc.gridx = 1;
        innerPanel3.add(textFieldBirthday, gbc);//3rd input
        
    
        
        textFieldBirthday.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog calendarDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(panel), true);
                
            
                calendarDialog.setUndecorated(true);
                calendarDialog.setLocationRelativeTo(textFieldBirthday);

                JCalendar calendar = new JCalendar();
                calendar.addPropertyChangeListener("calendar", evt -> {
                    if (evt.getPropertyName().equals("calendar")) {
                        Date selectedDate = calendar.getDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        textFieldBirthday.setText(sdf.format(selectedDate));
                    }
                });

                JPanel otherpanel = new JPanel(new BorderLayout());
                otherpanel.add(calendar, BorderLayout.CENTER);

                JButton okButton = new JButton("OK");
                okButton.addActionListener(e1 -> {
                    calendarDialog.dispose();
                });
                otherpanel.add(okButton, BorderLayout.SOUTH);

                calendarDialog.add(otherpanel);

                calendarDialog.pack();
                calendarDialog.setVisible(true);
            }
        });

        GridBagConstraints gbcSignInButton = new GridBagConstraints();
        gbcSignInButton.gridx = 1;
        gbcSignInButton.gridy = 5;
        gbcSignInButton.gridwidth = 1;
        gbcSignInButton.insets = new Insets(40, 85, 0, 20);

        JButton signUpButton = new JButton("RECOVER PASSWORD");
        Color skyBlue = new Color(135, 206, 235);
        signUpButton.setBackground(skyBlue.darker());
        signUpButton.setForeground(Color.WHITE);
        innerPanel3.add(signUpButton, gbcSignInButton);

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
      	
            	userInformation.loadFromJson();

            	String idNumberInput = textFieldIDNumber.getText();
            	String textLastNameInput = textLastName.getText();
                String textBirthdayInput = textFieldBirthday.getText();

            	int index = userInformation.IDNumber.indexOf(idNumberInput);           
            	if (index != -1) {
               
                    if (userInformation.LastName.get(index).equals(textLastNameInput.toLowerCase()) && userInformation.Birthdate.get(index).equals(textBirthdayInput)) {
                   
                        JOptionPane.showMessageDialog(null,"User found with ID: " + idNumberInput+"\nYour Password is : "+userInformation.Password.get(index), "User Found", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        
                        JOptionPane.showMessageDialog(null, "Last name or birthdate does not match for ID: " + idNumberInput, "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                 
                    JOptionPane.showMessageDialog(null,"User with ID " + idNumberInput + " not found.", "User Not Found", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        
        GridBagConstraints gbcSignUpLabel = new GridBagConstraints();
        gbcSignUpLabel.gridx = 1;
        gbcSignUpLabel.gridy = 5;
        gbcSignUpLabel.gridwidth = 1;
        gbcSignUpLabel.insets = new Insets(40, -200, 0, 200);

        JLabel signUpLabel = new JLabel("Back to login page");
        signUpLabel.setForeground(Color.WHITE);
        innerPanel3.add(signUpLabel, gbcSignUpLabel);

        
        GridBagConstraints gbcSignUpLinkLabel = new GridBagConstraints();
        gbcSignUpLinkLabel.gridx = 1;
        gbcSignUpLinkLabel.gridy = 5;
        gbcSignUpLinkLabel.gridwidth = 1;
        gbcSignUpLinkLabel.insets = new Insets(40, -200, 0,  30); 

        JLabel signUpLinkLabel = new JLabel("Click here");
        signUpLinkLabel.setForeground(skyBlue.brighter());
        signUpLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        signUpLinkLabel.addMouseListener(new MouseAdapter() {//mouse click function perform
       	 public void mouseClicked(MouseEvent e) {
                       
                   HomePage homePage = new HomePage();
                   homePage.ShowGUI();
               
                   JFrame CreationPageFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                   CreationPageFrame.dispose();
               
                   
       		 
       	 }
               
       });
        
        JPanel panelForDesign = new JPanel(new BorderLayout());
        panelForDesign.setBackground(new Color(64, 64, 64, 0)); 
        panelForDesign.setOpaque(true);
        panelForDesign.setPreferredSize(new Dimension(700, 0));

        innerPanel3.add(signUpLinkLabel, gbcSignUpLinkLabel);
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/resources/BagongMukha.png")); 
        JLabel imageLabelLogo = new JLabel(iconLogo);
        imageLabelLogo.setBorder(BorderFactory.createEmptyBorder(-30, 0, 450, 60)); // Add border
        panelForDesign.add(imageLabelLogo,BorderLayout.NORTH);
        innerPanel.add(panelForDesign,BorderLayout.EAST);



        innerPanel2.add(innerPanel3,BorderLayout.SOUTH);
        innerPanel2.add(imageLabel,BorderLayout.NORTH);
        innerPanel.add(innerPanel2,BorderLayout.WEST);
        label.setHorizontalAlignment(SwingConstants.CENTER); 
        panel.add(label, BorderLayout.NORTH);
        panel.add(innerPanel);
        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }
	 public JPanel getPanel() {
	        return panel;
	    }
}
