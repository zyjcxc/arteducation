package com.edu.admin.server.page.table;

public class OrderByObject {

    private boolean isAsc = false;

    private boolean isOrderBy = false;

    private String column;

    public boolean isAsc() {
        return isAsc;
    }

    public void setAsc(boolean asc) {
        isAsc = asc;
    }

    public boolean isOrderBy() {
        return isOrderBy;
    }

    public void setOrderBy(boolean orderBy) {
        isOrderBy = orderBy;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("OrderByObject{");
        sb.append("isAsc=").append(isAsc);
        sb.append(", isOrderBy=").append(isOrderBy);
        sb.append(", column='").append(column).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
