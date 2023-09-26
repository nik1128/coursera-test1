import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

public class PatientReportApp extends JFrame implements ActionListener {
    private JPanel doctorPanel;
    private JPanel patientPanel;
    private JPanel medicalHistoryPanel;
    private JPanel summaryPanel;
    private JPanel buttonPanel;

    private JTextField doctorNameField;
    private JTextField patientNameField;
    private JTextArea medicalHistoryTextArea;
    private JTextArea summaryTextArea;

    private JButton saveButton;

    public PatientReportApp() {
        setTitle("Patient Report");
        setLayout(new GridLayout(5, 1));

        doctorPanel = new JPanel(new GridLayout(3, 2));
        doctorPanel.add(new JLabel("Doctor's Name: "));
        doctorNameField = new JTextField(20);
        doctorPanel.add(doctorNameField);
        doctorPanel.add(new JLabel("Doctor's Contact: "));
        doctorPanel.add(new JTextField(20));
        doctorPanel.add(new JLabel("Specialization: "));
        doctorPanel.add(new JTextField(20));
        add(doctorPanel);

        patientPanel = new JPanel(new GridLayout(3, 2));
        patientPanel.add(new JLabel("Patient's Name: "));
        patientNameField = new JTextField(20);
        patientPanel.add(patientNameField);
        patientPanel.add(new JLabel("Patient's ID: "));
        patientPanel.add(new JTextField(20));
        patientPanel.add(new JLabel("Contact: "));
        patientPanel.add(new JTextField(20));
        add(patientPanel);

        medicalHistoryPanel = new JPanel();
        medicalHistoryPanel.setLayout(new BorderLayout());
        medicalHistoryPanel.add(new JLabel("Medical History: "), BorderLayout.NORTH);
        medicalHistoryTextArea = new JTextArea(10, 40);
        medicalHistoryTextArea.setLineWrap(true);
        medicalHistoryPanel.add(new JScrollPane(medicalHistoryTextArea), BorderLayout.CENTER);
        add(medicalHistoryPanel);

        summaryPanel = new JPanel();
        summaryPanel.setLayout(new BorderLayout());
        summaryPanel.add(new JLabel("Report Summary: "), BorderLayout.NORTH);
        summaryTextArea = new JTextArea(10, 40);
        summaryTextArea.setLineWrap(true);
        summaryPanel.add(new JScrollPane(summaryTextArea), BorderLayout.CENTER);
        add(summaryPanel);

        buttonPanel = new JPanel();
        saveButton = new JButton("Save Report");
        saveButton.addActionListener(this);
        buttonPanel.add(saveButton);
        add(buttonPanel);

        pack();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveButton) {
            // Gather data from the form
            String doctorName = doctorNameField.getText();
            String patientName = patientNameField.getText();
            String medicalHistory = medicalHistoryTextArea.getText();
            String reportSummary = summaryTextArea.getText();

            // Create a patient report string
            String patientReport = "Doctor's Name: " + doctorName + "\n"
                    + "Patient's Name: " + patientName + "\n"
                    + "Medical History: " + medicalHistory + "\n"
                    + "Report Summary: " + reportSummary;

            // Save the report to a file (you can modify the file path)
            try (FileWriter writer = new FileWriter("patient_report.txt")) {
                writer.write(patientReport);
                JOptionPane.showMessageDialog(this, "Patient report saved successfully!");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Error saving the patient report: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PatientReportApp app = new PatientReportApp();
            app.setVisible(true);
        });
    }
}
