import java.util.Scanner;

public class Manager 
{
    private static Scanner kb;
    private static Map m;
    private static boolean isPlaying = true;

    private static void startNewGame()
    {
        int width = 0, height = 0;
        while(true)
        {
            System.out.println("Please input a width and height for your maze (MINIMUM 3)");
            try{
                System.out.print("WIDTH: ");
                width = kb.nextInt();
                System.out.print("HEIGHT: ");
                height = kb.nextInt();
            } catch(Exception e){
                System.out.println("Invalid input - please try again!\n");
                kb.nextLine();
                startNewGame();
            }
            if(width >= 3 && height >= 3) break;
            System.out.println("Invalid size - please try again!\n");
        }
        m = new Map(width + (1 - width % 2), height + (1 - height % 2));
        kb.nextLine();
        runGame();
    }

    private static void runGame()
    {
        while(isPlaying)
        {
            System.out.println("\n"+m);
            if(m.isFinished()) break;
            getInput();
        }
        String decision = "";
        
        while(true)
        {
            System.out.println("Play again? (Y/N): ");
            decision = kb.nextLine();
            if(decision.equalsIgnoreCase("Y") || decision.equalsIgnoreCase("N")) break;
            System.out.println("Invalid selection - please try again!");
        }
        System.out.println();
        if(decision.equalsIgnoreCase("Y")) startNewGame();
        System.out.println("Thanks for playing!");
        kb.close();
    }

    private static void getInput()
    {
        System.out.println("Where would you like to move?");
        System.out.println("w = UP, d = RIGHT, s = DOWN, a = LEFT, q = QUIT");
        String input = kb.nextLine() + " ";
        switch(input.charAt(0))
        {
            case 119:
                m.move(0, 1);
                break;
            case 100:
                m.move(1, 0);
                break;
            case 115:
                m.move(0, -1);
                break;
            case 97:
                m.move(-1, 0);
                break;
            case 113:
                isPlaying = false;
                break;
            default:
                System.out.println("Invalid selection - please try again!");
                break;
        }
    }

    public static void main(String[] args)
    {
        kb = new Scanner(System.in);

        startNewGame();
    }
}
