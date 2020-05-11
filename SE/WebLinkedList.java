import java.util.ArrayList;

public class WebLinkedList {

   private Cell head;
   private Cell currentWord;
   private Cell currentSite;
   private int numWords;
   
   public WebLinkedList() {
      head = null;
      currentWord = null;
      currentSite = null;
      numWords = 0;
   }

   public void addWord(String word, String site) {
      boolean done = false;
      currentWord = head;
      Cell last = currentWord;
      if (head == null) {
         head = new Cell(word);
         numWords++;
         addSite(head, site);
      }
      else {
         while (currentWord != null) {
            if(word.compareTo(currentWord.getData()) == 0) {
               addSite(currentWord, site);
               currentWord = null;
            }
            else if(word.compareTo(currentWord.getData()) < 0 && currentWord.equals(head)) {
               Cell newWord = new Cell(word);
               newWord.setNextWord(currentWord);
               head = newWord;
               numWords++;
               addSite(head, site);
               currentWord = null;
             }
            else if(word.compareTo(currentWord.getData()) < 0) {
               Cell newWord = new Cell(word);
               newWord.setNextWord(currentWord);
               last.setNextWord(newWord);
               numWords++;
               addSite(newWord, site);
               currentWord = null;
             }
            else if(word.compareTo(currentWord.getData()) > 0 && currentWord.getNextWord() == null) {
               Cell newWord = new Cell(word);
               currentWord.setNextWord(newWord);
               numWords++;
               addSite(newWord, site);
               currentWord = null;
             }
             else {
                last = currentWord;
                currentWord = currentWord.getNextWord();
             }
             
          }
      }
   }

   public void addSite(Cell word, String site) {
      currentSite = word.getNextSite();
      if (currentSite == null) {
        word.setNextSite(new Cell(site));
      }
      else {
         boolean done = false;
         while(!done) {
            if(currentSite.getData().equals(site)) {
               done = true;
            }
            else if (currentSite.getNextSite() == null) {
               currentSite.setNextSite(new Cell(site));
               done = true;
            }
            else {
               currentSite = currentSite.getNextSite();
            }
         }
         
      }
   }

   public Cell goToWord(String input) {
      boolean done = false;
      currentWord = head;
      if (currentWord == null) {
        return null;
      }
      else {
         while (currentWord != null) {
            if (currentWord.getData().equals(input)) {
               return currentWord;
            }
            else {
               currentWord = currentWord.getNextWord();
            }
         }
         return null;
      }
   }

   public ArrayList<String> getSites(Cell word) {
      ArrayList<String> temp = new ArrayList<String>();
      currentSite = word.getNextSite();
      while (currentSite != null) {
         temp.add(currentSite.getData());
         currentSite = currentSite.getNextSite();
      }
      return temp;   
   }
   
   public void printList() {
      System.out.println("Head-->");
      currentWord = head;
      ArrayList temp;
      while(currentWord!= null) {
         System.out.println("Word: " + currentWord.getData());
         temp = getSites(currentWord);
         System.out.println("Sites: ");
         for (int i = 0; i < temp.size(); i++) {
             System.out.println(temp.get(i));
         }
         System.out.println("----->");
         currentWord = currentWord.getNextWord();
      }
   }


}