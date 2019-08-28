package com.accp.pojo;


import java.io.Serializable;
import java.util.Objects;

public class Line implements Serializable {
    private Integer lineid;
    private Integer lineno;
    private String linename;
    private Integer length;

    @Override
    public String toString() {
        return "Line{" +
                "lineid=" + lineid +
                ", lineno=" + lineno +
                ", linename='" + linename + '\'' +
                ", length=" + length +
                '}';
    }

    public Line() {
    }

    public Line(Integer lineid, Integer lineno, String linename, Integer length) {
        this.lineid = lineid;
        this.lineno = lineno;
        this.linename = linename;
        this.length = length;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return Objects.equals(lineid, line.lineid) &&
                Objects.equals(lineno, line.lineno) &&
                Objects.equals(linename, line.linename) &&
                Objects.equals(length, line.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineid, lineno, linename, length);
    }

    public Integer getLineid() {
        return lineid;
    }

    public void setLineid(Integer lineid) {
        this.lineid = lineid;
    }

    public Integer getLineno() {
        return lineno;
    }

    public void setLineno(Integer lineno) {
        this.lineno = lineno;
    }

    public String getLinename() {
        return linename;
    }

    public void setLinename(String linename) {
        this.linename = linename;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
