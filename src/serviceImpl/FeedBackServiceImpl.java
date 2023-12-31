package serviceImpl;
import entity.FeedBack;
import repo.CustomerRepository;
import repo.FeedBackRepository;
import service.FeedBackService;

import java.sql.SQLException;
import java.util.List;
/**
 * @author Dulanjana Lakshan Kumarasingha
 */
public class FeedBackServiceImpl implements FeedBackService {
    private final FeedBackRepository feedBackRepository;
    private final CustomerRepository customerRepository;

    public FeedBackServiceImpl(FeedBackRepository feedBackRepository, CustomerRepository customerRepository) {
        this.feedBackRepository = feedBackRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void addFeedBack(FeedBack feedBack) throws SQLException {
        if (customerRepository.customerExists(feedBack.getCustomerId())) {
            feedBackRepository.addFeedBack(feedBack);
        } else {
            System.out.println("Customer does not exist. Cannot add feedback.");
        }
    }

    @Override
    public void updateFeedBack(FeedBack feedBack) throws SQLException {
        feedBackRepository.updateFeedBack(feedBack);
    }

    @Override
    public void deleteFeedBack(int feedbackId) throws SQLException {
        feedBackRepository.deleteFeedBack(feedbackId);
    }

    @Override
    public List<FeedBack> getAllFeedbacks() throws SQLException {
        return feedBackRepository.getAllFeedbacks();
    }

    @Override
    public List<FeedBack> getFeedbacksByCustomerId(int customerId) throws SQLException {
        return feedBackRepository.getFeedbacksByCustomerId(customerId);
    }
}