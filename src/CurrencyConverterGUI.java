import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CurrencyConverterGUI extends JFrame implements ActionListener {

    private JTextField amountField;
    private JComboBox<String> fromCurrency;
    private JComboBox<String> toCurrency;
    private JLabel resultLabel;
    private JButton convertButton;
    private Map<String, Double> rates;

    public CurrencyConverterGUI() {
        // ===== Window Setup =====
        setTitle("Currency Converter");
        setSize(420, 280);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2, 10, 10));
        setLocationRelativeTo(null); // center window

        // ===== Components =====
        JLabel amountLabel = new JLabel("Enter Amount:");
        amountField = new JTextField();

        JLabel fromLabel = new JLabel("From:");
        fromCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY"});

        JLabel toLabel = new JLabel("To:");
        toCurrency = new JComboBox<>(new String[]{"USD", "EUR", "INR", "GBP", "JPY"});

        convertButton = new JButton("Convert");
        convertButton.addActionListener(this);

        resultLabel = new JLabel("Converted Amount: ");
        resultLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // ===== Add Components =====
        add(amountLabel);
        add(amountField);
        add(fromLabel);
        add(fromCurrency);
        add(toLabel);
        add(toCurrency);
        add(new JLabel("")); // spacer
        add(convertButton);
        add(new JLabel("")); // spacer
        add(resultLabel);

        // ===== Conversion Rates =====
        rates = new HashMap<>();
        rates.put("USD", 1.0);
        rates.put("INR", 83.25);
        rates.put("EUR", 0.92);
        rates.put("GBP", 0.78);
        rates.put("JPY", 149.30);

        // ===== 🎨 Step 4: UI Styling =====
        getContentPane().setBackground(new Color(245, 245, 245)); // light gray
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        fromLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        toLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        resultLabel.setFont(new Font("Segoe UI", Font.BOLD, 15));

        convertButton.setBackground(new Color(0, 102, 204)); // blue button
        convertButton.setForeground(Color.WHITE);
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        convertButton.setFocusPainted(false);
        convertButton.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Optional: Add some padding inside the frame
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        setVisible(true);
    }

    // ===== Conversion Logic =====
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double amount = Double.parseDouble(amountField.getText());
            String from = (String) fromCurrency.getSelectedItem();
            String to = (String) toCurrency.getSelectedItem();

            if (from.equals(to)) {
                resultLabel.setText("Converted Amount: " + amount + " " + to);
                return;
            }

            double converted = amount * (rates.get(to) / rates.get(from));
            resultLabel.setText("Converted Amount: " + String.format("%.2f", converted) + " " + to);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // ===== Main Method =====
    public static void main(String[] args) {
        // Use a nicer look and feel (optional)
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        new CurrencyConverterGUI();
    }
}
