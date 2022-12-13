import java.util.HashSet;
import java.util.Set;
import java.util.Stack;



/** ITSE2417 Programming Final Project
 *  This program tells you every unique permutation of a set of characters.
 *  @author Aydan Butler
 */
public class WordPermutations implements FinalProject
{
    private static int columns = 1;         //counter
    private final int numColumns = 15;      //constant for number of columns printed
    
    public WordPermutations() { }
    
    //formatted printer function
    private void wordPrinter(String str)
    {
        String format = "%-" + (str.length() + 2) + "s";
        System.out.printf(format, str);
        if(columns == numColumns)
        {
            System.out.println();
            columns = 1;
        }
        else
        {
            columns++;
        }
        
    }
    
    public void begin() throws IllegalArgumentException
    {
        Stack<String> stack = new Stack<>();        //to hold/iterate through the possible permutations
        String input = "", current, added;          
        char chArr[];
        int index;
        Set<String> permutations = new HashSet<>(); //improves the speed of the .contains() method
        
        
        System.out.println();
        System.out.println();
        System.out.println("____________________________________________________________");
        System.out.println("|                      Welcome!!!                          |");
        System.out.println("| This program will print out all the permutations of a    |");
        System.out.println("| word. Only unique entries are included. Please keep input|");
        System.out.println("| at 8 characters or fewer; any longer and the program     |");
        System.out.println("| takes a while to run.                                    |");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        Itse2417Main.myScan.nextLine();
        while(input.isEmpty())
        {
            try
            {
                System.out.print("Enter a string, then press enter: ");
        
                input = Itse2417Main.myScan.nextLine();
                input = input.strip();
            
                //input must be a character set between lengths 1 and 8
                if(input.length() < 1 || input.length() > 8 || input.isEmpty() || input.contains("|"))
                {
                    throw new IllegalArgumentException();
                }
            }
            catch(IllegalArgumentException e)
            {
                System.out.println("ERROR. Please keep your word between 1 and 8 characters.");
                
                //loop until input is validated
                input = "";
            }
        }
        
        System.out.println();
        System.out.println("Permutations");
        for(int i = 0; i < (input.length() * numColumns) + (2 * numColumns) - 2; i++)
        {
            System.out.print("*");
        }
        System.out.println();
        
        stack.add("|" + input);                         //"|word"
        
        while(!stack.empty())
        {
            current = stack.pop();
            if(current.endsWith("|"))                   //if "word|"
            {
                current = current.replace("|", "");     //remove marker character
                if(!permutations.contains(current))     //no duplicates
                {
                    permutations.add(current);
                    wordPrinter(current);
                    
                }
            }
            else
            {
                index = current.indexOf("|");                                           //get marker location   m|eat
                for(int i = index + 1; i < current.length(); i++)
                {
                    added = current.substring(0, index) + current.charAt(i) + "|";      //move current character after the marker to
                    chArr = current.substring(index + 1).toCharArray();                 //before the marker in 'added'
                    chArr[i - added.length() + 1] = '\n';                               //remove character at its position after the marker
                    for(char ch : chArr)                                                //me|at, ma|et, mt|ea will be added to the stack
                    {
                        if(ch != '\n')
                        {
                            added = added + ch;                                         //add everything after the marker to added except
                        }                                                               //the current character, add added to stack
                            
                    }
                    
                    stack.add(added);
                    
                }
            }
        }
       
        for(int i = 0; i < (input.length() * numColumns) + (2 * numColumns) - 2; i++)
        {
            System.out.print("*");
        }
        System.out.println();
        System.out.println();
        System.out.println("Number of permutations: " + permutations.size());
        System.out.println();
        System.out.println();

        
       
        
        try
        {
            java.util.concurrent.TimeUnit.SECONDS.sleep(2);  
        }
        catch(InterruptedException e)
        {}
    }
}
