package com.sndshun.jpa.hibernate;

import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

/**
 * 自定义 JPA 命名策略实现
 * 表名 字段名 使用反引号包裹
 *
 * @author sndshun
 * @date 2023/11/15
 */
public class JpaNamingStrategyStandardImpl extends CamelCaseToUnderscoresNamingStrategy {

    /**
     * 自定义表名
     */
    @Override
    public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier physicalTableName = super.toPhysicalTableName(name, jdbcEnvironment);
        return Identifier.toIdentifier("`" + physicalTableName.getText() + "`", name.isQuoted());
    }

    /**
     * 自定义列名
     */
    @Override
    public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment jdbcEnvironment) {
        Identifier physicalColumnName = super.toPhysicalColumnName(name, jdbcEnvironment);
        return Identifier.toIdentifier("`" + physicalColumnName.getText() + "`", name.isQuoted());
    }
}