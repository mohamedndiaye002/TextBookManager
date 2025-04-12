//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//
//public class inserer {
//
//    // Fonction pour insérer un élément dans la table fichecours
//    public static void insererFicheCours(int isSigned, String details, int idCours, int idCahierDeTexte) {
//        // URL, utilisateur et mot de passe pour la base de données
//        String url = "jdbc:mysql://localhost:3306/cdt1"; // Remplacez avec vos informations
//        String user = "root";
//        String password = "";
//
//        // Requête SQL d'insertion
//        // String sql = "INSERT INTO fichecours (isSigned, details, idCours, idCahierDeTexte) VALUES (?, ?, ?, ?)";
//        String sql = "SELECT cl.`classeName`, c.intitule FROM personnel p INNER JOIN cours c ON p.`idPersonnel` = c.`idPersonnel`\r\n" + //
//                        "INNER JOIN fichecours f ON f.`idCours` = c.`idCours`\r\n" + //
//                        "INNER JOIN cahierdetexte ct ON f.`idCahierDeTexte` = ct.`idCahierDeTexte`\r\n" + //
//                        "INNER JOIN classe cl ON ct.`idClasse`= cl.`idClasse`\r\n" + //
//                        "WHERE cl.`idDepartement` =1 AND p.`idPersonnel` = 21;";
//
//        try (Connection connection = DriverManager.getConnection(url, user, password);
//             PreparedStatement statement = connection.prepareStatement(sql)) {
//
//            // Assigner les valeurs aux paramètres de la requête
//            statement.setInt(1, isSigned); // Colonne isSigned
//            statement.setString(2, details); // Colonne details
//            statement.setInt(3, idCours); // Colonne idCours
//            statement.setInt(4, idCahierDeTexte); // Colonne idCahierDeTexte
//
//            // Exécuter la requête
//            int rowsInserted = statement.executeUpdate();
//            if (rowsInserted > 0) {
//                System.out.println("Insertion réussie !");
//            } else {
//                System.out.println("Échec de l'insertion.");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("erreur");
//        }
//    }
//
//    public static void main(String[] args) {
//        // Exemple d'utilisation : Insérer un nouvel enregistrement
//        insererFicheCours(1, "Séance 1 : Introduction aux Mathématiques", 1, 1);
//    }
//}