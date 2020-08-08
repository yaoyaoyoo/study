package com.yy.common;

import java.util.List;

/**
 * @author yy
 * @create 2020/8/8 21:12
 * @desc MultiTreeNode多叉树
 */
public class MultiTreeNode {
    public int val;
    public List<MultiTreeNode> children;

    public MultiTreeNode() {}

    public MultiTreeNode(int val) {
        val = val;
    }

    public MultiTreeNode(int val, List<MultiTreeNode> children) {
        this.val = val;
        this.children = children;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public List<MultiTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<MultiTreeNode> children) {
        this.children = children;
    }
}
