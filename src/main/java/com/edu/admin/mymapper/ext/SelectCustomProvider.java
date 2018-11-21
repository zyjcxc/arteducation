package com.edu.admin.mymapper.ext;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Iterator;
import java.util.Set;

/**
 * 扩展Mapper方法
 * @author mengqa
 * @date 2018-03-31
 **/
public class SelectCustomProvider extends MapperTemplate {


    public SelectCustomProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    public String selectByCustom(MappedStatement ms) {
        Class<?> entityClass = this.getEntityClass(ms);

        this.setResultType(ms, entityClass);
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, this.tableName(entityClass)));

        sql.append("<where>");
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        Iterator var4 = columnList.iterator();

        while(var4.hasNext()) {
            EntityColumn column = (EntityColumn)var4.next();
            /*if ("createTime".equalsIgnoreCase(column.getProperty()) || "updateTime".equalsIgnoreCase(column.getProperty())) {
                continue;
            }
            if (column.getProperty().contains("begintime") || column.getProperty().contains("begindate")) {
                String columnName = column.getColumn();
                String columnHolder = column.getColumnHolder();
                sql.append(getIfNotNull(null, column, " AND " + columnName + " >= " + columnHolder, false));
                continue;
            }
            if (column.getProperty().contains("begintime") || column.getProperty().contains("begindate")) {
                String columnName = column.getColumn();
                String columnHolder = column.getColumnHolder();
                sql.append(getIfNotNull(null, column, " AND " + columnName + " >= " + columnHolder, false));
                continue;
            }*/
            sql.append(getIfNotNull(null, column, " AND " + column.getColumnEqualsHolder(), true));
        }
        sql.append("</where>");

        setOrderBy(sql);

//        sql.append(SqlHelper.orderByDefault(entityClass));
        return sql.toString();
    }

    private void setOrderBy(StringBuilder sql) {
        String orderBy = "${orderBy}";
        sql.append("<if test=\"");
        sql.append("orderBy != null and orderBy != ''");
        sql.append("\">");
        sql.append(orderBy);
        sql.append("</if>");
    }

    private static String getIfNotNull(String entityName, EntityColumn column, String contents, boolean empty) {
        StringBuilder sql = new StringBuilder();
        sql.append("<if test=\"");
        if (StringUtil.isNotEmpty(entityName)) {
            sql.append(entityName).append(".");
        }

        sql.append(column.getProperty()).append(" != null");
        if (empty && column.getJavaType().equals(String.class)) {
            sql.append(" and ");
            if (StringUtil.isNotEmpty(entityName)) {
                sql.append(entityName).append(".");
            }

            sql.append(column.getProperty()).append(" != '' ");
        }

        sql.append("\">");
        sql.append(contents);
        sql.append("</if>");
        return sql.toString();
    }
}
