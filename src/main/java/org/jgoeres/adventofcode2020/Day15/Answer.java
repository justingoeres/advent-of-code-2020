package org.jgoeres.adventofcode2020.Day15;

public class Answer {
    private Long previous1;
    private Long previous2 = null;

    public Answer(Long previous1) {
        this.previous1 = previous1;
    }

    public void setNewPrevious1(Long previous1) {
        // Also move the old previous1 to previous2
        this.previous2 = this.previous1;
        this.previous1 = previous1;
    }

    public boolean hadBeenSaid(){
        // This answer *had already been said before* if its 'previous2' is now non-null
        return (this.previous2 != null);
    }
    public Long getTimeBetween() {
        return previous1 - previous2;
    }
}
