package com.edu.admin.server.utils;

import com.edu.admin.server.dto.GenerateInput;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class TemplateUtil {

    private static final Logger log = LoggerFactory.getLogger("adminLogger");

    public static String getTemplete(String fileName) {
        return FileUtil.getText(TemplateUtil.class.getClassLoader().getResourceAsStream("generate/" + fileName));
    }

    public static void saveJava(GenerateInput input) {
        String path = input.getPath();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        List<String> beanFieldName = input.getBeanFieldName();
        List<String> beanFieldType = input.getBeanFieldType();
        List<String> beanFieldValue = input.getBeanFieldValue();

        String text = getTemplete("java.txt");
        text = text.replace("{beanPackageName}", beanPackageName).replace("{beanName}", beanName);
        text = text.replace("{descCH}", input.getDescCH());
        text = text.replace("{date}", DateFormatUtils.format(new Date(), DateFormatUtils
				.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));

        String imports = "";
        if (beanFieldType.contains(BigDecimal.class.getSimpleName())) {
            imports += "import " + BigDecimal.class.getName() + ";\n";
        }
        if (beanFieldType.contains(Date.class.getSimpleName())) {
            imports += "import " + Date.class.getName() + ";";
        }

        text = text.replace("{import}", imports);
        String filelds = getFields(beanFieldName, beanFieldType, beanFieldValue);
        text = text.replace("{filelds}", filelds);
        text = text.replace("{getset}", getset(beanFieldName, beanFieldType));
        path = path + File.separator + "java" + File.separator;
        FileUtil.saveTextFile(text, path + getPackagePath(beanPackageName) + beanName + ".java");
        log.debug("生成java model：{}模板", beanName);
    }

    private static String getFields(List<String> beanFieldName, List<String> beanFieldType,
                                    List<String> beanFieldValue) {
        StringBuffer buffer = new StringBuffer();
        int size = beanFieldName.size();
        for (int i = 0; i < size; i++) {
            String name = beanFieldName.get(i);
            if ("id".equals(name) || "createTime".equals(name) || "updateTime".equals(name)) {
                continue;
            }
            if (isTimeColumn(name)) {
                continue;
            }
            String type = beanFieldType.get(i);
            buffer.append("\tprivate ").append(type).append(" ").append(name);
            // 默认值
//			String value = beanFieldValue.get(i);
//			if (!StringUtils.isEmpty(value)) {
//				buffer.append(" = ");
//				if (type.equals(String.class.getSimpleName())) {
//					value = "\"" + value + "\"";
//				} else if (type.equals(Double.class.getSimpleName())) {
//					value = value + "D";
//				} else if (type.equals(Float.class.getSimpleName())) {
//					value = value + "F";
//				} else if (type.equals(BigDecimal.class.getSimpleName())) {
//					value = "new BigDecimal(" + value + ")";
//				}
//
//				buffer.append(value);
//			}
            buffer.append(";\n");
        }

        return buffer.toString();
    }

    private static String getset(List<String> beanFieldName, List<String> beanFieldType) {
        StringBuffer buffer = new StringBuffer();
        int size = beanFieldName.size();
        for (int i = 0; i < size; i++) {
            String name = beanFieldName.get(i);
            if ("id".equals(name) || "createTime".equals(name) || "updateTime".equals(name)) {
                continue;
            }

            String type = beanFieldType.get(i);
            buffer.append("\tpublic ").append(type).append(" get")
                    .append(StringUtils.substring(name, 0, 1).toUpperCase() + name.substring(1, name.length()))
                    .append("() {\n");
            buffer.append("\t\treturn ").append(name).append(";\n");
            buffer.append("\t}\n");
            buffer.append("\tpublic ").append(type).append(" set")
                    .append(StringUtils.substring(name, 0, 1).toUpperCase() + name.substring(1, name.length()))
                    .append("() {\n");
            buffer.append("\t\treturn ").append(name).append(";\n");
            buffer.append("\t}\n");
        }

        return buffer.toString();
    }

    public static void saveJavaDao(GenerateInput input) {
        String path = input.getPath();
        String tableName = input.getTableName();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        String daoPackageName = input.getDaoPackageName();
        String daoName = input.getDaoName();
//        String finalPath = path + File.separator + "resources" + File.separator + "mybatis-mappers" + File.separator + "education"  + File.separator;

        String text = getTemplete("dao.txt");
        text = text.replace("{daoPackageName}", daoPackageName);
        text = text.replace("{beanPackageName}", beanPackageName);
        text = text.replace("{daoName}", daoName);
        text = text.replace("{table_name}", tableName);
        text = text.replace("{beanName}", beanName);
        text = text.replace("{beanParamName}", lowerFirstChar(beanName));
        text = text.replace("{descCH}", input.getDescCH());
        text = text.replace("{date}", DateFormatUtils.format(new Date(), DateFormatUtils
				.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));

        String insertColumns = getInsertColumns(input.getColumnNames());
        text = text.replace("{insert_columns}", insertColumns);
        String insertValues = getInsertValues(input.getColumnNames(), input.getBeanFieldName());
        text = text.replace("{insert_values}", insertValues);
        path = path + File.separator + "java" + File.separator;
        FileUtil.saveTextFile(text, path + getPackagePath(daoPackageName) + daoName + ".java");
        log.debug("生成java dao：{}模板", beanName);

        /*text = getTemplete("mapper.xml");
        text = text.replace("{daoPackageName}", daoPackageName);
        text = text.replace("{daoName}", daoName);
        text = text.replace("{table_name}", tableName);
        text = text.replace("{beanName}", beanName);
        String sets = getUpdateSets(input.getColumnNames(), input.getBeanFieldName());
        text = text.replace("{update_sets}", sets);
        String where = getWhere(input.getColumnNames(), input.getBeanFieldName());
        text = text.replace("{where}", where);
        FileUtil.saveTextFile(text, finalPath + beanName + "Mapper.xml");*/
    }

    private static String getInsertValues(List<String> columnNames, List<String> beanFieldName) {
        StringBuffer buffer = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            if (isTimeColumn(column)) {
                continue;
            }
            if (!"id".equals(column)) {
                buffer.append("#{").append(beanFieldName.get(i)).append("}, ");
            }
        }

        String sets = StringUtils.substringBeforeLast(buffer.toString(), ",");
        return sets;
    }

    private static String getInsertColumns(List<String> columnNames) {
        StringBuffer buffer = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            if (isTimeColumn(column)) {
                continue;
            }
            if (!"id".equals(column)) {
                buffer.append(column).append(", ");
            }
        }

        String insertColumns = StringUtils.substringBeforeLast(buffer.toString(), ",");
        return insertColumns;
    }

    private static boolean isTimeColumn(String column) {
        return ("createtime").equalsIgnoreCase(column) || ("updatetime").equalsIgnoreCase(column);
    }

    private static String getUpdateSets(List<String> columnNames, List<String> beanFieldName) {
        StringBuffer buffer = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            if (isTimeColumn(column)) {
                continue;
            }
            if (!"id".equals(column)) {
                buffer.append("\t\t\t<if test=\"" + column + " != null\">\n");
                buffer.append("\t\t\t\t" + column).append(" = ").append("#{").append(beanFieldName.get(i))
                        .append("}, \n");
                buffer.append("\t\t\t</if>\n");
            }
        }

        return buffer.toString();
    }

    private static String getWhere(List<String> columnNames, List<String> beanFieldName) {
        StringBuffer buffer = new StringBuffer();
        int size = columnNames.size();
        for (int i = 0; i < size; i++) {
            String column = columnNames.get(i);
            if (isTimeColumn(column)) {
                continue;
            }
            buffer.append("\t\t\t<if test=\"params." + column + " != null and params." + column + " != ''\">\n");
            buffer.append("\t\t\t\tand " + column).append(" = ").append("#{params.").append(beanFieldName.get(i))
                    .append("} \n");
            buffer.append("\t\t\t</if>\n");
        }

        return buffer.toString();
    }

    /**
     * 变量名
     *
     * @param beanName
     * @return
     */
    public static String lowerFirstChar(String beanName) {
        String name = StrUtil.str2hump(beanName);
        String firstChar = name.substring(0, 1);
        name = name.replaceFirst(firstChar, firstChar.toLowerCase());

        return name;
    }

    private static String getPackagePath(String packageName) {
        String packagePath = packageName.replace(".", "/");
        if (!packagePath.endsWith("/")) {
            packagePath = packagePath + "/";
        }

        return packagePath;
    }

    public static void saveController(GenerateInput input) {
        String path = input.getPath();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        String serviceName = input.getServiceName();
        String servicePkName = input.getServicePkName();
        String interfaceName = "I" + serviceName;

        path = path + File.separator + "java" + File.separator;
        String text = getTemplete("controller.txt");
        text = text.replace("{servicePackageName}", servicePkName);
        text = text.replace("{beanPackageName}", beanPackageName);
        text = text.replace("{serviceName}", interfaceName);
        text = text.replace("{serviceParamName}", lowerFirstChar(serviceName) + "Impl");
        text = text.replace("{beanName}", beanName);
        text = text.replace("{beanParamName}", lowerFirstChar(beanName));
        text = text.replace("{controllerPkgName}", input.getControllerPkgName());
        text = text.replace("{controllerName}", input.getControllerName());
        text = text.replace("{date}", DateFormatUtils.format(new Date(), DateFormatUtils
				.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        text = text.replace("{descCH}", input.getDescCH());
        FileUtil.saveTextFile(text, path + getPackagePath(input.getControllerPkgName())
                + input.getControllerName() + ".java");
        log.debug("生成controller：{}模板", beanName);
    }

    public static void saveService(GenerateInput input) {
        String path = input.getPath();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        String serviceName = input.getServiceName();
        String servicePkName = input.getServicePkName();
        String interfaceName = "I" + serviceName;


        String text = getTemplete("interface.txt");
        text = text.replace("{servicePkName}", servicePkName);
        text = text.replace("{beanPackageName}", beanPackageName);
        text = text.replace("{beanName}", beanName);
        text = text.replace("{beanParamName}", lowerFirstChar(beanName));
        text = text.replace("{interfaceName}", interfaceName);
        text = text.replace("{date}", DateFormatUtils.format(new Date(), DateFormatUtils
				.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        text = text.replace("{descCH}", input.getDescCH());

        path = path + File.separator + "java" + File.separator;
        FileUtil.saveTextFile(text, path + getPackagePath(input.getServicePkName())
                + interfaceName + ".java");

        log.debug("生成Service：{}模板", beanName);
    }

    public static void saveServiceImpl(GenerateInput input) {
        String path = input.getPath();
        String beanPackageName = input.getBeanPackageName();
        String beanName = input.getBeanName();
        String serviceName = input.getServiceName();
        String servicePkName = input.getServicePkName();
        String interfaceName = "I" + serviceName;
        String daoPackageName = input.getDaoPackageName();
        String daoName = input.getDaoName();

        String text = getTemplete("serviceImpl.txt");
        text = text.replace("{servicePkName}", servicePkName);
        text = text.replace("{serviceName}", serviceName);
        text = text.replace("{beanPackageName}", beanPackageName);
        text = text.replace("{beanName}", beanName);
        text = text.replace("{beanParamName}", lowerFirstChar(beanName));
        text = text.replace("{interfaceName}", interfaceName);
        text = text.replace("{daoPackageName}", daoPackageName);
        text = text.replace("{daoName}", daoName);
        text = text.replace("{daoParamName}", lowerFirstChar(daoName));
        text = text.replace("{date}", DateFormatUtils.format(new Date(), DateFormatUtils
				.ISO_8601_EXTENDED_DATE_FORMAT.getPattern()));
        text = text.replace("{descCH}", input.getDescCH());

        path = path + File.separator + "java" + File.separator;
        FileUtil.saveTextFile(text, path + getPackagePath(input.getServicePkName() + ".impl")
                + serviceName + "Impl" + ".java");

        log.debug("生成Service：{}模板", beanName);
    }

    public static void saveHtmlList(GenerateInput input) {
        String path = input.getPath();
        String beanName = input.getBeanName();
        String beanParamName = lowerFirstChar(beanName);
        String date = DateFormatUtils.format(new Date(), DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());
        String jsPath = path + File.separator + "resources" + File.separator + "static" + File.separator + "js" + File.separator + "pageJs" + File
				.separator + input.getDescEN() + File.separator;
        String pagePath = path + File.separator + "resources" + File.separator + "static" + File.separator + "pages" + File.separator + input
				.getDescEN() + File.separator;

        String text = getTemplete("htmlList.txt");
        text = text.replace("{beanParamName}", beanParamName);
        List<String> beanFieldNames = input.getBeanFieldName();
        text = text.replace("{ths}", getHtmlThs(beanFieldNames));
        text = text.replace("{descEN}", input.getDescEN());
        text = text.replace("{beanName}", beanName);
        FileUtil.saveTextFile(text, pagePath + beanParamName + "List.html");

        text = getTemplete("htmlListJs.txt");
        text = text.replace("{date}", date);
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{beanName}", beanName);
        text = text.replace("{columnsDatas}", getHtmlColumnsDatas(beanFieldNames));
        text = text.replace("{jsDom}", getJsThs(beanFieldNames));
        text = text.replace("{jsDom2}", getJsThs2(beanFieldNames));
        text = text.replace("{descEN}", input.getDescEN());
        text = text.replace("{descCH}", input.getDescCH());
        text = text.replace("{routeFlag}", input.getRouteFlag());
        FileUtil.saveTextFile(text, jsPath + beanParamName + "List.js");
        log.debug("生成查询页面：{}模板", beanName);

        text = getTemplete("htmlAdd.txt");
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
        text = text.replace("{descEN}", input.getDescEN());
        text = text.replace("{beanName}", beanName);
        FileUtil.saveTextFile(text, pagePath + "add" + beanName + ".html");

        text = getTemplete("htmlAddJs.txt");
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
        text = text.replace("{jsDom}", getJsThs(beanFieldNames));
        text = text.replace("{jsDom2}", getJsThs2(beanFieldNames));
        text = text.replace("{descEN}", input.getDescEN());
        text = text.replace("{descCH}", input.getDescCH());
        text = text.replace("{date}", date);
        text = text.replace("{routeFlag}", input.getRouteFlag());
        FileUtil.saveTextFile(text, jsPath + "add" + beanName + ".js");
        log.debug("生成添加页面：{}模板", beanName);

        text = getTemplete("htmlUpdate.txt");
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
        text = text.replace("{descEN}", input.getDescEN());
        text = text.replace("{beanName}", beanName);
        FileUtil.saveTextFile(text, pagePath + "update" + beanName + ".html");

        text = getTemplete("htmlUpdateJs.txt");
        text = text.replace("{beanParamName}", beanParamName);
        text = text.replace("{addDivs}", getAddDivs(beanFieldNames));
        text = text.replace("{initData}", getInitData(beanFieldNames));
        text = text.replace("{jsDom}", getJsThs(beanFieldNames));
        text = text.replace("{jsDom2}", getJsThs2(beanFieldNames));
        text = text.replace("{descEN}", input.getDescEN());
        text = text.replace("{descCH}", input.getDescCH());
        text = text.replace("{date}", date);
        FileUtil.saveTextFile(text, jsPath + "update" + beanName + ".js");
        log.debug("生成修改页面：{}模板", beanName);
    }

    private static CharSequence getInitData(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
//			builder.append("\t\t\t\t\t\t$('#" + b + "').val(data." + b + ");\n");
            builder.append("\t\t\t\t\t\t_self.getPageDom()." + b + ".val(data." + b + ");\n");
        });

        return builder.toString();
    }

    private static String getAddDivs(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            if (!"id".equals(b)) {
                builder.append("\t\t\t<div class='form-group'>\n");
                builder.append("\t\t\t\t<label class='col-md-2 control-label'>" + b + "</label>\n");
                builder.append("\t\t\t\t<div class='col-md-10'>\n");
                builder.append("\t\t\t\t\t<input class='form-control' placeholder='" + b + "' type='text' name='" + b
                        + "' id='" + b + "'>\n");
                builder.append("\t\t\t\t</div>\n");
                builder.append("\t\t\t</div>\n");
            }
        });
        return builder.toString();
    }

    private static String getHtmlThs(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            builder.append("\t\t\t\t\t\t\t\t\t<th>{beanFieldName}</th>\n".replace("{beanFieldName}", b));
        });
        return builder.toString();
    }

    private static String getJsThs(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            builder.append("\t\t\t\t\t{beanFieldName} : $(\"#{beanFieldName}\"),\n".replace("{beanFieldName}", b));
        });
        return builder.toString();
    }

    private static String getJsThs2(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            builder.append("\t\t\t\t\t\t\t\t\td.{beanFieldName} = page.$dom.{beanFieldName}.val();\n".replace("{beanFieldName}", b));
        });
        return builder.toString();
    }

    private static String getHtmlColumnsDatas(List<String> beanFieldNames) {
        StringBuilder builder = new StringBuilder();
        beanFieldNames.forEach(b -> {
            builder.append("\t\t\t\t{\"data\" : \"{beanFieldName}\", \"defaultContent\" : \"\"},\n"
                    .replace("{beanFieldName}", b));
        });

        builder.append("");
        return builder.toString();
    }

}
