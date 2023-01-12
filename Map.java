public class Map 
{
    private int[][] mapTiles = new int[20][20];
    private char[] signs = {' ', '#', '@'};
    private int playerX = 0;
    private int playerY = 0;

    public Map()
    {
        init();
    }

    private void init()
    {
        for(int i = 0; i < mapTiles.length; i++)
        {
            mapTiles[i][0] = 1;
            mapTiles[0][i] = 1;
            mapTiles[i][mapTiles.length - 1] = 1;
            mapTiles[mapTiles.length - 1][i] = 1;
        }
    }

    public void generateMap()
    {
        
    }

    public String toString()
    {
        String ret = "";
        for(int i = 0; i < mapTiles.length; i++)
        {
            for(int j = 0; j < mapTiles[i].length; j++)
            {
                ret += signs[mapTiles[j][i]] + " ";
            }
            ret += "\n";
        }
        return ret;
    }
}
