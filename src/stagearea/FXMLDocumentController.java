package stagearea;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mysql.jdbc.PreparedStatement;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
//import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FXMLDocumentController implements Initializable {

    private Label label;
    private TextField usernameField;
    private PasswordField passwordField;
    public Label zoneonelabel;

    //Table View Database Values
    @FXML
    private Button btnLoad;
    @FXML
    TextField fareAmount;

    private Button btnsetFare;
    @FXML
    private TableView<userDetailsModel> tableUser;
    @FXML
    private TableColumn<userDetailsModel, String> columnId;
    @FXML
    private TableColumn<userDetailsModel, String> columnName;
    @FXML
    private TableColumn<userDetailsModel, String> columnAge;
    @FXML
    private TableColumn<userDetailsModel, String> columnOrigin;
    @FXML
    private TableColumn<userDetailsModel, String> columnDestination;
    @FXML
    private TableColumn<userDetailsModel, String> columnDays;

    //Observable List to Hold data to be displayed from the databaase.
    private ObservableList<userDetailsModel> data;

    private void handleButtonAction(ActionEvent event) throws IOException {

        //validate user    
        if (usernameField.getText().equals("username") && passwordField.getText().equals("password")) {
            label.setText("Wrong credentials");
            label.setText("Awesome niggah");

            //Opening a new scene
            Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("main.css").toExternalForm());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

        } else {
            label.setText("Login Failed");
        }
    }
    @FXML
    ComboBox<String> comboBoxOrigin = new ComboBox();
    @FXML
    ComboBox<String> comboBoxDestination = new ComboBox();
    @FXML
    ComboBox<String> comboBoxDays = new ComboBox();
    @FXML
    TextField originField;
    @FXML
    TextField destinationField;
    @FXML
    TextField daysField;
    @FXML
    TextField nameField;
    @FXML
    TextField ageField;
    @FXML
    TextField filledName;
    @FXML
    TextField filledAge;
    @FXML
    Button chooseImage;
    @FXML
    DatePicker datepicker;
    @FXML
    Label datepickerlabel;
    @FXML
    TextField totalAmountPaid;
    @FXML
    TextField discountReceived;
    @FXML
    TextField muakahuuField;
    @FXML
    TextField kuzaliwaField;
    @FXML
    ImageView imageViewOne;

    TextField extraField;
    //Database variables
    Statement statement;

    //Populating comboBox with Option values
    ObservableList<String> listone = FXCollections.observableArrayList("Mosocho", "Oyugis", "Kisumu", "Bungoma");
    ObservableList<String> listtwo = FXCollections.observableArrayList("Kamera", "Keroka", "Kericho", "Nakuru");
    ObservableList<String> listThree = FXCollections.observableArrayList("7", "14", "21", "60");

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO 
        comboBoxOrigin.setItems(listone);
        comboBoxDestination.setItems(listtwo);
        comboBoxDays.setItems(listThree);
        // cc = new ConnectionClass();
    }

    //  Origin Station(Zone0ne)
    public void originStation(ActionEvent event) {
        //zoneonelabel.setText(comboBox.getValue());
        originField.setText(comboBoxOrigin.getValue());
        nameField.setText(filledName.getText());
        ageField.setText(filledAge.getText());
    }

    // Destination Station(ZoneTWo)
    public void destinationStation(ActionEvent event) {
        destinationField.setText(comboBoxDestination.getValue());
    }

    // number of days selected
    public void numberOfDaysSelected(ActionEvent event) {
        daysField.setText(comboBoxDays.getValue());
    }

    //Select Image using fileChooser;
    public void viewCustomers() {
        //file chooser logic
        /*   FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/home/trojan101/Pictures"));
        fc.getExtensionFilters().addAll(new ExtensionFilter("PDF FILES", "*.pdf"));
        fc.showOpenDialog(null);*/
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("CustomerViewers.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    //show date selected from date picker
    public void showDate() {
        LocalDate localDate = datepicker.getValue();
        datepickerlabel.setText(localDate.toString());
    }

    //Logic of both age and number of days
    public void calculateOffers() {

        String totalAmount = totalAmountPaid.getText();
        String originalZone = originField.getText();
        String destinationZone = destinationField.getText();
        String choosenDays = daysField.getText();

        //SECTION MANAGE FAIR PRICE
        //section One
        if (totalAmountPaid == null && filledAge == null) {
            JOptionPane.showMessageDialog(null, "You are required to set Amount paid");
        } else if (filledAge == null) {
            JOptionPane.showMessageDialog(null, "You need to set Customers Age");
        }
        if (originField.getText().equals("Mosocho") && destinationField.getText().equals("Kamera")) {

            //totalAmountPaid.getText();
        } else if (originField.getText().equals("Mosocho") && destinationField.getText().equals("Keroka")) {
            System.out.println("At keroka");
        } else if (originField.getText().equals("Mosocho") && destinationField.getText().equals("Kericho")) {
            System.out.println("At kericho");
        } else if (originField.getText().equals("Mosocho") && destinationField.getText().equals("Nakuru")) {
            System.out.println("At Nakuru");

            //section two
        } else if (originField.getText().equals("Oyugis") && destinationField.getText().equals("Kamera")) {
            System.out.println("Maneno");
        } else if (originField.getText().equals("Oyugis") && destinationField.getText().equals("Keroka")) {
            System.out.println("At keroka");
        } else if (originField.getText().equals("Oyugis") && destinationField.getText().equals("Kericho")) {
            System.out.println("At kericho");
        } else if (originField.getText().equals("Oyugis") && destinationField.getText().equals("Nakuru")) {
            System.out.println("At Nakuru");
        } //Section Three
        else if (originField.getText().equals("Kisumu") && destinationField.getText().equals("Kamera")) {
            System.out.println("Maneno");
        } else if (originField.getText().equals("Kisumu") && destinationField.getText().equals("Keroka")) {
            System.out.println("At keroka");
        } else if (originField.getText().equals("Kisumu") && destinationField.getText().equals("Kericho")) {
            System.out.println("At kericho");
        } else if (originField.getText().equals("Kisumu") && destinationField.getText().equals("Nakuru")) {
            System.out.println("At Nakuru");
        } //Section Four
        else if (originField.getText().equals("Bungoma") && destinationField.getText().equals("Kamera")) {
            System.out.println("Maneno");
        } else if (originField.getText().equals("Bungoma") && destinationField.getText().equals("Keroka")) {
            System.out.println("At keroka");
        } else if (originField.getText().equals("Bungoma") && destinationField.getText().equals("Kericho")) {
            System.out.println("At kericho");
        } else if (originField.getText().equals("Bungoma") && destinationField.getText().equals("Nakuru")) {
            System.out.println("At Nakuru");
        } else {
            String miaka = filledAge.getText();
            System.out.println(miaka);
        }

        //SECTION AGE AND DAYS MANAGE{netsted if statements}
        String miaka = filledAge.getText();
        int age = Integer.valueOf(miaka); // Integer.parseInt(miaka)

        String siku = daysField.getText();
        int days = Integer.valueOf(siku);

        if (age < 10 && days < 30) {
            //System.out.println(days);
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.1 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age < 10 && days > 30) {
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.4 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age > 50 && days < 7) {
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.1 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age < 50 && days <= 7) {
            //less than 50yrs and less than 7dyas 10%discount
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.1 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age < 50 && days <= 14) {
            //less than 50yrs and less than 14dyas 15%discount
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.15 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age < 50 && days <= 21) {
            //less than 50yrs and less than 7dyas 16%discount
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.16 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age < 50 && days <= 30) {
            //less than 50yrs and less than 7dyas 20%discount
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.2 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age < 50 && days > 30) {
            //less than 50yrs and less than 7dyas 22%discount
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.22 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else if (age > 50 && days > 7) {
            String ticket = totalAmountPaid.getText();
            int favoured = Integer.valueOf(ticket);
            int discountOne = (int) (0.4 * favoured);
            String displayDiscount = Integer.toString(discountOne);
            discountReceived.setText(displayDiscount);

        } else {
            String error = "Something went Wrong... ";
            JOptionPane.showMessageDialog(null, error);
        }
    }
    //Generate receipt

    public void generateReceipt() throws DocumentException, BadElementException, IOException {

        //VALIDATE USER INPUTS BEFORE RECEIPT GENERATION
        if (nameField == null) {
            JOptionPane.showMessageDialog(null, "Customer's name is required");
        } else if (ageField == null) {
            JOptionPane.showMessageDialog(null, "Customer's Age is required");
        } else if (originField == null) {
            JOptionPane.showMessageDialog(null, "Origin is required");
        } else if (destinationField == null) {
            JOptionPane.showMessageDialog(null, "Customer's destination is required");
        } else if (ageField == null) {
            JOptionPane.showMessageDialog(null, "Customer's Age is required");
        } else if (daysField == null) {
            JOptionPane.showMessageDialog(null, "PLease select number of days");
        } else if (totalAmountPaid == null) {
            JOptionPane.showMessageDialog(null, "Please set Current Fare");
        } else if (discountReceived == null) {
            JOptionPane.showMessageDialog(null, "discount need to be calculated correctly");
        } else {

            //Creating Pdf font colors, font-family, font-size
            Font redFont
                    = FontFactory.getFont(FontFactory.HELVETICA, 14, Font.UNDERLINE, new CMYKColor(15, 255, 20, 40));
            Font paraFontOne
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(2, 255, 20, 155));
            Font fontCustomerValue
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(2, 255, 20, 15));
            Font dateFont
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(8, 222, 20, 15));
            Font paidcolor
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(85, 222, 2, 15));

            Font discountColor
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(86, 244, 66, 22));
            Font thanksColor
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(244, 66, 66, 66));
            Font contactsColor
                    = FontFactory.getFont(FontFactory.COURIER, 11, Font.NORMAL, new CMYKColor(244, 66, 66, 00));
            try {

                //define page size and document(pdf)
                Rectangle pageSize = new Rectangle(220, 500);
                Document document = new Document(pageSize, 5, 5, 5, 5);
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("receipt.pdf"));
                document.open();

                PdfPTable table = new PdfPTable(4);
                table.setWidthPercentage(100);
                table.setLockedWidth(true);
                PdfContentByte contentByte = writer.getDirectContent();

                //Create fields int the table 
                //First Row
                PdfPCell cell = new PdfPCell(new Phrase("Kamwana"));
                cell.setFixedHeight(30);
                cell.setBorder(2);
                cell.setColspan(3);
                cell.setBackgroundColor(BaseColor.GRAY);

                //DOCUMENT LINKING
                String paraOne = "Welcome To newBus Travellers";
                String stageOne = ("Original Zone : " + originField.getText());
                String stageTwo = ("Destination Zone : " + destinationField.getText());
                String stageDays = ("Number Of Days : " + daysField.getText());
                String customerName = ("Customers Name : " + filledName.getText());
                String newDate = ("Date : " + datepickerlabel.getText());
                String totalAmountToBePaid = ("Amount Paid : " + totalAmountPaid.getText());
                String discountReceivedByCustomer = ("Discount Received : " + discountReceived.getText());
                String thankYouNote = "Thank You For Travelling With us";
                String contacts = "0707058437 or EMAIL US at haronnjugunaw@gmail.com";

                document.add(new Paragraph(paraOne, redFont));
                document.add(new Paragraph(newDate, dateFont));
                document.add(new Paragraph(customerName, fontCustomerValue));
                document.add(new Paragraph(stageOne, paraFontOne));
                document.add(new Paragraph(stageTwo, paraFontOne));
                document.add(new Paragraph(stageDays, paraFontOne));
                document.add(new Paragraph(totalAmountToBePaid, paidcolor));
                document.add(new Paragraph(discountReceivedByCustomer, discountColor));
                document.add(new Paragraph(thankYouNote, thanksColor));
                document.add(new Paragraph(contacts, contactsColor));
                table.addCell(cell);
                document.add(table);//Adding table component

                //Set Printing Permission
                int ALLOW_PRINTING = PdfWriter.ALLOW_PRINTING;
                int CenterWindow = PdfWriter.CenterWindow;

                //Dynamic content
                Image img = Image.getInstance("/home/trojan101/Pictures/WhatsApp Images/one.jpg");
                //setImage position
                img.setAbsolutePosition(200f, 450f);
                //rotate image
                img.setRotation(250f);
                //scale image
                img.scaleToFit(30f, 800f);
                img.setBorderColor(BaseColor.RED);

                //Show Alert
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Receipt Dialogue");
                alert.setHeaderText("Receipt has been generated successifully");
                //Alert buttons
                ButtonType buttonTypeOne = new ButtonType("CONFIRM");
                ButtonType buttonTypeTWO = new ButtonType("EXIT");
                alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTWO);

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == buttonTypeOne) {
                    try {
                        //some File Viewer logic
                        FileChooser viewReceipts = new FileChooser();
                        viewReceipts.setInitialDirectory(new File("/home/trojan101/NetBeansProjects/StageArea"));
                        viewReceipts.getExtensionFilters().addAll(new ExtensionFilter("PDF FILES", "*.pdf", "*.PDF"));
                        viewReceipts.showOpenDialog(null);

                    } catch (Exception e) {
                        //String dc = "Receipts Directory is not configured";
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                } else if (result.get() == buttonTypeTWO) {
                    //some Printing Logic
                    System.out.println("Some Printing Action Button");
                }
                document.add(img);
                document.close();
                writer.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveDataToDatabase(ActionEvent actionEvent) {

        String dbName = "Fxdata";
        String userName = "root";
        String password = "";
        try {
            Connection connection;
            PreparedStatement preparedStatement;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server" + dbName + " " + "database");

            String query = "INSERT INTO `customerdetails` (name,age,origin,destination,days,amountpaid,discount) values (?,?,?,?,?,?,?)";

            preparedStatement = (PreparedStatement) connection.prepareStatement(query);

            preparedStatement.setString(1, nameField.getText());
            preparedStatement.setString(2, ageField.getText());
            preparedStatement.setString(3, originField.getText());
            preparedStatement.setString(4, destinationField.getText());
            preparedStatement.setString(5, daysField.getText());
            preparedStatement.setString(6, totalAmountPaid.getText());
            preparedStatement.setString(7, discountReceived.getText());

            /*  String query = "INSERT INTO `customerfinance` (`amountpaid`, `discount`) "
                          + "VALUES ("+totalAmountPaid.getText().trim()+", "+discountReceived.getText().trim()+"";*/
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, " Data Saved Successfully");
        } catch (HeadlessException | SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    //Calculate AGE LOADER
    public void calculateAge() {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("AgeCalculator.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    //Calculate AGE BUTTON LOGIC
    public void ageAnswer() {
        try {
            String ansOne = muakahuuField.getText();
            String ansTwo = kuzaliwaField.getText();

            int one = Integer.valueOf(ansOne);
            int two = Integer.valueOf(ansTwo);

            if (ansOne == null) {
                JOptionPane.showInternalMessageDialog(null, "Current year cannot be null");
            } else if (ansTwo == null) {
                JOptionPane.showInternalMessageDialog(null, "PLease fill in Year Of Birth");
            } else {
                int ageAnswer = one - two;
                JOptionPane.showMessageDialog(null, "Customers Age is " + ageAnswer);
            }
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "The fields cannot be empty");
        }

    }

    @FXML
    public void loadDataFromDatabase(ActionEvent e) {
        //JOptionPane.showConfirmDialog(null, "You can get data from database");
        // cc = new ConnectionClass();
        // Connection connection = cc.getConnection();

        data = FXCollections.observableArrayList();
        try {
            String dbName = "Fxdata";
            String userName = "root";
            String password = "";

            Connection connection;
            // PreparedStatement preparedStatement;
            connection = DriverManager.getConnection("jdbc:mysql://localhost/" + dbName, userName, password);
            //JOptionPane.showMessageDialog(null, "Connected to database server" + dbName + " " + "database");

            //Execute Query and store data in a resulset
            ResultSet rs = connection.createStatement().executeQuery("SELECT * FROM customerdetails");

            while (rs.next()) {
                data.add(new userDetailsModel(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException ex) {
            //Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }

        //Set cell value factory to tableView
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnAge.setCellValueFactory(new PropertyValueFactory<>("age"));
        columnOrigin.setCellValueFactory(new PropertyValueFactory<>("origin"));
        columnDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
        columnDays.setCellValueFactory(new PropertyValueFactory<>("days"));

        //tableUser.setItems(null);
        tableUser.setItems(data);

    }

    //Set The Fare 
    public void setFare() {
        // JOptionPane.showMessageDialog(null, "set the fare madam");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("setFare.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("login.css").toExternalForm());
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

//Setting the Fare Logic
    public void btnsetFareOk(ActionEvent e) {

        try {
            String setAmount = fareAmount.getText();
            String strI = String.valueOf(setAmount);
            totalAmountPaid.setText(strI);
            JOptionPane.showMessageDialog(null, strI);
        } catch (NullPointerException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        //JOptionPane.showMessageDialog(null, "Fare set successfully");
        //JOptionPane.showMessageDialog(null, strI);
    }
}
