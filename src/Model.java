import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ikanisamani on 9/2/14.
 */
public class Model {
    private ArrayList<String> rows, columns;
    private ArrayList<String> diagCase2, diagCase3, diagCase5, diagCase6;
    public String diagCase1;
    private String diagCase4;

    public Model(ArrayList<String> grid) {

        this.rows = new ArrayList<String>(grid);
        this.columns = buildColumns();
        String tempString = "";

        // assuming the grid is a square grid, grid.size() should be equal to the number of characters
        // as the string
        for(int i = 0; i < grid.size(); ++i){
            for(String s : grid) {
                tempString +=s.charAt(i);
            }
            columns.add(tempString);
            tempString = "";
        }

        this.diagCase1 = buildCase1(grid);
        this.diagCase2 = buildCase2(grid);
        this.diagCase3 = buildCase3(grid);
        this.diagCase4 = buildCase4(grid);
        this.diagCase5 = buildCase5(grid);
        this.diagCase6 = buildCase6(grid);

    }

    public void wordSearch(ArrayList<String> words) {
        for(String w : words){
            if(!checkRows(w) && !checkColumns(w) && !checkCase1(w) && !checkCase2(w)
                 && !checkCase3(w) && !checkCase4(w)
                    && !checkCase5(w) && !checkCase6(w)) {
                System.out.println(w + " not found!");
            }
        }

    }
    private ArrayList<String> buildColumns() {
        ArrayList<String> storage = new ArrayList<String>();
        String sb = "";

            for (int j = 0; j < 10; ++j) {
                for (String s : rows) {
                    sb += (s.charAt(j));
                }
                storage.add(sb);
                sb = "";
            }
        return storage;
    }
    private ArrayList<String> buildCase2(ArrayList<String> grid) {
        final int CASE2_END = 9;
        int columnEnd = 8;
        ArrayList<String> newArray = new ArrayList<String>();
        StringBuilder newString = new StringBuilder();
        String tempString;
        int outerLoop = 0;
        int rowCounter = 1;
        int rowOuterCounter = 1;
        int columnCounter = 0;

        for(;outerLoop <= CASE2_END-1; ++outerLoop ) {
            for(; columnCounter <= columnEnd; ++columnCounter) {
                newString.append(grid.get(rowCounter).charAt(columnCounter));
                ++rowCounter;
            }
            tempString = newString.toString();
            newString = new StringBuilder();
            newArray.add(tempString);
            rowCounter = ++rowOuterCounter;
            columnCounter = 0;
            --columnEnd;
        }
//        System.out.println("Case 2:");
//        int rowCount = 1;
//        for(String s : newArray) {
//            System.out.println(rowCount++ + "\t" + s);
//        }
        return newArray;
    }
    private ArrayList<String> buildCase3(ArrayList<String> grid) {
        final int CASE2_END = 9;
        int columnEnd = 9;
        ArrayList<String> newArray = new ArrayList<String>();
        StringBuilder newString = new StringBuilder();
        String tempString;
        int outerLoop = 0;
        int rowCounter = 0;
        int outerColumnCounter = 1;
        int columnCounter = 1;

        for(;outerLoop <= CASE2_END-1; ++outerLoop ) {
            for(; columnCounter <= columnEnd; ++columnCounter) {
                newString.append(grid.get(rowCounter).charAt(columnCounter));
                ++rowCounter;
            }
            tempString = newString.toString();
            newString = new StringBuilder();
            newArray.add(tempString);
            rowCounter = 0;
            columnCounter = ++outerColumnCounter;
        }

//        int rowCount = 1;
//        System.out.println("Case 3:");
//        for(String s : newArray) {
//            System.out.println(rowCount++ + "\t" + s);
//        }
        return newArray;
    }
    private String buildCase1(ArrayList<String> grid) {

        ArrayList<String> newArray = new ArrayList<String>();
        StringBuilder newString = new StringBuilder();
        String temp;
        int columnCounter = 0;
        for(String s: grid) {
            newString.append(s.charAt(columnCounter));
            ++columnCounter;
        }

        return newString.toString();
    }
    private String buildCase4(ArrayList<String> grid) {
        final int END_PLACE = 9;
        ArrayList<String> newArray = new ArrayList<String>();
        StringBuilder newString = new StringBuilder();
        String temp;
        int columnCounter = END_PLACE;
        for(String s: grid) {
            newString.append(s.charAt(columnCounter));
            --columnCounter;
        }
//        System.out.println("Case4: " + newString.toString());
        return newString.toString();
    }
    private ArrayList<String> buildCase5(ArrayList<String> grid) {
        final int CASE5_END = 0;

        ArrayList<String> newArray = new ArrayList<String>();
        StringBuilder newString = new StringBuilder();
        String tempString;

        int outerLoop = 8;
        int rowCounter = 0;
        int columnOuterCounter = 8;
        int columnCounter = 8;
        int columnEnd = 0;

        for(;outerLoop >= CASE5_END; --outerLoop ) {
            for(; columnCounter >= columnEnd; --columnCounter) {
                newString.append(grid.get(rowCounter).charAt(columnCounter));
                ++rowCounter;
            }
            tempString = newString.toString();
            newString = new StringBuilder();
            newArray.add(tempString);
            rowCounter = 0;
            columnCounter = --columnOuterCounter;
        }
//        System.out.println("Case 5:");
//        int rowCount = 1;
//        for(String s : newArray) {
//            System.out.println(rowCount++ + "\t" + s);
//        }
        return newArray;
    }
    private ArrayList<String> buildCase6(ArrayList<String> grid) {
        final int CASE6_END = 8;

        ArrayList<String> newArray = new ArrayList<String>();
        StringBuilder newString = new StringBuilder();
        String tempString;

        int rowCounter = 1;
        int rowOuterCounter = 1;
        int rowEnd = 9;
        int columnCounter = 9;

        for(int outerLoop = 0;outerLoop <= CASE6_END; ++outerLoop ) {
            for(; rowCounter <=  rowEnd; ++rowCounter) {
                newString.append(grid.get(rowCounter).charAt(columnCounter));
                --columnCounter;
            }
            tempString = newString.toString();
            newString = new StringBuilder();
            newArray.add(tempString);
            rowCounter = ++rowOuterCounter;
            columnCounter = 9;
        }

//        System.out.println("Case 6:");
//        int rowCount = 1;
//        for(String s : newArray) {
//            System.out.println(rowCount++ + "\t" + s);
//        }
        return newArray;
    }

    private boolean checkColumns(String word) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        boolean flag = false;
        int column = 0;
        for(String s: columns) {
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(word + " found at start: " + matcher.start() + "," + column + " end at: " + (matcher.end()-1) + "," + column);
                flag = true;
                return true;
            }
            ++column;
        }
        if(!flag) {
            column = 0;
            for(String s: columns) {
                pattern = Pattern.compile(reverseString(word));
                matcher = pattern.matcher(s);
                if(matcher.find()) {
                    System.out.println(word + " found at start: " + (matcher.end()-1) + "," + column + " end at: " + matcher.start() + "," + column);
                    return true;
                }
                ++column;
            }

        }
        return false;
    }
    private boolean checkRows(String word) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        boolean flag = false;
        int row = 0;
        for(String s: rows) {
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(word + " found at start: " + row + "," + matcher.start() + " end at: " + row + "," + (matcher.end()-1));
                flag = true;
                return true;
            }
            ++row;
        }
        if(!flag) {
            row = 0;
            for(String s: rows) {
                pattern = Pattern.compile(reverseString(word));
                matcher = pattern.matcher(s);
                if(matcher.find()) {
                    System.out.println(word + " found at start: " + row + "," + (matcher.end()-1) + " end at: " + row + "," + matcher.start());
                    return true;
                }
                ++row;
            }
        }
        return false;
    }
    private boolean checkCase6(String word) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        boolean flag = false;
        int row = 1;
        for(String s: diagCase6) {
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(word + " found at start: " + (matcher.start()+ row) + "," + positionHelper(matcher.start()) + " end at: " + (matcher.end() + row -1) + "," + positionHelper(matcher.end() - 1));
                flag = true;
                return true;
            }
            ++row;
        }
        if(!flag) {
            row = 1;
            for(String s: diagCase6) {
                pattern = Pattern.compile(reverseString(word));
                matcher = pattern.matcher(s);
                if(matcher.find()) {
                    System.out.println(word + " found at start: " + (matcher.end() + row - 1) + "," + positionHelper(matcher.end() - 1) + " end " + (matcher.start() + row) + "," + positionHelper(matcher.start()));
                    return true;
                }
            }
            ++row;
        }
        return false;

    }
    private  boolean checkCase5(String word) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        int row = 1;
        boolean flag = false;
        for(String s: diagCase5) {
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(word + " found at start: "  + matcher.start() + "," + positionHelper(matcher.start() + row)  + " end at: " + (matcher.end()-1) + "," +  positionHelper(matcher.end() + row - 1));
                flag = true;
                return true;
            }
            ++row;
        }
        if(!flag){
            row = 1;
            for(String s: diagCase5) {
                pattern = Pattern.compile(reverseString(word));
                matcher = pattern.matcher(s);
                if (matcher.find()) {
                    System.out.println(word + " found at start: " + positionHelper(matcher.end() - 1) + "," + (matcher.end() + row - 1) + " end at: " + matcher.start() + "," + positionHelper(matcher.start() + row));
                    return true;
                }
                ++row;
            }
        }

        return false;

    }
    private boolean checkCase4(String word){
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(diagCase4);

        if (matcher.find()) {
            System.out.println(word + " found at start: " + matcher.start() + "," + positionHelper(matcher.start()) + " end " + (matcher.end()-1) + "," + positionHelper(matcher.end()-1));
            return true;
        } else {
            pattern = Pattern.compile(reverseString(word));
            matcher = pattern.matcher(diagCase4);
            if(matcher.find()) {
                System.out.println(word + " found at start: " + (matcher.end()-1) + "," + positionHelper(matcher.end()-1) + " end " + matcher.start() + "," + positionHelper(matcher.start()));
                return true;
            }
        }
        return false;
    }
    private  boolean checkCase3(String word) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        int row = 1;
        boolean flag = false;
        for(String s: diagCase3) {
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(word + " found at start: "  + matcher.start() + "," + (matcher.start()+ row)  + " end at: " + (matcher.end()-1) + "," +  (matcher.end() + row -1));
                flag = true;
                return true;
            }
            ++row;
        }
        if(!flag){
            row = 1;
            for(String s: diagCase3) {
                pattern = Pattern.compile(reverseString(word));
                matcher = pattern.matcher(s);
                if (matcher.find()) {
                    System.out.println(word + " found at start: " + (matcher.end() - 1) + "," + (matcher.end() + row - 1) + " end at: " + matcher.start() + "," + (matcher.start() + row));
                    return true;
                }
                ++row;
            }
        }

        return false;

    }
    private boolean checkCase2(String word) {
        Pattern pattern = Pattern.compile(word);
        Matcher matcher;
        boolean flag = false;
        int row = 1;
        for(String s: diagCase2) {
            matcher = pattern.matcher(s);
            if (matcher.find()) {
                System.out.println(word + " found at start: " + (matcher.start()+ row) + "," + matcher.start() + " end at: " + (matcher.end() + row -1) + "," + (matcher.end()-1));
                flag = true;
                return true;
            }
            ++row;
        }
        if(!flag) {
            row = 1;
            for(String s: diagCase2) {
                pattern = Pattern.compile(reverseString(word));
                matcher = pattern.matcher(s);
                if(matcher.find()) {
                    System.out.println(word + " found at start: " + (matcher.end() + row - 1) + "," + (matcher.end() - 1) + " end " + (matcher.start() + row) + "," + matcher.start());
                    return true;
                }
                ++row;
            }

        }
        return false;

    }
    private boolean checkCase1(String word){
        Pattern pattern = Pattern.compile(word);
        Matcher matcher = pattern.matcher(diagCase1);
        if (matcher.find()) {
            System.out.println(word + " found at start: " + matcher.start() + "," + (matcher.start()) + " end " + (matcher.end()-1) + "," + (matcher.end()-1));
            return true;
        } else {
            pattern = Pattern.compile(reverseString(word));
            matcher = pattern.matcher(diagCase1);
            if(matcher.find()) {
                System.out.println(word + " found at start: " + (matcher.end()-1) + "," + (matcher.end()-1) + " end " + matcher.start() + "," + matcher.start());
                return true;
            }
        }
        return false;
    }

    // helper function for reverse
    public String reverseString(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = s.length() - 1; i >= 0; --i) {
            sb.append(s.charAt(i));
        }
        return sb.toString();
    }
    // helper function
    public void display() {
        for(String s : rows){
            System.out.println(s);
        }
    }
    // helper function
    private int positionHelper(int n){
        switch (n){
            case(0): return 9;
            case(1): return 8;
            case(2): return 7;
            case(3): return 6;
            case(4): return 5;
            case(5): return 4;
            case(6): return 3;
            case(7): return 2;
            case(8): return 1;
            case(9): return 0;
            default: return -1;
        }
    }
}
