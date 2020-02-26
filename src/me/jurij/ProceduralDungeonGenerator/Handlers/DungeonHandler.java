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

import me.jurij.ProceduralDungeonGenerator.Objects.Dungeon;
import me.jurij.ProceduralDungeonGenerator.Objects.Point;
import me.jurij.ProceduralDungeonGenerator.Objects.Room;

import java.util.Random;

public class DungeonHandler
{
    private Random random;
    private Integer seed = null;
    private Dungeon dungeon;

    public DungeonHandler(int width, int height, int numberOfRooms)
    {
        this.dungeon = new Dungeon(width, height, numberOfRooms);
    }

    public DungeonHandler(int width, int height, int numberOfRooms, int seed)
    {
        this(width, height, numberOfRooms);
        this.seed = seed;
    }

    public char[][] generateDungeon()
    {
        char[][] map;

        initRandom();

        //2.
        map = addRoom(new Point(dungeon.getWidth()/2, dungeon.getHeight()/2));

        dungeon.setDungeonMap(map);
        return dungeon.getDungeonMap();
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

    private char[][] addRoom(Point cordinates)
    {
        Room room = new Room(cordinates, dungeon, random);

        return room.createRoom();
    }

    private char[][] addRoom()
    {
        return addRoom(new Point(-1, -1));
    }
}
