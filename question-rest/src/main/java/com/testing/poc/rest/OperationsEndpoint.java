package com.testing.poc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.testing.poc.model.OperationInput;
import com.testing.poc.model.OperationOutput;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

/**
 * REST endpoint for user manipulation.
 */
@Api(value = "operations", description = "Endpoint for operations management")
@Path("/operations")
public class OperationsEndpoint {

    @Context
    private UriInfo uriInfo;

    /**
     * Creates and registers user in application.
     */
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(value = "Trigger add operation", notes = "Trigger add operation.", response = OperationInput.class)
    @ApiResponses(value = { 
    		@ApiResponse(code = 201, message = "Successful registration of new user", response = OperationOutput.class), 
    		@ApiResponse(code = 406, message = "Malformed definition"),
            @ApiResponse(code = 409, message = "Already exists"), 
            @ApiResponse(code = 500, message = "Internal server error") })
    public Response add(@ApiParam(name = "operationInput", required = true) OperationInput operationInput) {
        try {
        	
            final OperationOutput operationOutput = new OperationOutput();
            
            int result = operationInput.getFirstNumber() + operationInput.getSecondNumber();
            operationOutput.setResponseCode(1);
            operationOutput.setResponseDesc("Operation Done.");
            operationOutput.setResult(result);
        	
            return Response.status(Status.CREATED).entity(operationOutput).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Status.NOT_ACCEPTABLE).entity(e.getMessage()).build();
        }
    }



}
