package com.qmcc.sys.common;

import java.util.ArrayList;
import java.util.List;

public class TreeNodeBuilder {
    /**
     * 把没有层级关系的菜单栏变为有层级关系的菜单
     * @param treeNodes
     * @param topPid
     * @return
     */
    public static List<TreeNode> build(List<TreeNode> treeNodes, Integer topPid){
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (TreeNode node1 : treeNodes){
            if (node1.getPid() == topPid){
                treeNodeList.add(node1);
            }
            for (TreeNode node2 : treeNodes){
                if (node2.getPid() == node1.getId()){
                    node1.getChildren().add(node2);
                }
            }
        }
        return treeNodeList;
    }
}
