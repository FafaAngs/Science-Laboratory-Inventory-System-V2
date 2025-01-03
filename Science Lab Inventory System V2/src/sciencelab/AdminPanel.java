package sciencelab;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class AdminPanel {

    private static final String LOGS_FILE_PATH = "logs.json";
    private static final String LOGSRETURN_FILE_PATH = "returnlogs.json";
    private JPanel panel;
    public static String[][] data;
    public static String[][] returndata;
    private static boolean showHistoryListenerAdded = false;
    JLabel userLiveTime = new JLabel();
    static FirstHomePage firstHomePage = new FirstHomePage();
    static UserInformation userInformation = new UserInformation();
    static ScienceLabItems scienceLabItems = new ScienceLabItems();
    static InventoryPage inventoryPage = new InventoryPage();
    
    static JPanel leftPanel;//sda
    static JPanel rightPanel;
     
    public void StartAdminPanel() {
    	
    	JFrame frame = new JFrame("Science Laboratory Inventory System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920,1080 );
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.orange);

        JLabel titleLabel = new JLabel("ADMINISTRATOR");
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 60));
        titleLabel.setForeground(Color.black.brighter());
        titleLabel.setFont(new Font("Serif", Font.BOLD, 36));
        titleLabel.setBackground(Color.orange.darker());
        titleLabel.setOpaque(true);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titlePanel.add(titleLabel, BorderLayout.NORTH);

        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTimeLabel(userLiveTime);
            }
        });
        timer.start();

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

        panel.add(titlePanel, BorderLayout.NORTH);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(Color.RED); 
        logoutButton.setForeground(Color.white);
        logoutButton.setFocusPainted(false);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 16));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(10, 15 , 10, 25));

        logoutButton.addActionListener(e -> {
            firstHomePage.StartFirstHomePage();
            frame.dispose();
        });

        JPanel spacer = new JPanel();
        spacer.setPreferredSize(new Dimension(710, 0)); 
        spacer.setBackground(new Color(64, 64, 64, 0)); 

        panel.add(logoutButton, BorderLayout.LINE_END);
        titlePanel.add(spacer, BorderLayout.CENTER);
        titlePanel.add(userLiveTime, BorderLayout.WEST);
        userLiveTime.setFont(new Font("Serif", Font.BOLD, 20));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(64, 64, 64, 125));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.insets = new Insets(10, 0, 10, 0);
        gbc.anchor = GridBagConstraints.CENTER;

        panel.add(centerPanel, BorderLayout.CENTER);
        JPanel buttonsPanel = new JPanel(new GridLayout(1, 5, 20, 0));
        buttonsPanel.setBackground(new Color(64, 64, 64, 0));

        JPanel yellowPanel = new JPanel(new BorderLayout());
        yellowPanel.setPreferredSize(new Dimension(1800, 900));
        yellowPanel.setBackground(new Color(64, 64, 64, 0));

        leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.PAGE_AXIS));
        leftPanel.setBackground(new Color(64, 64, 64, 0));

        rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.PAGE_AXIS));
        rightPanel.setBackground(new Color(64, 64, 64, 0));

        showItemsStocks();
        showMaterialStocks();
        

        yellowPanel.add(leftPanel, BorderLayout.WEST);
        yellowPanel.add(rightPanel, BorderLayout.EAST);

        centerPanel.add(yellowPanel, gbc);
        yellowPanel.setBorder(BorderFactory.createEmptyBorder(100, 0, 10, 0));

        JButton logsButton = new JButton("Logs");
        styleButton(logsButton, new Color(58, 128, 189));

        JButton returnedItemsUsersButton = new JButton("Users who Returned Items");
        styleButton(returnedItemsUsersButton, new Color(58, 128, 189));

        JButton registeredUsersButton = new JButton("Registered Users");
        styleButton(registeredUsersButton, new Color(58, 128, 189));

        JButton addItemsButton = new JButton("Add Items");
        styleButton(addItemsButton, new Color(58, 128, 189));

        JButton addMaterialsButton = new JButton("Add Materials");
        styleButton(addMaterialsButton, new Color(58, 128, 189));

        buttonsPanel.add(logsButton);
        buttonsPanel.add(returnedItemsUsersButton);
        buttonsPanel.add(registeredUsersButton);
        buttonsPanel.add(addItemsButton);
        buttonsPanel.add(addMaterialsButton);

        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));
        centerPanel.add(buttonsPanel, gbc);

        panel.add(centerPanel, BorderLayout.CENTER);

        logsButton.addActionListener(e -> showLogsDialog());
        returnedItemsUsersButton.addActionListener(e -> showReturnedItemsUsersDialog());
        registeredUsersButton.addActionListener(e -> showRegisteredUsersDialog());
        addItemsButton.addActionListener(e -> showAddItemsDialog());
        addMaterialsButton.addActionListener(e -> showAddMaterialsDialog());

        frame.add(panel);
        frame.setVisible(true);
    }

    private void styleButton(JButton button, Color backgroundColor) {
        button.setBackground(backgroundColor);
        button.setForeground(Color.white);
        button.setFocusPainted(false);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
    }

    private void showLogsDialog() {
        loadDataFromJson();
        JPanel historyPanel = new JPanel(new BorderLayout());
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        String[] columns = {"Name", "ID Number", "Item Name", "QTY / Volume","Date and Time"};
        JTable table = new JTable(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        table.setFont(font);
        JScrollPane scrollPane = new JScrollPane(table);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(1800, 900));
        JOptionPane.showMessageDialog(null, historyPanel, "Logs", JOptionPane.PLAIN_MESSAGE);
    }

    private void showReturnedItemsUsersDialog() {
    	loadReturnDataFromJson();
        JPanel returnedItemsUsersPanel = new JPanel(new BorderLayout());
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
        String[] columns = {"User Name", "User ID", "Returned Items", "Date Returned"};
        String[][] dataWithoutDate = new String[0][4];

        if (returndata != null && returndata.length > 0) {
            dataWithoutDate = new String[returndata.length][4];
            for (int i = 0; i < returndata.length; i++) {
                if (returndata[i] != null &&returndata[i].length >= 5) { 
                    dataWithoutDate[i][0] = returndata[i][0];
                    dataWithoutDate[i][1] = returndata[i][1];
                    dataWithoutDate[i][2] = returndata[i][2];
                    dataWithoutDate[i][3] = returndata[i][4];
                } else {
                
                    dataWithoutDate[i][0] = "";
                    dataWithoutDate[i][1] = "";
                    dataWithoutDate[i][2] = "";
                    dataWithoutDate[i][3] = "";
                }
            }
        }

        JTable table = new JTable(new DefaultTableModel(dataWithoutDate, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }    
        });
        table.setFont(font);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane scrollPane = new JScrollPane(table);
        returnedItemsUsersPanel.add(scrollPane, BorderLayout.CENTER);
        scrollPane.setPreferredSize(new Dimension(1200, 500));
        JOptionPane.showMessageDialog(null, returnedItemsUsersPanel, "Users who Returned Items", JOptionPane.PLAIN_MESSAGE);
    }



    private void showRegisteredUsersDialog() {
        userInformation.loadFromJson();
        JPanel registeredUsersPanel = new JPanel(new BorderLayout());
        String[] columns = {"User Name", "User ID", "Registration Date"};
        Object[][] userData = new Object[userInformation.FirstName.size()][3];
        for (int i = 0; i < userInformation.FirstName.size(); i++) {
            String firstName = userInformation.FirstName.get(i) + " ," + userInformation.LastName.get(i);  
            String userID = userInformation.IDNumber.get(i);
            String registrationDate = userInformation.AccountDateCreated.get(i);
            userData[i][0] = firstName.toUpperCase();
            userData[i][1] = userID;
            userData[i][2] = registrationDate;
        }
        DefaultTableModel model = new DefaultTableModel(userData, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable table = new JTable(model);
        Font font = new Font(Font.MONOSPACED, Font.PLAIN, 16);
        table.setFont(font);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(1800, 900));
        registeredUsersPanel.add(scrollPane, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(null, registeredUsersPanel, "Registered Users", JOptionPane.PLAIN_MESSAGE);
    }

    private void showAddItemsDialog() {
        JTextField itemNameField = new JTextField(10);
        JTextField quantityField = new JTextField(10);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Item Name:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(itemNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Quantity:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(quantityField, gbc);
        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Add Items", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	String itemName = itemNameField.getText();
        	String volumeInput = quantityField.getText();

        	int quantity = 0;
        	try {
        		quantity = Integer.parseInt(volumeInput);
        	} catch (NumberFormatException e) {
        
        	    JOptionPane.showMessageDialog(null, "Please enter a valid integer for volume.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        	    return; 
        	}
        	scienceLabItems.loadFromJson();

boolean equipmentFound = false;

for (int i = 0; i < inventoryPage.materialNames.length; i++) {
    if (itemName.equalsIgnoreCase(inventoryPage.equipmentNames[i])) {

        scienceLabItems.equipments[i] += quantity;
        
        equipmentFound = true;
        

        break;
    }
}

if (equipmentFound) {
	
   
    JOptionPane.showMessageDialog(null, "Equipment '" + itemName + "' added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    scienceLabItems.saveToJson();
	JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
	thisFrame.dispose();
	StartAdminPanel();

  
} else {
 
    JOptionPane.showMessageDialog(null, "Equipment '" + itemName + "' not found.", "Material Not Found", JOptionPane.ERROR_MESSAGE);
}
   
        }
    }

    private void showAddMaterialsDialog() {
        JTextField materialNameField = new JTextField(10);
        JTextField volumeField = new JTextField(10);
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 0;
        inputPanel.add(new JLabel("Material Name:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(materialNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        inputPanel.add(new JLabel("Volume:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(volumeField, gbc);
        int result = JOptionPane.showConfirmDialog(null, inputPanel, "Add Materials", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        	String materialName = materialNameField.getText();
        	String volumeInput = volumeField.getText();

        	int volumeToAdd = 0;
        	try {
        	    volumeToAdd = Integer.parseInt(volumeInput);
        	} catch (NumberFormatException e) {
        
        	    JOptionPane.showMessageDialog(null, "Please enter a valid integer for volume.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
        	    return; 
        	}
        	scienceLabItems.loadFromJson();

boolean materialFound = false;

for (int i = 0; i < inventoryPage.materialNames.length; i++) {
    if (materialName.equalsIgnoreCase(inventoryPage.materialNames[i])) {

        scienceLabItems.materials[i] += volumeToAdd;
        
        materialFound = true;
        

        break;
    }
}

if (materialFound) {
	
   
    JOptionPane.showMessageDialog(null, "Material '" + materialName + "' added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
    scienceLabItems.saveToJson();
	JFrame thisFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
	thisFrame.dispose();
	StartAdminPanel();
} else {
 
    JOptionPane.showMessageDialog(null, "Material '" + materialName + "' not found.", "Material Not Found", JOptionPane.ERROR_MESSAGE);
}
   }

             
            
        }
    

    public JPanel getPanel() {
        return panel;
    }

    public void saveDataToJson() {
        try (Writer writer = new FileWriter(LOGS_FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(data, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataFromJson() {
        try (Reader reader = new FileReader(LOGS_FILE_PATH)) {
            Gson gson = new Gson();
            data = gson.fromJson(reader, String[][].class);
        } catch (IOException e) {
            data = null;
        }
    }


    public void addData(String[] newData) {
        if (data == null) {
            data = new String[1][];
            data[0] = newData;
        } else {
            String[][] newDataArray = Arrays.copyOf(data, data.length + 1);
            newDataArray[data.length] = newData;
            data = newDataArray;
        }
     
    }
    
    
    
    
    public void saveReturnDataToJson() {
        try (Writer writer = new FileWriter(LOGSRETURN_FILE_PATH)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(returndata, writer);
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadReturnDataFromJson() {
        try (Reader reader = new FileReader(LOGSRETURN_FILE_PATH)) {
            Gson gson = new Gson();
            returndata = gson.fromJson(reader, String[][].class);
        } catch (IOException e) {
        	returndata = null;
        }
    }
    
    public void addDataReturn(String[] newData) {
        if (returndata == null) {
        	returndata = new String[1][];
        	returndata[0] = newData;
        } else {
            String[][] newDataArray = Arrays.copyOf(returndata, returndata.length + 1);
            newDataArray[returndata.length] = newData;
            returndata = newDataArray;
        }
     
    }

    private static void updateTimeLabel(JLabel label) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy - h:mm:ss a");
        String formattedDateTime = dateFormat.format(new Date());
        label.setText(formattedDateTime);
        label.setForeground(Color.black.darker().darker());
    }
    
    private void showItemsStocks() {
    	for (int i = 0; i < 6; i++) {
            scienceLabItems.loadFromJson();
            JLabel leftLabel = new JLabel("Item Name:" + inventoryPage.equipmentNames[i] + " Stocks Left: " + scienceLabItems.equipments[i]);
            leftLabel.setFont(new Font("Arial", Font.BOLD, 16));
            leftLabel.setBorder(BorderFactory.createEmptyBorder(0, 200, 10, 0)); 
            leftLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
            leftPanel.add(leftLabel);
        }

    }
    private void showMaterialStocks() {
    	for (int i = 0; i < 6; i++) {
            scienceLabItems.loadFromJson();
             JLabel rightLabel = new JLabel("Item Name:" + inventoryPage.materialNames[i] + " Stocks Left: " + scienceLabItems.materials[i]);
             rightLabel.setFont(new Font("Arial", Font.BOLD, 16));
             rightLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 200)); 
             rightLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
             rightPanel.add(rightLabel);
         }
    }
}