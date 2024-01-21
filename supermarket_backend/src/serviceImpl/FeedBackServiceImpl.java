package serviceImpl;
import entity.FeedBack;


import repoImpl.CustomerRepositoryImpl;
import repoImpl.FeedBackRepositoryImpl;


import java.sql.SQLException;
import java.util.List;


public class FeedBackServiceImpl  {
    private final FeedBackRepositoryImpl feedBackRepository;
    private final CustomerRepositoryImpl customerRepository;

    public FeedBackServiceImpl(FeedBackRepositoryImpl feedBackRepository, CustomerRepositoryImpl customerRepository) {
        this.feedBackRepository = feedBackRepository;
        this.customerRepository = customerRepository;
    }


    public void addFeedBack(FeedBack feedBack) throws SQLException {
        if (customerRepository.customerExists(feedBack.getCustomerId())) {
            feedBackRepository.addFeedBack(feedBack);
        } else {
            System.out.println("Customer does not exist. Cannot add feedback.");
        }
    }


    public void updateFeedBack(FeedBack feedBack) throws SQLException {
        feedBackRepository.updateFeedBack(feedBack);
    }


    public void deleteFeedBack(int feedbackId) throws SQLException {
        feedBackRepository.deleteFeedBack(feedbackId);
    }

    public List<FeedBack> getAllFeedbacks() throws SQLException {
        return feedBackRepository.getAllFeedbacks();
    }


    public List<FeedBack> getFeedbacksByCustomerId(int customerId) throws SQLException {
        return feedBackRepository.getFeedbacksByCustomerId(customerId);
    }
}