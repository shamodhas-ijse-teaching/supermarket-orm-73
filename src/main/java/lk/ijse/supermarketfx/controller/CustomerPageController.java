package lk.ijse.supermarketfx.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.supermarketfx.bo.BOFactory;
import lk.ijse.supermarketfx.bo.BOTypes;
import lk.ijse.supermarketfx.bo.custom.CustomerBO;
import lk.ijse.supermarketfx.bo.exception.DuplicateException;
import lk.ijse.supermarketfx.bo.exception.InUseException;
import lk.ijse.supermarketfx.bo.exception.NotFoundException;
import lk.ijse.supermarketfx.db.DBConnection;
import lk.ijse.supermarketfx.dto.CustomerDTO;
import lk.ijse.supermarketfx.dto.tm.CustomerTM;
import lk.ijse.supermarketfx.model.CustomerModel;
import lk.ijse.supermarketfx.util.AuthUtil;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 4/3/2025 10:29 AM
 * Project: SupermarketFX
 * --------------------------------------------
 **/

public class CustomerPageController implements Initializable {
    public Label lblId;
    public TextField txtName;
    public TextField txtNic;
    public TextField txtEmail;
    public TextField txtPhone;

    //    private final CustomerModel customerModel = new CustomerModel();
    private final CustomerBO customerBO = BOFactory.getInstance().getBO(BOTypes.CUSTOMER);

    // TM - table model Ex: CustomerTM
    public TableView<CustomerTM> tblCustomer;

    public TableColumn<CustomerTM, String> colId;
    public TableColumn<CustomerTM, String> colName;
    public TableColumn<CustomerTM, String> colNic;
    public TableColumn<CustomerTM, String> colEmail;
    public TableColumn<CustomerTM, String> colPhone;

    private final String namePattern = "^[A-Za-z ]+$";
    private final String nicPattern = "^[0-9]{9}[vVxX]||[0-9]{12}$";
    private final String emailPattern = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private final String phonePattern = "^(\\d+)||((\\d+\\.)(\\d){2})$";

    public Button btnDelete;
    public Button btnUpdate;
    public Button btnSave;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        CustomerBO bo = (CustomerBO) BOFactory.getInstance().getBO(BOTypes.CUSTOMER);
//        bo.getAllCustomer();

        // table column and tm class properties link
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        // C001
//        lblId.setText("C001");
        try {
//            loadNextId();
//            loadTableData();
            resetPage();
        } catch (Exception e) {
            new Alert(
                    Alert.AlertType.ERROR, "Fail to load data..!"
            ).show();
        }

        //        handle authentication
        boolean isAdmin = AuthUtil.isAdmin();
        if (!isAdmin) {
            btnSave.setDisable(true);
            btnDelete.setDisable(true);
            btnUpdate.setDisable(true);
        }
    }

    private void resetPage() throws SQLException {
        loadNextId();
        loadTableData();

//        Show or hide
//        btnDelete.setVisible();

//        disabled
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

//        enabled
        btnSave.setDisable(false);

        txtName.setText("");
        txtNic.setText("");
        txtEmail.setText("");
        txtPhone.setText("");
    }

    private void loadTableData() throws SQLException {
        // 1. Long code

        // Fetch data from DB
//        ArrayList<CustomerDTO> customerDTOArrayList = customerModel.getAllCustomer();
//
//        ObservableList<CustomerTM> list = FXCollections.observableArrayList();
//        for (CustomerDTO customerDTO : customerDTOArrayList){
//            CustomerTM customerTM = new CustomerTM(
//                    customerDTO.getCustomerId(),
//                    customerDTO.getName(),
//                    customerDTO.getNic(),
//                    customerDTO.getEmail(),
//                    customerDTO.getPhone()
//            );
//
//            list.add(customerTM);
//        }
//        tblCustomer.setItems(list);


        // 2. Short code

        // dto list -> tm list
//        List<CustomerTM> list = customerModel.getAllCustomer().stream().map(
//                customerDTO -> new CustomerTM(
//                        customerDTO.getCustomerId(),
//
//                        )
//        ).toList();


//        MVC
//        tblCustomer.setItems(FXCollections.observableArrayList(
//                customerModel.getAllCustomer().stream().map(customerDTO ->
//                        new CustomerTM(
//                                customerDTO.getCustomerId(),
//                                customerDTO.getName(),
//                                customerDTO.getNic(),
//                                customerDTO.getEmail(),
//                                customerDTO.getPhone()
//                        )).toList()
//        ));

//        Layered
        tblCustomer.setItems(FXCollections.observableArrayList(
                customerBO.getAllCustomer().stream().map(customerDTO ->
                        new CustomerTM(
                                customerDTO.getCustomerId(),
                                customerDTO.getName(),
                                customerDTO.getNic(),
                                customerDTO.getEmail(),
                                customerDTO.getPhone()
                        )).toList()
        ));


    }

    private void loadNextId() throws SQLException {
//        String nextId = customerModel.getNextId();
        String nextId = customerBO.getNextId();
        lblId.setText(nextId);
    }

    public void btnCustomerSaveOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String customerId = lblId.getText();

        // data validation
        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

//        if (!isValidName){
        // alert
//            return;
//        }

        // Shamodha sahan
//        String namePattern = "^[A-Za-z ]+$";
        // 1. Using Pattern object java.util.regex
//        Pattern compiledNamePattern = Pattern.compile(namePattern);
//        boolean isValidName = compiledNamePattern.matcher(name).matches();
//        System.out.println(name + " is valid:(1) " + isValidName);

        // 2. Using String class matches() method
//        boolean matches = name.matches(namePattern);
//        System.out.println(name + " is valid:(2) " + matches);


        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #BB25B9;");
        txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #BB25B9;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #BB25B9;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #BB25B9;");

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidNic) {
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhone) {
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
        }

        // DTO - Data transfer object
        // Create dto object wrap data to single unit
        CustomerDTO customerDTO = new CustomerDTO(
                customerId,
                name,
                nic,
                email,
                phone
        );

        // setter method customerDTO.setNic("");

        // Call CustomerModel inside saveCustomer method
        // controller to model parse data using dto
//        CustomerModel customerModel = new CustomerModel();
        if (isValidName && isValidNic && isValidEmail && isValidPhone) {
            try {
//                boolean isSave = customerModel.saveCustomer(customerDTO);
                customerBO.saveCustomer(customerDTO);

                // Business -> DAO
//                if (isSave) {
//                    loadNextId();
//                    loadTableData();
                resetPage();
                new Alert(
                        Alert.AlertType.INFORMATION, "Customer saved successfully..!"
                ).show();
//                } else {
//                    new Alert(
//                            Alert.AlertType.ERROR, "Fail to save customer..!"
//                    ).show();
//                }
            } catch (DuplicateException e) {
                System.out.println(e.getMessage());
//                e.printStackTrace();
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
//                e.getMessage();
                new Alert(
                        Alert.AlertType.ERROR, "Fail to save customer..!"
                ).show();
            }
        }
//       Static method call using classname CustomerModel.saveCustomer();
    }

    public void txtNameChange(KeyEvent keyEvent) {
        String name = txtName.getText();

        boolean isValidName = name.matches(namePattern);

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red");
        } else {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #BB25B9");
        }
    }

    public void onClickTable(MouseEvent mouseEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();

        if (selectedItem != null) {
            lblId.setText(selectedItem.getId());
            txtName.setText(selectedItem.getName());
            txtNic.setText(selectedItem.getNic());
            txtEmail.setText(selectedItem.getEmail());
            txtPhone.setText(selectedItem.getPhone());

            btnSave.setDisable(true);

            btnUpdate.setDisable(false);
            btnDelete.setDisable(false);
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(
                Alert.AlertType.CONFIRMATION,
                "Are your sure ?",
                ButtonType.YES,
                ButtonType.NO
        );

        Optional<ButtonType> response = alert.showAndWait();

        if (response.isPresent() && response.get() == ButtonType.YES) {
            try {
                String customerId = lblId.getText();
//                boolean isDeleted = customerModel.deleteCustomer(customerId);
                boolean isDeleted = customerBO.deleteCustomer(customerId);

                if (isDeleted) {
                    resetPage();
                    new Alert(
                            Alert.AlertType.INFORMATION, "Customer deleted successfully."
                    ).show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Fail to delete customer.").show();

                }
            } catch (InUseException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(
                        Alert.AlertType.ERROR, "Fail to delete customer..!"
                ).show();
            }
        }

//        null
//        optional { null }
//        optional { data }
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String name = txtName.getText();
        String nic = txtNic.getText();
        String email = txtEmail.getText();
        String phone = txtPhone.getText();
        String customerId = lblId.getText();

        boolean isValidName = name.matches(namePattern);
        boolean isValidNic = nic.matches(nicPattern);
        boolean isValidEmail = email.matches(emailPattern);
        boolean isValidPhone = phone.matches(phonePattern);

        txtName.setStyle(txtName.getStyle() + ";-fx-border-color: #BB25B9;");
        txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: #BB25B9;");
        txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: #BB25B9;");
        txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: #BB25B9;");

        if (!isValidName) {
            txtName.setStyle(txtName.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidNic) {
            txtNic.setStyle(txtNic.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidEmail) {
            txtEmail.setStyle(txtEmail.getStyle() + ";-fx-border-color: red;");
        }
        if (!isValidPhone) {
            txtPhone.setStyle(txtPhone.getStyle() + ";-fx-border-color: red;");
        }

        CustomerDTO customerDTO = new CustomerDTO(
                customerId,
                name,
                nic,
                email,
                phone
        );

        if (isValidName && isValidNic && isValidEmail && isValidPhone) {
            try {
                customerBO.updateCustomer(customerDTO);

                resetPage();
                new Alert(
                        Alert.AlertType.INFORMATION, "Customer update successfully..!"
                ).show();
            } catch (NotFoundException | DuplicateException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            } catch (Exception e) {
                e.printStackTrace();
                new Alert(
                        Alert.AlertType.ERROR, "Fail to update customer..!"
                ).show();
            }
        }
    }

    public void btnResetOnAction(ActionEvent actionEvent) throws SQLException {
        resetPage();
    }

    public void generateAllCustomerReport(ActionEvent actionEvent) {
        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/customers.jrxml")
            );

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_date", LocalDate.now().toString());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters, // null
                    connection
            );

            JasperViewer.viewReport(jasperPrint, false);

            // p_date

            // arraylist
            // 0 data
            // 1 data

            // map
            // key - value
            // v1 - hello
            // string - object


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void genrateCustomerOrderReport(ActionEvent actionEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        try {
            JasperReport jasperReport = JasperCompileManager.compileReport(
                    getClass().getResourceAsStream("/report/customer_order_report.jrxml")
            );

            Connection connection = DBConnection.getInstance().getConnection();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("p_customer_id", selectedItem.getId());

            JasperPrint jasperPrint = JasperFillManager.fillReport(
                    jasperReport,
                    parameters, // null
                    connection
            );

            JasperPrintManager.printReport(jasperPrint, true);

            JasperViewer.viewReport(jasperPrint, false);

            // p_date

            // arraylist
            // 0 data
            // 1 data

            // map
            // key - value
            // v1 - hello
            // string - object


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void btnSendMail(ActionEvent actionEvent) {
        CustomerTM selectedItem = tblCustomer.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            return;
        }

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/SendMailPage.fxml"));
            Parent load = fxmlLoader.load();

            SendMailPageController controller = fxmlLoader.getController();
            controller.setCustomerEmail(selectedItem.getEmail());

            Stage stage = new Stage();
            stage.setScene(new Scene(load));
            stage.setTitle("Send mail");

            stage.initModality(Modality.APPLICATION_MODAL);

            Window window = txtPhone.getScene().getWindow();
            stage.initOwner(window);

            stage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//$P{customerId}