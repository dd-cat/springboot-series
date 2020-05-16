package com.example.util.sql;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SqlUtil {
    /**
     * @param ids   集合参数
     * @param field 字段
     * @return field in () or in()...
     */
    public static String getOracleSQLIn(List<?> ids, String field) {
        if (ids.size() > 0) {
            int count = 1000;//1000个分割

            int len = ids.size();
            int size = len % count;
            if (size == 0) {
                size = len / count;
            } else {
                size = (len / count) + 1;
            }
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < size; i++) {
                int fromIndex = i * count;
                int toIndex = Math.min(fromIndex + count, len);
                String productId = StringUtils.defaultIfEmpty(StringUtils.join(ids.subList(fromIndex, toIndex), "','"), "");

                if (i != 0) {
                    builder.append(" or ");
                }
                builder.append(field).append(" in ('").append(productId).append("')");
            }
            return StringUtils.defaultIfEmpty(builder.toString(), field + " in ('')");
        }
        return "";
    }

    /**
     * 仅支持字母、数字、下划线、空格、逗号（支持多个字段排序）
     */
    public static String SQL_PATTERN = "[a-zA-Z0-9_\\ \\,]+";

    /**
     * 检查字符，防止注入绕过
     */
    public static String escapeOrderBySql(String value) {
        if (StringUtils.isNotEmpty(value) && !isValidOrderBySql(value)) {
            return StringUtils.EMPTY;
        }
        return value;
    }

    /**
     * 验证 order by 语法是否符合规范
     */
    public static boolean isValidOrderBySql(String value) {
        return value.matches(SQL_PATTERN);
    }
}
