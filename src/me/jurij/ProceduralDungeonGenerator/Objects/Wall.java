package me.jurij.ProceduralDungeonGenerator.Objects;

public class Wall
{
    private boolean isVertical;
    private int wallLenght;
    private Point anchorPoint;
    private boolean isOpen = false; //has door
    private Integer doorIndex = null; //where is the door according to lenght
    private char wallIndicator = '|';
    private char doorIndicator = 'D';

    public Wall(boolean isVertical, int wallLenght, Point anchorPoint)
    {
        this.isVertical = isVertical;
        this.wallLenght = wallLenght;
        this.anchorPoint = anchorPoint;
    }

    public char[][] drawWall(char[][] map)
    {
        if (isVertical)
        {
            for (int y = anchorPoint.getY(); y < anchorPoint.getY() + wallLenght; y++)
            {
                map[y][anchorPoint.getX()] = wallIndicator;
            }
        }
        else
        {
            for (int x = anchorPoint.getX(); x < anchorPoint.getX() + wallLenght; x++)
            {
                map[anchorPoint.getY()][x] = wallIndicator;
            }
        }

        return map;
    }
}
