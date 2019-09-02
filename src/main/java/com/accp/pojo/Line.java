package com.accp.pojo;


import javax.persistence.Entity;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class Line implements Serializable {
    private int lineid;
    private int  lineno;
    private String linename;
    private int  length;

    @Override
    public String toString() {
        return "Line{" +
                "lineid=" + lineid +
                ", lineno=" + lineno +
                ", linename='" + linename + '\'' +
                ", length=" + length +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return lineid == line.lineid &&
                lineno == line.lineno &&
                length == line.length &&
                Objects.equals(linename, line.linename);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineid, lineno, linename, length);
    }

    public int getLineid() {
        return lineid;
    }

    public void setLineid(int lineid) {
        this.lineid = lineid;
    }

    public int getLineno() {
        return lineno;
    }

    public void setLineno(int lineno) {
        this.lineno = lineno;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public Line() {
    }

    public Line(int lineid, int lineno, String linename, int length) {
        this.lineid = lineid;
        this.lineno = lineno;
        this.linename = linename;
        this.length = length;
    }
    public Line(int lineno, String linename, int length) {
        this.lineno = lineno;
        this.linename = linename;
        this.length = length;
    }
}
