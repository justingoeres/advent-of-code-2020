package org.jgoeres.adventofcode2020.Day14.Op;

import org.jgoeres.adventofcode2020.Day14.CPU;

public interface Op {
    Long ZERO = 0L;

    void execute(CPU cpu);
}
