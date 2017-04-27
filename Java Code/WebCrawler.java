
/*Caroline Lee
  9-9-2016
  Lab3
  This program uses a web crawler to traverse the web and pick out words from websites. 
  It then finds the most commonly used words and organizes them by most common, and
  creates a file of the top 50 words in descending order(with number of occurrences 
  in a column next to each word)starting with the most common word.
 */
import java.io.File;
import java.io.PrintWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.*;

public class WebCrawler {

    public static void main(String[] args) throws IOException, Exception {
        java.util.Scanner input = new java.util.Scanner(System.in);

        //first things first: figure out if the file to be used already exists
        try {
            File file = new File("Top 50 Words.txt");
            if (file.exists()) {
                System.out.print("File already exists. Overwrite file? (y/n) ");
                String answer = input.nextLine();
                while (1 == 1) {//check for correct input (y/n)
                    if (!answer.contains("y") && !answer.contains("n")) {
                        System.out.print("Incorrect input. Overwrite file? (y/n) ");
                        answer = input.nextLine();
                    } else {
                        break;
                    }
                }
                if (answer.contains("n")) {
                    System.exit(0);
                }
            }

            //file name available; begin program normally; ask user to enter a URL
            System.out.print("Enter a URL: ");
            String url = input.nextLine();

            //make sure a correct URL is entered by the user
            while (1 == 1) {
                if (!url.contains("http://") && !url.contains("https://")) {
                    System.out.print("Input incorrect. Enter a correct URL: ");
                    url = input.nextLine();
                } else {
                    break;
                }
            }

            //these are the steps to getting the words for the file
            String words = (crawler(url)).toString();//get words
            String justLetters = useARegex(words).toLowerCase();//get words with just letters
            String withoutStopWords = removeStopWords(justLetters);//delete stopwords
            String sortedWords = makeAList(withoutStopWords);//list, count, and sort words

            //write sorted list of words to a file
            PrintWriter output = new PrintWriter(file);
            output.write(sortedWords);
            output.close();
            System.out.println("New file created.");
            //exception handling
        } catch (IOException ex) {
            ex.getMessage();
        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    //method from book for web crawler
    public static ArrayList<String> crawler(String startingURL) {
        ArrayList<String> pendingURLs = new ArrayList<>();
        ArrayList<String> traversedURLs = new ArrayList<>();
        ArrayList<String> extractedLines = new ArrayList<>();

        //timer added to method
        java.util.Scanner input = new java.util.Scanner(System.in);
        System.out.print("Enter crawler time limit(sec): ");
        String uncheckedInput = input.nextLine();
        int timeLimit = 0;

        //check to make sure input in a number
        while (1 == 1) {
            if (!isNumber(uncheckedInput)) {
                System.out.print("Incorrect input. Enter time limit(sec): ");
                uncheckedInput = input.nextLine();
            } else {
                timeLimit = Integer.parseInt(uncheckedInput);//turn string to int
                break;
            }
        }
        
        LocalTime start = LocalTime.now();
        LocalTime end = LocalTime.now();
        pendingURLs.add(startingURL);
        while (!pendingURLs.isEmpty() && (end.toSecondOfDay() - start.toSecondOfDay())
                <= timeLimit) {
            String urlString = pendingURLs.remove(0);
            if (!traversedURLs.contains(urlString)) {
                traversedURLs.add(urlString);
                for (String s : getSubURLs(urlString)) {
                    if (!traversedURLs.contains(s)) {
                        pendingURLs.add(s);
                    }
                }
                //use same structure for getting subURLs to get paragraph content
                for (String s : getParaStuff(urlString)) {
                    if (!traversedURLs.contains(s) && !pendingURLs.contains(s)) {
                        extractedLines.add(s);
                    }
                }
            }
            end = LocalTime.now();
        }
        System.out.println("-------Crawler has finished-------");
        return extractedLines;//instead of returning URLs, return paragraph lines
    }

    //method from book for getting subURLs
    public static ArrayList<String> getSubURLs(String urlString) {
        ArrayList<String> list = new ArrayList<>();

        try {
            java.net.URL url = new java.net.URL(urlString);
            Scanner input = new Scanner(url.openStream());
            int current = 0;

            while (input.hasNext()) {
                String line = input.nextLine();
                current = line.indexOf("http:", current);
                while (current > 0) {
                    int endIndex = line.indexOf("\"", current);
                    if (endIndex > 0) { // Ensure that a correct URL is found
                        list.add(line.substring(current, endIndex));
                        current = line.indexOf("http:", endIndex);
                    } else {
                        current = -1;
                    }
                }
                //added code for obtaining https: as well as http:
                current = line.indexOf("https:", current);
                while (current > 0) {
                    int endIndex = line.indexOf("\"", current);
                    if (endIndex > 0) { // Ensure that a correct URL is found
                        list.add(line.substring(current, endIndex));
                        current = line.indexOf("https:", endIndex);
                    } else {
                        current = -1;
                    }
                }
            }
        } catch (Exception ex) {//turned off error messsages for inaccessible sites
        }
        return list;
    }

    //method for getting stuff in paragraphs(like getSubURLs method)
    public static ArrayList<String> getParaStuff(String s) {
        ArrayList<String> list = new ArrayList<>();
        try {
            java.net.URL url = new java.net.URL(s);
            Scanner input = new Scanner(url.openStream());

            int current = 0;
            while (input.hasNext()) {
                String line = input.nextLine();
                current = line.indexOf("<p>", current);
                while (current > 0) {
                    int endIndex = line.indexOf("</p>", current);
                    if (endIndex > 0) { // Ensure that a correct paragraph is found
                        list.add(line.substring(current + 3, endIndex - 4));
                        current = line.indexOf("<p>", endIndex);
                    } else {
                        current = -1;
                    }
                }
            }
        } catch (Exception ex) {
        }
        return list;
    }

    public static String useARegex(String s) throws Exception {
        //regex to match space, any word characters[no digits], space
        Pattern p = Pattern.compile("[\\s\\w&&[^\\d]]+\\s");
        Matcher m = p.matcher(s);
        String list = "";
        while (m.find()) {
            list += (m.group());
        }
        return list;
    }

    //this method removes stopwords from the string
    public static String removeStopWords(String s) {
        String[] stopWords = {" of ", " in ", " on ", " by ", " for ", " from ",
            " with ", " to ", " at ", " above ", " up ", " beside ", " before ",
            " into ", " after ", " under ", " over ", " around ", " through ",
            " is ", " and ", " but ", " a ", " the ", " will ", " has ", " had ",
            " s ", " are ", " if ", " it ", " that ", " an ", " be ", " as ",
            " its ", " t ", " we ", " or ", " we ", " you ", " n ", " was ",
            " were ", " been ", " have ", " also ", " yet ", " they ", " their ",
            " our ", " your ", " which ", " img ", " more ", " than ", " br ",
            " about ", " can ", " when ", " out ", " among ", " off ", " while ",
            " not ", " most ", " all ", " who ", " could ", " would ", " should ",
            " some ", " what ", " use ", " one ", " there ", " may ", " these ",
            " this ", " between ", " how ", " i ", " do ", " get ", " my ", " me ",
            " so ", " re ", " "};
        //specific syntax used here found on stackoverflow 
        for (String stopWord : stopWords) {
            if (s.contains(stopWord)) {
                s = s.replaceAll(stopWord, " ");
            }
        }
        return s;
    }

    /*My good friend, Andrew Smith, taught me how hashmaps work. Most of the next
    method was composed with his direction. It takes the "raw" words, splits them
    at (" ") and passes them to an arraylist of hashmaps with "word" and "count"
    as keys for each word and its count.
     */
    public static String makeAList(String rawWords) {
        ArrayList<HashMap<String, Object>> listWords = new ArrayList<>();

        for (String s : rawWords.split(" ")) {
            HashMap<String, Object> word = new HashMap<>();
            word.put("word", s);
            word.put("count", 1L);
            boolean found = false;
            for (int i = 0; i < listWords.size(); i++) {
                //if word is already in arraylist of hashmaps...
                if (listWords.get(i).get("word").equals(s)) {
                    //new hashmap created
                    HashMap<String, Object> replacement = new HashMap<>();
                    Long c = (Long) listWords.get(i).get("count");
                    //process and replace word and new count in new hashmap
                    replacement.put("count", c += 1);
                    replacement.put("word", s);
                    //put it back in the arraylist
                    listWords.set(i, replacement);
                    found = true;
                    break;
                }
            }
            //if not found in arraylist of hashmaps, add it
            if (!found) {
                listWords.add(word);
            }
        }
        //this next part sorts the word list by count values
        ArrayList<HashMap<String, Object>> sortedWords = new ArrayList<>();
        Long highestCount = 0L;
        int index = 0;
        String sortedWordString = "";

        for (int i = 0; i < listWords.size(); i++) {
            //find the count value in the first word
            Long currentCount = (Long) listWords.get(i).get("count");

            if (currentCount > highestCount) {
                highestCount = currentCount;
                index = i;//tells where to extract the highest count word at end of loop
            }
            if ((i + 1) == listWords.size()) {//if the end is near...
                sortedWords.add(listWords.get(index));//pull the highest count word
                listWords.remove(index);//remove it from the old list
                //reset counts for next loop around
                i = 0;
                index = 0;
                highestCount = 0L;
            }
            if (listWords.size() == 1) {//force extraction of last remaining word
                sortedWords.add(listWords.get(index));
            }
            if (sortedWords.size() == 51) {//only take top 50 words
                sortedWords.remove(0);//remove count for spaces
                break;
            }
        }
        for (HashMap<String, Object> sortedWord : sortedWords) {
            //make a string with sorted words in correct format for file writing
            sortedWordString += (sortedWord.get("word") + " "
                    + sortedWord.get("count") + "\r\n");
        }
        return sortedWordString;
    }
    
    //method for checking if crawler time limit input is an integer
    public static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException ex) {
            return false;
        }
        return true;
    }
}
