package teamplo_program_level_objective_setting;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.*;

public class PLOCreateFrame extends Frame {
    private PLOOperations ploOps;
    private DefaultTableModel tableModel;

    public PLOCreateFrame(PLOOperations ploOps, DefaultTableModel tableModel) {
        this.ploOps = ploOps;
        this.tableModel = tableModel;
        
        setTitle("Create PLO");
        setSize(600, 500);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setBackground(new Color(240, 240, 240));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setPreferredSize(new Dimension(600, 50));

        Label header = new Label("Create Program Level Objective", Label.CENTER);
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

        // Form Fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(createLabel("LO Code:"), gbc);
        gbc.gridx = 1;
        TextField tfCode = createTextField();
        formPanel.add(tfCode, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        formPanel.add(createLabel("LO No:"), gbc);
        gbc.gridx = 1;
        TextField tfNo = createTextField();
        formPanel.add(tfNo, gbc);

        gbc.gridy = 2;
        gbc.gridx = 0;
        formPanel.add(createLabel("LO Name:"), gbc);
        gbc.gridx = 1;
        TextField tfName = createTextField();
        formPanel.add(tfName, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        formPanel.add(createLabel("LO Details:"), gbc);
        gbc.gridx = 1;
        TextField tfDetails = createTextField();
        formPanel.add(tfDetails, gbc);

        // Message Label
        Label msg = new Label("", Label.CENTER);
        msg.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        msg.setForeground(new Color(231, 76, 60));
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        formPanel.add(msg, gbc);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        Button btnBack = createStyledButton("Back", new Color(149, 165, 166));
        Button btnSave = createStyledButton("Save", new Color(46, 204, 113));

        buttonPanel.add(btnBack);
        buttonPanel.add(btnSave);

        // Add components to main frame
        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Event Listeners
        btnSave.addActionListener(e -> {
            if (tfCode.getText().trim().isEmpty() || tfNo.getText().trim().isEmpty() ||
                    tfName.getText().trim().isEmpty() || tfDetails.getText().trim().isEmpty()) {
                msg.setText("Please fill in all fields.");
                return;
            }
            
            if (ploOps.TeamPLO_plo_create(tfCode.getText(), tfNo.getText(), tfName.getText(), tfDetails.getText())) {
                ploOps.TeamPLO_plo_retrieve(tableModel);
                dispose();
            } else {
                msg.setText("Error creating record. Please try again.");
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