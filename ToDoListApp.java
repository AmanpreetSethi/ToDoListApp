import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListApp extends JFrame implements ActionListener {
    // UI Components
    private JTextField taskInputField;
    private JButton addTaskButton, deleteTaskButton;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskDisplayList;

    // Constructor to set up the UI
    public ToDoListApp() {
        setTitle("To-Do List App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Creating components
        taskInputField = new JTextField();
        addTaskButton = new JButton("Add Task");
        deleteTaskButton = new JButton("Delete Task");

        // Setting up task list
        taskListModel = new DefaultListModel<>();
        taskDisplayList = new JList<>(taskListModel);
        JScrollPane scrollPane = new JScrollPane(taskDisplayList); // Add scrolling for better usability

        // Add action listeners to buttons
        addTaskButton.addActionListener(this);
        deleteTaskButton.addActionListener(this);

        // Top panel for task input
        JPanel inputPanel = new JPanel(new BorderLayout(5, 5));
        inputPanel.add(taskInputField, BorderLayout.CENTER);
        inputPanel.add(addTaskButton, BorderLayout.EAST);

        // Bottom panel for delete button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(deleteTaskButton);

        // Adding components to the frame
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        // Make the window visible
        setVisible(true);
    }

    // Handling button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addTaskButton) {
            String task = taskInputField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskInputField.setText(""); // Clear input after adding task
            } else {
                JOptionPane.showMessageDialog(this, "Task cannot be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        } else if (e.getSource() == deleteTaskButton) {
            int selectedIndex = taskDisplayList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a task to delete.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(ToDoListApp::new); // Ensuring GUI runs on the Event Dispatch Thread
    }
}
