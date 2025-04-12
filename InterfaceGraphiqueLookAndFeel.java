//// import javax.swing.*;
//
//// public class ms {
////     public static void main(String[] args) {
////         // Créer une fenêtre
////         JFrame frame = new JFrame("Exemple JLabel avec HTML");
////         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
////         // Créer un JLabel avec du contenu HTML
////         JLabel label = new JLabel("<html><h1 style='color:blue;font-family:poppins;'>Bonjour,</h1><p>Ceci est du <b>HTML</b> dans un JLabel !</p></html>");
//
////         // Ajouter le JLabel à la fenêtre
////         frame.add(label);
//
////         // Ajuster la taille de la fenêtre
////         frame.pack();
////         frame.setVisible(true);
////     }
//// }
//
//
//import javax.swing.*;
//
//public class InterfaceGraphiqueLookAndFeel {
//    public static void main(String[] args) {
//        try {
//            // Activer le Look and Feel Nimbus
//            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        // Créer une fenêtre
//        JFrame frame = new JFrame("Exemple Interface Graphique");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 300);
//
//        // Ajouter un panneau
//        JPanel panel = new JPanel();
//
//        // Ajouter un champ de texte
//        JTextField textField = new JTextField(20);
//        panel.add(textField);
//
//        // Ajouter un bouton
//        JButton button = new JButton("Cliquez-moi !");
//        button.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Bonjour, " + textField.getText() + " !"));
//        panel.add(button);
//
//        // Ajouter le panneau à la fenêtre
//        frame.add(panel);
//
//        // Rendre la fenêtre visible
//        frame.setVisible(true);
//    }
//}