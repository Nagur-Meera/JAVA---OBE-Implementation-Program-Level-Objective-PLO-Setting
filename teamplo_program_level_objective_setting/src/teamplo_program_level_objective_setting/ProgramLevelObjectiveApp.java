package teamplo_program_level_objective_setting;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.border.*;

public class ProgramLevelObjectiveApp extends Frame {
    private JTable table;
    private DefaultTableModel tableModel;
    private PLOOperations ploOps;

    public static void main(String[] args) {
        DBConnection.connect("teamplo.db");
        new ProgramLevelObjectiveApp();
    }

    public ProgramLevelObjectiveApp() {
        ploOps = new PLOOperations();
        
        setTitle("Team PLO - Program Level Objective Setting");
        setSize(1000, 700);
        setLayout(new BorderLayout(10, 10));
        setLocationRelativeTo(null);
        setBackground(new Color(240, 240, 240));

        // Header Panel
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(52, 73, 94));
        headerPanel.setPreferredSize(new Dimension(1000, 60));

        Label header = new Label("Program Level Objective Records", Label.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 24));
        header.setForeground(Color.WHITE);
        headerPanel.add(header, BorderLayout.CENTER);

        // Table Panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.WHITE);
        tablePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] columnNames = {"ID", "LO Code", "LO No", "LO Name", "LO Details"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        table = new JTable(tableModel);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.setRowHeight(30);
        table.setSelectionBackground(new Color(52, 152, 219));
        table.setSelectionForeground(Color.WHITE);
        table.setGridColor(new Color(220, 220, 220));
        table.setShowGrid(true);

        // Style table header
        JTableHeader headerTable = table.getTableHeader();
        headerTable.setFont(new Font("Segoe UI", Font.BOLD, 14));
        headerTable.setBackground(new Color(52, 73, 94));
        headerTable.setForeground(Color.WHITE);
        headerTable.setPreferredSize(new Dimension(headerTable.getWidth(), 40));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new LineBorder(new Color(200, 200, 200)));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        buttonPanel.setBackground(new Color(240, 240, 240));

        Button btnCreate = createStyledButton("Create", new Color(46, 204, 113));
        Button btnUpdate = createStyledButton("Update", new Color(52, 152, 219));
        Button btnDelete = createStyledButton("Delete", new Color(231, 76, 60));
        Button btnRefresh = createStyledButton("Refresh", new Color(155, 89, 182));
        Button btnExit = createStyledButton("Exit", new Color(149, 165, 166));

        for (Button b : new Button[]{btnCreate, btnUpdate, btnDelete, btnRefresh, btnExit}) {
            buttonPanel.add(b);
        }

        // Add components to main frame
        add(headerPanel, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Add double-click listener to table
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        int id = (int) tableModel.getValueAt(row, 0);
                        String code = (String) tableModel.getValueAt(row, 1);
                        String no = (String) tableModel.getValueAt(row, 2);
                        String name = (String) tableModel.getValueAt(row, 3);
                        String details = (String) tableModel.getValueAt(row, 4);
                        
                        PLOUpdateFrame updateFrame = new PLOUpdateFrame(ploOps, tableModel);
                        updateFrame.setVisible(true);
                        updateFrame.loadData(id, code, no, name, details);
                    }
                }
            }
        });

        // Button actions
        btnCreate.addActionListener(e -> new PLOCreateFrame(ploOps, tableModel).setVisible(true));
        btnUpdate.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                int id = (int) tableModel.getValueAt(row, 0);
                String code = (String) tableModel.getValueAt(row, 1);
                String no = (String) tableModel.getValueAt(row, 2);
                String name = (String) tableModel.getValueAt(row, 3);
                String details = (String) tableModel.getValueAt(row, 4);
                
                PLOUpdateFrame updateFrame = new PLOUpdateFrame(ploOps, tableModel);
                updateFrame.setVisible(true);
                updateFrame.loadData(id, code, no, name, details);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a row to update.", 
                    "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });
        btnDelete.addActionListener(e -> new PLODeleteFrame(ploOps, tableModel).setVisible(true));
        btnRefresh.addActionListener(e -> ploOps.TeamPLO_plo_retrieve(tableModel));
        btnExit.addActionListener(e -> {
            DBConnection.closeConnection();
            dispose();
        });

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DBConnection.closeConnection();
                dispose();
            }
        });

        ploOps.TeamPLO_plo_retrieve(tableModel);
        setVisible(true);
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