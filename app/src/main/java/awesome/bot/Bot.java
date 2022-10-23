package awesome.bot;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.entities.User;

import javax.security.auth.login.LoginException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Bot extends ListenerAdapter {
	private String prefix = "#";

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
		Message message = event.getMessage();

		User author = message.getAuthor();
		String content = message.getContentDisplay();
		System.out.printf("%s sent: %s\n", author.getName(), content);

		if (!author.isBot()) {
			try {
				if (message.getMentions().getUsers().get(0).getName().equals("AwesomeBot")) {
					message.reply("My prefix here is "+prefix).queue();
				}
			} catch (IndexOutOfBoundsException e) {}

			if (content.equals(this.prefix+"ping")) {
				message.reply("Pong!").queue();
			} else if (content.equals(this.prefix+"hello")) {
				message.reply("Hello!").queue();
			} else if (content.equals(this.prefix+"greet")) {
				message.reply("Have a nice day!").queue();
			} else if (content.equals(this.prefix+"thank")) {
				message.reply("You're welcome!").queue();
			} else if (content.equals(this.prefix+"whoareyou")) {
				message.reply("I am awesome bot, a bot filled to the brim with awesomeness!").queue();
			}
		}
	}
}
