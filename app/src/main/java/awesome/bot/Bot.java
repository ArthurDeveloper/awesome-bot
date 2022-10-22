package awesome.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import javax.security.auth.login.LoginException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Bot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException, IOException {
		Path tokenFilePath = Path.of("token.txt");
		String token = Files.readString(tokenFilePath);
		token = token.replaceAll("\\x0d", " ").trim();

		JDABuilder builder = JDABuilder.createDefault(token);
		builder.enableIntents(GatewayIntent.MESSAGE_CONTENT);

		JDA jda = builder.build();
		jda.addEventListener(new Bot());

		System.out.println("Bot's working!");
    }

	@Override
	public void onReady(ReadyEvent ready) {
		System.out.println("Ready!");
	}

	@Override
	public void onMessageReceived(MessageReceivedEvent event) {
		System.out.printf("%s sent: %s\n", event.getAuthor().getName(), event.getMessage().getContentDisplay());
	}
}
