package com.simplilearn;

import java.io.File;
import java.text.Collator;
import java.util.Arrays;
import java.util.Scanner;

public class FileOperationsMenuApp {
    public static void main(String[] args) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("#################################################################################");
        System.out.println("@@@                                                                           @@@");
        System.out.println("@@@                      W E L C O M E                                        @@@");
        System.out.println("---------------------------------------------------------------------------------");
        System.out.println("@@@   Application Name : File Operations Menu Application                     @@@");
        System.out.println("@@@   Developer Name   : K Pradeep Kumar Reddy                                @@@");
        System.out.println("@@@   Description : This application will have the following menu options     @@@");
        System.out.println("@@@     main-menu :                                                           @@@");
        System.out.println("@@@         option-1 : List files in current dir in ascending order           @@@");
        System.out.println("@@@         option-2 : This option will have sub menu as follows              @@@");
        System.out.println("@@@             sub-menu :                                                    @@@");
        System.out.println("@@@                 option1 : Add file to the existing directory list         @@@");
        System.out.println("@@@                 option2 : Delete file from the existing directory list    @@@");
        System.out.println("@@@                 option3 : search user specified file from directory       @@@");
        System.out.println("@@@                 option4 : Go back to main menu                            @@@");
        System.out.println("@@@         option-3 : Close the application                                  @@@");
        System.out.println("@@@                                                                           @@@");
        System.out.println("#################################################################################");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        try {
            // Creating a test directory
            File projTestDir = new File("proj_test_dir");
            if (!projTestDir.exists()) {
                if (!projTestDir.mkdir()) {
                    System.out.println("Failed to create proj_test_dir");
                    return;
                }
            }

            int mainMenuChoice = 0;
            Scanner scanner = new Scanner(System.in);
            do {
                try {
                    System.out.println("\n\n Main Menu ");
                    System.out.println("---------------------------------------------------");
                    System.out.println("1) List files in current dir in ascending order ");
                    System.out.println("2) File operations sub menu ");
                    System.out.println("3) Close the application ");
                    System.out.println("---------------------------------------------------");
                    System.out.println("Enter your choice : ");
                    try {
                        mainMenuChoice = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException nfe) {
                        System.out.println("Invalid input. Try again. Please choose an option from the given menu");
                        continue;
                    }
                    switch (mainMenuChoice) {
                        case 1:
                            String[] files = projTestDir.list();
                            if (files == null) {
                                System.out.println("I/O error occurred");
                                continue;
                            }
                            Arrays.sort(files, Collator.getInstance());
                            if (files.length == 0) {
                                System.out.println("No files to list. Empty directory. Try adding some files");
                            } else {
                                System.out.println("total files : " + files.length);
                                System.out.println("---------------------");
                                for (String file : files) {
                                    System.out.println(file);
                                }
                            }
                            break;
                        case 2:
                            int subMenuChoice = 0;
                            do {
                                System.out.println("\n Sub Menu ");
                                System.out.println("---------------------------------------------------");
                                System.out.println("1) Add file to the existing directory list ");
                                System.out.println("2) Delete file from the existing directory list ");
                                System.out.println("3) Search user specified file from directory ");
                                System.out.println("4) Go back to main menu ");
                                System.out.println("---------------------------------------------------");
                                System.out.println("Enter your choice : ");
                                try {
                                    subMenuChoice = Integer.parseInt(scanner.nextLine());
                                } catch (NumberFormatException nfe) {
                                    System.out.println("Invalid input. Try again. Please choose an option from the " +
                                            "given menu");
                                    continue;
                                }
                                switch (subMenuChoice) {
                                    case 1:
                                        System.out.println("Enter file name to add : ");
                                        String fileName = scanner.nextLine();
                                        File newFile = new File(projTestDir.getAbsolutePath() +
                                                "\\" + fileName);
                                        if (newFile.createNewFile()) {
                                            System.out.println(fileName + " file created successfully");
                                        } else {
                                            System.out.println("could not create file");
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Enter file name to delete : ");
                                        String deleteFileName = scanner.nextLine();
                                        File deleteFile = new File(projTestDir.getAbsolutePath() +
                                                "\\" + deleteFileName.toLowerCase());
                                        if (deleteFile.exists()) {
                                            if (deleteFile.delete()) {
                                                System.out.println(deleteFileName + " file deleted successfully");
                                            } else {
                                                System.out.println(deleteFileName + " file delete failed");
                                            }
                                        } else {
                                            System.out.println(deleteFileName + " file does not exists to delete. " +
                                                    "enter the correct file name");
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Enter file name to search : ");
                                        String searchFileName = scanner.nextLine();
                                        File searchFile = new File(projTestDir.getAbsolutePath() +
                                                "\\" + searchFileName.toLowerCase());
                                        if (searchFile.exists()) {
                                            System.out.println(searchFileName + " file found in directory");
                                        } else {
                                            System.out.println(searchFileName + " file does not exists. " +
                                                    "enter the correct file name");
                                        }
                                        break;
                                    case 4:
                                        System.out.println("Taking you back to main menu.");
                                        break;
                                    default:
                                        System.out.println("Invalid option, please choose an option from the " +
                                                "given menu");
                                        break;
                                }
                            } while (subMenuChoice != 4);
                            break;
                        case 3:
                            System.out.println("Thank you for using this application. bye bye ...");
                            break;
                        default:
                            System.out.println("Invalid option, please choose an option from the given menu");
                            break;
                    }
                } catch (NullPointerException npe) {
                    System.out.println("something unexpected happened, sorry for the inconvenience. restarting app...");
                    // this case can happen if somebody  removes or renames the directory when the app is running
                    if (!projTestDir.exists()) {
                        if (!projTestDir.mkdir()) {
                            System.out.println("Failed to create proj_test_dir");
                            return;
                        }
                    }
                }
            } while (mainMenuChoice != 3);

        } catch (Exception ex) {
            System.out.println("Exception ex = " + ex.getMessage());
        }
    }
}