package io.github.kathyyliang.tulyp;

/**
 * Created by TK on 5/3/16.
 */
/**
 * Data structure to store medication information.
 */
public class Drug {
    private String instructions;
    private String warnings;
    private String start_date;
    private String end_date;
    public Drug(){}
    public Drug(String inst, String warnings, String start_date, String end_date) {
        this.instructions = inst;
        this.warnings = warnings;
        this.start_date = start_date;
        this.end_date = end_date;
    }
}