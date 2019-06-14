package com.itc.robot.hover;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.itc.robot.hover.controller.RoboticHoverController;
import com.itc.robot.hover.rest.model.JSONRequestModel;
import com.itc.robot.hover.rest.model.JSONResponceModel;

@Path("/robotichover")
public class RoboticHoverService {
	@POST
	@Path("/cleanroom")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response cleanRoom(InputStream incomingData) {
		StringBuilder crunchifyBuilder = new StringBuilder();

		try {
			BufferedReader in = new BufferedReader(new InputStreamReader(incomingData));
			String line = null;
			while ((line = in.readLine()) != null) {
				crunchifyBuilder.append(line);
			}
		} catch (Exception e) {
			System.out.println("Error Parsing: - ");
		}
		System.out.println("Data Received: " + crunchifyBuilder.toString());

		JSONRequestModel jsonRequestModel = new Gson().fromJson(crunchifyBuilder.toString(), JSONRequestModel.class);
		RoboticHoverController roboticHoverController = new RoboticHoverController();

		try {
			JSONResponceModel jsonResponceModel = roboticHoverController.cleanRoom(jsonRequestModel);
			String result = new Gson().toJson(jsonResponceModel);

			return Response.status(200).entity(result).build();
		} catch (IllegalArgumentException e) {
			return Response.status(422).entity("Invalid request data").build();
		}
	}
}