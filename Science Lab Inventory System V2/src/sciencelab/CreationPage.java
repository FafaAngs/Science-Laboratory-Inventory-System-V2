package sciencelab;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreationPage {
	UserInformation userInformation = new UserInformation(); 
	static HomePage homePage = new HomePage();

	JPanel panel;
	
	
	public void createAndShowGUI() {
		

        JFrame frame = new JFrame("Science Laboratory Inventory System");
     //   frame.setUndecorated(true);
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

        ImageIcon icon = new ImageIcon("CTU.png"); 
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0)); // Add border
        

        JPanel innerPanel3 = new JPanel(); 
        innerPanel3.setLayout(new GridBagLayout());
        innerPanel3.setBackground(new Color(64, 64, 64, 0));
        innerPanel3.setPreferredSize(new Dimension(0, 450)); 

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
      
        Font font = new Font("Serif", Font.BOLD, 24);
        JLabel headerMainPage = new JLabel("<html><font color='#CCCCCC'>Create your Account</font><br><font color='#CCCCCC'>Cebu Technological University</font></html>");
        headerMainPage.setFont(font);



        headerMainPage.setHorizontalAlignment(SwingConstants.CENTER); // Center the text horizontally

        // GridBagConstraints for POTANGG INA label
        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;  
        gbcHeader.gridy = 0;  
        gbcHeader.gridwidth = 2; 
        gbcHeader.insets = new Insets(-100, 0, 0, 0); 

        innerPanel3.add(headerMainPage, gbcHeader);




        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel itemNameLabel1 = new JLabel("Email Address");
        itemNameLabel1.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel1, gbc);

        gbc.gridx = 1;
        JTextField textFieldEmail = new JTextField(16); 
        innerPanel3.add(textFieldEmail, gbc);//first input
      

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel itemNameLabel2 = new JLabel("What is your ID Number?");
        itemNameLabel2.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel2, gbc);

        gbc.gridx = 1;
        JTextField textFieldIDNumber = new JTextField(16); 
        innerPanel3.add(textFieldIDNumber, gbc);//second input
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        
        JLabel itemNameLabel3 = new JLabel("Birthdate");
        itemNameLabel3.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel3, gbc);
        JTextField textField = new JTextField(16);
        gbc.gridx = 1;
        innerPanel3.add(textField, gbc);//second input
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel itemNameLabel4 = new JLabel("First Name");
        itemNameLabel4.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel4, gbc);
        gbc.gridx = 1;
        JTextField textFieldFirstName = new JTextField(16); 
        innerPanel3.add(textFieldFirstName, gbc);//second input
        
        
        
        
        
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel itemNameLabel5 = new JLabel("Last Name");
        itemNameLabel5.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel5, gbc);
        gbc.gridx = 1;
        JTextField textFieldLastName = new JTextField(16); 
        
        innerPanel3.add(textFieldLastName, gbc);//second input
        
        
    
        
        textField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JDialog calendarDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(panel), true);
                
            
                calendarDialog.setUndecorated(true);
                calendarDialog.setLocationRelativeTo(textField);

                JCalendar calendar = new JCalendar();
                calendar.addPropertyChangeListener("calendar", evt -> {
                    if (evt.getPropertyName().equals("calendar")) {
                        Date selectedDate = calendar.getDate();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        textField.setText(sdf.format(selectedDate));
                    }
                });

                JPanel otherpanel = new JPanel(new BorderLayout());
                otherpanel.add(calendar, BorderLayout.CENTER);

                JButton okButton = new JButton("OK");
                okButton.addActionListener(e1 -> {
                    calendarDialog.dispose(); // Close the dialog when "OK" button is clicked
                });
                otherpanel.add(okButton, BorderLayout.SOUTH);

                calendarDialog.add(otherpanel);

                calendarDialog.pack();
                calendarDialog.setVisible(true);
            }
        });


        GridBagConstraints gbcSignInButton = new GridBagConstraints();
        gbcSignInButton.gridx = 1;
        gbcSignInButton.gridy = 6;
        gbcSignInButton.gridwidth = 1;
        gbcSignInButton.insets = new Insets(10, 90, 0, -10);

        JButton signUpButton = new JButton("SIGN UP");
        Color skyBlue = new Color(135, 206, 235);
        signUpButton.setBackground(skyBlue.darker());
        signUpButton.setForeground(Color.WHITE); 
        innerPanel3.add(signUpButton, gbcSignInButton);

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
             
                String inputtedEmail = textFieldEmail.getText();
                String inputtedIDNumber =textFieldIDNumber.getText(); 
                String inputtedBirthday = textField.getText(); 
                String inputtedFirstName = textFieldFirstName.getText(); 
                String inputtedLastName = textFieldLastName.getText(); 
                userInformation.loadFromJson(); // read *******************
                
            
               userInformation.createAccount(inputtedEmail, inputtedIDNumber, inputtedBirthday, inputtedFirstName, inputtedLastName,panel); 
               
               
               
               userInformation.saveToJson();
               
             
                
               
            }
        });

        

  
    
        GridBagConstraints gbcSignUpLabel = new GridBagConstraints();
        gbcSignUpLabel.gridx = 1;
        gbcSignUpLabel.gridy = 6;
        gbcSignUpLabel.gridwidth = 1;
        gbcSignUpLabel.insets = new Insets(10, -200, 0, 100);

        JLabel signUpLabel = new JLabel("Already have an Account?");
        signUpLabel.setForeground(Color.WHITE);
        innerPanel3.add(signUpLabel, gbcSignUpLabel);

        
        GridBagConstraints gbcSignUpLinkLabel = new GridBagConstraints();
        gbcSignUpLinkLabel.gridx = 1;
        gbcSignUpLinkLabel.gridy = 6;
        gbcSignUpLinkLabel.gridwidth = 1;
        gbcSignUpLinkLabel.insets = new Insets(10, -200, 0, -110); 

        JLabel signUpLinkLabel = new JLabel("Click here");
        signUpLinkLabel.setForeground(skyBlue.brighter());
        signUpLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLinkLabel.addMouseListener(new MouseAdapter() {
       	 public void mouseClicked(MouseEvent e) {
              
              
                   HomePage homePage = new HomePage();
                   homePage.ShowGUI();
               
                   JFrame CreationPageFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                   CreationPageFrame.dispose(); 
 	 
       	 }
               
       });
        
        
        JPanel panelForDesign = new JPanel(new BorderLayout());
        panelForDesign.setBackground(new Color(64, 64, 64, 0)); // 

        panelForDesign.setOpaque(true);
        panelForDesign.setPreferredSize(new Dimension(700, 0));
        
        

        innerPanel3.add(signUpLinkLabel, gbcSignUpLinkLabel);
        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/resources/BagongMukha.png"));
        JLabel imageLabelLogo = new JLabel(iconLogo);
        imageLabelLogo.setBorder(BorderFactory.createEmptyBorder(-30, 0, 450, 60)); // Add border
        panelForDesign.add(imageLabelLogo,BorderLayout.CENTER);
        innerPanel.add(panelForDesign,BorderLayout.CENTER);



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

