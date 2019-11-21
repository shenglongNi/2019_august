package com.nsl.mybatis.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegerArrayTypeHandler extends BaseTypeHandler<Integer[]> {

    private static Logger logger = LoggerFactory.getLogger(IntegerArrayTypeHandler.class);
    private static final ObjectMapper mapper = new ObjectMapper();


    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Integer[] integers, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, arrayToString(integers));
    }

    @Override
    public Integer[] getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        return this.stringToArray(resultSet.getString(columnName));
    }

    @Override
    public Integer[] getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        return this.stringToArray(resultSet.getString(columnIndex));
    }

    @Override
    public Integer[] getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        return this.stringToArray(callableStatement.getString(columnIndex));
    }


    private String arrayToString(Integer[] arrs) {
        try {
            return mapper.writeValueAsString(arrs);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage(), e);
        }
        return "[]";
    }


    private Integer[] stringToArray(String s) {
        try {
            return mapper.readValue(s, Integer[].class);
        } catch (IOException e) {

            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
