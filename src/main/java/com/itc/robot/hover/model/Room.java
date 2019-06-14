package com.itc.robot.hover.model;

import java.util.HashSet;
import java.util.Set;

public class Room {
    private final int numberOfColumns;
    private final int numberOfRows;
    private final int numberOfPositions;
    
    private int numberOfPositionsCleaned = 0;
    private Set<Position> dirtPositions;
    private Position robotPosition;

    public Room(Position roomDimension, Position robotPosition) {

        if(!isValidPosition(roomDimension)){

        }

        this.numberOfColumns = roomDimension.getColumnNumber();
        this.numberOfRows = roomDimension.getRowNumber();
        numberOfPositions = numberOfColumns * numberOfRows;
        dirtPositions = new HashSet<Position>();
        this.robotPosition = robotPosition;
    }

    public int getNumberOfColumns(){
        return numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOfPositions()
    {
        return numberOfPositions;
    }

    public void addDirtPosition(Position position){       
        dirtPositions.add(position);
    }

    public void cleanPosition(Position position){
        if(dirtPositions.contains(position)) {
        	numberOfPositionsCleaned += 1;
        	removeDirtPosition(position);
        }
    }
    
    private void removeDirtPosition(Position position){
        dirtPositions.remove(position);
    }
    
    public int getumberOfPositionsCleaned()
    {
    	return numberOfPositionsCleaned;
    }

    public boolean isValidPosition(Position position){

        int rowNumber = position.getRowNumber();
        int columnNumber = position.getColumnNumber();

        if(rowNumber >0 && rowNumber <= numberOfRows && columnNumber > 0 && columnNumber <= numberOfColumns)
        {
            return  true;
        }

        return false;
    }
    
    public Position getCurrentRobotPosition() {
    	return robotPosition;		
	}
    
    public void moveRobotAsPerInstructions(char[] instructions) {
    	for(char instruction:instructions) {
    		 moveRobotAndCleanPosition(instruction);
    	}
    }
    
private void moveRobotAndCleanPosition(char instruction) {
	int x = robotPosition.getColumnNumber();
	int y= robotPosition.getRowNumber();
	
		switch(instruction){
			case 'E':
				x += 1;
				break;
			case 'N':
				y += 1;
				break;
			case 'S':
				y -= 1;
				break;
			case 'W':
				x -= 1;
				break;							
		}
		
		Position nextRobotPosition = new Position(x, y);
		
		robotPosition = isValidPosition(nextRobotPosition) ? nextRobotPosition : robotPosition;
		cleanPosition(robotPosition);
	}
}
