package com.question.engine.factory.spi;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.question.engine.factory.config.SpringConfig;
import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.framework.SpringRuleEngine;
import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucketDetails;
import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.persistence.factory.spi.Persistence;

public class SimpleQuestion extends Question {

	private static Logger logger = Logger.getLogger(SimpleQuestion.class);
	
	private SpringRuleEngine startQengine = null;
	private SpringRuleEngine nextQengine = null;
	private SpringRuleEngine checkAengine = null;
	private SpringRuleEngine wrongEngine = null;
	
	private SpringRuleEngine startQuizEngine = null;
	private SpringRuleEngine saveAnswerQuizEngine = null;
	private SpringRuleEngine checkResultQuizEngine = null;
	
	public final static String BLANK = "";

	SimpleQuestion(Persistence persistence) {
		super(QuestionType.SIMPLE, persistence);
		construct();
	}

	@Override
	protected void construct() {

		System.out.println("Building simple question");

		ApplicationContext context = new AnnotationConfigApplicationContext(
				SpringConfig.class);

		//---------------- quiz logic
		
		startQuizEngine = (SpringRuleEngine) context.getBean("StartQuizProcessor");
		saveAnswerQuizEngine = (SpringRuleEngine) context.getBean("SaveAnswerQuizProcessor");
		checkResultQuizEngine = (SpringRuleEngine) context.getBean("CheckResultQuizProcessor");
		
		
		//----------------- question logic
		
		startQengine = (SpringRuleEngine) context.getBean("StartQAProcessor");

		nextQengine = (SpringRuleEngine) context
				.getBean("NextQuestionProcessor");

		checkAengine = (SpringRuleEngine) context
				.getBean("CheckAnswerProcessor");

		wrongEngine = (SpringRuleEngine) context
				.getBean("WrongAnswerProcessor");

	}
	
	@Override
	public List<QuizBucket> getQuizResult(String memberNumber, String sessionId) {
		try {
			ContextDAO application = new ContextDAO();
			application.setMemberNumber(memberNumber);
			application.setSessiondId(sessionId);
			application.setPersistence(getPersistence());
			checkResultQuizEngine.processRequest(application);
			return applicationToQuizBucketList(application, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}	

	@Override
	public SaveAnswerResponse saveQuizAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) {
		ContextDAO application = new ContextDAO();
		application.setPersistence(getPersistence());
		application.setMemberNumber(memberNumber);
		application.setSessiondId(sessionId);
		application.setQuestionSessionNumber(questionNumber);
		application.setQuestionAnswer(answer);
		try {
			saveAnswerQuizEngine.processRequest(application);
			SaveAnswerResponse response = new SaveAnswerResponse();
			response.setCode(0);
			response.setDescription("Answer saved.");
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}	
	
	@Override
	public <T> List<QuizBucket> getQuizQuestions(T input, String memberNumber) {
		try {
			ContextDAO application = new ContextDAO();
			application.setMemberNumber(memberNumber);
			application.setPersistence(getPersistence());
			if(input instanceof InputStream) {
				InputStream in = (InputStream)input;
				application.setInputFile(in);
			}
			startQuizEngine.processRequest(application);
			return applicationToQuizBucketList(application, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}	

	@Override
	public QuestionBucket getWrongAnswer(String memberNumber, String sessionId) {
		try {
			ContextDAO application = new ContextDAO();
			application.setMemberNumber(memberNumber);
			application.setSessiondId(sessionId);
			application.setPersistence(getPersistence());
			wrongEngine.processRequest(application);
			return applicationToBucket(application, true);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public <T> QuestionBucket getFirstQuestion(T input, String memberNumber) {
		try {
			ContextDAO application = new ContextDAO();
			application.setMemberNumber(memberNumber);
			application.setPersistence(getPersistence());
			if(input instanceof InputStream) {
				InputStream in = (InputStream)input;
				application.setInputFile(in);
			}
			startQengine.processRequest(application);
			return applicationToBucket(application, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public AnswerBucket checkAnswer(String answer, String memberNumber,
			String sessionId, int questionNumber) {
		ContextDAO application = new ContextDAO();
		application.setPersistence(getPersistence());
		application.setMemberNumber(memberNumber);
		application.setSessiondId(sessionId);
		application.setQuestionSessionNumber(questionNumber);
		application.setQuestionAnswer(answer);
		try {
			checkAengine.processRequest(application);
			AnswerBucket bucket = new AnswerBucket();
			bucket.setAnswer(application.getStatus().toString());
			return bucket;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	@Override
	public QuestionBucket getNextQuestion(String memberNumber, String sessionId) {
		try {
			ContextDAO application = new ContextDAO();
			application.setMemberNumber(memberNumber);
			application.setSessiondId(sessionId);
			application.setPersistence(getPersistence());
			nextQengine.processRequest(application);
			return applicationToBucket(application, false);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}

	/**
	 * Converts application to QuizBucket
	 * @param application
	 * @return
	 */
	private List<QuizBucket> applicationToQuizBucketList(ContextDAO application, boolean showAnswer) {
		
		List<QuizBucket> questionListResp = new ArrayList<QuizBucket>();
		List<QuestionDAO> questionList = application.getQandaSessionDAO().getQuestions();
		if(questionList.size()>0) {
			Collections.shuffle(questionList);
			for(QuestionDAO question : questionList ) {
				QuizBucket quizBucket = new QuizBucket();				
				quizBucket.setQuestion(question.getQuestion());
				quizBucket.setSelection(question.getSelections());
				quizBucket.setQuestionType(question.getAnswer().contains(",") ? "multi" : "single" );
				quizBucket.setQuestionNumber(question.getQuestionNumber());
				if(showAnswer) {
					quizBucket.setAnswer(question.getAnswer());
					quizBucket.setCorrect(question.isCorrect());
					quizBucket.setMemberAnswer(question.getMemberAnswer());
					quizBucket.setExplanation(question.getExplanation());
					quizBucket.setStatus(QuestionBucketStatus.QUIZ_QUESTION_ANSWERED);	
					quizBucket.setMemberAnswer(question.getMemberAnswer() == null ? "" : question.getMemberAnswer() );
				} else {
					quizBucket.setAnswer(BLANK);
					quizBucket.setCorrect(false);
					quizBucket.setMemberAnswer(BLANK);
					quizBucket.setExplanation(BLANK);
					quizBucket.setStatus(QuestionBucketStatus.QUIZ_QUESTION_INITIALIZED);					
				}
				quizBucket.setSessionId(application.getSessiondId());
				quizBucket.setMemberNumber(application.getMemberNumber());
				questionListResp.add(quizBucket);
			}			
		}
		return questionListResp;
	}
	
	private QuestionBucket applicationToBucket(ContextDAO application,
			boolean showAnswer) {
		QuestionDAO qa = application.getQandaQuestion();
		QuestionBucket bucket = new QuestionBucket();
		bucket.setQuestion(qa == null ? "" : qa.getQuestion());
		bucket.setSelection(qa == null ? new HashMap<String, String>() : qa
				.getSelections());
		bucket.setQuestionNumber(application.getQandaSessionDAO()
				.getQuestionSessionNumber());
		bucket.setSessionId(application.getSessiondId());
		bucket.setQuestionType(qa == null ? "" : qa.getQuestionType());
		if (application.getStatus().equals(ContextDAO.QUESTION_AVAILABLE)) {
			bucket.setStatus(QuestionBucketStatus.QUESTION_AVAILABLE);
		} else if (application.getStatus().equals(
				ContextDAO.QUESTION_SET_TOTAL_REACHED)) {
			bucket.setStatus(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED);
		} else if (application.getStatus()
				.equals(ContextDAO.WRONG_ANSWER_FOUND)) {
			bucket.setStatus(QuestionBucketStatus.WRONG_ANSWER_FOUND);
		}
		QuestionBucketDetails details = new QuestionBucketDetails();
		details.setQuestionNumber(application.getQandaSessionDAO()
				.getQuestionSessionNumber());
		details.setTotalQuestion((application.getQandaSessionDAO()
				.getTotalQuestionRunningValue() - 1));
		details.setTotalQuestion(application.getQandaSessionDAO()
				.getTotalQuestion());
		details.setNumberOfSetsDone(application.getQandaSessionDAO()
				.getNumberOfSetsDone());
		details.setQuestionSetRunningValue((application.getQandaSessionDAO()
				.getQuestionSetRunningValue() - 1));
		details.setQuestionSetTotalValue(application.getQandaSessionDAO()
				.getQuestionSetTotalValue());
		bucket.setQuestionBucketDetails(details);

		if (showAnswer) {
			bucket.setAnswer(qa == null ? "NA" : qa.getAnswer());
			bucket.setExplanation(qa == null ? "NA" : qa
					.getExplanation());
		}

		return bucket;
	}

}