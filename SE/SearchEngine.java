import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SearchEngine {
   
   private static Scanner in = new Scanner(System.in);
   private static WebLinkedList list; 
   public static Page page;
   public static ArrayList<String> urls = new ArrayList<String>();
   
   public static void main(String[] args) {
      list = new WebLinkedList();
      ArrayList<String> temp = new ArrayList<String>();
      loadSiteTest("https://mcs.msudenver.edu/~gordon/cs2050/hw/index.html");
/*
      urls = page.getLinks();

      for (int i = 0; i < 30; i++) {
         loadSiteTest(urls.get(i));
         if (urls.size() >= 30) {
            break;
         }
         else {
            temp = page.getLinks();
            for (int j = 0; j < temp.size(); i++) {
               urls.add(temp.get(j));
            }
         }
      }
*/
      loadSiteTest("https://mcs.msudenver.edu/~gordon/cs2050/hw/pgm07-blackBoxPerformance.html");  
      loadSiteTest("https://mcs.msudenver.edu/~gordon/cs2050/hw/imagelab_assignment/imagelab.html");
      loadSiteTest("https://mcs.msudenver.edu/~gordon/cs2050/hw/pgm01Quakes.html");   
      list.printList();
      window();
   }

   public static void siteTest() {
      ArrayList<String> urls = new ArrayList<String>();
      urls = page.getLinks();
      System.out.println("Sites: ");
      for (int i = 0; i < urls.size(); i++) {
         System.out.println(urls);
      }
      
   }

   public static void loadSiteTest(String url) {
      ArrayList<String> newWords = new ArrayList<String>();
      try {
         page = new Page(url); 
      } catch (Exception ee) {
         System.out.println("Problem with: " + url);
         return;
      }
      while (!page.pageDone()) {
         String line = page.getLine();
         //System.out.println("Original: \n" + line);
         line = line.toLowerCase().replaceAll("<.*?>", "");
      //   System.out.println("After: \n" + line);
         line = line.replaceAll("\\p{Punct}", "");
         String[] words = line.split("\\W+");
         for(int i=0; i<words.length; ++i){
            if(words[i].equals("a")||words[i].equals("an")||words[i].equals("and")
                ||words[i].equals("at")||words[i].equals("if")
                ||words[i].equals("is")||words[i].equals("it")
                ||words[i].equals("in")||words[i].equals("of")
                ||words[i].equals("on")||words[i].equals("or")
                ||words[i].equals("to")||words[i].equals("the")
                ||words[i].equals("this")||words[i].equals("that")
                ||words[i].equals("which")){}
            else {
               newWords.add(words[i]);
            }
         }//End for
      }
      for (int i = 0; i < newWords.size(); i++) {
         list.addWord(newWords.get(i), url);
      }
   }

   public static void window()  {
      JFrame frame = new JFrame("Search Engine - Kayla Ritchie");
      frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      JTextField text = new JTextField(20);
      JTextArea siteList = new JTextArea(10, 1);
      JButton search = new JButton("Search");
      JLabel label = new JLabel();

      search.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          String input = text.getText();
          Cell word = list.goToWord(input);
          if (word == null) {
            siteList.setText(input + " is not in the list. \nNo Sites Found.");
         }
         else {
            ArrayList sites = list.getSites(word);
            
            for (int i = sites.size()-1; i < 10; i++) {
               sites.add(" "); 
            }
            siteList.setText("Sites: \n" + sites.get(0) + "\n"+ sites.get(1) + "\n"+ sites.get(2) 
                                       + "\n"+ sites.get(3) + "\n"+ sites.get(4) + "\n" + sites.get(5) + "\n"+ sites.get(6) 
                                       + "\n"+ sites.get(7) + "\n"+ sites.get(8) + "\n"+ sites.get(9) + "\n");
            
         }
        }
      });

      
      JPanel center = new JPanel();
      JPanel top = new JPanel();
      JPanel bottom = new JPanel();
      JPanel left = new JPanel();
      JPanel right = new JPanel();
      label.setForeground(Color.white);
      label.setText("Type a word");
      top.add(label);
      center.add(text);
      center.add(search);
      center.add(siteList);
     

      center.setBackground(Color.lightGray);


      frame.add(center, BorderLayout.CENTER);

      top.setBackground(Color.BLACK);
      frame.add(top, BorderLayout.NORTH);

      bottom.setBackground(Color.BLACK);
      frame.add(bottom, BorderLayout.SOUTH);

      left.setBackground(Color.BLACK);
      frame.add(left, BorderLayout.WEST);

      right.setBackground(Color.BLACK);
      frame.add(right, BorderLayout.EAST);

      center.setLayout(new BoxLayout( center, BoxLayout.PAGE_AXIS));
      frame.setSize(500, 300);
      frame.setVisible(true);
   
   }

   public static void test() {
      String[] t1 = { "dog", "cat", "alligator", "bobcat", "jace", "alligator"};
      String t1Site = "https//www.animals.com";

      String[] t2 = { "ape", "cat", "croc", "jeff", "jace"};
      String t2Site = "https//www.goofball.com";

      String[] t3 = { "dog", "canada", "ape", "tams", "jace"};
      String t3Site = "https//www.shootpool.com";

      for(int i = 0; i < 6; i ++){
         list.addWord(t1[i], t1Site);
      }

      for(int i = 0; i < 5; i ++){
         list.addWord(t2[i], t2Site);
      }
      for(int i = 0; i < 5; i ++){
         list.addWord(t3[i], t3Site);
      }

   
      list.printList();
   
      while (true) {
         ArrayList<String> sites;
         System.out.println("What word? ");
         String input = in.nextLine();
         if (input.equals("q") ){
            System.out.println("Quitting...");
            System.exit(0);
         }
         Cell word = list.goToWord(input);
         if (word == null) {
            System.out.println(input + " is not in the list. No Sites Found.");
         }
         else {
            sites = list.getSites(word);
            System.out.println("Sites: ");
            for (int i = 0; i < sites.size(); i++) {
               System.out.println(sites.get(i));  
            }
         }
      }
      

   }
   
}