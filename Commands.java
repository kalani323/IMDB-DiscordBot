package Main;
import java.io.*;
import java.net.*;
import java.util.*;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class Commands extends ListenerAdapter {
	public String start = "!";
	@Override
	public void onGuildMessageReceived(GuildMessageReceivedEvent event) {
		String[] args = event.getMessage().getContentRaw().split(" ");
		// command that gets the movie name and runs the program using it
		if (args[0].startsWith(start)) {
			// create one long string with all (what the user has entered into the discord chat) arguments
			StringBuilder sb = new StringBuilder();
			for (int i=0; i<args.length; i++) {
				sb.append(args[i]);
			}
			// Create a string with the arguments (which should be the name) and remove the !
			String name = sb.toString();
			String movieName = name.substring(1);
			// create imdb object
			IMDB movieIMDB = new IMDB(movieName);
			// use imdb object to get link for movie
			String key = movieIMDB.getID(movieName);
			if (key.equals("Movie/Show does not have a trailer")) {
				event.getChannel().sendMessage("Movie/Show does not have a trailer").queue();
			}
			else {
				String info = movieIMDB.getLink(key);
				event.getChannel().sendMessage(info).queue();
			}
		}
	}
	
} // end comman