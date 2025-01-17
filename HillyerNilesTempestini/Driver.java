package HillyerNilesTempestini;
import java.util.*;
import java.io.*;
import java.beans.*;
import org.json.simple.*;

/**
 * 
 * @author Sam Tempestini
 * @author Cheasea Niles
 * @author Chris Hillyer
 *
 */
// testing 123 

public class Driver implements Serializable{
	/**
	 * @author Sam Tempestini
	 * @param none
	 * @return void
	 */
	public static void welcome()
	{
		System.out.println("****************************************************");
		System.out.println("\n\t\tINSURANCE SCORE CARD");
		System.out.println("\tThis app scores a potential customer");
		System.out.println("\ton various health attributes: blood");
		System.out.println("\tpressure, age, height, weight, and");
		System.out.println("\tfamily history of disease. It writes");
		System.out.println("\teach member's insurance grade to a");
		System.out.println("\tJSON file so that they can be easily");
		System.out.println("\tshared on a web-based data exchange.\n");
		System.out.println("****************************************************");
	}
	/**
	 * @author Sam Tempestini
	 * @param none
	 * @return void
	 */
	public static void showMenu()
	{
		System.out.println("Here are your choices: ");
		System.out.println("\t1. List members");
		System.out.println("\t2. Add a new member");
		System.out.println("\t3. Save members");
		System.out.println("\t4. Load members");
		System.out.println("\t5. Assess members");
		System.out.println("\t6. Save assessments as JSON");
		System.out.println("\t7. Exit");
	}
	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		//C:\Users\Sam Tempestini\Desktop\memberData.txt
		int choice = 0;
		String ftype, outputFname, inputFname, fileName, ft, extension;
		String firstname, lastname, cancer, diabetes, alzheimers;
		int age, weight, height, bps, bpd;
		ArrayList<Member> members = new ArrayList<Member>();
		ArrayList<Member> txtMembers = new ArrayList<Member>();
		ArrayList<Member> binMembers = new ArrayList<Member>();
		ArrayList<Member> xmlMembers = new ArrayList<Member>();

		
		welcome();
		
		String fname;
		Scanner fsc = new Scanner(System.in);
		//System.out.print("Enter name of file: ");
		//fname = fsc.nextLine();
		fname = "C:\\Users\\Sam Tempestini\\Desktop\\memberData.txt";
		System.out.println("The file path is: " + fname);
		members = MemberReader.readTextFile(fname);
	
		
		Scanner sc = new Scanner(System.in);
		Scanner fs = new Scanner(System.in);
		do {
			showMenu();
			try {
				System.out.print("\nEnter your selection: ");
				choice = sc.nextInt();
				
				if (choice == 1)
				{
					MemberWriter.writeMembersToScreen(members);
				}
				else if(choice == 2)
				{
					//Needed to change Member.java in order to take in String instead of Char
					//for taking user input. -Chelsea
					System.out.print("Enter Member's First Name: ");
					firstname = sc.next();
					System.out.print("Enter Member's Last Name: ");
					lastname = sc.next();
					System.out.print("Enter Member's Age: ");
					age = sc.nextInt();
					System.out.print("Enter Member's Weight: ");
					weight = sc.nextInt();
					System.out.print("Enter Member's Height in inches: ");
					height = sc.nextInt();
					System.out.print("Enter Member's Blood Pressure (sys): ");
					bps = sc.nextInt();
					System.out.print("Enter Member's Blood Presssure (dia): ");
					bpd = sc.nextInt();
					System.out.println("Does the Member have any of the following?");
					System.out.println("(Answer with y or n");
					System.out.print("Does the Member have Cancer? ");
					cancer = sc.next();
					System.out.print("Does the Member have Diabetes? ");
					diabetes = sc.next();
					System.out.print("Does the Member have alzheimers? ");
					alzheimers = sc.next();
					members.add(new Member(firstname, lastname, age, weight, height, bps, bpd, cancer, diabetes, alzheimers));
				}
				else if(choice == 3) 
				{
					// write files
					try {
						boolean outValid = false;
						System.out.println("(T)ext, (B)inary, or (X)ML?");
						ftype = fs.nextLine().toUpperCase().trim();
						System.out.println("Enter name of output file: ");
						outputFname = fs.nextLine().trim();
						extension = outputFname.substring(outputFname.lastIndexOf(".")).toLowerCase();
						
						if (ftype.equals("T") && extension.equals(".txt"))
						{			
							if (MemberWriter.writeTextFile(members,outputFname)) {
								System.out.println("Success, text file was written.");
								outValid = true;
							} 						
						}
						else if (ftype.equals("B") && extension.equals(".bin"))
						{
							if (MemberWriter.writeBinaryFile(members,outputFname)) {
								System.out.println("Success, binary file was written.");
								outValid = true;
							} 							
						}
						else if(ftype.equals("X") && extension.equals(".xml"))
						{
							if (MemberWriter.writeXmlFile(members,outputFname)) {
								System.out.println("Success, XML file was written.");
								outValid = true;
							} 
						}
						if (outValid == false) 
							System.out.println("Members were NOT written successfully.");
						
					}
					catch(Exception ex)
					{
						System.out.println("ERROR: Could NOT write file as specified.");
					}
					
				}
				else if(choice == 4) 
				{
					
					// read files
					try {
						boolean inValid = false;
						System.out.println("(T)ext, (B)inary, or (X)ML?");
						ftype = fs.next().toUpperCase().trim();
						System.out.println("Enter name of input file: ");
						inputFname = fs.next().trim();
		                extension = inputFname.substring(inputFname.lastIndexOf(".")).toLowerCase();
						
						// test if vaild file entry
						if (ftype.equals("T") && extension.equals(".txt"))
						{
							inValid = true;
						}
						else if (ftype.equals("B") && extension.equals(".bin"))
						{
							members = MemberReader.readBinaryFile(inputFname);
							inValid = true;
						}
						else if(ftype.equals("X") && extension.equals(".xml"))
						{
							members = MemberReader.readXmlFile(inputFname);
							inValid = true;
						}
						
						
						if (inValid == true) {
							System.out.println("Members were read successfully.");
							MemberWriter.writeMembersToScreen(members);
						}
						else 
						{
							System.out.println("Members were NOT read successfully.");
						}
		
					}catch(Exception ex)
					{
						System.out.println("ERROR: Could NOT read file as specifed");
					}
				}
				else if(choice == 5) 
				{
					
				}
				else if(choice == 6) 
				{
					System.out.println("Enter name of JSON file: ");
					fileName = fs.nextLine().trim();
					extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					
					if (extension.equals(".json"))
						MemberWriter.writeJSONFile(members,fileName);
						System.out.println("Members were written to JSON file successfully.");
				}	
				else if(choice == 8) 
				{
					System.out.println("Enter name of JSON file: ");
					fileName = fs.nextLine().trim();
					extension = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();
					
					if (extension.equals(".json"))
						MemberWriter.writeJSONFile(members,fileName);
						System.out.println("Members were written to JSON file successfully.");
				}
			}catch(Exception ex)
			{
				System.out.println("You have chosen poorly...Exiting Program");
				System.exit(1);
				sc.close();
			}
		}while(choice != 7);
		System.out.println("You have chosen wisely...Good bye!");
		sc.close();
		System.exit(0);
	}
}

   
    
    
    
    
    
    




