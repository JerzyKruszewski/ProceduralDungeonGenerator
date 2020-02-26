package me.jurij.ProceduralDungeonGenerator.Objects;

public class Dungeon
{
    private int width;
    private int height;
    private int numberOfRooms;
    private char dungeonIndicator = 'X';
    private char[][] dungeonMap;

    public Dungeon (int width, int height, int noOfRooms)
    {
        this.width = width * 16;
        this.height = height * 16;
        this.numberOfRooms = noOfRooms;
        this.dungeonMap = new char[this.height][this.width];

        initDungeon();
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public char[][] getDungeonMap()
    {
        return dungeonMap;
    }

    public void setDungeonMap(char[][] map)
    {
        this.dungeonMap = map;
    }

    private void initDungeon()
    {
        for (int y = 0; y < this.height; y++)
        {
            for (int x = 0; x < this.width; x++)
            {
                this.dungeonMap[y][x] = this.dungeonIndicator;
            }
        }
    }
}
