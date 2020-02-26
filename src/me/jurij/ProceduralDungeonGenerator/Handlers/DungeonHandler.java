package me.jurij.ProceduralDungeonGenerator.Handlers;

/*
1. Fill the whole map with solid earth
2. Dig out a single room in the centre of the map
3. Pick a wall of any room
4. Decide upon a new feature to build
5. See if there is room to add the new feature through the chosen wall
6. If yes, continue. If no, go back to step 3
7. Add the feature through the chosen wall
8. Go back to step 3, until the dungeon is complete
9. Add the up and down staircases at random points in map
10. Finally, sprinkle some monsters and items liberally over dungeon

From: http://roguebasin.roguelikedevelopment.org/index.php?title=Dungeon-Building_Algorithm
 */

import me.jurij.ProceduralDungeonGenerator.Objects.Point;

import java.util.Random;

public class DungeonHandler
{
    private Integer seed = null;
    private int width;
    private int height;
    private int minRoomWidth = 5;
    private int minRoomHeight = 5;
    private int maxRoomWidth = 5;
    private int maxRoomHeight = 5;
    private Random random;
    private char[][] dungeon;

    public DungeonHandler(int width, int height)
    {
        this.width = width * 16;
        this.height = height * 16;
    }

    public DungeonHandler(int width, int height, int seed)
    {
        this(width, height);
        this.seed = seed;
    }

    public char[][] generateDungeon()
    {
        initRandom();

        //1.
        initDungeon();

        //2.
        createRoom(new Point(width/2, height/2));

        return dungeon;
    }

    private void initRandom()
    {
        if (seed != null)
        {
            this.random = new Random(seed);
        }
        else
        {
            this.random = new Random();
        }
    }

    private char[][] initDungeon()
    {
        dungeon = new char[height][width];

        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                dungeon[y][x] = 'X';
            }
        }

        return dungeon;
    }

    private char[][] createRoom(Point cordinates)
    {
        checkAndInitializeCords(cordinates);

        int roomWidth = random.nextInt(maxRoomWidth - minRoomWidth + 1) + minRoomWidth;
        int roomHeight = random.nextInt(maxRoomHeight - minRoomHeight + 1) + minRoomHeight;

        for (int y = cordinates.getY(); y < cordinates.getY() + roomHeight; y++)
        {
            for (int x = cordinates.getX(); x < cordinates.getX() + roomWidth; x++)
            {
                dungeon[y][x] = '.';
            }
        }

        return dungeon;
    }

    private char[][] createRoom()
    {
        return createRoom(new Point(-1, -1));
    }

    private void checkAndInitializeCords(Point cordinates)
    {
        int x = cordinates.getX();
        int y = cordinates.getY();

        if (x < 0  || x >= width - maxRoomWidth)
        {
            cordinates.setX(random.nextInt(width - maxRoomWidth));
        }

        if (y < 0 || y >= height - maxRoomHeight)
        {
            cordinates.setY(random.nextInt(height - maxRoomHeight/2));
        }
    }
}
