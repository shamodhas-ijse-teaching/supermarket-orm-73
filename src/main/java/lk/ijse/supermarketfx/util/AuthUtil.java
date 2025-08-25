package lk.ijse.supermarketfx.util;

import lk.ijse.supermarketfx.dto.Roles;
import lk.ijse.supermarketfx.dto.UserDTO;

/**
 * --------------------------------------------
 * Author: Shamodha Sahan
 * GitHub: https://github.com/shamodhas
 * Website: https://shamodha.com
 * --------------------------------------------
 * Created: 8/25/2025 3:55 PM
 * Project: supermarket-orm
 * --------------------------------------------
 **/

public class AuthUtil {
    private static UserDTO userDTO;

    public static UserDTO getUserDTO() {
        return userDTO;
    }

    public static void setUserDTO(UserDTO userDTO) {
        AuthUtil.userDTO = userDTO;
    }

    public static boolean isAdmin() {
        if (userDTO != null) {
            return userDTO.getRole().equals(Roles.ADMIN);
        }
        return false;
    }
}
