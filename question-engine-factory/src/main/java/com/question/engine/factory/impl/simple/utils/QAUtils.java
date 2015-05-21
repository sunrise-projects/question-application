package com.question.engine.factory.impl.simple.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Map.Entry;

import com.question.engine.factory.impl.simple.dao.ContextDAO;
import com.question.engine.factory.impl.simple.dao.QuestionDAO;
import com.question.engine.factory.impl.simple.dao.SessionDAO;
import com.question.engine.factory.impl.simple.model.QuestionBucket;

public class QAUtils {

	@SuppressWarnings("resource")
	public static String showQuestionHelper(QuestionBucket bucket, boolean showAnswer) {
		Scanner scanIn = null;
		System.out.println("QuestionType: "+bucket.getQuestionType());
		System.out.println("Question: "+bucket.getQuestion());
		if(showAnswer) {
			System.out.println("View selections below:");
			Map<String, String> m11 = new TreeMap<String, String>(bucket.getSelection());
			Iterator<Entry<String, String>> i1 = m11.entrySet().iterator();
			while(i1.hasNext()) {
				Entry<String, String> e1 = i1.next();
				System.out.println(e1.getKey()+"="+e1.getValue());			
			}
			System.out.println("Answer: "+bucket.getAnswer());
			System.out.println("Explanation: "+ bucket.getExplanation()==null ? "NA":bucket.getExplanation());					
			return null;
		} else {
			System.out.println("Select answer below:");
			Map<String, String> m1 = bucket.getSelection();
			List<String> keys = QAUtils.shuffle(m1);
			Map<Integer, String> mapAnswer = new HashMap<Integer, String>();
			int mapAnswerCount = 1;
			for (Object o : keys) {
				mapAnswer.put(mapAnswerCount, o.toString());
				System.out.println(mapAnswerCount + "=" + m1.get(o));
				mapAnswerCount++;
			}
			System.out.print("Answer ? : ");
			scanIn = new Scanner(System.in);
			String answerInt = scanIn.nextLine();		
			
			String answer = "";
			if(answerInt.contains(",")) {
				String[] ans = answerInt.split(",");
				int s = ans.length-1;
				int c = 0;
				for(String a : ans) {
					answer = answer + mapAnswer.get(Integer.valueOf(a));
					if(c < s ) {
						answer = answer + ",";
					}
					c++;
				}
			} else {
				answer = mapAnswer.get(Integer.valueOf(answerInt));
			}
			
			
			System.out.println("Your answer is: "+answerInt + " = " +answer);	
			return answer;				
		}		
	}

	public static List<String>  shuffle(Map<String,String> map) {
		Map<String,String> out = new HashMap<String,String>();
		List<String> keys = new ArrayList<String>(map.keySet());
		Collections.shuffle(keys);	
		return keys;
		
	}
	
	public static void evaluteQuestionSet(SessionDAO session, List<QuestionDAO> questions, ContextDAO application ) {
		List<Integer> questionIndexes = new ArrayList<Integer>();
		for ( int i=0; i<questions.size(); i++ ) {
			QuestionDAO  qandaQuestion = questions.get(i); 
			//question times to be asked if answered correctly
			if(qandaQuestion.getCorrectAnswerCount() < 2) questionIndexes.add(i);
		}
		
		if(questionIndexes.isEmpty()) {
			application.setStatus(ContextDAO.NO_QUESTION_REMAINING);
		} else {
			application.setStatus(ContextDAO.QUESTION_AVAILABLE);
			Collections.shuffle(questionIndexes);
			
			LinkedList<Integer> questionIndexesLimited = new LinkedList<Integer>();
			//this happens if question indexes is greater than question set 
			if ( questionIndexes.size() < session.getQuestionSetTotalValue() ) {
				//1 to 7
				//questionIndexesLimited = questionIndexes.subList(0, questionIndexes.size());
				for (int i=0;i<questionIndexes.size();i++) {
					questionIndexesLimited.add(questionIndexes.get(i));
				}
				
				
			} else {
				//get question set using the cutoff. i.e. 8
				//questionIndexesLimited = questionIndexes.subList(0, session.getQuestionSetTotalValue());
				for (int i=0;i<session.getQuestionSetTotalValue();i++) {
					questionIndexesLimited.add(questionIndexes.get(i));
				}
			}
			

			session.setQuestionSet(questionIndexesLimited);
			
			int index = session.getQuestionSetRunningValue()-1; //because this is zero based.
			application.setQandaQuestion(session.getQuestions().get(questionIndexesLimited.get(index)));
			session.setQuestionSessionNumber(session.getQuestions().get(questionIndexesLimited.get(index)).getQuestionNumber());
			session.setTotalQuestionRunningValue(session.getTotalQuestionRunningValue()+1);
			//session.setQuestionSetRunningValue(session.getQuestionSetRunningValue()+1);

		
		}
		
		
	}
	
}
