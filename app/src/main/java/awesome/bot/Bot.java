package awesome.bot;

import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDABuilder;

import javax.security.auth.login.LoginException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Bot {
    public static void main(String[] args) throws LoginException, IOException {
        JDABuilder builder = new JDABuilder(AccountType.BOT);

		Path tokenFilePath = Path.of("token.txt");
		String token = Files.readString(tokenFilePath);
		token = token.replaceAll("\\x0d", " ").trim();

		System.out.println(token);
		
		builder.setToken(token);
		builder.buildAsync();

		System.out.println("Bot's working!");
    }
}
