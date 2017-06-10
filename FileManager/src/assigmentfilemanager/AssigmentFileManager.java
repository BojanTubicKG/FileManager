package assigmentfilemanager;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Scanner;

public class AssigmentFileManager {

    public static void main(String[] args) {

        System.out.println("Enter command : ");
        Scanner scn = new Scanner(System.in);
        String command = scn.nextLine();

        switch (command) {
            case "LIST":
                System.out.println("Enter parh: ");
                String path = scn.nextLine();
                File file = new File(path);
                if (file.exists() && file.isDirectory()) {
                    String[] elements = file.list();
                    for (int i = 0; i < elements.length; i++) {
                        System.out.println(elements[i]);
                    }
                }
                break;
            case "INFO":
                System.out.println("Enter parh: ");
                String path1 = scn.nextLine();
                File file1 = new File(path1);
                System.out.println("Name: " + file1.getName());
                System.out.println("Absolute path: " + file1.getAbsolutePath());
                System.out.println("Path: " + file1.getPath());
                System.out.println("File size: " + file1.length() + " B");

                Instant ilm = Instant.ofEpochMilli(file1.lastModified());
                LocalDateTime dtlm = LocalDateTime.ofInstant(ilm, ZoneId.systemDefault());
                System.out.println("Last acces: " + dtlm);
                break;
            case "CREATE_DIR":
                System.out.println("Enter path: ");
                String path2 = scn.nextLine();
                File file2 = new File(path2);
                try {
                    if (!file2.exists()) {
                        file2.mkdir();
                        System.out.println("Created a directory called " + file2.getName());
                    } else {
                        System.out.println("Directory called " + file2.getName() + " already exsists.");
                    }
                } catch (Exception e) {
                    System.out.println("Couldn't create a directory called " + file2.getName());
                }
                break;
            case "RENAME":
                System.out.println("Enter old path: ");
                String oldPath = scn.nextLine();
                System.out.println("Enter new path: ");
                String newPath = scn.nextLine();
                File oldfile = new File(oldPath);
                File newfile = new File(newPath);
                if (!oldfile.exists()) {
                    System.out.println("File doesn't exists!");
                    return;
                }
                if (newfile.exists()) {
                    System.out.println("File with desierd name already exists!");
                    return;
                }
                if (oldfile.renameTo(newfile)) {
                    System.out.println("Rename succesful");
                } else {
                    System.out.println("Rename failed");
                }
                break;
            case "COPY":
                String path3 = "";
                String destination = "";
                try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {

                    System.out.println("Enter file path:");
                    path3 = br.readLine();
                    System.out.println("Enter destinacion folder:");
                    destination = br.readLine();

                } catch (IOException e) {
                    System.out.println(e);
                }
                File afile = new File(path3);
                File bfile = new File(destination + "\\" + afile.getName());
                try (FileInputStream is = new FileInputStream(afile);
                        FileOutputStream os = new FileOutputStream(bfile);) {
                    byte[] b = new byte[1024];
                    int length;

                    while ((length = is.read(b)) > 0) {
                        os.write(b, 0, length);
                    }
                    System.out.println("File is copy sussessfuly!");
                } catch (IOException exc) {
                    System.out.println(exc);
                }
                break;
            case "MOVE":
                String path4 = "";
                String destination1 = "";
                try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));) {

                    System.out.println("Enter file path:");
                    path4 = br.readLine();
                    System.out.println("Enter destinacion folder:");
                    destination1 = br.readLine();

                } catch (IOException e) {
                    System.out.println(e);
                }
                File afile1 = new File(path4);
                File bfile1 = new File(destination1 + "\\" + afile1.getName());
                try (FileInputStream is = new FileInputStream(afile1);
                        FileOutputStream os = new FileOutputStream(bfile1);) {
                    byte[] b = new byte[1024];
                    int length;

                    while ((length = is.read(b)) > 0) {
                        os.write(b, 0, length);
                    }
                    System.out.println("File is move sussessfuly!");
                } catch (IOException exc) {
                    System.out.println(exc);
                } finally {
                    afile1.delete();
                }
                break;
            case "DELETE":
                System.out.println("Enter parh: ");
                String path5 = scn.nextLine();
                File file3 = new File(path5);

                if (file3.exists()) {
                    file3.delete();
                    System.out.println("File successfully deleted!");
                } else {
                    System.out.println("Can not delete " + file3.getName() + " becouse " + file3.getName() + " does not exist.");
                }
                break;
            default:
                System.out.println("Command does not exist!");
                break;
        }

    }
}
