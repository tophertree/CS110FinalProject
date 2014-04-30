/**
Chris Ogletree
COMP 110
Final Project

WarGame class
*/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.util.ArrayList;
import java.util.Random;

public class WarGame extends JFrame{
   
   private Random rand = new Random();
      
   private String image1 = "",image2 = "",backSide = "ImageFiles\\back.jpg";
   private String oldImage1,oldImage2;
   
   private final ImageIcon backside = new ImageIcon(backSide);
   private JPanel panel;

   private DeckBuild deck = new DeckBuild();

   private ArrayList<Card> player1deck = new ArrayList<>(deck.getDeckOne());
   private ArrayList<Card> player2deck = new ArrayList<>(deck.getDeckTwo());
   
   private Card playerOneFlip,playerTwoFlip;   
   
   private Card warCard1,warCard2;
   
   private boolean war;
      
   
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
   
   
   //used for the center of the gridlayout
   private ImageIcon player1Card = new ImageIcon(image1);
   private ImageIcon player2Card = new ImageIcon(image2);
   private JLabel cardOne = new JLabel(),cardTwo = new JLabel();
   private JLabel p1 = new JLabel(),p2 = new JLabel();
   
   
   //*******************   
   //constructor, sets the look of the actual GUI
   
   /**
   Constructor
   sets look and add panels to GUI
   */
   public WarGame(){
      
      //create both half decks from the getDeck method
      player1deck = deck.getDeckOne();
      player2deck = deck.getDeckTwo();
      
      
      JFrame window = new JFrame();
      
      window.setLayout(new BorderLayout());
      
      window.setTitle("Let's play war!");
      
      playerOnePanel();
      window.add(panel,BorderLayout.WEST);
      
      playerTwoPanel();
      window.add(panel,BorderLayout.EAST);
      
      buttonPanel();
      window.add(panel,BorderLayout.SOUTH);
      
      cardImagePanel();
      window.add(panel,BorderLayout.CENTER);
      
      window.pack();
      
      window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      window.setVisible(true);
      
   }
   
   /**
   panel to set text on buttons and add ActionListeners
   */
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
   
   /**
   panel to set player one's half of the GUI
   */
   public void playerOnePanel(){
      
      player1.setText("Player 1");
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(2,1));
      
      back1.setIcon(backside);
         
      panel.add(back1);
      panel.add(player1);
   }
   
   
   /**
   panel to set player two's half of the GUI
   */
   public void playerTwoPanel(){
      
      player2.setText("Player 2");
      
      panel = new JPanel();
      panel.setLayout(new GridLayout(2,1));
      
      back2.setIcon(backside);

      panel.add(back2);      
      panel.add(player2);
   }
   
   
   /**
   panel used to display the card images
   */
   public void cardImagePanel(){
      
      panel = new JPanel();
      
      panel.setLayout(new GridLayout(2,2));
      
      panel.setPreferredSize(new Dimension(500,500));
      
      cardOne.setIcon(player1Card);
      cardTwo.setIcon(player2Card);
      
      panel.add(cardOne);
      panel.add(cardTwo);
      
      
      p1.setText("Player 1 card");
      p2.setText("Player 2 card");
      
      panel.add(p1);
      panel.add(p2);
      
   }
   


   /**
   ActionListener for flipButton
   */
   private class FlipButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
      
            
         //take next Card from array of Cards
         playerOneFlip = player1deck.get(0);
         playerTwoFlip = player2deck.get(0);
         
         player1deck.remove(0);
         player2deck.remove(0);
         
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
                     
         
         if(playerOneFlip.getRank() != playerTwoFlip.getRank()){
            
            if(oldImage1 == backSide && oldImage2 == backSide){
               
               if(playerOneFlip.getRank() > playerTwoFlip.getRank()){
                  
                  player1deck.add(warCard1);
                  player1deck.add(warCard2);
                  
               }
               else{
                  
                  player2deck.add(warCard1);
                  player2deck.add(warCard2);
                  
               }
            }
            
            
            
            
            if(playerOneFlip.getRank() > playerTwoFlip.getRank()){
            
               System.out.println("Player 1 gets the pair");
               
               player1deck.add(playerOneFlip);
               player1deck.add(playerTwoFlip);
               
            }
            else if(playerOneFlip.getRank() < playerTwoFlip.getRank()){
            
               System.out.println("Player 2 gets the pair");
               
               player2deck.add(playerOneFlip);
               player2deck.add(playerTwoFlip);

            }


            //add the images to the panel
            p1.setText("Player 1 card");
            panel.add(p1);
            p2.setText("Player 2 card");
            panel.add(p2);
            
         }

         
         else{
            
            
            if(image1 != backSide && image2 != backSide){
               
               warCard1 = playerOneFlip;
               warCard2 = playerTwoFlip;
                                 
               oldImage1 = backSide;
               oldImage2 = backSide;
               
               player1Card = new ImageIcon(oldImage1);
               cardOne.setIcon(player1Card);
               panel.add(cardOne);
               
               //set the image for player2
               player2Card = new ImageIcon(oldImage2);
               cardTwo.setIcon(player2Card);
               panel.add(cardTwo);
               
               panel.add(p1);
               panel.add(p2);
               
            }
               
         }
         
               
         if(playerOneFlip.getRank() == playerTwoFlip.getRank()){
            
            System.out.println("IT'S A WAR!");

         }
         
         if(player1deck.size() == 0 || player2deck.size() == 0){  
            
            if(player1deck.size() == 0){
               
               System.out.println("Player 2 wins!");
            }   
            else if(player2deck.size() == 0){
               
               System.out.println("Player 1 wins!");
            }
            System.exit(0);
         }     
      } 
   }
   
      
   /**
   ActionListener for quitButton
   */
   private class QuitButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
         
         System.out.println("Thanks for playing!");
         
         System.exit(0);
      }
   }
   
   /**
   ActionListener for resetButton
   */
   private class ResetButtonListener implements ActionListener{
      
      public void actionPerformed(ActionEvent e){
                  
         deck = new DeckBuild();
         
         player1deck = deck.getDeckOne();
         player2deck = deck.getDeckTwo();
         
                  
         image1 = "";
         image2 = "";
         
         player1Card = new ImageIcon(image1);
         cardOne.setIcon(player1Card);
         panel.add(cardOne);
         
         player2Card = new ImageIcon(image2);
         cardTwo.setIcon(player2Card);
         panel.add(cardTwo);
         
         panel.add(p1);
         panel.add(p2);
         
         p1.setText("Player 1 card");
         panel.add(p1);
         p2.setText("Player 2 card");
         panel.add(p2);
      }
   }
   
}