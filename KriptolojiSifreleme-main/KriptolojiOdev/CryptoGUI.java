import javax.swing.*;
import java.awt.*;

public class CryptoGUI extends JFrame {
    private final JComboBox<String> cipherSelect;
    private final JTextField keyField1;
    private final JTextField keyField2;
    private final JTextArea inputArea;
    private final JTextArea outputArea;

    public CryptoGUI() {
        super("Kriptoloji Araçları");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);

        cipherSelect = new JComboBox<>(new String[]{"Caesar", "Substitution", "Affine", "Vigenere"});
        keyField1 = new JTextField(15);
        keyField2 = new JTextField(15);
        inputArea = new JTextArea(6, 40);
        outputArea = new JTextArea(6, 40);
        outputArea.setEditable(false);

        // initial enable/disable state
        keyField1.setEnabled(true);
        keyField2.setEnabled(false);

        // update key fields when cipher selection changes
        cipherSelect.addActionListener(e -> {
            String sel = (String) cipherSelect.getSelectedItem();
            if ("Affine".equals(sel)) {
                keyField1.setEnabled(true); // a
                keyField2.setEnabled(true); // b
            } else if ("Caesar".equals(sel) || "Substitution".equals(sel)) {
                keyField1.setEnabled(true); // shift
                keyField2.setEnabled(false); // not used
                keyField2.setText("");
            } else if ("Vigenere".equals(sel)) {
                keyField1.setEnabled(true); // key string
                keyField2.setEnabled(false);
                keyField2.setText("");
            } else {
                keyField1.setEnabled(true);
                keyField2.setEnabled(true);
            }
        });

        JPanel top = new JPanel();
        top.add(new JLabel("Cipher: "));
        top.add(cipherSelect);
        top.add(new JLabel("Key1: "));
        top.add(keyField1);
        top.add(new JLabel("Key2: "));
        top.add(keyField2);

        JPanel center = new JPanel(new BorderLayout());
        center.add(new JScrollPane(inputArea), BorderLayout.NORTH);
        center.add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        JButton encryptBtn = new JButton("Encrypt");
        JButton decryptBtn = new JButton("Decrypt");

        JPanel bottom = new JPanel();
        bottom.add(encryptBtn);
        bottom.add(decryptBtn);

        add(top, BorderLayout.NORTH);
        add(center, BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        encryptBtn.addActionListener(e -> process(true));
        decryptBtn.addActionListener(e -> process(false));
    }

    private void process(boolean encrypt) {
        String cipherName = (String) cipherSelect.getSelectedItem();
        String input = inputArea.getText();
        // If decrypting and input is empty, use the output area as ciphertext (common UI pattern)
        if (!encrypt && (input == null || input.trim().isEmpty())) {
            input = outputArea.getText();
        }
        try {
            String result;
            switch (cipherName) {
                case "Caesar": {
                    int shift = Integer.parseInt(keyField1.getText().trim());
                    SezarSifreleme c = new SezarSifreleme(shift);
                    result = encrypt ? c.encrypt(input) : c.decrypt(input);
                    break;
                }
                case "Substitution": {
                    int shift = Integer.parseInt(keyField1.getText().trim());
                    SubstitutionCipher c = new SubstitutionCipher(shift);
                    result = encrypt ? c.encrypt(input) : c.decrypt(input);
                    break;
                }
                case "Affine": {
                    int a = Integer.parseInt(keyField1.getText().trim());
                    int b = Integer.parseInt(keyField2.getText().trim());
                    // validate a has modular inverse mod 26
                    if (AffineCipher.modInverse(a, 26) == -1) {
                        throw new IllegalArgumentException("Affine: 'a' must be coprime with 26");
                    }
                    AffineCipher c = new AffineCipher(a, b);
                    result = encrypt ? c.encrypt(input) : c.decrypt(input);
                    break;
                }
                case "Vigenere": {
                    String key = keyField1.getText().trim();
                    VigenereCipher c = new VigenereCipher(key);
                    result = encrypt ? c.encrypt(input) : c.decrypt(input);
                    break;
                }
                default:
                    result = "Unknown cipher";
            }
            outputArea.setText(result);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Hata: " + ex.getMessage(), "Hata", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CryptoGUI gui = new CryptoGUI();
            gui.setVisible(true);
        });
    }
}
