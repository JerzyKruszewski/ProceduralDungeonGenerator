package me.jurij.ProceduralDungeonGenerator.Objects;

import java.util.*;

public class Room
{
    private int minRoomWidth = 4;
    private int minRoomHeight = 3;
    private int maxRoomWidth = 7;
    private int maxRoomHeight = 5;
    private char roomIndicator = '.';

    private Integer roomWidth;
    private Integer roomHeight;
    private Point anchorPoint;

    private Dungeon dungeon;
    private Random random;

    private Wall[] walls = new Wall[4];
    private List<Corridor> corridors = new LinkedList<Corridor>();

    public Room(Point anchorPoint, Dungeon dungeon, Random random)
    {
        this.anchorPoint = anchorPoint;
        this.dungeon = dungeon;
        this.random = random;
    }

    private void checkAndInitializeCords()
    {
        int x = anchorPoint.getX();
        int y = anchorPoint.getY();

        //check if room will fit inside a dungeon
        if (x < 0  || x >= dungeon.getWidth() - maxRoomWidth)
        {
            anchorPoint.setX(random.nextInt(dungeon.getWidth() - maxRoomWidth));
        }

        if (y < 0 || y >= dungeon.getHeight() - maxRoomHeight)
        {
            anchorPoint.setY(random.nextInt(dungeon.getHeight() - maxRoomHeight));
        }
    }

    public char[][] createRoom()
    {
        checkAndInitializeCords();

        //create random size of the room
        this.roomWidth = random.nextInt(maxRoomWidth - minRoomWidth + 1) + minRoomWidth;
        this.roomHeight = random.nextInt(maxRoomHeight - minRoomHeight + 1) + minRoomHeight;

        char[][] map = dungeon.getDungeonMap();

        walls[0] = new Wall(false, roomWidth, anchorPoint);
        walls[1] = new Wall(true, roomHeight, anchorPoint);
        walls[2] = new Wall(false, roomWidth + 1, new Point(anchorPoint.getX(), anchorPoint.getY() + roomHeight)); //I don't know why +1, but without it it can break.
        walls[3] = new Wall(true, roomHeight, new Point(anchorPoint.getX() + roomWidth, anchorPoint.getY()));

        //add room to map
        for (int y = anchorPoint.getY(); y < anchorPoint.getY() + roomHeight; y++)
        {
            for (int x = anchorPoint.getX(); x < anchorPoint.getX() + roomWidth; x++)
            {
                map[y][x] = roomIndicator;
            }
        }

        for (Wall wall : walls)
        {
            wall.drawWall(map);
        }

        return map;
    }
}
