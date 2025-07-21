/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BSTree;

import Entities.Bus;

/**
 *
 * @author EHVN
 */
public class TreeNode {
    private Bus value;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(Bus value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    public Bus getValue() {
        return value;
    }
    public void setValue(Bus value) {
        this.value = value;
    }
    public TreeNode getLeft() {
        return left;
    }
    public void setLeft(TreeNode left) {
        this.left = left;
    }
    public TreeNode getRight() {
        return right;
    }
    public void setRight(TreeNode right) {
        this.right = right;
    }
}
