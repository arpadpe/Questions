package data;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileHandler {

    private static final String questionsFileName = "questions.xml";
    private static File questionsFile = new File(questionsFileName);

    public FileHandler() {
        if (!questionsFile.exists()) {
            try {
                questionsFile.createNewFile();
            } catch (IOException e) {
                // Log error
            }
        }
    }

    /**
     *
     * Load saved questions from file.
     *
     * @return
     */
    public static Map<String, List<String>> loadQuestionsAndAnswers() {

        Questions questions = loadQuestionsXml();

        Map<String, List<String>> questionsMap = new HashMap<>();

        if (questions != null) {
            for (Question question : questions.getQuestions()) {
                questionsMap.put(question.getQuestion(), question.getAnswers());
            }
        }

        return questionsMap;
    }

    /**
     *
     * Save new question to file.
     *
     * @param question
     * @param answers
     */
    public static boolean saveQuestionAndAnswers(String question, List<String> answers) {

        Questions questionsObject = loadQuestionsXml();

        if (questionsObject == null) {
            questionsObject = new Questions();
        }

        Question questionObject = new Question(question, answers);

        questionsObject.getQuestions().add(questionObject);

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Questions.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            jaxbMarshaller.marshal(questionsObject, new File(questionsFileName));

        } catch (JAXBException e) {
            // Log error
            return false;
        }

        return true;
    }

    /**
     *
     * Load Questions object from xml.
     *
     * @return
     */
    private static Questions loadQuestionsXml() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Questions.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            Questions questions = (Questions) jaxbUnmarshaller.unmarshal(new File(questionsFileName));

            return questions;

        } catch (JAXBException e) {
            // Log error
        }

        return null;
    }

}
