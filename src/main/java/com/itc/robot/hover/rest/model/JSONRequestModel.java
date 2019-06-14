package com.itc.robot.hover.rest.model;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.itc.robot.hover.model.Position;

public class JSONRequestModel {

	@SerializedName("roomSize")
	@Expose
	private List<Integer> roomSize = null;
	@SerializedName("coords")
	@Expose
	private List<Integer> coords = null;
	@SerializedName("patches")
	@Expose
	private List<List<Integer>> patches = null;
	@SerializedName("instructions")
	@Expose
	private String instructions;

	private Position roomDimension;
	private Position initialRobotPosition;
	private List<Position> dirtPositions;

	public List<Integer> getRoomSize() {
		return roomSize;
	}

	public Position getRoomDimension() {
		return roomDimension;
	}

	public Position getInitialRobotPosition() {
		return initialRobotPosition;
	}

	public List<Position> getDirtPositions() {
		return dirtPositions;
	}

	public void setRoomSize(List<Integer> roomSize) {
		this.roomSize = roomSize;
	}

	public List<Integer> getCoords() {
		return coords;
	}

	public void setCoords(List<Integer> coords) {
		this.coords = coords;
	}

	public List<List<Integer>> getPatches() {
		return patches;
	}

	public void setPatches(List<List<Integer>> patches) {
		this.patches = patches;
	}

	public String getInstructions() {
		return instructions;
	}

	public void setInstructions(String instructions) {
		this.instructions = instructions;
	}

	public boolean isValidRequest() {
		if (roomSize == null || coords == null || patches == null || instructions == null) {
			return false;
		}

		int roomX = 0;
		int roomY = 0;

		// validate room dimension and load room dimension
		if (roomSize.size() == 2) {

			roomX = roomSize.get(0);
			roomY = roomSize.get(1);

			if (roomX < 0 || roomY < 0) {
				return false;
			} else {
				roomDimension = new Position(roomX, roomY);
			}
		} else {
			return false;
		}

		// validate cords and load initial robot position
		if (coords.size() == 2) {
			int coordsX = coords.get(0);
			int coordsY = coords.get(1);

			if (coordsX < 0 || coordsY < 0 || coordsX > roomX || coordsY > roomY) {
				return false;
			} else {
				initialRobotPosition = new Position(coordsX, coordsY);
			}
		} else {
			return false;
		}

		
		// Validate patches and load dirt positions
		dirtPositions = new ArrayList<Position>();
		for (List<Integer> patch : patches) {
			if (patch.size() == 2) {
				int patchX = patch.get(0);
				int patchY = patch.get(1);

				if (patchX < 0 || patchY < 0 || patchX > roomX || patchY > roomY) {
					return false;
				} else {
					Position dirtPosition = new Position(patchX, patchY);
					dirtPositions.add(dirtPosition);
				}
			} else {
				return false;
			}
		}

		// Validate instructions
		if (!instructions.replace("E", "").replace("N", "").replace("W", "").replace("S", "").isEmpty()) {
			return false;
		}

		return true;
	}
}
