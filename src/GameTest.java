import static org.junit.Assert.*;
import java.awt.Color;
import org.junit.Test;

public class GameTest {
    @Test
    public void gameTest() {
        Game game = new Game(3, Color.RED);
        assertEquals(game.max_depth(), 3);
    }
    
    @Test
    public void testMaxDepth() {
        Game game = new Game(3, Color.RED);
        assertEquals(game.max_depth(), 3);
    }

    @Test
    public void testRandomInit() {
        Game game = new Game(3, Color.RED);
        assertNotNull(game.getRoot().getTopLeftTree());
    }
    
    
    @Test
    public void testGetBlock() {
        Game game = new Game(3, Color.RED);
        IBlock root = game.getRoot();
        
        root.smash(3);
        
        IBlock topLeft =  root.getTopLeftTree();
        IBlock topRight =  root.getTopRightTree();
        IBlock botLeft =  root.getBotLeftTree();
        IBlock botRight =  root.getBotRightTree();
        
        assertEquals(game.getBlock(0), root);
        assertEquals(game.getBlock(1), topLeft);
        assertEquals(game.getBlock(2), topRight);
        assertEquals(game.getBlock(3), botRight);
        assertEquals(game.getBlock(4), botLeft);
    }
    
    @Test
    public void testGetRoot() {
        Game game = new Game(3, Color.RED);
        IBlock root = game.getRoot();
        
        assertEquals(game.getBlock(0), root);
    }
    

    @Test
    public void testSwap() {
        Game game = new Game(3, Color.RED);
        IBlock root = game.getRoot();
        
        root.smash(3);
        
        IBlock topLeft =  root.getTopLeftTree();
        IBlock topRight =  root.getTopRightTree();
        IBlock botLeft =  root.getBotLeftTree();
        IBlock botRight =  root.getBotRightTree();
        
        root.smash(3);

        game.swap(1, 2);
        assertEquals(topRight, root.getTopLeftTree());
        assertEquals(topLeft, root.getTopRightTree());
        
        game.swap(2, 3);
        assertEquals(topLeft, root.getBotRightTree());
        assertEquals(botRight, root.getTopRightTree());
        
        game.swap(3, 4);
        assertEquals(topLeft, root.getBotLeftTree());
        assertEquals(botLeft, root.getBotRightTree());
        
    }

    @Test
    public void testFlatten() {
        Game game = new Game(3, Color.RED);
        IBlock root = game.getRoot();
        
        root.smash(3);
        
        IBlock[][] grid = game.flatten();
    
        IBlock topLeft =  root.getTopLeftTree();
        IBlock topRight =  root.getTopRightTree();
        
        assertEquals(grid[0][0], topLeft);
        assertEquals(grid[0][1], topLeft);
        assertEquals(grid[0][2], topLeft);
        assertEquals(grid[0][3], topLeft);
        assertEquals(grid[0][4], topRight);
    }

    @Test
    public void testPerimeterScore() {
        Game game = new Game(3, Color.RED);
        IBlock root = game.getRoot();

        root.smash(3);
        
        IBlock topLeft =  root.getTopLeftTree();
        IBlock topRight =  root.getTopRightTree();
        IBlock botLeft =  root.getBotLeftTree();
        IBlock botRight =  root.getBotRightTree();
        
        root.setColor(null);
        topLeft.setColor(Color.RED);
        topRight.setColor(Color.WHITE);
        botLeft.setColor(Color.WHITE);
        botRight.setColor(Color.WHITE);
        
        assertEquals(8, game.perimeter_score());
    }
    
    @Test
    public void testSetRoot() {
        Game game1 = new Game(3, Color.RED);
        
        Game game2 = new Game(3, Color.RED);
        IBlock root2 = game2.getRoot();

        game1.setRoot(root2);
        
        assertEquals(game1.getRoot(), root2);
    }
    

}
