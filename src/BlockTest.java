import static org.junit.Assert.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class BlockTest {
    @Test
    public void blockTest() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        assertEquals(root.depth(), 0);
        assertEquals(topLeft, root.getTopLeft());
        assertEquals(botRight, root.getBotRight());
        assertNull(root.getParent());
        assertNull(root.getColor());
    }

    @Test
    public void testDepth() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        assertEquals(root.depth(), 0);
    }

    @Test
    public void testSmash() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        root.smash(3);
        
        Block topLeftTree = (Block) root.getTopLeftTree();
        Block topRightTree = (Block) root.getTopRightTree();
        Block botRightTree = (Block) root.getBotRightTree();
        Block botLeftTree = (Block) root.getBotLeftTree();
        
        assertEquals(topLeftTree.getTopLeft().getX(), 0);
        assertEquals(topLeftTree.getTopLeft().getY(), 0);
        assertEquals(topLeftTree.getBotRight().getX(), 4);
        assertEquals(topLeftTree.getBotRight().getY(), 4);

        
        assertEquals(topRightTree.getTopLeft().getX(), 4);
        assertEquals(topRightTree.getTopLeft().getY(), 0);
        assertEquals(topRightTree.getBotRight().getX(), 8);
        assertEquals(topRightTree.getBotRight().getY(), 4);
        
        assertEquals(botRightTree.getTopLeft().getX(), 4);
        assertEquals(botRightTree.getTopLeft().getY(), 4);
        assertEquals(botRightTree.getBotRight().getX(), 8);
        assertEquals(botRightTree.getBotRight().getY(), 8);
        
        assertEquals(botLeftTree.getTopLeft().getX(), 0);
        assertEquals(botLeftTree.getTopLeft().getY(), 4);
        assertEquals(botLeftTree.getBotRight().getX(), 4);
        assertEquals(botLeftTree.getBotRight().getY(), 8);
        
    }

    @Test
    public void testChildren() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        List<IBlock> children = new ArrayList<IBlock>();
        assertEquals(root.children(), children);
        
        root.smash(3);
        
        children.add(root.getTopLeftTree());
        children.add(root.getTopRightTree());
        children.add(root.getBotRightTree());
        children.add(root.getBotLeftTree());
        
        assertEquals(root.children(), children);
    }

    
    @Test
    public void testRotate() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        root.smash(3);
        
        Block topLeftTree = (Block) root.getTopLeftTree();
        Block topRightTree = (Block) root.getTopRightTree();
        Block botLeftTree = (Block) root.getBotLeftTree();
        Block botRightTree = (Block) root.getBotRightTree();
        topLeftTree.smash(3);
        
        root.rotate();
        
        assertEquals(root.getTopLeftTree(), botLeftTree);
        assertEquals(root.getTopRightTree(), topLeftTree);
        assertEquals(root.getBotRightTree(), topRightTree);
        assertEquals(root.getBotLeftTree(), botRightTree);
    }
    
    @Test
    public void testGetColor() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        root.smash(3);
        assertNull(root.getColor());
    }
    
    @Test
    public void testSetColor() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        root.setColor(Color.RED);

        assertEquals(root.getColor(), Color.RED);
    }
    
    @Test
    public void testGetTopLeft() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);

        assertEquals(root.getTopLeft(), topLeft);
    }
    
    @Test
    public void testGetBotRight() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);

        assertEquals(root.getBotRight(), botRight);
    }

    @Test
    public void testIsleaf() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block root = new Block(topLeft, botRight, 0, null);
        
        assertTrue(root.isleaf());
        
        root.smash(3);
        
        assertFalse(root.isleaf());
        assertTrue(root.getTopLeftTree().isleaf());
        assertTrue(root.getTopRightTree().isleaf());
        assertTrue(root.getBotLeftTree().isleaf());
        assertTrue(root.getBotRightTree().isleaf());
    }
    
    @Test
    public void testSetTopLeftTree() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block block1 = new Block(topLeft, botRight, 0, null);
        Block block2 = new Block(topLeft, botRight, 0, null);
        
        block1.setTopLeftTree(block2);

        assertEquals(block1.getTopLeftTree(), block2);
        
    }
    
    @Test
    public void testSetTopRightTree() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block block1 = new Block(topLeft, botRight, 0, null);
        Block block2 = new Block(topLeft, botRight, 0, null);
        
        block1.setTopRightTree(block2);

        assertEquals(block1.getTopRightTree(), block2);
        
    }
    
    @Test
    public void testSetBotRightTree() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block block1 = new Block(topLeft, botRight, 0, null);
        Block block2 = new Block(topLeft, botRight, 0, null);
        
        block1.setBotRightTree(block2);

        assertEquals(block1.getBotRightTree(), block2);
        
    }
    
    @Test
    public void testSetBotLeftTree() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block block1 = new Block(topLeft, botRight, 0, null);
        Block block2 = new Block(topLeft, botRight, 0, null);
        
        block1.setBotLeftTree(block2);

        assertEquals(block1.getBotLeftTree(), block2);
        
    }

    @Test
    public void testSetParent() {
        Point topLeft = new Point(0, 0);
        Point botRight = new Point(8, 8);
        Block block1 = new Block(topLeft, botRight, 0, null);
        Block block2 = new Block(topLeft, botRight, 0, null);
        
        block1.setTopLeftTree(block2);
        block2.setParent(block1);
        assertEquals(block2.getParent(), block1);
        
    }
}
