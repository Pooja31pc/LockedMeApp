package com.simplilearn.project;

import java.io.*;
import java.util.Scanner;

public class LockedMeApp {

	public static void main(String[] args) throws IOException {
		System.out.println("===================================================================");
		System.out.println("LOCKEDME.COM");
		System.out.println();
		System.out.println("Welcome to Digital Locker, Store your creds here");
		System.out.println();
		System.out.println("Developed by: Pooja Choudhary");
		System.out.println("===================================================================");
		System.out.println();

		Scanner sc = new Scanner(System.in);

		int b = 1;
		while (b != 0) {

			promtRegisterationOrLogin();

			b = sc.nextInt();
			String a = sc.nextLine();

			if (b == 2) {

				String name;
				String loginId;
				String password;

				System.out.println();
				System.out.println("===================================================================");
				System.out.println("WELCOME TO THE REGISTRATION PAGE!");
				System.out.println("===================================================================");
				System.out.println();

				System.out.println("Enter your Name,Login Id, Password ");

				name = sc.nextLine();
				loginId = sc.nextLine();
				password = sc.nextLine();

				registerUser(name, loginId, password);

			} else
			// sprint 3
			if (b == 1) {

				String loginId;
				String password;

				System.out.println("\n");
				System.out.println("===================================================================");
				System.out.println("WELCOME TO THE LOGIN PAGE!");
				System.out.println("===================================================================");
				System.out.println("\n");
				System.out.println("Enter your Login Id, Password ");

				loginId = sc.nextLine();
				password = sc.nextLine();

				// for checking in the file
				boolean validLogin = loginUser(loginId, password);
				if (validLogin) {
					System.out.println("===============================================================");
					System.out.println("Successfull Login");
					System.out.println("===============================================================");
					System.out.println();
					int c = 1;

					while (c != 0) {

						Scanner scan = new Scanner(System.in);

						System.out.println("Choose the option: ");
						System.out.println("1. Fetch");
						System.out.println("2. Store");
						System.out.println("3. Remove");
						System.out.println("4. Display All");
						System.out.println("0. Exit");

						c = scan.nextInt();
						scan.nextLine();
						System.out.println("Selected option is: " + c);
						System.out.println();

						if (c == 1) {

							System.out.println("===================================================================");
							System.out.println("WELCOME TO THE FETCH PAGE!");
							System.out.println("===================================================================");
							System.out.println();
							System.out.println("Enter your site name for fetching details");

							String usersiteinput = scan.nextLine();
							fetchCredentials(loginId, usersiteinput);

						} else if (c == 2) {

							FileWriter fw = new FileWriter("D:/SimplilearnProject/CredStoreFile/" + loginId + ".txt",
									true);
							BufferedWriter bw = new BufferedWriter(fw);
							PrintWriter pw = new PrintWriter(bw);

							File file = new File("D:/SimplilearnProject/CredStoreFile/" + loginId + ".txt");

							BufferedReader br = new BufferedReader(new FileReader(file));

							String st = br.readLine();
							System.out.println("Enter the site name: ");
							Scanner inputsite = new Scanner(System.in);
							String checksitename = inputsite.nextLine();

							boolean flag = false;
							while ((st = br.readLine()) != null) {
								String input[] = st.split(",");
								if (input[0].equals(checksitename)) {
									flag = true;
								}
							}
							if (flag == false) {
								//
								System.out.println("Credential for the given site doesn't exist.");
								System.out.println();
								System.out
										.println("===================================================================");
								System.out.println("WELCOME TO THE STORE PAGE!");
								System.out
										.println("===================================================================");
								System.out.println();
								System.out.println("Enter your credentials in the below given form: ");
								System.out.println();

								// System.out.println("Enter the Site name");
								// String input1 = sc.nextLine();
								System.out.println("Enter the User Name");
								String input2 = sc.nextLine();
								System.out.println("Enter your Password");
								String input3 = sc.nextLine();
								System.out.println("===============================================================");
								System.out.println("Your credentials are stored successfully");
								System.out.println("===============================================================");
								System.out.println();
								String storesite = checksitename;
								String storeuname = input2;
								String storepword = input3;

								pw.println(storesite + "," + storeuname + "," + storepword);
							} else {
								System.out.println("Credentials for the given site already exists. Please fetch it.");
								System.out.println();
							}

							pw.close();
							bw.close();
							fw.close();
							br.close();
						}
						
						else if(c==3) {
							//
							File inputFile = new File("D:/SimplilearnProject/CredStoreFile/" + loginId + ".txt");
							File tempFile = new File("D:/SimplilearnProject/CredStoreFile/" + loginId + ".temp.txt");
							BufferedReader reader = new BufferedReader(new FileReader(inputFile));
							BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
							//
							System.out.println("Enter the site name: ");
							Scanner inputsite = new Scanner(System.in);
							String deletesitedetails = inputsite.nextLine();
							//
							String readinput = null;
							boolean flag =false;
							while((readinput = reader.readLine())!=null) {

								//String sitedetails[] = readinput.split(",");
								String sitedetails = readinput.trim(); 

								if(sitedetails.split(",")[0].equals(deletesitedetails)) { 
									flag=true;
									continue;
								}
								writer.write(readinput + System.getProperty("line.separator"));
							}
							if(!flag) {
								System.out.println("This site doesn't exist");
							}else
								System.out.println("required creds deleted");
							
							writer.close();
							reader.close();
							System.out.println(inputFile.exists());
							System.out.println(inputFile.delete());
							boolean successful = tempFile.renameTo(inputFile);
							System.out.println(successful);
						}
						
						else if(c==4) {
							
							File displayfile = new File("D:/SimplilearnProject/CredStoreFile/"+ loginId + ".txt"); 
							
							BufferedReader display = new BufferedReader(new FileReader(displayfile));
							
							String dis = null;
							
							while((dis = display.readLine())!=null) {
								
								String input[] = dis.split(",");
								
								System.out.println("User Site:"+input[0]);
								System.out.println("User Name:"+input[1]);
								System.out.println("User Password:"+input[2]);
								System.out.println();
								
						} 
							display.close();
							
						}
						
					}

				}

				else {
					System.out.println("Invalid Login id or Password");
				}
			}
			
			
			else {
				System.out.println("===============================================================");
				System.out.println("You've successfully exited the page.");
				System.out.println("===============================================================");
				System.exit(0);
				System.out.println();
			}
		}

	}

	private static void fetchCredentials(String loginId, String userSiteInput)
			throws FileNotFoundException, IOException {
		File file = new File("D:/SimplilearnProject/CredStoreFile/" + loginId + ".txt");

		BufferedReader br = new BufferedReader(new FileReader(file));

		String st = br.readLine();

		boolean flag = false;

		while ((st = br.readLine()) != null) {

			String input[] = st.split(",");

			if (input[0].equals(userSiteInput)) {
				flag = true;
				System.out.println("User Site:" + input[0]);
				System.out.println("User Name:" + input[1]);
				System.out.println("User Password:" + input[2]);
				System.out.println();

			}
		}
		if (flag == false) {
			System.out.println("Requested credentials not found");
			System.out.println();
		}

		br.close();
	}

	private static boolean loginUser(String loginId, String password) throws FileNotFoundException, IOException {
		File log = new File("D:/SimplilearnProject/UserDetailsFile/NewRegistered");

		BufferedReader read = new BufferedReader(new FileReader(log));

		String pwd = null;

		while ((pwd = read.readLine()) != null) {

			String insert[] = pwd.split(",");

			if (insert[1].equals(loginId) && insert[2].equals(password)) {
				return true;
			}
		}
		read.close();

		return false;
	}

	private static void registerUser(String name, String loginId, String password) throws IOException {
		FileWriter fw = new FileWriter("D:/SimplilearnProject/UserDetailsFile/NewRegistered", true);
		BufferedWriter bw = new BufferedWriter(fw);
		PrintWriter pw = new PrintWriter(bw);

		pw.println(name + "," + loginId + "," + password);

		File f = new File("D:/SimplilearnProject/CredStoreFile", loginId + ".txt");
		f.createNewFile();
		System.out.println("===============================================================");
		System.out.println("You are successfully registered. Please login or exit.");
		System.out.println("===============================================================");
		System.out.println();
		pw.close();
		bw.close();
		fw.close();
	}

	private static void promtRegisterationOrLogin() {
		System.out.println("To access account: ");
		System.out.println();
		System.out.println("1.LOGIN");
		System.out.println("OR");
		System.out.println("Don't have an account?");
		System.out.println("2.SIGN UP");
		System.out.println();
		System.out.println("3.Exit");
	}

}
