package com.xuguruogu.auth.dto;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

    /**  */
    private static final long serialVersionUID = -8013343374331726584L;
    private List<T>           rows;
    private int               results;

    public PageResult() {
    }

    public PageResult(List<T> rows, int results) {
        this.rows = rows;
        this.results = results;
    }

    /**
     * Getter method for property <tt>rows</tt>.
     * 
     * @return property value of rows
     */
    public List<T> getRows() {
        return rows;
    }

    /**
     * Setter method for property <tt>rows</tt>.
     * 
     * @param rows value to be assigned to property rows
     */
    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    /**
     * Getter method for property <tt>results</tt>.
     * 
     * @return property value of results
     */
    public int getResults() {
        return results;
    }

    /**
     * Setter method for property <tt>results</tt>.
     * 
     * @param results value to be assigned to property results
     */
    public void setResults(int results) {
        this.results = results;
    }

}