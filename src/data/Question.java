package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Question object to save question with answers in xml.
 *
 */
@XmlRootElement(name = "question")
@XmlAccessorType(XmlAccessType.FIELD)
public class Question {

    private String question;
    private List<String> answers;

    public Question(){
        super();
        this.answers = new ArrayList<>();
    }

    public Question(String question, List<String> answers) {
        super();
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }
}
