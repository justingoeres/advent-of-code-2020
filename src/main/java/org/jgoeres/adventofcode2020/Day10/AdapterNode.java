package org.jgoeres.adventofcode2020.Day10;

import java.util.ArrayList;
import java.util.List;

public class AdapterNode {
    int joltage;
    List<AdapterNode> parents = new ArrayList<>();
    List<AdapterNode> children = new ArrayList<>();

    private Long totalNumPaths = null;

    public AdapterNode(int joltage) {
        this.joltage = joltage;
    }

    public AdapterNode(int joltage, AdapterNode parent) {
        this.joltage = joltage;
        this.parents.add(parent);
    }

    public void addChild(AdapterNode childNode) {
        children.add(childNode);
    }

    public void addParent(AdapterNode parentNode) {
        parents.add(parentNode);
    }

    public Long calculateTotalNumPaths() {
        // The total number of paths from this node to the endpoint
        // is the SUM of the paths of its children to the endpoint.
        Long sum = 0L;
        for (AdapterNode child : children) {
            if (child.getTotalNumPaths() != null) {
                // if we know this child's total number of paths,
                // add it to the sum.
                sum += child.getTotalNumPaths();
            } else {
                // We don't yet know this child's total number of paths,
                // so calculate it
                sum += child.calculateTotalNumPaths();
            }
        }
        // Set our own total count of paths
        totalNumPaths = sum;
        // Then return the sum
        return sum;
    }

    public Long getTotalNumPaths() {
        return totalNumPaths;
    }

    public void setTotalNumPaths(Long totalNumPaths) {
        this.totalNumPaths = totalNumPaths;
    }
}
