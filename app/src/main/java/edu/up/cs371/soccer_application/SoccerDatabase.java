package edu.up.cs371.soccer_application;

import android.util.Log;

import edu.up.cs371.soccer_application.soccerPlayer.SoccerPlayer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.Hashtable;
import java.util.Enumeration;
/**
 * Soccer player database -- presently, all dummied up
 * 
 * @author *** put your name here ***
 * @version *** put date of completion here ***
 *
 */
public class SoccerDatabase implements SoccerDB {
    Hashtable<String,SoccerPlayer> stats = new Hashtable<String,SoccerPlayer>();
    /**
     * add a player
     *
     * @see SoccerDB#addPlayer(String, String, int, String)
     */
    @Override
	public boolean addPlayer(String firstName, String lastName,
			int uniformNumber, String teamName) {
         if(stats.containsKey(firstName + "##" + lastName))
        {
             return false;
        }
        SoccerPlayer newPlayer = new SoccerPlayer(firstName, lastName, uniformNumber, teamName);
        String keyforPlayer = firstName + "##" + lastName;
        stats.put(keyforPlayer, newPlayer);
        return true;
	}

    /**
     * remove a player
     *
     * @see SoccerDB#removePlayer(String, String)
     */
    @Override
    public boolean removePlayer(String firstName, String lastName) {
        String key =  firstName + "##" + lastName;

        if(stats.containsKey(key))
        {
            stats.remove(key);
            return true;

        }


        return false;
    }

    /**
     * look up a player
     *
     * @see SoccerDB#getPlayer(String, String)
     */
    @Override
	public SoccerPlayer getPlayer(String firstName, String lastName) {
        String key = firstName +"##" + lastName;
        if (stats.containsKey(key))
        {
            return stats.get(key);
        }
            return null;
    }

    /**
     * increment a player's goals
     *
     * @see SoccerDB#bumpGoals(String, String)
     */
    @Override
    public boolean bumpGoals(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        if (!(stats.containsKey(key))){
            return false;

        }
        stats.get(key).bumpGoals();
        return true;

    }

    /**
     * increment a player's assists
     *
     * @see SoccerDB#bumpAssists(String, String)
     */
    @Override
    public boolean bumpAssists(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        if (!(stats.containsKey(key))){
            return false;

        }
        stats.get(key).bumpAssists();
        return true;
    }

    /**
     * increment a player's shots
     *
     * @see SoccerDB#bumpShots(String, String)
     */
    @Override
    public boolean bumpShots(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        if (!(stats.containsKey(key))){
            return false;

        }
        stats.get(key).bumpShots();
        return true;
    }

    /**
     * increment a player's saves
     *
     * @see SoccerDB#bumpSaves(String, String)
     */
    @Override
    public boolean bumpSaves(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        if (!(stats.containsKey(key))){
            return false;

        }
        stats.get(key).bumpSaves();
        return true;
    }

    /**
     * increment a player's fouls
     *
     * @see SoccerDB#bumpFouls(String, String)
     */
    @Override
    public boolean bumpFouls(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        if (!(stats.containsKey(key))){
            return false;

        }
        stats.get(key).bumpFouls();
        return true;
    }

    /**
     * increment a player's yellow cards
     *
     * @see SoccerDB#bumpYellowCards(String, String)
     */
    @Override
    public boolean bumpYellowCards(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        if (!(stats.containsKey(key))){
            return false;

        }
        stats.get(key).bumpYellowCards();
        return true;
    }

    /**
     * increment a player's red cards
     *
     * @see SoccerDB#bumpRedCards(String, String)
     */
    @Override
    public boolean bumpRedCards(String firstName, String lastName) {
        String key = firstName + "##" + lastName;
        if (!(stats.containsKey(key))){
            return false;

        }
        stats.get(key).bumpRedCards();
        return true;
    }

    /**
     * tells the number of players on a given team
     *
     * @see SoccerDB#numPlayers(String)
     */
    @Override
    // report number of players on a given team (or all players, if null)
	public int numPlayers(String teamName) {
        if(teamName==null)
        {
            return stats.size();

        }

        else
        {
                int team = 0;
                Enumeration <String> e = stats.keys();
                while(e.hasMoreElements()){
                    String key = e.nextElement();
                    SoccerPlayer player = stats.get(key);
                    if(player.getTeamName().equals(teamName)){
                        team++;

                    }


                }
            return team;



        }

	}

    /**
     * gives the nth player on a the given team
     *
     * @see SoccerDB#playerNum(int, String)
     */
	// get the nTH player
	@Override
    public SoccerPlayer playerNum(int idx, String teamName) {
        if(teamName==null)
        {
            int count = 0;
            Enumeration <String> e = stats.keys();
            while(e.hasMoreElements())
            {
                String key = e.nextElement();
                SoccerPlayer player = stats.get(key);
                if(count==idx)
                {
                    return player;
                }

                    count++;
            }


        }



            int countP = 0;

            Enumeration <String> e = stats.keys();
            while(e.hasMoreElements())
            {
                String key = e.nextElement();
                SoccerPlayer player = stats.get(key);
                if(player.getTeamName().equals(teamName))
                {
                    if(countP==idx)
                        return player;

                    countP++;

                }


            }


            return null;
        }

    /**
     * reads database data from a file
     *
     * @see SoccerDB#readData(java.io.File)
     */
	// read data from file
    @Override
	public boolean readData(File file) {
        return file.exists();
	}

    /**
     * write database data to a file
     *
     * @see SoccerDB#writeData(java.io.File)
     */
	// write data to file
    @Override
	public boolean writeData(File file) {
        PrintWriter pw;
        try {
            pw = new PrintWriter(file);
        }

        catch(Exception FileNotFoundException) {
            return false;
        }
        Enumeration <String> e = stats.keys();
        while(e.hasMoreElements())
        {
            String key = e.nextElement();
            SoccerPlayer player = stats.get(key);
            pw.println(logString(player.getFirstName()));
            pw.println(logString(player.getLastName()));
            pw.println(logString(Integer.toString(player.getUniform())));
            pw.println(logString(Integer.toString(player.getGoals())));
            pw.println(logString(Integer.toString(player.getShots())));
            pw.println(logString(Integer.toString(player.getFouls())));
            pw.println(logString(Integer.toString(player.getSaves())));
            pw.println(logString(Integer.toString(player.getYellowCards())));
            pw.println(logString(Integer.toString(player.getRedCards())));
            pw.println(logString(player.getTeamName()));

        }
        pw.close();

        return false;
	}

    /**
     * helper method that logcat-logs a string, and then returns the string.
     * @param s the string to log
     * @return the string s, unchanged
     */
    private String logString(String s) {
        Log.i("write string", s);
        return s;
    }

    /**
     * returns the list of team names in the database
     *
     * @see edu.up.cs371.soccer_application.SoccerDB#getTeams()
     */
	// return list of teams
    @Override
	public HashSet<String> getTeams() {
        return new HashSet<String>();
	}

}
