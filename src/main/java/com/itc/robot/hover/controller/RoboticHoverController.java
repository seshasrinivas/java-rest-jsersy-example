package com.itc.robot.hover.controller;

import java.util.ArrayList;
import java.util.List;

import com.itc.robot.hover.model.Position;
import com.itc.robot.hover.model.Room;
import com.itc.robot.hover.rest.model.JSONRequestModel;
import com.itc.robot.hover.rest.model.JSONResponceModel;

public class RoboticHoverController {

	public JSONResponceModel cleanRoom(JSONRequestModel jsonRequestModel) {					
		if(!jsonRequestModel.isValidRequest()) {
			return prepareResponse(false, null);
		}
		
		Room room = createRoom(jsonRequestModel);
		room.moveRobotAsPerInstructions(jsonRequestModel.getInstructions().toCharArray());
		
		return prepareResponse(true, room);
	}
	
	private Room createRoom(JSONRequestModel jsonRequestModel) {
		Room room = new Room(jsonRequestModel.getRoomDimension(), jsonRequestModel.getInitialRobotPosition());
		List<Position> dirtPositions = jsonRequestModel.getDirtPositions();
		
		for(Position position : dirtPositions) {
			room.addDirtPosition(position);
		}
		
		return room;
	}
	
	private JSONResponceModel prepareResponse(boolean status,Room room) {
		JSONResponceModel jsonResponceModel = new JSONResponceModel();
		
		if(!status) {
			throw new IllegalArgumentException("Invalid inputs");
		}
		else
		{
			if(room != null) {
				jsonResponceModel.setPatches(room.getumberOfPositionsCleaned());
				
				Position currentPosition = room.getCurrentRobotPosition();
				List<Integer> coords = new ArrayList<Integer>(2);
				coords.add(currentPosition.getColumnNumber());
				coords.add(currentPosition.getRowNumber());				
				jsonResponceModel.setCoords(coords);
			}
		}
		
		return jsonResponceModel;
	}
	
}