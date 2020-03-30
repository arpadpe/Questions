package test;

import data.FileHandler;
import logic.QuestionsHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.HashMap;
import java.util.List;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FileHandler.class)
public class QuestionsHandlerTest {

    private QuestionsHandler questionsHandler;
    private String expectedDefaultAnswer = "the answer to life, universe and everything is 42";

    @Before
    public void init() {
        questionsHandler = new QuestionsHandler();
        PowerMockito.mockStatic(FileHandler.class);
        PowerMockito.when(FileHandler.loadQuestionsAndAnswers()).thenReturn(new HashMap<>());
    }

    @Test
    public void askQuestion_defaultAnswerTest() {

        String newQuestion = "new unadded question?";
        List<String> answers = questionsHandler.getAnswers(newQuestion);

        Assert.assertNotNull(answers);
        Assert.assertEquals(1, answers.size());
        Assert.assertEquals(expectedDefaultAnswer, answers.get(0));
    }

    @Test
    public void askQuestion_exactQuestionTest() {

        String question = "What are Peter's favourite drinks?";
        String answerCola = "Coca-cola";
        String answerFanta = "Fanta";

        boolean result = questionsHandler.addQuestion(String.format("%s \"%s\" \"%s\"", question, answerCola, answerFanta));

        Assert.assertTrue(result);

        // Ask exact question
        List<String> answers = questionsHandler.getAnswers(question);

        Assert.assertNotNull(answers);
        Assert.assertEquals(2, answers.size());
        Assert.assertEquals(answerCola, answers.get(0));
        Assert.assertEquals(answerFanta, answers.get(1));

        // Ask almost exact question
        String newQuestion = "what are Peter's favourite drinks?";
        answers = questionsHandler.getAnswers(newQuestion);

        Assert.assertNotNull(answers);
        Assert.assertEquals(1, answers.size());
        Assert.assertEquals(expectedDefaultAnswer, answers.get(0));
    }

    @Test
    public void addQuestion_addQuestionTest() {

        String question = "What are Peter's favourite drinks?";
        String answerCola = "Coca-cola";
        String answerFanta = "Fanta";

        boolean result = questionsHandler.addQuestion(String.format("%s \"%s\" \"%s\"", question, answerCola, answerFanta));

        Assert.assertTrue(result);

        // Ask added question
        List<String> answers = questionsHandler.getAnswers(question);

        Assert.assertNotNull(answers);
        Assert.assertEquals(2, answers.size());
        Assert.assertEquals(answerCola, answers.get(0));
        Assert.assertEquals(answerFanta, answers.get(1));

        // Ask not added question
        String newQuestion = "new unadded question?";
        answers = questionsHandler.getAnswers(newQuestion);

        Assert.assertNotNull(answers);
        Assert.assertEquals(1, answers.size());
        Assert.assertSame(expectedDefaultAnswer, answers.get(0));
    }

    @Test
    public void addQuestion_addSameQuestionTest() {

        String question = "What are Peter's favourite drinks?";
        String answerCola = "Coca-cola";
        String answerFanta = "Fanta";

        boolean result = questionsHandler.addQuestion(String.format("%s \"%s\" \"%s\"", question, answerCola, answerFanta));

        Assert.assertTrue(result);

        List<String> answers = questionsHandler.getAnswers(question);

        Assert.assertNotNull(answers);
        Assert.assertEquals(2, answers.size());
        Assert.assertEquals(answerCola, answers.get(0));
        Assert.assertEquals(answerFanta, answers.get(1));

        // Add same question again
        result = questionsHandler.addQuestion(String.format("%s \"%s\" \"%s\"", question, answerCola, answerFanta));

        Assert.assertFalse(result);
    }

    @Test
    public void addQuestion_incorrectFormatTest() {

        String question = "What are Peter's favourite drinks?";
        String answerCola = "Coca-cola";

        // Try adding question without answers
        boolean result = questionsHandler.addQuestion(question);

        Assert.assertFalse(result);

        // Try adding answer before question
        result = questionsHandler.addQuestion(String.format("\"%s\" %s", answerCola, question));

        Assert.assertFalse(result);

    }

}
