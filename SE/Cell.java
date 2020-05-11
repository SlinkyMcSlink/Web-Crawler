public class Cell {

   private String data;
   private Cell nextWord;
   private Cell nextSite;

   public Cell(String d) {
      data = d;
      nextWord = null;
      nextSite = null;
   }

   public String getData() {
      return data;
   }

   public Cell getNextWord() {
      return nextWord;
   }

   public Cell getNextSite() {
      return nextSite;
   }

   public void setNextWord(Cell x) {
      nextWord = x;
   }

   public void setNextSite(Cell x) {
      nextSite = x;
   }

   public String toString() {
      return "[" + data + "]";
   }

}