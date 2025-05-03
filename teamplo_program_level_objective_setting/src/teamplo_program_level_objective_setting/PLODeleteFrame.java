package teamplo_program_level_objective_setting;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

public class PLODeleteFrame extends Frame {
    private PLOOperations ploOps;
    private DefaultTableModel tableModel;

    public PLODeleteFrame(PLOOperations ploOps, DefaultTableModel tableModel) {
        this.ploOps = ploOps;
        this.tableModel = tableModel;
        
        setTitle("Delete PLO");
        setSize(500, 300);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setBackground(new Color(240, 240, 240));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setPreferredSize(new Dimension(500, 50));

        Label header = new Label("Delete Program Level Objective", Label.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 20));
        header.setForeground(Color.WHITE);
        headerPanel.add(header, BorderLayout.CENTER);

        // Form Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        formPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // ID Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        inputPanel.setBackground(Color.WHITE);
        Label lblID = new Label("Enter LO ID:", Label.RIGHT);
        lblID.setFont(new Font("Segoe UI", Font.BOLD, 14));
        TextField tfID = createTextField();
        inputPanel.add(lblID);
        inputPanel.add(tfID);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(inputPanel, gbc);

        // Message Label
        Label msg = new Label("", Label.CENTER);
        msg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        msg.setForeground(new Color(231, 76, 60));
        gbc.gridy = 1;
        formPanel.add(msg, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        Button btnBack = createStyledButton("Back", new Color(149, 165, 166));
        Button btnDelete = createStyledButton("Delete", new Color(231, 76, 60));

        buttonPanel.add(btnBack);
        buttonPanel.add(btnDelete);

        // Add components to main frame
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        btnDelete.addActionListener(e -> {
            try {
                int id = Integer.parseInt(tfID.getText());
                int confirm = JOptionPane.showConfirmDialog(this, 
                    "Are you sure you want to delete this record?", 
                    "Confirm Delete", 
                    JOptionPane.YES_NO_OPTION);
                
                if (confirm == JOptionPane.YES_OPTION) {
                    if (ploOps.TeamPLO_plo_delete(id)) {
                        ploOps.TeamPLO_plo_retrieve(tableModel);
                        dispose();
                    } else {
                        msg.setText("ID not found.");
                    }
                }
            } catch (Exception ex) {
                msg.setText("Error: " + ex.getMessage());
            }
        });

        btnBack.addActionListener(e -> dispose());
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private Label createLabel(String text) {
        Label label = new Label(text, Label.RIGHT);
        label.setFont(new Font("Segoe UI", Font.BOLD, 14));
        return label;
    }

    private TextField createTextField() {
        TextField tf = new TextField(20);
        tf.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        return tf;
    }

    private Button createStyledButton(String text, Color bgColor) {
        Button button = new Button(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setBackground(bgColor);
        button.setForeground(Color.WHITE);
        button.setPreferredSize(new Dimension(120, 40));
        button.setFocusable(false);
        return button;
    }
} 