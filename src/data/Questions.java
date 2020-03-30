package data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Questions wrapper object to save questions in xml format.
 *
 */
@XmlRootElement(name = "questions")
@XmlAccessorType(XmlAccessType.FIELD)
public class Questions {

    @XmlElement(name = "question")
    private List<Question> questions;

    public Questions() {
        super();
        questions = new ArrayList<>();
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
}
