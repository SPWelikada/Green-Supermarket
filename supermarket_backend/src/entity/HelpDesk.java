package entity;


public class HelpDesk {
    private int id;
    private String FAQs;
    private String Questions;
    private String answers;
    private String guidance;

    public HelpDesk() {
    }

    public HelpDesk(int id, String FAQs, String questions, String answers, String guidance) {
        this.id = id;
        this.FAQs = FAQs;
        Questions = questions;
        this.answers = answers;
        this.guidance = guidance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFAQs() {
        return FAQs;
    }

    public void setFAQs(String FAQs) {
        this.FAQs = FAQs;
    }

    public String getQuestions() {
        return Questions;
    }

    public void setQuestions(String questions) {
        Questions = questions;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getGuidance() {
        return guidance;
    }

    public void setGuidance(String guidance) {
        this.guidance = guidance;
    }

    @Override
    public String toString() {
        return "HelpDesk{" +
                "id=" + id +
                ", FAQs='" + FAQs + '\'' +
                ", Questions='" + Questions + '\'' +
                ", answers='" + answers + '\'' +
                ", guidance='" + guidance + '\'' +
                '}';
    }
}
