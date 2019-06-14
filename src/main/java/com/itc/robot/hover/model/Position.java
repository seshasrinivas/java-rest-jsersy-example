package com.itc.robot.hover.model;

/**
 * This class represents a Position in a room. Positions represents a unit in a room by using row and column number.
 * For example, if there are 5 rows and 5 columns, then there are 25 Position.
 */
public class Position{

    private final Integer rowNumber;
    private final Integer columnNumber;

    public Position(Integer columnNumber, Integer rowNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public Integer getRowNumber() { return rowNumber; }
    public Integer getColumnNumber() { return columnNumber; }

    @Override
    public int hashCode() { return rowNumber.hashCode() ^ columnNumber.hashCode(); }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Position)) return false;
        Position pairo = (Position) o;
        return this.rowNumber.equals(pairo.getRowNumber()) &&
                this.columnNumber.equals(pairo.getColumnNumber());
    }

}