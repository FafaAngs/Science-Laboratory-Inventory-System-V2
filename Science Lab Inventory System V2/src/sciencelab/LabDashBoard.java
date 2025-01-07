package sciencelab;
import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.*;

import java.util.List;
import java.util.ArrayList;
public class LabDashBoard {
    
    public static int userIndex;
    InventoryPage inventoryPage = new InventoryPage();
    UserInformation userInformation = new UserInformation();
    JLabel userWelcome = new JLabel();
    JLabel userHistory = new JLabel();
    JLabel userLiveTime = new JLabel();
     HomePage homePage = new HomePage();
     AdminPanel adminPanel = new AdminPanel();
     static ScienceLabItems scienceLabItems = new ScienceLabItems();
    
     public void StartDashBoard() {
    	 
             userInformation.loadFromJson();
    	 
    	    JFrame frame = new JFrame("Science Laboratory Inventory System");
    	    
    	    JPanel panel = new JPanel();
    	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	    frame.setSize(1400, 800);
    	    frame.setLocationRelativeTo(null);
    	    
    	    panel.setLayout(new BorderLayout());
    	    
    	    JLabel label = new JLabel("SCIENCE LABORATORY INVENTORY SYSTEM");
    	    label.setBorder(BorderFactory.createEmptyBorder(10, 200, 0, 60)); // Add border
    	    label.setForeground(Color.black.brighter());
    	    label.setFont(new Font("Serif", Font.BOLD, 36));
    	    label.setBackground(Color.orange.darker());
    	    label.setOpaque(true); 
    	    label.setHorizontalAlignment(SwingConstants.CENTER);
    	    
    	    
    	    
    	    JButton logout = new JButton("LOGOUT");
    	    Font buttonFont = new Font("Roboto", Font.BOLD, 18);
    	    logout.setForeground(Color.black.brighter());
    	    logout.setFont(buttonFont);
    	    logout.setBackground(Color.orange);
    	    
    	    logout.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
    	            UserInformation.firstTimeLogin = false;
    	            homePage.ShowGUI();
    	            topFrame.dispose();
    	        }
    	    });
    	    
    	    JPanel titlePanel = new JPanel(new BorderLayout());
    	    titlePanel.setBackground(label.getBackground());
    	    titlePanel.add(logout, BorderLayout.EAST);
    	    titlePanel.add(label, BorderLayout.CENTER);
    	    
    	    ImageIcon imageIcon = new ImageIcon(getClass().getResource("/resources/xctug.jpg"));

    	  
    	    Image image = imageIcon.getImage();
    	    Image scaledImage = image.getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
    	    ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
    	    JPanel innerPanel = new JPanel() {
    	        @Override
    	        protected void paintComponent(Graphics g) {
    	            super.paintComponent(g);
    	            g.drawImage(scaledImageIcon.getImage(), 0, 0, getWidth(), getHeight(), this);
    	        }
    	    };
    	    
    	    innerPanel.setLayout(new BorderLayout());

    	    Timer timer = new Timer(1000, new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            updateTimeLabel(userLiveTime);
    	        }
    	    });
    	    timer.start();
    	    
    	    Font robotoFont = new Font("Serif", Font.BOLD, 20);    
    	    
    	    userWelcome.setBorder(BorderFactory.createEmptyBorder(100, 20, 0, 0)); // Add border 
    	    userWelcome.setFont(robotoFont);
    	    userWelcome.setForeground(Color.WHITE.brighter().brighter().brighter());
    	    
    	    userHistory.setBorder(BorderFactory.createEmptyBorder(100, 50, 0, 0)); // Add border 
    	    userHistory.setFont(robotoFont);
    	    userHistory.setForeground(Color.WHITE.brighter().brighter().brighter());

    	    userLiveTime.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0)); // Add border 
    	    userLiveTime.setFont(robotoFont);
    	    userLiveTime.setOpaque(true); 
    	    userLiveTime.setPreferredSize(new Dimension(100, 100));
    	    userLiveTime.setBackground(Color.orange.darker());
    	    
    	    
    	    
    	    
    	    JLabel containerLabel = new JLabel();
            containerLabel.setLayout(new FlowLayout()); 
            containerLabel.setPreferredSize(new Dimension(200, 50)); 

            JButton changePasswordButton = new JButton("Change Password");
            Font changePassButtonFont = new Font("Roboto", Font.BOLD, 12);
            changePasswordButton.setForeground(Color.black.brighter());
            changePasswordButton.setFont(changePassButtonFont);
            changePasswordButton.setPreferredSize(new Dimension(150, 50)); 
            changePasswordButton.setBackground(Color.orange);

            changePasswordButton.addActionListener(e -> {
       
                JPasswordField oldPassword = new JPasswordField();
                JPasswordField newPassword = new JPasswordField();

           
                JCheckBox showPasswordCheckBox = new JCheckBox("Show Password");

              
                showPasswordCheckBox.addActionListener(event -> {
                    if (showPasswordCheckBox.isSelected()) {
                 
                        oldPassword.setEchoChar((char) 0);
                        newPassword.setEchoChar((char) 0);
                    } else {
              
                        oldPassword.setEchoChar('*');
                        newPassword.setEchoChar('*');
                    }
                });

        
                Object[] fields = {
                    "Old Password:", oldPassword, 
                    "New Password:", newPassword,
                    showPasswordCheckBox
                };

               
                ImageIcon customIcon =new ImageIcon(getClass().getResource("/resources/changepassimg.png"));
                Image image1 = customIcon.getImage();
                Image resizedImage = image1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                customIcon = new ImageIcon(resizedImage);


       
                if (JOptionPane.showConfirmDialog(null, fields, "Change Password", JOptionPane.OK_CANCEL_OPTION, 
                    JOptionPane.PLAIN_MESSAGE, customIcon) == JOptionPane.OK_OPTION) {
                	
                	
                	String oldPass = new String(oldPassword.getPassword());
                	String newPass = new String(newPassword.getPassword());

                	String currentUserPass = userInformation.Password.get(userIndex);
                	
                	if (oldPass.isEmpty() || newPass.isEmpty()) {
                
                	    Toolkit.getDefaultToolkit().beep();
                	    
                	    JOptionPane.showMessageDialog(null, "Both fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                	} else if (oldPass.equals(currentUserPass)) {
                
                	    userInformation.Password.set(userIndex, newPass);
                	    userInformation.saveToJson();
                	 
                	    JOptionPane.showMessageDialog(null, "Password changed successfully.", "Confirmation", JOptionPane.INFORMATION_MESSAGE);

                	  
                	    userInformation.loadFromJson();
                	    String user = userInformation.Email.get(userIndex);  
                	    String userPass = userInformation.Password.get(userIndex);

                	    
                	    userInformation.loginAccount(user, userPass, null);
                	    

                	
                	    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor((JComponent)e.getSource());
                	    topFrame.dispose();

       
                	} else {
                	  
                	    Toolkit.getDefaultToolkit().beep();
                	    
                	    
                	    JOptionPane.showMessageDialog(null, "The old password is incorrect. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                	}
                }
            });
            
            


    	    containerLabel.add(changePasswordButton);


    	    
    	    
    	    
    	    
    	    JButton returnitems = new JButton("Return Items");
    	    returnitems.setForeground(Color.black.brighter());
    	    returnitems.setFont(buttonFont);
    	    returnitems.setBackground(Color.orange);

    	    returnitems.addActionListener(new ActionListener() {
    	        @Override
    	        public void actionPerformed(ActionEvent e) {
    	            JDialog returnDialog = new JDialog((JFrame) null, "Return Items Window", true);
    	            returnDialog.setSize(400, 300); 
    	            returnDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); 
    	            returnDialog.setLocationRelativeTo(null); 

    	      
    	            JPanel panel = new JPanel();
    	            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    	   
    	            InventoryItem userInventory = userInformation.inventoryList.get(userIndex);


    	            List<String> listnamesList = new ArrayList<>();
    	            for (int j = 0; j < userInventory.names.length; j++) {
    	                int borrowedQuantity = userInventory.quantities[j];
    	                if (borrowedQuantity > 0) {
    	                    String listItem = userInventory.names[j] + " (Borrowed: " + borrowedQuantity + ")";
    	                    listnamesList.add(listItem);
    	                }
    	            }

    	            String[] listnames = listnamesList.toArray(new String[0]); 
    	            JList<String> borrowedItemsList = new JList<>(listnames);
    	            borrowedItemsList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
    	            panel.add(new JScrollPane(borrowedItemsList));

    	            panel.add(new JLabel("Enter Quantity to Return:"));
    	            JTextField quantityField = new JTextField(5); 
    	            quantityField.setMaximumSize(new Dimension(200, 25)); 
    	            panel.add(quantityField);

    	            // Return Button
    	            JButton returnButton = new JButton("Return Selected Item");
    	            returnButton.addActionListener(new ActionListener() {
    	                @Override
    	                public void actionPerformed(ActionEvent e) {
    	                    String selectedItem = borrowedItemsList.getSelectedValue();
    	                    String quantityStr = quantityField.getText();

    	                    if (selectedItem != null && !quantityStr.isEmpty()) {
    	                        try {
    	                            String[] parts = selectedItem.split(" \\(");
    	                            String itemName = parts[0]; 
    	                            String quantityPart = parts[1].replaceAll("[^0-9]", ""); 
    	                            int borrowedCount = Integer.parseInt(quantityPart);

    	                            int quantity = Integer.parseInt(quantityStr); 
    	                            if (quantity > 0) {
    	                                
    	                                for (int i = 0; i < userInventory.names.length; i++) {
    	                                    if (userInventory.names[i].equals(itemName)) {
    	                                        if (quantity > borrowedCount) {
    	                                            JOptionPane.showMessageDialog(returnDialog, 
    	                                                "You are trying to return more than you borrowed. Returning only " + borrowedCount + " items.", 
    	                                                "Warning", 
    	                                                JOptionPane.WARNING_MESSAGE);
    	                                            quantity = borrowedCount;
    	                                        }

    	                                   
    	                                        userInventory.quantities[i] -= quantity;
    	                                        
    	                                     // Loop the materials
    	                                        for (int j = 0; j < scienceLabItems.materials.length; j++) {
    	                                            if (inventoryPage.materialNames.length > j && inventoryPage.materialNames[j] != null && inventoryPage.materialNames[j].equals(itemName)) {
    	                                            	scienceLabItems.loadFromJson();
    	                                                
    	                                            
    	                                                
    	                                                scienceLabItems.materials[j] += quantity;

    	                                               
    	                                            }
    	                                        }

    	                                        // Loop  the equipment
    	                                        for (int k = 0; k < scienceLabItems.equipments.length; k++) {
    	                                            if (inventoryPage.equipmentNames.length > k && inventoryPage.equipmentNames[k] != null && inventoryPage.equipmentNames[k].equals(itemName)) {
    	                                            	scienceLabItems.loadFromJson();
    	                                             
    	                                                scienceLabItems.equipments[k] += quantity;

    	                                            }
    	                                        }
    	                                        
    	                                        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy - h:mm:ss a");
    	                                	    String formattedDateTime = dateFormat.format(new Date());

    	                                        String[] newReturnData ={userInformation.FirstName.get(userIndex).toUpperCase()+" "+userInformation.LastName.get(userIndex).toUpperCase(),userInformation.IDNumber.get(userIndex), itemName,"-"+Integer.toString(quantity),formattedDateTime};
    	                                        
    	                                        adminPanel.addDataReturn(newReturnData);
    	                                        adminPanel.saveReturnDataToJson();
    	                                        
    	                                        scienceLabItems.saveToJson();
    	                                    
    	                                        userInformation.saveToJson();

    	                                        // Refresh the borrowed items list
    	                                        listnamesList.clear(); 
    	                                        for (int j = 0; j < userInventory.names.length; j++) {
    	                                            int updatedBorrowedQuantity = userInventory.quantities[j];
    	                                            if (updatedBorrowedQuantity > 0) {
    	                                                listnamesList.add(userInventory.names[j] + " (Borrowed: " + updatedBorrowedQuantity + ")");
    	                                            }
    	                                        }
    	                                        borrowedItemsList.setListData(listnamesList.toArray(new String[0])); //refresh
    	                                        JOptionPane.showMessageDialog(returnDialog, "Item returned successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    	                                        quantityField.setText("");  // <-- This clears the text field
    	                                     // Check if no items are left to return, and close the dialog if empty
    	                                        if (listnamesList.isEmpty()) {
    	                                           
    	                                            returnDialog.dispose();  // Close the dialog if there are no items left to return
    	                                        }
    	                                        break;
    	                                    }
    	                                }
    	                            } else {
    	                                JOptionPane.showMessageDialog(returnDialog, "Please enter a valid quantity.", "Error", JOptionPane.ERROR_MESSAGE);
    	                            }
    	                        } catch (NumberFormatException ex) {
    	                            JOptionPane.showMessageDialog(returnDialog, "Please enter a valid number for quantity.", "Error", JOptionPane.ERROR_MESSAGE);
    	                        }
    	                    } else {
    	                        JOptionPane.showMessageDialog(returnDialog, "Please select an item and enter a quantity to return.", "Error", JOptionPane.ERROR_MESSAGE);
    	                    }
    	                }
    	            });
    	            panel.add(returnButton);

    	   
    	            JButton closeButton = new JButton("Close");
    	            closeButton.addActionListener(new ActionListener() {
    	                @Override
    	                public void actionPerformed(ActionEvent e) {
    	                    returnDialog.dispose();
    	                }
    	            });
    	            panel.add(closeButton);

    	            returnDialog.add(panel);
    	            returnDialog.setVisible(true); 
    	        }
    	    });







    	   
    	    
    	    JButton proceed = new JButton("Proceed To Laboratory");
    	    proceed.setForeground(Color.black.brighter());
    	    proceed.setFont(buttonFont);
    	    proceed.setBackground(Color.orange);
    	    
    	    proceed.addActionListener(new ActionListener() {
    	        public void actionPerformed(ActionEvent e) {
    	            JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
    	            inventoryPage.StartInventoryPage();
    	            inventoryPage.getUserIndex(userIndex);
    	            topFrame.dispose();
    	        }
    	    });

    	    JPanel contentPane = new JPanel(new GridBagLayout());
    	    GridBagConstraints gbc = new GridBagConstraints();
    	    contentPane.setBackground(new Color(64, 64, 64, 0)); 
    	    gbc.insets = new Insets(10, 10, 10, 10); 

    	    
    	    contentPane.add(returnitems,gbc);
    	    contentPane.add(proceed, gbc);
    	   

    	    JPanel userDetails = new JPanel();
    	    userDetails.setLayout(new BorderLayout());
    	    userDetails.setBackground(new Color(64, 64, 64, 128).darker().darker().darker());
    	    userDetails.setPreferredSize(new Dimension(450, 200)); 
    	    containerLabel.setBorder(BorderFactory.createEmptyBorder(220, 0, 0, 0)); 
    	    userDetails.add(containerLabel, BorderLayout.WEST);


    	

    	    JPanel userBorrowHistory = new JPanel();
    	    userBorrowHistory.setLayout(new BorderLayout());
    	    userBorrowHistory.setBackground(new Color(64, 64, 64, 128).darker().darker().darker()); 
    	    userBorrowHistory.setPreferredSize(new Dimension(400, 200)); 

    	    userDetails.add(userWelcome, BorderLayout.NORTH);
    	    userDetails.add(userLiveTime, BorderLayout.SOUTH);
    	   
    	    userBorrowHistory.add(userHistory, BorderLayout.NORTH);

    	    
    	    
    	    innerPanel.add(userDetails, BorderLayout.WEST);
    	    innerPanel.add(userBorrowHistory, BorderLayout.EAST);
    	    innerPanel.add(contentPane, BorderLayout.CENTER);
    	    
    	    panel.add(titlePanel, BorderLayout.NORTH);
    	    panel.add(innerPanel);
    	    frame.add(panel);
    	    frame.setVisible(true);
    	    frame.setResizable(false);
    	}

    	private static void updateTimeLabel(JLabel label) {
    	    SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy - h:mm:ss a");
    	    String formattedDateTime = dateFormat.format(new Date());
    	    label.setText(formattedDateTime);
    	    label.setForeground(Color.black.darker().darker());
    	    
    	}

    	public void getUserIndex(int index) {
    	    this.userIndex = index;
    	}

}
