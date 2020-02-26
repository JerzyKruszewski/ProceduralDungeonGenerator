package me.jurij.ProceduralDungeonGenerator;

import me.jurij.ProceduralDungeonGenerator.Handlers.DungeonHandler;

public class ProceduralDungeonGenerator
{
    public static void main(String[] args)
    {
        DungeonHandler dungeonHandler = new DungeonHandler(3, 1, 0);

        char[][] dungeon = dungeonHandler.generateDungeon();

        for (char[] line : dungeon)
        {
            System.out.println(line);
        }
    }
}
