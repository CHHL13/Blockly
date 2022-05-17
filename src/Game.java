import java.awt.Color;
import java.util.LinkedList;
import java.util.Queue;

public class Game implements IGame {
    
    private int max_depth;
    private IBlock root;
    private Color color;
    
    public Game(int max_depth, Color color) {
        this.max_depth = max_depth;
        this.color = color;
        this.root = this.random_init();
    }

    @Override
    public int max_depth() {
        return this.max_depth;
    }

    @Override
    public IBlock random_init() {
        this.root = new Block();
        
        if (this.max_depth >= 1) {
            root.smash(this.max_depth);
        }
        
        return root;
    }

    @Override
    public IBlock getBlock(int pos) {
        int level = 0;
        
        Queue<IBlock> q = new LinkedList<>();
        q.add(root);
        
        while (!q.isEmpty()) {
            IBlock node = q.poll();
            
            if (pos == level) {
                return node;
            }
            
            for (IBlock child : node.children()) {
                q.add(child);
            }
            
            level++;
        }
        
        return null;
    }

    @Override
    public IBlock getRoot() {
        return this.root;
    }

    @Override
    public void swap(int x, int y) {
        IBlock block1 = this.getBlock(x);
        IBlock block2 = this.getBlock(y);
        
        if (block1 != null && block2 != null && block1.depth() == block2.depth()) {
            int xDiff = block2.getTopLeft().getX() - block1.getTopLeft().getX();
            int yDiff = block2.getTopLeft().getY() - block1.getTopLeft().getY();
            
            updateParent(block1, block2);
            ((Block) block1).move(xDiff, yDiff);
            ((Block) block2).move(-xDiff, -yDiff);
        }
        
    }
    
    private void updateParent(IBlock block1, IBlock block2) {
        Block parent1 = (Block) ((Block) block1).getParent();
        Block parent2 = (Block) ((Block) block2).getParent();
        
        int flag = -1;
        
        if (parent1.getTopLeftTree().equals(block1)) {
            parent1.setTopLeftTree(block2);
            flag = 0;
        } else if (parent1.getTopRightTree().equals(block1)) {
            parent1.setTopRightTree(block2);
            flag = 1;
        } else if (parent1.getBotLeftTree().equals(block1)) {
            parent1.setBotLeftTree(block2);
            flag = 2;
        } else if (parent1.getBotRightTree().equals(block1)) {
            parent1.setBotRightTree(block2);
            flag = 3;
        } 
        
        if (!parent1.equals(parent2)) {
            flag = -1;
        }
        
        if (parent2.getTopLeftTree().equals(block2) && flag != 0) {
            parent2.setTopLeftTree(block1);
        } else if (parent2.getTopRightTree().equals(block2) && flag != 1) {
            parent2.setTopRightTree(block1);
        } else if (parent2.getBotLeftTree().equals(block2) && flag != 2) {
            parent2.setBotLeftTree(block1);
        } else if (parent2.getBotRightTree().equals(block2) && flag != 3) {
            parent2.setBotRightTree(block1);
        }
        
        ((Block) block1).setParent(parent2);
        ((Block) block2).setParent(parent1);
        
    }
    

    @Override
    public IBlock[][] flatten() {
        IBlock[][] blocks = 
                new IBlock[(int) Math.pow(2, this.max_depth)][(int) Math.pow(2, this.max_depth)];
        
        Queue<IBlock> q = new LinkedList<IBlock>();
        
        double val = Math.pow(2, this.max_depth) / 8;
        
        q.add(root);
        while (!q.isEmpty()) {
            IBlock node = q.poll();
            
            if (node.getTopLeftTree() == null) {
                Point top = node.getTopLeft();
                Point bot = node.getBotRight();
                
                for (int i = (int) (top.getX() * val); i < (int) (bot.getX() * val); i++) {
                    for (int j = (int) (top.getY() * val); j < (int) (bot.getY() * val); j++) {
                        blocks[j][i] = node;
                    }
                }
            }
            
            for (IBlock child : node.children()) {
                q.add(child);
            }
            
        }
        
        return blocks;
    }

    @Override
    public int perimeter_score() {
        int score = 0;
        
        IBlock[][] blocks = this.flatten();
        
        if (blocks.length > 2) {
            for (int i = 0; i < blocks.length; i++) {
                if (blocks[0][i].getColor() == this.color) {
                    score++;
                }
                
                if (blocks[i][blocks.length - 1].getColor() == this.color) {
                    score++;
                }
                
                if (blocks[blocks.length - 1 - i][0].getColor() == this.color) {
                    score++;
                }
                
                if (blocks[blocks.length - 1][blocks.length - 1 - i].getColor() == this.color) {
                    score++;
                }
                
            }
        }
        
        return score;
    }

    @Override
    public void setRoot(IBlock root) {
        this.root = root;
    }

}
