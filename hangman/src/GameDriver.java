/*
 * Copyright 2017 Marc Liberatore.
 * Modified 2018 David Wemhoener.
 */

package hangman;

/**
 * A class to play the hangman game.
 */
public class GameDriver {

	public static void main(String[] args) {
          Words words = new Words("data/grade2-words.txt");
          String word = words.pick();
          GameIO gameIO = new ConsoleGameIO();
          GameModel model = new RecursiveGameModel(word);
          GameController gameController = new GameController(gameIO, model);
          gameController.play();
	}

}
