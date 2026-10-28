import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.time.temporal.ChronoUnit;

public class BirthdayCountdown extends JFrame {

    private JTextField dayField;
    private JTextField monthField;
    private JLabel resultLabel;

    public BirthdayCountdown() {

        setTitle("Birthday Countdown 🎂");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(new Color(255, 235, 243));

        // Heading
        JLabel title = new JLabel("🎂 Birthday Countdown 🎂");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(new Color(190, 60, 120));
        title.setBounds(65, 25, 330, 35);
        panel.add(title);

        // Instruction
        JLabel instruction = new JLabel("When is your birthday?");
        instruction.setFont(new Font("SansSerif", Font.PLAIN, 15));
        instruction.setBounds(135, 75, 200, 25);
        panel.add(instruction);

        // Day label
        JLabel dayLabel = new JLabel("Birthday Day:");
        dayLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        dayLabel.setBounds(65, 120, 120, 25);
        panel.add(dayLabel);

        // Day input
        dayField = new JTextField();
        dayField.setBounds(200, 120, 150, 30);
        panel.add(dayField);

        // Month label
        JLabel monthLabel = new JLabel("Birthday Month:");
        monthLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        monthLabel.setBounds(65, 165, 120, 25);
        panel.add(monthLabel);

        // Month input
        monthField = new JTextField();
        monthField.setBounds(200, 165, 150, 30);
        panel.add(monthField);

        // Calculate button
        JButton calculateButton = new JButton("Calculate Countdown");
        calculateButton.setBounds(110, 215, 230, 40);
        calculateButton.setBackground(new Color(235, 130, 175));
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFocusPainted(false);
        calculateButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(calculateButton);

        // Result
        resultLabel = new JLabel("Your countdown appears here!", SwingConstants.CENTER);
        resultLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        resultLabel.setForeground(new Color(150, 50, 100));
        resultLabel.setBounds(25, 275, 390, 50);
        panel.add(resultLabel);

        calculateButton.addActionListener(e -> calculateCountdown());

        add(panel);
        setVisible(true);
    }

    private void calculateCountdown() {

        try {
            int day = Integer.parseInt(dayField.getText().trim());
            int month = Integer.parseInt(monthField.getText().trim());

            LocalDate today = LocalDate.now();

            // Validate the birthday using the current year
            LocalDate birthday = LocalDate.of(today.getYear(), month, day);

            // If the birthday has passed, use next year
            if (birthday.isBefore(today)) {
                birthday = birthday.plusYears(1);
            }

            long daysLeft = ChronoUnit.DAYS.between(today, birthday);

            if (daysLeft == 0) {
                resultLabel.setText("🎉 Happy Birthday! Have an amazing day! 🎂");
            } else {
                resultLabel.setText("🎁 Only " + daysLeft
                        + " days until your birthday!");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter numbers for the day and month.",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (DateTimeException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid birthday!",
                    "Invalid Date",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(BirthdayCountdown::new);
    }
}

