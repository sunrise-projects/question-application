package com.testing.poc.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.questionfactory.core.simple.model.QuestionBucket;
import com.questionfactory.core.simple.model.QuestionBucketStatus;
import com.testing.poc.model.OperationInput;
import com.testing.poc.model.OperationOutput;
import com.testing.poc.model.QuestionBucketDetailsModel;
import com.testing.poc.model.QuestionBucketModel;
import com.testing.poc.service.QuestionService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST endpoint for user manipulation.
 * http://www.mkyong.com/webservices/jax-rs/jax-rs-pathparam-example/
 */
@Api(value = "questions", description = "Endpoint for questions management")
@Path("/questions")
public class QuestionsEndpoint {

    @Context
    private UriInfo uriInfo;
    
    @Inject
    private QuestionService questionService;

    /**
     * Creates and registers user in application.
     */
    @GET
    @Path("/getFirstQuestion/{memberNumber}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Trigger getFirstQuestion operation", notes = "Trigger getFirstQuestion operation.")
    @ApiResponses(value = { 
    		@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class), 
    		@ApiResponse(code = 406, message = "Malformed definition"),
            @ApiResponse(code = 409, message = "Already exists"), 
            @ApiResponse(code = 500, message = "Internal server error") })
    public Response getFirstQuestion( @PathParam("memberNumber") String memberNumber) {
        try {
        	
        	
        	QuestionBucket questionBucket = questionService.getFirstQuestion("", memberNumber);
        	
        	System.out.println(questionBucket.toString());
    	    System.out.println(questionBucket.getStatus());
    	    
            return Response.status(Status.CREATED).entity(toModel(questionBucket)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }

    /**
     * Creates and registers user in application.
     */
    @GET
    @Path("/getNextQuestion/{memberNumber}/{sessionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Trigger getNextQuestion operation", notes = "Trigger getNextQuestion operation.")
    @ApiResponses(value = { 
    		@ApiResponse(code = 201, message = "Successful registration of new user", response = QuestionBucketModel.class), 
    		@ApiResponse(code = 406, message = "Malformed definition"),
            @ApiResponse(code = 409, message = "Already exists"), 
            @ApiResponse(code = 500, message = "Internal server error") })
    public Response getNextQuestion(@PathParam("memberNumber") String memberNumber,
    		@PathParam("sessionId") String sessionId) {
        try {
        	
        	
        	QuestionBucket questionBucket = questionService.getNextQuestion(memberNumber, sessionId);
        	
        	System.out.println(questionBucket.toString());
    	    System.out.println(questionBucket.getStatus());
    	    
        	
            return Response.status(Status.CREATED).entity(toModel(questionBucket)).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }

    private QuestionBucketModel toModel(QuestionBucket questionBucket) {
    	QuestionBucketModel model = new QuestionBucketModel();
	    model.setAnswer(questionBucket.getAnswer());
	    model.setExplanation(questionBucket.getExplanation());
	    model.setQuestion(questionBucket.getQuestion());
	    QuestionBucketDetailsModel questionBucketDetailsModel = new QuestionBucketDetailsModel();
	    questionBucketDetailsModel.setNumberOfSetsDone(questionBucket.getQuestionBucketDetails().getNumberOfSetsDone());
	    questionBucketDetailsModel.setQuestionNumber(questionBucket.getQuestionBucketDetails().getQuestionNumber());
	    questionBucketDetailsModel.setQuestionSetRunningValue(questionBucket.getQuestionBucketDetails().getQuestionSetRunningValue());
	    questionBucketDetailsModel.setQuestionSetTotalValue(questionBucket.getQuestionBucketDetails().getQuestionSetTotalValue());
	    questionBucketDetailsModel.setTotalQuestion(questionBucket.getQuestionBucketDetails().getTotalQuestion());
	    questionBucketDetailsModel.setTotalQuestionRunningValue(questionBucket.getQuestionBucketDetails().getTotalQuestionRunningValue());
	    model.setQuestionBucketDetails(questionBucketDetailsModel);
	    model.setQuestionNumber(questionBucket.getQuestionNumber());
	    model.setSelection(questionBucket.getSelection());
	    model.setSessionId(questionBucket.getSessionId());
	    if(questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_AVAILABLE)) {
	    	model.setStatus(QuestionBucketStatus.QUESTION_AVAILABLE);
	    } else if(questionBucket.getStatus().equals(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED)) {
	    	model.setStatus(QuestionBucketStatus.QUESTION_SET_TOTAL_REACHED);
	    } else if(questionBucket.getStatus().equals(QuestionBucketStatus.WRONG_ANSWER_FOUND)) {
	    	model.setStatus(QuestionBucketStatus.WRONG_ANSWER_FOUND);
	    }
	    return model;
    }

}
