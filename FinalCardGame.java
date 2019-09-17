//Justin Vuong

//For the players but look at the index in the string. Then you put the first character at the end after the hand is done
import java.util.Arrays;
import java.util.Scanner;

public class FinalCardGame {
 
	public static String[] dealCard(int numIterations, int index, String[] deck) {//This method deals cards from the deck
		String[] myHand = new String[numIterations]; //Deals a given number of cards
		for (int y = 0; y < numIterations; y++) {
			myHand[y] = deck[index + y];
		}
		return myHand; //returns an array in case you want to be dealt more than one card at a time
	}
	
	public static String nameCard(String card) {
	// The cards in the deck only have the minimum amount of information
	// required meaning they do not make sense on their own. This method
	// will interpret the cards and give them names that can be recognized.

	//The format of a card is 
	//(number) [space] (First letter of its suit) ex "8 C" is 8 of clubs 
	int numberValue = Integer.parseInt(card.substring(0, card.indexOf(' '))); //find the number portion of the card
	String cardName = "";
	if (numberValue > 13) {
		cardName = "Ace of ";
	}
	else if(numberValue > 12) {
		cardName = "King of ";
	} else if (numberValue > 11) {
		cardName = "Queen of " ;
	} else if (numberValue > 10) {
		cardName = "Jack of ";
	} else if (numberValue > 9) {
		cardName = "Ten of ";
	}else if (numberValue > 8) {
		cardName = "Nine of ";
	}else if (numberValue > 7) {
		cardName = "Eight of ";
	}else if (numberValue > 6) {
		cardName = "Seven of ";
	}else if (numberValue > 5) {
		cardName = "Six of ";
	} else if (numberValue > 4) {
		cardName = "Five of ";
	}else if (numberValue > 3) {
		cardName = "Four of ";
	}else if (numberValue > 2) {
		cardName = "Three of ";
	}else if (numberValue > 1) {
		cardName = "Deuce of ";
	}
	else {
		cardName = "One of ";
	}

	char suit = card.substring(card.indexOf(' ') + 1, card.length()).charAt(0); //Finds and interprets the character that corresponds to a suit
	if (suit == 'D') {
		cardName += "Diamonds";
	} else if (suit == 'C') {
		cardName += "Clubs";
	} else if (suit == 'H') {
		cardName += "Heart";
	} else {
		cardName += "Spades";
	}
		
	return cardName;	
	}

	public static String nameHand(String handCode) {
	
		//Similar to the cards, the hands do not make sense on their own. This method will put it into words.
		//The typical code given to any 5 cards starts with the hand. If the first character in the code is a
		//1 then those 5 cards only have a high card. If the first character is a 9, then its a straight flush
		//Following the initial number, the next number (separated by a comma) describes the card values of the 
		//hand for example "1,5" tells me that the hand is 5 cards with the highest card being a 5. The same idea 
		//goes for 3 and 4 of a kind, "8,8" means a 4 of a kind of 8's. For the hands where 2 players can be made
		//by 2 player's in the same hand, the rest of the cards in descending order follow to finish off the code. 
		//ex. "2,5,10,9,6" describes the 2 fives, a ten, a nine, and a six.
	
		//Looks an integer that describes the type of hand ie 4 for 3 of a kind
		int hand = Integer.parseInt(handCode.substring(0,1)); 
	
		//Looks for the second number in the code
		String type = handCode.substring(handCode.indexOf(",") + 1, handCode.indexOf(",",2)); 
	
		//If the number of the card is greater than 10, the cards have special names
		if (type.length() == 2) { //can't put else statement at the end of this because 10 is a possibility
			if(type.equals("11")) {
				type = "Jack";
			} else if(type.equals("12")) {
				type = "Queen";
			} else if (type.equals("13")) {
				type = "King";
			}else if (type.equals("14")) {
				type = "Ace";
			}
		}
	
		//Formatting for output
		if(hand == 1) {
			return "High Card of " + type;
		} else if (hand == 2) {
			return "Pair of " + type + "'s";
		}  else if(hand == 3) {
			return type + " High Two Pairs";
		}else if (hand == 4) {
			return "3 " + type +"'s";
		}else if (hand == 5) {
			return type + " High Straight";
		}    else if (hand == 6) {
			return type + " High Flush";
		}else if (hand == 7) {
			return "Full House " + type + "'s";
		}else if (hand == 8) {
			return "4 " + type +"'s";
		}else if(type.equals("14")){
		  return "Royal Flush!";
		} else {
			return type + " High Straight Flush";
		}
	
	}

	public static void shuffle(String[] deck) {
		//Shuffles cards
		int rndIndex;
		String tempCard;

		for (int x = 0; x < 52; x++) {
			rndIndex = (int) (Math.random() * 52);
			tempCard = deck[rndIndex];
			deck[rndIndex] = deck[x];
			deck[x] = tempCard;

		}
	}

	public static String[] isSameCard(String[] hand) {
		
		//This method looks for repeating numbers in the hand. First it will identify all of cards with unique face values 
		//Then find the frequency of each
		
		int index = 1; //Keeps track of what the index of the next free spot spot is in the array
		int[] uniqueValues = { -1, -1, -1, -1, -1 };
		int[] count = new int[5]; // Counts frequency
		uniqueValues[0] = Integer.parseInt(hand[0].substring(0, hand[0].indexOf(' ')));
		count[0] = 1;
		boolean done = false;
		
		//Runs through every card in the hand to see if it is already in unique values, if it is then increase the array count
		for (int y = 1; y < 5; y++) {
			done = false;
			for (int x = 0; x < 5; x++) {
				if (Integer.parseInt(hand[y].substring(0, hand[y].indexOf(' '))) == uniqueValues[x]) {
					count[x]++;
					done = true;
				} else if (x == 4 && done == false) {//If by the end the face value is not found in the array add it to the end
					uniqueValues[index] = Integer.parseInt(hand[y].substring(0, hand[y].indexOf(' ')));
					count[index]++;
					index++;// with the index of the unique values.
				}
			}
		}
		
		//Only want an array with the unique values in them, do not want empty spots that are created when there is a reoccurring face value
		int[] uniqueNumHand = new int[index];
		for (int u = 0; u < index; u++) {
			uniqueNumHand[u] = uniqueValues[u];
		}
		
	// Array where index 0 is for pairs, 1 is for 3 of a kinds, 2 is for 4 of a kind. The sets of cards will be classified in their 		
	// respective index and will be marked by their face value followed by a comma to separate multiple sets
	String[] sameCards = {"","","","",""}; 
		
	//Filling in the array based on the values in the array "count"
	for (int y = 0; y < uniqueNumHand.length; y++) {
		sameCards[count[y] - 1] += uniqueNumHand[y] + ",";
	}
				
	//Trying to sort the cards within their categories. (ie. want the highest single at the beginning of the index 0) 
	//The idea is that if there is more than one set of  a set, there has to be at least 4 characters so i will sort the ones that are longer than 3 characters
	for (int x = 0; x < 5; x++) {
		if (sameCards[x].length() > 3) {
			String temp = sameCards[x];
			String[] cards = (temp.split(","));
			int[] sortCard = new int[cards.length];

			// Convert the array from String to int
			for (int h = 0; h < sortCard.length; h++) {
				sortCard[h] = Integer.parseInt(cards[h]);
			}

			Arrays.sort(sortCard);
			sameCards[x] = "";

				// Putting cards back in the array but in order
			for (int u = 0; u < sortCard.length; u++) {
				sameCards[x] += sortCard[u] + ",";
			}
		}
	}
		
	//In the singles category for most hands I need the highest card in case there is a tie between players
	String isThisHighestCard = sameCards[0].split(",")[sameCards[0].split(",").length - 1];
		
	//If a player has a full house then they have no singles so a place holder is put that is never referenced but satisfies parameters of other methods
	if (isThisHighestCard == "") {
		sameCards[4] = "0";
	} else { //In every other case, the 4th index in sameCards is set to the highest single card
		sameCards[4] = isThisHighestCard;
	}

	return sameCards;
}

	public static char checkSuit(String[] hand) {
		//Checks to see if all cards in hand are the same. If they are then return the first letter of that suit
		//If none then return "n"
		
		boolean stopChecking = false;
		char suit = hand[0].charAt(hand[0].length() - 1);
		for (int f = 0; f < hand.length; f++) {
			if (hand[f].charAt(hand[f].length() - 1) != suit && stopChecking == false) {
				stopChecking = true;
			}
		}
		
		if (stopChecking == false) {
			return suit;
		} else {
			return 'n';
		}
	}

	public static int checkRun(String[] hand) {

		// Extracting the numbers of the cards and then sorting them
		int[] cardNumbers = new int[5];

		for (int y = 0; y < hand.length; y++) {
			cardNumbers[y] = Integer.parseInt(hand[y].substring(0, hand[y].indexOf(' ')));
		}
		Arrays.sort(cardNumbers);

		// Ace can count as a 1 or 14
		if (cardNumbers[4] == 14 && cardNumbers[0] == 2) {
			cardNumbers[4] = 1;
			Arrays.sort(cardNumbers);
		}

		//Checks if numbers are in sequence
		boolean isConsecutiveNumbers = true;
		for (int q = 1; q < cardNumbers.length; q++) {
			if (cardNumbers[q - 1] + 1 != cardNumbers[q]) {
				isConsecutiveNumbers = false;
			}
		}

		//If the numbers are in sequence then return the highest number. If not then return -1
		if (isConsecutiveNumbers == false) {
			return -1;
		} else {
			return cardNumbers[4];
		}
	}

	public static String scoreHand(String[] sameCards, int straight, char suit, int highCard) {
	
		//Based on all the data that has been collected on the cards so far, the best hand that those cards form is found
		if (straight > 0 && suit != 'n') {//Straight flush
			return "9," + straight+",";
		} else if (sameCards[3].length() > 0) {//4 of a kind
			return "8," + sameCards[3]+",";
		} else if (sameCards[2].length() > 0 && sameCards[1].length() > 0) { //Full house, can't have another full house of the same face value therefore no need to keep track of high card
			return "7," + sameCards[2]+",";
		} else if (suit != 'n') {// Flush
			return "6," + highCard+",";
		} else if (straight > 0) {// Straight. Players tie if high card is the same
			return "5," + straight+",";
		} else if (sameCards[2].length() > 0) {//3 of a kind
			return "4," + sameCards[2].substring(0, sameCards[2].indexOf(',')) + ",";
		} else if (sameCards[1].length() > 3) {// 2 pairs
			String[] cards = sameCards[1].split(",");
			return "3," + cards[1] + "," + cards[0] + "," + highCard + ",";//List the higher pair first
		} else if (sameCards[1].length() > 0) {//Pair
			String[] cards = sameCards[0].split(",");
			return "2," + sameCards[1].substring(0, sameCards[1].indexOf(',')) + "," + cards[2] + "," + cards[1] + "," + cards[0] + ",";
		} else {//High card
			String[] cards = sameCards[0].split(",");
			return "1," + cards[4] + "," + cards[3] + "," + cards[2] + ","+ cards[1] + ","+ cards[0] + ",";
		}
	}

	public static String winner(String[] allHands) {

		//Get the indexes with highest hand (ex. if highest hand is a straight but 2 players have 
		//straights then take both of them and their cards will be compared)
		
		//Right now we are looking at the first number in the codes to tell us the type of hand
		int highestHand = Integer.parseInt(allHands[0].substring(0, 1));
		String index = "0";

		//Index will keep track of which index(es) hold the highest hand (still have not looked at highest card) 
		for (int q = 1; q < allHands.length; q++) {
			if (Integer.parseInt(allHands[q].substring(0, 1)) > highestHand) {
				highestHand = Integer.parseInt(allHands[q].substring(0, 1));
				index = Integer.toString(q);
			} else if (Integer.parseInt(allHands[q].substring(0, 1)) == highestHand) {
				index += "," + Integer.toString(q);
			}
		}

		// This keeps track if 2 players have hands with the same codes. ex straights with the same high card 
		//In the cases where that hand is the highest, the players will tie and split the pot.
		String sameThing = "";
			
		// If there are more than one that have the same hand then we need to look at the highest card
		if (index.length() > 1) {
			String[] compare = index.split(","); //Compare holds the indexes that need to be looked at in an array
			String highHand = allHands[Integer.parseInt(compare[0])];
			int whatIndexInArray = 0;
			sameThing = "0";
				
			//We need to split up the codes given to the 2 hands so we can compare them
			//Right now they are in Strings so we will put them in an array of integers 
				
			for (int g = 1; g < compare.length; g++) {
				String[] currentHighest = highHand.split(",");
				String[] checkIfHigher = allHands[Integer.parseInt(compare[g])].split(",");
	
				int[] intCurrentHighest = new int[currentHighest.length];
				int[] intCheckIfHigher = new int[checkIfHigher.length];
	
				for (int k = 0; k < currentHighest.length; k++) {
					intCurrentHighest[k] = Integer.parseInt(currentHighest[k]);
					intCheckIfHigher[k] = Integer.parseInt(checkIfHigher[k]);
				}
				
				//Now the codes of the 2 hands have been split up and the program will search starting at the first index 
				//of the string to determine which hand is higher (Remember the first index is the type of hand so we already know those are the same).
				boolean haveChosen = false;
				boolean isHigher = false;
				int checkIndex = 0;
	
				do {
					checkIndex++;
					try {
						if (intCheckIfHigher[checkIndex] > intCurrentHighest[checkIndex]) {
							haveChosen = true;
							isHigher = true;
						} else if (intCheckIfHigher[checkIndex] < intCurrentHighest[checkIndex]) {
							haveChosen = true;
							isHigher = false;
						}
					} catch (ArrayIndexOutOfBoundsException e){
						sameThing += Integer.toString(g);
						haveChosen = true;
						isHigher = false;
					}
					
				} while (haveChosen == false);
	
				//If the hand under currentHighestHand is less than the hand then it is replaced and the 
				//process continues  
				if (isHigher == true) {
					highHand = allHands[Integer.parseInt(compare[g])];
					whatIndexInArray=g;
					sameThing = Integer.toString(g);
				}
			}
	
			// sameThing.length() will be 1 if there is only one player with a winning hand (considering high cards)
			//If 2 players get the same hand with the same high card then there will be 2 winners
			if (sameThing.length() == 1) {
				return compare[whatIndexInArray];
			} else {
				String sameCodes = "";
				
				for (int r = 0; r < sameThing.length(); r++) {
					sameCodes += compare[Integer.parseInt(sameThing.substring(r, r + 1))];
				}
				return sameCodes;
			}
		} else { //if only onr player has the highest hand 
			return index;
		}
	}
	
	public static void main(String[] args) {
		int indexInDeck = 0;
		
		// Legend for deck of cards: 2-10 are the respective numbers, 11 = Jack
				// 12 = Queen, 13 = King, 14 = Ace. S = Spades, H = Hearts, C = Clubs, D = Diamonds
		
		String[] deck = { "2 S", "2 H", "2 C", "3 D", "3 S", "3 H", "3 C", "3 D", "4 S",
				"4 H", "4 C", "4 D", "5 S", "5 H", "5 C", "5 D", "6 S", "6 H", "6 C", "6 D", "7 S", "7 H", "7 C", "7 D",
				"8 S", "8 H", "8 C", "8 D", "9 S", "9 H", "9 C", "9 D", "10 S", "10 H", "10 C", "10 D", "11 S", "11 H",
				"11 C", "11 D", "12 S", "12 H", "12 C", "12 D", "13 S", "13 H", "13 C", "13 D", "14 S", "14 H", "14 C", "14 D"};
		
		String remindHands = "The hands from highest to lowest in poker are:\nStraight Flush: A run of five cards in sequence all with "
				+ "the same suit\nFour of a Kind: Four cards of the same face value\nFull House: A set of three and a set of two cards "
				+ "with the different face values\nFlush: Five cards of the same suit\nStraight: Five cards in sequence\nThree of a Kind: "
				+ "Three cards with the same face value\nTwo Pairs: Two sets of two cards that have the same face values\nPair: One set of "
				+ "two cards that have the same face value\nHigh Card: When no player's cards form any hand, the player with the highest card "
				+ "wins.\n\nNote that Aces are high but can still be used as ones in straights. Also, if 2 or more players have the same hand "
				+ "(ie. flush) \nthe winner is decided by the player with the highest face value of the card in the set.\n\n";
		
		Scanner user_input = new Scanner(System.in);
		
		//Find out how may players are playing
		System.out.print("Welcome to a poker simulation game. There are four spots, how many people are playing today? ");
		int numPlayers;
		
		do { 
			numPlayers = user_input.nextInt();
			if(numPlayers < 0) {
				System.out.print("You can not have negative players! Enter a non-negative integer: ");
			} else if(numPlayers > 4){
			  System.out.print("The maximum number of players you can have is 4. Please enter an integer between 1-4 players inclusive: ");
			}
			
		} while (numPlayers<0 || numPlayers > 4);
		
		if(numPlayers == 0) {
			System.out.print("No one to play, what a shame.");
			user_input.close();
			return;
		} else if (numPlayers == 1) {
			System.out.println("Well it looks like you are gonna have to play against yourself. \nIm adding another player for you so you are not lonely.");
			numPlayers = 2;
		}
		
		
		//The string playersInGame keeps track of which players are still in the game, if the player has the money left, 
		//their index (the same one that is used to reference the player's money) is put into this string. This string 
		//is necessary because players that are eliminated can not play the next round
		
		String playersInGame="";
		int[] coins = new int[numPlayers];
					int highestBet = 49;
		for(int c = 0; c < numPlayers; c++) {
			coins[c] = 500; //Default everyone starts with 500 coins
			playersInGame+=c; //Every player's index is added to the string playersInGame
		}
		   
		System.out.print("\nGreat! Now does everyone know how to play poker? (Enter \"n\" for rules or anything else to continue): ");
		
		String answer = user_input.next().trim().toLowerCase();
		 if (answer.equals("n")) {
			 System.out.print("\nHere is a quick run down. Poker is a card game where players face off with wages and win rounds by having "
			 		+ "the highest hand. \nPlayers are eliminated if they have nothing left to bet. A hand can be anything from a pair or even "
			 		+ "a single card but these \nare not strong hand and do not give high chances of winning rounds. In this version, you are "
			 		+ "asked to bet before you see your \ncards meaning you do not often want to bet everything you have on one round.\n\n" 
			 		+ "For your reference, t"+ remindHands.substring(1));
		 }
		
		do {
		  shuffle(deck);
			int pot = 0;
			String finalHand[]= new String[playersInGame.length()];
			String[] nameOfPlayerHands = new String[playersInGame.length()];
		 highestBet = 49;
			for (int x = 0; x < playersInGame.length(); x++) { 
				
				if(highestBet == 49){
				  System.out.print("########################################################################################################"
						+ "\n\nPlayer " + (Integer.parseInt(playersInGame.substring(x,x+1))+1) + ", you get first bet. You have " + coins[Integer.parseInt(playersInGame.substring(x,x+1))] + " coins and the minimum bet is 50 coins.\nHow much would you like to bet? ");
				} else {
				System.out.print("########################################################################################################"
						+ "\n\nPlayer " + (Integer.parseInt(playersInGame.substring(x,x+1))+1) + ", you are up. You have " + coins[Integer.parseInt(playersInGame.substring(x,x+1))] + " coins.\nHow much would you like to bet? You must match the bet of " + highestBet + " coins or go all in: ");	
				}
				int bet;
				boolean validBet = false;
				do {
				  
					//Betting	
					bet = user_input.nextInt();	

					if(highestBet == 49 && bet > coins[Integer.parseInt(playersInGame.substring(x,x+1))]){
					  System.out.print("You only have " + coins[Integer.parseInt(playersInGame.substring(x,x+1))] + " coins. Enter a bet less than or equal to your balance: ");
					} else if(highestBet == 49 && bet >= 50){
					  validBet = true;
					  highestBet = bet;
					} else if(highestBet == 49 && bet < 50) {
						System.out.print("Invalid bet. The minimum bet is 50 coins per round. How many coins would you like to bet this round? ");
					}else if (bet < highestBet && bet != coins[Integer.parseInt(playersInGame.substring(x,x+1))]) { System.out.print("You need to match the " + highestBet + " coin bet or go all in. Enter a bet: ");
					} else if (bet > coins[Integer.parseInt(playersInGame.substring(x,x+1))]) {
						System.out.print("You only have "+ coins[Integer.parseInt(playersInGame.substring(x,x+1))] +" coins, please enter a bet lower than or equal to your balance: ");
					} else if (bet > highestBet){
					  validBet = true;
					  highestBet = bet;
					} else if (bet == coins[Integer.parseInt(playersInGame.substring(x,x+1))]) {
					   validBet = true;
					}else{ //matched exactly the bet
					  validBet = true;
					}
				} while (validBet == false);
					System.out.print("\n\nHighest Bet: " + highestBet + "\n\n");
				coins[Integer.parseInt(playersInGame.substring(x,x+1))] -= bet;
				pot += bet;
		
				char suit = 'n'; // n for placeholder
				int straight = -1;
				
				String[] myHand = dealCard(5, indexInDeck, deck);
		
				//Prepare output
				System.out.println("\nHere is your hand");
				for(int s = 0; s < myHand.length; s++) {
					System.out.print("["+(s+1)+"] ");
					System.out.print(nameCard(myHand[s]) + "\n");
				}
				
				indexInDeck += 5; //Keeps track of what index of the deck has been dealt
				int numCardsSwitched = 8; //Players get up to 10 chances to switch cards
		
				//Looking for input if the player wants to switch their cards
				System.out.print("\nNow that you have seen your hand, you can switch a card for a new one from the deck. You can do this up to 8 times.\nIf you need a reminder of what the hands are in poker enter \"-1\".\nOtherwise enter the number surrounded by square brackets to switch a card or 0 to keep your current ones: ");
				int switchCards;
				do {
					boolean valid;
					do {valid =true;
						switchCards = user_input.nextInt();
						if (switchCards < -1 || switchCards > 5) {
							System.out.print("Invaid Input. Please enter a single integer between -1 and 5 inclusive to decide what to do with your hand.");
							valid = false;
						}
					} while (valid == false);
		
					if(switchCards == -1) {//Player asked for instructions
						System.out.print("\n" + remindHands);	
						System.out.println("Here is your hand");
						for(int s = 0; s < myHand.length; s++) {
							System.out.print("["+(s+1)+"] ");
							System.out.print(nameCard(myHand[s]) + "\n");
						}
						System.out.print("\nEnter the number surrounded by square brackets to switch a card or 0 to keep your current ones: ");
					} else if(switchCards == 0) { //Player wants to hold cards
						numCardsSwitched = 0;
					} else {//Switch Card
						String oldCard = myHand[switchCards-1];
						myHand[switchCards-1] = dealCard(1, indexInDeck, deck)[0]; //Deal a new card
						indexInDeck++;
						numCardsSwitched --;
				 
						if(numCardsSwitched > 0) {
							System.out.print("\n" +"########################################################################################################" 
								+ "\n\n"+nameCard(oldCard) + " -----> was switched for -----> " + nameCard(myHand[switchCards-1]) +  "\nYou can switch up to " 
							 	+ numCardsSwitched + " more times.\n\n------- YOUR HAND ------\n[1] "+ nameCard(myHand[0]) + "\n[2] "+ nameCard(myHand[1]) 
							 	+ "\n[3] "+ nameCard(myHand[2]) +"\n[4] "+ nameCard(myHand[3]) + "\n[5] "+ nameCard(myHand[4]) + "\n\nEnter the number "
							 	+ "surrounded by square brackets to switch a card or 0 to keep your current ones: ");
						}else {
							System.out.print("\n" +"########################################################################################################" 
								+ "\n\n"+nameCard(oldCard) + " -----> was switched for -----> " + nameCard(myHand[switchCards-1]) + "\n\n------- YOUR HAND "
								+ "------\n[1] "+ nameCard(myHand[0]) + "\n[2] "+ nameCard(myHand[1]) + "\n[3] "+ nameCard(myHand[2]) +"\n[4] "+ nameCard(myHand[3]) 
								+ "\n[5] "+ nameCard(myHand[4]));
						}
					}
				} while (numCardsSwitched  > 0);
		
				//Finding reoccurring face values using method isSameCard() described above
				String[] sameCards = isSameCard(myHand);
		
				//There can only be runs or cards with the suit if there are no cards with 2 of the same face value 
				if (sameCards[1].length() == 0 && sameCards[2].length() == 0 && sameCards[3].length() == 0) { // if there are no repeating numbers (on cards) then enter																									// enter
					suit = checkSuit(myHand);
					straight = checkRun(myHand);
				}
		
				//Prepare for output
				String nameOfHand = nameHand(scoreHand(sameCards, straight, suit, Integer.parseInt(sameCards[4])));
				nameOfPlayerHands[x] = nameOfHand; //Store this for future output
		
				System.out.print("\nHERE IS YOUR HAND: " + nameOfHand);
		
				//Store the code of the hand of the player
				finalHand[x] = scoreHand(sameCards, straight, suit, Integer.parseInt(sameCards[4]));
	
				//Put these pauses so the player can take the time to read what is going on
				System.out.print("\n\nEnter anything to continue: ");
				String wait = user_input.next();

			}
		
			//Outputting winner
			int whoWon = 0;
			String winnerOfHand = winner(finalHand);
			String whichWinners = "Player " + (Integer.parseInt(winnerOfHand.substring(0,1)) +1);
		
			if(winnerOfHand.length() > 1) {
				//There are multiple players with the same winning hand ie. 2 players have straights
				for(int i = 0; i < winnerOfHand.length(); i++) {
					//Split the coins evenly
					coins[Integer.parseInt(winnerOfHand.substring(i, i+1))] += Math.floor(pot/winnerOfHand.length());
					if(i>0) {
						whichWinners += ", Player " + ((Integer.parseInt(winnerOfHand.substring(i,i+1)) + 1));
					}
				}
				whoWon = Integer.parseInt(winnerOfHand.substring(0,1));
			
			} else {//There is only one winner
				whoWon = Integer.parseInt(winnerOfHand);
				coins[whoWon] += pot;
			}
				
			System.out.print("\n########################################################################################################"
					+ "\nThe cards are in! The winner of the " + pot + " coin pot is...\n" + whichWinners + " with a " + nameOfPlayerHands[whoWon]);
		
			if(winnerOfHand.length()<4) { //Display the rest of the hands after the winner(s) hand is shown
				System.out.print("\n\nHere are the rest of the hands\n");
				for(int d = 48; d < playersInGame.length() + 48; d++) {
					if (winnerOfHand.indexOf(d) < 0) {
						System.out.println("Player " + (d-47) + " had a " + nameOfPlayerHands[d-48]);
					}
				}
			}
		
		//Print all of the balances of the players that are in
			System.out.print("\nThe current balances are:\n");
			for(int e = 0; e < playersInGame.length(); e++) {
				System.out.print("Player " + (Integer.parseInt(playersInGame.substring(e,e+1))+1) + ": " + coins[Integer.parseInt(playersInGame.substring(e,e+1))] + "\n");
			}
			
			//Determine if the players have enough coins to buy into the next round. If not then they are eliminated
			for(int r = 0; r < playersInGame.length(); r++) {	
				if(coins[Integer.parseInt(playersInGame.substring(r, r+1))] < 50) { 
					System.out.println("\nPlayer " + (Integer.parseInt(playersInGame.substring(r,r+1))+1) + " was eliminated because they have less than 50 coins to buy into the next round.");
					char[] whichPlayersAreIn = playersInGame.toCharArray();
					whichPlayersAreIn[r] = ' ';
					playersInGame = String.valueOf(whichPlayersAreIn);
				
				}
			}
			
			//reset the deck for the next hand
			indexInDeck = 0;
			shuffle(deck);
			playersInGame = playersInGame.replaceAll("\\W", "");
			
			//Changes who gets first bet
			char firstPlayer;
			firstPlayer = playersInGame.charAt(0);
			playersInGame = playersInGame.substring(1,playersInGame.length()) + firstPlayer;
			
		} while (playersInGame.length() > 1); //Stops once there is only one player with more than 50 coins
		
		System.out.print("Game Over. Player " + (Integer.parseInt(playersInGame.substring(0,1)) + 1) + " wins!");
	user_input.close();
	}
}