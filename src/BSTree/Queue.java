/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package BSTree;

import java.util.ArrayList;

/**
 *
 * @author EHVN
 */
public class Queue {
    private ArrayList<TreeNode> elements;

    public Queue() {
        elements = new ArrayList<>();
    }

    public void add(TreeNode node) {
        elements.add(node);
    }

    public TreeNode poll() {
        if (elements.isEmpty()) {
            return null;
        }
        return elements.remove(0);
    }

    public boolean isEmpty() {
        return elements.isEmpty();
    }

    public void clear() {
        elements.clear();
    }

    public ArrayList<TreeNode> getElements() {
        return elements;
    }
}
