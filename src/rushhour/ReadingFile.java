package rushhour;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class ReadingFile {

    public static void writeToFile(String outputFile, ArrayList<String> printList){
        try {
            FileWriter myWriter = new FileWriter(outputFile);
            for (String s : printList) {
                myWriter.write(s + "\n");
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static String getSolutionBoard(String inputFile) throws FileNotFoundException {
        StringBuilder inputBoard = new StringBuilder();
        try {
            File myObj = new File(inputFile);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                inputBoard.append(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e + " ");
        }
        return inputBoard.toString();
    }

}
