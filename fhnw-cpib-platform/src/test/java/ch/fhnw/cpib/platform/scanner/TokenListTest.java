package ch.fhnw.cpib.platform.scanner;


import ch.fhnw.cpib.platform.scanner.tokens.Terminal;
import ch.fhnw.cpib.platform.scanner.tokens.TokenList;
import ch.fhnw.cpib.platform.scanner.tokens.Tokens;
import org.junit.Assert;
import org.junit.Test;

public class TokenListTest {

    @Test
    public void testTokenList() {
        TokenList tokenlist = new TokenList();
        Assert.assertEquals(null, tokenlist.nextToken());
        Assert.assertEquals(0, tokenlist.getSize());
        Assert.assertEquals("[]", tokenlist.toString());

        Tokens.Token token1 = new Tokens.Token(Terminal.SENTINEL);
        tokenlist.addToken(token1);
        Assert.assertEquals(token1, tokenlist.nextToken());
        Assert.assertEquals(1, tokenlist.getSize());
        Assert.assertEquals("SENTINEL", tokenlist.toString());

        Tokens.Token token2 = new Tokens.Token(Terminal.SENTINEL);
        tokenlist.addToken(token2);
        Assert.assertEquals(token2, tokenlist.nextToken());
        Assert.assertEquals(2, tokenlist.getSize());
        Assert.assertEquals("SENTINEL, SENTINEL", tokenlist.toString());

        tokenlist.resetCounter();
        Assert.assertEquals(token1, tokenlist.nextToken());
        Assert.assertEquals(2, tokenlist.getSize());
        Assert.assertEquals("SENTINEL, SENTINEL", tokenlist.toString());
    }
}
