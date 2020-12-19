package org.jgoeres.adventofcode2020;

import org.jgoeres.adventofcode2020.Day18.Day18Service;
import org.jgoeres.adventofcode2020.Day18.RunDay18;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class Day18Test {

    @Test
    public void Day18AExample1() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(71);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartA("1 + 2 * 3 + 4 * 5 + 6");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample2() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(51);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartA("1 + (2 * 3) + (4 * (5 + 6))");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample3() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(26);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartA("2 * 3 + (4 * 5)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample4() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(437);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartA("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample5() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(12240);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartA("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18AExample6() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(13632);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartA("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18A() {
        // Let's all pause here to remember the time Justin had a bug
        // in his code that produced a Part A answer of
        // 618693072565803219213285208212796343080102507627213478340...
        // 311867459257301109998191213555964814242001367472876452289...
        // 388804818686159621397409542261452603051680913867863693072...
        // 744262673353894511888788255078386592978542923265377465814...
        // 678220568424281354510591497272707221368220939841159657344...
        // 153547799706717052549886475826365082090922473438355198488...
        // 111521452874033728078822793313890352254520736220304407209...
        // 816273148491096503861335648449311816289817364341029288609...
        // 624624015901612946713674184879490413595342871935679497044...
        // 851338093561637521337287537935830688591695687237209995502...
        // 296781044880797820817927795920853542388338780430669823380...
        // 076876460545357928826780252298967842004308707290199133735...
        // 852218403659530217875089779261194553391069465054983307270...
        // 464122566333940183054543785590554029906464351107978313723...
        // 257928592148092910321656991612163796339228768930970015488...
        // 577701126397941954558486192439287589349972157013256098788...
        // 083567856070422937907715806482310239251162853306237601179...
        // 591160053461779854606334593697738554016064733124582034060...
        // 950653179011973110635197542425463322614122244061116804706...
        // 287356496370711785398535192519875442310229844075781985706...
        // 029620586453461630379864463899835129032849142832422615548
        //
        // which was "too high."
        final BigInteger EXPECTED = BigInteger.valueOf(25190263477788L);
        BigInteger result = BigInteger.ZERO;
        try {
            result = RunDay18.problem18A();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18BExample1() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(231);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartB("1 + 2 * 3 + 4 * 5 + 6");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18BExample2() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(51);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartB("1 + (2 * 3) + (4 * (5 + 6))");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18BExample3() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(46);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartB("2 * 3 + (4 * 5)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18BExample4() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(1445);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartB("5 + (8 * 3 + 9 + 3 * 4 * 3)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18BExample5() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(669060);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartB("5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18BExample6() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(23340);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartB("((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18BPuzzleLine8() {
        Day18Service day18Service = new Day18Service();
        final BigInteger EXPECTED = BigInteger.valueOf(1512);
        BigInteger result = BigInteger.ZERO;
        try {
            result = day18Service.evaluatePartB("(5 + 5) + 2 + 4 + 5 * (8 * 9)");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }

    @Test
    public void Day18B() {
        final BigInteger EXPECTED = BigInteger.valueOf(297139939002972L);
        BigInteger result = BigInteger.ZERO;
        try {
            result = RunDay18.problem18B();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        Assert.assertEquals(EXPECTED, result);
    }
}
