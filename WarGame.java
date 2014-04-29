import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.ArrayList;

public class WarGame extends JFrame{

   private final int WINDOW_HEIGHT = 600;
   private final int WINDOW_WIDTH = 1000;
   
   private final int FULL_DECK = 52;
   
   private String image1 = "",image2 = "",backSide = "back.jpg";
   
   private final ImageIcon backside = new ImageIcon(backSide);
   private JPanel panel;
   
   private ArrayList<Card> player1deck,player2deck;
   private int numFlips = 0;
   
   private Card playerOneFlip,playerTwoFlip;
   private int player1score = 0, player2score = 0,numWars = 0;
   
   private Card oldFlip1,oldFlip2;
   
   private Card p1war,p2war,downFlip1,downFlip2;
   
   private DeckBuild deck = new DeckBuild();
   
   private boolean another = false;
   private boolean war;
   
   private ArrayList<Card> nextDeck1 = new ArrayList<>();
   private ArrayList<Card> nextDeck2 = new ArrayList<>();
   
   
   //**************
   //create the parts of the GUI (buttons, etc.)
      
      
   //used for west section of gridlayout
   private JLabel player1 = new JLabel();
   private JLabel back1 = new JLabel();
   
   
   //used for east section of gridlayout
   private JLabel player2 = new JLabel();
   private JLabel back2 = new JLabel();
   
   
   //used for south section of gridlayout
   private JButton flipButton = new JButton();
   private JButton quitButton = new JButton();
   private JButton resetButton = new JButton();
   
   
   //these are for the center of the gridlayout
   private ImageIcon player1Card = new ImageIcon(image1);
   private ImageIcon player2Card = new ImageIcon(image2);
   private JLabel cardOne = new JLabel(),cardTwo = new JLabel();
   private JLabel p1 = new JLabel(),p2 = new JLabel();
   
   
   //*******************   
   //constructor, sets the look of the actual GUI
   
   
   public WarGame(){
      
      player1deck = deck.getDeckOne();
      player2deck = deck.getDeckTwo();
      
      
      JFrame window = new JFrame();
      
      window.setLayout(new BorderLayout());
      
      window.setTitle("Let's play war!");
      window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
      
      playerOnePanel();
      window.add(panel,BorderLayout.WEST);
      
      playerTwoPanel();
      window.add(panel,BorderLayout.EAST);
      
      buttonPanel();
      window.add(panel,BorderLayout.SOUTH);
      
      cardImagePanel();
      window.add(panel,BorderLayout.CENTER);
      
      
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setVisible(true);
      
   }
   
   public void buttonPanel(){
      
      flipButton.setText("Flip next Card");
      
      flipButton.addActionListener(new FlipButtonListener());
            
      quitButton.setText("Quit Game");
      
      quitButton.addActionListener(new QuitButtonListener());


      resetButton.setText("Reset Game");
      
      resetButton.addActionListener(new ResetButtonListener());


      panel = new JPanel();
            
      panel.setLayout(new BorderLayout());
      
      panel.add(flipButton,BorderLayout.CENTER);
            
      panel.add(quitButton,BorderLayout.EAST);
            
      panel.add(resetButton,BorderLayout.WEST);
   }
   
   
   public void playerOnePanel(){
      
      player1.setText("Player 1");
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(2,1));
      
      back1.setIcon(backside);
         
      panel.add(back1);
      panel.add(player1);
   }
   
   
   public void playerTwoPanel(){
      
      player2.setText("Player 2");
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(2,1));
      
      back2.setIcon(backside);

      panel.add(back2);      
      panel.add(player2);
   }
   
   public void cardImagePanel(){
      
      panel = new JPanel();
      
      panel.setLayout(new GridLayout(2,2));
      
      cardOne.setIcon(player1Card);
      cardTwo.setIcon(player2Card);
      
      panel.add(cardOne);
      panel.add(cardTwo);
      
      
      p1.setText("Player 1 score: " + player1score);
      p2.setText("Player 2 score: " + player2score);
      
      panel.add(p1);
      panel.add(p2);
      
   }
   
   
   //**************   
   //ActionListener for flipButton
   
   private class FlipButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
      
         try{
            
             //take next Card from array of Cards
            playerOneFlip = player1deck.get(numFlips);
            playerTwoFlip = player2deck.get(numFlips);
            
            // set String image to name of file related to card
            image1 = playerOneFlip.getCardFile();
            image2 = playerTwoFlip.getCardFile();
            
            // set the image for player1
            player1Card = new ImageIcon(image1);
            cardOne.setIcon(player1Card);
            panel.add(cardOne);
            
            // set the image for player2
            player2Card = new ImageIcon(image2);
            cardTwo.setIcon(player2Card);
            panel.add(cardTwo);
            
            // add the images to the panel
            panel.add(p1);
            panel.add(p2);
            
            
            if(war != true){
               
               if(playerOneFlip.getRank() > playerTwoFlip.getRank()){
               
                  System.out.println("Player 1 gets the pair");
                  
                  nextDeck1.add(playerOneFlip);
                  nextDeck1.add(playerTwoFlip);
                  
                  player1score++;
               }
               else if(playerOneFlip.getRank() < playerTwoFlip.getRank()){
               
                  System.out.println("Player 2 gets the pair");
                  
                  nextDeck2.add(playerOneFlip);
                  nextDeck2.add(playerTwoFlip);

                  player2score++;
               }


               //add the images to the panel
               p1.setText("Player 1 score: " + player1score);
               panel.add(p1);
               p2.setText("Player 2 score: " + player2score);
               panel.add(p2);
               
               
            }

            
            else if(war == true){
               
               if(image1 == backSide && image2 == backSide){
                  
                  if(playerOneFlip.getRank() > playerTwoFlip.getRank()){
                     
                     player1score = player1score + 2;
                     
                     nextDeck1.add(oldFlip1);
                     nextDeck1.add(oldFlip2);
                  }
                  
                  else if(playerOneFlip.getRank() < playerTwoFlip.getRank()){
                     
                     player2score = player2score + 2;
                     
                     nextDeck2.add(oldFlip1);
                     nextDeck2.add(oldFlip2);
                  }
                  
                  
               }
               else if(image1 != backSide && image2 != backSide){
                  
                  
                  playerOneFlip = player1deck.get(numFlips);
                  playerTwoFlip = player2deck.get(numFlips);
                  
                  if(playerOneFlip.getRank() > playerTwoFlip.getRank()){
                        
                     nextDeck1.add(p1war);
                     nextDeck1.add(p2war);
                  }
                  else if(playerOneFlip.getRank() < playerTwoFlip.getRank()){
                     
                     nextDeck2.add(p1war);
                     nextDeck2.add(p2war);
                  }
                  
                  image1 = backSide;
                  image2 = backSide;
                  
                  player1Card = new ImageIcon(image1);
                  cardOne.setIcon(player1Card);
                  panel.add(cardOne);
                  
                  //set the image for player2
                  player2Card = new ImageIcon(image2);
                  cardTwo.setIcon(player2Card);
                  panel.add(cardTwo);
                  
                  panel.add(p1);
                  panel.add(p2);
                  
                  numFlips++;
                  
                  
               }
            }
            
            //*******************
            
            
            //set the card from the array into the oldFlip variable
            oldFlip1 = playerOneFlip;
            oldFlip2 = playerTwoFlip;
            
            war = false;
            
            if(oldFlip1.getRank() == oldFlip2.getRank()){
               
               System.out.println("IT'S A WAR!");
               
               war = true;
               p1war = oldFlip1;
               p1war = oldFlip2;
            }
            
            
         }
               
        
         catch(IndexOutOfBoundsException c){
         
            if(nextDeck1.size() != 0 && nextDeck2.size() != 0){
               System.out.println("That's the end of the round!");
               
               System.out.println("Player 1 has " + nextDeck1.size() + " cards for next round");
               System.out.println("Player 2 has " + nextDeck2.size() + " cards for next round");
               
               another = true;
            }
               
            else if(nextDeck1.size() == 0 || nextDeck2.size() == 0){  
            
            
               if(nextDeck2.size() == 0){
                  System.out.println("Player 1 wins!");
               }
               else if(nextDeck1.size() == 0){
                  System.out.println("Player 2 wins!");
               }
               
               System.exit(0);
            }
            System.exit(0);
         }
         
         
         numFlips++;
       } 
   }
   
   
   //************
   //ActionListener for quitButton
   
   private class QuitButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
         
         System.out.println("Thanks for playing!");
         
         System.exit(0);
      }
   }
   
   
   //*************
   //ActionListener for resetButton
   
   private class ResetButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
         
         deck = new DeckBuild();
         
         player1deck = deck.getDeckOne();
         player2deck = deck.getDeckTwo();
      }
   }
   
   
   
   
   
   
   public boolean war(Card card1, Card card2){
      
      if(card1.getRank() == card2.getRank()){
         
         return true;
      }
      else
         return false;
         
   }
}