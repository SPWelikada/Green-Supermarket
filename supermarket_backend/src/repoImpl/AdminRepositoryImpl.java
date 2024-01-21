package repoImpl;

import dto.AdminDTO;
import dto.LoginDTO;
import entity.Admin;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl  {
    private static final String INSERT_ADMIN_QUERY = "INSERT INTO admin (name, email, username, password) VALUES (?, ?, ?, ?)";
    private static final String CHECK_CUSTOMER_QUERY = "SELECT COUNT(*) FROM customer WHERE id = ?";
    private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customer WHERE id = ?";
    private static final String UPDATE_CUSTOMER_QUERY = "UPDATE customer SET contact = ?, email = ?, username = ?, password = ? WHERE id = ?";
    private static final String SEARCH_CUSTOMER_BY_ID_QUERY = "SELECT * FROM customer WHERE id = ?";
    private static final String SEARCH_CUSTOMER_BY_USERNAME_QUERY = "SELECT * FROM customer WHERE username = ?";
    private static final String GET_ADMIN_BY_USERNAME_PASSWORD  = "SELECT * FROM admin WHERE username = ? AND password = ?";


    public AdminDTO save(AdminDTO adminDTO) throws Exception {

        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ADMIN_QUERY)) {
         //   preparedStatement.setInt(1, adminDTO.getId());
            preparedStatement.setString(1, adminDTO.getName());
            preparedStatement.setString(2, adminDTO.getEmail());
            preparedStatement.setString(3, adminDTO.getUsername());
            preparedStatement.setString(4, adminDTO.getPassword());
            preparedStatement.executeUpdate();

            return  adminDTO;
        } catch (SQLException throwables) {
             throw new Exception(throwables.getMessage());
        }

    }


    public LoginDTO getAdminByUserNameAndPassword(LoginDTO loginDTO) throws Exception {

            try {
                Connection connection = util.DBConnection.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(GET_ADMIN_BY_USERNAME_PASSWORD);
                preparedStatement.setString(1, loginDTO.getUsername());
                preparedStatement.setString(2, loginDTO.getPassword());
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    return new LoginDTO(resultSet.getString("username"),resultSet.getString("password"));
                }else{
                    throw new Exception("admin login error");
                }
            } catch (SQLException throwables) {
                throw new Exception(throwables.getMessage());
            }

    }

    private Admin extractCustomerFromResultSet(ResultSet resultSet) throws SQLException {
        return new Admin(
                resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("email"),
                resultSet.getString("username"),
                resultSet.getString("password")
        );
    }
}
