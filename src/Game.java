/**
 * This class is the main class of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game. Users
 * can walk around some scenery. That's all. It should really be extended
 * to make it more interesting!
 *
 * To play this game, create an instance of this class and call the "play"
 * method.
 *
 * This main class creates and initialises all the others: it creates all
 * rooms, creates the parser and starts the game. It also evaluates and
 * executes the commands that the parser returns.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game {
    private Parser parser;
    private Room currentRoom;

    /**
     * Create the game and initialise its internal map.
     */
    public Game() {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms() {
        Room oceano, submarino, caverna, chuva, piratas, tubaroes, arraia, icebearg, tesouro, morte;
        oceano = new Room(
                "perdido no oceano indico. É um pirata procurado pelo governo e está atrás de um tesouro perdido.");
        caverna = new Room("em uma caverna desmoronando. O tesouro está no final.");
        submarino = new Room("em um submario.");

        chuva = new Room("em uma forte tempestade.");
        piratas = new Room("cercado de piratas rivais.");
        tubaroes = new Room("sendo atacado por tubarões assasinos. Atire agora.");
        arraia = new Room("sendo atrapalhados por arraias. Limpe o visor.");
        icebearg = new Room("colidindo com o icebearg. Bombardeie-o");

        tesouro = new Room("em frente ao tesouro. Ganhou o jogo.");
        morte = new Room("morrendo. Perdeu o jogo");

        // Primeira fase
        oceano.setExit("leste", submarino);
        oceano.setExit("sul", caverna);
        oceano.setExit("oeste", chuva);
        oceano.setExit("norte", piratas);

        submarino.setExit("leste", oceano);
        submarino.setExit("sul", caverna);
        submarino.setExit("oeste", chuva);
        submarino.setExit("norte", piratas);

        caverna.setExit("leste", submarino);
        caverna.setExit("sul", tubaroes);
        caverna.setExit("oeste", chuva);
        caverna.setExit("norte", piratas);

        chuva.setExit("leste", submarino);
        chuva.setExit("sul", caverna);
        chuva.setExit("oeste", tubaroes);
        chuva.setExit("norte", piratas);

        piratas.setExit("leste", tubaroes);
        piratas.setExit("oeste", arraia);
        piratas.setExit("norte", icebearg);
        piratas.setExit("sul", caverna);

        tubaroes.setExit("leste", submarino);
        tubaroes.setExit("oeste", arraia);
        tubaroes.setExit("norte", icebearg);
        tubaroes.setExit("sul", caverna);

        arraia.setExit("leste", submarino);
        arraia.setExit("oeste", arraia);
        arraia.setExit("norte", icebearg);
        arraia.setExit("sul", caverna);

        icebearg.setExit("leste", submarino);
        icebearg.setExit("oeste", arraia);
        icebearg.setExit("norte", morte);
        icebearg.setExit("sul", tesouro);

        currentRoom = oceano; // start game outside
    }

    /**
     * Main play routine. Loops until end of play.
     */
    public void play() {
        printWelcome();

        // Enter the main command loop. Here we repeatedly read commands and
        // execute them until the game is over.

        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome() {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     *
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) {
        boolean wantToQuit = false;

        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the
     * command words.
     */
    private void printHelp() {
        System.out.println("Você está perdido. Você está só. Você vagueia");
        System.out.println("por aí no oceano.");
        System.out.println();
        System.out.println("Suas palavras de comando são:");
        parser.showCommands();
    }

    /**
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) {
        if (!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        } else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /**
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     *
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) {
        if (command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        } else {
            return true; // signal that we want to quit
        }
    }
}
