package repoImpl;

import entity.HelpDesk;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HelpDeskRepositoryImpl {
    private static final String INSERT_HELPDESK_QUERY = "INSERT INTO supermarket.helpdesk (id, FAQs, questions, answers, guidance) VALUES (?, ?, ?, ?, ?)";
    private static final String UPDATE_HELPDESK_QUERY = "UPDATE supermarket.helpdesk SET FAQs = ?, questions = ?, answers = ?, guidance = ? WHERE id = ?";
    private static final String DELETE_HELPDESK_QUERY = "DELETE FROM supermarket.helpdesk WHERE id = ?";
    private static final String SELECT_HELPDESK_BY_ID_QUERY = "SELECT * FROM supermarket.helpdesk WHERE id = ?";
    private static final String SELECT_ALL_HELPDESK_QUERY = "SELECT * FROM supermarket.helpdesk";


    public void addHelpDesk(HelpDesk helpDesk) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HELPDESK_QUERY)) {
            preparedStatement.setInt(1, helpDesk.getId());
            preparedStatement.setString(2, helpDesk.getFAQs());
            preparedStatement.setString(3, helpDesk.getQuestions());
            preparedStatement.setString(4, helpDesk.getAnswers());
            preparedStatement.setString(5, helpDesk.getGuidance());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void updateHelpDesk(HelpDesk helpDesk) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_HELPDESK_QUERY)) {
            preparedStatement.setString(1, helpDesk.getFAQs());
            preparedStatement.setString(2, helpDesk.getQuestions());
            preparedStatement.setString(3, helpDesk.getAnswers());
            preparedStatement.setString(4, helpDesk.getGuidance());
            preparedStatement.setInt(5, helpDesk.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public void deleteHelpDesk(int helpDeskId) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_HELPDESK_QUERY)) {
            preparedStatement.setInt(1, helpDeskId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }


    public HelpDesk getHelpDeskById(int helpDeskId) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HELPDESK_BY_ID_QUERY)) {
            preparedStatement.setInt(1, helpDeskId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return extractHelpDeskFromResultSet(resultSet);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }


    public List<HelpDesk> getAllHelpDesk() {
        List<HelpDesk> helpDeskList = new ArrayList<>();
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_HELPDESK_QUERY)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                helpDeskList.add(extractHelpDeskFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return helpDeskList;
    }

    private HelpDesk extractHelpDeskFromResultSet(ResultSet resultSet) throws SQLException {
        return new HelpDesk(
                resultSet.getInt("id"),
                resultSet.getString("FAQs"),
                resultSet.getString("questions"),
                resultSet.getString("answers"),
                resultSet.getString("guidance")
        );
    }
}
