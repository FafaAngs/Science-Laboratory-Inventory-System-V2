package sciencelab;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;



public class HomePage {
    static JPanel panel;
    UserInformation userInformation = new UserInformation();
 
    static JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
    static JButton backButton = new JButton("BACK");
    static FirstHomePage firstHomePage = new FirstHomePage();

   

    public void ShowGUI() {
        JFrame frame = new JFrame("Science Laboratory Inventory System");
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
        label.setBorder(BorderFactory.createEmptyBorder(10, 200, 10, 60));
        label.setForeground(Color.black.brighter());
        label.setFont(new Font("Serif", Font.BOLD, 36));
        label.setBackground(Color.orange.darker());
        label.setOpaque(true);
        label.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.add(label, BorderLayout.CENTER);
        headerPanel.add(backButton, BorderLayout.EAST);
        headerPanel.setBackground(label.getBackground()); 

        backButton.setPreferredSize(new Dimension(120, 10));
        backButton.setMaximumSize(new Dimension(120, 10));
        backButton.setBackground(Color.orange);
        backButton.setFont(new Font("Roboto", Font.BOLD, 12));

        backButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                firstHomePage.StartFirstHomePage();
                frame.dispose();
            }
        });

    
        

        JPanel innerPanel = new JPanel();
        innerPanel.setLayout(new BorderLayout());
        innerPanel.setBackground(new Color(64, 64, 64, 128).darker());

        JPanel innerPanel2 = new JPanel();
        innerPanel2.setLayout(new BorderLayout());
        innerPanel2.setBackground(new Color(64, 64, 64, 128));
        innerPanel2.setPreferredSize(new Dimension(500, 200));

        ImageIcon icon = new ImageIcon("CTU.png");
        JLabel imageLabel = new JLabel(icon);
        imageLabel.setBorder(BorderFactory.createEmptyBorder(30, 0, 0, 0));

        JPanel innerPanel3 = new JPanel();
        innerPanel3.setLayout(new GridBagLayout());
        innerPanel3.setBackground(new Color(64, 64, 64, 0));
        innerPanel3.setPreferredSize(new Dimension(0, 450));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;

        Font font = new Font("Serif", Font.BOLD, 24);
        JLabel headerMainPage = new JLabel("<html><font color='#CCCCCC'>Sign in<br>Cebu Technological University</font></html>");
        headerMainPage.setFont(font);
        headerMainPage.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbcHeader = new GridBagConstraints();
        gbcHeader.gridx = 0;
        gbcHeader.gridy = 0;
        gbcHeader.gridwidth = 2;
        gbcHeader.insets = new Insets(-200, 0, 0, 0);

        innerPanel3.add(headerMainPage, gbcHeader);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel itemNameLabel1 = new JLabel("Email Address");
        itemNameLabel1.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel1, gbc);

        gbc.gridx = 1;

        JTextField textFieldEmail = new JTextField(16);
        innerPanel3.add(textFieldEmail, gbc); // 1st input

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel itemNameLabel2 = new JLabel("Password");
        itemNameLabel2.setForeground(Color.WHITE);
        innerPanel3.add(itemNameLabel2, gbc);

        gbc.gridx = 1;
        JPasswordField textFieldPassword = new JPasswordField(16);
        innerPanel3.add(textFieldPassword, gbc);

        GridBagConstraints gbcSignInButton = new GridBagConstraints();
        gbcSignInButton.gridx = 1;
        gbcSignInButton.gridy = 5;
        gbcSignInButton.gridwidth = 1;
        gbcSignInButton.insets = new Insets(40, 90, 0, 0);

        Color skyBlue = new Color(135, 206, 235);
        JButton signInButton = new JButton("SIGN IN");
        signInButton.setBackground(skyBlue.darker());
        signInButton.setForeground(Color.WHITE);
        innerPanel3.add(signInButton, gbcSignInButton);

        signInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               
                    String inputtedEmail = textFieldEmail.getText();
                    String inputtedPassword = new String(textFieldPassword.getPassword());
                    userInformation.loadFromJson();
                    userInformation.loginAccount(inputtedEmail, inputtedPassword, panel);
               
            }
        });

        GridBagConstraints gbcForgotPasswordLabel = new GridBagConstraints();
        gbcForgotPasswordLabel.gridx = 1;
        gbcForgotPasswordLabel.gridy = 4;
        gbcForgotPasswordLabel.gridwidth = 1;
       
        gbcForgotPasswordLabel.insets = new Insets(0, 60 ,0, 0);

        JLabel forgotPasswordLabel = new JLabel("<html>Forgot Password?</html>");
        skyBlue = new Color(135, 206, 235);
        forgotPasswordLabel.setForeground(skyBlue.brighter());
        innerPanel3.add(forgotPasswordLabel, gbcForgotPasswordLabel);

        forgotPasswordLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
           
                    RecoveryPage recoveryPage = new RecoveryPage();
                    recoveryPage.StartRecovery();
                    JFrame loginHomePage = (JFrame) SwingUtilities.getWindowAncestor(panel);
                    loginHomePage.dispose();
               
            }

        });

        GridBagConstraints gbcSignUpLabel = new GridBagConstraints();
        gbcSignUpLabel.gridx = 1;
        gbcSignUpLabel.gridy = 5;
        gbcSignUpLabel.gridwidth = 1;
        gbcSignUpLabel.insets = new Insets(40, -200, 0, 100);

        JLabel signUpLabel = new JLabel("Don't have an account?");
        signUpLabel.setForeground(Color.WHITE);
        innerPanel3.add(signUpLabel, gbcSignUpLabel);

        GridBagConstraints gbcSignUpLinkLabel = new GridBagConstraints();
        gbcSignUpLinkLabel.gridx = 1;
        gbcSignUpLinkLabel.gridy = 5;
        gbcSignUpLinkLabel.gridwidth = 1;
        gbcSignUpLinkLabel.insets = new Insets(40, -200, 0, -110);

        JLabel signUpLinkLabel = new JLabel("Sign up Here");
        signUpLinkLabel.setForeground(skyBlue.brighter());
        signUpLinkLabel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpLinkLabel.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                
                    CreationPage creationPage = new CreationPage();
                    creationPage.createAndShowGUI();
                    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                    topFrame.dispose();
                

            }
        });

        JPanel panelForDesign = new JPanel(new BorderLayout());
        panelForDesign.setBackground(new Color(64, 64, 64, 0));
        panelForDesign.setOpaque(true);
        panelForDesign.setPreferredSize(new Dimension(700, 0));

        innerPanel3.add(signUpLinkLabel, gbcSignUpLinkLabel);

        ImageIcon iconLogo = new ImageIcon(getClass().getResource("/resources/BagongMukha.png"));
        JLabel imageLabelLogo = new JLabel(iconLogo);
        imageLabelLogo.setBorder(BorderFactory.createEmptyBorder(100, 0, 450, 60));

        
        
        buttonPanel.setBackground(new Color(0,0,0,0));
    
        panelForDesign.add(buttonPanel, BorderLayout.SOUTH);

        panelForDesign.add(imageLabelLogo, BorderLayout.NORTH);
        innerPanel.add(panelForDesign, BorderLayout.CENTER);

        innerPanel2.add(innerPanel3, BorderLayout.SOUTH);
        innerPanel2.add(imageLabel, BorderLayout.NORTH);
        innerPanel.add(innerPanel2, BorderLayout.WEST);

        panel.add(headerPanel, BorderLayout.NORTH);
        panel.add(innerPanel);

        frame.add(panel);
        frame.setVisible(true);
        frame.setResizable(false);
    }

 
}
