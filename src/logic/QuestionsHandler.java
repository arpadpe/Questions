package logic;

import data.FileHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class QuestionsHandler {

    private Map<String, List<String>> questionsAndAnswers;
    private final List<String> defaultAnswer = new ArrayList<>(Collections.singleton("the answer to life, universe and everything is 42"));
    private final String questionsAndAnswersRegex = "(.*)? (\".*\")+";
    private final Pattern answersPattern = Pattern.compile("\"(.*?)\"");

    public QuestionsHandler() {
        questionsAndAnswers = FileHandler.loadQuestionsAndAnswers();
    }

    public QuestionsHandler(Map<String, List<String>> questions) {
        this.questionsAndAnswers = questions;
    }

    /**
     * Get answers for the question.
     * Default to the default answer in case it is an unknown question.
     *
     * Returns the list of answers.
     *
     * @param question
     * @return
     */
    public List<String> getAnswers(String question) {
        if (questionsAndAnswers.containsKey(question)) {
            return questionsAndAnswers.get(question);
        }
        else {
            return defaultAnswer;
        }
    }

    /**
     *
     * Add a new question with the answers.
     *
     * Returns true or false, depending on the success of adding the question with answers.
     *
     * @param question
     * @param answers
     * @return
     */
    public boolean addQuestion(String question, List<String> answers) {
        try {
            if (questionsAndAnswers.containsKey(question)) {
                return false;
            }

            questionsAndAnswers.put(question, answers);
            FileHandler.saveQuestionAndAnswers(question, answers);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     *
     * Add a new question with answers in the form;
     * <question>? "<answer1>" "<answer2>" ...
     *
     * Returns true or false, depending on the success of adding the question with answers.
     *
     * @param questionAndAnswers
     * @return
     */
    public boolean addQuestion(String questionAndAnswers) {

        if (questionAndAnswers.matches(questionsAndAnswersRegex)) {
            String[] result = questionAndAnswers.split("\\?");

            String question = result[0] + "?";
            List<String> answers = new ArrayList<>();
            String answersString = result[1];

            Matcher answersMatcher = answersPattern.matcher(answersString);

            while(answersMatcher.find()) {
                String answer = answersMatcher.group().replaceAll(answersPattern.pattern(), "$1");

                answers.add(answer);
            }

            return addQuestion(question, answers);
        }

        return false;

    }

}
