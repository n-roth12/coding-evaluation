package com.aa.act.interview.org;

import java.util.Optional;
import java.util.Queue;
import java.util.LinkedList;

public abstract class Organization {

    private Position root;
    
    public Organization() {
        root = createOrganization();
    }
    
    protected abstract Position createOrganization();
    
    /**
     * hire the given person as an employee in the position that has that title
     * 
     * @param person
     * @param title
     * @return the newly filled position or empty if no position has that title
     */
    public Optional<Position> hire(Name person, String title) {
        //your code here
        Queue<Position> queue = new LinkedList<Position>();
        queue.add(root);
        int identifierCount = 1;
        while (!queue.isEmpty()) {
            Position pos = queue.remove();
            if (pos.getTitle().equals(title)) {
                pos.setEmployee(Optional.of(new Employee(identifierCount, person)));
                return Optional.of(pos);
            }
            identifierCount++;
            queue.addAll(pos.getDirectReports());
        }
        return Optional.empty();
    }

    @Override
    public String toString() {
        return printOrganization(root, "");
    }
    
    private String printOrganization(Position pos, String prefix) {
        StringBuffer sb = new StringBuffer(prefix + "+-" + pos.toString() + "\n");
        for(Position p : pos.getDirectReports()) {
            sb.append(printOrganization(p, prefix + "  "));
        }
        return sb.toString();
    }
}
