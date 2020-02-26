package me.jurij.ProceduralDungeonGenerator.Objects;

import java.util.*;

public class Room
{
    private int minRoomWidth = 5;
    private int minRoomHeight = 5;
    private int maxRoomWidth = 5;
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
        int x = this.anchorPoint.getX();
        int y = this.anchorPoint.getY();

        if (x < 0  || x >= dungeon.getWidth() - maxRoomWidth)
        {
            this.anchorPoint.setX(random.nextInt(dungeon.getWidth() - maxRoomWidth));
        }

        if (y < 0 || y >= dungeon.getHeight() - maxRoomHeight)
        {
            this.anchorPoint.setY(random.nextInt(dungeon.getHeight() - maxRoomHeight/2));
        }
    }

    public char[][] createRoom()
    {
        checkAndInitializeCords();

        this.roomWidth = random.nextInt(maxRoomWidth - minRoomWidth + 1) + minRoomWidth;
        this.roomHeight = random.nextInt(maxRoomHeight - minRoomHeight + 1) + minRoomHeight;

        char[][] map = dungeon.getDungeonMap();

        for (int y = this.anchorPoint.getY(); y < this.anchorPoint.getY() + this.roomHeight; y++)
        {
            for (int x = this.anchorPoint.getX(); x < this.anchorPoint.getX() + this.roomWidth; x++)
            {
                map[y][x] = this.roomIndicator;
            }
        }

        return map;
    }
}
