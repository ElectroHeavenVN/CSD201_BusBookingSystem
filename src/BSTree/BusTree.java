/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import Entities.Bus;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.function.Consumer;

/**
 *
 * @author EHVN
 */
public class BusTree {
    private TreeNode root;

    public BusTree() {
        this.root = null;
    }

    public BusTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }

    public void insert(Bus bus) {
        TreeNode newNode = new TreeNode(bus);
        if (root == null) {
            root = newNode;
        } else {
            TreeNode current = root;
            TreeNode parent;
            while (true) {
                parent = current;
                int value = bus.getCode().compareTo(current.getValue().getCode());
                if (value < 0) {
                    current = current.getLeft();
                    if (current == null) {
                        parent.setLeft(newNode);
                        return;
                    }
                } else if (value > 0) {
                    current = current.getRight();
                    if (current == null) {
                        parent.setRight(newNode);
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }
    
    public void InOrderTraversal(Consumer<TreeNode> action) {
        InOrderTraversal(root, action);
    }

    private void InOrderTraversal(TreeNode node, Consumer<TreeNode> action) {
        if (node != null) {
            InOrderTraversal(node.getLeft(), action);
            action.accept(node);
            InOrderTraversal(node.getRight(), action);
        }
    }

    public void PreOrderTraversal(Consumer<TreeNode> action) {
        PreOrderTraversal(root, action);
    }

    private void PreOrderTraversal(TreeNode node, Consumer<TreeNode> action) {
        if (node != null) {
            action.accept(node);
            PreOrderTraversal(node.getLeft(), action);
            PreOrderTraversal(node.getRight(), action);
        }
    }

    public void PostOrderTraversal(Consumer<TreeNode> action) {
        PostOrderTraversal(root, action);
    }

    private void PostOrderTraversal(TreeNode node, Consumer<TreeNode> action) {
        if (node != null) {
            PostOrderTraversal(node.getLeft(), action);
            PostOrderTraversal(node.getRight(), action);
            action.accept(node);
        }
    }

    public void BreadthFirstTraversal(Consumer<TreeNode> action) {
        if (root == null) {
            return;
        }
        Queue queue = new Queue();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            action.accept(current);
            if (current.getLeft() != null) {
                queue.add(current.getLeft());
            }
            if (current.getRight() != null) {
                queue.add(current.getRight());
            }
        }
    }

    // public Bus Search(Function<TreeNode, Boolean> condition) {
    //     return Search(root, condition);
    // }

    // private Bus Search(TreeNode node, Function<TreeNode, Boolean> condition) {
    //     if (node == null) {
    //         return null;
    //     }
    //     if (condition.apply(node)) {
    //         return node.getValue();
    //     }
    //     Bus leftResult = Search(node.getLeft(), condition);
    //     if (leftResult != null) {
    //         return leftResult;
    //     }
    //     return Search(node.getRight(), condition);
    // }

    public Bus SearchByCode(String code) {
        return SearchByCode(root, code);
    }

    private Bus SearchByCode(TreeNode node, String code) {
        if (node == null) {
            return null;
        }
        int cmp = code.compareTo(node.getValue().getCode());
        if (cmp < 0) {
            return SearchByCode(node.getLeft(), code);
        } else if (cmp > 0) {
            return SearchByCode(node.getRight(), code);
        } else {
            return node.getValue();
        }
    }

    public void Delete(Bus bus) {
        root = Delete(root, bus);
    }

    private TreeNode Delete(TreeNode node, Bus bus) {
        if (node == null) {
            return null;
        }
        int cmp = bus.getCode().compareTo(node.getValue().getCode());
        if (cmp < 0) {
            node.setLeft(Delete(node.getLeft(), bus));
        } else if (cmp > 0) {
            node.setRight(Delete(node.getRight(), bus));
        } else {
            if (node.getLeft() == null) {
                return node.getRight();
            } else if (node.getRight() == null) {
                return node.getLeft();
            }
            TreeNode successor = FindMin(node.getRight());
            node.setValue(successor.getValue());
            node.setRight(Delete(node.getRight(), successor.getValue()));
        }
        return node;
    }

    private TreeNode FindMin(TreeNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public void Clear() {
        root = null;
    }

    public boolean IsEmpty() {
        return root == null;
    }

    public int Size() {
        return Size(root);
    }

    private int Size(TreeNode node) {
        if (node == null) {
            return 0;
        }
        return 1 + Size(node.getLeft()) + Size(node.getRight());
    }

    public void Balance() {
        ArrayList<TreeNode> nodes = new ArrayList<>();
        InOrderTraversal(node -> {
            nodes.add(node);
        });
        root = BuildBalancedTree(nodes, 0, nodes.size() - 1);
    }

    private TreeNode BuildBalancedTree(ArrayList<TreeNode> nodes, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end) / 2;
        TreeNode node = nodes.get(mid);
        node.setLeft(BuildBalancedTree(nodes, start, mid - 1));
        node.setRight(BuildBalancedTree(nodes, mid + 1, end));
        return node;
    }

    // additional methods

    public boolean loadFromFile(String fileName) {
        File f = new File(fileName);
        if (!f.exists()) {
            return false;
        }
        try {
            BufferedReader br = new BufferedReader(new FileReader(f));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 6) {
                    String code = parts[0].trim();
                    String busName = parts[1].trim();
                    int seat = Integer.parseInt(parts[2].trim());
                    int booked = Integer.parseInt(parts[3].trim());
                    double departTime = Double.parseDouble(parts[4].trim());
                    double rate = Double.parseDouble(parts[5].trim());
                    Bus bus = new Bus(code, busName, seat, booked, departTime, rate);
                    insert(bus);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error reading file: " + e.getMessage());
            return false;
        }
        return true;
    }
}
