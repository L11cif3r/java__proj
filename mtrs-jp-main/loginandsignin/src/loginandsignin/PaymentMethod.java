package loginandsignin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentMethod {
    private JFrame frame;
    private JTextField upiIdField;
    private JTextField accountHolderNameField;
    private JTextField cardNumberField;
    private JTextField cvvField;

    public PaymentMethod() {
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Payment Method");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new FlowLayout());

        JLabel upiIdLabel = new JLabel("UPI ID:");
        upiIdField = new JTextField(20);

        JLabel accountHolderNameLabel = new JLabel("Account Holder Name:");
        accountHolderNameField = new JTextField(20);

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField(20);

        JLabel cvvLabel = new JLabel("CVV:");
        cvvField = new JTextField(20);

        JButton googlePayButton = new JButton("Google Pay");
        googlePayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payWithUPI("Google Pay");
            }
        });

        JButton phonePeButton = new JButton("PhonePe");
        phonePeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payWithUPI("PhonePe");
            }
        });

        JButton paytmButton = new JButton("Paytm");
        paytmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                payWithUPI("Paytm");
            }
        });

        JButton creditCardButton = new JButton("Credit Card");
        creditCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountHolderName = accountHolderNameField.getText();
                String cardNumber = cardNumberField.getText();
                int cvv = Integer.parseInt(cvvField.getText());
                PaymentMethodImplementationCreditCard paymentMethod = new PaymentMethodImplementationCreditCard(accountHolderName, cardNumber, cvv);
                paymentMethod.pay();
            }
        });

        JButton debitCardButton = new JButton("Debit Card");
        debitCardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String accountHolderName = accountHolderNameField.getText();
                String cardNumber = cardNumberField.getText();
                int cvv = Integer.parseInt(cvvField.getText());
                PaymentMethodImplementationDebitCard paymentMethod = new PaymentMethodImplementationDebitCard(accountHolderName, cardNumber, cvv);
                paymentMethod.pay();
            }
        });

        frame.add(upiIdLabel);
        frame.add(upiIdField);

        frame.add(accountHolderNameLabel);
        frame.add(accountHolderNameField);

        frame.add(cardNumberLabel);
        frame.add(cardNumberField);

        frame.add(cvvLabel);
        frame.add(cvvField);

        frame.add(googlePayButton);
        frame.add(phonePeButton);
        frame.add(paytmButton);
        frame.add(creditCardButton);
        frame.add(debitCardButton);

        frame.pack();
        frame.setVisible(true);
    }

    private void payWithUPI(String paymentMethod) {
        String upiId = upiIdField.getText();
        PaymentMethodImplementationUPI paymentMethodInstance = new PaymentMethodImplementationUPI(paymentMethod, upiId);
        paymentMethodInstance.pay();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PaymentMethod();
            }
        });
    }
}

abstract class PaymentMethodImplementation{
    protected String paymentMethod;

    public PaymentMethodImplementation(String paymentMethod) {
        // Add error handling or validation here if needed
        this.paymentMethod = paymentMethod;
    }

    public abstract void pay();
}

class PaymentMethodImplementationDebitCard extends PaymentMethodImplementation {
    private String accountHolderName;
    private String cardNumber;
    private int cvv;

    public PaymentMethodImplementationDebitCard(String accountHolderName, String cardNumber, int cvv) {
        super("Debit Card");
        this.accountHolderName = accountHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public void pay() {
        System.out.println("Paying with Debit Card: " + paymentMethod);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("CVV: " + cvv);
         JOptionPane.showMessageDialog(null, "Payment successful!" );
         System.exit(0);
    }
}

class PaymentMethodImplementationCreditCard extends PaymentMethodImplementation {
    private String accountHolderName;
    private String cardNumber;
    private int cvv;

    public PaymentMethodImplementationCreditCard(String accountHolderName, String cardNumber, int cvv) {
        super("Credit Card");
        this.accountHolderName = accountHolderName;
        this.cardNumber = cardNumber;
        this.cvv = cvv;
    }

    @Override
    public void pay() {
        System.out.println("Paying with Credit Card: " + paymentMethod);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Card Number: " + cardNumber);
        System.out.println("CVV: " + cvv);
         JOptionPane.showMessageDialog(null, "Payment successful!" );
         System.exit(0);
    }
}

class PaymentMethodImplementationUPI extends PaymentMethodImplementation {
    private String upiId;

    public PaymentMethodImplementationUPI(String paymentMethod, String upiId) {
        super(paymentMethod);
        this.upiId = upiId;
    }

    @Override
    public void pay() {
        System.out.println("Paying with UPI: " + paymentMethod);
        System.out.println("UPI ID: " + upiId);
         JOptionPane.showMessageDialog(null, "Payment successful!" );
         System.exit(0);
    }
}