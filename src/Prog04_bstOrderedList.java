import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Scanner;

/**
 * Main file that prompts user for an input file
 * with instructional data about cars, and then
 * prompts user for a valid output file name to
 * write the performed instructions in a readable format
 * 
 * CSC 1351 Programming Project No 2
 * Section 2
 * 
 * @author Shane Ruegg
 * @since 03/27/24
 * 
 */
public class Prog04_bstOrderedList {		
		/**
		 * Main method that prompts user for a valid input file, that then reads the data in the
		 * file(provided it is formatted correctly), then sorts, and reformats that data, then
		 * prompts the user for a valid output file, and writes the formatted data into it.
		 * 
		 * CSC 1351 Programming Project No 2
		 * Section 2
		 * 
		 * @author Shane Ruegg
		 * @since 03/27/23
		 * 
		 */
		public static void main(String[] args) {
			
			
			Scanner console = new Scanner(System.in); // Scanner for retrieving the names of the input and output files
			
			String fileName = null; // A String representation of the input file, and then the output file
			
			Scanner readFile = null; // Scanner that will parse the data of the provided input file
			
			PrintWriter writeFile = null; // PrintWriter that will write formatted and sorted data into the output file
			
			bstOrderedList<Movie> list = new bstOrderedList<Movie>(); // Declares and instantiates an bstOrderedList object
			 														  // that has been implemented to sort data accordingly
			//User Prompt for input file
			while(true) {
				System.out.print("Enter input filename: ");
				fileName = console.nextLine();
				// Check in case system uses a different file separator
				if(fileName.contains("\\")) {
					for(int i = 0; i < fileName.length(); i++) {
		          		if(fileName.charAt(i) == '\\') {
	            			fileName = fileName.substring(0,i) + File.separator + fileName.substring(i+1);
		        	 	}
		          	}
				}
				try {
					readFile = GetInputFile(fileName);
					break;
					
				} catch(FileNotFoundException e) {
					System.out.printf("File specified <%s> does not exist. Would you like to continue? <Y/N> ", fileName);
				}
				if(console.nextLine().toUpperCase().equals("N")) {
					System.exit(0);
				}	
			}
			
			//Reading the file
			while(readFile.hasNextLine()) {
				
				String movie = readFile.nextLine(); // current line to be interpreted
				
				String[] info = movie.split(","); // A string array to handle each data parameter
				
				if(info[0].equals("A")) {
					try {
						list.add(new Movie(info[1], 
								Integer.parseInt(info[2]), 
								info[3],
								Integer.parseInt(info[4])));
					} catch(NumberFormatException e) {} // Will just ignore incorrect parameter types and move on
				}
				else if(info[0].equals("D")) {
					list.remove(new Movie(info[1], 
								Integer.parseInt(info[2]), 
								"",0));
				}
				
			}
			readFile.close(); // No longer in use
			
			StringBuilder movieFormat = new StringBuilder(); // String to format the car data neatly
			movieFormat.append("Number of movies:\t" + list.size() + "\n\n");
			for(int i = 0; i < list.size(); i++) {
				Movie currMovie = (Movie) list.toArray("PostOrder")[i]; // Representation of the i'th car that will be formatted
				
				NumberFormat addCommas = NumberFormat.getInstance(); // Adds commas to the price if needed
				addCommas.setGroupingUsed(true);
				
				movieFormat.append("Title:\t\t" + currMovie.getTitle() + "\n");
				movieFormat.append("Year:\t\t" + currMovie.getYear() + "\n");
				movieFormat.append("Rating:\t\t" + currMovie.getRating() + "\n");
				movieFormat.append("Reviews:\t" + currMovie.getReview() + "\n\n");
			}
			
			//User Prompt for output file
			while(true) {
				System.out.print("Enter output filename: ");
				fileName = console.nextLine();
				// Check in case system uses a different file separator
				if(fileName.contains("\\")) {
					for(int i = 0; i < fileName.length(); i++) {
			            if(fileName.charAt(i) == '\\') {
			            	fileName = fileName.substring(0,i) + File.separator + fileName.substring(i+1);
			            }
			        }
				}
				try {
					writeFile = GetOutputFile(fileName);
					writeFile.write(movieFormat.toString());
					writeFile.close();
					console.close();
					break;
					
				} catch(FileNotFoundException e) {
					System.out.printf("File specified <%s> not vaild. Would you like to continue? <Y/N> ", fileName);
				}
				
				if(console.nextLine().toUpperCase().equals("N")) {
					System.exit(0);
				}	
			}
			
		}

		
		/**
		 * A method that will provide the main method
		 * a scanner that is able to read a given file path
		 * 
		 * CSC 1351 Programming Project No 1
		 * Section 2
		 * 
		 * @author Shane Ruegg
		 * @since 03/27/23
		 * 
		 */
		public static Scanner GetInputFile(String UserPrompt) throws FileNotFoundException {
			Scanner fileScanner = new Scanner(new File(UserPrompt)); //Scanner that can read the file path provided by the user
			return fileScanner;
		}
		
		/**
		 * A method that will provide the main method
		 * a PrintWriter that is able to write to a given file path
		 * 
		 * CSC 1351 Programming Project No 1
		 * Section 2
		 * 
		 * @author Shane Ruegg
		 * @since 03/27/23
		 * 
		 */
		public static PrintWriter GetOutputFile(String UserPrompt) throws FileNotFoundException {
			PrintWriter fileWriter = new PrintWriter(new File(UserPrompt)); //Scanner that can write to a file path provided by the user
			return fileWriter;
		}

}
