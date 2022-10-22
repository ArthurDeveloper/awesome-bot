package awesome.bot;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Bot {
    public static void main(String[] args) throws LoginException, IOException {
		Path tokenFilePath = Path.of(".\\token.txt");
		String token = Files.readString(tokenFilePath);
		token = token.replaceAll("\\x0d", " ").trim();

		JDABuilder builder = JDABuilder.createDefault(token);

		builder.build();

		System.out.println("Bot's working!");
    }
}
