package Main;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

public class BotStart {
	public static void main(String[] args) throws LoginException {
		// key given by discord to create bot
		JDABuilder jda = JDABuilder.createDefault("OTAwODE1NzE2MzgxNTAzNTI5.YXG0Aw.SC0Vj6P-3omJSCZ-iuNPemJloz4");
		// to set the activity
		jda.setActivity(Activity.watching("IMDB Show/Movies"));
		// to put status as online
		jda.setStatus(OnlineStatus.ONLINE);
		// connect to commands class
		jda.addEventListeners(new Commands());
		jda.build();
	}
}