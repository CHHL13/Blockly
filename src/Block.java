import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Block implements IBlock {
    private Point topLeft;
    private Point botRight;
    private Color color;
    private int depth;
    private IBlock topLeftTree;
    private IBlock topRightTree;
    private IBlock botLeftTree;
    private IBlock botRightTree;
    private IBlock parent;
    
    public Block() {
        this.topLeft = new Point(0, 0);
        this.botRight = new Point(8, 8);
        this.depth = 0;
        this.parent = null;
    }
    
    public Block(Point topLeft, Point botRight, int depth, IBlock parent) {
        this.topLeft = topLeft;
        this.botRight = botRight;
        this.depth = depth;
        this.parent = parent;
    }

    @Override
    public int depth() {
        return this.depth;
    }

    @Override
    public void smash(int maxDepth) {
        if (maxDepth != this.depth && this.children().size() == 0) {
            
            this.color = null;
            
            int centerX = (this.topLeft.getX() + this.botRight.getX()) / 2;
            int centerY = (this.topLeft.getY() + this.botRight.getY()) / 2;
            
            Point centerPoint = new Point(centerX, centerY);
            Point botPoint = new Point(centerX, this.botRight.getY());
            Point topPoint = new Point(centerX, this.topLeft.getY());
            Point rightPoint = new Point(this.botRight.getX(), centerY);
            Point leftPoint = new Point(this.topLeft.getX(), centerY);
            
            this.topLeftTree = this.createBlock(this.topLeft, centerPoint, this.depth + 1, this);
            this.topRightTree = this.createBlock(topPoint, rightPoint, this.depth + 1, this);
            this.botLeftTree = this.createBlock(leftPoint, botPoint, this.depth + 1, this);
            this.botRightTree = this.createBlock(centerPoint, this.botRight, this.depth + 1, this);
            
        }
        
    }
    
    private IBlock createBlock(Point topLeft, Point botRight, int depth, IBlock parent) {
        IBlock block = new Block(topLeft, botRight, depth, parent);
        int colorNum = (int) (Math.random() * (IBlock.COLORS.length));
        block.setColor(IBlock.COLORS[colorNum]);
        return block;
    }

    @Override
    public List<IBlock> children() {
        if (this.topLeftTree == null) {
            return new ArrayList<>();
        }
        
        ArrayList<IBlock> list = 
                new ArrayList<>(Arrays.asList(this.topLeftTree, this.topRightTree,
                        this.botRightTree, this.botLeftTree));
        
        return list;
    }

    @Override
    public void rotate() {
        if (this.topLeftTree != null) {
            int moveVal = this.topRightTree.getTopLeft().getX() 
                    - this.topLeftTree.getTopLeft().getX();
            
            Block newTopLeftTree = ((Block) this.botLeftTree).move(0, -moveVal);
            Block newTopRightTree = ((Block) this.topLeftTree).move(moveVal, 0);
            Block newBotRightTree = ((Block) this.topRightTree).move(0, moveVal);
            Block newBotLeftTree = ((Block) this.botRightTree).move(-moveVal, 0);
            
            this.topLeftTree = newTopLeftTree;
            this.topRightTree = newTopRightTree;
            this.botRightTree = newBotRightTree;
            this.botLeftTree = newBotLeftTree; 
        } 
    }
    
    public Block move(int x, int y) {
        this.topLeft = new Point(this.topLeft.getX() + x, this.topLeft.getY() + y);
        this.botRight = new Point(this.botRight.getX() + x, this.botRight.getY() + y);
        
        if (this.topLeftTree != null) {
            ((Block) (this.topLeftTree)).move(x, y);
            ((Block) (this.topRightTree)).move(x, y);
            ((Block) (this.botRightTree)).move(x, y);
            ((Block) (this.botLeftTree)).move(x, y);
        }
        
        return this;
    }

    @Override
    public Color getColor() {
        return this.color;
    }

    @Override
    public void setColor(Color c) {
        this.color = c;
    }

    @Override
    public Point getTopLeft() {
        return this.topLeft;
    }

    @Override
    public Point getBotRight() {
        return this.botRight;
    }

    @Override
    public boolean isleaf() {
        return this.topLeftTree == null;
    }

    @Override
    public IBlock getTopLeftTree() {
        return this.topLeftTree;
    }

    @Override
    public IBlock getTopRightTree() {
        return this.topRightTree;
    }

    @Override
    public IBlock getBotLeftTree() {
        return this.botLeftTree;
    }

    @Override
    public IBlock getBotRightTree() {
        return this.botRightTree;
    }
    
    public IBlock getParent() {
        return this.parent;
    }
    
    public void setTopLeftTree(IBlock topLeftTree) {
        this.topLeftTree = topLeftTree;
    }

    public void setTopRightTree(IBlock topRightTree) {
        this.topRightTree = topRightTree;
    }

    public void setBotLeftTree(IBlock botLeftTree) {
        this.botLeftTree = botLeftTree;
    }

    public void setBotRightTree(IBlock botRightTree) {
        this.botRightTree = botRightTree;
    }

    public void setParent(IBlock parent) {
        this.parent = parent;
    }

}
