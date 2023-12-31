package service;

import entity.FeedBack;

import java.sql.SQLException;
import java.util.List;

/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public interface FeedBackService {
    void addFeedBack(FeedBack feedBack) throws SQLException;
    void updateFeedBack(FeedBack feedBack) throws SQLException;
    void deleteFeedBack(int feedbackId) throws SQLException;
    List<FeedBack> getAllFeedbacks() throws SQLException;
    List<FeedBack> getFeedbacksByCustomerId(int customerId) throws SQLException;
}
