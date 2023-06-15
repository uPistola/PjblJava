package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Pong.*;

abstract class PersistentObjectManager<T> {
    private String fileName;

    public PersistentObjectManager(String fileName) {
        this.fileName = fileName;
    }

    public abstract void save(List<T> objects);

    public abstract List<T> load();

    public abstract void open();
}

class LeaderboardManager extends PersistentObjectManager<PlayerScore> {
    public LeaderboardManager(String fileName) {
        super(fileName);
    }

    @Override
    public void open() {
    	
    	
        List<PlayerScore> scores = load();
        scores.sort((s1, s2) -> Integer.compare(s2.getScore(), s1.getScore()));

        JFrame leaderboardFrame = new JFrame("Leaderboard");
        leaderboardFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        leaderboardFrame.setSize(300, 400);
        leaderboardFrame.setLayout(new BorderLayout());
        leaderboardFrame.setLocationRelativeTo(null);


        DefaultListModel<String> listModel = new DefaultListModel<>();
        int rank = 1;
        for (PlayerScore score : scores) {
            String playerInfo = "Rank " + rank + ": " + score.getPlayerName() + " - " + score.getScore() + " (" + score.getGameName() + ")";
            listModel.addElement(playerInfo);
            rank++;
        }

        JList<String> leaderboardList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(leaderboardList);
        leaderboardFrame.add(scrollPane, BorderLayout.CENTER);

        leaderboardFrame.setVisible(true);
    }

    @Override
    public void save(List<PlayerScore> objects) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("rsc/data/leaderboard.txt", true))) {
            for (PlayerScore score : objects) {
                writer.println(score.getPlayerName() + "," + score.getScore() + "," + score.getGameName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PlayerScore> load() {
        List<PlayerScore> objects = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("rsc/data/leaderboard.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                String playerName = data[0];
                int score = Integer.parseInt(data[1]);
                String gameName = data[2];
                PlayerScore playerScore = new PlayerScore(playerName, score, gameName);
                objects.add(playerScore);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return objects;
    }
}

class PlayerScore {
    private String playerName;
    private int score;
    private String gameName;

    public PlayerScore(String playerName, int score, String gameName) {
        this.playerName = playerName;
        this.score = score;
        this.gameName = gameName;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getScore() {
        return score;
    }

    public String getGameName() {
        return gameName;
    }
}

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Game App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);
        frame.setLayout(new FlowLayout());
        frame.setLocationRelativeTo(null);

        JButton leaderboardButton = new JButton("Leaderboard");
        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LeaderboardManager leaderboardManager = new LeaderboardManager("rsc/data/leaderboard.txt");
                leaderboardManager.open();
            }
        });
        frame.add(leaderboardButton);

        JButton game1Button = new JButton("Tetrix");
        game1Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	Game.currentBlock = new Block();
        		Game.blocks.add(Game.currentBlock);
        		Game.nextBlock = new Block();
        		
        		Gui g = new Gui();
        		
        		try {
        			g.create();
        		} catch (Exception e1) {
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}
        		
        		startLoop();
        	}
        	
        	public void startLoop() {
        		GameLoop loop = new GameLoop();	
        		loop.start();
        	}
            
        });
        frame.add(game1Button);

        JButton game2Button = new JButton("Pong");
        game2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame frame = new GameFrame();
            }
        });
        frame.add(game2Button);

        frame.setVisible(true);
    }
}

