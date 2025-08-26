package lk.ijse.supermarketfx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.supermarketfx.dto.Roles;
import lk.ijse.supermarketfx.dto.UserDTO;
import lk.ijse.supermarketfx.util.AuthUtil;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 8/25/2025 3:46 PM
 * Project: supermarket-orm
 * --------------------------------------------
 **/

public class LoginController implements Initializable {
    @FXML
    private Button btnLogin;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    private final List<UserDTO> userDTOS = new ArrayList<>();

    public void btnLoginOnACtion(ActionEvent actionEvent) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        for (UserDTO userDTO : userDTOS) {
            if (userDTO.getUsername().equals(username) && userDTO.getPassword().equals(password)) {
//                userDTO
                Roles role = userDTO.getRole();
                AuthUtil.setUserDTO(userDTO);

                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/Dashboard.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(fxmlLoader.load()));
                stage.show();

                Stage window = (Stage) txtPassword.getScene().getWindow();
                window.close();

                if (role.equals(Roles.ADMIN)) {
                    // admin navigation
                } else {
                    // user navigation
                }
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("hello");
        userDTO.setPassword("1234");
        userDTO.setRole(Roles.ADMIN);

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setUsername("hi");
        userDTO2.setPassword("4321");
        userDTO2.setRole(Roles.USER);

        userDTOS.add(userDTO);
        userDTOS.add(userDTO2);
    }
}
