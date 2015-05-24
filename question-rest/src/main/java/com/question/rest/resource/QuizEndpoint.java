package com.question.rest.resource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.media.multipart.FormDataParam;

import com.question.engine.factory.impl.simple.model.AnswerBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucket;
import com.question.engine.factory.impl.simple.model.QuestionBucketStatus;
import com.question.engine.factory.impl.simple.model.QuizBucket;
import com.question.engine.factory.impl.simple.model.SaveAnswerResponse;
import com.question.rest.model.AnswerBucketModel;
import com.question.rest.model.QuestionBucketDetailsModel;
import com.question.rest.model.QuestionBucketModel;
import com.question.rest.model.QuizBucketModel;
import com.question.rest.model.SaveAnswerResponseModel;
import com.question.rest.service.QuestionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST endpoint for user manipulation.
 * http://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
 */
@Api(value = "quiz", description = "Endpoint for quiz")
@Path("/quiz")
public class QuizEndpoint {

    @Context
    private UriInfo uriInfo;
    
    @Inject
    private QuestionService questionService;

    @GET
    @Path("/getQuizResult/{memberNumber}/{sessionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Trigger getWrongAnswer operation", notes = "Trigger getWrongAnswer operation.")
    @ApiResponses(value = { 
    		@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class), 
    		@ApiResponse(code = 406, message = "Malformed definition"),
            @ApiResponse(code = 409, message = "Already exists"), 
            @ApiResponse(code = 500, message = "Internal server error") })
    public Response getWrongAnswer(@PathParam("memberNumber") String memberNumber,
    		@PathParam("sessionId") String sessionId) {
        try {
        	List<QuizBucket> result = questionService.getQuizResult(memberNumber, 
    				sessionId);
            return Response.status(Status.CREATED).entity(toModel(result)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }
    
    @GET
    @Path("/saveAnswer/{answer}/{memberNumber}/{sessionId}/{questionNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Trigger saveAnswer operation", notes = "Trigger getWrongAnswer operation.")
    @ApiResponses(value = { 
    		@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class), 
    		@ApiResponse(code = 406, message = "Malformed definition"),
            @ApiResponse(code = 409, message = "Already exists"), 
            @ApiResponse(code = 500, message = "Internal server error") })
    public Response checkAnswer(
    		@PathParam("answer") String answer,
    		@PathParam("memberNumber") String memberNumber,
    		@PathParam("sessionId") String sessionId,
    		@PathParam("questionNumber") int questionNumber ) {
        try {
        	
        	SaveAnswerResponse saveAnswerResponse = questionService.saveQuizAnswer(answer, 
    				memberNumber, sessionId, questionNumber);
        	
        	SaveAnswerResponseModel model = new SaveAnswerResponseModel();
        	model.setCode(saveAnswerResponse.getCode());
        	model.setDescription(saveAnswerResponse.getDescription());
        	
            return Response.status(Status.CREATED).entity(model).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }    
    
    @PUT
    @Path("/getQuizQuestions/{memberNumber}/inputFile")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @ApiImplicitParams(@ApiImplicitParam(dataType = "file", name = "inputFile", paramType = "body"))
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Trigger getQuizQuestions operation", notes = "Trigger getFirstQuestion operation.")
    @ApiResponses(value = { 
    		@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class), 
    		@ApiResponse(code = 406, message = "Malformed definition"),
            @ApiResponse(code = 409, message = "Already exists"), 
            @ApiResponse(code = 500, message = "Internal server error") })
    public Response getFirstQuestion( @PathParam("memberNumber") String memberNumber,
    		@ApiParam(access = "hidden") @FormDataParam("inputFile") InputStream inputFile
    		) {
        try {
        	List<QuizBucket> questionBucketList = questionService.getQuizQuestions(inputFile, memberNumber);
            return Response.status(Status.CREATED).entity(toModel(questionBucketList)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }
    private List<QuizBucketModel> toModel(List<QuizBucket> questionBucketList) {
    	List<QuizBucketModel> quizBucktModelList = new ArrayList<QuizBucketModel>();
    	
    	for(QuizBucket bucket : questionBucketList) {
    		QuizBucketModel model = new QuizBucketModel();
    		model.setAnswer(bucket.getAnswer());
    		model.setCorrect(bucket.isCorrect());
    		model.setExplanation(bucket.getExplanation());
    		model.setMemberAnswer(bucket.getMemberAnswer());
    		model.setMemberNumber(bucket.getMemberNumber());
    		model.setQuestion(bucket.getQuestion());
    		model.setQuestionNumber(bucket.getQuestionNumber());
    		model.setQuestionType(bucket.getQuestionType());
    		model.setSelection(bucket.getSelection());
    		model.setSessionId(bucket.getSessionId());
    		model.setStatus(bucket.getStatus());  		
    		quizBucktModelList.add(model);
    	}
    	return quizBucktModelList;
    	
    }

}
