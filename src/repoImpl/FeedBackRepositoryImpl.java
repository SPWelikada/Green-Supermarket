package repoImpl;

import entity.FeedBack;
import repo.CustomerRepository;
import repo.FeedBackRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public class FeedBackRepositoryImpl implements FeedBackRepository {
    private static final String INSERT_FEEDBACK_QUERY = "INSERT INTO feedback (id, customerId, message, rating) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_FEEDBACK_QUERY = "UPDATE feedback SET customerId = ?, message = ?, rating = ? WHERE id = ?";
    private static final String DELETE_FEEDBACK_QUERY = "DELETE FROM feedback WHERE id = ?";
    private static final String GET_ALL_FEEDBACKS_QUERY = "SELECT * FROM feedback";
    private static final String GET_FEEDBACKS_BY_CUSTOMER_ID_QUERY = "SELECT * FROM feedback WHERE customerId = ?";


    private CustomerRepository customerRepository;

    public FeedBackRepositoryImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public FeedBackRepositoryImpl() {

    }


    @Override
    public void addFeedBack(FeedBack feedBack) {
        if (customerRepository.customerExists(feedBack.getCustomerId())) {
            try (Connection connection = util.DBConnection.getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_FEEDBACK_QUERY)) {
                preparedStatement.setInt(1, feedBack.getId());
                preparedStatement.setInt(2, feedBack.getCustomerId());
                preparedStatement.setString(3, feedBack.getMessage());
                preparedStatement.setInt(4, feedBack.getRating());
                preparedStatement.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.out.println("Customer does not exist. Cannot add feedback.");
        }
    }

    @Override
    public void updateFeedBack(FeedBack feedBack) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_FEEDBACK_QUERY)) {
            preparedStatement.setInt(1, feedBack.getCustomerId());
            preparedStatement.setString(2, feedBack.getMessage());
            preparedStatement.setInt(3, feedBack.getRating());
            preparedStatement.setInt(4, feedBack.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void deleteFeedBack(int feedbackId) {
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FEEDBACK_QUERY)) {
            preparedStatement.setInt(1, feedbackId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<FeedBack> getAllFeedbacks() {
        List<FeedBack> feedbacks = new ArrayList<>();
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_FEEDBACKS_QUERY);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                feedbacks.add(extractFeedbackFromResultSet(resultSet));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return feedbacks;
    }

    @Override
    public List<FeedBack> getFeedbacksByCustomerId(int customerId) {
        List<FeedBack> feedbacks = new ArrayList<>();
        try (Connection connection = util.DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(GET_FEEDBACKS_BY_CUSTOMER_ID_QUERY)) {
            preparedStatement.setInt(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    feedbacks.add(extractFeedbackFromResultSet(resultSet));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return feedbacks;
    }

    private FeedBack extractFeedbackFromResultSet(ResultSet resultSet) throws SQLException {
        return new FeedBack(
                resultSet.getInt("id"),
                resultSet.getInt("customerId"),
                resultSet.getString("message"),
                resultSet.getInt("rating")
        );
    }
}