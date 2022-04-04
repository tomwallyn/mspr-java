package com.mspr;

import java.io.*;
import java.util.*;

public class ReaderJava {

    private final File staffFile;
    private final File listFile;

    public ReaderJava(){
        this.staffFile = new File("src/main/resources/assets/staff.txt");
        this.listFile = new File("src/main/resources/assets/list.txt");
    }

    /**
     * @param staffFile the txt file containing staff list
     * @param listFile the txt file containing the list of all items available
     */
    public ReaderJava(File staffFile, File listFile){
        this.staffFile = staffFile;
        this.listFile = listFile;
    }

    public ArrayList<String> getAListOfUsersFromAFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(this.staffFile));
        ArrayList<String> listOfUsers = new ArrayList<>();
        String user;
        while ((user = br.readLine()) != null) {
            listOfUsers.add(user.trim());
        }
        return listOfUsers;
    }

    public File getUserFile(String user){
        return new File("src/main/resources/assets/" + user + ".txt");
    }

    public String getHtpasswd(String user) throws IOException {
        File file = getUserFile(user);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<String> allLines = new ArrayList<>();
        while ((line=br.readLine())!=null){
            allLines.add(line.trim());
        }
        int index = 0;
        for (String allLine : allLines) {
            if (Objects.equals(allLine, "")){
                index = allLines.indexOf(allLine);
            }
        }
        return allLines.get(index - 1);

    }

    public ArrayList<String> getAllItemsFromUser(String user) throws IOException {
        File file = getUserFile(user);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        ArrayList<String> itemsOfUser = new ArrayList<>();
        ArrayList<Map<String, String>> items = createItemsList();

        while ((line = br.readLine()) != null) {
            for (Map<String, String> item : items) {
                if (item.containsKey(line.trim())) {
                    itemsOfUser.add(item.get(line.trim()));
                }
            }
        }


        return itemsOfUser;
    }

    public ArrayList<String> getAllItemsNotUsedByUsers(String user) throws IOException {
        ArrayList<String> itemsOfUser = getAllItemsFromUser(user);
        ArrayList<String> itemsNotUsed = new ArrayList<>();
        ArrayList<Map<String, String>> items = createItemsList();
        for (Map<String, String> item : items) {
            itemsNotUsed.addAll(item.values());
        }
        itemsNotUsed.removeIf(itemsOfUser::contains);
        return itemsNotUsed;
    }

    public ArrayList<Map<String, String>> createItemsList() throws IOException {
        BufferedReader br2 = new BufferedReader(new FileReader(this.listFile));
        String line2;
        ArrayList<Map<String, String>> items = new ArrayList<>();
        while ((line2 = br2.readLine()) != null) {
            String[] words = line2.split("(?=\\p{Upper})");
            int i = 0;
            Map<String, String> map = new HashMap<>();
            ArrayList<String> tempList = new ArrayList<>();
            for (String word : words) {
                if (i == 0) {
                    tempList.add(word.trim());
                }
                if (i == 1) {
                    tempList.add(word);
                    map.put(tempList.get(0).trim(), tempList.get(1).trim());
                }
                i += 1;
            }
            items.add(map);


        }
        return items;
    }


}

