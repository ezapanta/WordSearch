

import java.io.*;
import java.util.ArrayList;

/**
 * Created by ikanisamani on 9/2/14.
 *
 * This program takes a 9X9 alphabetical grid then a list of words to search for.
 * It will output the location where the word starts and ends.
 *
 */

public class Search {


    public static void main(String[] args) {
	    // write your code here
        ArrayList<String> array = new ArrayList<String>();
        ArrayList<String> wordList = new ArrayList<String>();
        String line;

        if(args.length < 1 || args.length > 1) {
            System.out.println("Error: Too many or no parameters were provided");
            System.exit(0);
        }

        try{
            BufferedReader br = new BufferedReader(new FileReader(args[0]));

            // read Grid
            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    array.add(line.toUpperCase());
                } else {
                    break;
                }
            }
            // read words to search for
            while ((line = br.readLine()) != null) {
                if(!line.equals("")) {
                    wordList.add(line);
                } else {
                    break;
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }

        Model m1 = new Model(array);
        System.out.println("======Grid======");
        m1.display();
        System.out.println("======Grid======");
        System.out.println();
        System.out.println("======WordList======");;
        m1.wordSearch(wordList);

    }
}
