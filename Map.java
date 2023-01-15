import java.util.ArrayList;
import java.util.Collections;

public class Map 
{
    private char[][] mapTiles; //the current maze. true tiles are empty and walkable; false tiles are walls

    private int playerX = 0, playerY = 0; //player pointer's position on the x and y axes

    /** Initializes a random maze with given size
     * 
     * @param width The width (x) of the requested maze
     * @param height The height (y) of the requested maze
     */
    public Map(int width, int height)
    {
        generateMap(width, height);
    }

    //#region Map Generation

    /** Initializes the mapTiles array with a new size. Starts the recursive depth-first search algorithm for new map generation
     * 
     * @param width The width of the new map. This value must be greater than 2, and it works best with odd numbers for aesthetics
     * @param height The height of the new map. This value must be greater than 2, and it works best with odd numbers for aesthetics
     */
    private void generateMap(int width, int height)
    {
        mapTiles = new char[height][width];
        for(int i = 0; i < mapTiles.length; i++) for(int j = 0; j < mapTiles[0].length; j++) mapTiles[i][j] = 'X';
        int row = 0, col = 0;
        while(row % 2 == 0) row = (int) (Math.random() * mapTiles.length);
        while(col % 2 == 0) col = (int) (Math.random() * mapTiles[0].length);

        mapTiles[row][col] = ' ';

        pathfind(row, col);

        mapTiles[mapTiles.length - 2][0] = ' ';
        mapTiles[1][mapTiles[0].length - 1] = '*';
        playerY = mapTiles.length - 2;
    }

    /** The recursive method for pathfinding through the grid to create a new maze
     * 
     * @param r The row at which to place the pointer for the next search. Works best with odd numbers, untested with even
     * @param c The column at which to place the pointer for the next search. Works best with odd numbers, untested with even
     */
    private void pathfind(int r, int c)
    {
        Integer[] dirs = generateRandomDirArray();
        System.out.println("Finding new path at ["+r+", "+c+"]");

        for (Integer i : dirs) 
        {
            switch(dirs[i])
            {
                case 0:
                    if(r - 2 > 0 && mapTiles[r - 2][c] != ' ')
                    {
                        mapTiles[r-1][c] = ' ';
                        mapTiles[r-2][c] = ' ';
                        pathfind(r - 2, c);
                    }
                    break;
                case 1:
                    if(c + 2 < mapTiles[0].length - 1 && mapTiles[r][c + 2] != ' ')
                    {
                        mapTiles[r][c + 1] = ' ';
                        mapTiles[r][c + 2] = ' ';
                        pathfind(r, c + 2);
                    }
                    break;
                case 2:
                    if(r + 2 < mapTiles.length - 1 && mapTiles[r + 2][c] != ' ')
                    {
                        mapTiles[r+1][c] = ' ';
                        mapTiles[r+2][c] = ' ';
                        pathfind(r + 2, c);
                    }
                    break;
                case 3:
                    if(c - 2 > 0 && mapTiles[r][c - 2] != ' ')
                    {
                        mapTiles[r][c-1] = ' ';
                        mapTiles[r][c-2] = ' ';
                        pathfind(r, c - 2);
                    }
                    break;
                default:
                    System.out.println("DEAD END");
                    break;
            }
        }
    }

    /** Generates a new random order for the cardinal directions of motion for the generative pointer
     * 
     * @return An array of Integer types containing values 1-4 in random order
     */
    private Integer[] generateRandomDirArray()
    {
        ArrayList<Integer> dirs = new ArrayList<Integer>();
        for(int i = 0; i < 4; i++) dirs.add(i);
        Collections.shuffle(dirs);
        return dirs.toArray(new Integer[4]);
    }
    //#endregion Map Generation

    /** Determines if the player has reached the goal of the maze
     * 
     * @return The truth of if the player position matches the end position
     */
    public boolean isFinished()
    {
        return playerX == mapTiles[0].length - 1 && playerY == 1;
    }

    /** Moves the player pointer throughout the grid only if a suggested position does not fall on a wall
     * 
     * @param x The amount to increment the x position of the player. Positive numbers move the pointer right, negative move it left
     * @param y The amount to increment the y position of the player. Positive numbers move the pointer up, negative move it down
     */
    public void move(int x, int y)
    {
        if(mapTiles[playerY - y][playerX + x] != 'X')
        {
            playerY -= y;
            playerX += x;
        }
        else System.out.println("Invalid position - please try again!");
    }
    
    public String toString()
    {
        String ret = "";
        for(int j = 0; j < mapTiles.length; j++)
        {
            for(int i = 0; i < mapTiles[0].length; i++)
            {
                if(j == playerY && i == playerX) ret+="@ ";
                else ret+=mapTiles[j][i]+" ";
            }
            ret+="\n";
        }
        return ret;
    }
}
