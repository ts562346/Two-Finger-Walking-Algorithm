/**
 * @author Tasneem Hoque
 * @date 01 November, 2020
 * @description this class calls the merge method to sort generic elements in ascending order
 *              by reading two user input names of files
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class OrderedListDemo {
    public static void main (String [] args) throws FileNotFoundException {
        Scanner sc = new Scanner(System.in); // initialize scanner

        OrderedList<String> names1 = new OrderedList<>(); // initialize two objects to merge
        OrderedList<String> names2 = new OrderedList<>();

        // reading file
        System.out.print("Enter the first filename to read from: "); // read first file
        String filename = sc.nextLine();

        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        while (inputFile.hasNext()){
            names1.insert(inputFile.nextLine()); // populating list with values from file
        }

        System.out.print("Enter the second filename to read from: "); // read second file
        filename = sc.nextLine();

        file = new File(filename);
        inputFile = new Scanner(file);

        while (inputFile.hasNext()){
            names2.insert(inputFile.nextLine()); // populating list with values from file
        }

        System.out.println("The ordered list contains the following entries: \nList 1: ");
        names1.enumerate(); // print first list
        System.out.println("List 2: ");
        names2.enumerate(); // print second list

        System.out.println("\nA merged version of the two lists looks like this: ");
        OrderedList<String> namesMerged = merge(names1, names2); // call merge method
        namesMerged.enumerate(); // print merged list
    }

    /**
     * This method merges two ordered lists by implementing the two-finger walking algorithm
     * @param list1, one of two lists to merge
     * @param list2, two of two lists to merge
     * @return list3, a generic OrderedList that has the two lists merged
     */

    public static<T extends Comparable<T>> OrderedList<T> merge (OrderedList<T> list1, OrderedList<T> list2) {
        OrderedList<T> list3 = new OrderedList<>();
        int count1 = 0, count2 = 0;

        while (count1 < list1.size() && count2 < list2.size()){ // traverse till reaching end of any one of the list
            // if list1 element is greater than list2, add list1 element to list3
            if (list1.get(count1).compareTo(list2.get(count2)) < 0){
                list3.add(list1.get(count1));
                count1++;
                // if list2 element is greater than list1, add list2 element to list3
            } else if (list1.get(count1).compareTo(list2.get(count2)) > 0){
                list3.add(list2.get(count2));
                count2++;
            }
            else { // if both elements are the same, add one and move to next element for both lists
                list3.add (list1.get(count1));
                count1++; count2++;
            }
        }

        if (count1 == list1.size()) {  // if list1 elements run out, add rest of list2 to the new list
            for (int i = count2; i < list2.size(); i++)
                list3.add(list2.get(i));
        }
        else if (count2 == list2.size()) { // if list2 elements run out, add rest of list1 to the new list
            for (int i = count1; i < list1.size(); i++)
                list3.add(list1.get(i));
        }
        return list3;
    }
}
