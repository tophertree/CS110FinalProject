/**
Chris Ogletree
COMP 110
Instructor: Jackie Horton

Card class
*/


import java.util.Random;

public class Card implements CardInterface{
   
   private Random rand;
   
   private int rank,suit;

   private String fileRank,fileSuit, fileString;
   
   
   /**
   Constructor
   @param suitIn The suit of the Card object
   @param rankIn The rank of the Card object
   
   */
   public Card(int suitIn, int rankIn){
      
      suit = suitIn;
      rank = rankIn;
      
   }
   
   /**
   returns value of suit
   */
   public int getSuit(){
      
      return suit;
   }
   
   /**
   returns value of rank
   */
   public int getRank(){

      return rank;
   }
   
   /**
   returns a String consisting of the rank and suit
   */
   public String toString(){
      
      String outputString = "";
      String rankString = "";
      String suitString = "";
      
      switch(rank){
         case 1:
            rankString = "Ace";
            break;
         case 2:
            rankString = "Two";
            break;
         case 3:
            rankString = "Three";
            break;           
         case 4:
            rankString = "Four";
            break;
         case 5:
            rankString = "Five";
            break;
         case 6:
            rankString = "Six";
            break;
         case 7:
            rankString = "Seven";
            break;                        
         case 8:
            rankString = "Eight";
            break;
         case 9:
            rankString = "Nine";
            break;
         case 10:
            rankString = "Ten";
            break;
         case 11:
            rankString = "Jack";
            break;
         case 12:
            rankString = "Queen";
            break;
         case 13:
            rankString = "King";
            break;                                                            
         default:
            break;
      }
            
      switch(suit){
         case 1:
            suitString = "Clubs";
            break;
         case 2:
            suitString = "Diamonds";
            break;
         case 3:
            suitString = "Spades";
            break;
         case 4:
            suitString = "Hearts";
            break;
         default:
            break;
      }
      
      
      outputString = rankString + " of " + suitString;
      
      return outputString;   
   }
   
   /**
   compares two Card objects to test for equality
   @param otherCard Card object to compare
   */
   public boolean equals(Card otherCard){
      
      if(otherCard.rank == this.rank){
         return true;
      }
      else
         return false;
   }  
   
   /**
   compares two Card objects to test if it is smaller
   @param otherCard Card object to compare
   */
   public boolean lessThan(Card otherCard){
      
      if(otherCard.rank > this.rank){
         return true;
      }
      else
         return false;
   }
   
   /**
   compares two Card objects to test if one is larger
   @param otherCard Card object to compare
   */
   public boolean greaterThan(Card otherCard){
      
      if(otherCard.rank < this.rank)
         return true;
      else
         return false;
   }
   
   
   /**
   returns a String in the form of a file name
   */
   public String getCardFile(){
      
      switch(rank){
         case 1:
            fileRank = "ace";
            break;
         case 2:
            fileRank = "2";
            break;
         case 3:
            fileRank = "3";
            break;           
         case 4:
            fileRank = "4";
            break;
         case 5:
            fileRank = "5";
            break;
         case 6:
            fileRank = "6";
            break;
         case 7:
            fileRank = "7";
            break;                        
         case 8:
            fileRank = "8";
            break;
         case 9:
            fileRank = "9";
            break;
         case 10:
            fileRank = "10";
            break;
         case 11:
            fileRank = "jack";
            break;
         case 12:
            fileRank = "queen";
            break;
         case 13:
            fileRank = "king";
            break;                                                            
         default:
            break;
      }
            
      switch(suit){
         case 1:
            fileSuit = "c";
            break;
         case 2:
            fileSuit = "d";
            break;
         case 3:
            fileSuit = "s";
            break;
         case 4:
            fileSuit = "h";
            break;
         default:
            break;
      }
      
      fileString = "ImageFiles//" + fileRank + fileSuit + ".jpg";
      
      return fileString;
      
   }
}